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

import com.adobe.stock.enums.AssetPurchaseState;

@Listeners({ com.adobe.stock.logger.TestCustomLogger.class,
    com.adobe.stock.logger.TestSuiteLogger.class })
@Test(suiteName = "LicensePurchaseOptions")
public class LicensePurchaseOptionsTest {
    private LicensePurchaseOptions purchaseOptions;
    
    @BeforeSuite
    void creating_LicensePurchaseOptions_InstanceSuccessfully() {
        purchaseOptions = new LicensePurchaseOptions();
        Assert.assertNotNull(purchaseOptions);
    }
    
    @Test(groups = { "Getters" })
    void getMessage_should_return_message_of_Type_String_LicensePurchaseOptions()
            throws NoSuchFieldException, IllegalAccessException {
        Field f = purchaseOptions.getClass().getDeclaredField("mMessage");
        f.setAccessible(true);
        f.set(purchaseOptions, "message");
        Assert.assertTrue(purchaseOptions.getMessage().equals("message"));
    }

    @Test(groups = { "Setters" })
    void setMessage_should_set_message_of_Type_String_LicensePurchaseOptions()
            throws NoSuchFieldException, IllegalAccessException {
        purchaseOptions.setMessage("message");
        Field f = purchaseOptions.getClass().getDeclaredField("mMessage");
        f.setAccessible(true);
        Assert.assertTrue(f.get(purchaseOptions).equals("message"));
    }
    
    @Test(groups = { "Getters" })
    void getPurchaseUrl_should_return_purchaseUrl_of_Type_String_LicensePurchaseOptions()
            throws NoSuchFieldException, IllegalAccessException {
        Field f = purchaseOptions.getClass().getDeclaredField("mPurchaseUrl");
        f.setAccessible(true);
        f.set(purchaseOptions, "testUrl");
        Assert.assertTrue(purchaseOptions.getPurchaseUrl().equals("testUrl"));
    }

    @Test(groups = { "Setters" })
    void setPurchaseUrl_should_set_purchaseUrl_of_Type_String_LicensePurchaseOptions()
            throws NoSuchFieldException, IllegalAccessException {
        purchaseOptions.setPurchaseUrl("testUrl");
        Field f = purchaseOptions.getClass().getDeclaredField("mPurchaseUrl");
        f.setAccessible(true);
        Assert.assertTrue(f.get(purchaseOptions).equals("testUrl"));
    }
    
    @Test(groups = { "Getters" })
    void getRequiresCheckout_should_return_RequiresCheckout_of_Type_Boolean_LicensePurchaseOptions()
            throws NoSuchFieldException, IllegalAccessException {
        Field f = purchaseOptions.getClass().getDeclaredField("mRequiresCheckout");
        f.setAccessible(true);
        f.set(purchaseOptions, true);
        Assert.assertTrue(purchaseOptions.getRequiresCheckout());
    }

    @Test(groups = { "Setters" })
    void setRequiresCheckout_should_set_RequiresCheckout_of_Type_Boolean_LicensePurchaseOptions()
            throws NoSuchFieldException, IllegalAccessException {
        purchaseOptions.setRequiresCheckout(true);
        Field f = purchaseOptions.getClass().getDeclaredField("mRequiresCheckout");
        f.setAccessible(true);
        Assert.assertTrue(f.get(purchaseOptions).equals(true));
    }
    
    @Test(groups = { "Getters" })
    void getPurchaseState_should_return_PurchaseState_of_Type_AssetPurchaseState_LicensePurchaseOptions()
            throws NoSuchFieldException, IllegalAccessException {
        Field f = purchaseOptions.getClass().getDeclaredField("mPurchaseState");
        f.setAccessible(true);
        f.set(purchaseOptions, AssetPurchaseState.NOT_POSSIBLE);
        Assert.assertEquals(purchaseOptions.getPurchaseState(), AssetPurchaseState.NOT_POSSIBLE);
    }

    @Test(groups = { "Setters" })
    void setPurchaseState_should_set_PurchaseState_of_Type_AssetPurchaseState_LicensePurchaseOptions()
            throws NoSuchFieldException, IllegalAccessException {
        purchaseOptions.setPurchaseState(AssetPurchaseState.CANCELLED);
        Field f = purchaseOptions.getClass().getDeclaredField("mPurchaseState");
        f.setAccessible(true);
        Assert.assertEquals(f.get(purchaseOptions),AssetPurchaseState.CANCELLED);
    }
}
