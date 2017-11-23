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

import com.adobe.stock.apis.License;
import com.adobe.stock.config.StockConfig;
import com.adobe.stock.enums.AssetLicenseState;
import com.adobe.stock.enums.AssetPurchaseState;

@Listeners({ com.adobe.stock.logger.TestCustomLogger.class,
    com.adobe.stock.logger.TestSuiteLogger.class })
@Test(suiteName = "LicenseRequest")
public class LicenseRequestTest {
    LicenseRequest licenseRequest;

    @BeforeSuite
    void creating_LicenseReference_InstanceSuccessfully() {
        licenseRequest = new LicenseRequest();
        Assert.assertNotNull(licenseRequest);
    }
    @Test(groups = { "Setters" })
    void setContentId_should_set_Assets_unique_identifer_and_should_return_instanceof_LicenseRequest()
            throws IllegalAccessException, NoSuchFieldException {
        Assert.assertNotNull(licenseRequest.setContentId(1));
        Field f = licenseRequest.getClass().getDeclaredField("mContentId");
        f.setAccessible(true);
        Assert.assertEquals(1, f.get(licenseRequest));
    }

    @Test(groups = { "Getters" })
    void getContentId_should_set_Assets_unique_identifer()
            throws NoSuchFieldException, IllegalAccessException {
        Assert.assertTrue(licenseRequest.getContentId() == null);
        Field f = licenseRequest.getClass().getDeclaredField("mContentId");
        f.setAccessible(true);
        f.set(licenseRequest, 1);
        Assert.assertEquals(1, licenseRequest.getContentId().intValue());
    }
    @Test(groups = { "Exceptions" }, expectedExceptions = IllegalArgumentException.class, expectedExceptionsMessageRegExp = "Should be a valid Content ID")
    void setContentId_should_throw_exception_if_Assets_unique_identifer_is_set_with_Negative_value() {
        licenseRequest.setContentId(-1);
    }
    @Test(groups = { "Setters" })
    void setLocale_should_setValue_in_LocaleField_and_should_return_instanceof_LicenseRequest()
            throws IllegalAccessException, NoSuchFieldException {
        Assert.assertNotNull(licenseRequest.setLocale("enUS"));
        Field f = licenseRequest.getClass().getDeclaredField("mLocale");
        f.setAccessible(true);
        Assert.assertTrue(((String) f.get(licenseRequest)).equals("enUS"));
    }

    @Test(groups = { "Getters" })
    void getLocale_should_return_StringValue_of_Field_Locale()
            throws NoSuchFieldException, IllegalAccessException {
        Field f = licenseRequest.getClass().getDeclaredField("mLocale");
        f.setAccessible(true);
        f.set(licenseRequest, "enUS");
        Assert.assertEquals("enUS", licenseRequest.getLocale());
    }

    @Test(groups = { "Exceptions" }, expectedExceptions = IllegalArgumentException.class, expectedExceptionsMessageRegExp = "Locale cannot be null or empty")
    void setLocale_should_throw_exception_if_locale_field_is_set_with_EmptyValue() {
        licenseRequest.setLocale("");
    }

    @Test(groups = { "Exceptions" }, expectedExceptions = IllegalArgumentException.class, expectedExceptionsMessageRegExp = "Locale cannot be null or empty")
    void setLocale_should_throw_exception_if_locale_field_is_set_with_NullValue() {
        licenseRequest.setLocale(null);
    }
    @Test(groups = { "Setters" })
    void setFormat_should_setValue_message_ccx_in_format_field_if_true_is_passed_and_should_return_instanceof_LicenseRequest()
            throws IllegalAccessException, NoSuchFieldException {
        Assert.assertNotNull(licenseRequest.setFormat(true));
        Field f = licenseRequest.getClass().getDeclaredField("mFormat");
        f.setAccessible(true);
        Assert.assertTrue((f.get(licenseRequest)).equals("message_ccx"));
    }
    @Test(groups = { "Setters" })
    void setFormat_should_set_null_in_format_field__if_false_is_passedand_should_return_instanceof_LicenseRequest()
            throws IllegalAccessException, NoSuchFieldException {
        Assert.assertNotNull(licenseRequest.setFormat(false));
        Field f = licenseRequest.getClass().getDeclaredField("mFormat");
        f.setAccessible(true);
        Assert.assertEquals(null, f.get(licenseRequest));
    }
    @Test(groups = { "Getters" })
    void getFormat_should_return_whether_format_is_set_to_message_ccx_or_not()
            throws NoSuchFieldException, IllegalAccessException {
        Field f = licenseRequest.getClass().getDeclaredField("mFormat");
        f.setAccessible(true);
        f.set(licenseRequest, "message_ccx");
        Assert.assertEquals(true, licenseRequest.getFormat());
    }

