/*******************************************************************************
 * Copyright 2017 Adobe Systems Incorporated. All rights reserved.
 * This file is licensed to you under the Apache License, Version 2.0
 * (the "License") you may not use this file except in compliance with the
 * License. You may obtain a copy of the License at
 * http://www.apache.org/licenses/LICENSE-2.0
 ******************************************************************************/
package com.adobe.stock.models;

import java.lang.reflect.Field;

import org.testng.Assert;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;

import com.adobe.stock.enums.LicenseHistoryResultColumn;
import com.adobe.stock.enums.LicenseHistoryThumbnailSize;

@Listeners({ com.adobe.stock.logger.TestCustomLogger.class,
    com.adobe.stock.logger.TestSuiteLogger.class })
@Test(suiteName = "LicenseHistoryRequest")
public class LicenseHistoryRequestTest {

    LicenseHistoryRequest request;
    
    @BeforeSuite
    void creating_LicenseHistoryRequest_InstanceSuccessfully() {
        request = new LicenseHistoryRequest();
        Assert.assertNotNull(request);
    }

    @Test(groups = { "Setters" })
    void setLocale_should_setValue_in_LocaleField_and_should_return_instanceof_LicenseHistoryRequest()
            throws IllegalAccessException, NoSuchFieldException {
        Assert.assertNotNull(request.setLocale("enUS"));
        Field f = request.getClass().getDeclaredField("mLocale");
        f.setAccessible(true);
        Assert.assertTrue(((String) f.get(request)).equals("enUS"));
    }

    @Test(groups = { "Getters" })
    void getLocale_should_return_StringValue_of_Field_Locale()
            throws NoSuchFieldException, IllegalAccessException {
        Assert.assertTrue(request.getLocale().equals(""));
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
    void setSearchParams_should_set_parameters_in_SearchParametersField_and_return_instanceOf_LicenseHistoryRequest()
            throws NoSuchFieldException, IllegalAccessException {
        SearchParametersLicenseHistory params = new SearchParametersLicenseHistory();
        params.setLimit(1).setOffset(3)
                .setThumbnailSize(LicenseHistoryThumbnailSize.BIG);
        Assert.assertNotNull(request.setSearchParams(params));
        Field f = request.getClass().getDeclaredField("mSearchParams");
        f.setAccessible(true);
        Assert.assertTrue(((SearchParametersLicenseHistory) f.get(request)).equals(params));
    }

    @Test(groups = { "Getters" })
    void getSearchParams_should_return_Object_of_Field_SearchParametersLicenseHistory()
            throws NoSuchFieldException, IllegalAccessException {
        Assert.assertTrue(request.getSearchParams() == null);
        SearchParametersLicenseHistory params = new SearchParametersLicenseHistory();
        params.setLimit(1).setOffset(3)
                .setThumbnailSize(LicenseHistoryThumbnailSize.BIG);
        Field f = request.getClass().getDeclaredField("mSearchParams");
        f.setAccessible(true);
        f.set(request, params);
        Assert.assertTrue(request.getSearchParams().equals(params));
    }

    @Test(groups = { "Exceptions" }, expectedExceptions = IllegalArgumentException.class, expectedExceptionsMessageRegExp = "Search Params cannot be null")
    void setSearchParams_should_throw_exceptions_if_nullValue_isSet_to_SearchParams() {
        request.setSearchParams(null);
    }

    @Test(groups = { "Setters" })
    void setLicenseHistoryResultColumns_should_set_what_fields_to_be_included_in_Result_and_return_instanceOf_LicenseHistoryRequest()
            throws NoSuchFieldException, IllegalAccessException {
        LicenseHistoryResultColumn[] result = { LicenseHistoryResultColumn.THUMBNAIL_1000_HEIGHT,
                LicenseHistoryResultColumn.THUMBNAIL_1000_URL };
        Assert.assertNotNull(request.setResultColumns(result));
        Field f = request.getClass().getDeclaredField("mResultColumns");
        f.setAccessible(true);
        result = (LicenseHistoryResultColumn[]) f.get(request);
    }

    @Test(groups = { "Getters" })
    void getLicenseHistoryResultColumns_should_return_Array_of_ResultColumns_ofTypeEnum()
            throws NoSuchFieldException, IllegalAccessException {
        Assert.assertTrue(request.getResultColumns() == null);
        LicenseHistoryResultColumn[] result = { LicenseHistoryResultColumn.THUMBNAIL_1000_HEIGHT,
                LicenseHistoryResultColumn.THUMBNAIL_1000_URL };
        Field f = request.getClass().getDeclaredField("mResultColumns");
        f.setAccessible(true);
        f.set(request, result);
        Assert.assertTrue(request.getResultColumns()[1].equals(result[1]));
    }

    @Test(groups = { "Exceptions" }, expectedExceptions = IllegalArgumentException.class, expectedExceptionsMessageRegExp = "Request Columns cannot be null")
    void setResultColumns_should_throw_exceptions_if_nullValue_isSet_to_ResultColumns() {
        request.setResultColumns(null);
    }
}
