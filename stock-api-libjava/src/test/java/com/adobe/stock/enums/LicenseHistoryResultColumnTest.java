/*******************************************************************************
 * Copyright 2017 Adobe Systems Incorporated. All rights reserved.
 * This file is licensed to you under the Apache License, Version 2.0
 * (the "License") you may not use this file except in compliance with the
 * License. You may obtain a copy of the License at
 * http://www.apache.org/licenses/LICENSE-2.0
 ******************************************************************************/
package com.adobe.stock.enums;

import java.util.HashMap;

import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;

@Listeners({ com.adobe.stock.logger.TestCustomLogger.class,
    com.adobe.stock.logger.TestSuiteLogger.class })
@Test(suiteName = "LicenseHistoryLicenseHistoryResultColumn")
public class LicenseHistoryResultColumnTest {

    private HashMap<LicenseHistoryResultColumn, String> testData = new HashMap<LicenseHistoryResultColumn, String>();

    @BeforeClass
    public void initializeTestData() {
        
        testData.put(LicenseHistoryResultColumn.THUMBNAIL_110_URL, "thumbnail_110_url");
        testData.put(LicenseHistoryResultColumn.THUMBNAIL_110_WIDTH, "thumbnail_110_width");
        testData.put(LicenseHistoryResultColumn.THUMBNAIL_110_HEIGHT, "thumbnail_110_height");
        testData.put(LicenseHistoryResultColumn.THUMBNAIL_160_URL, "thumbnail_160_url");
        testData.put(LicenseHistoryResultColumn.THUMBNAIL_160_WIDTH, "thumbnail_160_width");
        testData.put(LicenseHistoryResultColumn.THUMBNAIL_160_HEIGHT, "thumbnail_160_height");
        testData.put(LicenseHistoryResultColumn.THUMBNAIL_220_URL, "thumbnail_220_url");
        testData.put(LicenseHistoryResultColumn.THUMBNAIL_220_WIDTH, "thumbnail_220_width");
        testData.put(LicenseHistoryResultColumn.THUMBNAIL_220_HEIGHT, "thumbnail_220_height");
        testData.put(LicenseHistoryResultColumn.THUMBNAIL_240_URL, "thumbnail_240_url");
        testData.put(LicenseHistoryResultColumn.THUMBNAIL_240_WIDTH, "thumbnail_240_width");
        testData.put(LicenseHistoryResultColumn.THUMBNAIL_240_HEIGHT, "thumbnail_240_height");
        testData.put(LicenseHistoryResultColumn.THUMBNAIL_500_URL, "thumbnail_500_url");
        testData.put(LicenseHistoryResultColumn.THUMBNAIL_500_WIDTH, "thumbnail_500_width");
        testData.put(LicenseHistoryResultColumn.THUMBNAIL_500_HEIGHT, "thumbnail_500_height");
        testData.put(LicenseHistoryResultColumn.THUMBNAIL_1000_URL, "thumbnail_1000_url");
        testData.put(LicenseHistoryResultColumn.THUMBNAIL_1000_WIDTH, "thumbnail_1000_width");
        testData.put(LicenseHistoryResultColumn.THUMBNAIL_1000_HEIGHT,
                "thumbnail_1000_height");
    }

    @Test(groups = "LicenseHistoryResultColumn.toString")
    public void toString_should_return_string_equivalent_value_of_LicenseHistoryResultColumn_enum() {
        for (LicenseHistoryResultColumn env : LicenseHistoryResultColumn.values()) {
            LicenseHistoryResultColumn.valueOf(env.name());
            Assert.assertEquals(env.toString(), testData.get(env));
        }
    }
}
