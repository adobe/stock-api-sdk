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
@Test(suiteName = "AssetTemplateCategory")
public class AssetTemplateCategoryTest {

    private HashMap<AssetTemplateCategory, String> testData = new HashMap<AssetTemplateCategory, String>();

    @BeforeClass
    public void initializeTestData() {
        testData.put(AssetTemplateCategory.MOBILE, "1");
        testData.put(AssetTemplateCategory.WEB, "2");
        testData.put(AssetTemplateCategory.PRINT, "3");
        testData.put(AssetTemplateCategory.PHOTO, "4");
        testData.put(AssetTemplateCategory.FILM, "5");
        testData.put(AssetTemplateCategory.ART, "6");
    }

    @Test(groups = "AssetTemplateCategory.toString")
    public void toString_should_return_string_equivalent_value_of_AssetTemplateCategory_enum() {
        for (AssetTemplateCategory env : AssetTemplateCategory
                .values()) {
            AssetTemplateCategory.valueOf(env.name());
            Assert.assertEquals(env.toString(), testData.get(env));
        }
    }
    
    @Test(groups = "AssetTemplateCategory.fromString")
    public void fromString_should_return_AssetTemplateCategory_enum_equivalent_value_of_string() {
        for (AssetTemplateCategory env : AssetTemplateCategory.values()) {
            Assert.assertEquals(env, AssetTemplateCategory.fromString(testData.get(env)));
        }
        
        for (AssetTemplateCategory env : AssetTemplateCategory.values()) {
            Assert.assertNull(AssetTemplateCategory.fromString("\0"+testData.get(env)));
        }
        
        Assert.assertNull(AssetTemplateCategory.fromString("\07"));
    }
}
