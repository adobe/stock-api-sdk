/*******************************************************************************
 * Copyright 2017 Adobe Systems Incorporated. All rights reserved.
 * This file is licensed to you under the Apache License, Version 2.0
 * (the "License") you may not use this file except in compliance with the
 * License. You may obtain a copy of the License at
 * http://www.apache.org/licenses/LICENSE-2.0
 *******************************************************************************/
package com.adobe.stock.apis;

import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.util.Map;

import org.apache.http.entity.ContentType;
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
import com.adobe.stock.enums.AssetLicenseSize;
import com.adobe.stock.enums.AssetLicenseState;
import com.adobe.stock.enums.AssetPurchaseState;
import com.adobe.stock.enums.Environment;
import com.adobe.stock.exception.StockException;
import com.adobe.stock.models.LicenseReference;
import com.adobe.stock.models.LicenseRequest;
import com.adobe.stock.models.LicenseResponse;
import com.adobe.stock.models.SearchCategoryRequest;
import com.adobe.stock.models.StockFileCategory;

@PowerMockIgnore({ "javax.management.*", "javax.xml.parsers.*",
    "com.sun.org.apache.xerces.internal.jaxp.*", "ch.qos.logback.*",
    "org.slf4j.*", "javax.net.ssl.*" })
@Listeners({ com.adobe.stock.logger.TestCustomLogger.class,
    com.adobe.stock.logger.TestSuiteLogger.class })
@PrepareForTest({ StockConfig.class, Endpoints.class, HttpUtils.class})
@Test(suiteName = "License")
public class LicenseTest {
    private static final String TEST_LICENSE_CONTENT_LICENSE = "{\"member\":{\"stock_id\":1262293},\"available_entitlement\":null,\"contents\":{\"84071201\":{\"content_id\":\"84071201\",\"size\":\"Comp\",\"purchase_details\":{\"state\":\"not_possible\",\"message\":\"\"}}}}";
    private static final String TEST_LICENSE_CONTENT_INFO = "{\"member\":{\"stock_id\":1262293},\"contents\":{\"84071201\":{\"content_id\":\"84071201\",\"size\":\"Comp\",\"purchase_details\":{\"state\":\"not_purchased\",\"stock_id\":1262293}}}}";
    private static final String TEST_LICENSE_MEMBER_PROFILE = "{ \"available_entitlement\": { \"quota\": 6, \"full_entitlement_quota\": { \"image_quota\": 6 } }, \"member\": { \"member_id\": 1222994 }, \"purchase_options\": { \"state\": \"not_possible\", \"requires_checkout\": false, \"message\": \"This will use 1 of your 6 licenses.\" } }";
    
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

    @Test(groups = "License.constructor", expectedExceptions = {
            StockException.class }, expectedExceptionsMessageRegExp = "Config can't be null")
    public void License_should_throw_stockexception_since_config_parameter_is_null()
            throws StockException {
        new License(null);
    }

    @Test(groups = "License.constructor")
    public void License_should_throw_stockexception_since_config_not_initialized_properly()
            throws StockException {
        StockConfig config = new StockConfig();
        try {
            new License(config);
        } catch (StockException e) {
            Assert.assertEquals(e.getMessage(),
                    "Api Key configuration can't be null!");
        }
        config.setApiKey("testKey");
        try {
            new License(config);
        } catch (StockException e) {
            Assert.assertEquals(e.getMessage(),
                    "Product configuration can't be null!");
        }
    }

