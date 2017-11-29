/*******************************************************************************
 * Copyright 2017 Adobe Systems Incorporated. All rights reserved.
 * This file is licensed to you under the Apache License, Version 2.0
 * (the "License") you may not use this file except in compliance with the
 * License. You may obtain a copy of the License at
 * http://www.apache.org/licenses/LICENSE-2.0
 *******************************************************************************/
package com.adobe.stock.apis;

import java.io.IOException;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.HashMap;

import org.apache.http.StatusLine;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.entity.ContentType;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.mockito.Mockito;
import org.powermock.api.mockito.PowerMockito;
import org.powermock.core.classloader.annotations.PowerMockIgnore;
import org.powermock.core.classloader.annotations.PrepareForTest;
import org.powermock.modules.testng.PowerMockObjectFactory;
import org.testng.Assert;
import org.testng.IObjectFactory;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Listeners;
import org.testng.annotations.ObjectFactory;
import org.testng.annotations.Test;

import com.adobe.stock.apis.HttpUtils;
import com.adobe.stock.exception.StockException;

@PowerMockIgnore({ "javax.management.*", "javax.xml.parsers.*",
        "com.sun.org.apache.xerces.internal.jaxp.*", "ch.qos.logback.*",
        "org.slf4j.*", "javax.net.ssl.*" })
@Listeners({ com.adobe.stock.logger.TestCustomLogger.class,
        com.adobe.stock.logger.TestSuiteLogger.class })
@PrepareForTest(HttpUtils.class)
@Test(suiteName = "HttpUtils")
public class HttpUtilsTest {

    private CloseableHttpClient mockHttpClient;

    @ObjectFactory
    public IObjectFactory getObjectFactory() {
        return new PowerMockObjectFactory();
    }

    @BeforeClass
    public void initializeHttpClient() {
        try {
            mockHttpClient = PowerMockito.mock(CloseableHttpClient.class);
        } catch (Exception e) {
            Assert.fail("Fail to mock the CloseableHttpClient!", e);
        }
    }

    @Test(groups = "HttpsUtils.initialize")
    public void initialize_should_return_httpClient_object() {
        Method method = null;
        try {
            method = HttpUtils.class.getDeclaredMethod("initialize");
            method.setAccessible(true);

            HttpClient httpClient = (HttpClient) method.invoke(null);

            method.setAccessible(false);
            Assert.assertNotNull(httpClient);
            Assert.assertTrue(httpClient instanceof CloseableHttpClient);
        } catch (Exception e) {
            try {
                if (method != null)
                    method.setAccessible(false);
            } catch (Exception e1) {
                Assert.fail("Unable to make initialize method private again!");
            }
            Assert.fail("initialize method invocation failed with reflection!");
        }
    }

    // test to check if the doGet throws exception if uri is null or blank
    @Test(groups = "HttpUtils.doGet")
    public void doGet_should_throw_stock_exception_since_uri_is_blank() {

        PowerMockito.spy(HttpUtils.class);

        try {
            PowerMockito.doReturn(mockHttpClient).when(HttpUtils.class,
                    "initialize");
        } catch (Exception e1) {
            Assert.fail("Couldn't mock the HttpUtils.initialize method!", e1);
        }

        HashMap<String, String> headers = new HashMap<String, String>();
        headers.put("test", "value");

        try {
            HttpUtils.doGet("", headers);

            Assert.fail("Expected doGet to throw StockException - URI cannot be null or Empty!");
        } catch (StockException e) {
            Assert.assertEquals(e.getMessage(), "URI cannot be null or Empty");

            try {
                HttpUtils.doGet(null, headers);

                Assert.fail("Expected doGet to throw StockException - URI cannot be null or Empty!");
            } catch (StockException e1) {
                Assert.assertEquals(e.getMessage(),
                        "URI cannot be null or Empty");
            } catch (Exception e1) {
                Assert.fail(
                        "Didn't expect the exception other than StockException",
                        e);
            }
        } catch (Exception e) {
            Assert.fail(
                    "Didn't expect the exception other than StockException", e);
        }
    }

