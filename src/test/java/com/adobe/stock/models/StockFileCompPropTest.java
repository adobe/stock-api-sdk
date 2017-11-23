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
@Test(suiteName = "StockFileCompProp")
public class StockFileCompPropTest {

    StockFileCompProp prop;

    @BeforeSuite()
    void create_SearchFileCompProp_Instance_successfully() {
        prop = new StockFileCompProp();
    }

    @Test(groups = { "Getters" })
    void getWidth_should_return_width_of_Type_Integer_StockFileCompProp()
            throws NoSuchFieldException, IllegalAccessException {
        Field f = prop.getClass().getDeclaredField("mWidth");
        f.setAccessible(true);
        f.set(prop, 100);
        Assert.assertEquals(100, prop.getWidth().intValue());
    }

    @Test(groups = { "Setters" })
    void setWidth_should_set_width_of_Type_Integer_StockFileCompProp()
            throws NoSuchFieldException, IllegalAccessException {
        prop.setWidth(1000);
        Field f = prop.getClass().getDeclaredField("mWidth");
        f.setAccessible(true);
        Assert.assertEquals(1000, f.get(prop));
    }

    @Test(groups = { "Getters" })
    void getHeight_should_return_Height_of_Type_Integer_StockFileCompProp()
            throws NoSuchFieldException, IllegalAccessException {
        Field f = prop.getClass().getDeclaredField("mHeight");
        f.setAccessible(true);
        f.set(prop, 100);
        Assert.assertEquals(100, prop.getHeight().intValue());
    }

    @Test(groups = { "Setters" })
    void setHeight_should_set_height_of_Type_Integer_StockFileCompProp()
            throws NoSuchFieldException, IllegalAccessException {
        prop.setHeight(1000);
        Field f = prop.getClass().getDeclaredField("mHeight");
        f.setAccessible(true);
        Assert.assertEquals(1000, f.get(prop));
    }

    @Test(groups = { "Getters" })
    void getUrl_should_return_Url_of_Type_String_StockFileCompProp()
            throws NoSuchFieldException, IllegalAccessException {
        Field f = prop.getClass().getDeclaredField("mUrl");
        f.setAccessible(true);
        f.set(prop, "www.example.com");
        Assert.assertTrue(prop.getUrl().equals("www.example.com"));
    }

    @Test(groups = { "Setters" })
    void setUrl_should_set_Url_of_Type_String_StockFileCompProp()
            throws NoSuchFieldException, IllegalAccessException {
        prop.setUrl("www.example.com");
        Field f = prop.getClass().getDeclaredField("mUrl");
        f.setAccessible(true);
        Assert.assertTrue(f.get(prop).equals("www.example.com"));
    }

}
