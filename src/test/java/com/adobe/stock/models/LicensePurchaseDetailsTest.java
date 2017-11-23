/*******************************************************************************
 * Copyright 2017 Adobe Systems Incorporated. All rights reserved.
 * This file is licensed to you under the Apache License, Version 2.0
 * (the "License") you may not use this file except in compliance with the
 * License. You may obtain a copy of the License at
 * http://www.apache.org/licenses/LICENSE-2.0
 *******************************************************************************/
package com.adobe.stock.models;

import java.lang.reflect.Field;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.testng.Assert;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;

import com.adobe.stock.enums.AssetLicenseState;
import com.adobe.stock.enums.AssetPurchaseState;
import com.adobe.stock.exception.StockException;

@Listeners({ com.adobe.stock.logger.TestCustomLogger.class,
    com.adobe.stock.logger.TestSuiteLogger.class })
@Test(suiteName = "LicensePurchaseDetails")
public class LicensePurchaseDetailsTest {
    private LicensePurchaseDetails purchaseDetails;
    @BeforeSuite
    void creating_purchaseDetails_InstanceSuccessfully() {
        purchaseDetails = new LicensePurchaseDetails();
        Assert.assertNotNull(purchaseDetails);
    }

    @Test(groups = { "Getters" })
    void getUrl_should_return_url_of_Type_String_LicensePurchaseDetails()
            throws NoSuchFieldException, IllegalAccessException {
        Field f = purchaseDetails.getClass().getDeclaredField("mUrl");
        f.setAccessible(true);
        f.set(purchaseDetails, "testUrl");
        Assert.assertTrue(purchaseDetails.getUrl().equals("testUrl"));
    }

    @Test(groups = { "Setters" })
    void setUrl_should_set_url_of_Type_String_LicensePurchaseDetails()
            throws NoSuchFieldException, IllegalAccessException {
        purchaseDetails.setUrl("testUrl");
        Field f = purchaseDetails.getClass().getDeclaredField("mUrl");
        f.setAccessible(true);
        Assert.assertTrue(f.get(purchaseDetails).equals("testUrl"));
    }
    
    @Test(groups = { "Getters" })
    void getPurchaseState_should_return_PurchaseState_of_Type_AssetPurchaseState_LicensePurchaseDetails()
            throws NoSuchFieldException, IllegalAccessException {
        Field f = purchaseDetails.getClass().getDeclaredField("mPurchaseState");
        f.setAccessible(true);
        f.set(purchaseDetails, AssetPurchaseState.CANCELLED);
        Assert.assertEquals(AssetPurchaseState.CANCELLED, purchaseDetails.getPurchaseState());
    }

    @Test(groups = { "Setters" })
    void setPurchaseState_should_set_PurchaseState_of_Type_AssetPurchaseState_LicensePurchaseDetails()
            throws NoSuchFieldException, IllegalAccessException {
        purchaseDetails.setPurchaseState(AssetPurchaseState.JUST_PURCHASED);
        Field f = purchaseDetails.getClass().getDeclaredField("mPurchaseState");
        f.setAccessible(true);
        Assert.assertEquals(AssetPurchaseState.JUST_PURCHASED, f.get(purchaseDetails));
    }
    
    @Test(groups = { "Getters" })
    void getLicenseState_should_return_LicenseState_of_Type_Integer_LicensePurchaseDetails()
            throws NoSuchFieldException, IllegalAccessException {
        Field f = purchaseDetails.getClass().getDeclaredField("mLicenseState");
        f.setAccessible(true);
        f.set(purchaseDetails, AssetLicenseState.STANDARD);
        Assert.assertEquals(AssetLicenseState.STANDARD, purchaseDetails.getLicenseState());
    }

    @Test(groups = { "Setters" })
    void setLicenseState_should_set_LicenseState_of_Type_AssetLicenseState_LicensePurchaseDetails()
            throws NoSuchFieldException, IllegalAccessException {
        purchaseDetails.setLicenseState(AssetLicenseState.EXTENDED);
        Field f = purchaseDetails.getClass().getDeclaredField("mLicenseState");
        f.setAccessible(true);
        Assert.assertEquals(AssetLicenseState.EXTENDED, f.get(purchaseDetails));
    }
    
