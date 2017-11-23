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

import com.adobe.stock.enums.ResultColumn;
import com.adobe.stock.enums.AssetAge;

@Listeners({ com.adobe.stock.logger.TestCustomLogger.class,
        com.adobe.stock.logger.TestSuiteLogger.class })
@Test(suiteName = "SearchFilesRequest")
public class SearchFilesRequestTest {
    SearchFilesRequest request;

    @BeforeSuite
    void creating_SearchFilesRequest_InstanceSuccessfully() {
        request = new SearchFilesRequest();
        Assert.assertNotNull(request);
    }

    @Test(groups = { "Setters" })
    void setLocale_should_setValue_in_LocaleField_and_should_return_instanceof_SearchFilesRequest()
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
    void setSearchParams_should_set_parameters_in_SearchParametersField_and_return_instanceOf_SearchFilesRequest()
            throws NoSuchFieldException, IllegalAccessException {
        SearchParameters params = new SearchParameters();
        params.setCreatorId(1).setFilterAge(AssetAge.ALL)
                .setFilterContentTypeAll(true);
        Assert.assertNotNull(request.setSearchParams(params));
        Field f = request.getClass().getDeclaredField("mSearchParams");
        f.setAccessible(true);
        Assert.assertTrue(((SearchParameters) f.get(request)).equals(params));
    }

    @Test(groups = { "Getters" })
    void getSearchParams_should_return_Object_of_Field_SearchParameters()
            throws NoSuchFieldException, IllegalAccessException {
        Assert.assertTrue(request.getSearchParams() == null);
        SearchParameters params = new SearchParameters();
        params.setCreatorId(1).setFilterAge(AssetAge.ALL)
                .setFilterContentTypeAll(true);
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
    void setResultColumns_should_set_what_fields_to_be_included_in_Result_and_return_instanceOf_SearchFilesRequest()
            throws NoSuchFieldException, IllegalAccessException {
        ResultColumn[] result = { ResultColumn.COMPS,
                ResultColumn.CREATION_DATE };
        Assert.assertNotNull(request.setResultColumns(result));
        Field f = request.getClass().getDeclaredField("mResultColumns");
        f.setAccessible(true);
        result = (ResultColumn[]) f.get(request);
        // Assert.assertTrue(result[1].equals(ResultColumn.CREATION_DATE));
    }

    @Test(groups = { "Getters" })
    void getResultColumns_should_return_Array_of_ResultColumns_ofTypeEnum()
            throws NoSuchFieldException, IllegalAccessException {
        Assert.assertTrue(request.getResultColumns() == null);
        ResultColumn[] result = { ResultColumn.COMPS,
                ResultColumn.CREATION_DATE };
        Field f = request.getClass().getDeclaredField("mResultColumns");
        f.setAccessible(true);
        f.set(request, result);
        Assert.assertTrue(request.getResultColumns()[1].equals(result[1]));
    }

    @Test(groups = { "Exceptions" }, expectedExceptions = IllegalArgumentException.class, expectedExceptionsMessageRegExp = "Request Columns cannot be null")
    void setResultColumns_should_throw_exceptions_if_nullValue_isSet_to_ResultColumns() {
        request.setResultColumns(null);
    }

    @Test(groups = { "Setters" })
    void setSimilarImage_should_set_byteArray_to_search_SimilarImage()
            throws NoSuchFieldException, IllegalAccessException {
        String val = "This is a Test";
        Assert.assertNotNull(request.setSimilarImage(val.getBytes()));
        Field f = request.getClass().getDeclaredField("mSimilarImage");
        f.setAccessible(true);
        Assert.assertTrue(f.get(request) != null);
    }

    @Test(groups = { "Getters" })
    void getSimilarImage_should_return_byteArray_of_SimilarImage()
            throws NoSuchFieldException, IllegalAccessException {
        Assert.assertTrue(request.getSimilarImage() == null);
        String val = "This is a Test";
        Field f = request.getClass().getDeclaredField("mSimilarImage");
        f.setAccessible(true);
        f.set(request, val.getBytes());
        Assert.assertTrue(request.getSimilarImage() != null);
    }

    @Test(groups = { "Exceptions" }, expectedExceptions = IllegalArgumentException.class, expectedExceptionsMessageRegExp = "Should be a valid Image")
    void setSimilarImage_should_throw_exceptions_if_nullValue_isSet_insteadof_byteArray_in_SimilarImage() {
        request.setSimilarImage(null);
    }

}
