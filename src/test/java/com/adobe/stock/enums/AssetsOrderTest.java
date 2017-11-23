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
@Test(suiteName = "AssetsOrder")
public class AssetsOrderTest {
    private HashMap<AssetsOrder, String> testData = new HashMap<AssetsOrder, String>();

    @BeforeClass
    public void initializeTestData() {
        testData.put(AssetsOrder.RELEVANCE, "relevance");
        testData.put(AssetsOrder.CREATION, "creation");
        testData.put(AssetsOrder.POPULARITY, "popularity");
        testData.put(AssetsOrder.NB_DOWNLOADS, "nb_downloads");
        testData.put(AssetsOrder.UNDISCOVERED, "undiscovered");
    }

    @Test(groups = "AssetsOrder.toString")
    public void toString_should_return_string_equivalent_value_of_AssetsOrder_enum() {
        for (AssetsOrder env : AssetsOrder.values()) {
            AssetsOrder.valueOf(env.name());
            Assert.assertEquals(env.toString(), testData.get(env));
        }
    }
}