    @Test(groups = "HttpUtils.doGet")
    public void doGet_should_return_null_since_httpClient_execute_returned_with_unknown_response_code()
            throws ClientProtocolException, IOException {

        String succResponse = "Test Response";

        CloseableHttpResponse httpResponse = PowerMockito
                .mock(CloseableHttpResponse.class);
        StatusLine statusLine = PowerMockito.mock(StatusLine.class);

        StringEntity entity = new StringEntity(succResponse);

        PowerMockito.spy(HttpUtils.class);

        try {
            PowerMockito.doReturn(mockHttpClient).when(HttpUtils.class,
                    "initialize");
        } catch (Exception e1) {
            Assert.fail("Couldn't mock the HttpUtils.initialize method!", e1);
        }

        // and:
        PowerMockito.when(statusLine.getStatusCode()).thenReturn(600);
        PowerMockito.when(httpResponse.getEntity()).thenReturn(entity);
        PowerMockito.when(httpResponse.getStatusLine()).thenReturn(statusLine);

        PowerMockito.when(mockHttpClient.execute(Mockito.any(HttpGet.class)))
                .thenReturn(httpResponse);
        HashMap<String, String> headers = new HashMap<String, String>();

        headers.put("test", "value");

        try {
            String response = HttpUtils.doGet("http://example.com", headers);

            Assert.assertNull(response);
        } catch (StockException e) {
            Assert.fail("Exception occured while calling HttpUtils.doGet!", e);
        }
    }

    @Test(groups = "HttpUtils.doGet")
    public void doGet_should_return_string_response_since_httpClient_execute_returned_with_success()
            throws ClientProtocolException, IOException {

        String succResponse = "Test Response";

        CloseableHttpResponse httpResponse = PowerMockito
                .mock(CloseableHttpResponse.class);
        StatusLine statusLine = PowerMockito.mock(StatusLine.class);

        StringEntity entity = new StringEntity(succResponse);

        PowerMockito.spy(HttpUtils.class);

        try {
            PowerMockito.doReturn(mockHttpClient).when(HttpUtils.class,
                    "initialize");
        } catch (Exception e1) {
            Assert.fail("Couldn't mock the HttpUtils.initialize method!", e1);
        }

        // and:
        PowerMockito.when(statusLine.getStatusCode()).thenReturn(200);
        PowerMockito.when(httpResponse.getEntity()).thenReturn(entity);
        PowerMockito.when(httpResponse.getStatusLine()).thenReturn(statusLine);

        PowerMockito.when(mockHttpClient.execute(Mockito.any(HttpGet.class)))
                .thenReturn(httpResponse);
        HashMap<String, String> headers = new HashMap<String, String>();

        headers.put("test", "value");

        try {
            String response = HttpUtils.doGet("http://example.com", headers);

            Assert.assertEquals(response, succResponse);
        } catch (StockException e) {
            Assert.fail("Exception occured while calling HttpUtils.doGet!", e);
        }
    }

    @Test(groups = "HttpUtils.doGet")
    public void doGet_should_throw_stockexception_since_httpClient_execute_returned_with_bad_request()
            throws ClientProtocolException, IOException {

        String errorResponse = "{ \"error\": { \"message\": \"Test Response\" } }";

        CloseableHttpResponse httpResponse = PowerMockito
                .mock(CloseableHttpResponse.class);
        StatusLine statusLine = PowerMockito.mock(StatusLine.class);

        StringEntity entity = new StringEntity(errorResponse);

        PowerMockito.spy(HttpUtils.class);

        try {
            PowerMockito.doReturn(mockHttpClient).when(HttpUtils.class,
                    "initialize");
        } catch (Exception e1) {
            Assert.fail("Couldn't mock the HttpUtils.initialize method!", e1);
        }

        // and:
        PowerMockito.when(statusLine.getStatusCode()).thenReturn(400);
        PowerMockito.when(httpResponse.getEntity()).thenReturn(entity);
        PowerMockito.when(httpResponse.getStatusLine()).thenReturn(statusLine);

        PowerMockito.when(mockHttpClient.execute(Mockito.any(HttpGet.class)))
                .thenReturn(httpResponse);

        try {
            HttpUtils.doGet("http://example.com", null);

            Assert.fail("Expected the StockException since the httpclient.execute returned with bad request!");
        } catch (StockException e) {
            Assert.assertEquals(e.getMessage(), errorResponse);
            Assert.assertEquals(e.getCode(), 400);
        }
    }

