/**
 * Copyright 2017 Adobe Systems Incorporated. All rights reserved.
 * This file is licensed to you under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License. You may obtain a copy
 * of the License at http://www.apache.org/licenses/LICENSE-2.0
 */
import { expect } from 'chai';
import sinon from 'sinon';
import Constants from './../src/constants/constants';
import SearchFilesIterator from './../src/models/searchFilesIterator';
import LicenseHistoryIterator from './../src/models/licenseHistoryIterator';

const AdobeStock = require('./../src/adobeStock');
const prodEndpoints = require('./../resources/prod_env.json');
const stageEndpoints = require('./../resources/stage_env.json');

describe('AdobeStock', () => {
  describe('constructor', () => {
    it('should create object of AdobeStock type with two properties config and stockApis', () => {
      const stageStock = new AdobeStock('testApiKey', 'testProduct', Constants.ENVIRONMENT.STAGE);
      expect(stageStock).to.be.ok;
      expect(stageStock).to.have.property('config');
      expect(stageStock).to.have.property('stockApis');

      expect(stageStock.config).to.have.property('x_api_key', 'testApiKey');
      expect(stageStock.config).to.have.property('x_product', 'testProduct');
      expect(stageStock.config).to.have.property('target_env', Constants.ENVIRONMENT.STAGE);
      expect(stageStock.config).to.have.property('endpoints', stageEndpoints);

      expect(stageStock.stockApis).to.have.property('config', stageStock.config);

      const prodStock = new AdobeStock('testProdApiKey', 'testProdProduct', Constants.ENVIRONMENT.PROD);
      expect(prodStock).to.be.ok;
      expect(prodStock).to.have.property('config');
      expect(prodStock).to.have.property('stockApis');

      expect(prodStock.config).to.have.property('x_api_key', 'testProdApiKey');
      expect(prodStock.config).to.have.property('x_product', 'testProdProduct');
      expect(prodStock.config).to.have.property('target_env', Constants.ENVIRONMENT.PROD);
      expect(prodStock.config).to.have.property('endpoints', prodEndpoints);

      expect(prodStock.stockApis).to.have.property('config', prodStock.config);
    });

    it('should throw error if apiKey argument provided not ok', () => {
      const testFn = () => new AdobeStock('', 'testProduct', Constants.ENVIRONMENT.STAGE);
      expect(testFn).to.throw(/Api Key configuration is missing!/);
    });

    it('should throw error if product argument provided not ok', () => {
      const testFn = () => new AdobeStock('testApiKey', '', Constants.ENVIRONMENT.STAGE);
      expect(testFn).to.throw(/Product configuration is missing!/);
    });
  });

  // test cases for Enums/Constants
  const testCasesConstants = [
    {
      name: 'ENVIRONMENT',
      test: AdobeStock.ENVIRONMENT,
      expected: Constants.ENVIRONMENT,
      testFunc: () => { AdobeStock.ENVIRONMENT = 'test'; },
    },
    {
      name: 'SEARCH_PARAMS',
      test: AdobeStock.SEARCH_PARAMS,
      expected: Constants.SEARCH_PARAMS,
      testFunc: () => { AdobeStock.SEARCH_PARAMS = 'test'; },
    },
    {
      name: 'LICENSE_HISTORY_SEARCH_PARAMS',
      test: AdobeStock.LICENSE_HISTORY_SEARCH_PARAMS,
      expected: Constants.LICENSE_HISTORY_SEARCH_PARAMS,
      testFunc: () => { AdobeStock.LICENSE_HISTORY_SEARCH_PARAMS = 'test'; },
    },
    {
      name: 'SEARCH_PARAMS_TYPE',
      test: AdobeStock.SEARCH_PARAMS_TYPE,
      expected: Constants.SEARCH_PARAMS_TYPE,
      testFunc: () => { AdobeStock.SEARCH_PARAMS_TYPE = 'test'; },
    },
    {
      name: 'SEARCH_PARAMS_ORDER',
      test: AdobeStock.SEARCH_PARAMS_ORDER,
      expected: Constants.SEARCH_PARAMS_ORDER,
      testFunc: () => { AdobeStock.SEARCH_PARAMS_ORDER = 'test'; },
    },
    {
      name: 'SEARCH_PARAMS_ORIENTATION',
      test: AdobeStock.SEARCH_PARAMS_ORIENTATION,
      expected: Constants.SEARCH_PARAMS_ORIENTATION,
      testFunc: () => { AdobeStock.SEARCH_PARAMS_ORIENTATION = 'test'; },
    },
    {
      name: 'SEARCH_PARAMS_HAS_RELEASES',
      test: AdobeStock.SEARCH_PARAMS_HAS_RELEASES,
      expected: Constants.SEARCH_PARAMS_HAS_RELEASES,
      testFunc: () => { AdobeStock.SEARCH_PARAMS_HAS_RELEASES = 'test'; },
    },
    {
      name: 'SEARCH_PARAMS_3D_TYPES',
      test: AdobeStock.SEARCH_PARAMS_3D_TYPES,
      expected: Constants.SEARCH_PARAMS_3D_TYPES,
      testFunc: () => { AdobeStock.SEARCH_PARAMS_3D_TYPES = 'test'; },
    },
    {
      name: 'SEARCH_PARAMS_TEMPLATE_CATEGORIES',
      test: AdobeStock.SEARCH_PARAMS_TEMPLATE_CATEGORIES,
      expected: Constants.SEARCH_PARAMS_TEMPLATE_CATEGORIES,
      testFunc: () => { AdobeStock.SEARCH_PARAMS_TEMPLATE_CATEGORIES = 'test'; },
    },
    {
      name: 'SEARCH_PARAMS_TEMPLATE_TYPES',
      test: AdobeStock.SEARCH_PARAMS_TEMPLATE_TYPES,
      expected: Constants.SEARCH_PARAMS_TEMPLATE_TYPES,
      testFunc: () => { AdobeStock.SEARCH_PARAMS_TEMPLATE_TYPES = 'test'; },
    },
    {
      name: 'SEARCH_PARAMS_THUMB_SIZES',
      test: AdobeStock.SEARCH_PARAMS_THUMB_SIZES,
      expected: Constants.SEARCH_PARAMS_THUMB_SIZES,
      testFunc: () => { AdobeStock.SEARCH_PARAMS_THUMB_SIZES = 'test'; },
    },
    {
      name: 'SEARCH_PARAMS_AGE',
      test: AdobeStock.SEARCH_PARAMS_AGE,
      expected: Constants.SEARCH_PARAMS_AGE,
      testFunc: () => { AdobeStock.SEARCH_PARAMS_AGE = 'test'; },
    },
    {
      name: 'SEARCH_PARAMS_VIDEO_DURATION',
      test: AdobeStock.SEARCH_PARAMS_VIDEO_DURATION,
      expected: Constants.SEARCH_PARAMS_VIDEO_DURATION,
      testFunc: () => { AdobeStock.SEARCH_PARAMS_VIDEO_DURATION = 'test'; },
    },
    {
      name: 'SEARCH_PARAMS_PREMIUM',
      test: AdobeStock.SEARCH_PARAMS_PREMIUM,
      expected: Constants.SEARCH_PARAMS_PREMIUM,
      testFunc: () => { AdobeStock.SEARCH_PARAMS_PREMIUM = 'test'; },
    },
    {
      name: 'RESULT_COLUMNS',
      test: AdobeStock.RESULT_COLUMNS,
      expected: Constants.RESULT_COLUMNS,
      testFunc: () => { AdobeStock.RESULT_COLUMNS = 'test'; },
    },
    {
      name: 'LICENSE_HISTORY_RESULT_COLUMNS',
      test: AdobeStock.LICENSE_HISTORY_RESULT_COLUMNS,
      expected: Constants.LICENSE_HISTORY_RESULT_COLUMNS,
      testFunc: () => { AdobeStock.LICENSE_HISTORY_RESULT_COLUMNS = 'test'; },
    },
  ];
  testCasesConstants.forEach((testcase) => {
    describe(testcase.name, () => {
      it(`should return Constants.${testcase.name}`, () => {
        expect(testcase.test).to.deep.equal(testcase.expected);
      });

      it(`should throw error Cannot set property ${testcase.name} if tried to set`, () => {
        const regex = new RegExp(`Cannot set property ${testcase.name}`);
        expect(testcase.testFunc).to.throw(regex);
      });
    });
  });

  describe('searchFilesByCategory', () => {
    beforeEach(function () {
      this.stock = new AdobeStock('testApiKey', 'testProduct', Constants.ENVIRONMENT.STAGE);
      this.accessToken = 'testAccessToken';
    });

    it('should throw error Library not initialized', function () {
      const isConfigInitialized = sinon.stub(this.stock.config, 'isConfigInitialized');

      isConfigInitialized.returns(false);
      let testFn = () => this.stock.searchFilesByCategory(this.accessToken, 695, 'en-US', {}, []);
      expect(testFn).to.throw('Library not initialized! Please initialize the library first.');

      isConfigInitialized.returns(true);
      testFn = () => this.stock.searchFilesByCategory(this.accessToken, 695, 'en-US', {}, []);
      expect(testFn).to.not.throw(Error);

      isConfigInitialized.restore();
    });

    it('should throw error filters expects Object if filters argument passed is not object', function () {
      let testFn = () => this.stock.searchFilesByCategory(this.accessToken, 695, 'en-US', 'filters', []);
      expect(testFn).to.throw(/filters expects Object/);

      testFn = () => this.stock.searchFilesByCategory(this.accessToken, 695, 'en-US', [], []);
      expect(testFn).to.throw(/filters expects Object/);

      testFn = () => this.stock.searchFilesByCategory(this.accessToken, 695, 'en-US', 1, []);
      expect(testFn).to.throw(/filters expects Object/);
    });

    it('should throw error resultColumns expects Array if filters argument passed is not object', function () {
      let testFn = () => this.stock.searchFilesByCategory(this.accessToken, 695, 'en-US', {}, 'test');
      expect(testFn).to.throw(/resultColumns expects Array/);

      testFn = () => this.stock.searchFilesByCategory(this.accessToken, 695, 'en-US', {}, {});
      expect(testFn).to.throw(/resultColumns expects Array/);

      testFn = () => this.stock.searchFilesByCategory(this.accessToken, 695, 'en-US', {}, 1);
      expect(testFn).to.throw(/resultColumns expects Array/);
    });

    it('should throw error locale expects string only if locale argument is passed other than string', function () {
      let testFn = () => this.stock.searchFilesByCategory(this.accessToken, 695, 1, {}, []);
      expect(testFn).to.throw(/locale expects string only/);

      testFn = () => this.stock.searchFilesByCategory(this.accessToken, 695, {}, {}, []);
      expect(testFn).to.throw(/locale expects string only/);

      testFn = () => this.stock.searchFilesByCategory(this.accessToken, 695, [], {}, []);
      expect(testFn).to.throw(/locale expects string only/);
    });

    it('should throw error Access Token missing if accessToken is not provided and result column is_licensed is requested', function () {
      const testFn = () => this.stock.searchFilesByCategory(undefined, 695, 'en-US', {},
                                                            [Constants.RESULT_COLUMNS.IS_LICENSED]);
      expect(testFn).to.throw(/Access Token missing! Result Column 'is_licensed' requires authentication\./);
    });

    it('should throw error Invalid value of Search Parameter if categoryId provided other than integer', function () {
      let testFn = () => this.stock.searchFilesByCategory(this.accessToken, '695', 'en-US', {},
                                                            [Constants.RESULT_COLUMNS.ID]);
      expect(testFn).to.throw(/Invalid value of Search Parameter 'category'\./);

      testFn = () => this.stock.searchFilesByCategory(this.accessToken, { test: '695' }, 'en-US', {},
                                                            [Constants.RESULT_COLUMNS.ID]);
      expect(testFn).to.throw(/Invalid value of Search Parameter 'category'\./);

      testFn = () => this.stock.searchFilesByCategory(this.accessToken, ['695'], 'en-US', {},
                                                            [Constants.RESULT_COLUMNS.ID]);
      expect(testFn).to.throw(/Invalid value of Search Parameter 'category'\./);

      testFn = () => this.stock.searchFilesByCategory(this.accessToken, undefined, 'en-US', {},
                                                            [Constants.RESULT_COLUMNS.ID]);
      expect(testFn).to.throw(/Invalid value of Search Parameter 'category'\./);
    });

    it('should return object of SearchFilesIterator type with relevant properties set', function () {
      let stock = this.stock.searchFilesByCategory(this.accessToken, 695, 'en-US', {},
                                                            [Constants.RESULT_COLUMNS.ID]);
      expect(stock).to.be.ok;
      expect(stock).instanceof(SearchFilesIterator);
      expect(stock).to.have.property('stockApis', this.stock.stockApis);
      expect(stock).to.have.property('accessToken', this.accessToken);
      expect(stock).to.have.property('queryParams');
      expect(stock).to.have.property('nbResultsPresent', false);
      expect(stock).to.have.property('response');
      expect(stock.response).to.be.empty;
      expect(stock).to.have.property('lastQueryParams', null);
      expect(stock).to.have.property('apiInProgress', false);

      stock = this.stock.searchFilesByCategory(this.accessToken, 695, 'en-US');
      expect(stock).to.be.ok;
      expect(stock).instanceof(SearchFilesIterator);
      expect(stock).to.have.property('nbResultsPresent', true);

      stock = this.stock.searchFilesByCategory(this.accessToken, 695, 'en-US', undefined,
                                [Constants.RESULT_COLUMNS.NB_RESULTS, Constants.RESULT_COLUMNS.ID]);
      expect(stock).to.be.ok;
      expect(stock).instanceof(SearchFilesIterator);
      expect(stock).to.have.property('nbResultsPresent', true);
    });
  });

  describe('searchSimilarFilesById', () => {
    beforeEach(function () {
      this.stock = new AdobeStock('testApiKey', 'testProduct', Constants.ENVIRONMENT.STAGE);
      this.accessToken = 'testAccessToken';
    });

    it('should throw error Library not initialized', function () {
      const isConfigInitialized = sinon.stub(this.stock.config, 'isConfigInitialized');

      isConfigInitialized.returns(false);
      let testFn = () => this.stock.searchSimilarFilesById(this.accessToken, 695, 'en-US', {}, []);
      expect(testFn).to.throw('Library not initialized! Please initialize the library first.');

      isConfigInitialized.returns(true);
      testFn = () => this.stock.searchSimilarFilesById(this.accessToken, 695, 'en-US', {}, []);
      expect(testFn).to.not.throw(Error);

      isConfigInitialized.restore();
    });

    it('should throw error filters expects Object if filters argument passed is not object', function () {
      let testFn = () => this.stock.searchSimilarFilesById(this.accessToken, 695, 'en-US', 'filters', []);
      expect(testFn).to.throw(/filters expects Object/);

      testFn = () => this.stock.searchSimilarFilesById(this.accessToken, 695, 'en-US', [], []);
      expect(testFn).to.throw(/filters expects Object/);

      testFn = () => this.stock.searchSimilarFilesById(this.accessToken, 695, 'en-US', 1, []);
      expect(testFn).to.throw(/filters expects Object/);
    });

    it('should throw error resultColumns expects Array if filters argument passed is not object', function () {
      let testFn = () => this.stock.searchSimilarFilesById(this.accessToken, 695, 'en-US', {}, 'test');
      expect(testFn).to.throw(/resultColumns expects Array/);

      testFn = () => this.stock.searchSimilarFilesById(this.accessToken, 695, 'en-US', {}, {});
      expect(testFn).to.throw(/resultColumns expects Array/);

      testFn = () => this.stock.searchSimilarFilesById(this.accessToken, 695, 'en-US', {}, 1);
      expect(testFn).to.throw(/resultColumns expects Array/);
    });

    it('should throw error locale expects string only if locale argument is passed other than string', function () {
      let testFn = () => this.stock.searchSimilarFilesById(this.accessToken, 695, 1, {}, []);
      expect(testFn).to.throw(/locale expects string only/);

      testFn = () => this.stock.searchSimilarFilesById(this.accessToken, 695, {}, {}, []);
      expect(testFn).to.throw(/locale expects string only/);

      testFn = () => this.stock.searchSimilarFilesById(this.accessToken, 695, [], {}, []);
      expect(testFn).to.throw(/locale expects string only/);
    });

    it('should throw error Access Token missing if accessToken is not provided and result column is_licensed is requested', function () {
      const testFn = () => this.stock.searchSimilarFilesById(undefined, 695, 'en-US', {},
                                                            [Constants.RESULT_COLUMNS.IS_LICENSED]);
      expect(testFn).to.throw(/Access Token missing! Result Column 'is_licensed' requires authentication\./);
    });

    it('should throw error Invalid value of Search Parameter if mediaId provided other than integer', function () {
      let testFn = () => this.stock.searchSimilarFilesById(this.accessToken, '695', 'en-US', {},
                                                            [Constants.RESULT_COLUMNS.ID]);
      expect(testFn).to.throw(/Invalid value of Search Parameter 'similar'\./);

      testFn = () => this.stock.searchSimilarFilesById(this.accessToken, { test: '695' }, 'en-US', {},
                                                            [Constants.RESULT_COLUMNS.ID]);
      expect(testFn).to.throw(/Invalid value of Search Parameter 'similar'\./);

      testFn = () => this.stock.searchSimilarFilesById(this.accessToken, ['695'], 'en-US', {},
                                                            [Constants.RESULT_COLUMNS.ID]);
      expect(testFn).to.throw(/Invalid value of Search Parameter 'similar'\./);

      testFn = () => this.stock.searchSimilarFilesById(this.accessToken, undefined, 'en-US', {},
                                                            [Constants.RESULT_COLUMNS.ID]);
      expect(testFn).to.throw(/Invalid value of Search Parameter 'similar'\./);
    });

    it('should return object of SearchFilesIterator type with relevant properties set', function () {
      let stock = this.stock.searchSimilarFilesById(this.accessToken, 695, 'en-US', {},
                                                            [Constants.RESULT_COLUMNS.ID]);
      expect(stock).to.be.ok;
      expect(stock).instanceof(SearchFilesIterator);
      expect(stock).to.have.property('stockApis', this.stock.stockApis);
      expect(stock).to.have.property('accessToken', this.accessToken);
      expect(stock).to.have.property('queryParams');
      expect(stock).to.have.property('nbResultsPresent', false);
      expect(stock).to.have.property('response');
      expect(stock.response).to.be.empty;
      expect(stock).to.have.property('lastQueryParams', null);
      expect(stock).to.have.property('apiInProgress', false);

      stock = this.stock.searchSimilarFilesById(this.accessToken, 695, 'en-US');
      expect(stock).to.be.ok;
      expect(stock).instanceof(SearchFilesIterator);
      expect(stock).to.have.property('nbResultsPresent', true);

      stock = this.stock.searchSimilarFilesById(this.accessToken, 695, 'en-US', undefined,
                                [Constants.RESULT_COLUMNS.NB_RESULTS, Constants.RESULT_COLUMNS.ID]);
      expect(stock).to.be.ok;
      expect(stock).instanceof(SearchFilesIterator);
      expect(stock).to.have.property('nbResultsPresent', true);
    });
  });

  describe('searchFilesByKeywords', () => {
    beforeEach(function () {
      this.stock = new AdobeStock('testApiKey', 'testProduct', Constants.ENVIRONMENT.STAGE);
      this.accessToken = 'testAccessToken';
    });

    it('should throw error filters expects Object if filters argument passed is not object', function () {
      let testFn = () => this.stock.searchFilesByKeywords(this.accessToken, 'test', 'en-US', 'filters', []);
      expect(testFn).to.throw(/filters expects Object/);

      testFn = () => this.stock.searchFilesByKeywords(this.accessToken, 'test', 'en-US', [], []);
      expect(testFn).to.throw(/filters expects Object/);

      testFn = () => this.stock.searchFilesByKeywords(this.accessToken, 'test', 'en-US', 1, []);
      expect(testFn).to.throw(/filters expects Object/);
    });

    it('should throw error resultColumns expects Array if filters argument passed is not object', function () {
      let testFn = () => this.stock.searchFilesByKeywords(this.accessToken, 'test', 'en-US', {}, 'test');
      expect(testFn).to.throw(/resultColumns expects Array/);

      testFn = () => this.stock.searchFilesByKeywords(this.accessToken, 'test', 'en-US', {}, {});
      expect(testFn).to.throw(/resultColumns expects Array/);

      testFn = () => this.stock.searchFilesByKeywords(this.accessToken, 'test', 'en-US', {}, 1);
      expect(testFn).to.throw(/resultColumns expects Array/);
    });

    it('should throw error locale expects string only if locale argument is passed other than string', function () {
      let testFn = () => this.stock.searchFilesByKeywords(this.accessToken, 'test', 1, {}, []);
      expect(testFn).to.throw(/locale expects string only/);

      testFn = () => this.stock.searchFilesByKeywords(this.accessToken, 'test', {}, {}, []);
      expect(testFn).to.throw(/locale expects string only/);

      testFn = () => this.stock.searchFilesByKeywords(this.accessToken, 'test', [], {}, []);
      expect(testFn).to.throw(/locale expects string only/);
    });

    it('should throw error Access Token missing if accessToken is not provided and result column is_licensed is requested', function () {
      const testFn = () => this.stock.searchFilesByKeywords(undefined, 'test', 'en-US', {},
                                                            [Constants.RESULT_COLUMNS.IS_LICENSED]);
      expect(testFn).to.throw(/Access Token missing! Result Column 'is_licensed' requires authentication\./);
    });

    it('should throw error Invalid value of Search Parameter if keywords provided are other than string', function () {
      let testFn = () => this.stock.searchFilesByKeywords(this.accessToken, 695, 'en-US', {},
                                                            [Constants.RESULT_COLUMNS.ID]);
      expect(testFn).to.throw(/Invalid value of Search Parameter 'words'\./);

      testFn = () => this.stock.searchFilesByKeywords(this.accessToken, { test: '695' }, 'en-US', {},
                                                            [Constants.RESULT_COLUMNS.ID]);
      expect(testFn).to.throw(/Invalid value of Search Parameter 'words'\./);

      testFn = () => this.stock.searchFilesByKeywords(this.accessToken, ['695'], 'en-US', {},
                                                            [Constants.RESULT_COLUMNS.ID]);
      expect(testFn).to.throw(/Invalid value of Search Parameter 'words'\./);

      testFn = () => this.stock.searchFilesByKeywords(this.accessToken, undefined, 'en-US', {},
                                                            [Constants.RESULT_COLUMNS.ID]);
      expect(testFn).to.throw(/Invalid value of Search Parameter 'words'\./);
    });

    it('should return object of SearchFilesIterator type with relevant properties set', function () {
      let stock = this.stock.searchFilesByKeywords(this.accessToken, 'test', 'en-US', {},
                                                            [Constants.RESULT_COLUMNS.ID]);
      expect(stock).to.be.ok;
      expect(stock).instanceof(SearchFilesIterator);
      expect(stock).to.have.property('stockApis', this.stock.stockApis);
      expect(stock).to.have.property('accessToken', this.accessToken);
      expect(stock).to.have.property('queryParams');
      expect(stock).to.have.property('nbResultsPresent', false);
      expect(stock).to.have.property('response');
      expect(stock.response).to.be.empty;
      expect(stock).to.have.property('lastQueryParams', null);
      expect(stock).to.have.property('apiInProgress', false);

      stock = this.stock.searchFilesByKeywords(this.accessToken, 'test', 'en-US');
      expect(stock).to.be.ok;
      expect(stock).instanceof(SearchFilesIterator);
      expect(stock).to.have.property('nbResultsPresent', true);

      stock = this.stock.searchFilesByKeywords(this.accessToken, 'test', 'en-US', undefined,
                                [Constants.RESULT_COLUMNS.NB_RESULTS, Constants.RESULT_COLUMNS.ID]);
      expect(stock).to.be.ok;
      expect(stock).instanceof(SearchFilesIterator);
      expect(stock).to.have.property('nbResultsPresent', true);
    });
  });

  describe('licenseHistory', () => {
    beforeEach(function () {
      this.stock = new AdobeStock('testApiKey', 'testProduct', Constants.ENVIRONMENT.STAGE);
      this.accessToken = 'testAccessToken';
      this.queryParams = {
        locale: 'en-US',
        search_parameters: {
          limit: 10,
          offset: 10,
          thumbnail_size: 160,
        },
      };

      this.resultColumns = [
        Constants.LICENSE_HISTORY_RESULT_COLUMNS.THUMBNAIL_110_URL,
      ];
    });

    it('should throw error Library not initialized', function () {
      const isConfigInitialized = sinon.stub(this.stock.config, 'isConfigInitialized');

      isConfigInitialized.returns(false);
      let testFn = () => this.stock.licenseHistory(this.accessToken, this.queryParams, []);
      expect(testFn).to.throw('Library not initialized! Please initialize the library first.');

      isConfigInitialized.returns(true);
      testFn = () => this.stock.licenseHistory(this.accessToken, this.queryParams, []);
      expect(testFn).to.not.throw(Error);

      isConfigInitialized.restore();
    });

    it('should throw error queryParams expects Object if queryParams argument passed is not object', function () {
      let testFn = () => this.stock.licenseHistory(this.accessToken, 'test', []);
      expect(testFn).to.throw(/queryParams expects Object/);

      testFn = () => this.stock.licenseHistory(this.accessToken, [], []);
      expect(testFn).to.throw(/queryParams expects Object/);

      testFn = () => this.stock.licenseHistory(this.accessToken, 1, []);
      expect(testFn).to.throw(/queryParams expects Object/);
    });

    it('should throw error resultColumns expects Array if filters argument passed is not object', function () {
      let testFn = () => this.stock.licenseHistory(this.accessToken, this.queryParams, 'test');
      expect(testFn).to.throw(/resultColumns expects Array/);

      testFn = () => this.stock.licenseHistory(this.accessToken, this.queryParams, {});
      expect(testFn).to.throw(/resultColumns expects Array/);

      testFn = () => this.stock.licenseHistory(this.accessToken, this.queryParams, 1);
      expect(testFn).to.throw(/resultColumns expects Array/);
    });

    it('should throw error locale expects string only if locale argument is passed other than string', function () {
      this.queryParams.locale = undefined;
      let testFn = () => this.stock.licenseHistory(this.accessToken,
                                                          this.queryParams, this.resultColumns);
      expect(testFn).to.throw(/locale expects string only/);

      this.queryParams.locale = 1;
      testFn = () => this.stock.licenseHistory(this.accessToken,
                                                      this.queryParams, this.resultColumns);
      expect(testFn).to.throw(/locale expects string only/);

      this.queryParams.locale = {};
      testFn = () => this.stock.licenseHistory(this.accessToken,
                                                      this.queryParams, this.resultColumns);
      expect(testFn).to.throw(/locale expects string only/);
    });

    it('should throw error Invalid query parameter if any unsupported query parameter is passed with queryParams argument', function () {
      this.queryParams.test = 'test';
      const testFn = () => this.stock.licenseHistory(this.accessToken,
                                                    this.queryParams, this.resultColumns);
      expect(testFn).to.throw(/Invalid query parameter 'test'/);
    });

    it('should throw error Search parameter not supported if any unsupported search parameter is passed with queryParams', function () {
      this.queryParams.search_parameters.test = 0;
      const testFn = () => this.stock.licenseHistory(undefined,
                                                    this.queryParams, this.resultColumns);
      expect(testFn).to.throw(/Search parameter 'test' not supported!/);
    });

    it('should throw error Invalid Result Column if any unsupported result column is passed with resultColumns argument', function () {
      this.resultColumns.push('test');
      const testFn = () => this.stock.licenseHistory(undefined,
                                                    this.queryParams, this.resultColumns);
      expect(testFn).to.throw(/Invalid Result Column 'test'/);
    });

    it('should throw error Invalid value of Search Parameter if queryParams.search_parameters properties have invalid value', function () {
      this.queryParams.search_parameters.limit = 'hello';
      let testFn = () => this.stock.licenseHistory(this.accessToken,
                                                  this.queryParams, this.resultColumns);
      expect(testFn).to.throw(/Invalid value of Search Parameter/);

      this.queryParams.search_parameters.offset = 2;
      this.queryParams.search_parameters.limit = 'test';
      testFn = () => this.stock.licenseHistory(this.accessToken,
                                                            this.queryParams, this.resultColumns);
      expect(testFn).to.throw(/Invalid value of Search Parameter/);

      this.queryParams.search_parameters.limit = 10;
      this.queryParams.search_parameters.thumbnail_size = 'test';
      testFn = () => this.stock.licenseHistory(this.accessToken,
                                                            this.queryParams, this.resultColumns);
      expect(testFn).to.throw(/Invalid value of Search Parameter/);
    });

    it('should return object of LicenseHistoryIterator type with relevant properties set', function () {
      const stock = this.stock.licenseHistory(this.accessToken,
                                              this.queryParams, this.resultColumns);
      expect(stock).to.be.ok;
      expect(stock).instanceof(LicenseHistoryIterator);
      expect(stock).to.have.property('stockApis', this.stock.stockApis);
      expect(stock).to.have.property('accessToken', this.accessToken);
      expect(stock).to.have.property('queryParams');
      expect(stock).to.have.property('nbResultsPresent', true);
      expect(stock).to.have.property('response');
      expect(stock.response).to.be.empty;
      expect(stock).to.have.property('lastQueryParams', null);
      expect(stock).to.have.property('apiInProgress', false);
    });
  });

  describe('searchFiles', () => {
    beforeEach(function () {
      this.stock = new AdobeStock('testApiKey', 'testProduct', Constants.ENVIRONMENT.STAGE);
      this.accessToken = 'testAccessToken';
      this.queryParams = {
        locale: 'en-US',
        search_parameters: {
          words: 'search keywords',
          limit: 10,
          offset: 10,
          filters_template_category_id: [
            Constants.SEARCH_PARAMS_TEMPLATE_CATEGORIES.PRINT,
            Constants.SEARCH_PARAMS_TEMPLATE_CATEGORIES.PHOTO,
          ],
          filters_area_pixels: '0-2500',
          similar_image: 1,
        },
        similar_image: 'test data',
      };

      this.resultColumns = [
        Constants.RESULT_COLUMNS.ID,
        Constants.RESULT_COLUMNS.TITLE,
        Constants.RESULT_COLUMNS.NB_RESULTS,
      ];
    });

    it('should throw error Library not initialized', function () {
      const isConfigInitialized = sinon.stub(this.stock.config, 'isConfigInitialized');

      isConfigInitialized.returns(false);
      let testFn = () => this.stock.searchFiles(this.accessToken, this.queryParams, []);
      expect(testFn).to.throw('Library not initialized! Please initialize the library first.');

      isConfigInitialized.returns(true);
      testFn = () => this.stock.searchFiles(this.accessToken, this.queryParams, []);
      expect(testFn).to.not.throw(Error);

      isConfigInitialized.restore();
    });

    it('should throw error queryParams expects Object if queryParams argument passed is not object', function () {
      let testFn = () => this.stock.searchFiles(this.accessToken, 'test', []);
      expect(testFn).to.throw(/queryParams expects Object/);

      testFn = () => this.stock.searchFiles(this.accessToken, [], []);
      expect(testFn).to.throw(/queryParams expects Object/);

      testFn = () => this.stock.searchFiles(this.accessToken, 1, []);
      expect(testFn).to.throw(/queryParams expects Object/);
    });

    it('should throw error resultColumns expects Array if filters argument passed is not object', function () {
      let testFn = () => this.stock.searchFiles(this.accessToken, this.queryParams, 'test');
      expect(testFn).to.throw(/resultColumns expects Array/);

      testFn = () => this.stock.searchFiles(this.accessToken, this.queryParams, {});
      expect(testFn).to.throw(/resultColumns expects Array/);

      testFn = () => this.stock.searchFiles(this.accessToken, this.queryParams, 1);
      expect(testFn).to.throw(/resultColumns expects Array/);
    });

    it('should throw error locale expects string only if locale argument is passed other than string', function () {
      this.queryParams.locale = undefined;
      let testFn = () => this.stock.searchFiles(this.accessToken,
                                                          this.queryParams, this.resultColumns);
      expect(testFn).to.throw(/locale expects string only/);

      this.queryParams.locale = 1;
      testFn = () => this.stock.searchFiles(this.accessToken,
                                                      this.queryParams, this.resultColumns);
      expect(testFn).to.throw(/locale expects string only/);

      this.queryParams.locale = {};
      testFn = () => this.stock.searchFiles(this.accessToken,
                                                      this.queryParams, this.resultColumns);
      expect(testFn).to.throw(/locale expects string only/);
    });

    it('should throw error Access Token missing if accessToken is not provided and result column is_licensed is requested', function () {
      this.resultColumns.push(Constants.RESULT_COLUMNS.IS_LICENSED);
      const testFn = () => this.stock.searchFiles(undefined,
                                                    this.queryParams, this.resultColumns);
      expect(testFn).to.throw(/Access Token missing! Result Column 'is_licensed' requires authentication\./);
    });

    it('should throw error Invalid query parameter if any unsupported query parameter is passed with queryParams argument', function () {
      this.queryParams.test = 'test';
      const testFn = () => this.stock.searchFiles(this.accessToken,
                                                    this.queryParams, this.resultColumns);
      expect(testFn).to.throw(/Invalid query parameter 'test'/);
    });

    it('should throw error Search parameter not supported if any unsupported search parameter is passed with queryParams', function () {
      this.queryParams.search_parameters.test = 0;
      const testFn = () => this.stock.searchFiles(undefined,
                                                    this.queryParams, this.resultColumns);
      expect(testFn).to.throw(/Search parameter 'test' not supported!/);
    });

    it('should throw error Invalid Result Column if any unsupported result column is passed with resultColumns argument', function () {
      this.resultColumns.push('test');
      const testFn = () => this.stock.searchFiles(undefined,
                                                    this.queryParams, this.resultColumns);
      expect(testFn).to.throw(/Invalid Result Column 'test'/);
    });

    it('should throw error Invalid value of Search Parameter if queryParams.search_parameters properties have invalid value', function () {
      this.queryParams.search_parameters.words = 1;
      let testFn = () => this.stock.searchFiles(this.accessToken,
                                                  this.queryParams, this.resultColumns);
      expect(testFn).to.throw(/Invalid value of Search Parameter/);

      this.queryParams.search_parameters.words = 'test';
      this.queryParams.search_parameters.limit = 'test';
      testFn = () => this.stock.searchFiles(this.accessToken,
                                                            this.queryParams, this.resultColumns);
      expect(testFn).to.throw(/Invalid value of Search Parameter/);

      this.queryParams.search_parameters.limit = 10;
      this.queryParams.search_parameters.filters_template_category_id[1] = 'test';
      testFn = () => this.stock.searchFiles(this.accessToken,
                                                            this.queryParams, this.resultColumns);
      expect(testFn).to.throw(/Invalid value of Search Parameter/);

      this.queryParams.search_parameters.filters_template_category_id[1] =
                                            Constants.SEARCH_PARAMS_TEMPLATE_CATEGORIES.PHOTO;
      this.queryParams.search_parameters.filters_area_pixels = '250';
      testFn = () => this.stock.searchFiles(this.accessToken,
                                                            this.queryParams, this.resultColumns);
      expect(testFn).to.throw(/Invalid value of Search Parameter/);
    });

    it('should throw error Image Data missing if similar_image param is not provided with queryParams and search_parameters has similar_image param set to 1', function () {
      delete this.queryParams.similar_image;
      const testFn = () => this.stock.searchFiles(undefined,
                                                    this.queryParams, this.resultColumns);
      expect(testFn).to.throw(/Image Data missing! Search parameter similar_image requires similar_image in query parameters/);
    });

    it('should return object of SearchFilesIterator type with relevant properties set', function () {
      let stock = this.stock.searchFiles(this.accessToken,
                                              this.queryParams, this.resultColumns);
      expect(stock).to.be.ok;
      expect(stock).instanceof(SearchFilesIterator);
      expect(stock).to.have.property('stockApis', this.stock.stockApis);
      expect(stock).to.have.property('accessToken', this.accessToken);
      expect(stock).to.have.property('queryParams');
      expect(stock).to.have.property('nbResultsPresent', true);
      expect(stock).to.have.property('response');
      expect(stock.response).to.be.empty;
      expect(stock).to.have.property('lastQueryParams', null);
      expect(stock).to.have.property('apiInProgress', false);

      stock = this.stock.searchFiles(this.accessToken,
                                        this.queryParams, [Constants.RESULT_COLUMNS.ID]);
      expect(stock).to.be.ok;
      expect(stock).instanceof(SearchFilesIterator);
      expect(stock).to.have.property('nbResultsPresent', false);
    });
  });

  describe('searchCategory', () => {
    beforeEach(function () {
      this.stock = new AdobeStock('testApiKey', 'testProduct', Constants.ENVIRONMENT.STAGE);
      this.queryParams = {
        locale: 'en_us',
        category_id: 1043,
      };
    });

    it('should throw error Library not initialized', function () {
      const isConfigInitialized = sinon.stub(this.stock.config, 'isConfigInitialized');

      isConfigInitialized.returns(false);
      let testFn = () => this.stock.searchCategory(this.queryParams);
      expect(testFn).to.throw('Library not initialized! Please initialize the library first.');

      isConfigInitialized.returns(true);
      testFn = () => this.stock.searchCategory(this.queryParams);
      expect(testFn).to.not.throw(Error);

      isConfigInitialized.restore();
    });
  });

  describe('searchCategoryTree', () => {
    beforeEach(function () {
      this.stock = new AdobeStock('testApiKey', 'testProduct', Constants.ENVIRONMENT.STAGE);
      this.queryParams = {
        locale: 'en_us',
        category_id: 1043,
      };
    });

    it('should throw error Library not initialized', function () {
      const isConfigInitialized = sinon.stub(this.stock.config, 'isConfigInitialized');

      isConfigInitialized.returns(false);
      let testFn = () => this.stock.searchCategoryTree(this.queryParams);
      expect(testFn).to.throw('Library not initialized! Please initialize the library first.');

      isConfigInitialized.returns(true);
      testFn = () => this.stock.searchCategoryTree(this.queryParams);
      expect(testFn).to.not.throw(Error);

      isConfigInitialized.restore();
    });
  });

  describe('accessMemberProfile', () => {
    beforeEach(function () {
      this.stock = new AdobeStock('testApiKey', 'testProduct', Constants.ENVIRONMENT.STAGE);
      this.accessToken = 'testAccessToken';
    });

    it('should throw error contentId expects Integer if contentId argument passed is not Integer', function () {
      let testFn = () => this.stock.accessMemberProfile(this.accessToken, 'test', 'STANDARD', 'en_us');
      expect(testFn).to.throw(/contentId is not of type Integer/);

      testFn = () => this.stock.accessMemberProfile(this.accessToken, [], 'STANDARD', 'en_us');
      expect(testFn).to.throw(/contentId is not of type Integer/);

      testFn = () => this.stock.accessMemberProfile(this.accessToken, {}, 'STANDARD', 'en_us');
      expect(testFn).to.throw(/contentId is not of type Integer/);
    });

    it('should throw error license expects string if license argument passed is not string', function () {
      let testFn = () => this.stock.accessMemberProfile(this.accessToken, 1234, 1, 'en_us');
      expect(testFn).to.throw(/license is not of type string/);

      testFn = () => this.stock.accessMemberProfile(this.accessToken, 1234, [], 'en_us');
      expect(testFn).to.throw(/license is not of type string/);

      testFn = () => this.stock.accessMemberProfile(this.accessToken, 1234, {}, 'en_us');
      expect(testFn).to.throw(/license is not of type string/);
    });

    it('should throw error locale expects string if locale argument is passed other than string', function () {
      let testFn = () => this.stock.accessMemberProfile(this.accessToken, 1234, 'STANDARD', 1);
      expect(testFn).to.throw(/locale expects string/);

      testFn = () => this.stock.accessMemberProfile(this.accessToken, 1234, 'STANDARD', []);
      expect(testFn).to.throw(/locale expects string/);

      testFn = () => this.stock.accessMemberProfile(this.accessToken, 1234, 'STANDARD', {});
      expect(testFn).to.throw(/locale expects string/);
    });

    it('should throw error Access Token missing if accessToken is not provided', function () {
      const testFn = () => this.stock.accessMemberProfile(undefined, 1234, 'STANDARD', 'en_us');
      expect(testFn).to.throw(/access token missing or not of type string!/);
    });

    it('should throw error Access Token missing if accessToken is not provided', function () {
      const testFn = () => this.stock.accessMemberProfile(4, 1234, 'STANDARD', 'en_us');
      expect(testFn).to.throw(/access token missing or not of type string!/);
    });

    it('should return object of Promise type with relevant properties set', function () {
      const stock = this.stock.accessMemberProfile(this.accessToken, 1234, 'STANDARD', 'en_us');

      expect(stock).to.be.ok;
      expect(stock).instanceof(Promise);
    });
  });

  describe('memberAbandonLicensing', () => {
    beforeEach(function () {
      this.stock = new AdobeStock('testApiKey', 'testProduct', Constants.ENVIRONMENT.STAGE);
      this.accessToken = 'testAccessToken';
    });

    it('should return object of Promise type with relevant properties set', function () {
      const stock = this.stock.memberAbandonLicensing(this.accessToken, 1234, 'PURCHASED');

      expect(stock).to.be.ok;
      expect(stock).instanceof(Promise);
    });
  });

  describe('getLicenseInfoForContent', () => {
    beforeEach(function () {
      this.stock = new AdobeStock('testApiKey', 'testProduct', Constants.ENVIRONMENT.STAGE);
      this.accessToken = 'testAccessToken';
    });

    it('should return object of Promise type with relevant properties set', function () {
      const stock = this.stock.getLicenseInfoForContent(this.accessToken, 1234, 'STANDARD');

      expect(stock).to.be.ok;
      expect(stock).instanceof(Promise);
    });
  });

  describe('requestLicenseForContent', () => {
    beforeEach(function () {
      this.stock = new AdobeStock('testApiKey', 'testProduct', Constants.ENVIRONMENT.STAGE);
      this.accessToken = 'testAccessToken';
    });

    it('should return object of Promise type with relevant properties set', function () {
      const stock = this.stock.requestLicenseForContent(this.accessToken, 1234, 'STANDARD');

      expect(stock).to.be.ok;
      expect(stock).instanceof(Promise);
    });
  });

  describe('downloadAsset', () => {
    beforeEach(function () {
      this.stock = new AdobeStock('testApiKey', 'testProduct', Constants.ENVIRONMENT.STAGE);
      this.accessToken = 'testAccessToken';
    });

    it('should return object of Promise type with relevant properties set', function () {
      const stock = this.stock.downloadAsset(this.accessToken, 1234,
                                             Constants.LICENSE_STATE_PARAMS.IMAGE.STANDARD);
      expect(stock).to.be.ok;
      expect(stock).instanceof(Promise);
    });
  });
});
