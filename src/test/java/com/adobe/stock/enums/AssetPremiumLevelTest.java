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
@Test(suiteName = "AssetPremiumLevel")
public class AssetPremiumLevelTest {

    private HashMap<AssetPremiumLevel, String> testData = new HashMap<AssetPremiumLevel, String>();

    @BeforeClass
    public void initializeTestData() {
        testData.put(AssetPremiumLevel.CORE, "0");
        testData.put(AssetPremiumLevel.FREE, "1");
        testData.put(AssetPremiumLevel.PREMIUM1, "2");
        testData.put(AssetPremiumLevel.PREMIUM2, "3");
        testData.put(AssetPremiumLevel.PREMIUM3, "4");
    }

    @Test(groups = "AssetPremiumLevel.toString")
    public void toString_should_return_string_equivalent_value_of_AssetPremiumLevel_enum() {
        for (AssetPremiumLevel env : AssetPremiumLevel.values()) {
            AssetPremiumLevel.valueOf(env.name());
            Assert.assertEquals(env.toString(), testData.get(env));
        }
    }
    
    @Test(groups = "AssetPremiumLevel.fromString")
    public void fromString_should_return_AssetPremiumLevel_enum_equivalent_value_of_string() {
        for (AssetPremiumLevel env : AssetPremiumLevel.values()) {
            Assert.assertEquals(env, AssetPremiumLevel.fromString(testData.get(env)));
        }
        
        for (AssetPremiumLevel env : AssetPremiumLevel.values()) {
            Assert.assertNull(AssetPremiumLevel.fromString("\0"+testData.get(env)));
        }
        
        Assert.assertNull(AssetPremiumLevel.fromString("\0Unknown"));
    }
}