    @Test(groups = "HttpUtils.doGet")
    public void doGet_should_throw_stockexception_since_httpClient_execute_returned_with_internal_server_error()
            throws ClientProtocolException, IOException {

        CloseableHttpResponse httpResponse = PowerMockito
                .mock(CloseableHttpResponse.class);
        StatusLine statusLine = PowerMockito.mock(StatusLine.class);

        PowerMockito.spy(HttpUtils.class);

        try {
            PowerMockito.doReturn(mockHttpClient).when(HttpUtils.class,
                    "initialize");
        } catch (Exception e1) {
            Assert.fail("Couldn't mock the HttpUtils.initialize method!", e1);
        }

        // and:
        PowerMockito.when(statusLine.getStatusCode()).thenReturn(500);
        PowerMockito.when(httpResponse.getStatusLine()).thenReturn(statusLine);

        PowerMockito.when(mockHttpClient.execute(Mockito.any(HttpGet.class)))
                .thenReturn(httpResponse);

        try {
            HttpUtils.doGet("http://example.com", null);

            Assert.fail("Expected the StockException since the httpclient.execute returned with bad request!");
        } catch (StockException e) {
            Assert.assertEquals(e.getMessage(),
                    "API returned with Server Error");
            Assert.assertEquals(e.getCode(), 500);
        }
    }

    @Test(groups = "HttpUtils.doGet", expectedExceptions = StockException.class)
    public void doGet_should_throw_stockexception_since_entityutils_tostring_throws_exception()
            throws ClientProtocolException, IOException, StockException {

        CloseableHttpResponse httpResponse = PowerMockito
                .mock(CloseableHttpResponse.class);
        StatusLine statusLine = PowerMockito.mock(StatusLine.class);

        PowerMockito.spy(HttpUtils.class);

        try {
            PowerMockito.doReturn(mockHttpClient).when(HttpUtils.class,
                    "initialize");
        } catch (Exception e1) {
            Assert.fail("Couldn't mock the HttpUtils.initialize method!", e1);
        }

        // and:
        PowerMockito.when(statusLine.getStatusCode()).thenReturn(400);
        PowerMockito.when(httpResponse.getEntity()).thenReturn(null);
        PowerMockito.when(httpResponse.getStatusLine()).thenReturn(statusLine);

        PowerMockito.when(mockHttpClient.execute(Mockito.any(HttpGet.class)))
                .thenReturn(httpResponse);

        HttpUtils.doGet("http://example.com", null);
    }

    @Test(groups = "HttpUtils.doPost")
    public void doPost_should_throw_stock_exception_since_uri_is_blank_or_null() {

        PowerMockito.spy(HttpUtils.class);

        String reqBody = "Test request";

        try {
            PowerMockito.doReturn(mockHttpClient).when(HttpUtils.class,
                    "initialize");
        } catch (Exception e1) {
            Assert.fail("Couldn't mock the HttpUtils.initialize method!", e1);
        }

        HashMap<String, String> headers = new HashMap<String, String>();
        headers.put("test", "value");

        try {
            HttpUtils.doPost("", headers, reqBody.getBytes(),
                    ContentType.TEXT_PLAIN);

            Assert.fail("Expected doPost to throw StockException - URI cannot be null or Empty!");
        } catch (StockException e) {
            Assert.assertEquals(e.getMessage(), "URI cannot be null or Empty");

            try {
                HttpUtils.doPost(null, headers, reqBody.getBytes(),
                        ContentType.TEXT_PLAIN);

                Assert.fail("Expected doPost to throw StockException - URI cannot be null or Empty!");
            } catch (StockException e1) {
                Assert.assertEquals(e.getMessage(),
                        "URI cannot be null or Empty");
            } catch (Exception e1) {
                Assert.fail(
                        "Didn't expect the exception other than StockException",
                        e);
            }

        } catch (Exception e) {
            Assert.fail(
                    "Didn't expect the exception other than StockException", e);
        }
    }

