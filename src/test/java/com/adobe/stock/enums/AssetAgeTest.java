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
@Test(suiteName = "AssetAge")
public class AssetAgeTest {
    private HashMap<AssetAge, String> testData = new HashMap<AssetAge, String>();

    @BeforeClass
    public void initializeTestData() {
        testData.put(AssetAge.ONE_WEEK, "1w");
        testData.put(AssetAge.ONE_MONTH, "1m");
        testData.put(AssetAge.SIX_MONTH, "6m");
        testData.put(AssetAge.ONE_YEAR, "1y");
        testData.put(AssetAge.TWO_YEAR, "2y");
        testData.put(AssetAge.ALL, "all");
    }

    @Test(groups = "AssetAge.toString")
    public void toString_should_return_string_equivalent_value_of_AssetAge_enum() {
        for (AssetAge env : AssetAge.values()) {
            AssetAge.valueOf(env.name());
            Assert.assertEquals(env.toString(), testData.get(env));
        }
    }
}
