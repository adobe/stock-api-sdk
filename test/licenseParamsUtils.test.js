import { expect } from 'chai';
import LicenseParamsUtils from './../src/utils/licenseParamsUtils';
import Constants from './../src/constants/constants';

describe('LicenseParamsUtils', () => {
  // Tests for validateContentLicenseParams function
  describe('validateContentLicenseParams', () => {
    beforeEach(function () {
      this.accessToken = 'testAccessToken';
    });

    it('should throw error contentId expects Integer if contentId argument passed is not Integer', function () {
      const LICENSE_TYPE = Constants.LICENSE_STATE_PARAMS.IMAGE.STANDARD;
      let testFn = () => LicenseParamsUtils.validateContentLicenseParams(this.accessToken, 'test', LICENSE_TYPE);
      expect(testFn).to.throw(/contentId missing or contentId is not of type Integer/);

      testFn = () => LicenseParamsUtils.validateContentLicenseParams(this.accessToken, [],
                                                                     LICENSE_TYPE);
      expect(testFn).to.throw(/contentId missing or contentId is not of type Integer/);

      testFn = () => LicenseParamsUtils.validateContentLicenseParams(this.accessToken, {},
                                                                     LICENSE_TYPE);
      expect(testFn).to.throw(/contentId missing or contentId is not of type Integer/);
    });

    it('should throw error license expects string if license argument passed is not string', function () {
      let testFn = () => LicenseParamsUtils.validateContentLicenseParams(this.accessToken, 1234, 1);
      expect(testFn).to.throw(/license missing or license is not of type string/);

      testFn = () => LicenseParamsUtils.validateContentLicenseParams(this.accessToken, 1234, []);
      expect(testFn).to.throw(/license missing or license is not of type string/);

      testFn = () => LicenseParamsUtils.validateContentLicenseParams(this.accessToken, 1234, {});
      expect(testFn).to.throw(/license missing or license is not of type string/);

      testFn = () => LicenseParamsUtils.validateContentLicenseParams(this.accessToken, 1234);
      expect(testFn).to.throw(/license missing or license is not of type string/);
    });

    it('should throw error Access Token missing if accessToken is not provided', () => {
      const testFn = () => LicenseParamsUtils.validateContentLicenseParams(undefined, 1234, 'STANDARD');
      expect(testFn).to.throw(/access token missing or not of type string!/);
    });

    it('should throw error Access Token missing if accessToken is not provided', () => {
      const testFn = () => LicenseParamsUtils.validateContentLicenseParams(4, 1234, 'STANDARD');
      expect(testFn).to.throw(/access token missing or not of type string!/);
    });

    it('should throw error for unsupported license type', function () {
      const testFn = () => LicenseParamsUtils.validateContentLicenseParams(this.accessToken, 1, 'myLicenseType');
      expect(testFn).to.throw(/License type not supported!/);
    });

    it('should not throw error for unsupported license type', function () {
      let LICENSE_TYPE = Constants.LICENSE_STATE_PARAMS.EMPTY.EMPTY_LICENSE;
      let testFn = () => LicenseParamsUtils.validateContentLicenseParams(this.accessToken, 1,
                                                                         LICENSE_TYPE);
      expect(testFn).to.not.throw(Error);

      LICENSE_TYPE = Constants.LICENSE_STATE_PARAMS.IMAGE.STANDARD;
      testFn = () => LicenseParamsUtils.validateContentLicenseParams(this.accessToken, 1,
                                                                     LICENSE_TYPE);
      expect(testFn).to.not.throw(Error);

      LICENSE_TYPE = Constants.LICENSE_STATE_PARAMS.VIDEO.VIDEO_HD;
      testFn = () => LicenseParamsUtils.validateContentLicenseParams(this.accessToken, 1,
                                                                     LICENSE_TYPE);
      expect(testFn).to.not.throw(Error);

      LICENSE_TYPE = Constants.LICENSE_STATE_PARAMS.VECTOR_ASSETS.STANDARD;
      testFn = () => LicenseParamsUtils.validateContentLicenseParams(this.accessToken, 1,
                                                                     LICENSE_TYPE);
      expect(testFn).to.not.throw(Error);

      LICENSE_TYPE = Constants.LICENSE_STATE_PARAMS.ASSETS_3D.STANDARD;
      testFn = () => LicenseParamsUtils.validateContentLicenseParams(this.accessToken, 1,
                                                                     LICENSE_TYPE);
      expect(testFn).to.not.throw(Error);

      LICENSE_TYPE = Constants.LICENSE_STATE_PARAMS.TEMPLATES.STANDARD;
      testFn = () => LicenseParamsUtils.validateContentLicenseParams(this.accessToken, 1,
                                                                     LICENSE_TYPE);
      expect(testFn).to.not.throw(Error);
    });
  });

  // Tests for validateContentLicenseParams function
  describe('validateMemberAbandonLicenseParams', () => {
    beforeEach(function () {
      this.accessToken = 'testAccessToken';
    });

    it('should throw error contentId expects Integer if contentId argument passed is not Integer', function () {
      let testFn = () => LicenseParamsUtils.validateMemberAbandonLicenseParams(this.accessToken, 'test', 'STANDARD');
      expect(testFn).to.throw(/contentId missing or contentId is not of type Integer/);

      testFn = () => LicenseParamsUtils.validateMemberAbandonLicenseParams(this.accessToken, [], 'STANDARD');
      expect(testFn).to.throw(/contentId missing or contentId is not of type Integer/);

      testFn = () => LicenseParamsUtils.validateMemberAbandonLicenseParams(this.accessToken, {}, 'STANDARD');
      expect(testFn).to.throw(/contentId missing or contentId is not of type Integer/);
    });

    it('should throw error state expects string if state argument passed is not string', function () {
      let testFn = () => LicenseParamsUtils.validateMemberAbandonLicenseParams(this.accessToken,
                                                                               1234, 1);
      expect(testFn).to.throw(/state missing or state is not of type string/);

      testFn = () => LicenseParamsUtils.validateMemberAbandonLicenseParams(this.accessToken, 1234,
                                                                           []);
      expect(testFn).to.throw(/state missing or state is not of type string/);

      testFn = () => LicenseParamsUtils.validateMemberAbandonLicenseParams(this.accessToken, 1234,
                                                                           {});
      expect(testFn).to.throw(/state missing or state is not of type string/);

      testFn = () => LicenseParamsUtils.validateMemberAbandonLicenseParams(this.accessToken, 1234);
      expect(testFn).to.throw(/state missing or state is not of type string/);
    });

    it('should throw error Access Token missing if accessToken is not provided', () => {
      const testFn = () => LicenseParamsUtils.validateMemberAbandonLicenseParams(undefined, 1234,
                                                                                'not_possible');
      expect(testFn).to.throw(/access Token missing or not of type string/);
    });

    it('should throw error Access Token missing if accessToken is not provided', () => {
      const testFn = () => LicenseParamsUtils.validateMemberAbandonLicenseParams(4, 1234, 'NOT_PURCHASED');
      expect(testFn).to.throw(/access Token missing or not of type string/);
    });

    it('should throw error for unsupported license state', function () {
      const testFn = () => LicenseParamsUtils.validateMemberAbandonLicenseParams(this.accessToken, 1234, 'myLicenseState');
      expect(testFn).to.throw(/License state not supported!/);
    });

    it('should not throw error for supported license state', function () {
      let LICENSE_STATE = Constants.PURCHASE_STATE_PARAMS.NOT_PURCHASED;
      let testFn = () => LicenseParamsUtils.validateMemberAbandonLicenseParams(this.accessToken,
                                                                               1234, LICENSE_STATE);
      expect(testFn).to.not.throw(Error);

      LICENSE_STATE = Constants.PURCHASE_STATE_PARAMS.OVERAGE;
      testFn = () => LicenseParamsUtils.validateMemberAbandonLicenseParams(this.accessToken, 1234,
                                                                           LICENSE_STATE);
      expect(testFn).to.not.throw(Error);
    });
  });
});
