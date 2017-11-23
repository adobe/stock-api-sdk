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
@Test(suiteName = "LicenseComp")
public class LicenseCompTest {
    private LicenseComp comp;
    
    @BeforeSuite
    void creating_LicenseComp_InstanceSuccessfully() {
        comp = new LicenseComp();
        Assert.assertNotNull(comp);
    }

    @Test(groups = { "Getters" })
    void getUrl_should_return_url_of_Type_String_Licensecomp()
            throws NoSuchFieldException, IllegalAccessException {
        Field f = comp.getClass().getDeclaredField("mUrl");
        f.setAccessible(true);
        f.set(comp, "testUrl");
        Assert.assertTrue(comp.getUrl().equals("testUrl"));
    }

    @Test(groups = { "Setters" })
    void setUrl_should_set_url_of_Type_String_Licensecomp()
            throws NoSuchFieldException, IllegalAccessException {
        comp.setUrl("testUrl");
        Field f = comp.getClass().getDeclaredField("mUrl");
        f.setAccessible(true);
        Assert.assertTrue(f.get(comp).equals("testUrl"));
    }

    @Test(groups = { "Getters" })
    void getContentType_should_return_ContentType_of_Type_String_Licensecomp()
            throws NoSuchFieldException, IllegalAccessException {
        Field f = comp.getClass().getDeclaredField("mContentType");
        f.setAccessible(true);
        f.set(comp, "testType");
        Assert.assertTrue(comp.getContentType().equals("testType"));
    }

    @Test(groups = { "Setters" })
    void setContentType_should_set_ContentType_of_Type_String_Licensecomp()
            throws NoSuchFieldException, IllegalAccessException {
        comp.setContentType("testType");
        Field f = comp.getClass().getDeclaredField("mContentType");
        f.setAccessible(true);
        Assert.assertTrue(f.get(comp).equals("testType"));
    }

    @Test(groups = { "Getters" })
    void getWidth_should_return_width_of_Type_Integer_Licensecomp()
            throws NoSuchFieldException, IllegalAccessException {
        Field f = comp.getClass().getDeclaredField("mWidth");
        f.setAccessible(true);
        f.set(comp, 100);
        Assert.assertTrue(comp.getWidth().equals(100));
    }

    @Test(groups = { "Setters" })
    void setWidth_should_set_width_of_Type_Integer_Licensecomp()
            throws NoSuchFieldException, IllegalAccessException {
        comp.setWidth(100);
        Field f = comp.getClass().getDeclaredField("mWidth");
        f.setAccessible(true);
        Assert.assertTrue(f.get(comp).equals(100));
    }

    @Test(groups = { "Getters" })
    void getHeight_should_return_height_of_Type_Integer_Licensecomp()
            throws NoSuchFieldException, IllegalAccessException {
        Field f = comp.getClass().getDeclaredField("mHeight");
        f.setAccessible(true);
        f.set(comp, 200);
        Assert.assertTrue(comp.getHeight().equals(200));
    }

    @Test(groups = { "Setters" })
    void setHeight_should_set_height_of_Type_Integer_Licensecomp()
            throws NoSuchFieldException, IllegalAccessException {
        comp.setHeight(100);
        Field f = comp.getClass().getDeclaredField("mHeight");
        f.setAccessible(true);
        Assert.assertTrue(f.get(comp).equals(100));
    }

    @Test(groups = { "Getters" })
    void getContentLength_should_return_ContentLength_of_Type_Integer_Licensecomp()
            throws NoSuchFieldException, IllegalAccessException {
        Field f = comp.getClass().getDeclaredField("mContentLength");
        f.setAccessible(true);
        f.set(comp, 200);
        Assert.assertTrue(comp.getContentLength().equals(200));
    }

    @Test(groups = { "Setters" })
    void setContentLength_should_set_ContentLength_of_Type_Integer_Licensecomp()
            throws NoSuchFieldException, IllegalAccessException {
        comp.setContentLength(100);
        Field f = comp.getClass().getDeclaredField("mContentLength");
        f.setAccessible(true);
        Assert.assertTrue(f.get(comp).equals(100));
    }

    @Test(groups = { "Getters" })
    void getDuration_should_return_Duration_of_Type_Integer_Licensecomp()
            throws NoSuchFieldException, IllegalAccessException {
        Field f = comp.getClass().getDeclaredField("mDuration");
        f.setAccessible(true);
        f.set(comp, 200);
        Assert.assertTrue(comp.getDuration().equals(200));
    }

    @Test(groups = { "Setters" })
    void setDuration_should_set_Duration_of_Type_Integer_Licensecomp()
            throws NoSuchFieldException, IllegalAccessException {
        comp.setDuration(100);
        Field f = comp.getClass().getDeclaredField("mDuration");
        f.setAccessible(true);
        Assert.assertTrue(f.get(comp).equals(100));
    }

    @Test(groups = { "Getters" })
    void getFrameRate_should_return_FrameRate_of_Type_Double_Licensecomp()
            throws NoSuchFieldException, IllegalAccessException {
        Field f = comp.getClass().getDeclaredField("mFrameRate");
        f.setAccessible(true);
        f.set(comp, 200.1);
        Assert.assertTrue(comp.getFrameRate().equals(200.1));
    }

    @Test(groups = { "Setters" })
    void setFrameRate_should_set_FrameRate_of_Type_Double_Licensecomp()
            throws NoSuchFieldException, IllegalAccessException {
        comp.setFrameRate(100.1);
        Field f = comp.getClass().getDeclaredField("mFrameRate");
        f.setAccessible(true);
        Assert.assertTrue(f.get(comp).equals(100.1));
    }
}
