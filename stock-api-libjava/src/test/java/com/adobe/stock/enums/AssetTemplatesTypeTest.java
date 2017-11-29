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
@Test(suiteName = "AssetTemplatesType")
public class AssetTemplatesTypeTest {

    private HashMap<AssetTemplatesType, String> testData = new HashMap<AssetTemplatesType, String>();

    @BeforeClass
    public void initializeTestData() {
        testData.put(AssetTemplatesType.PSDT, "1");
        testData.put(AssetTemplatesType.AIT, "2");
        testData.put(AssetTemplatesType.INDT, "3");
    }

    @Test(groups = "AssetTemplatesType.toString")
    public void toString_should_return_string_equivalent_value_of_AssetTemplatesType_enum() {
        for (AssetTemplatesType env : AssetTemplatesType.values()) {
            AssetTemplatesType.valueOf(env.name());
            Assert.assertEquals(env.toString(), testData.get(env));
        }
    }
    
    @Test(groups = "AssetTemplatesType.fromString")
    public void fromString_should_return_AssetTemplatesType_enum_equivalent_value_of_string() {
        for (AssetTemplatesType env : AssetTemplatesType.values()) {
            Assert.assertEquals(env, AssetTemplatesType.fromString(testData.get(env)));
        }
        
        for (AssetTemplatesType env : AssetTemplatesType.values()) {
            Assert.assertNull(AssetTemplatesType.fromString("\0"+testData.get(env)));
        }
        
        Assert.assertNull(AssetTemplatesType.fromString("\0Unknown"));
    }
}