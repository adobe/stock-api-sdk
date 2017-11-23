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
@Test(suiteName = "LicenseThumbnail")
public class LicenseThumbnailTest {
    private LicenseThumbnail thumbnail;
    
    @BeforeSuite
    void creating_LicenseThumbnail_InstanceSuccessfully() {
        thumbnail = new LicenseThumbnail();
        Assert.assertNotNull(thumbnail);
    }

    @Test(groups = { "Getters" })
    void getUrl_should_return_url_of_Type_String_LicenseThumbnail()
            throws NoSuchFieldException, IllegalAccessException {
        Field f = thumbnail.getClass().getDeclaredField("mUrl");
        f.setAccessible(true);
        f.set(thumbnail, "testUrl");
        Assert.assertTrue(thumbnail.getUrl().equals("testUrl"));
    }

    @Test(groups = { "Setters" })
    void setUrl_should_set_url_of_Type_String_LicenseThumbnail()
            throws NoSuchFieldException, IllegalAccessException {
        thumbnail.setUrl("testUrl");
        Field f = thumbnail.getClass().getDeclaredField("mUrl");
        f.setAccessible(true);
        Assert.assertTrue(f.get(thumbnail).equals("testUrl"));
    }

    @Test(groups = { "Getters" })
    void getContentType_should_return_ContentType_of_Type_String_LicenseThumbnail()
            throws NoSuchFieldException, IllegalAccessException {
        Field f = thumbnail.getClass().getDeclaredField("mContentType");
        f.setAccessible(true);
        f.set(thumbnail, "testType");
        Assert.assertTrue(thumbnail.getContentType().equals("testType"));
    }

    @Test(groups = { "Setters" })
    void setContentType_should_set_ContentType_of_Type_String_LicenseThumbnail()
            throws NoSuchFieldException, IllegalAccessException {
        thumbnail.setContentType("testType");
        Field f = thumbnail.getClass().getDeclaredField("mContentType");
        f.setAccessible(true);
        Assert.assertTrue(f.get(thumbnail).equals("testType"));
    }

    @Test(groups = { "Getters" })
    void getWidth_should_return_width_of_Type_Integer_LicenseThumbnail()
            throws NoSuchFieldException, IllegalAccessException {
        Field f = thumbnail.getClass().getDeclaredField("mWidth");
        f.setAccessible(true);
        f.set(thumbnail, 100);
        Assert.assertTrue(thumbnail.getWidth().equals(100));
    }

    @Test(groups = { "Setters" })
    void setWidth_should_set_width_of_Type_Integer_LicenseThumbnail()
            throws NoSuchFieldException, IllegalAccessException {
        thumbnail.setWidth(100);
        Field f = thumbnail.getClass().getDeclaredField("mWidth");
        f.setAccessible(true);
        Assert.assertTrue(f.get(thumbnail).equals(100));
    }

    @Test(groups = { "Getters" })
    void getHeight_should_return_height_of_Type_Integer_LicenseThumbnail()
            throws NoSuchFieldException, IllegalAccessException {
        Field f = thumbnail.getClass().getDeclaredField("mHeight");
        f.setAccessible(true);
        f.set(thumbnail, 200);
        Assert.assertTrue(thumbnail.getHeight().equals(200));
    }

    @Test(groups = { "Setters" })
    void setHeight_should_set_height_of_Type_Integer_LicenseThumbnail()
            throws NoSuchFieldException, IllegalAccessException {
        thumbnail.setHeight(100);
        Field f = thumbnail.getClass().getDeclaredField("mHeight");
        f.setAccessible(true);
        Assert.assertTrue(f.get(thumbnail).equals(100));
    }
}
