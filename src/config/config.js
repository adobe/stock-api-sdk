/**
 * Copyright 2017 Adobe Systems Incorporated. All rights reserved.
 * This file is licensed to you under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License. You may obtain a copy
 * of the License at http://www.apache.org/licenses/LICENSE-2.0
 */
import Constants from './../constants/constants';

const prodEndpoints = require('./../../resources/prod_env.json');
const stageEndpoints = require('./../../resources/stage_env.json');

export default class Config {

  /**
   * Constructor for Config class
   * @param {string} apiKey x_api_key header for the api calls
   * @param {string} product x_product header for the api calls
   * @param {ENVIRONMENT} env defines the environment for the library
   */
  constructor(apiKey, product, env = Constants.ENVIRONMENT.STAGE) {
    this.target_env = env;

    this.endpoints = (this.target_env === Constants.ENVIRONMENT.PROD)
        ? prodEndpoints : stageEndpoints;

    if (apiKey) {
      this.x_api_key = apiKey;
    } else {
      throw new Error('Api Key configuration is missing!');
    }

    if (product) {
      this.x_product = product;
    } else {
      throw new Error('Product configuration is missing!');
    }
  }

  /**
   * Checks if the config is initialized/set
   * @returns {boolean} true if config is initialized/set, false otherwise
   */
  isConfigInitialized() {
    if (this.target_env && this.endpoints && this.x_api_key && this.x_product) {
      return true;
    }
    return false;
  }
}
