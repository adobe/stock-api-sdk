/**
 * Copyright 2017 Adobe Systems Incorporated. All rights reserved.
 * This file is licensed to you under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License. You may obtain a copy
 * of the License at http://www.apache.org/licenses/LICENSE-2.0
 */
import { expect } from 'chai';
import Config from './../src/config/config';
import Constants from './../src/constants/constants';

const prodEndpoints = require('./../resources/prod_env.json');
const stageEndpoints = require('./../resources/stage_env.json');

describe('Config', () => {
  describe('constructor', () => {
    it('should create object of Config type with properties x_api_key, x_product, target_env, endpoints', () => {
      const stageConfig = new Config('testApiKey', 'testProduct', Constants.ENVIRONMENT.STAGE);
      expect(stageConfig).to.be.ok;
      expect(stageConfig).to.have.property('x_api_key', 'testApiKey');
      expect(stageConfig).to.have.property('x_product', 'testProduct');
      expect(stageConfig).to.have.property('target_env', Constants.ENVIRONMENT.STAGE);
      expect(stageConfig).to.have.property('endpoints', stageEndpoints);

      const prodConfig = new Config('testProdApiKey', 'testProdProduct', Constants.ENVIRONMENT.PROD);
      expect(prodConfig).to.be.ok;
      expect(prodConfig).to.have.property('x_api_key', 'testProdApiKey');
      expect(prodConfig).to.have.property('x_product', 'testProdProduct');
      expect(prodConfig).to.have.property('target_env', Constants.ENVIRONMENT.PROD);
      expect(prodConfig).to.have.property('endpoints', prodEndpoints);
    });

    it('should throw error if apiKey argument provided not ok', () => {
      const testFn = () => new Config('', 'testProduct', Constants.ENVIRONMENT.STAGE);
      expect(testFn).to.throw(/Api Key configuration is missing!/);
    });

    it('should throw error if product argument provided not ok', () => {
      const testFn = () => new Config('testApiKey', '', Constants.ENVIRONMENT.STAGE);
      expect(testFn).to.throw(/Product configuration is missing!/);
    });
  });

  // test cases for isConfigInitialized
  describe('isConfigInitialized', () => {
    it('should return true since the Config object is created successfully', () => {
      const config = new Config('testApiKey', 'testProduct', Constants.ENVIRONMENT.STAGE);
      expect(config.isConfigInitialized()).to.be.true;
    });

    it('should return false since the Config object properties modified with invalid values later', () => {
      const config = new Config('testApiKey', 'testProduct', Constants.ENVIRONMENT.STAGE);
      config.x_api_key = '';
      expect(config.isConfigInitialized()).to.be.false;

      config.x_api_key = 'testApiKey';
      config.x_product = '';
      expect(config.isConfigInitialized()).to.be.false;

      config.x_api_key = 'testApiKey';
      config.x_product = 'testProduct';
      config.target_env = undefined;
      expect(config.isConfigInitialized()).to.be.false;

      config.x_api_key = 'testApiKey';
      config.x_product = 'testProduct';
      config.target_env = Constants.ENVIRONMENT.STAGE;
      config.endpoints = null;
      expect(config.isConfigInitialized()).to.be.false;
    });
  });
});
