/*******************************************************************************
 * Copyright 2017 Adobe Systems Incorporated. All rights reserved.
 * This file is licensed to you under the Apache License, Version 2.0
 * (the "License") you may not use this file except in compliance with the
 * License. You may obtain a copy of the License at
 * http://www.apache.org/licenses/LICENSE-2.0
 ******************************************************************************/
package com.adobe.stock.apis;

import java.lang.reflect.Field;
import java.util.Arrays;
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

import com.adobe.stock.config.StockConfig;
import com.adobe.stock.enums.LicenseHistoryResultColumn;
import com.adobe.stock.enums.LicenseHistoryThumbnailSize;
import com.adobe.stock.exception.StockException;
import com.adobe.stock.models.LicenseHistoryRequest;
import com.adobe.stock.models.LicenseHistoryResponse;
import com.adobe.stock.models.SearchParametersLicenseHistory;

@PowerMockIgnore({ "javax.management.*", "javax.xml.parsers.*",
    "com.sun.org.apache.xerces.internal.jaxp.*", "ch.qos.logback.*",
    "org.slf4j.*", "javax.net.ssl.*" })
@Listeners({ com.adobe.stock.logger.TestCustomLogger.class,
    com.adobe.stock.logger.TestSuiteLogger.class })
@PrepareForTest({ HttpUtils.class })
@Test(suiteName = "LicenseHistory")
public class LicenseHistoryTest {

    private static final String TEST_RESPONSE = "{\"nb_results\":200,\"files\":[{\"id\":148563830,\"title\":\"Red, white, and blue American flag for Memorial day or Veteran\'s day background\"}]}";
    private static final Integer TEST_NB_RESULTS = 200,
            TEST_FILE_ID = 148563830, TEST_DEFAULT_LIMIT = 100,
            TEST_TOTAL_PAGES = (TEST_NB_RESULTS / TEST_DEFAULT_LIMIT);
    private static final String TEST_FILE_TITLE = "Red, white, and blue American flag for Memorial day or Veteran\'s day background";
    private LicenseHistoryRequest licenseHistoryRequest;
    private StockConfig config;
    
    @ObjectFactory
    public IObjectFactory getObjectFactory() {
        return new PowerMockObjectFactory();
    }
    
    @Test(groups = "LicenseHistory.constructor")
    public void LicenseHistory_should_return_new_object_of_LicenseHistory_class() {
        try {
            LicenseHistoryResultColumn[] resultColumns = { LicenseHistoryResultColumn.THUMBNAIL_1000_HEIGHT,
                    LicenseHistoryResultColumn.THUMBNAIL_1000_URL };
            licenseHistoryRequest.setResultColumns(resultColumns);
            LicenseHistory api = new LicenseHistory(config, "abc", licenseHistoryRequest);
            Assert.assertNotNull(api);
            Field fConfig = LicenseHistory.class.getDeclaredField("mConfig");
            fConfig.setAccessible(true);
            StockConfig mConfig = (StockConfig) fConfig.get(api);
            Assert.assertEquals(mConfig.getApiKey(), config.getApiKey());
            Assert.assertEquals(mConfig.getProduct(), config.getProduct());
            Assert.assertEquals(mConfig.getTargetEnvironment(),
                    config.getTargetEnvironment());

            Field fRequest = LicenseHistory.class.getDeclaredField("mRequest");
            fRequest.setAccessible(true);
            LicenseHistoryRequest mRequest = (LicenseHistoryRequest) fRequest
                    .get(api);
            Assert.assertNotNull(mRequest);
            Assert.assertNotNull(mRequest.getSearchParams());
            Assert.assertEquals(mRequest.getLocale(), "");

            Assert.assertEquals(mRequest.getResultColumns().length,
                    licenseHistoryRequest.getResultColumns().length);
            Assert.assertTrue(Arrays.equals(mRequest.getResultColumns(),
                    licenseHistoryRequest.getResultColumns()));

            Assert.assertEquals(mRequest.getSearchParams().getOffset(),
                    licenseHistoryRequest.getSearchParams().getOffset());
            Assert.assertEquals(mRequest.getSearchParams().getThumbnailSize(),
                    licenseHistoryRequest.getSearchParams().getThumbnailSize());
            

        } catch (Exception e) {
            Assert.fail("Didn't expect the exception here!", e);
        }
    }
    
