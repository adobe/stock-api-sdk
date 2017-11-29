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
@Test(suiteName = "AssetType")
public class AssetTypeTest {

    private HashMap<AssetType, String> testData = new HashMap<AssetType, String>();

    @BeforeClass
    public void initializeTestData() {
        testData.put(AssetType.PHOTOS, "1");
        testData.put(AssetType.ILLUSTRATIONS, "2");
        testData.put(AssetType.VECTORS, "3");
        testData.put(AssetType.VIDEOS, "4");
        testData.put(AssetType.INSTANT_PHOTOS, "5");
        testData.put(AssetType.THREE_DIMENSIONAL, "6");
        testData.put(AssetType.TEMPLATES, "7");
    }

    @Test(groups = "AssetType.toString")
    public void toString_should_return_string_equivalent_value_of_AssetType_enum() {
        for (AssetType env : AssetType.values()) {
            AssetType.valueOf(env.name());
            Assert.assertEquals(env.toString(), testData.get(env));
        }
    }
    
    @Test(groups = "AssetType.fromString")
    public void fromString_should_return_AssetType_enum_equivalent_value_of_string() {
        for (AssetType env : AssetType.values()) {
            Assert.assertEquals(env, AssetType.fromString(testData.get(env)));
        }
        
        for (AssetType env : AssetType.values()) {
            Assert.assertNull(AssetType.fromString("\0"+testData.get(env)));
        }
        
        Assert.assertNull(AssetType.fromString("\0Unknown"));
    }
}