    @Test(groups = "HttpUtils.doPost")
    public void doPost_should_return_null_since_httpClient_execute_returned_with_unknown_response_code()
            throws ClientProtocolException, IOException {

        String succResponse = "Test Response";

        CloseableHttpResponse httpResponse = PowerMockito
                .mock(CloseableHttpResponse.class);
        StatusLine statusLine = PowerMockito.mock(StatusLine.class);

        StringEntity entity = new StringEntity(succResponse);

        PowerMockito.spy(HttpUtils.class);

        try {
            PowerMockito.doReturn(mockHttpClient).when(HttpUtils.class,
                    "initialize");
        } catch (Exception e1) {
            Assert.fail("Couldn't mock the HttpUtils.initialize method!", e1);
        }

        // and:
        PowerMockito.when(statusLine.getStatusCode()).thenReturn(300);
        PowerMockito.when(httpResponse.getEntity()).thenReturn(entity);
        PowerMockito.when(httpResponse.getStatusLine()).thenReturn(statusLine);

        PowerMockito.when(mockHttpClient.execute(Mockito.any(HttpGet.class)))
                .thenReturn(httpResponse);
        HashMap<String, String> headers = new HashMap<String, String>();

        headers.put("test", "value");

        try {
            String response = HttpUtils.doPost("http://example.com", headers,
                    succResponse.getBytes(), ContentType.TEXT_PLAIN);

            Assert.assertNull(response);
        } catch (StockException e) {
            Assert.fail("Exception occured while calling HttpUtils.doGet!", e);
        }
    }

    @Test(groups = "HttpUtils.doPost")
    public void doPost_should_return_string_response_since_httpClient_execute_returned_with_200_success()
            throws ClientProtocolException, IOException {

        String succResponse = "Test Response";

        CloseableHttpResponse httpResponse = PowerMockito
                .mock(CloseableHttpResponse.class);
        StatusLine statusLine = PowerMockito.mock(StatusLine.class);

        StringEntity entity = new StringEntity(succResponse);

        PowerMockito.spy(HttpUtils.class);

        try {
            PowerMockito.doReturn(mockHttpClient).when(HttpUtils.class,
                    "initialize");
        } catch (Exception e1) {
            Assert.fail("Couldn't mock the HttpUtils.initialize method!", e1);
        }

        // and:
        PowerMockito.when(statusLine.getStatusCode()).thenReturn(200);
        PowerMockito.when(httpResponse.getEntity()).thenReturn(entity);
        PowerMockito.when(httpResponse.getStatusLine()).thenReturn(statusLine);

        PowerMockito.when(mockHttpClient.execute(Mockito.any(HttpGet.class)))
                .thenReturn(httpResponse);
        HashMap<String, String> headers = new HashMap<String, String>();

        headers.put("test", "value");

        try {
            String response = HttpUtils.doPost("http://example.com", headers,
                    succResponse.getBytes(), ContentType.TEXT_PLAIN);

            Assert.assertEquals(response, succResponse);
        } catch (StockException e) {
            Assert.fail("Exception occured while calling HttpUtils.doGet!", e);
        }
    }

