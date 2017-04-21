import SearchParamsUtils from './../utils/searchParamsUtils';
import ResultColumnsUtils from './../utils/resultColumnsUtils';
import Utils from './../utils/utils';
import Constants from './../constants/constants';

/**
 * Creates the url for search files api
 * @param {string} endpoint api endpoint url
 * @param {object} queryParams object of query parameters
 * @returns {string} api url
 */
function createSearchFilesApiUrl(endpoint, queryParams) {
  let url = endpoint;
  const paramsStr = [];

  if (queryParams.locale) {
    paramsStr.push(`locale=${encodeURIComponent(queryParams.locale)}`);
  }

  if (queryParams.search_parameters) {
    paramsStr.push(SearchParamsUtils.encodeURI(queryParams.search_parameters));
  }

  if (queryParams.result_columns) {
    paramsStr.push(ResultColumnsUtils.encodeURI(queryParams.result_columns));
  }

  if (paramsStr.length > 0) {
    url = `${url}?${paramsStr.join('&')}`;
  }

  return url;
}

/**
 * Creates the url for search files api
 * @param {string} endpoint api endpoint url
 * @param {object} queryParams object of query parameters
 * @returns {string} api url
 */
function createSearchCategoryApiUrl(endpoint, queryParams) {
  let url = endpoint;
  const paramsStr = [];

  if (queryParams.locale) {
    paramsStr.push(`${Constants.QUERY_PARAMS_PROPS.LOCALE}=${encodeURIComponent(queryParams.locale)}`);
  }

  if (queryParams.category_id) {
    paramsStr.push(`${Constants.QUERY_PARAMS_PROPS.CATEGORY}=${encodeURIComponent(queryParams.category_id)}`);
  }

  if (paramsStr.length > 0) {
    url = `${url}?${paramsStr.join('&')}`;
  }

  return url;
}

/**
 * Get the http method for the search files api call
 * @param {object} queryParams object of query parameters
 * @returns {string} http method
 */
function getSearchFilesApiHttpMethod(queryParams) {
  let method = Constants.HTTPMETHOD.GET;
  // The http method for similar image search is POST
  if (queryParams.search_parameters &&
    queryParams.search_parameters.similar_image === 1) {
    method = Constants.HTTPMETHOD.POST;
  }

  return method;
}

export default class StockApis {
  /*
   * Constructor of StockApis class
   */
  constructor(config) {
    this.config = config;
  }
  /**
   * Creates the url for search api
   * @param {string} accessToken to be sent with Authorization header
   * @param {object} queryParams query params object to be used for search files api query params
   * @returns {promise} returns promise
   */
  searchFiles(accessToken, queryParams) {
    return new Promise((onSuccess, onError) => {
      try {
        const httpMethod = getSearchFilesApiHttpMethod(queryParams);

        const requestURL = createSearchFilesApiUrl(this.config.endpoints.search, queryParams);

        const headers = {
          'x-api-key': this.config.x_api_key,
          'x-product': this.config.x_product,
        };

        if (accessToken) {
          headers.Authorization = `Bearer ${accessToken}`;
        }

        if (httpMethod === Constants.HTTPMETHOD.GET) {
          Utils.makeGetAjaxCall(requestURL, headers)
              .then(onSuccess, onError);
        } else {
          // multipart/form-data request for similar_image search
          const fd = new FormData();
          fd.append('similar_image', queryParams.similar_image);
          Utils.makeMultiPartAjaxCall(requestURL, headers, fd)
              .then(onSuccess, onError);
        }
      } catch (e) {
        if (onError) {
          onError(e);
        }
      }
    });
  }

  /**
   * Creates the url for category search api and fetch the result
   * @param {object} queryParams query params object to be used for search category api query params
   * @returns {promise} returns promise
   */
  searchCategory(queryParams) {
    return new Promise((onSuccess, onError) => {
      try {
        const requestURL = createSearchCategoryApiUrl(this.config.endpoints.category, queryParams);

        const headers = {
          'x-api-key': this.config.x_api_key,
          'x-product': this.config.x_product,
        };

        Utils.makeGetAjaxCall(requestURL, headers)
             .then(onSuccess, onError);
      } catch (e) {
        if (onError) {
          onError(e);
        }
      }
    });
  }

  /**
   * Creates the url for category search api and fetch the result
   * @param {object} queryParams query params object to be used for search category api query params
   * @returns {promise} returns promise
   */
  searchCategoryTree(queryParams) {
    return new Promise((onSuccess, onError) => {
      try {
        const requestURL = createSearchCategoryApiUrl(this.config.endpoints.category_tree,
                                                      queryParams);

        const headers = {
          'x-api-key': this.config.x_api_key,
          'x-product': this.config.x_product,
        };

        Utils.makeGetAjaxCall(requestURL, headers)
             .then(onSuccess, onError);
      } catch (e) {
        if (onError) {
          onError(e);
        }
      }
    });
  }

}
