/*******************************************************************************
 * Copyright 2017 Adobe Systems Incorporated. All rights reserved.
 * This file is licensed to you under the Apache License, Version 2.0
 * (the "License") you may not use this file except in compliance with the
 * License. You may obtain a copy of the License at
 * http://www.apache.org/licenses/LICENSE-2.0
 ******************************************************************************/
package com.adobe.stock.models;

import java.lang.reflect.Field;
import java.util.ArrayList;

import org.testng.Assert;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;

@Listeners({ com.adobe.stock.logger.TestCustomLogger.class,
    com.adobe.stock.logger.TestSuiteLogger.class })
@Test(suiteName = "LicenseHistoryResponse")
public class LicenseHistoryResponseTest {

    LicenseHistoryResponse response;

    @BeforeSuite
    void creating_LicenseHistoryResponse_InstanceSuccessfully() {
        response = new LicenseHistoryResponse();
    }

    @Test(groups = { "Getters" })
    void getNbResults_should_return_number_of_results_in_the_response()
            throws NoSuchFieldException, IllegalAccessException {
        Field f = response.getClass().getDeclaredField("mNbResults");
        f.setAccessible(true);
        f.set(response, 100);
        Assert.assertEquals(100, response.getNbResults().intValue());
    }

    @Test(groups = { "Setters" })
    void setNbResults_should_set_number_of_results_in_the_response()
            throws NoSuchFieldException, IllegalAccessException {
        response.setNbResults(100);
        Field f = response.getClass().getDeclaredField("mNbResults");
        f.setAccessible(true);
        Assert.assertEquals(100, f.get(response));
    }

    @Test(groups = { "Getters" })
    void getFiles_should_return_Array_of_stock_media_files_in_response()
            throws NoSuchFieldException, IllegalAccessException {
        Field f = response.getClass().getDeclaredField("mFiles");
        f.setAccessible(true);
        StockLicenseHistoryFile file = new StockLicenseHistoryFile();
        file.setId(3);
        file.setTitle("Some text");
        ArrayList<StockLicenseHistoryFile> files = new ArrayList<StockLicenseHistoryFile>();
        files.add(file);
        files.add(file);
        f.set(response, files);
        Assert.assertTrue(response.getFiles().get(0).getId()
                .equals(3));
        Assert.assertTrue(response.getFiles().get(0).getTitle()
                .equals("Some text"));
    }

    @Test(groups = { "Setters" })
    void setFiles_should_set_Array_of_stock_media_files_in_response()
            throws NoSuchFieldException, IllegalAccessException {
        StockLicenseHistoryFile file = new StockLicenseHistoryFile();
        file.setId(3);
        file.setTitle("Some text");
        ArrayList<StockLicenseHistoryFile> files = new ArrayList<StockLicenseHistoryFile>();
        files.add(file);
        files.add(file);
        response.setFiles(files);
        Field f = response.getClass().getDeclaredField("mFiles");
        f.setAccessible(true);
        Assert.assertTrue(f.get(response) != null);
    }

}
