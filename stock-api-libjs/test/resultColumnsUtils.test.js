import { expect } from 'chai';
import Constants from './../src/constants/constants';
import ResultColumnsUtils from './../src/utils/resultColumnsUtils';
import Utils from './../src/utils/utils';

describe('ResultColumnsUtils', () => {
  // Tests for encodeURI function
  describe('encodeURI', () => {
    const mockParams = [
      Constants.RESULT_COLUMNS.CATEGORY,
      Constants.RESULT_COLUMNS.THUMBNAIL_URL,
      Constants.RESULT_COLUMNS.DESCRIPTION,
    ];

    const encodedURI = ResultColumnsUtils.encodeURI(mockParams);
    const encodedURIArray = encodedURI.split('&');

    encodedURIArray.forEach((encodedURIParams) => {
      const param = encodedURIParams.split('=');
      const checkParam = Utils.doesArrayContainValue(mockParams, param[1]);

      it(`should contain '${param[1]}' in encodedURI`, () => {
        expect(checkParam).to.equal(true);
      });
    });
  });

  // Tests for validate function
  describe('validate', () => {
    const mockParamsTrue = [
      Constants.RESULT_COLUMNS.CATEGORY,
      Constants.RESULT_COLUMNS.THUMBNAIL_URL,
      Constants.RESULT_COLUMNS.DESCRIPTION,
    ];

    const mockParamsFalse = [
      Constants.RESULT_COLUMNS.CATEGORY,
      'MOCK_THUMBNAIL_URL',
      Constants.RESULT_COLUMNS.DESCRIPTION,
    ];

    it(`should throw an error since '${mockParamsFalse[1]}' does not exist in result columns`, () => {
      expect(() => ResultColumnsUtils.validate(mockParamsFalse)).to.throw(`Invalid Result Column '${mockParamsFalse[1]}'`);
    });

    it('should not throw any error since every value exists in result column', () => {
      expect(() => ResultColumnsUtils.validate(mockParamsTrue)).to.not.throw(Error);
    });
  });
});
