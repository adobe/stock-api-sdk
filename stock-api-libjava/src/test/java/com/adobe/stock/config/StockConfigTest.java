/*******************************************************************************
 * Copyright 2017 Adobe Systems Incorporated. All rights reserved.
 * This file is licensed to you under the Apache License, Version 2.0
 * (the "License") you may not use this file except in compliance with the
 * License. You may obtain a copy of the License at
 * http://www.apache.org/licenses/LICENSE-2.0
 *******************************************************************************/
package com.adobe.stock.config;

import java.lang.reflect.Field;

import org.mockito.Mockito;
import org.powermock.api.mockito.PowerMockito;
import org.powermock.core.classloader.annotations.PrepareForTest;
import org.powermock.modules.testng.PowerMockObjectFactory;
import org.testng.Assert;
import org.testng.IObjectFactory;
import org.testng.annotations.Listeners;
import org.testng.annotations.ObjectFactory;
import org.testng.annotations.Test;

import com.adobe.stock.enums.Environment;
import com.adobe.stock.exception.StockException;

@PrepareForTest({ Endpoints.class })
@Listeners({ com.adobe.stock.logger.TestCustomLogger.class,
        com.adobe.stock.logger.TestSuiteLogger.class })
@Test(suiteName = "StockConfig")
public class StockConfigTest {

    @ObjectFactory
    public IObjectFactory getObjectFactory() {
        return new PowerMockObjectFactory();
    }

    @Test(groups = "StockConfig.StockConfig")
    public void StockConfig_should_throw_stock_exception_since_the_endpoint_properties_file_is_missing() {
        PowerMockito.spy(Endpoints.class);

        try {
            PowerMockito.doReturn(null).when(Endpoints.class,
                    "getResourceAsStream", Mockito.any(String.class));
        } catch (Exception e1) {
            Assert.fail(
                    "Couldn't mock the Endpoints.getResourceAsStream method!",
                    e1);
        }

        try {
            new StockConfig();

            Assert.fail("Didn't expect the endpoints to get constructed without exception!");
        } catch (StockException e) {
            Assert.assertEquals(e.getMessage(),
                    "Could not load the endpoint properties file");
        }
    }

    @Test(groups = "StockConfig.isConfigInitialized")
    public void isConfigInitialized_should_return_false_since_api_key_is_not_set() {
        try {
            StockConfig config = new StockConfig();

            Assert.assertFalse(config.isConfigInitialized());

            config.setProduct("TestProduct");

            Assert.assertFalse(config.isConfigInitialized());

            config.setTargetEnvironment(Environment.PROD);

            Assert.assertFalse(config.isConfigInitialized());
        } catch (StockException e) {
            Assert.fail("Didn't expect the exception here!", e);
        }
    }

    @Test(groups = "StockConfig.isConfigInitialized")
    public void isConfigInitialized_should_return_false_since_product_is_not_set() {
        try {
            StockConfig config = new StockConfig();

            Assert.assertFalse(config.isConfigInitialized());

            config.setApiKey("TestApiKey");

            Assert.assertFalse(config.isConfigInitialized());

            config.setTargetEnvironment(Environment.STAGE);

            Assert.assertFalse(config.isConfigInitialized());
        } catch (StockException e) {
            Assert.fail("Didn't expect the exception here!", e);
        }
    }

    @Test(groups = "StockConfig.isConfigInitialized")
    public void isConfigInitialized_should_return_true_since_all_config_params_set_properly() {
        try {
            StockConfig config = new StockConfig().setApiKey("TestApiKey")
                    .setProduct("TestProduct");

            Assert.assertTrue(config.isConfigInitialized());

            config.setTargetEnvironment(Environment.STAGE);

            Assert.assertTrue(config.isConfigInitialized());
        } catch (StockException e) {
            Assert.fail("Didn't expect the exception here!", e);
        }
    }

    @Test(groups = "StockConfig.getTargetEnvironment")
    public void getTargetEnvironment_should_return_prod_environment_since_set_environment_is_prod()
            throws StockException {
        Environment[] envs = { Environment.PROD, Environment.STAGE };
        StockConfig config = new StockConfig();

        try {
            for (Environment env : envs) {
                Field f = StockConfig.class
                        .getDeclaredField("mTargetEnvironment");
                f.setAccessible(true);
                f.set(config, env);

                Assert.assertEquals(config.getTargetEnvironment(), env);
            }
        } catch (Exception e) {
            Assert.fail("Didn't expect the exception here!", e);
        }

    }

    @Test(groups = "StockConfig.setTargetEnvironment")
    public void setTargetEnvironment_should_not_modify_target_environment_config_param_since_parameter_passed_is_null()
            throws StockException {
        StockConfig config = new StockConfig();

        try {
            Field f = StockConfig.class.getDeclaredField("mTargetEnvironment");
            f.setAccessible(true);
            Environment env = (Environment) f.get(config);

            config.setTargetEnvironment(null);

            Assert.assertEquals((Environment) f.get(config), env);
        } catch (Exception e) {
            Assert.fail("Didn't expect the exception here!", e);
        }

    }

