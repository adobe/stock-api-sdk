import Utils from './utils';
import SearchParamsUtils from './searchParamsUtils';
import ResultColumnsUtils from './resultColumnsUtils';
import Constants from './../constants/constants';

export default class QueryParamsUtils {

  /**
  * Checks if the queryParams has nb_results column
  * @param {!object} queryParams object of query parameters
  * @returns {boolean} true if nb_results present, false otherwise
  */
  static isNBResultsColumnPresent(queryParams) {
    // we need result column for hasMoreFiles & nextSearchFiles to work properly
    if (queryParams.result_columns
             && !queryParams.result_columns.includes(Constants.RESULT_COLUMNS.NB_RESULTS)) {
      return false;
    }

    // if result_columns isn't present in query params then default result_columns
    // contains nb_results
    return true;
  }

  /**
  * Add result column nb_results
  * @param {!object} queryParams object of query parameters
  * @returns {object} query parameter object with nb_results column if added
  */
  static addResultColumnNBResults(queryParams) {
    if (queryParams.result_columns && QueryParamsUtils.isNBResultsColumnPresent(queryParams)) {
      throw new Error('Query parameter already contains nb_results in result_columns');
    }

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
  static validateSearchFileQueryParams(queryParams, accessToken) {
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
        throw new Error(`Invalid query parameter '${param}'! for file search query`);
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
  * Validates the query parameters
  * @param {!object} queryParams object of query parameters
  */
  static validateSearchCategoryQueryParams(queryParams) {
    QueryParamsUtils.validateSearchCategoryTreeQueryParams(queryParams);

    //  category_id is required with url parameters
    if (!Object.getOwnPropertyDescriptor(queryParams, Constants.QUERY_PARAMS_PROPS.CATEGORY)) {
      throw new Error('category_id missing! category search requires category_id in query parameters.');
    }
  }

  /**
  * Validates the query parameters
  * @param {!object} queryParams object of query parameters
  */
  static validateSearchCategoryTreeQueryParams(queryParams) {
    if (queryParams && !Utils.isObject(queryParams)) {
      throw new Error('queryParams expects Object!');
    }

    Object.keys(queryParams).forEach((param) => {
      if (param === Constants.QUERY_PARAMS_PROPS.LOCALE) {
        if (typeof queryParams[param] !== 'string') {
          throw new Error('locale expects string only. For e.g. en-US, fr-FR etc.');
        }
      } else if (param === Constants.QUERY_PARAMS_PROPS.CATEGORY) {
        if (!Number.isInteger(queryParams[param])) {
          throw new Error('category_id expects integer only. For e.g. 1043, 4526 etc.');
        }
      } else {
        throw new Error(`Invalid query parameter '${param}'! for category search query!`);
      }
    });
  }

}