    @Test(groups = { "Getters" })
    void getDate_should_return_date_of_Type_Date_LicensePurchaseDetails()
            throws NoSuchFieldException, IllegalAccessException {
        Field f = purchaseDetails.getClass().getDeclaredField("mDate");
        f.setAccessible(true);
        Date date = new Date();
        f.set(purchaseDetails, date);
        Assert.assertTrue(purchaseDetails.getDate().equals(date));
    }

    @Test(groups = { "Setters" })
    void setDate_should_set_date_of_Type_Date_LicensePurchaseDetails()
            throws NoSuchFieldException, IllegalAccessException {
        String dateString = "2017-06-18 05:57:21.246303";
        SimpleDateFormat format = new SimpleDateFormat(
                "yyyy-MM-dd hh:mm:ss.SSS");
        Date date = null;
        try {
            date = format.parse(dateString);
            purchaseDetails.setDate(dateString);
        } catch (Exception e) {
            Assert.fail("Didn't expect the Exception!");
        }
        Field f = purchaseDetails.getClass().getDeclaredField("mDate");
        f.setAccessible(true);
        Assert.assertTrue(f.get(purchaseDetails).equals(date));
    }

    @Test(groups = { "Setters" }, expectedExceptions = {
            StockException.class }, expectedExceptionsMessageRegExp = "Could not parse the date string")
    void setDate_should_throw_exception_date_string_not_valid_LicensePurchaseDetails()
            throws Exception {
       String dateString = "2017-06-";
       purchaseDetails.setDate(dateString);
    }
    
    @Test(groups = { "Getters" })
    void getCancelledDate_should_return_CancelledDate_of_Type_Date_LicensePurchaseDetails()
            throws NoSuchFieldException, IllegalAccessException {
        Field f = purchaseDetails.getClass().getDeclaredField("mCancelledDate");
        f.setAccessible(true);
        Date date = new Date();
        f.set(purchaseDetails, date);
        Assert.assertTrue(purchaseDetails.getCancelledDate().equals(date));
    }

    @Test(groups = { "Setters" })
    void setCancelledDate_should_set_CancelledDate_of_Type_Date_LicensePurchaseDetails()
            throws NoSuchFieldException, IllegalAccessException {
        String dateString = "2017-06-18 05:57:21.246303";
        SimpleDateFormat format = new SimpleDateFormat(
                "yyyy-MM-dd hh:mm:ss.SSS");
        Date date = null;
        try {
            date = format.parse(dateString);
            purchaseDetails.setCancelledDate(dateString);
        } catch (Exception e) {
            Assert.fail("Didn't expect the Exception!");
        }
        Field f = purchaseDetails.getClass().getDeclaredField("mCancelledDate");
        f.setAccessible(true);
        Assert.assertTrue(f.get(purchaseDetails).equals(date));

        dateString = "2017-06-18 05:57:21";
        format = new SimpleDateFormat(
                "yyyy-MM-dd hh:mm:ss");
        date = null;
        try {
            date = format.parse(dateString);
            purchaseDetails.setCancelledDate(dateString);
        } catch (Exception e) {
            Assert.fail("Didn't expect the Exception!");
        }
        Assert.assertTrue(f.get(purchaseDetails).equals(date));
    }

    @Test(groups = { "Setters" }, expectedExceptions = {
            StockException.class }, expectedExceptionsMessageRegExp = "Could not parse the date string")
    void setCancelledDate_should_throw_exception_date_string_not_valid_LicensePurchaseDetails()
            throws Exception {
       String dateString = "2017-06-";
       purchaseDetails.setCancelledDate(dateString);
    }


    @Test(groups = { "Getters" })
    void getContentType_should_return_ContentType_of_Type_String_LicensePurchaseDetails()
            throws NoSuchFieldException, IllegalAccessException {
        Field f = purchaseDetails.getClass().getDeclaredField("mContentType");
        f.setAccessible(true);
        f.set(purchaseDetails, "testType");
        Assert.assertTrue(purchaseDetails.getContentType().equals("testType"));
    }

    @Test(groups = { "Setters" })
    void setContentType_should_set_ContentType_of_Type_String_LicensePurchaseDetails()
            throws NoSuchFieldException, IllegalAccessException {
        purchaseDetails.setContentType("testType");
        Field f = purchaseDetails.getClass().getDeclaredField("mContentType");
        f.setAccessible(true);
        Assert.assertTrue(f.get(purchaseDetails).equals("testType"));
    }

