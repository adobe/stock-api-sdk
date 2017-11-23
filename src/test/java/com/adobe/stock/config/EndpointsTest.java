/*******************************************************************************
 * Copyright 2017 Adobe Systems Incorporated. All rights reserved.
 * This file is licensed to you under the Apache License, Version 2.0
 * (the "License") you may not use this file except in compliance with the
 * License. You may obtain a copy of the License at
 * http://www.apache.org/licenses/LICENSE-2.0
 *******************************************************************************/
package com.adobe.stock.config;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

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
@Test(suiteName = "Endpoints")
public class EndpointsTest {
    private static final String SEARCH_FILES_ENDPOINT_KEY = "api.endpoints.search";
    private static final String PROPS_FILE_PROD = "prod-environment.properties";
    private static final String PROPS_FILE_STAGE = "stage-environment.properties";

    @ObjectFactory
    public IObjectFactory getObjectFactory() {
        return new PowerMockObjectFactory();
    }

    @Test(groups = "Endpoints.getSearchFilesEndpoint")
    public void getSearchFilesEndpoint_should_throw_stockexception_since_the_input_environment_is_null() {
        try {
            new Endpoints(null);

            Assert.fail("Didn't expect the endpoints to get contructed without exception!");
        } catch (StockException e) {
            Assert.assertEquals(e.getMessage(), "Environment can't be null");
        }
    }

    @Test(groups = "Endpoints.getSearchFilesEndpoint")
    public void getSearchFilesEndpoint_should_throw_stockexception_since_the_endpoint_properties_file_is_missing() {
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
            new Endpoints(Environment.STAGE);

            Assert.fail("Didn't expect the endpoints to get constructed without exception!");
        } catch (StockException e) {
            Assert.assertEquals(e.getMessage(),
                    "Could not load the endpoint properties file");
        }
    }

    @Test(groups = "Endpoints.getSearchFilesEndpoint")
    public void getSearchFilesEndpoint_should_throw_stockexception_since_the_endpoint_properties_load_thrown_ioexception() {
        PowerMockito.spy(Endpoints.class);

        InputStream fakeResource = PowerMockito.spy(new ByteArrayInputStream(
                "foobar".getBytes()));

        try {
            PowerMockito.doThrow(new IOException()).when(fakeResource)
                    .read(Mockito.any(byte[].class));
            PowerMockito.doReturn(fakeResource).when(Endpoints.class,
                    "getResourceAsStream", Mockito.any(String.class));
        } catch (Exception e1) {
            Assert.fail(
                    "Couldn't mock the Endpoints.getResourceAsStream method!",
                    e1);
        }

        try {
            new Endpoints(Environment.STAGE);

            Assert.fail("Didn't expect the endpoints to get constructed without exception!");
        } catch (StockException e) {
            Assert.assertEquals(e.getMessage(),
                    "Could not initialize the endpoints");
        }
    }

    @Test(groups = "Endpoints.getSearchFilesEndpoint")
    public void getSearchFilesEndpoint_should_return_prod_endpoint_if_environment_is_prod() {
        try {
            Endpoints endpoints = new Endpoints(Environment.PROD);
            InputStream input = getClass().getClassLoader()
                    .getResourceAsStream(PROPS_FILE_PROD);
            if (input == null)
                Assert.fail("Could not load properties file");
            Properties props = new Properties();
            props.load(input);
            Assert.assertEquals(props.getProperty(SEARCH_FILES_ENDPOINT_KEY),
                    endpoints.getSearchFilesEndpoint());
            input.close();
        } catch (Exception e) {
            Assert.fail("Didn't expect the Exception!", e);
        }
    }

    @Test(groups = "Endpoints.getSearchFilesEndpoint")
    public void getSearchFilesEndpoint_should_return_stage_endpoint_if_environment_is_stage() {
        try {
            Endpoints endpoints = new Endpoints(Environment.STAGE);
            InputStream input = getClass().getClassLoader()
                    .getResourceAsStream(PROPS_FILE_STAGE);
            if (input == null)
                Assert.fail("Could not load properties file");
            Properties props = new Properties();
            props.load(input);
            Assert.assertEquals(props.getProperty(SEARCH_FILES_ENDPOINT_KEY),
                    endpoints.getSearchFilesEndpoint());
            input.close();
        } catch (Exception e) {
            Assert.fail("Didn't expect the Exception!", e);
        }
    }

    @Test(groups = "Endpoints.getEnvironment")
    public void getEnvironment_should_return_correct_environment() {
        try {
            Endpoints endpoints = new Endpoints(Environment.STAGE);
            Assert.assertEquals(endpoints.getEnvironment(), Environment.STAGE);
        } catch (Exception e) {
            Assert.fail("Didn't expect the Exception!", e);
        }
    }
}