    @Test(groups = "License.constructor")
    public void License_should_return_new_object_of_License_class() {
        try {
            License api = new License(config);
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

    @Test(groups = "License.getContentInfo", expectedExceptions = {
            StockException.class }, expectedExceptionsMessageRegExp = "Request can't be null")
    public void getContentInfo_should_throw_stockexception_since_request_is_null()
            throws StockException {
        License api = new License(config);
        api.getContentInfo(null, null);
    }

    @Test(groups = "License.getContentInfo", expectedExceptions = {
            StockException.class }, expectedExceptionsMessageRegExp = "Asset Content id must be present in the license request")
    public void getContentInfo_should_throw_stockexception_since_categoryid_not_present_request()
            throws StockException {
        License api = new License(config);
        api.getContentInfo(new LicenseRequest(), "");
    }

    @Test(groups = "License.getContentInfo", expectedExceptions = {
            StockException.class }, expectedExceptionsMessageRegExp = "Could not create the license request url")
    public void getContentInfo_should_throw_stockexception_since_url_invalid() throws Exception{

        Endpoints mockEndpoints = PowerMockito.mock(Endpoints.class);
        PowerMockito.whenNew(Endpoints.class).withArguments(Matchers.any(Environment.class)).thenReturn(mockEndpoints);
        PowerMockito.when(mockEndpoints.getLicenseContentInfoEndpoint()).thenReturn("invalid_url");
        StockConfig config = new StockConfig().setApiKey("TestApiKey")
                .setProduct("TestProduct");
        PowerMockito.verifyNew(Endpoints.class).withArguments(Matchers.any(Environment.class));
        License api = new License(config);
        api.getContentInfo(new LicenseRequest().setContentId(84071201).setLicenseState(AssetLicenseState.STANDARD),"testToken");
    }

    @Test(groups = "License.getContentInfo")
    public void getContentInfo_should_return_valid_response() {
        try {
            PowerMockito
                    .when(HttpUtils.doGet(Mockito.anyString(),
                            Matchers.<Map<String, String>> any()))
                    .thenReturn(TEST_LICENSE_CONTENT_INFO);
            License api = new License(config);
            LicenseReference ref = new LicenseReference();
            ref.setLicenseReferenceId(1).setLicenseReferenceValue("Trees");
            LicenseReference refArray[] = {ref};
            LicenseRequest request = new LicenseRequest().setContentId(84071201)
                    .setLocale("en-US").setLicenseState(AssetLicenseState.EXTENDED).setLicenseReference(refArray)
                    .setPurchaseState(AssetPurchaseState.PURCHASED);
            LicenseResponse response = api.getContentInfo(request, "testToken");
            Assert.assertTrue(response.getContents().get(0).getSize().toString().equals(AssetLicenseSize.COMPLEMENTARY.toString()));
            Assert.assertTrue(response.getContents().get(0).getPurchaseDetails().getPurchaseState().toString()
                    .toString().equals(AssetPurchaseState.NOT_PURCHASED.toString()));
        } catch (Exception e) {
            Assert.fail("Didn't expect the exception here!", e);
        }
        
    }
    @Test(groups = "License.getContentLicense")
    public void getContentLicense_should_return_valid_response() {
        try {
            PowerMockito
                    .when(HttpUtils.doGet(Mockito.anyString(),
                            Matchers.<Map<String, String>> any()))
                    .thenReturn(TEST_LICENSE_CONTENT_LICENSE);
            License api = new License(config);
            LicenseRequest request = new LicenseRequest().setContentId(84071201).setLicenseState(AssetLicenseState.EXTENDED)
                    .setLocale("en-US").setPurchaseState(AssetPurchaseState.PURCHASED);
            LicenseResponse response = api.getContentLicense(request, "testToken");
            Assert.assertTrue(response.getContents().get(0).getSize().toString().equals(AssetLicenseSize.COMPLEMENTARY.toString()));
            Assert.assertTrue(response.getContents().get(0).getPurchaseDetails().getPurchaseState().toString()
                    .toString().equals(AssetPurchaseState.NOT_POSSIBLE.toString()));
        } catch (Exception e) {
            Assert.fail("Didn't expect the exception here!", e);
        }
        
    }
    @Test(groups = "License.getContentLicense")
    public void getContentLicense_should_return_valid_response_with_post_request() {
        try {
            PowerMockito
           .when(HttpUtils.doPost(Mockito.anyString(),
                            Matchers.<Map<String, String>> any(),
                            Matchers.any(byte[].class), (ContentType)
                            Matchers.any())).thenReturn(TEST_LICENSE_CONTENT_LICENSE);
            License api = new License(config);
            LicenseReference ref = new LicenseReference();
            ref.setLicenseReferenceId(1).setLicenseReferenceValue("Trees");
            LicenseReference refArray[] = {ref};
            LicenseRequest request = new LicenseRequest().setContentId(84071201)
                    .setLocale("en-US").setLicenseState(AssetLicenseState.EXTENDED).setLicenseReference(refArray)
                    .setPurchaseState(AssetPurchaseState.PURCHASED);
            LicenseResponse response = api.getContentLicense(request, "testToken");
            Assert.assertTrue(response.getContents().get(0).getSize().toString().equals(AssetLicenseSize.COMPLEMENTARY.toString()));
            Assert.assertTrue(response.getContents().get(0).getPurchaseDetails().getPurchaseState().toString()
                    .toString().equals(AssetPurchaseState.NOT_POSSIBLE.toString()));
        } catch (Exception e) {
            Assert.fail("Didn't expect the exception here!", e);
        }
        
    }
    @Test
    public void getContentLicense_should_throw_stockexception_since_object_is_not_converted_json_properly()
            throws StockException {
        PowerMockito
        .when(HttpUtils.doPost(Mockito.anyString(),
                         Matchers.<Map<String, String>> any(),
                         Matchers.any(byte[].class), (ContentType)
                         Matchers.any())).thenReturn(TEST_LICENSE_CONTENT_LICENSE);
         License api = new License(config);
         LicenseReference ref = new LicenseReference();
         ref.setLicenseReferenceId(1).setLicenseReferenceValue("~");
         LicenseReference refArray[] = {ref};
         LicenseRequest request = new LicenseRequest().setContentId(84071201)
                 .setLocale("en-US").setLicenseState(AssetLicenseState.EXTENDED).setLicenseReference(refArray)
                 .setPurchaseState(AssetPurchaseState.PURCHASED);
         LicenseResponse response = api.getContentLicense(request, "testToken");
    }
    @Test(groups = "License.getContentLicense", expectedExceptions = {
            StockException.class }, expectedExceptionsMessageRegExp = "Request can't be null")
    public void getContentLicense_should_throw_stockexception_since_request_is_null()
            throws StockException {
        License api = new License(config);
        api.getContentLicense(null, null);
    }

    @Test(groups = "License.getContentLicense", expectedExceptions = {
            StockException.class }, expectedExceptionsMessageRegExp = "Asset Content id must be present in the license request")
    public void getContentLicense_should_throw_stockexception_since_categoryid_not_present_request()
            throws StockException {
        License api = new License(config);
        api.getContentLicense(new LicenseRequest(), "");
    }
    @Test(groups = { "Exceptions" }, expectedExceptions  = java.lang.InstantiationException.class)
    void testValidatesThatClassLicenseIsNotInstantiable() 
            throws ClassNotFoundException, InstantiationException, IllegalAccessException {
      Class<?> cls = Class.forName("com.adobe.stock.apis.License");
      cls.newInstance(); 
    }

    @Test(groups = { "Exceptions" }, expectedExceptions  = java.lang.IllegalAccessException.class)
    void testValidatesThatClassLicenseAPIHelpersIsNotInstantiable() 
            throws ClassNotFoundException, InstantiationException, IllegalAccessException {
      Class<?> cls = Class.forName("com.adobe.stock.apis.LicenseAPIHelpers");
      cls.newInstance(); 
    }


    @Test(groups = "License.getMemberProfile", expectedExceptions = {
            StockException.class }, expectedExceptionsMessageRegExp = "Request can't be null")
    public void getMemberProfile_should_throw_stockexception_since_request_is_null()
            throws StockException {
        License api = new License(config);
        api.getContentInfo(null, null);
    }

    @Test(groups = "License.getMemberProfile", expectedExceptions = {
            StockException.class }, expectedExceptionsMessageRegExp = "Asset Content id must be present in the license request")
    public void getMemberProfile_should_throw_stockexception_since_contentid_not_present_request()
            throws StockException {
        License api = new License(config);
        api.getContentInfo(new LicenseRequest(), "testToken");
    }

    @Test(groups = "License.getMemberProfile", expectedExceptions = {
            StockException.class }, expectedExceptionsMessageRegExp = "Licensing state must be present in the license request")
    public void getMemberProfile_should_throw_stockexception_since_license_state_not_present_request()
            throws StockException {
        License api = new License(config);
        api.getMemberProfile(new LicenseRequest().setContentId(84071201), "testToken");
    }

    @Test(groups = "License.getMemberProfile", expectedExceptions = {
            StockException.class }, expectedExceptionsMessageRegExp = "Access token can't be null or empty")
    public void getMemberProfile_should_throw_stockexception_since_access_token_not_present_request()
            throws StockException {
        License api = new License(config);
        api.getMemberProfile(new LicenseRequest().setContentId(84071201).setLicenseState(AssetLicenseState.STANDARD), "");
    }

    @Test(groups = "License.getMemberProfile", expectedExceptions = {
            StockException.class }, expectedExceptionsMessageRegExp = "Could not create the license request url")
    public void getMemberProfile_should_throw_stockexception_since_url_invalid() throws Exception{

        Endpoints mockEndpoints = PowerMockito.mock(Endpoints.class);
        PowerMockito.whenNew(Endpoints.class).withArguments(Matchers.any(Environment.class)).thenReturn(mockEndpoints);
        PowerMockito.when(mockEndpoints.getLicenseContentInfoEndpoint()).thenReturn("invalid_url");
        StockConfig config = new StockConfig().setApiKey("TestApiKey")
                .setProduct("TestProduct");
        PowerMockito.verifyNew(Endpoints.class).withArguments(Matchers.any(Environment.class));
        License api = new License(config);
        api.getMemberProfile(new LicenseRequest().setContentId(84071201).setLicenseState(AssetLicenseState.STANDARD),"testToken");
    }

    @Test(groups = "License.getMemberProfile")
    public void getMemberProfile_should_return_valid_response() {
        try {
            PowerMockito
                    .when(HttpUtils.doGet(Mockito.anyString(),
                            Matchers.<Map<String, String>> any()))
                    .thenReturn(TEST_LICENSE_MEMBER_PROFILE);
            License api = new License(config);
            LicenseRequest request = new LicenseRequest().setContentId(84071201).setLicenseState(AssetLicenseState.EXTENDED);
            LicenseResponse response = api.getMemberProfile(request, "testToken");
            Assert.assertEquals(response.getPurchaseOptions().getPurchaseState(), AssetPurchaseState.NOT_POSSIBLE );
            Assert.assertEquals(response.getEntitlement().getQuota().intValue(), 6);
        } catch (Exception e) {
            Assert.fail("Didn't expect the exception here!", e);
        }
        
    }
    
    @Test(groups = "License.abandonLicense", expectedExceptions = {
            StockException.class }, expectedExceptionsMessageRegExp = "Request can't be null")
    public void abandonLicense_should_throw_stockexception_since_request_is_null()
            throws StockException {
        License api = new License(config);
        api.abandonLicense(null, null);
    }

    @Test(groups = "License.abandonLicense", expectedExceptions = {
            StockException.class }, expectedExceptionsMessageRegExp = "Asset Content id must be present in the license request")
    public void abandonLicense_should_throw_stockexception_since_contentid_not_present_request()
            throws StockException {
        License api = new License(config);
        api.abandonLicense(new LicenseRequest(), "");
    }

    @Test(groups = "License.abandonLicense", expectedExceptions = {
            StockException.class }, expectedExceptionsMessageRegExp = "Could not create the license request url")
    public void abandonLicense_should_throw_stockexception_since_url_invalid() throws Exception{

        Endpoints mockEndpoints = PowerMockito.mock(Endpoints.class);
        PowerMockito.whenNew(Endpoints.class).withArguments(Matchers.any(Environment.class)).thenReturn(mockEndpoints);
        PowerMockito.when(mockEndpoints.getLicenseContentInfoEndpoint()).thenReturn("invalid_url");
        StockConfig config = new StockConfig().setApiKey("TestApiKey")
                .setProduct("TestProduct");
        PowerMockito.verifyNew(Endpoints.class).withArguments(Matchers.any(Environment.class));
        License api = new License(config);
        api.abandonLicense(new LicenseRequest().setContentId(84071201).setLicenseState(AssetLicenseState.STANDARD),"testToken");
    }

    @Test(groups = "License.abandonLicense")
    public void abandonLicense_should_not_throw_exception() {
        try {
            PowerMockito
                    .when(HttpUtils.doGet(Mockito.anyString(),
                            Matchers.<Map<String, String>> any()))
                    .thenReturn("204");
            License api = new License(config);
            LicenseRequest request = new LicenseRequest().setContentId(84071201).setLicenseState(AssetLicenseState.EXTENDED);
            api.abandonLicense(request, "testToken");
        } catch (Exception e) {
            Assert.fail("Didn't expect the exception here!", e);
        }
        
    }

    @Test(groups = "License.abandonLicense", expectedExceptions = {
            StockException.class }, expectedExceptionsMessageRegExp = "Stock API returned with an error")
    public void abandonLicense_should_throw_exception_api_send_incorrect_response() throws StockException{
        PowerMockito
                .when(HttpUtils.doGet(Mockito.anyString(),
                        Matchers.<Map<String, String>> any()))
                .thenReturn("testResponse");
        License api = new License(config);
        LicenseRequest request = new LicenseRequest().setContentId(84071201).setLicenseState(AssetLicenseState.EXTENDED);
        api.abandonLicense(request, "testToken");
    }

    @Test(groups = "License.abandonLicense", expectedExceptions = {
            StockException.class }, expectedExceptionsMessageRegExp = "Access token can't be null or empty")
    public void abandonLicense_should_throw_stockexception_since_token_not_present_request()
            throws StockException {
        License api = new License(config);
        api.abandonLicense(new LicenseRequest().setContentId(84071201).setLicenseState(AssetLicenseState.STANDARD), null);
    }

    @Test(groups = "License.downloadAsset")
    public void downloadAsset_should_return_valid_asset_url() {
        String jsonResponse = "{ \"member\": { \"member_id\": \"5PAGXppkUvXRR851OtNbz9HaODSXa7BV\" }, \"available_entitlement\": { \"quota\": 4}, \"contents\": { \"84071201\": { \"content_id\": \"84071201\", \"size\": \"Comp\", \"purchase_details\": { \"state\": \"purchased\", \"license\": \"Standard\", \"date\": \"2017-06-21 10:38:52\", \"url\": \"https://my.example.com/my/asset\", \"content_type\": \"image/jpeg\", \"width\": 4000, \"height\": 3928 } } } } }";
        try {
            PowerMockito
            .when(HttpUtils.doGet(Mockito.anyString(),
                    Matchers.<Map<String, String>> any()))
            .thenReturn(jsonResponse, jsonResponse,"url");
            License api = new License(config);
            String assetUrl = api.downloadAsset(new LicenseRequest().setContentId(84071201).setLicenseState(AssetLicenseState.STANDARD), "accessToken");
            Assert.assertEquals(assetUrl, "url");
        } catch (Exception e) {
            Assert.fail("Didn't expect the exception here!", e);
        }
    }

    @Test(groups = "License.downloadAsset", expectedExceptions = {
            StockException.class }, expectedExceptionsMessageRegExp = "Could not find the licensing information for the asset")
    public void downloadAsset_should_throw_exception_since_license_info_null() throws Exception{
        PowerMockito
        .when(HttpUtils.doGet(Mockito.anyString(),
                Matchers.<Map<String, String>> any()))
        .thenReturn("null");
        License api = new License(config);
        api.downloadAsset(new LicenseRequest().setContentId(84071201).setLicenseState(AssetLicenseState.STANDARD), "accessToken");
    }

    @Test(groups = "License.downloadAsset", expectedExceptions = {
            StockException.class }, expectedExceptionsMessageRegExp = "Could not find the purchase details for the asset")
    public void downloadAsset_should_throw_exception_since_purchase_details_null() throws Exception{
        String jsonResponse = "{ \"member\": { \"member_id\": \"5PAGXppkUvXRR851OtNbz9HaODSXa7BV\" }, \"available_entitlement\": { \"quota\": 4}, \"contents\": { \"84071201\": { \"content_id\": \"84071201\", \"size\": \"Comp\" } }, \"purchase_options\": { \"state\": \"not_possible\", \"requires_checkout\": false, \"message\": \"This will use 1 of your 6 licenses.\" } } }";
        PowerMockito
        .when(HttpUtils.doGet(Mockito.anyString(),
                Matchers.<Map<String, String>> any()))
        .thenReturn(jsonResponse);
        License api = new License(config);
        api.downloadAsset(new LicenseRequest().setContentId(84071201).setLicenseState(AssetLicenseState.STANDARD), "accessToken");
    }

    @Test(groups = "License.downloadAsset", expectedExceptions = {
            StockException.class }, expectedExceptionsMessageRegExp = "Content not licensed but have enough quota or overage plan, so first buy the license")
    public void downloadAsset_should_throw_exception_since_asset_not_purchased_but_can_be_licensed() throws Exception{
        String jsonResponse = "{ \"member\": { \"member_id\": \"5PAGXppkUvXRR851OtNbz9HaODSXa7BV\" }, \"available_entitlement\": { \"quota\": 4}, \"contents\": { \"84071201\": { \"content_id\": \"84071201\", \"size\": \"Comp\", \"purchase_details\": { \"state\": \"not_purchased\"} } }, \"purchase_options\": { \"state\": \"not_possible\", \"requires_checkout\": false, \"message\": \"This will use 1 of your 6 licenses.\" } } }";
        
        PowerMockito
        .when(HttpUtils.doGet(Mockito.anyString(),
                Matchers.<Map<String, String>> any()))
        .thenReturn(jsonResponse);
        License api = new License(config);
        api.downloadAsset(new LicenseRequest().setContentId(84071201).setLicenseState(AssetLicenseState.STANDARD), "accessToken");
    }

    @Test(groups = "License.downloadAsset", expectedExceptions = {
            StockException.class }, expectedExceptionsMessageRegExp = "Content not licensed and you do not have enough quota or overage plan")
    public void downloadAsset_should_throw_exception_since_asset_not_purchased_but_cannot_be_licensed() throws Exception{
        String jsonResponse = "{ \"member\": { \"member_id\": \"5PAGXppkUvXRR851OtNbz9HaODSXa7BV\" }, \"available_entitlement\": { \"quota\": 0}, \"contents\": { \"84071201\": { \"content_id\": \"84071201\", \"size\": \"Comp\", \"purchase_details\": { \"state\": \"not_purchased\"} } }, \"purchase_options\": { \"state\": \"not_possible\", \"requires_checkout\": false, \"message\": \"This will use 1 of your 6 licenses.\" } } }";
        
        PowerMockito
        .when(HttpUtils.doGet(Mockito.anyString(),
                Matchers.<Map<String, String>> any()))
        .thenReturn(jsonResponse);
        License api = new License(config);
        api.downloadAsset(new LicenseRequest().setContentId(84071201).setLicenseState(AssetLicenseState.STANDARD), "accessToken");
    }

    @Test(groups = "License.downloadAsset", expectedExceptions = {
            StockException.class }, expectedExceptionsMessageRegExp = "Content not licensed but have enough quota or overage plan, so first buy the license")
    public void downloadAsset_should_throw_exception_since_asset_not_purchased_but_overage_plan_present() throws Exception{
        String jsonResponse = "{ \"member\": { \"member_id\": \"5PAGXppkUvXRR851OtNbz9HaODSXa7BV\" }, \"available_entitlement\": { \"quota\": 0}, \"contents\": { \"84071201\": { \"content_id\": \"84071201\", \"size\": \"Comp\", \"purchase_details\": { \"state\": \"not_purchased\"} } }, \"purchase_options\": { \"state\": \"overage\", \"requires_checkout\": false, \"message\": \"This will use 1 of your 6 licenses.\" } } }";
        
        PowerMockito
        .when(HttpUtils.doGet(Mockito.anyString(),
                Matchers.<Map<String, String>> any()))
        .thenReturn(jsonResponse);
        License api = new License(config);
        api.downloadAsset(new LicenseRequest().setContentId(84071201).setLicenseState(AssetLicenseState.STANDARD), "accessToken");
    }

    @Test(groups = "License.downloadAsset", expectedExceptions = {
            StockException.class }, expectedExceptionsMessageRegExp = "Could not find the purchase details for the asset")
    public void downloadAsset_should_throw_exception_since_asset_url_not_present() throws Exception{
        String jsonResponse = "{ \"member\": { \"member_id\": \"5PAGXppkUvXRR851OtNbz9HaODSXa7BV\" }, \"available_entitlement\": { \"quota\": 4}, \"contents\": { \"84071201\": { \"content_id\": \"84071201\", \"size\": \"Comp\", \"purchase_details\": { \"state\": \"purchased\", \"license\": \"Standard\", \"date\": \"2017-06-21 10:38:52\", \"content_type\": \"image/jpeg\", \"width\": 4000, \"height\": 3928 } } } } }";
        
        PowerMockito
        .when(HttpUtils.doGet(Mockito.anyString(),
                Matchers.<Map<String, String>> any()))
        .thenReturn(jsonResponse);
        License api = new License(config);
        api.downloadAsset(new LicenseRequest().setContentId(84071201).setLicenseState(AssetLicenseState.STANDARD), "accessToken");
    }

    @Test(groups = "License.downloadAsset", expectedExceptions = {
            StockException.class }, expectedExceptionsMessageRegExp = "Asset URL returned from Stock API is not valid")
    public void downloadAsset_should_throw_exception_since_asset_url_not_valid() throws Exception{
        String jsonResponse = "{ \"member\": { \"member_id\": \"5PAGXppkUvXRR851OtNbz9HaODSXa7BV\" }, \"available_entitlement\": { \"quota\": 4}, \"contents\": { \"84071201\": { \"content_id\": \"84071201\", \"size\": \"Comp\", \"purchase_details\": { \"state\": \"purchased\", \"license\": \"Standard\", \"date\": \"2017-06-21 10:38:52\", \"url\": \"my.example.com/my/asset\", \"content_type\": \"image/jpeg\", \"width\": 4000, \"height\": 3928 } } } } }";

        PowerMockito
        .when(HttpUtils.doGet(Mockito.anyString(),
                Matchers.<Map<String, String>> any()))
        .thenReturn(jsonResponse);
        License api = new License(config);
        api.downloadAsset(new LicenseRequest().setContentId(84071201).setLicenseState(AssetLicenseState.STANDARD), "accessToken");
    }

    @Test(groups = "License.downloadAsset", expectedExceptions = {
            StockException.class }, expectedExceptionsMessageRegExp = "Could not find the available licenses for the user")
    public void downloadAsset_should_throw_exception_since_entitlement_is_not_present() throws Exception{
        String jsonResponse = "{ \"member\": { \"member_id\": \"5PAGXppkUvXRR851OtNbz9HaODSXa7BV\" }, \"contents\": { \"84071201\": { \"content_id\": \"84071201\", \"size\": \"Comp\", \"purchase_details\": { \"state\": \"not_purchased\"} } }, \"purchase_options\": { \"state\": \"not_possible\", \"requires_checkout\": false, \"message\": \"This will use 1 of your 6 licenses.\" } } }";
        
        PowerMockito
        .when(HttpUtils.doGet(Mockito.anyString(),
                Matchers.<Map<String, String>> any()))
        .thenReturn(jsonResponse);
        License api = new License(config);
        api.downloadAsset(new LicenseRequest().setContentId(84071201).setLicenseState(AssetLicenseState.STANDARD), "accessToken");
    }

    @Test(groups = "License.downloadAsset", expectedExceptions = {
            StockException.class }, expectedExceptionsMessageRegExp = "Could not find the user purchasing options for the asset")
    public void downloadAsset_should_throw_exception_since_purchasing_options_is_not_present() throws Exception{
        String jsonResponse = "{ \"member\": { \"member_id\": \"5PAGXppkUvXRR851OtNbz9HaODSXa7BV\" }, \"available_entitlement\": { \"quota\": 4}, \"contents\": { \"84071201\": { \"content_id\": \"84071201\", \"size\": \"Comp\", \"purchase_details\": { \"state\": \"not_purchased\"} } } } }";
        
        PowerMockito
        .when(HttpUtils.doGet(Mockito.anyString(),
                Matchers.<Map<String, String>> any()))
        .thenReturn(jsonResponse);
        License api = new License(config);
        api.downloadAsset(new LicenseRequest().setContentId(84071201).setLicenseState(AssetLicenseState.STANDARD), "accessToken");
    }

    @Test(groups = "License.downloadAsset", expectedExceptions = {
            StockException.class }, expectedExceptionsMessageRegExp = "Could not find the purchase details for the asset")
    public void downloadAsset_should_throw_exception_since_purchasing_state_is_not_present() throws Exception{
        String jsonResponse = "{ \"member\": { \"member_id\": \"5PAGXppkUvXRR851OtNbz9HaODSXa7BV\" }, \"available_entitlement\": { \"quota\": 4}, \"contents\": { \"84071201\": { \"content_id\": \"84071201\", \"size\": \"Comp\", \"purchase_details\": { } } } } }";
        
        PowerMockito
        .when(HttpUtils.doGet(Mockito.anyString(),
                Matchers.<Map<String, String>> any()))
        .thenReturn(jsonResponse);
        License api = new License(config);
        api.downloadAsset(new LicenseRequest().setContentId(84071201).setLicenseState(AssetLicenseState.STANDARD), "accessToken");
    }

    @Test
    public void LicenseAPIHelpers_instance_should_be_created_using_reflection()
            throws NoSuchMethodException, IllegalAccessException,
            InvocationTargetException, InstantiationException {
        Constructor<LicenseAPIHelpers> constructor = LicenseAPIHelpers.class
                .getDeclaredConstructor();
        constructor.setAccessible(true);
        LicenseAPIHelpers instance = constructor.newInstance();
        Assert.assertNotNull(instance);
    }
}
