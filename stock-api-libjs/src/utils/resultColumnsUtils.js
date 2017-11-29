import Utils from './utils';
import Constants from './../constants/constants';

export default class ResultColumnsUtils {

  /**
   * Validates the result columns array
   * @param {object} params array of result columns
   */
  static validate(params) {
    const supportedColumns = Object.keys(Constants.RESULT_COLUMNS)
                                .map(key => Constants.RESULT_COLUMNS[key]);

    params.forEach((param) => {
      if (!Utils.doesArrayContainValue(supportedColumns, param)) {
        throw new Error(`Invalid Result Column '${param}'`);
      }
    });
  }

  /**
   * Validates the result columns array for license history
   * @param {object} params array of result columns
   */
  static validateLicenseHistory(params) {
    const supportedColumns = Object.keys(Constants.LICENSE_HISTORY_RESULT_COLUMNS)
                                .map(key => Constants.LICENSE_HISTORY_RESULT_COLUMNS[key]);

    params.forEach((param) => {
      if (!Utils.doesArrayContainValue(supportedColumns, param)) {
        throw new Error(`Invalid Result Column '${param}'`);
      }
    });
  }

  /**
   * Encodes the result columns array into uri
   * @param {object} params array of result columns
   * @returns {string} uri encoded result columns
   */
  static encodeURI(params) {
    const uri = [];
    params.forEach((param) => {
      const paramToStr = `${encodeURIComponent(Constants.RESULTS_COLUMNS_TOSTRING)}=${encodeURIComponent(param)}`;
      uri.push(paramToStr);
    });

    return uri.join('&');
  }
}
