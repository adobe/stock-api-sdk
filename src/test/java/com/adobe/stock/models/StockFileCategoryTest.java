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
@Test(suiteName = "StockFileCategory")
public class StockFileCategoryTest {
    StockFileCategory stockFileCategory;

    @BeforeSuite()
    void create_stockFileCategory_Instance_successfully() {
        stockFileCategory = new StockFileCategory();
    }

    @Test(groups = { "Getters" })
    void getName_should_return_localised_name_of_category_ofType_String_StockFileCategory()
            throws NoSuchFieldException, IllegalAccessException {
        Field f = stockFileCategory.getClass().getDeclaredField("mName");
        f.setAccessible(true);
        f.set(stockFileCategory, "SomeText");
        Assert.assertTrue(stockFileCategory.getName().equals("SomeText"));
    }

    @Test(groups = { "Setters" })
    void setName_should_set_localised_name_of_category_ofType_String_StockFileCategory()
            throws NoSuchFieldException, IllegalAccessException {
        stockFileCategory.setName("SomeText");
        Field f = stockFileCategory.getClass().getDeclaredField("mName");
        f.setAccessible(true);
        Assert.assertTrue(f.get(stockFileCategory).equals("SomeText"));
    }

    @Test(groups = { "Getters" })
    void getId_should_return_category_id_of_Type_Integer_StockFileCategory()
            throws NoSuchFieldException, IllegalAccessException {
        Field f = stockFileCategory.getClass().getDeclaredField("mId");
        f.setAccessible(true);
        f.set(stockFileCategory, 100);
        Assert.assertEquals(100, stockFileCategory.getId().intValue());
    }

    @Test(groups = { "Setters" })
    void setId_should_set_category_id_of_Type_Integer_StockFileCategory()
            throws NoSuchFieldException, IllegalAccessException {
        stockFileCategory.setId(1000);
        Field f = stockFileCategory.getClass().getDeclaredField("mId");
        f.setAccessible(true);
        Assert.assertEquals(1000, f.get(stockFileCategory));
    }

    @Test(groups = { "Getters" })
    void getLink_should_return_link_of_Type_String_StockFileCategory()
            throws NoSuchFieldException, IllegalAccessException {
        Field f = stockFileCategory.getClass().getDeclaredField("mLink");
        f.setAccessible(true);
        f.set(stockFileCategory, "SomeLink");
        Assert.assertEquals("SomeLink", stockFileCategory.getLink());
    }

    @Test(groups = { "Setters" })
    void setLink_should_set_link_of_Type_String_StockFileCategory()
            throws NoSuchFieldException, IllegalAccessException {
        stockFileCategory.setLink("SomeLink");;
        Field f = stockFileCategory.getClass().getDeclaredField("mLink");
        f.setAccessible(true);
        Assert.assertEquals("SomeLink", f.get(stockFileCategory));
    }
}