    @Test(groups = "LicenseHistory.constructor")
    public void LicenseHistory_should_throw_stockexception_since_config_parameter_is_null() {
        try {
            new LicenseHistory(null, "", licenseHistoryRequest);
            Assert.fail("StockException was expected since the config parameter in null!");
        } catch (StockException e) {
            Assert.assertEquals(e.getMessage(), "config can't be null");
        }
    }

    @Test(groups = "LicenseHistory.constructor")
    public void LicenseHistory_should_throw_stockexception_since_licenseHistoryRequest_parameter_is_null() {
        try {
            new LicenseHistory(config, "", null);
            Assert.fail("StockException was expected since the search files request parameter in null!");
        } catch (StockException e) {
            Assert.assertEquals(e.getMessage(), "request can't be null");
        }
    }

    @Test(groups = "LicenseHistory.constructor")
    public void LicenseHistory_should_throw_stockexception_since_searchparameters_field_of_request_parameter_is_null() {
        try {
            new LicenseHistory(config, "",
                    new LicenseHistoryRequest().setLocale("en-US"));
            Assert.fail("StockException was expected since the search_parameters is null in request parameter!");
        } catch (StockException e) {
            Assert.assertEquals(e.getMessage(),
                    "Search parameter must be present in the request object");
        }
    }

    @BeforeMethod
    public void beforeEachMethod() {
        try {
            PowerMockito.mockStatic(HttpUtils.class);
            LicenseHistoryResultColumn[] resultColumns = { LicenseHistoryResultColumn.THUMBNAIL_1000_HEIGHT,
                    LicenseHistoryResultColumn.THUMBNAIL_1000_URL };
            config = new StockConfig().setApiKey("TestApiKey").setProduct(
                    "TestProduct");
            SearchParametersLicenseHistory params =
                    new SearchParametersLicenseHistory().setOffset(0);
            licenseHistoryRequest = new LicenseHistoryRequest().setSearchParams(params)
                    .setResultColumns(resultColumns);

        } catch (Exception e) {
            Assert.fail("Didn't expect the Exception!", e);
        }
    }
    
    private static void checkTestResponse(LicenseHistoryResponse response) {
        Assert.assertNotNull(response);
        Assert.assertEquals(response.getNbResults(), TEST_NB_RESULTS);
        Assert.assertEquals(response.getFiles().get(0).getId(), TEST_FILE_ID);
        Assert.assertEquals(response.getFiles().get(0).getTitle(),
                TEST_FILE_TITLE);
    }
    
    @Test(groups = "LicenseHistory.getNextLicenseHistory")
    public void getNextLicenseHistory_should_return_valid_response() {
        try {

            LicenseHistory licenseHistory = new LicenseHistory(config, null,
                    licenseHistoryRequest);

            PowerMockito.when(
                    HttpUtils.doGet(Mockito.anyString(),
                            Matchers.<Map<String, String>> any())).thenReturn(
                    TEST_RESPONSE);

            LicenseHistoryResponse response = licenseHistory.getNextLicenseHistory();
            LicenseHistoryTest.checkTestResponse(response);
            Assert.assertEquals(licenseHistory.currentLicenseHistoryPageIndex(), 0);
        } catch (StockException e) {
            Assert.fail("Didn't expect the StockException!", e);
        }
    }

    @Test(groups = "LicenseHistory.getNextLicenseHistory", expectedExceptions = { StockException.class }, expectedExceptionsMessageRegExp = "No more results available!")
    public void getNextLicenseHistory_should_throw_exception_since_offset_exceed_result_count()
            throws StockException {

        licenseHistoryRequest.getSearchParams().setOffset(100).setLimit(100);
        LicenseHistory licenseHistory = new LicenseHistory(config, null, licenseHistoryRequest);

        PowerMockito.when(
                HttpUtils.doGet(Mockito.anyString(),
                        Matchers.<Map<String, String>> any())).thenReturn(
                TEST_RESPONSE);
        licenseHistory.getNextLicenseHistory();
        // calling next again will exceed results count limit
        licenseHistory.getNextLicenseHistory();
    }

