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
@Test(suiteName = "SearchCategoryRequest")
public class SearchCategoryRequestTest {
    SearchCategoryRequest request;
    
    @BeforeSuite
    void creating_SearchCategoryRequest_InstanceSuccessfully(){
        request = new SearchCategoryRequest();
        Assert.assertNotNull(request);
    }

    @Test(groups = { "Setters" })
    void setLocale_should_setValue_in_LocaleField_and_should_return_instanceof_SearchCategoryRequest()
            throws IllegalAccessException, NoSuchFieldException {
        Assert.assertNotNull(request.setLocale("enUS"));
        Field f = request.getClass().getDeclaredField("mLocale");
        f.setAccessible(true);
        Assert.assertTrue(((String) f.get(request)).equals("enUS"));
    }

    @Test(groups = { "Getters" })
    void getLocale_should_return_StringValue_of_Field_Locale()
            throws NoSuchFieldException, IllegalAccessException {
        Field f = request.getClass().getDeclaredField("mLocale");
        f.setAccessible(true);
        f.set(request, "enUS");
        Assert.assertEquals("enUS", request.getLocale());
    }

    @Test(groups = { "Exceptions" }, expectedExceptions = IllegalArgumentException.class, expectedExceptionsMessageRegExp = "Should be a valid locale")
    void setLocale_should_throw_exception_if_locale_field_is_set_with_EmptyValue() {
        request.setLocale("");
    }

    @Test(groups = { "Exceptions" }, expectedExceptions = IllegalArgumentException.class, expectedExceptionsMessageRegExp = "Should be a valid locale")
    void setLocale_should_throw_exception_if_locale_field_is_set_with_NullValue() {
        request.setLocale(null);
    }

    @Test(groups = { "Setters" })
    void setCategoryId_should_setValue_in_CategoryIdField_and_should_return_instanceof_SearchCategoryRequest()
            throws IllegalAccessException, NoSuchFieldException {
        Assert.assertNotNull(request.setCategoryId(1043));
        Field f = request.getClass().getDeclaredField("mCategoryId");
        f.setAccessible(true);
        Assert.assertTrue((f.get(request)).equals(1043));
    }

    @Test(groups = { "Getters" })
    void getCategoryId_should_return_IntegerValue_of_Field_CategoryId()
            throws NoSuchFieldException, IllegalAccessException {
        Field f = request.getClass().getDeclaredField("mCategoryId");
        f.setAccessible(true);
        f.set(request, 1231);
        Assert.assertEquals(1231, request.getCategoryId().intValue());
    }

    @Test(groups = { "Exceptions" }, expectedExceptions = IllegalArgumentException.class, expectedExceptionsMessageRegExp = "Should be a valid category id")
    void setCategoryId_should_throw_exception_if_categoryId_field_is_set_with_negative_value() {
        request.setCategoryId(-2);
    }

}
