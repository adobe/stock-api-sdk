import Utils from './utils/utils';
import Config from './config/config';
import StockApis from './api/stockApis';
import SearchFilesIterator from './models/searchFilesIterator';
import LicenseHistoryIterator from './models/licenseHistoryIterator';
import Constants from './constants/constants';
import QueryParamsUtils from './utils/queryParamsUtils';
import LicenseParamsUtils from './utils/licenseParamsUtils';

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
   * Enum for query params props
   */
  static get QUERY_PARAMS_PROPS() {
    return Constants.QUERY_PARAMS_PROPS;
  }

  /**
   * Enums for search parameters and results columns
   */
  static get SEARCH_PARAMS() {
    return Constants.SEARCH_PARAMS;
  }

  static get LICENSE_HISTORY_SEARCH_PARAMS() {
    return Constants.LICENSE_HISTORY_SEARCH_PARAMS;
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

  static get SEARCH_PARAMS_PREMIUM() {
    return Constants.SEARCH_PARAMS_PREMIUM;
  }

  static get RESULT_COLUMNS() {
    return Constants.RESULT_COLUMNS;
  }

  static get LICENSE_HISTORY_RESULT_COLUMNS() {
    return Constants.LICENSE_HISTORY_RESULT_COLUMNS;
  }

  static get LICENSE_STATE_PARAMS() {
    return Constants.LICENSE_STATE_PARAMS;
  }

  static get PURCHASE_STATE_PARAMS() {
    return Constants.PURCHASE_STATE_PARAMS;
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

    QueryParamsUtils.validateSearchFileQueryParams(localQueryParams, accessToken);

    // we need result column for hasMoreFiles & nextSearchFiles to work properly
    nbResultsPresent = QueryParamsUtils.isNBResultsColumnPresent(localQueryParams);

    if (!nbResultsPresent) {
      localQueryParams = QueryParamsUtils.addResultColumnNBResults(localQueryParams);
    }

    return new SearchFilesIterator(this.stockApis,
                                    accessToken,
                                    localQueryParams,
                                    nbResultsPresent);
  }

  /**
   * function to get license history for a memeber
   * @param {string} accessToken (required) access token to be used for Authorization header
   * @param {object} queryParams (optional) the params to be used for getting license history
   * @param {object} resultColumns (optional) desired result columns in the response
   * @returns {LicenseHistoryIterator} object of LicenseHistoryIterator type
   */
  licenseHistory(accessToken, queryParams, resultColumns) {
    let localQueryParams = {};
    const nbResultsPresent = true;

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

    QueryParamsUtils.validateLicenseHistoryQueryParams(localQueryParams);

    return new LicenseHistoryIterator(this.stockApis,
                                    accessToken,
                                    localQueryParams,
                                    nbResultsPresent);
  }

  /**
   * function to search stock files by search params
   * @param {object} queryParams (optional) the params to be used for image search & filter
   * @returns {promise} returns Promise object for category search call
   */
  searchCategory(queryParams) {
    if (!isAdobeStockInitialized.call(this)) {
      throw new Error('Library not initialized! Please initialize the library first.');
    }

    QueryParamsUtils.validateSearchCategoryQueryParams(queryParams);

    return this.stockApis.searchCategory(queryParams);
  }

  /**
   * function to search stock files by search params
   * @param {object} queryParams (optional) the params to be used for image search & filter
   * @returns {promise} returns Promise object for category tree search call
   */
  searchCategoryTree(queryParams) {
    if (!isAdobeStockInitialized.call(this)) {
      throw new Error('Library not initialized! Please initialize the library first.');
    }

    QueryParamsUtils.validateSearchCategoryTreeQueryParams(queryParams);

    return this.stockApis.searchCategoryTree(queryParams);
  }

  /**
   * function to acsess licensing capabilities for a specific user for an asset
   * @param {string} accessToken (required) access token to be used for Authorization header
   * @param {integer} contentId (required) asset's unique identifer
   * @param {string} license (required) licensing state for the asset
   * @param {string} locale (optional) location language code
   * @returns {promise} promise which will give json data for member profile if found
   */
  accessMemberProfile(accessToken, contentId, license, locale) {
    if (!isAdobeStockInitialized.call(this)) {
      throw new Error('Library not initialized! Please initialize the library first.');
    }

    LicenseParamsUtils.validateContentLicenseParams(accessToken, contentId, license);

    if (locale && typeof locale !== 'string') {
      throw new Error('locale expects string only. For e.g. en-US, fr-FR etc.!');
    }

    return this.stockApis.accessMemberProfile(accessToken, contentId, license, locale);
  }

  /**
   * Notifies the system when a user abandons a licensing operation
   * @param {string} accessToken to be sent with Authorization header
   * @param {integer} contentId (required) asset's unique identifer
   * @param {string} state (required) user's purchase relationship to an asset
   * @returns {promise} returns promise
   */
  memberAbandonLicensing(accessToken, contentId, state) {
    if (!isAdobeStockInitialized.call(this)) {
      throw new Error('Library not initialized! Please initialize the library first.');
    }

    LicenseParamsUtils.validateMemberAbandonLicenseParams(accessToken, contentId, state);

    return this.stockApis.memberAbandon(accessToken, contentId, state);
  }

  /**
   * Get licensing information about a specific asset for a specific user
   * @param {string} accessToken (required) access token to be used for Authorization header
   * @param {integer} contentId (required) asset's unique identifer
   * @param {string} license (required) licensing state for the asset.
   * @returns {promise} promise which will give json data for licensing information
   */
  getLicenseInfoForContent(accessToken, contentId, license) {
    if (!isAdobeStockInitialized.call(this)) {
      throw new Error('Library not initialized! Please initialize the library first.');
    }

    LicenseParamsUtils.validateContentLicenseParams(accessToken, contentId, license);

    return this.stockApis.licenseInfo(accessToken, contentId, license);
  }

  /**
   * Requests a license for an asset for a specific user
   * @param {string} accessToken (required) access token to be used for Authorization header
   * @param {integer} contentId (required) asset's unique identifer
   * @param {string} license (required) licensing state for the asset.
   * @returns {promise} promise which will give json data with license info
   * containing a download URL
   */
  requestLicenseForContent(accessToken, contentId, license) {
    if (!isAdobeStockInitialized.call(this)) {
      throw new Error('Library not initialized! Please initialize the library first.');
    }

    LicenseParamsUtils.validateContentLicenseParams(accessToken, contentId, license);

    return this.stockApis.requestLicense(accessToken, contentId, license);
  }

  /**
   * Download the asset if user has the asset already licensed
   * @param {string} accessToken (required) access token to be used for Authorization header
   * @param {integer} contentId (required) asset's unique identifer
   * @param {string} license (required) licensing state for the asset.
   * @returns {promise} promise having url of asset as a response
   */
  downloadAsset(accessToken, contentId, license) {
    if (!isAdobeStockInitialized.call(this)) {
      throw new Error('Library not initialized! Please initialize the library first.');
    }

    LicenseParamsUtils.validateContentLicenseParams(accessToken, contentId, license);

    return this.stockApis.downloadAsset(accessToken, contentId, license);
  }
}

module.exports = AdobeStock;