    @Test(groups = "LicenseHistory.getNextLicenseHistory")
    public void getNextLicenseHistory_should_revert_to_last_request_state_when_search_api_fails() {
        try {
            licenseHistoryRequest.getSearchParams().setLimit(10);
            LicenseHistory licenseHistory = new LicenseHistory(config, null,
                    licenseHistoryRequest);

            PowerMockito.when(
                    HttpUtils.doGet(Mockito.anyString(),
                            Matchers.<Map<String, String>> any())).thenReturn(
                    TEST_RESPONSE);

            LicenseHistoryResponse response = licenseHistory.getNextLicenseHistory();
            LicenseHistoryTest.checkTestResponse(response);
            Assert.assertEquals(licenseHistory.currentLicenseHistoryPageIndex(), 0);

            PowerMockito.when(
                    HttpUtils.doGet(Mockito.anyString(),
                            Matchers.<Map<String, String>> any())).thenThrow(
                    new StockException("API returned with Server Error"));
            try {
                response = licenseHistory.getNextLicenseHistory();
                Assert.fail("Should have thrown StockException");
            } catch (StockException e) {
                Assert.assertEquals(licenseHistory.currentLicenseHistoryPageIndex(), 0);
                LicenseHistoryTest
                        .checkTestResponse(licenseHistory.getLastLicenseHistory());
            }
            PowerMockito.mockStatic(HttpUtils.class);
            PowerMockito.when(
                    HttpUtils.doGet(Mockito.anyString(),
                            Matchers.<Map<String, String>> any())).thenReturn(
                    TEST_RESPONSE);
            response = licenseHistory.getNextLicenseHistory();
            LicenseHistoryTest.checkTestResponse(response);
            Assert.assertEquals(licenseHistory.currentLicenseHistoryPageIndex(), 1);

        } catch (StockException e) {
            Assert.fail("Didn't expect the StockException!", e);
        }
    }

    @Test(groups = "LicenseHistory.getNextLicenseHistory", expectedExceptions = { StockException.class }, expectedExceptionsMessageRegExp = "No more results available!")
    public void getNextLicenseHistory_should_throw_exception_when_result_count_zero()
            throws StockException {
        String responseString = "{\"nb_results\":0,\"files\":[] }";

        licenseHistoryRequest.getSearchParams().setLimit(2);
        licenseHistoryRequest.setLocale("en-US");
        LicenseHistory licenseHistory = new LicenseHistory(config, "accessToken",
                licenseHistoryRequest);

        PowerMockito.when(
                HttpUtils.doGet(Mockito.anyString(),
                        Matchers.<Map<String, String>> any())).thenReturn(
                                responseString);

        licenseHistory.getNextLicenseHistory();
        licenseHistory.getNextLicenseHistory();
    }

    @Test(groups = "LicenseHistory.getNextLicenseHistory", expectedExceptions = { StockException.class }, expectedExceptionsMessageRegExp = "No more results available!")
    public void getNextLicenseHistory_should_throw_exception_when_results_count_not_present_response()
            throws StockException {
        String responseString = "{}";

        licenseHistoryRequest.getSearchParams().setLimit(2);
        LicenseHistory licenseHistory = new LicenseHistory(config, "accessToken",
                licenseHistoryRequest);

        PowerMockito.when(
                HttpUtils.doGet(Mockito.anyString(),
                        Matchers.<Map<String, String>> any())).thenReturn(
                                responseString);

        LicenseHistoryResponse response = licenseHistory.getNextLicenseHistory();
        Assert.assertEquals(licenseHistory.getTotalLicenseHistoryFiles(),
                LicenseHistory.LICENSE_HISTORY_RETURN_ERROR);
        Assert.assertEquals(licenseHistory.getTotalLicenseHistoryPages(),
                LicenseHistory.LICENSE_HISTORY_RETURN_ERROR);
        Assert.assertEquals(licenseHistory.currentLicenseHistoryPageIndex(),
                LicenseHistory.LICENSE_HISTORY_RETURN_ERROR);
        Assert.assertNull(response.getNbResults());
        licenseHistory.getNextLicenseHistory();
    }
    
