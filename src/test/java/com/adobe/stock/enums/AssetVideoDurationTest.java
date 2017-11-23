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
@Test(suiteName = "AssetVideoDuration")
public class AssetVideoDurationTest {

    private HashMap<AssetVideoDuration, String> testData = new HashMap<AssetVideoDuration, String>();

    @BeforeClass
    public void initializeTestData() {
        testData.put(AssetVideoDuration.TEN, "10");
        testData.put(AssetVideoDuration.TWENTY, "20");
        testData.put(AssetVideoDuration.THIRTY, "30");
        testData.put(AssetVideoDuration.ABOVE_THIRTY, "30-");
        testData.put(AssetVideoDuration.ALL, "all");
    }

    @Test(groups = "AssetVideoDuration.toString")
    public void toString_should_return_string_equivalent_value_of_AssetVideoDuration_enum() {
        for (AssetVideoDuration env : AssetVideoDuration.values()) {
            AssetVideoDuration.valueOf(env.name());
            Assert.assertEquals(env.toString(), testData.get(env));
        }
    }
}
