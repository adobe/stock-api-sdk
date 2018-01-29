import Constants from './../constants/constants';
import Utils from './../utils/utils';

/**
 * validates license against the predefined license type
 * @param {string} license License type
 */
function validateLicenseType(license) {
  // stock APIs do case insensitive matching for license type,
  // hence converting all characters to lower case
  const licenseLower = license.toLowerCase();

  // check if license is one of the license type supported
  if (!Utils.doesJSONContainsValue(Constants.LICENSE_STATE_PARAMS, licenseLower)) {
    throw new Error('License type not supported!');
  }
}

/**
 * validates license state against the predefined license state
 * @param {string} state License state
 */
function validateLicenseState(state) {
  // stock APIs do case insensitive matching for license state,
  // hence converting all characters to lower case
  const stateLower = state.toLowerCase();
  // check if license is one of the license type supported
  if (!Utils.doesJSONContainsValue(Constants.PURCHASE_STATE_PARAMS, stateLower)) {
    throw new Error('License state not supported!');
  }
}

export default class LicenseParamsUtils {
  /**
   * function to verify the params for the license related APIs
   * @param {string} accessToken access token to be used for Authorization header
   * @param {integer} contentId asset's unique identifer
   * @param {string} license licensing state for the asset.
   */
  static validateContentLicenseParams(accessToken, contentId, license) {
    if (accessToken == null || typeof accessToken !== 'string') {
      throw new Error('access token missing or not of type string!');
    }

    if (contentId == null || Number.isInteger(contentId) === false) {
      throw new Error('contentId missing or contentId is not of type Integer!');
    }

    if (license && typeof license !== 'string') {
      throw new Error('license is not of type string!');
    }

    if (license) {
      validateLicenseType(license);
    }
  }

  /**
   * function to verify the params for the license related APIs
   * @param {string} accessToken access token to be used for Authorization header
   * @param {integer} contentId asset's unique identifer
   * @param {string} state licensing state for the asset.
   */
  static validateMemberAbandonLicenseParams(accessToken, contentId, state) {
    if (accessToken == null || typeof accessToken !== 'string') {
      throw new Error('access Token missing or not of type string!');
    }

    if (contentId == null || Number.isInteger(contentId) === false) {
      throw new Error('contentId missing or contentId is not of type Integer!');
    }

    if (state == null || typeof state !== 'string') {
      throw new Error('state missing or state is not of type string!');
    }

    validateLicenseState(state);
  }

  /**
   * function to verify the params for the license related APIs
   * @param {string} accessToken access token to be used for Authorization header
   * @param {integer} contentId asset's unique identifer
   * @param {string} license licensing state for the asset.
   */
  static validateMemberProfileLicenseParams(accessToken, contentId, license) {
    if (accessToken == null || typeof accessToken !== 'string') {
      throw new Error('access token missing or not of type string!');
    }

    if (contentId && Number.isInteger(contentId) === false) {
      throw new Error('contentId is not of type Integer!');
    }

    if (license && typeof license !== 'string') {
      throw new Error('license is not of type string!');
    }

    if (license) {
      validateLicenseType(license);
    }
  }

}
