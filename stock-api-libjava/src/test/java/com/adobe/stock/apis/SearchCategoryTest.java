/*******************************************************************************
 * Copyright 2017 Adobe Systems Incorporated. All rights reserved.
 * This file is licensed to you under the Apache License, Version 2.0
 * (the "License") you may not use this file except in compliance with the
 * License. You may obtain a copy of the License at
 * http://www.apache.org/licenses/LICENSE-2.0
 *******************************************************************************/
package com.adobe.stock.apis;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.Map;

import org.mockito.Matchers;
import org.mockito.Mockito;
import org.powermock.api.mockito.PowerMockito;
import org.powermock.core.classloader.annotations.PowerMockIgnore;
import org.powermock.core.classloader.annotations.PrepareForTest;
import org.powermock.modules.testng.PowerMockObjectFactory;
import org.testng.Assert;
import org.testng.IObjectFactory;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Listeners;
import org.testng.annotations.ObjectFactory;
import org.testng.annotations.Test;

import com.adobe.stock.config.Endpoints;
import com.adobe.stock.config.StockConfig;
import com.adobe.stock.enums.Environment;
import com.adobe.stock.exception.StockException;
import com.adobe.stock.models.SearchCategoryRequest;
import com.adobe.stock.models.StockFileCategory;

@PowerMockIgnore({ "javax.management.*", "javax.xml.parsers.*",
        "com.sun.org.apache.xerces.internal.jaxp.*", "ch.qos.logback.*",
        "org.slf4j.*", "javax.net.ssl.*" })
@Listeners({ com.adobe.stock.logger.TestCustomLogger.class,
        com.adobe.stock.logger.TestSuiteLogger.class })
@PrepareForTest({ StockConfig.class, Endpoints.class, HttpUtils.class})
@Test(suiteName = "SearchCategory")
public class SearchCategoryTest {
    private static final String TEST_CATEGORY_RESPONSE = "{\"id\":1043,\"link\":\"/Category/travel/1043\",\"name\":\"Travel\"}";
    private static final String TEST_CATEGORY_TREE_RESPONSE = "[{\"id\":1013,\"link\":\"/Category/transport/1013?load_type=category\",\"name\":\"Transport\"},{\"id\":1043,\"link\":\"/Category/travel/1043?load_type=category\",\"name\":\"Travel\"}]";
    private StockConfig config;

    @ObjectFactory
    public IObjectFactory getObjectFactory() {
        return new PowerMockObjectFactory();
    }

    @BeforeMethod
    public void beforeEachMethod() {
        try {
            PowerMockito.mockStatic(HttpUtils.class);
            config = new StockConfig().setApiKey("TestApiKey")
                    .setProduct("TestProduct");
        } catch (Exception e) {
            Assert.fail("Didn't expect the Exception!", e);
        }
    }

    @Test(groups = "SearchCategory.constructor", expectedExceptions = {
            StockException.class }, expectedExceptionsMessageRegExp = "Config can't be null")
    public void SearchCategory_should_throw_stockexception_since_config_parameter_is_null()
            throws StockException {
        new SearchCategory(null);
    }

    @Test(groups = "SearchCategory.constructor")
    public void SearchCategory_should_throw_stockexception_since_config_not_initialized_properly()
            throws StockException {
        StockConfig config = new StockConfig();
        try {
            new SearchCategory(config);
        } catch (StockException e) {
            Assert.assertEquals(e.getMessage(),
                    "Api Key configuration can't be null!");
        }
        config.setApiKey("testKey");
        try {
            new SearchCategory(config);
        } catch (StockException e) {
            Assert.assertEquals(e.getMessage(),
                    "Product configuration can't be null!");
        }
    }

    @Test(groups = "SearchCategory.constructor")
    public void SearchCategory_should_return_new_object_of_SearchCategory_class() {
        try {
            SearchCategory api = new SearchCategory(config);
            Assert.assertNotNull(api);
            Field fConfig = api.getClass().getDeclaredField("mConfig");
            fConfig.setAccessible(true);
            StockConfig mConfig = (StockConfig) fConfig.get(api);
            Assert.assertNotNull(mConfig);
            Assert.assertEquals(mConfig.getApiKey(), config.getApiKey());
            Assert.assertEquals(mConfig.getProduct(), config.getProduct());
            Assert.assertEquals(mConfig.getTargetEnvironment(),
                    config.getTargetEnvironment());
        } catch (Exception e) {
            Assert.fail("Didn't expect the exception here!", e);
        }
    }

    @Test(groups = "SearchCategory.getCategory", expectedExceptions = {
            StockException.class }, expectedExceptionsMessageRegExp = "Request can't be null")
    public void getCategory_should_throw_stockexception_since_request_is_null()
            throws StockException {
        SearchCategory api = new SearchCategory(config);
        api.getCategory(null);
    }

