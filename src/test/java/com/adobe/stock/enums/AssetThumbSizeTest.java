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
@Test(suiteName = "AssetThumbSize")
public class AssetThumbSizeTest {

    private HashMap<AssetThumbSize, String> testData = new HashMap<AssetThumbSize, String>();

    @BeforeClass
    public void initializeTestData() {
        testData.put(AssetThumbSize.MEDIUM, "110");
        testData.put(AssetThumbSize.BIG, "160");
        testData.put(AssetThumbSize.XL, "500");
        testData.put(AssetThumbSize.XXL, "1000");
    }

    @Test(groups = "AssetThumbSize.toString")
    public void toString_should_return_string_equivalent_value_of_AssetThumbSize_enum() {
        for (AssetThumbSize env : AssetThumbSize.values()) {
            AssetThumbSize.valueOf(env.name());
            Assert.assertEquals(env.toString(), testData.get(env));
        }
    }
}
