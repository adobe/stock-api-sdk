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

import com.adobe.stock.enums.AssetLicenseSize;

@Listeners({ com.adobe.stock.logger.TestCustomLogger.class,
    com.adobe.stock.logger.TestSuiteLogger.class })
@Test(suiteName = "LicenseContent")
public class LicenseContentTest {
    private LicenseContent content;
    
    @BeforeSuite
    void creating_LicenseContent_InstanceSuccessfully() {
        content = new LicenseContent();
        Assert.assertNotNull(content);
    }
    
    @Test(groups = { "Getters" })
    void getContentId_should_return_ContentId_of_Type_String_LicenseContent()
            throws NoSuchFieldException, IllegalAccessException {
        Field f = content.getClass().getDeclaredField("mContentId");
        f.setAccessible(true);
        f.set(content, "test");
        Assert.assertTrue(content.getContentId().equals("test"));
    }

    @Test(groups = { "Setters" })
    void setContentId_should_set_ContentId_of_Type_String_LicenseContent()
            throws NoSuchFieldException, IllegalAccessException {
        content.setContentId("test");
        Field f = content.getClass().getDeclaredField("mContentId");
        f.setAccessible(true);
        Assert.assertTrue(f.get(content).equals("test"));
    }
    
    @Test(groups = { "Getters" })
    void getPurchaseDetails_should_return_PurchaseDetails_of_Type_LicensePurchaseDetails_LicenseContent()
            throws NoSuchFieldException, IllegalAccessException {
        Field f = content.getClass().getDeclaredField("mPurchaseDetails");
        f.setAccessible(true);
        LicensePurchaseDetails details = new LicensePurchaseDetails();
        details.setUrl("testUrl");
        f.set(content, details);
        Assert.assertTrue(content.getPurchaseDetails().getUrl().equals("testUrl"));
    }

    @Test(groups = { "Setters" })
    void setPurchaseDetails_should_set_PurchaseDetails_of_Type_LicensePurchaseDetails_LicenseContent()
            throws NoSuchFieldException, IllegalAccessException {
        LicensePurchaseDetails details = new LicensePurchaseDetails();
        details.setUrl("testUrl");
        content.setPurchaseDetails(details);
        Field f = content.getClass().getDeclaredField("mPurchaseDetails");
        f.setAccessible(true);
        details = (LicensePurchaseDetails) f.get(content);
        Assert.assertTrue(details.getUrl().equals("testUrl"));
    }
    
    @Test(groups = { "Getters" })
    void getSize_should_return_Size_of_Type_AssetLicenseSize_LicenseContent()
            throws NoSuchFieldException, IllegalAccessException {
        Field f = content.getClass().getDeclaredField("mSize");
        f.setAccessible(true);
        f.set(content, AssetLicenseSize.ORIGINAL);
        Assert.assertTrue(content.getSize().equals(AssetLicenseSize.ORIGINAL));
    }

    @Test(groups = { "Setters" })
    void setSize_should_set_Size_of_Type_AssetLicenseSize_LicenseContent()
            throws NoSuchFieldException, IllegalAccessException {
        content.setSize(AssetLicenseSize.COMPLEMENTARY);
        Field f = content.getClass().getDeclaredField("mSize");
        f.setAccessible(true);
        Assert.assertTrue(f.get(content).equals(AssetLicenseSize.COMPLEMENTARY));
    }

    @Test(groups = { "Getters" })
    void getComp_should_return_comp_of_Type_LicenseComp_LicenseContent()
            throws NoSuchFieldException, IllegalAccessException {
        Field f = content.getClass().getDeclaredField("mComp");
        f.setAccessible(true);
        LicenseComp comp  = new LicenseComp();
        comp.setUrl("testUrl");
        f.set(content, comp);
        Assert.assertTrue(content.getComp().getUrl().equals("testUrl"));
    }

    @Test(groups = { "Setters" })
    void setComp_should_set_comp_of_Type_LicenseComp_LicenseContent()
            throws NoSuchFieldException, IllegalAccessException {
        LicenseComp comp  = new LicenseComp();
        comp.setUrl("testUrl");
        content.setComp(comp);
        Field f = content.getClass().getDeclaredField("mComp");
        f.setAccessible(true);
        comp = (LicenseComp) f.get(content);
        Assert.assertTrue(comp.getUrl().equals("testUrl"));
    }

    @Test(groups = { "Getters" })
    void getThumbnail_should_return_thumbnail_of_Type_LicenseThumbnail_LicenseContent()
            throws NoSuchFieldException, IllegalAccessException {
        Field f = content.getClass().getDeclaredField("mThumbnail");
        f.setAccessible(true);
        LicenseThumbnail thumb = new LicenseThumbnail();
        thumb.setUrl("testUrl");
        f.set(content, thumb);
        Assert.assertTrue(content.getThumbnail().getUrl().equals("testUrl"));
    }

    @Test(groups = { "Setters" })
    void setThumbnail_should_set_thumbnail_of_Type_LicenseThumbnail_LicenseContent()
            throws NoSuchFieldException, IllegalAccessException {
        LicenseThumbnail thumb = new LicenseThumbnail();
        thumb.setUrl("testUrl");
        content.setThumbnail(thumb);
        Field f = content.getClass().getDeclaredField("mThumbnail");
        f.setAccessible(true);
        thumb = (LicenseThumbnail) f.get(content);
        Assert.assertTrue(thumb.getUrl().equals("testUrl"));
    }
}