    @Test(groups = "LicenseHistory.getPreviousLicenseHistory", expectedExceptions = { StockException.class }, expectedExceptionsMessageRegExp = "No more results available!")
    public void getPreviousLicenseHistory_should_throw_exception_when_called_from_first_page()
            throws StockException {
        LicenseHistory licenseHistory = new LicenseHistory(config, null, licenseHistoryRequest);

        PowerMockito.when(
                HttpUtils.doGet(Mockito.anyString(),
                        Matchers.<Map<String, String>> any())).thenReturn(
                TEST_RESPONSE);

        try {
            licenseHistory.getPreviousLicenseHistory();
            Assert.fail("Should have thrown StockException");
        } catch (StockException e) {
            Assert.assertEquals(e.getMessage(),
                    "No more results available!");
        }
        licenseHistory.getNextLicenseHistory();
        // calling previous again from first page
        licenseHistory.getPreviousLicenseHistory();
    }

    @Test(groups = "LicenseHistory.getPreviousLicenseHistory")
    public void getPreviousLicenseHistory_should_return_valid_response() {
        try {
            licenseHistoryRequest.getSearchParams().setOffset(TEST_DEFAULT_LIMIT);
            LicenseHistory licenseHistory = new LicenseHistory(config, null,
                    licenseHistoryRequest);

            PowerMockito.when(
                    HttpUtils.doGet(Mockito.anyString(),
                            Matchers.<Map<String, String>> any())).thenReturn(
                    TEST_RESPONSE);

            LicenseHistoryResponse response = licenseHistory.getPreviousLicenseHistory();

            LicenseHistoryTest.checkTestResponse(response);
            Assert.assertEquals(licenseHistory.currentLicenseHistoryPageIndex(), 0);
        } catch (StockException e) {
            Assert.fail("Didn't expect the StockException!", e);
        }
    }

    @Test(groups = "LicenseHistory.getLicenseHistoryPage", expectedExceptions = { StockException.class }, expectedExceptionsMessageRegExp = "Page index out of bounds")
    public void getLicenseHistoryPage_should_throw_exception_when_invalid_pageindex()
            throws StockException {
        LicenseHistory licenseHistory = new LicenseHistory(config, null, licenseHistoryRequest);

        PowerMockito.when(
                HttpUtils.doGet(Mockito.anyString(),
                        Matchers.<Map<String, String>> any())).thenReturn(
                TEST_RESPONSE);
        int pageIndex = -1;
        licenseHistory.getNextLicenseHistory();
        try {
            licenseHistory.getLicenseHistoryPage(pageIndex);
            Assert.fail("Should have thrown StockException");
        } catch (StockException e) {
            Assert.assertEquals("Page index out of bounds", e.getMessage());
        }
        licenseHistory.getLicenseHistoryPage(TEST_TOTAL_PAGES);
    }

    @Test(groups = "LicenseHistory.getLicenseHistoryPage")
    public void getLicenseHistoryPage_should_return_valid_response() {
        try {
            licenseHistoryRequest.getSearchParams().setThumbnailSize(LicenseHistoryThumbnailSize.BIG);
            LicenseHistory licenseHistory = new LicenseHistory(config, null,
                    licenseHistoryRequest);

            PowerMockito.when(
                    HttpUtils.doGet(Mockito.anyString(),
                            Matchers.<Map<String, String>> any())).thenReturn(
                    TEST_RESPONSE);

            int pageIndex = (int) (Math.random() * TEST_TOTAL_PAGES);
            LicenseHistoryResponse response = licenseHistory
                    .getLicenseHistoryPage(pageIndex);

            LicenseHistoryTest.checkTestResponse(response);
            Assert.assertEquals(licenseHistory.currentLicenseHistoryPageIndex(), pageIndex);
            LicenseHistoryTest.checkTestResponse(licenseHistory.getLastLicenseHistory());
        } catch (StockException e) {
            Assert.fail("Didn't expect the StockException!", e);
        }
    }