    @Test(groups = "HttpUtils.doPost")
    public void doPost_should_return_string_response_since_httpClient_execute_returned_with_201_success()
            throws ClientProtocolException, IOException {

        String succResponse = "Test Response";

        CloseableHttpResponse httpResponse = PowerMockito
                .mock(CloseableHttpResponse.class);
        StatusLine statusLine = PowerMockito.mock(StatusLine.class);

        StringEntity entity = new StringEntity(succResponse);

        PowerMockito.spy(HttpUtils.class);

        try {
            PowerMockito.doReturn(mockHttpClient).when(HttpUtils.class,
                    "initialize");
        } catch (Exception e1) {
            Assert.fail("Couldn't mock the HttpUtils.initialize method!", e1);
        }

        // and:
        PowerMockito.when(statusLine.getStatusCode()).thenReturn(201);
        PowerMockito.when(httpResponse.getEntity()).thenReturn(entity);
        PowerMockito.when(httpResponse.getStatusLine()).thenReturn(statusLine);

        PowerMockito.when(mockHttpClient.execute(Mockito.any(HttpGet.class)))
                .thenReturn(httpResponse);
        HashMap<String, String> headers = new HashMap<String, String>();

        headers.put("test", "value");

        try {
            String response = HttpUtils.doPost("http://example.com", headers,
                    succResponse.getBytes(), ContentType.TEXT_PLAIN);

            Assert.assertEquals(response, succResponse);
        } catch (StockException e) {
            Assert.fail("Exception occured while calling HttpUtils.doGet!", e);
        }
    }

    @Test(groups = "HttpUtils.doPost")
    public void doPost_should_throw_stockexception_since_httpClient_execute_returned_with_bad_request()
            throws ClientProtocolException, IOException {

        String errorResponse = "{ \"error\": { \"message\": \"Test Response\" } }";

        CloseableHttpResponse httpResponse = PowerMockito
                .mock(CloseableHttpResponse.class);
        StatusLine statusLine = PowerMockito.mock(StatusLine.class);

        StringEntity entity = new StringEntity(errorResponse);

        PowerMockito.spy(HttpUtils.class);

        try {
            PowerMockito.doReturn(mockHttpClient).when(HttpUtils.class,
                    "initialize");
        } catch (Exception e1) {
            Assert.fail("Couldn't mock the HttpUtils.initialize method!", e1);
        }

        // and:
        PowerMockito.when(statusLine.getStatusCode()).thenReturn(400);
        PowerMockito.when(httpResponse.getEntity()).thenReturn(entity);
        PowerMockito.when(httpResponse.getStatusLine()).thenReturn(statusLine);

        PowerMockito.when(mockHttpClient.execute(Mockito.any(HttpGet.class)))
                .thenReturn(httpResponse);

        try {
            HttpUtils.doPost("http://example.com", null,
                    errorResponse.getBytes(), ContentType.TEXT_PLAIN);

            Assert.fail("Expected the StockException since the httpclient.execute returned with bad request!");
        } catch (StockException e) {
            Assert.assertEquals(e.getMessage(), errorResponse);
            Assert.assertEquals(e.getCode(), 400);
        }
    }

    @Test(groups = "HttpUtils.doPost")
    public void doPost_should_throw_stockexception_since_httpClient_execute_returned_with_internal_server_error()
            throws ClientProtocolException, IOException {

        CloseableHttpResponse httpResponse = PowerMockito
                .mock(CloseableHttpResponse.class);
        StatusLine statusLine = PowerMockito.mock(StatusLine.class);

        PowerMockito.spy(HttpUtils.class);

        try {
            PowerMockito.doReturn(mockHttpClient).when(HttpUtils.class,
                    "initialize");
        } catch (Exception e1) {
            Assert.fail("Couldn't mock the HttpUtils.initialize method!", e1);
        }

        // and:
        PowerMockito.when(statusLine.getStatusCode()).thenReturn(500);
        PowerMockito.when(httpResponse.getStatusLine()).thenReturn(statusLine);

        PowerMockito.when(mockHttpClient.execute(Mockito.any(HttpGet.class)))
                .thenReturn(httpResponse);

        try {
            HttpUtils.doPost("http://example.com", null, null,
                    ContentType.TEXT_PLAIN);

            Assert.fail("Expected the StockException since the httpclient.execute returned with bad request!");
        } catch (StockException e) {
            Assert.assertEquals(e.getMessage(),
                    "API returned with Server Error");
            Assert.assertEquals(e.getCode(), 500);
        }
    }

