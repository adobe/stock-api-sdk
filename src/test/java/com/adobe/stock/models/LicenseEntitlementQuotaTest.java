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
@Test(suiteName = "LicenseEntitlementQuota")
public class LicenseEntitlementQuotaTest {
    private LicenseEntitlementQuota entitlementQuota;
    
    @BeforeSuite
    void creating_LicenseEntitlementQuota_InstanceSuccessfully() {
        entitlementQuota = new LicenseEntitlementQuota();
        Assert.assertNotNull(entitlementQuota);
    }
    
    @Test(groups = { "Getters" })
    void getImageQuota_should_return_imageQuota_of_Type_Integer_LicenseEntitlementQuota()
            throws NoSuchFieldException, IllegalAccessException {
        Field f = entitlementQuota.getClass().getDeclaredField("mImageQuota");
        f.setAccessible(true);
        f.set(entitlementQuota, 200);
        Assert.assertEquals(200, entitlementQuota.getImageQuota().intValue());
    }

    @Test(groups = { "Setters" })
    void setImageQuota_should_set_imageQuota_of_Type_Integer_LicenseEntitlementQuota()
            throws NoSuchFieldException, IllegalAccessException {
        entitlementQuota.setImageQuota(500);
        Field f = entitlementQuota.getClass().getDeclaredField("mImageQuota");
        f.setAccessible(true);
        Assert.assertEquals(500, f.get(entitlementQuota));
    }

    @Test(groups = { "Getters" })
    void getVideoQuota_should_return_videoQuota_of_Type_Integer_LicenseEntitlementQuota()
            throws NoSuchFieldException, IllegalAccessException {
        Field f = entitlementQuota.getClass().getDeclaredField("mVideoQuota");
        f.setAccessible(true);
        f.set(entitlementQuota, 200);
        Assert.assertEquals(200, entitlementQuota.getVideoQuota().intValue());
    }

    @Test(groups = { "Setters" })
    void setVideoQuota_should_set_videoQuota_of_Type_Integer_LicenseEntitlementQuota()
            throws NoSuchFieldException, IllegalAccessException {
        entitlementQuota.setVideoQuota(500);
        Field f = entitlementQuota.getClass().getDeclaredField("mVideoQuota");
        f.setAccessible(true);
        Assert.assertEquals(500, f.get(entitlementQuota));
    }

    @Test(groups = { "Getters" })
    void getCreditsQuota_should_return_creditsQuota_of_Type_Integer_LicenseEntitlementQuota()
            throws NoSuchFieldException, IllegalAccessException {
        Field f = entitlementQuota.getClass().getDeclaredField("mCreditsQuota");
        f.setAccessible(true);
        f.set(entitlementQuota, 200);
        Assert.assertEquals(200, entitlementQuota.getCreditsQuota().intValue());
    }

    @Test(groups = { "Setters" })
    void setCreditsQuota_should_set_creditsQuota_of_Type_Integer_LicenseEntitlementQuota()
            throws NoSuchFieldException, IllegalAccessException {
        entitlementQuota.setCreditsQuota(500);
        Field f = entitlementQuota.getClass().getDeclaredField("mCreditsQuota");
        f.setAccessible(true);
        Assert.assertEquals(500, f.get(entitlementQuota));
    }

    @Test(groups = { "Getters" })
    void getStandardCreditsQuota_should_return_StandardCreditsQuota_of_Type_Integer_LicenseEntitlementQuota()
            throws NoSuchFieldException, IllegalAccessException {
        Field f = entitlementQuota.getClass().getDeclaredField("mStandardCreditsQuota");
        f.setAccessible(true);
        f.set(entitlementQuota, 200);
        Assert.assertEquals(200, entitlementQuota.getStandardCreditsQuota().intValue());
    }

    @Test(groups = { "Setters" })
    void setStandardCreditsQuota_should_set_StandardCreditsQuota_of_Type_Integer_LicenseEntitlementQuota()
            throws NoSuchFieldException, IllegalAccessException {
        entitlementQuota.setStandardCreditsQuota(500);
        Field f = entitlementQuota.getClass().getDeclaredField("mStandardCreditsQuota");
        f.setAccessible(true);
        Assert.assertEquals(500, f.get(entitlementQuota));
    }

    @Test(groups = { "Getters" })
    void getPremiumCreditsQuota_should_return_PremiumCreditsQuota_of_Type_Integer_LicenseEntitlementQuota()
            throws NoSuchFieldException, IllegalAccessException {
        Field f = entitlementQuota.getClass().getDeclaredField("mPremiumCreditsQuota");
        f.setAccessible(true);
        f.set(entitlementQuota, 200);
        Assert.assertEquals(200, entitlementQuota.getPremiumCreditsQuota().intValue());
    }

    @Test(groups = { "Setters" })
    void setPremiumCreditsQuota_should_set_PremiumCreditsQuota_of_Type_Integer_LicenseEntitlementQuota()
            throws NoSuchFieldException, IllegalAccessException {
        entitlementQuota.setPremiumCreditsQuota(500);
        Field f = entitlementQuota.getClass().getDeclaredField("mPremiumCreditsQuota");
        f.setAccessible(true);
        Assert.assertEquals(500, f.get(entitlementQuota));
    }

    @Test(groups = { "Getters" })
    void getUniversalCreditsQuota_should_return_UniversalCreditsQuota_of_Type_Integer_LicenseEntitlementQuota()
            throws NoSuchFieldException, IllegalAccessException {
        Field f = entitlementQuota.getClass().getDeclaredField("mUniversalCreditsQuota");
        f.setAccessible(true);
        f.set(entitlementQuota, 200);
        Assert.assertEquals(200, entitlementQuota.getUniversalCreditsQuota().intValue());
    }

    @Test(groups = { "Setters" })
    void setUniversalCreditsQuota_should_set_UniversalCreditsQuota_of_Type_Integer_LicenseEntitlementQuota()
            throws NoSuchFieldException, IllegalAccessException {
        entitlementQuota.setUniversalCreditsQuota(500);
        Field f = entitlementQuota.getClass().getDeclaredField("mUniversalCreditsQuota");
        f.setAccessible(true);
        Assert.assertEquals(500, f.get(entitlementQuota));
    }
}
