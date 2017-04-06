import Utils from './utils/utils';
import Config from './config/config';
import StockApis from './api/stockApis';
import SearchParamsUtils from './utils/searchParamsUtils';
import ResultColumnsUtils from './utils/resultColumnsUtils';
import SearchFilesIterator from './models/searchFilesIterator';
import Constants from './constants/constants';

/**
 * Checks if the queryParams has nb_results column
 * @param {!object} queryParams object of query parameters
 * @returns {boolean} true if nb_results present, false otherwise
 */
function isNBResultsColumnPreset(queryParams) {
  // we need result column for hasMoreFiles & nextSearchFiles to work properly
  if (queryParams.result_columns
        && !queryParams.result_columns.includes(Constants.RESULT_COLUMNS.NB_RESULTS)) {
    return false;
  }

  return true;
}

/**
 * Add result column nb_results
 * @param {!object} queryParams object of query parameters
 * @returns {object} query parameter object with nb_results column if added
 */
function addResultColumnNBResults(queryParams) {
  // we need result column for hasMoreFiles & nextSearchFiles to work properly
  if (queryParams.result_columns) {
    queryParams.result_columns.push(Constants.RESULT_COLUMNS.NB_RESULTS);
  }

  return queryParams;
}

/**
 * Validates the query parameters
 * @param {!object} queryParams object of query parameters
 * @param {string} accessToken access token to be validated if is_licensed requested in results
 * column
 */
function validateQueryParams(queryParams, accessToken) {
  Object.keys(queryParams).forEach((param) => {
    if (param === Constants.QUERY_PARAMS_PROPS.LOCALE) {
      if (typeof queryParams[param] !== 'string') {
        throw new Error('locale expects string only. For e.g. en-US, fr-FR etc.');
      }
    } else if (param === Constants.QUERY_PARAMS_PROPS.SEARCH_PARAMETERS) {
      SearchParamsUtils.validate(queryParams[param]);
    } else if (param === Constants.QUERY_PARAMS_PROPS.RESULT_COLUMNS) {
      ResultColumnsUtils.validate(queryParams[param]);
    } else if (param !== Constants.QUERY_PARAMS_PROPS.SIMILAR_IMAGE) {
      throw new Error(`Invalid query parameter '${param}'!`);
    }
  });

  // Check if is_licensed is requested in result_columns
  if (queryParams.result_columns
      && queryParams.result_columns.includes(Constants.RESULT_COLUMNS.IS_LICENSED)
      && !accessToken) {
    throw new Error('Access Token missing! Result Column \'is_licensed\' requires authentication.');
  }

  if (queryParams.search_parameters
      && queryParams.search_parameters.similar_image === 1
      && !queryParams.similar_image) {
    throw new Error('Image Data missing! Search parameter similar_image requires similar_image in query parameters');
  }
}

/**
 * function to check if AdobeStock initialized properly
 * @returns {boolean} return true if AdobeStock initialized, false otherwise
 */
function isAdobeStockInitialized() {
  if (this.config.isConfigInitialized() && this.stockApis) {
    return true;
  }

  return false;
}

/**
 * The Adobe Stock library class
 */
class AdobeStock {

  /**
   * Constructor for AdobeStock class
   * @param {string} apiKey api key of the client used for the header for the api calls
   * @param {string} product header for the api calls
   * @param {Environment} targetEnv defines the set environment for the library
   */
  constructor(apiKey, product, targetEnv) {
    this.config = new Config(apiKey, product, targetEnv);
    this.stockApis = new StockApis(this.config);
  }
  /**
   * Enum for stack environment
   */
  static get ENVIRONMENT() {
    return Constants.ENVIRONMENT;
  }

  /**
   * Enums for search parameters and results columns
   */
  static get SEARCH_PARAMS() {
    return Constants.SEARCH_PARAMS;
  }

  static get SEARCH_PARAMS_TYPE() {
    return Constants.SEARCH_PARAMS_TYPE;
  }

  static get SEARCH_PARAMS_ORDER() {
    return Constants.SEARCH_PARAMS_ORDER;
  }

  static get SEARCH_PARAMS_ORIENTATION() {
    return Constants.SEARCH_PARAMS_ORIENTATION;
  }

  static get SEARCH_PARAMS_HAS_RELEASES() {
    return Constants.SEARCH_PARAMS_HAS_RELEASES;
  }

  static get SEARCH_PARAMS_3D_TYPES() {
    return Constants.SEARCH_PARAMS_3D_TYPES;
  }

  static get SEARCH_PARAMS_TEMPLATE_CATEGORIES() {
    return Constants.SEARCH_PARAMS_TEMPLATE_CATEGORIES;
  }

  static get SEARCH_PARAMS_TEMPLATE_TYPES() {
    return Constants.SEARCH_PARAMS_TEMPLATE_TYPES;
  }

