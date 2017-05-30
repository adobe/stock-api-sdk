package com.adobe.stock.enums;

import java.util.HashMap;

import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;

@Listeners({ com.adobe.stock.logger.TestCustomLogger.class,
        com.adobe.stock.logger.TestSuiteLogger.class })
@Test(suiteName = "AssetHasReleases")
public class AssetHasReleasesTest {
    private HashMap<AssetHasReleases, String> testData = new HashMap<AssetHasReleases, String>();

    @BeforeClass
    public void initializeTestData() {
        testData.put(AssetHasReleases.TRUE, "true");
        testData.put(AssetHasReleases.FALSE, "false");
        testData.put(AssetHasReleases.ALL, "all");
    }

    @Test(groups = "AssetHasReleases.toString")
    public void toString_should_return_string_equivalent_value_of_AssetHasReleases_enum() {
        for (AssetHasReleases env : AssetHasReleases.values()) {
            AssetHasReleases.valueOf(env.name());
            Assert.assertEquals(env.toString(), testData.get(env));
        }
    }
}