    @Test(groups = "HttpUtils.doPost", expectedExceptions = StockException.class)
    public void doPost_should_throw_stockexception_since_entityutils_tostring_throws_exception()
            throws ClientProtocolException, IOException, StockException {

        CloseableHttpResponse httpResponse = PowerMockito
                .mock(CloseableHttpResponse.class);
        StatusLine statusLine = PowerMockito.mock(StatusLine.class);

        try {
            Field f = HttpUtils.class.getDeclaredField("sHttpClient");
            f.setAccessible(true);
            f.set(HttpUtils.class, null);
        } catch (Exception e) {
            Assert.fail("Couldn't reset the sHttpClient to null!", e);
        }

        PowerMockito.spy(HttpUtils.class);

        try {
            PowerMockito.doReturn(mockHttpClient).when(HttpUtils.class,
                    "initialize");
        } catch (Exception e1) {
            Assert.fail("Couldn't mock the HttpUtils.initialize method!", e1);
        }

        // and:
        PowerMockito.when(statusLine.getStatusCode()).thenReturn(400);
        PowerMockito.when(httpResponse.getEntity()).thenReturn(null);
        PowerMockito.when(httpResponse.getStatusLine()).thenReturn(statusLine);

        PowerMockito.when(mockHttpClient.execute(Mockito.any(HttpGet.class)))
                .thenReturn(httpResponse);

        String body = "test request";

        HttpUtils.doPost("http://example.com", null, body.getBytes(),
                ContentType.TEXT_PLAIN);
    }

    @Test(groups = "HttpUtils.doMultiPart")
    public void doMultiPart_should_throw_stock_exception_since_uri_is_blank_or_null() {

        PowerMockito.spy(HttpUtils.class);

        String reqBody = "Test request";

        try {
            PowerMockito.doReturn(mockHttpClient).when(HttpUtils.class,
                    "initialize");
        } catch (Exception e1) {
            Assert.fail("Couldn't mock the HttpUtils.initialize method!", e1);
        }

        HashMap<String, String> headers = new HashMap<String, String>();
        headers.put("test", "value");

        try {
            HttpUtils.doMultiPart("", reqBody.getBytes(), headers);

            Assert.fail("Expected doPost to throw StockException - URI cannot be null or Empty!");
        } catch (StockException e) {
            Assert.assertEquals(e.getMessage(), "URI cannot be null or Empty");

            try {
                HttpUtils.doMultiPart(null, reqBody.getBytes(), headers);

                Assert.fail("Expected doPost to throw StockException - URI cannot be null or Empty!");
            } catch (StockException e1) {
                Assert.assertEquals(e.getMessage(),
                        "URI cannot be null or Empty");
            } catch (Exception e1) {
                Assert.fail(
                        "Didn't expect the exception other than StockException",
                        e);
            }

        } catch (Exception e) {
            Assert.fail(
                    "Didn't expect the exception other than StockException", e);
        }
    }

    @Test(groups = "HttpUtils.doMultiPart")
    public void doMultiPart_should_return_null_since_httpClient_execute_returned_with_unknown_response_code()
            throws ClientProtocolException, IOException {

        String succResponse = "Test Response";

        CloseableHttpResponse httpResponse = PowerMockito
                .mock(CloseableHttpResponse.class);
        StatusLine statusLine = PowerMockito.mock(StatusLine.class);

        StringEntity entity = new StringEntity(succResponse);

        PowerMockito.spy(HttpUtils.class);

        try {
            PowerMockito.doReturn(mockHttpClient).when(HttpUtils.class,
                    "initialize");
        } catch (Exception e1) {
            Assert.fail("Couldn't mock the HttpUtils.initialize method!", e1);
        }

        // and:
        PowerMockito.when(statusLine.getStatusCode()).thenReturn(300);
        PowerMockito.when(httpResponse.getEntity()).thenReturn(entity);
        PowerMockito.when(httpResponse.getStatusLine()).thenReturn(statusLine);

        PowerMockito.when(mockHttpClient.execute(Mockito.any(HttpGet.class)))
                .thenReturn(httpResponse);
        HashMap<String, String> headers = new HashMap<String, String>();

        headers.put("test", "value");

        try {
            String response = HttpUtils.doMultiPart("http://example.com",
                    succResponse.getBytes(), headers);

            Assert.assertNull(response);
        } catch (StockException e) {
            Assert.fail("Exception occured while calling HttpUtils.doGet!", e);
        }
    }