    @Test(groups = "LicenseHistory.getLicenseHistoryPage")
    public void getLicenseHistoryPage_should_revert_to_last_request_state_when_search_api_fails() {
        try {
            int pageIndex = (int) (Math.random() * TEST_TOTAL_PAGES);
            int previousPageIndex = pageIndex;
            LicenseHistory licenseHistory = new LicenseHistory(config, null,
                    licenseHistoryRequest);

            PowerMockito.when(
                    HttpUtils.doGet(Mockito.anyString(),
                            Matchers.<Map<String, String>> any())).thenReturn(
                    TEST_RESPONSE);

            LicenseHistoryResponse response = licenseHistory
                    .getLicenseHistoryPage(pageIndex);
            Assert.assertEquals(licenseHistory.currentLicenseHistoryPageIndex(), pageIndex);

            PowerMockito.when(
                    HttpUtils.doGet(Mockito.anyString(),
                            Matchers.<Map<String, String>> any())).thenThrow(
                    new StockException("API returned with Server Error"));
            pageIndex = (int) (Math.random() * TEST_TOTAL_PAGES);
            try {
                response = licenseHistory.getLicenseHistoryPage(pageIndex);
                Assert.fail("Should have thrown StockException");
            } catch (StockException e) {
                LicenseHistoryTest.checkTestResponse(response);
                Assert.assertEquals(licenseHistory.currentLicenseHistoryPageIndex(),
                        previousPageIndex);
            }

        } catch (StockException e) {
            Assert.fail("Didn't expect the StockException!", e);
        }
    }

    @Test(groups = "LicenseHistory.getLastLicenseHistory")
    public void getLastLicenseHistory_should_return_null_response_without_api_call() {
        try {
            LicenseHistory licenseHistory = new LicenseHistory(config, null,
                    licenseHistoryRequest);
            Assert.assertNull(licenseHistory.getLastLicenseHistory());
        } catch (StockException e) {
            Assert.fail("Didn't expect the StockException!", e);
        }
    }

    @Test(groups = "LicenseHistory.currentLicenseHistoryPageIndex")
    public void currentLicenseHistoryPageIndex_should_return_error_when_called_without_api_call() {
        try {
            LicenseHistory licenseHistory = new LicenseHistory(config, null,
                    licenseHistoryRequest);
            Assert.assertEquals(licenseHistory.currentLicenseHistoryPageIndex(),
                    LicenseHistory.LICENSE_HISTORY_RETURN_ERROR);
        } catch (StockException e) {
            Assert.fail("Didn't expect the StockException!", e);
        }
    }

    @Test(groups = "LicenseHistory.getTotalLicenseHistoryFiles")
    public void getTotalLicenseHistoryFiles_should_return_error_when_called_without_api_call() {
        try {
            LicenseHistory licenseHistory = new LicenseHistory(config, null,
                    licenseHistoryRequest);
            Assert.assertEquals(licenseHistory.getTotalLicenseHistoryFiles(),
                    LicenseHistory.LICENSE_HISTORY_RETURN_ERROR);
        } catch (StockException e) {
            Assert.fail("Didn't expect the StockException!", e);
        }
    }

    @Test(groups = "LicenseHistory.getTotalLicenseHistoryFiles")
    public void getTotalLicenseHistoryFiles_should_return_total_files() {
        try {
            LicenseHistoryRequest licenseHistoryRequest = new LicenseHistoryRequest()
                    .setSearchParams(new SearchParametersLicenseHistory());
            LicenseHistory licenseHistory = new LicenseHistory(config, null,
                    licenseHistoryRequest);

            PowerMockito.when(
                    HttpUtils.doGet(Mockito.anyString(),
                            Matchers.<Map<String, String>> any())).thenReturn(
                    TEST_RESPONSE);

            licenseHistory.getNextLicenseHistory();
            Assert.assertEquals(licenseHistory.getTotalLicenseHistoryFiles(),
                    TEST_NB_RESULTS.intValue());
        } catch (StockException e) {
            Assert.fail("Didn't expect the StockException!", e);
        }
    }
}