    @Test(groups = { "Getters" })
    void getWidth_should_return_width_of_Type_Integer_LicensePurchaseDetails()
            throws NoSuchFieldException, IllegalAccessException {
        Field f = purchaseDetails.getClass().getDeclaredField("mWidth");
        f.setAccessible(true);
        f.set(purchaseDetails, 100);
        Assert.assertTrue(purchaseDetails.getWidth().equals(100));
    }

    @Test(groups = { "Setters" })
    void setWidth_should_set_width_of_Type_Integer_LicensePurchaseDetails()
            throws NoSuchFieldException, IllegalAccessException {
        purchaseDetails.setWidth(100);
        Field f = purchaseDetails.getClass().getDeclaredField("mWidth");
        f.setAccessible(true);
        Assert.assertTrue(f.get(purchaseDetails).equals(100));
    }

    @Test(groups = { "Getters" })
    void getHeight_should_return_height_of_Type_Integer_LicensePurchaseDetails()
            throws NoSuchFieldException, IllegalAccessException {
        Field f = purchaseDetails.getClass().getDeclaredField("mHeight");
        f.setAccessible(true);
        f.set(purchaseDetails, 200);
        Assert.assertTrue(purchaseDetails.getHeight().equals(200));
    }

    @Test(groups = { "Setters" })
    void setHeight_should_set_height_of_Type_Integer_LicensePurchaseDetails()
            throws NoSuchFieldException, IllegalAccessException {
        purchaseDetails.setHeight(100);
        Field f = purchaseDetails.getClass().getDeclaredField("mHeight");
        f.setAccessible(true);
        Assert.assertTrue(f.get(purchaseDetails).equals(100));
    }

    @Test(groups = { "Getters" })
    void getContentLength_should_return_ContentLength_of_Type_Integer_LicensePurchaseDetails()
            throws NoSuchFieldException, IllegalAccessException {
        Field f = purchaseDetails.getClass().getDeclaredField("mContentLength");
        f.setAccessible(true);
        f.set(purchaseDetails, 200);
        Assert.assertTrue(purchaseDetails.getContentLength().equals(200));
    }

    @Test(groups = { "Setters" })
    void setContentLength_should_set_ContentLength_of_Type_Integer_LicensePurchaseDetails()
            throws NoSuchFieldException, IllegalAccessException {
        purchaseDetails.setContentLength(100);
        Field f = purchaseDetails.getClass().getDeclaredField("mContentLength");
        f.setAccessible(true);
        Assert.assertTrue(f.get(purchaseDetails).equals(100));
    }

    @Test(groups = { "Getters" })
    void getDuration_should_return_Duration_of_Type_Integer_LicensePurchaseDetails()
            throws NoSuchFieldException, IllegalAccessException {
        Field f = purchaseDetails.getClass().getDeclaredField("mDuration");
        f.setAccessible(true);
        f.set(purchaseDetails, 200);
        Assert.assertTrue(purchaseDetails.getDuration().equals(200));
    }

    @Test(groups = { "Setters" })
    void setDuration_should_set_Duration_of_Type_Integer_LicensePurchaseDetails()
            throws NoSuchFieldException, IllegalAccessException {
        purchaseDetails.setDuration(100);
        Field f = purchaseDetails.getClass().getDeclaredField("mDuration");
        f.setAccessible(true);
        Assert.assertTrue(f.get(purchaseDetails).equals(100));
    }

    @Test(groups = { "Getters" })
    void getFrameRate_should_return_FrameRate_of_Type_Double_LicensePurchaseDetails()
            throws NoSuchFieldException, IllegalAccessException {
        Field f = purchaseDetails.getClass().getDeclaredField("mFrameRate");
        f.setAccessible(true);
        f.set(purchaseDetails, 200.1);
        Assert.assertTrue(purchaseDetails.getFrameRate().equals(200.1));
    }

    @Test(groups = { "Setters" })
    void setFrameRate_should_set_FrameRate_of_Type_Double_LicensePurchaseDetails()
            throws NoSuchFieldException, IllegalAccessException {
        purchaseDetails.setFrameRate(100.1);
        Field f = purchaseDetails.getClass().getDeclaredField("mFrameRate");
        f.setAccessible(true);
        Assert.assertTrue(f.get(purchaseDetails).equals(100.1));
    }
}
