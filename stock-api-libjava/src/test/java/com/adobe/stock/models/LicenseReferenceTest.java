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
@Test(suiteName = "LicenseReference")
public class LicenseReferenceTest {
    LicenseReference licenseReference;

    @BeforeSuite
    void creating_LicenseReference_InstanceSuccessfully() {
        licenseReference = new LicenseReference();
        Assert.assertNotNull(licenseReference);
    }

    @Test(groups = { "Setters" })
    void setLicenseReferenceId_should_set_license_referenceId_and_should_return_instanceof_LicenseReference()
            throws IllegalAccessException, NoSuchFieldException {
        Assert.assertNotNull(licenseReference.setLicenseReferenceId(1));
        Field f = licenseReference.getClass().getDeclaredField("mId");
        f.setAccessible(true);
        Assert.assertEquals(1, f.get(licenseReference));
    }

    @Test(groups = { "Getters" })
    void getLicenseReferenceId_should_return_Integer_Value_of_license_referenceId()
            throws NoSuchFieldException, IllegalAccessException {
        Assert.assertTrue(licenseReference.getLicenseReferenceId() == null);
        Field f = licenseReference.getClass().getDeclaredField("mId");
        f.setAccessible(true);
        f.set(licenseReference, 2);
        Assert.assertEquals(2, licenseReference.getLicenseReferenceId().intValue());
    }
    @Test(groups = { "Exceptions" }, expectedExceptions = IllegalArgumentException.class, expectedExceptionsMessageRegExp = "Should be a valid license reference id")
    void setLicenseReferenceId_should_throw_exception_if_license_reference_id_is_set_with_Negative_value() {
        licenseReference.setLicenseReferenceId(-1);
    }
    @Test(groups = { "Setters" })
    void setLicenseReferenceValue_should_set_license_reference_Value_and_should_return_instanceof_LicenseReference()
            throws IllegalAccessException, NoSuchFieldException {
        Assert.assertNotNull(licenseReference.setLicenseReferenceValue("Some Value"));
        Field f = licenseReference.getClass().getDeclaredField("mValue");
        f.setAccessible(true);
        Assert.assertEquals("Some Value", f.get(licenseReference));
    }

    @Test(groups = { "Getters" })
    void getLicenseReferenceValue_should_return_String_Value_of_license_reference()
            throws NoSuchFieldException, IllegalAccessException {
        Assert.assertTrue(licenseReference.getLicenseReferenceValue() == null);
        Field f = licenseReference.getClass().getDeclaredField("mValue");
        f.setAccessible(true);
        f.set(licenseReference, "Some Value");
        Assert.assertEquals("Some Value", licenseReference.getLicenseReferenceValue().toString());
    }
    @Test(groups = { "Exceptions" }, expectedExceptions = IllegalArgumentException.class, expectedExceptionsMessageRegExp = "License reference value cannot be null or empty")
    void setLicenseReferenceValue_should_throw_exception_if_license_reference_value_is_set_to_null() {
        licenseReference.setLicenseReferenceValue(null);
    }
    @Test(groups = { "Exceptions" }, expectedExceptions = IllegalArgumentException.class, expectedExceptionsMessageRegExp = "License reference value cannot be null or empty")
    void setLicenseReferenceValue_should_throw_exception_if_license_reference_value_is_set_to_empty() {
        licenseReference.setLicenseReferenceValue("");
    }

}
