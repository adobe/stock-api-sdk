import Utils from './../utils/utils';
import Constants from './../constants/constants';

const DEFAULT_SEARCH_FILES_LIMIT = 32;

/**
 * Helper function for SearchFilesIterator to clone Query Params object
 * @param {object} queryParams object to be cloned
 * @returns {object} the cloned object
 */
function cloneQueryParamsHelper(queryParams) {
  const cloneQueryParams = {};

  Object.keys(queryParams).forEach((key) => {
    if (key === Constants.QUERY_PARAMS_PROPS.SEARCH_PARAMETERS
      || key === Constants.QUERY_PARAMS_PROPS.RESULT_COLUMNS) {
      cloneQueryParams[key] = JSON.parse(JSON.stringify(queryParams[key]));
    } else {
      cloneQueryParams[key] = queryParams[key];
    }
  });

  return cloneQueryParams;
}

/**
 * Stores queryParams
 * @private
 */
function storeQueryParamsState() {
  this.lastQueryParams = this.queryParams;
}

/**
 * Restores queryParams
 * @private
 */
function revertToLastQueryParamsState() {
  if (this.lastQueryParams) {
    this.queryParams = this.lastQueryParams;
  }
  this.lastQueryParams = null;
}

/**
 * Does stuff on doApiCall success
 * @param {string} response string from the api
 */
function doOnSuccess(response) {
  this.response = response;

  this.apiInProgress = false;
}

/**
 * Does stuff on doApiCall failure
 */
function doOnError() {
  revertToLastQueryParamsState.call(this);
  this.apiInProgress = false;
}

/**
 * Do search files api call
 * @param {object} queryParams query parameter object
 * @returns {promise} returns promise
 */
function doApiCall(queryParams) {
  return new Promise((onSuccess, onError) => {
    if (this.apiInProgress) {
      throw new Error('Some other search is already in progress!');
    }

    try {
      this.apiInProgress = true;

      storeQueryParamsState.call(this);
      this.queryParams = queryParams;

      if (this.stockApis) {
        this.stockApis.searchFiles(this.accessToken, this.queryParams)
              .then((response) => {
                doOnSuccess.call(this, response);
                if (onSuccess) {
                  onSuccess();
                }
              },
              (error) => {
                doOnError.call(this, error);
                if (onError) {
                  onError(error);
                }
              });
      } else {
        throw new Error('Library couldn\'t initialize properly! Please initialize the library first.');
      }
    } catch (e) {
      doOnError.call(this, e);
      if (onError) {
        onError(e);
      }
    }
  });
}

/**
 * SearchFilesIterator class
 */
export default class SearchFilesIterator {

  /**
   * constructor of SearchFilesIterator
   * @param {StockApis} stockApis api object
   * @param {string} accessToken access token string to be used with api calls
   * @param {object} queryParams query params object to be used for search files api query params
   * @param {boolean} nbResultsPresent nb_results column was present in user requested columns
   */
  constructor(stockApis, accessToken, queryParams, nbResultsPresent) {
    this.stockApis = stockApis;
    this.accessToken = accessToken;
    this.queryParams = queryParams;
    this.nbResultsPresent = nbResultsPresent;
    this.response = {};
    this.lastQueryParams = null;
    this.apiInProgress = false;

    if (!this.queryParams.search_parameters.limit) {
      this.queryParams.search_parameters.limit = DEFAULT_SEARCH_FILES_LIMIT;
    }

    // Set the offset to one page behind to the current page
    if (!this.queryParams.search_parameters.offset) {
      this.queryParams.search_parameters.offset = 0;
    }

    this.queryParams.search_parameters.offset -= this.queryParams.search_parameters.limit;
  }

  /**
  * Returns total search files available
  * @returns {integer} total search files available
   */
  totalSearchFiles() {
    if (this.queryParams.search_parameters.offset >= 0 && this.response.nb_results) {
      return this.response.nb_results;
    }
    return Constants.SEARCH_FILES_ITERATOR_RETURN_ERROR;
  }

  /**
   * Returns total search results pages
   * @returns {integer} total search results (pages) available
   */
  totalSearchPages() {
    if (this.queryParams.search_parameters.offset >= 0 && this.response.nb_results) {
      return Math.ceil(this.response.nb_results / this.queryParams.search_parameters.limit);
    }
    return Constants.SEARCH_FILES_ITERATOR_RETURN_ERROR;
  }

  /**
   * Returns current search results page index
   * @returns {integer} index of current search results (page) available
   */
  currentSearchPageIndex() {
    if (this.queryParams.search_parameters.offset >= 0 && this.response.nb_results) {
      const offset = this.queryParams.search_parameters.offset;
      return Math.ceil(offset / this.queryParams.search_parameters.limit);
    }
    return Constants.SEARCH_FILES_ITERATOR_RETURN_ERROR;
  }

  /**
   * Returns response from last api call
   * @returns {object} response object available from last api call
   */
  getResponse() {
    const userResponse = cloneQueryParamsHelper(this.response);

    if (!this.nbResultsPresent && userResponse.nb_results) {
      delete userResponse.nb_results;
    }

    return userResponse;
  }

  /**
   * function to get to next search results page
   * @returns {promise} returns the promise
   */
  next() {
    return new Promise((onSuccess, onError) => {
      try {
        const queryParams = cloneQueryParamsHelper(this.queryParams);

        const limit = queryParams.search_parameters.limit;

        if (!queryParams.search_parameters.offset) {
          queryParams.search_parameters.offset = limit;
        } else {
          queryParams.search_parameters.offset += limit;
        }

        if (this.response.nb_results
            && queryParams.search_parameters.offset >= this.response.nb_results) {
          throw new Error('No more search results available!');
        }

        doApiCall.call(this, queryParams)
              .then(onSuccess, onError);
      } catch (er) {
        if (onError) {
          onError(er);
        }
      }
    });
  }

  /**
   * function to get to previous search results page
   * @returns {promise} returns the promise
   */
  previous() {
    return new Promise((onSuccess, onError) => {
      try {
        const queryParams = cloneQueryParamsHelper(this.queryParams);

        queryParams.search_parameters.offset -= queryParams.search_parameters.limit;

        if (queryParams.search_parameters.offset < 0) {
          throw new Error('No more search results available!');
        }

        doApiCall.call(this, queryParams)
              .then(onSuccess, onError);
      } catch (er) {
        if (onError) {
          onError(er);
        }
      }
    });
  }

  /**
   * function to skip to a specific search results page
   * @param {integer} pageIndex (required) the index of page to skip on
   * @returns {promise} returns the promise
   */
  skipTo(pageIndex) {
    return new Promise((onSuccess, onError) => {
      try {
        const totalPages = this.totalSearchPages();

        if (!Utils.isInteger(pageIndex)
              || pageIndex < 0
              || (totalPages !== Constants.SEARCH_FILES_ITERATOR_RETURN_ERROR
                  && pageIndex >= totalPages)) {
          throw new Error('Page index out of bounds!');
        }

        const queryParams = cloneQueryParamsHelper(this.queryParams);

        queryParams.search_parameters.offset = pageIndex * queryParams.search_parameters.limit;

        if (queryParams.search_parameters.offset < 0) {
          throw new Error('No more search results available!');
        }

        doApiCall.call(this, queryParams)
              .then(onSuccess, onError);
      } catch (er) {
        if (onError) {
          onError(er);
        }
      }
    });
  }
}
