/**
 * Copyright 2017 Adobe Systems Incorporated. All rights reserved.
 * This file is licensed to you under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License. You may obtain a copy
 * of the License at http://www.apache.org/licenses/LICENSE-2.0
 */
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

  describe('validateLicenseHistory', () => {
    const mockParamsTrue = [
      Constants.LICENSE_HISTORY_RESULT_COLUMNS.THUMBNAIL_110_URL,
      Constants.LICENSE_HISTORY_RESULT_COLUMNS.THUMBNAIL_110_WIDTH,
      Constants.LICENSE_HISTORY_RESULT_COLUMNS.THUMBNAIL_110_HEIGHT,
    ];

    const mockParamsFalse = [
      Constants.LICENSE_HISTORY_RESULT_COLUMNS.THUMBNAIL_110_URL,
      'MOCK_THUMBNAIL_URL',
      Constants.LICENSE_HISTORY_RESULT_COLUMNS.THUMBNAIL_110_HEIGHT,
    ];

    it(`should throw an error since '${mockParamsFalse[1]}' does not exist in result columns`, () => {
      expect(() => ResultColumnsUtils.validateLicenseHistory(mockParamsFalse)).to.throw(`Invalid Result Column '${mockParamsFalse[1]}'`);
    });

    it('should not throw any error since every value exists in result column', () => {
      expect(() => ResultColumnsUtils.validateLicenseHistory(mockParamsTrue)).to.not.throw(Error);
    });
  });
});
