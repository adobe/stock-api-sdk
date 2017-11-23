/*******************************************************************************
 * Copyright 2017 Adobe Systems Incorporated. All rights reserved.
 * This file is licensed to you under the Apache License, Version 2.0
 * (the "License") you may not use this file except in compliance with the
 * License. You may obtain a copy of the License at
 * http://www.apache.org/licenses/LICENSE-2.0
 *******************************************************************************/
package com.adobe.stock.enums;

import java.util.HashMap;

import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;

@Listeners({ com.adobe.stock.logger.TestCustomLogger.class,
        com.adobe.stock.logger.TestSuiteLogger.class })
@Test(suiteName = "AssetLicenseSize")
public class AssetLicenseSizeTest {
    private HashMap<AssetLicenseSize, String> testData = new HashMap<AssetLicenseSize, String>();

    @BeforeClass
    public void initializeTestData() {
        testData.put(AssetLicenseSize.COMPLEMENTARY, "Comp");
        testData.put(AssetLicenseSize.ORIGINAL, "Original");
    }

    @Test(groups = "AssetLicenseSize.toString")
    public void toString_should_return_string_equivalent_value_of_AssetLicenseSize_enum() {
        for (AssetLicenseSize env : AssetLicenseSize.values()) {
            AssetLicenseSize.valueOf(env.name());
            Assert.assertEquals(env.toString(), testData.get(env));
        }
    }
    
    @Test(groups = "AssetLicenseSize.fromString")
    public void fromString_should_return_AssetLicenseSize_enum_equivalent_value_of_string() {
        for (AssetLicenseSize env : AssetLicenseSize.values()) {
            Assert.assertEquals(env, AssetLicenseSize.fromString(testData.get(env)));
        }
        
        for (AssetLicenseSize env : AssetLicenseSize.values()) {
            Assert.assertNull(AssetLicenseSize.fromString("\0"+testData.get(env)));
        }
        
        Assert.assertNull(AssetLicenseSize.fromString("\0Unknown"));
    }
}