    @Test(groups = "HttpUtils.doMultiPart")
    public void doMultiPart_should_return_string_response_since_httpClient_execute_returned_with_200_success()
            throws ClientProtocolException, IOException {

        String succResponse = "Test Response";

        CloseableHttpResponse httpResponse = PowerMockito
                .mock(CloseableHttpResponse.class);
        StatusLine statusLine = PowerMockito.mock(StatusLine.class);

        StringEntity entity = new StringEntity(succResponse);

        PowerMockito.spy(HttpUtils.class);

        try {
            PowerMockito.doReturn(mockHttpClient).when(HttpUtils.class,
                    "initialize");
        } catch (Exception e1) {
            Assert.fail("Couldn't mock the HttpUtils.initialize method!", e1);
        }

        // and:
        PowerMockito.when(statusLine.getStatusCode()).thenReturn(200);
        PowerMockito.when(httpResponse.getEntity()).thenReturn(entity);
        PowerMockito.when(httpResponse.getStatusLine()).thenReturn(statusLine);

        PowerMockito.when(mockHttpClient.execute(Mockito.any(HttpGet.class)))
                .thenReturn(httpResponse);
        HashMap<String, String> headers = new HashMap<String, String>();

        headers.put("test", "value");

        try {
            String response = HttpUtils.doMultiPart("http://example.com",
                    succResponse.getBytes(), headers);

            Assert.assertEquals(response, succResponse);
        } catch (StockException e) {
            Assert.fail("Exception occured while calling HttpUtils.doGet!", e);
        }
    }

    @Test(groups = "HttpUtils.doMultiPart")
    public void doMultiPart_should_return_string_response_since_httpClient_execute_returned_with_201_success()
            throws ClientProtocolException, IOException {

        String succResponse = "Test Response";

        CloseableHttpResponse httpResponse = PowerMockito
                .mock(CloseableHttpResponse.class);
        StatusLine statusLine = PowerMockito.mock(StatusLine.class);

        StringEntity entity = new StringEntity(succResponse);

        PowerMockito.spy(HttpUtils.class);

        try {
            PowerMockito.doReturn(mockHttpClient).when(HttpUtils.class,
                    "initialize");
        } catch (Exception e1) {
            Assert.fail("Couldn't mock the HttpUtils.initialize method!", e1);
        }

        // and:
        PowerMockito.when(statusLine.getStatusCode()).thenReturn(201);
        PowerMockito.when(httpResponse.getEntity()).thenReturn(entity);
        PowerMockito.when(httpResponse.getStatusLine()).thenReturn(statusLine);

        PowerMockito.when(mockHttpClient.execute(Mockito.any(HttpGet.class)))
                .thenReturn(httpResponse);
        HashMap<String, String> headers = new HashMap<String, String>();

        headers.put("test", "value");

        try {
            String response = HttpUtils.doMultiPart("http://example.com",
                    succResponse.getBytes(), headers);

            Assert.assertEquals(response, succResponse);
        } catch (StockException e) {
            Assert.fail("Exception occured while calling HttpUtils.doGet!", e);
        }
    }