    @Test(groups = { "Exceptions" }, expectedExceptions = IllegalArgumentException.class, expectedExceptionsMessageRegExp = "Format can be set to true or false only.")
    void setFormat_should_throw_exception_if_null_value_is_passed() {
        licenseRequest.setFormat(null);
    }
    @Test(groups = { "Setters" })
    void setLicenseState_should_set_licensing_state_of_asset_and_should_return_instanceof_LicenseRequest()
            throws IllegalAccessException, NoSuchFieldException {
        Assert.assertNotNull(licenseRequest.setLicenseState(AssetLicenseState.VIDEO_4K));
        Field f = licenseRequest.getClass().getDeclaredField("mLicenseState");
        f.setAccessible(true);
        Assert.assertTrue((f.get(licenseRequest)).equals(AssetLicenseState.VIDEO_4K));
    }

    @Test(groups = { "Getters" })
    void getLicenseState_should_return_licensing_state_of_asset()
            throws NoSuchFieldException, IllegalAccessException {
        Field f = licenseRequest.getClass().getDeclaredField("mLicenseState");
        f.setAccessible(true);
        f.set(licenseRequest, AssetLicenseState.VIDEO_4K);
        Assert.assertEquals(AssetLicenseState.VIDEO_4K, licenseRequest.getLicenseState());
    }

    @Test(groups = { "Exceptions" }, expectedExceptions = IllegalArgumentException.class, expectedExceptionsMessageRegExp = "License State cannot be null")
    void setLicenseState_should_throw_exception_if_null_value_is_passed() {
        licenseRequest.setLicenseState(null);
    }
    @Test(groups = { "Setters" })
    void setPurchaseState_should_set_purchase_state_of_asset_and_should_return_instanceof_LicenseRequest()
            throws IllegalAccessException, NoSuchFieldException {
        Assert.assertNotNull(licenseRequest.setPurchaseState(AssetPurchaseState.JUST_PURCHASED));
        Field f = licenseRequest.getClass().getDeclaredField("mPurchaseState");
        f.setAccessible(true);
        Assert.assertTrue((f.get(licenseRequest)).equals(AssetPurchaseState.JUST_PURCHASED));
    }

    @Test(groups = { "Getters" })
    void getPurchaseState_should_return_purchase_state_of_asset()
            throws NoSuchFieldException, IllegalAccessException {
        Field f = licenseRequest.getClass().getDeclaredField("mPurchaseState");
        f.setAccessible(true);
        f.set(licenseRequest, AssetPurchaseState.NOT_POSSIBLE);
        Assert.assertEquals(AssetPurchaseState.NOT_POSSIBLE, licenseRequest.getPurchaseState());
    }

    @Test(groups = { "Exceptions" }, expectedExceptions = IllegalArgumentException.class, expectedExceptionsMessageRegExp = "Purchase state cannot be null")
    void setPurchaseState_should_throw_exception_if_null_value_is_passed() {
        licenseRequest.setPurchaseState(null);
    }
    @Test(groups = { "Setters" })
    void setLicenseReference_should_set_licensing_reference_of_asset_and_should_return_instanceof_LicenseRequest()
            throws IllegalAccessException, NoSuchFieldException {
        LicenseReference ref = new LicenseReference();
        ref.setLicenseReferenceId(100).setLicenseReferenceValue("Some Value");
        LicenseReference[] refArray = {ref}; 
        Assert.assertNotNull(licenseRequest.setLicenseReference(refArray));
        Field f = licenseRequest.getClass().getDeclaredField("mLicenseReference");
        f.setAccessible(true);
        LicenseReference[] newRef = (LicenseReference[])(f.get(licenseRequest));
        Assert.assertTrue(newRef[0].equals(refArray[0]));
    }

    @Test(groups = { "Getters" })
    void getLicenseReference_should_return_license_Reference_of_asset_in_the_form_id_and_value()
            throws NoSuchFieldException, IllegalAccessException {
        LicenseReference ref = new LicenseReference();
        ref.setLicenseReferenceId(100).setLicenseReferenceValue("Some Value");
        LicenseReference[] refArray = {ref}; 
        Field f = licenseRequest.getClass().getDeclaredField("mLicenseReference");
        f.setAccessible(true);
        f.set(licenseRequest, refArray);
        Assert.assertEquals(refArray[0], licenseRequest.getLicenseReference()[0]);
    }

    @Test(groups = { "Exceptions" }, expectedExceptions = IllegalArgumentException.class, expectedExceptionsMessageRegExp = "LicenseReference array cannot be null")
    void setLicenseReference_should_throw_exception_if_null_value_is_passed() throws IllegalArgumentException{
        licenseRequest.setLicenseReference(null);
    }

}

