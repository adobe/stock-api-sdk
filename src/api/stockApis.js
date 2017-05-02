import SearchParamsUtils from './../utils/searchParamsUtils';
import ResultColumnsUtils from './../utils/resultColumnsUtils';
import Utils from './../utils/utils';
import DownSamplingUtils from './../utils/downSamplingUtils';
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
 * Creates the url for access member profile api
 * @param {string} (required) endpoint api endpoint url
 * @param {integer} contentId (required) asset's unique identifer
 * @param {string} license (required) licensing state for the asset.
 * @param {string} locale (optional) location language code
 * @returns {string} api url
 */
function createAccessMemberProfileApiUrl(endpoint, contentId, license, locale) {
  let url = endpoint;
  const paramsStr = [];

  if (locale) {
    paramsStr.push(`locale=${encodeURIComponent(locale)}`);
  }

  paramsStr.push(`content_id=${encodeURIComponent(contentId)}`);
  paramsStr.push(`license=${encodeURIComponent(license)}`);

  url = `${url}?${paramsStr.join('&')}`;

  return url;
}

/**
 * Creates the url for content info api
 * @param {string} (required) endpoint api endpoint url
 * @param {integer} contentId (required) asset's unique identifer
 * @param {string} license (required) licensing state for the asset.
 * @returns {string} api url
 */
function createContentInfoApiUrl(endpoint, contentId, license) {
  let url = endpoint;
  const paramsStr = [];

  paramsStr.push(`content_id=${encodeURIComponent(contentId)}`);
  paramsStr.push(`license=${encodeURIComponent(license)}`);

  url = `${url}?${paramsStr.join('&')}`;

  return url;
}

/**
 * Creates the url for request license api
 * @param {string} (required) endpoint api endpoint url
 * @param {integer} contentId (required) asset's unique identifer
 * @param {string} license (required) licensing state for the asset.
 * @returns {string} api url
 */
function createRequestLicenseApiUrl(endpoint, contentId, license) {
  let url = endpoint;
  const paramsStr = [];

  paramsStr.push(`content_id=${encodeURIComponent(contentId)}`);
  paramsStr.push(`license=${encodeURIComponent(license)}`);

  url = `${url}?${paramsStr.join('&')}`;

  return url;
}

/**
 * Creates the url for member abandon profile api
 * @param {string} (required) endpoint api endpoint url
 * @param {integer} contentId (required) asset's unique identifer
 * @param {string} state (required) user's purchase relationship to an asset
 * @returns {string} api url
 */
function createMemberAbandonApiUrl(endpoint, contentId, state) {
  let url = endpoint;
  const paramsStr = [];

  paramsStr.push(`content_id=${encodeURIComponent(contentId)}`);
  paramsStr.push(`state=${encodeURIComponent(state)}`);

  url = `${url}?${paramsStr.join('&')}`;

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
          const downsamplePromise = DownSamplingUtils.downsampleAndFixOrientationImage(
                                    queryParams.similar_image);
          downsamplePromise.then(
            (downsampledFile) => {
              fd.append('similar_image', downsampledFile);
              Utils.makeMultiPartAjaxCall(requestURL, headers, fd)
                  .then(onSuccess, onError);
            },
            (e) => {
              onError(e);
            },
          );
        }
      } catch (e) {
        onError(e);
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
        onError(e);
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
        onError(e);
      }
    });
  }

  /**
   * Acess member profile
   * @param {string} {required} accessToken to be sent with Authorization header
   * @param {integer} contentId (required) asset's unique identifer
   * @param {string} license (required) licensing state for the asset.
   * @param {string} locale (optional) location language code
   * @returns {promise} returns promise
   */
  accessMemberProfile(accessToken, contentId, license, locale) {
    return new Promise((onSuccess, onError) => {
      try {
        const requestURL = createAccessMemberProfileApiUrl(this.config.endpoints.user_profile
          , contentId, license, locale);

        const headers = {
          'x-api-key': this.config.x_api_key,
          'x-product': this.config.x_product,
        };

        headers.Authorization = `Bearer ${accessToken}`;

        Utils.makeGetAjaxCall(requestURL, headers)
              .then(onSuccess, onError);
      } catch (e) {
        onError(e);
      }
    });
  }

  /**
   * Notifies the system when a user abandons a licensing operation
   * @param {string} {required} accessToken to be sent with Authorization header
   * @param {integer} contentId (required) asset's unique identifer
   * @param {string} state (required) user's purchase relationship to an asset
   * @returns {promise} returns promise
   */
  memberAbandon(accessToken, contentId, state) {
    return new Promise((onSuccess, onError) => {
      try {
        const requestURL = createMemberAbandonApiUrl(this.config.endpoints.abandon
          , contentId, state);

        const headers = {
          'x-api-key': this.config.x_api_key,
          'x-product': this.config.x_product,
        };

        headers.Authorization = `Bearer ${accessToken}`;

        Utils.makeGetAjaxCall(requestURL, headers)
              .then(onSuccess, onError);
      } catch (e) {
        onError(e);
      }
    });
  }

  /**
   * Get licensing information about a specific asset for a specific user
   * @param {string} {required} accessToken to be sent with Authorization header
   * @param {integer} contentId (required) asset's unique identifer
   * @param {string} license (required) licensing state for the asset.
   * @returns {promise} returns promise
   */
  licenseInfo(accessToken, contentId, license) {
    return new Promise((onSuccess, onError) => {
      try {
        const requestURL = createContentInfoApiUrl(this.config.endpoints.license_info
          , contentId, license);

        const headers = {
          'x-api-key': this.config.x_api_key,
          'x-product': this.config.x_product,
        };

        headers.Authorization = `Bearer ${accessToken}`;

        Utils.makeGetAjaxCall(requestURL, headers)
              .then(onSuccess, onError);
      } catch (e) {
        onError(e);
      }
    });
  }

   /**
   * Requests a license for an asset for a specific user
   * @param {string} {required} accessToken to be sent with Authorization header
   * @param {integer} contentId (required) asset's unique identifer
   * @param {string} license (required) licensing state for the asset.
   * @returns {promise} returns promise
   */
  requestLicense(accessToken, contentId, license) {
    return new Promise((onSuccess, onError) => {
      try {
        const requestURL = createRequestLicenseApiUrl(this.config.endpoints.license
          , contentId, license);

        const headers = {
          'x-api-key': this.config.x_api_key,
          'x-product': this.config.x_product,
        };

        headers.Authorization = `Bearer ${accessToken}`;

        Utils.makeGetAjaxCall(requestURL, headers)
              .then(onSuccess, onError);
      } catch (e) {
        onError(e);
      }
    });
  }
}