    @Test(groups = "HttpUtils.doMultiPart")
    public void doMultiPart_should_throw_stockexception_since_httpClient_execute_returned_with_bad_request()
            throws ClientProtocolException, IOException {

        String errorResponse = "{ \"error\": { \"message\": \"Test Response\" } }";

        CloseableHttpResponse httpResponse = PowerMockito
                .mock(CloseableHttpResponse.class);
        StatusLine statusLine = PowerMockito.mock(StatusLine.class);

        StringEntity entity = new StringEntity(errorResponse);

        PowerMockito.spy(HttpUtils.class);

        try {
            PowerMockito.doReturn(mockHttpClient).when(HttpUtils.class,
                    "initialize");
        } catch (Exception e1) {
            Assert.fail("Couldn't mock the HttpUtils.initialize method!", e1);
        }

        // and:
        PowerMockito.when(statusLine.getStatusCode()).thenReturn(400);
        PowerMockito.when(httpResponse.getEntity()).thenReturn(entity);
        PowerMockito.when(httpResponse.getStatusLine()).thenReturn(statusLine);

        PowerMockito.when(mockHttpClient.execute(Mockito.any(HttpGet.class)))
                .thenReturn(httpResponse);

        try {
            HttpUtils.doMultiPart("http://example.com",
                    errorResponse.getBytes(), null);

            Assert.fail("Expected the StockException since the httpclient.execute returned with bad request!");
        } catch (StockException e) {
            Assert.assertEquals(e.getMessage(), errorResponse);
            Assert.assertEquals(e.getCode(), 400);
        }
    }

    @Test(groups = "HttpUtils.doMultiPart")
    public void doMultiPart_should_throw_stockexception_since_httpClient_execute_returned_with_internal_server_error()
            throws ClientProtocolException, IOException {

        CloseableHttpResponse httpResponse = PowerMockito
                .mock(CloseableHttpResponse.class);
        StatusLine statusLine = PowerMockito.mock(StatusLine.class);

        PowerMockito.spy(HttpUtils.class);

        try {
            PowerMockito.doReturn(mockHttpClient).when(HttpUtils.class,
                    "initialize");
        } catch (Exception e1) {
            Assert.fail("Couldn't mock the HttpUtils.initialize method!", e1);
        }

        // and:
        PowerMockito.when(statusLine.getStatusCode()).thenReturn(500);
        PowerMockito.when(httpResponse.getStatusLine()).thenReturn(statusLine);

        PowerMockito.when(mockHttpClient.execute(Mockito.any(HttpGet.class)))
                .thenReturn(httpResponse);

        try {
            HttpUtils.doMultiPart("http://example.com", null, null);

            Assert.fail("Expected the StockException since the httpclient.execute returned with bad request!");
        } catch (StockException e) {
            Assert.assertEquals(e.getMessage(),
                    "API returned with Server Error");
            Assert.assertEquals(e.getCode(), 500);
        }
    }

    @Test(groups = "HttpUtils.doMultiPart", expectedExceptions = StockException.class)
    public void doMultiPart_should_throw_stockexception_since_entityutils_tostring_throws_exception()
            throws ClientProtocolException, IOException, StockException {

        CloseableHttpResponse httpResponse = PowerMockito
                .mock(CloseableHttpResponse.class);
        StatusLine statusLine = PowerMockito.mock(StatusLine.class);

        try {
            Field f = HttpUtils.class.getDeclaredField("sHttpClient");
            f.setAccessible(true);
            f.set(HttpUtils.class, null);
        } catch (Exception e) {
            Assert.fail("Couldn't reset the sHttpClient to null!", e);
        }

        PowerMockito.spy(HttpUtils.class);

        try {
            PowerMockito.doReturn(mockHttpClient).when(HttpUtils.class,
                    "initialize");
        } catch (Exception e1) {
            Assert.fail("Couldn't mock the HttpUtils.initialize method!", e1);
        }

        // and:
        PowerMockito.when(statusLine.getStatusCode()).thenReturn(400);
        PowerMockito.when(httpResponse.getEntity()).thenReturn(null);
        PowerMockito.when(httpResponse.getStatusLine()).thenReturn(statusLine);

        PowerMockito.when(mockHttpClient.execute(Mockito.any(HttpGet.class)))
                .thenReturn(httpResponse);

        String body = "test request";

        HttpUtils.doMultiPart("http://example.com", body.getBytes(), null);
    }
}