    @Test(groups = "StockConfig.setTargetEnvironment")
    public void setTargetEnvironment_should_set_target_environment_and_endpoints_config_params_as_per_environment_parameter_passed()
            throws StockException {
        StockConfig config = new StockConfig();

        try {
            Field fEnv = StockConfig.class
                    .getDeclaredField("mTargetEnvironment");
            fEnv.setAccessible(true);

            Field fEndpoints = StockConfig.class.getDeclaredField("mEndpoints");
            fEndpoints.setAccessible(true);

            Field fEndpointsEnv = Endpoints.class
                    .getDeclaredField("mEnvironment");
            fEndpointsEnv.setAccessible(true);

            config.setTargetEnvironment(Environment.PROD);

            Assert.assertEquals((Environment) fEnv.get(config),
                    Environment.PROD);
            Endpoints endpoints = (Endpoints) fEndpoints.get(config);
            Assert.assertEquals(fEndpointsEnv.get(endpoints), Environment.PROD);

            config.setTargetEnvironment(Environment.STAGE);

            Assert.assertEquals((Environment) fEnv.get(config),
                    Environment.STAGE);

            endpoints = (Endpoints) fEndpoints.get(config);
            Assert.assertEquals(fEndpointsEnv.get(endpoints), Environment.STAGE);

        } catch (Exception e) {
            Assert.fail("Didn't expect the exception here!", e);
        }

    }

    @Test(groups = "StockConfig.getEndpoints")
    public void getEndpoints_should_return_endpoints_with_stage_environment_if_environment_set_to_stage()
            throws StockException {
        StockConfig config = new StockConfig()
                .setTargetEnvironment(Environment.STAGE);

        Assert.assertEquals(config.getEndpoints().getEnvironment(),
                Environment.STAGE);
    }

    @Test(groups = "StockConfig.getEndpoints")
    public void getEndpoints_should_return_endpoints_with_prod_environment_if_environment_set_to_prod()
            throws StockException {
        StockConfig config = new StockConfig()
                .setTargetEnvironment(Environment.PROD);

        Assert.assertEquals(config.getEndpoints().getEnvironment(),
                Environment.PROD);
    }

    @Test(groups = "StockConfig.getApiKey")
    public void getApiKey_should_return_null_if_api_key_is_not_set()
            throws StockException {
        StockConfig config = new StockConfig();

        Assert.assertNull(config.getApiKey());
    }

    @Test(groups = "StockConfig.getApiKey")
    public void getApiKey_should_return_api_key_if_api_key_set()
            throws StockException {
        StockConfig config = new StockConfig();

        try {
            Field fApiKey = StockConfig.class.getDeclaredField("mApiKey");
            fApiKey.setAccessible(true);
            fApiKey.set(config, "ApiKey");

            Assert.assertEquals(config.getApiKey(), "ApiKey");
        } catch (Exception e) {
            Assert.fail("Didn't expect the exception here!", e);
        }

    }

    @Test(groups = "StockConfig.setApiKey")
    public void setApiKey_should_throw_stockexception_if_parameter_passed_is_null() {
        try {
            new StockConfig().setApiKey(null);

            Assert.fail("Expected setApiKey to throw exception since parameter passed is null");
        } catch (Exception e) {
            Assert.assertEquals(e.getMessage(),
                    "Api Key configuration can't be null!");
        }

    }

    @Test(groups = "StockConfig.setApiKey")
    public void setApiKey_should_set_api_key_if_parameter_passed_is_non_null() {
        try {
            StockConfig config = new StockConfig().setApiKey("ApiKey");
            Field fApiKey = StockConfig.class.getDeclaredField("mApiKey");
            fApiKey.setAccessible(true);

            Assert.assertEquals(fApiKey.get(config), "ApiKey");

            config.setApiKey("NewApiKey");
            Assert.assertEquals(fApiKey.get(config), "NewApiKey");
        } catch (Exception e) {
            Assert.fail("Didn't expect the exception here!", e);
        }

    }

    @Test(groups = "StockConfig.getProduct")
    public void getProduct_should_return_null_if_product_is_not_set()
            throws StockException {
        StockConfig config = new StockConfig();

        Assert.assertNull(config.getProduct());
    }

    @Test(groups = "StockConfig.getProduct")
    public void getProduct_should_return_product_if_product_set()
            throws StockException {
        StockConfig config = new StockConfig();

        try {
            Field fApiKey = StockConfig.class.getDeclaredField("mProduct");
            fApiKey.setAccessible(true);
            fApiKey.set(config, "AProduct");

            Assert.assertEquals(config.getProduct(), "AProduct");
        } catch (Exception e) {
            Assert.fail("Didn't expect the exception here!", e);
        }
    }

    @Test(groups = "StockConfig.setProduct")
    public void setProduct_should_throw_stockexception_if_parameter_passed_is_null() {
        try {
            new StockConfig().setProduct(null);

            Assert.fail("Expected setApiKey to throw exception since parameter passed is null");
        } catch (Exception e) {
            Assert.assertEquals(e.getMessage(),
                    "Product configuration can't be null!");
        }

    }

    @Test(groups = "StockConfig.setProduct")
    public void setProduct_should_set_api_key_if_parameter_passed_is_non_null() {
        try {
            StockConfig config = new StockConfig().setProduct("AProduct");
            Field fProduct = StockConfig.class.getDeclaredField("mProduct");
            fProduct.setAccessible(true);

            Assert.assertEquals(fProduct.get(config), "AProduct");

            config.setProduct("BProduct");
            Assert.assertEquals(fProduct.get(config), "BProduct");
        } catch (Exception e) {
            Assert.fail("Didn't expect the exception here!", e);
        }

    }
}