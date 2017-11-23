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
@Test(suiteName = "StockFileComps")
public class StockFileCompsTest {

    StockFileComps comps;

    @BeforeSuite
    void creating_StockFileComps_InstanceSuccessfully() {
        comps = new StockFileComps();
    }

    @Test(groups = { "Setters" })
    void setStandard_should_setValue_of_Type_StockFileCompProp_in_mStandard_Field()
            throws IllegalAccessException, NoSuchFieldException {
        StockFileCompProp prop = new StockFileCompProp();
        prop.setHeight(100);
        comps.setStandard(prop);
        Field f = comps.getClass().getDeclaredField("mStandard");
        f.setAccessible(true);
        prop = (StockFileCompProp) f.get(comps);
        Assert.assertTrue((prop.getHeight()).equals(100));
    }

    @Test(groups = { "Getters" })
    void getStandard_should_return_Value_of_Type_StockFileCompProp_of_field_mstandard()
            throws NoSuchFieldException, IllegalAccessException {
        StockFileCompProp prop = new StockFileCompProp();
        prop.setWidth(1000);
        Field f = comps.getClass().getDeclaredField("mStandard");
        f.setAccessible(true);
        f.set(comps, prop);
        Assert.assertEquals(comps.getStandard().getWidth().intValue(), 1000);

    }

    @Test(groups = { "Setters" })
    void setVideoHD_should_setValue_of_Type_StockFileCompProp_in_mVideoHD_Field()
            throws IllegalAccessException, NoSuchFieldException {
        StockFileCompProp prop = new StockFileCompProp();
        prop.setWidth(100);
        comps.setVideoHD(prop);
        Field f = comps.getClass().getDeclaredField("mVideoHD");
        f.setAccessible(true);
        prop = (StockFileCompProp) f.get(comps);
        Assert.assertTrue((prop.getWidth()).equals(100));
    }

    @Test(groups = { "Getters" })
    void getVideoHD_should_return_Value_of_Type_StockFileCompProp_of_field_mVideoHD()
            throws NoSuchFieldException, IllegalAccessException {
        StockFileCompProp prop = new StockFileCompProp();
        prop.setHeight(1000);
        Field f = comps.getClass().getDeclaredField("mVideoHD");
        f.setAccessible(true);
        f.set(comps, prop);
        Assert.assertEquals(comps.getVideoHD().getHeight().intValue(), 1000);

    }

    @Test(groups = { "Setters" })
    void setVideo4K_should_setValue_of_Type_StockFileCompProp_in_mStandard_Field()
            throws IllegalAccessException, NoSuchFieldException {
        StockFileCompProp prop = new StockFileCompProp();
        prop.setUrl("Text");
        comps.setVideo4K(prop);
        Field f = comps.getClass().getDeclaredField("mVideo4K");
        f.setAccessible(true);
        prop = (StockFileCompProp) f.get(comps);
        Assert.assertTrue((prop.getUrl()).equals("Text"));
    }

    @Test(groups = { "Getters" })
    void getVideo4K_should_return_Value_of_Type_StockFileCompProp_of_field_mVideoHD()
            throws NoSuchFieldException, IllegalAccessException {
        StockFileCompProp prop = new StockFileCompProp();
        prop.setUrl("Text");
        Field f = comps.getClass().getDeclaredField("mVideo4K");
        f.setAccessible(true);
        f.set(comps, prop);
        Assert.assertTrue(comps.getVideo4K().getUrl().equals("Text"));

    }

}