    @Test(groups = "SearchCategory.getCategory", expectedExceptions = {
            StockException.class }, expectedExceptionsMessageRegExp = "Category id must be present in the search request")
    public void getCategory_should_throw_stockexception_since_categoryid_not_present_request()
            throws StockException {
        SearchCategory api = new SearchCategory(config);
        api.getCategory(new SearchCategoryRequest());
    }

    @Test(groups = "SearchCategory.getCategory", expectedExceptions = {
            StockException.class }, expectedExceptionsMessageRegExp = "Could not create the search request url")
    public void getCategory_should_throw_stockexception_since_url_invalid() throws Exception{

        Endpoints mockEndpoints = PowerMockito.mock(Endpoints.class);
        PowerMockito.whenNew(Endpoints.class).withArguments(Matchers.any(Environment.class)).thenReturn(mockEndpoints);
        PowerMockito.when(mockEndpoints.getSearchCategoryEndpoint()).thenReturn("invalid_url");
        StockConfig config = new StockConfig().setApiKey("TestApiKey")
                .setProduct("TestProduct");
        PowerMockito.verifyNew(Endpoints.class).withArguments(Matchers.any(Environment.class));
        SearchCategory api = new SearchCategory(config);
        api.getCategory(new SearchCategoryRequest().setCategoryId(1043));
    }

    @Test(groups = "SearchCategory.getCategory")
    public void getCategory_should_return_valid_response() {
        try {
            PowerMockito
                    .when(HttpUtils.doGet(Mockito.anyString(),
                            Matchers.<Map<String, String>> any()))
                    .thenReturn(TEST_CATEGORY_RESPONSE);
            SearchCategory api = new SearchCategory(config);
            SearchCategoryRequest request = new SearchCategoryRequest()
                    .setCategoryId(1043).setLocale("en-US");
            StockFileCategory category = api.getCategory(request);
            Assert.assertEquals(category.getName(), "Travel");
            Assert.assertEquals(category.getId().intValue(), 1043);
            Assert.assertEquals(category.getLink(), "/Category/travel/1043");
        } catch (Exception e) {
            Assert.fail("Didn't expect the exception here!", e);
        }
    }

    @Test(groups = "SearchCategory.getCategory", expectedExceptions = {
            StockException.class }, expectedExceptionsMessageRegExp = "API returned with Server Error")
    public void getCategory_should_throw_stockexception_since_api_returns_with_error()
            throws StockException {
        PowerMockito
                .when(HttpUtils.doGet(Mockito.anyString(),
                        Matchers.<Map<String, String>> any()))
                .thenThrow(
                        new StockException("API returned with Server Error"));
        SearchCategory api = new SearchCategory(config);
        SearchCategoryRequest request = new SearchCategoryRequest()
                .setCategoryId(1043);
        api.getCategory(request);
    }

    @Test(groups = "SearchCategoryTree.getCategoryTree", expectedExceptions = {
            StockException.class }, expectedExceptionsMessageRegExp = "Request can't be null")
    public void getCategoryTree_should_throw_stockexception_since_request_is_null()
            throws StockException {
        SearchCategory api = new SearchCategory(config);
        api.getCategoryTree(null);
    }

    @Test(groups = "SearchCategory.getCategoryTree")
    public void getCategoryTree_should_return_valid_response() {
        try {
            PowerMockito
                    .when(HttpUtils.doGet(Mockito.anyString(),
                            Matchers.<Map<String, String>> any()))
                    .thenReturn(TEST_CATEGORY_TREE_RESPONSE);
            SearchCategory api = new SearchCategory(config);
            ArrayList<StockFileCategory> categoryList = api
                    .getCategoryTree(new SearchCategoryRequest());
            Assert.assertEquals(categoryList.get(0).getName(), "Transport");
            Assert.assertEquals(categoryList.get(0).getId().intValue(), 1013);
            Assert.assertEquals(categoryList.get(0).getLink(),
                    "/Category/transport/1013?load_type=category");
        } catch (Exception e) {
            Assert.fail("Didn't expect the exception here!", e);
        }
    }

    @Test(groups = "SearchCategory.getCategoryTree", expectedExceptions = {
            StockException.class }, expectedExceptionsMessageRegExp = "API returned with Server Error")
    public void getCategoryTree_should_throw_stockexception_since_api_returns_with_error()
            throws StockException {
        PowerMockito
                .when(HttpUtils.doGet(Mockito.anyString(),
                        Matchers.<Map<String, String>> any()))
                .thenThrow(
                        new StockException("API returned with Server Error"));
        SearchCategory api = new SearchCategory(config);
        api.getCategoryTree(new SearchCategoryRequest());
    }
}
