/*******************************************************************************
 * Copyright 2017 Adobe Systems Incorporated. All rights reserved.
 * This file is licensed to you under the Apache License, Version 2.0
 * (the "License") you may not use this file except in compliance with the
 * License. You may obtain a copy of the License at
 * http://www.apache.org/licenses/LICENSE-2.0
 *******************************************************************************/
package com.adobe.stock.apis;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.util.Map;

import org.testng.Assert;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;

import com.adobe.stock.config.StockConfig;
import com.adobe.stock.enums.Environment;

@Listeners({ com.adobe.stock.logger.TestCustomLogger.class,
        com.adobe.stock.logger.TestSuiteLogger.class })
@Test(suiteName = "ApiUtils")
public class ApiUtilsTest {

    private static final String X_API_KEY = "x-api-key";
    private static final String X_PRODUCT = "x-product";
    private static final String X_PRODUCT_LOCATION = "x-product-location";
    private static final String X_REQUEST_ID = "x-request-id";
    private static final String AUTHORIZATION = "Authorization";
    private static final String BEARER = "Bearer ";

    @Test
    public void generateCommonAPIHeaders_should_return_all_valid_headers() {
        try {
            String apiKey = "ApiKey", product = "TestProduct",
                    location = "TestLocation";
            StockConfig config = new StockConfig().setApiKey(apiKey)
                    .setProduct(product).setProductLocation(location)
                    .setTargetEnvironment(Environment.STAGE);
            String accessToken = "TestToken";
            Map<String, String> headers = ApiUtils
                    .generateCommonAPIHeaders(config, accessToken);

            Assert.assertEquals(headers.get(X_API_KEY), apiKey);
            Assert.assertEquals(headers.get(X_PRODUCT), product);
            Assert.assertEquals(headers.get(X_PRODUCT_LOCATION), location);
            Assert.assertEquals(headers.get(AUTHORIZATION),
                    BEARER + accessToken);
            Assert.assertNotNull(headers.get(X_REQUEST_ID));
        } catch (Exception e) {
            Assert.fail("Didn't expect the exception!", e);
        }
    }

    @Test
    public void generateCommonAPIHeaders_should_not_return_headers_for_null_values() {
        try {
            StockConfig config = new StockConfig();
            Map<String, String> headers = ApiUtils
                    .generateCommonAPIHeaders(config, null);
            Assert.assertFalse(headers.containsKey(X_API_KEY));
            Assert.assertFalse(headers.containsKey(X_PRODUCT));
            Assert.assertFalse(headers.containsKey(X_PRODUCT_LOCATION));
            Assert.assertFalse(headers.containsKey(AUTHORIZATION));
        } catch (Exception e) {
            Assert.fail("Didn't expect the exception!", e);
        }
    }

    @Test
    public void APIUtils_instance_should_be_created_using_reflection()
            throws NoSuchMethodException, IllegalAccessException,
            InvocationTargetException, InstantiationException {
        Constructor<ApiUtils> constructor = ApiUtils.class
                .getDeclaredConstructor();
        constructor.setAccessible(true);
        ApiUtils instance = constructor.newInstance();
        Assert.assertNotNull(instance);
    }
}
