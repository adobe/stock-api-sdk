import Constants from './../constants/constants';
import Http from './http';

const DECIMAL_RADIX = 10;

export default class Utilities {
  /**
   * Checks if value is string
   * @param {object} value to be checked
   * @returns {boolean} returns true if value is string otherwise false
   */
  static isString(value) {
    return typeof value === 'string';
  }

  /**
   * Checks if array contains given value
   * @param {object} array object in which the value needs to be checked
   * @param {string} value to be checked
   * @returns {boolean} returns true if array contains value otherwise false
   */
  static doesArrayContainValue(array, value) {
    return array.indexOf(value) > -1;
  }

  /**
   * Checks if json object contains given value
   * @param {object} json object in which the value needs to be checked
   * @param {boolean|number|string} value value to be checked in json
   * @returns {boolean} returns true if json contains value otherwise false (checks recursively)
   */
  static doesJSONContainsValue(json, value) {
    if (!Utilities.isObject(json)) {
      return false;
    }

    let hasValue = false;
    Object.keys((json)).forEach((key) => {
      if (json[key] === value) {
        hasValue = true;
      } else if (typeof json[key] === 'object') {
        hasValue = hasValue || Utilities.doesJSONContainsValue(json[key], value);
      }
    });

    return hasValue;
  }

  /**
   * Checks if the variable is integer
   * @param {object} variable to be checked for
   * @returns {boolean} returns true if variable is integer otherwise false
   */
  static isInteger(variable) {
    // even if the variable is string but contains valid integer number return true
    if (!isNaN(variable) && variable === parseInt(variable, DECIMAL_RADIX)) {
      return true;
    }

    return false;
  }

  /**
   * Checks if the variable is array
   * @param {object} variable to be checked for
   * @returns {boolean} returns true if variable is array otherwise false
   */
  static isArray(variable) {
    if (variable.constructor === Array) {
      return true;
    }

    return false;
  }

  /**
   * Checks if the variable is object
   * @param {object} variable to be checked for
   * @returns {boolean} returns true if variable is object otherwise false
   */
  static isObject(variable) {
    if (variable.constructor === Object) {
      return true;
    }

    return false;
  }

  /**
   * Makes GET ajax request to given url
   * @param {string} url object for ajax request
   * @param {object} headers to be sent with ajax request
   * @returns {promise} returns promise
   */
  static makeGetAjaxCall(url, headers) {
    return new Promise((onSuccess, onError) => {
      const settings = {
        url,
        headers,
      };

      Http.doXhr(settings)
        .then(onSuccess, onError);
    });
  }

  /**
   * Makes POST ajax request to given url
   * @param {string} url object for ajax request
   * @param {object} headers to be sent with ajax request
   * @param {data} data to be sent as post request body with request
   * @returns {promise} returns promise
   */
  static makePostAjaxCall(url, headers, data) {
    return new Promise((onSuccess, onError) => {
      const settings = {
        data,
        url,
        method: Constants.HTTPMETHOD.POST,
        headers,
      };

      Http.doXhr(settings)
        .then(onSuccess, onError);
    });
  }

  /**
   * Makes multipart/form-data ajax request to given url
   * @param {string} url object for ajax request
   * @param {object} headers to be sent with ajax request
   * @param {data} data to be sent as post request body with request
   * @returns {promise} returns promise
   */
  static makeMultiPartAjaxCall(url, headers, data) {
    return new Promise((onSuccess, onError) => {
      const settings = {
        data,
        url,
        method: Constants.HTTPMETHOD.POST,
        contentType: false,
        headers,
      };

      Http.doXhr(settings)
        .then(onSuccess, onError);
    });
  }

}
