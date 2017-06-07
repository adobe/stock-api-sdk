package com.adobe.stock.enums;

import java.util.HashMap;

import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;

@Listeners({ com.adobe.stock.logger.TestCustomLogger.class,
        com.adobe.stock.logger.TestSuiteLogger.class })
@Test(suiteName = "AssetPremiumCategory")
public class AssetPremiumCategoryTest {

    private HashMap<AssetPremiumCategory, String> testData = new HashMap<AssetPremiumCategory, String>();

    @BeforeClass
    public void initializeTestData() {
        testData.put(AssetPremiumCategory.TRUE, "true");
        testData.put(AssetPremiumCategory.FALSE, "false");
        testData.put(AssetPremiumCategory.ALL, "all");
    }

    @Test(groups = "AssetPremiumCategory.toString")
    public void toString_should_return_string_equivalent_value_of_AssetPremiumCategory_enum() {
        for (AssetPremiumCategory env : AssetPremiumCategory.values()) {
            AssetPremiumCategory.valueOf(env.name());
            Assert.assertEquals(env.toString(), testData.get(env));
        }
    }
}
