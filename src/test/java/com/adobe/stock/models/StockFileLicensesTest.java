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
@Test(suiteName = "StockFileLicenses")
public class StockFileLicensesTest {

    StockFileLicenses stockFileLicenses;

    @BeforeSuite()
    void creating_StockFileLicenses_Instance_successfully() {
        stockFileLicenses = new StockFileLicenses();
    }

    @Test(groups = { "Setters" })
    void setStandard_should_setValue_of_Type_StockFileLicenseProp_in_mStandard_Field()
            throws IllegalAccessException, NoSuchFieldException {
        StockFileLicenseProp standard = new StockFileLicenseProp();
        standard.setHeight(100);
        stockFileLicenses.setStandard(standard);
        Field f = stockFileLicenses.getClass().getDeclaredField("mStandard");
        f.setAccessible(true);
        standard = (StockFileLicenseProp) f.get(stockFileLicenses);
        Assert.assertTrue((standard.getHeight()).equals(100));
    }

    @Test(groups = { "Setters" })
    void setStandardM_should_setValue_of_Type_StockFileLicenseProp_in_mStandardM_Field()
            throws IllegalAccessException, NoSuchFieldException {
        StockFileLicenseProp standard = new StockFileLicenseProp();
        standard.setWidth(1000);
        stockFileLicenses.setStandardM(standard);
        Field f = stockFileLicenses.getClass().getDeclaredField("mStandardM");
        f.setAccessible(true);
        standard = (StockFileLicenseProp) f.get(stockFileLicenses);
        Assert.assertTrue((standard.getWidth()).equals(1000));
    }

    @Test(groups = { "Getters" })
    void getStandard_should_return_Value_of_Type_StockFileLicenseProp_of_field_mstandard()
            throws NoSuchFieldException, IllegalAccessException {
        StockFileLicenseProp standard = new StockFileLicenseProp();
        standard.setWidth(1000);
        Field f = stockFileLicenses.getClass().getDeclaredField("mStandard");
        f.setAccessible(true);
        f.set(stockFileLicenses, standard);
        Assert.assertEquals(stockFileLicenses.getStandard().getWidth()
                .intValue(), 1000);

    }

    @Test(groups = { "Getters" })
    void getStandardM_should_return_Value_of_Type_StockFileLicenseProp_of_field_mstandardM()
            throws NoSuchFieldException, IllegalAccessException {
        StockFileLicenseProp standard = new StockFileLicenseProp();
        standard.setWidth(1000);
        Field f = stockFileLicenses.getClass().getDeclaredField("mStandardM");
        f.setAccessible(true);
        f.set(stockFileLicenses, standard);
        Assert.assertEquals(stockFileLicenses.getStandardM().getWidth()
                .intValue(), 1000);

    }
}
