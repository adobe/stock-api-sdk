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

import com.adobe.stock.enums.Environment;

@Listeners({ com.adobe.stock.logger.TestCustomLogger.class,
        com.adobe.stock.logger.TestSuiteLogger.class })
@Test(suiteName = "Environment")
public class EnvironmentTest {

    private HashMap<Environment, String> testData = new HashMap<Environment, String>();

    @BeforeClass
    public void initializeTestData() {
        testData.put(Environment.PROD, "1");
        testData.put(Environment.STAGE, "2");
    }

    @Test(groups = "Environment.toString")
    public void toString_should_return_string_equivalent_value_of_Environment_enum() {
        for (Environment env : Environment.values()) {
            Environment.valueOf(env.name());
            Assert.assertEquals(env.toString(), testData.get(env));
        }
    }
}