  static get SEARCH_PARAMS_THUMB_SIZES() {
    return Constants.SEARCH_PARAMS_THUMB_SIZES;
  }

  static get SEARCH_PARAMS_AGE() {
    return Constants.SEARCH_PARAMS_AGE;
  }

  static get SEARCH_PARAMS_VIDEO_DURATION() {
    return Constants.SEARCH_PARAMS_VIDEO_DURATION;
  }

  static get RESULT_COLUMNS() {
    return Constants.RESULT_COLUMNS;
  }

  /**
   * function to search stock files by category id
   * @param {string} accessToken (required) access token to be used for Authorization header
   * @param {integer} categoryId (required) the identifier of category to be searched for files
   * @param {string} locale (optional) location language code
   * @param {object} filters (optional) of search_parameters
   * @param {object} resultColumns (optional) desired result columns in the response
   * @returns {SearchFilesIterator} object of SearchFilesIterator type
   */
  searchFilesByCategory(accessToken, categoryId, locale, filters, resultColumns) {
    const queryParams = {
      locale,
      search_parameters: {
        category: categoryId,
      },
    };

    if (filters && Utils.isObject(filters)) {
      Object.assign(queryParams.search_parameters, filters);
    } else if (filters && !Utils.isObject(filters)) {
      throw new Error('filters expects Object!');
    }

    return this.searchFiles(accessToken, queryParams, resultColumns);
  }

  /**
   * function to search similar stock files by media id
   * @param {string} accessToken (required) access token to be used for Authorization header
   * @param {integer} mediaId (required) the identifier of media to be searched for similar files
   * @param {string} locale (optional) location language code
   * @param {object} filters (optional) of search_parameters
   * @param {object} resultColumns (optional) desired result columns in the response
   * @returns {SearchFilesIterator} object of SearchFilesIterator type
   */
  searchSimilarFilesById(accessToken, mediaId, locale, filters, resultColumns) {
    const queryParams = {
      locale,
      search_parameters: {
        similar: mediaId,
      },
    };

    if (filters && Utils.isObject(filters)) {
      Object.assign(queryParams.search_parameters, filters);
    } else if (filters && !Utils.isObject(filters)) {
      throw new Error('filters expects Object!');
    }

    return this.searchFiles(accessToken, queryParams, resultColumns);
  }

  /**
   * function to search stock files by keywords
   * @param {string} accessToken (required) access token to be used for Authorization header
   * @param {string} keywords (required) the keywords to be searched for files
   * @param {string} locale (optional) location language code
   * @param {object} filters (optional) of search_parameters
   * @param {object} resultColumns (optional) desired result columns in the response
   * @returns {SearchFilesIterator} object of SearchFilesIterator type
   */
  searchFilesByKeywords(accessToken, keywords, locale, filters, resultColumns) {
    const queryParams = {
      locale,
      search_parameters: {
        words: keywords,
      },
    };

    if (filters && Utils.isObject(filters)) {
      Object.assign(queryParams.search_parameters, filters);
    } else if (filters && !Utils.isObject(filters)) {
      throw new Error('filters expects Object!');
    }

    return this.searchFiles(accessToken, queryParams, resultColumns);
  }

  /**
   * function to search stock files by search params
   * @param {string} accessToken (required) access token to be used for Authorization header
   * @param {object} queryParams (optional) the params to be used for image search & filter
   * @param {object} resultColumns (optional) desired result columns in the response
   * @returns {SearchFilesIterator} object of SearchFilesIterator type
   */
  searchFiles(accessToken, queryParams, resultColumns) {
    let localQueryParams = {};
    let nbResultsPresent = false;

    if (!isAdobeStockInitialized.call(this)) {
      throw new Error('Library not initialized! Please initialize the library first.');
    }

    if (queryParams && Utils.isObject(queryParams)) {
      localQueryParams = queryParams;
    } else if (queryParams && !Utils.isObject(queryParams)) {
      throw new Error('queryParams expects Object!');
    }

    if (resultColumns && Utils.isArray(resultColumns)) {
      localQueryParams.result_columns = resultColumns;
    } else if (resultColumns && !Utils.isArray(resultColumns)) {
      throw new Error('resultColumns expects Array!');
    }

    validateQueryParams(localQueryParams, accessToken);

    // we need result column for hasMoreFiles & nextSearchFiles to work properly
    nbResultsPresent = isNBResultsColumnPreset(localQueryParams);

    if (!nbResultsPresent) {
      localQueryParams = addResultColumnNBResults(localQueryParams);
    }

    return new SearchFilesIterator(this.stockApis,
                                    accessToken,
                                    localQueryParams,
                                    nbResultsPresent);
  }
}

module.exports = AdobeStock;
