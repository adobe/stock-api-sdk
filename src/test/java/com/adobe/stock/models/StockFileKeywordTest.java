/*******************************************************************************
 * Copyright 2017 Adobe Systems Incorporated. All rights reserved.
 * This file is licensed to you under the Apache License, Version 2.0
 * (the "License") you may not use this file except in compliance with the
 * License. You may obtain a copy of the License at
 * http://www.apache.org/licenses/LICENSE-2.0
 *******************************************************************************/
package com.adobe.stock.models;

import java.lang.reflect.Field;

import org.testng.Assert;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;

@Listeners({ com.adobe.stock.logger.TestCustomLogger.class,
        com.adobe.stock.logger.TestSuiteLogger.class })
@Test(suiteName = "StockFileKeyword")
public class StockFileKeywordTest {
    StockFileKeyword stockFileKeyword;

    @BeforeSuite()
    void create_StockFileLicenseProp_Instance_successfully() {
        stockFileKeyword = new StockFileKeyword();
    }

    @Test(groups = { "Getters" })
    void getName_should_return_name_of_media_keyword_ofType_String_StockFileKeyword()
            throws NoSuchFieldException, IllegalAccessException {
        Field f = stockFileKeyword.getClass().getDeclaredField("mName");
        f.setAccessible(true);
        f.set(stockFileKeyword, "SomeText");
        Assert.assertTrue(stockFileKeyword.getName().equals("SomeText"));
    }

    @Test(groups = { "Setters" })
    void setName_should_set_name_of_media_keyword_ofType_String_StockFileKeyword()
            throws NoSuchFieldException, IllegalAccessException {
        stockFileKeyword.setName("SomeText");
        Field f = stockFileKeyword.getClass().getDeclaredField("mName");
        f.setAccessible(true);
        Assert.assertTrue(f.get(stockFileKeyword).equals("SomeText"));
    }
}
