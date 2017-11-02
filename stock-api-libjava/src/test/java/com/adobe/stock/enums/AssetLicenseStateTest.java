package com.adobe.stock.enums;

import java.util.HashMap;

import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;

@Listeners({ com.adobe.stock.logger.TestCustomLogger.class,
        com.adobe.stock.logger.TestSuiteLogger.class })
@Test(suiteName = "AssetLicenseState")
public class AssetLicenseStateTest {
    private HashMap<AssetLicenseState, String> testData = new HashMap<AssetLicenseState, String>();

    @BeforeClass
    public void initializeTestData() {
        testData.put(AssetLicenseState.STANDARD, "Standard");
        testData.put(AssetLicenseState.EXTENDED, "Extended");
        testData.put(AssetLicenseState.VIDEO_HD, "Video_HD");
        testData.put(AssetLicenseState.VIDEO_4K, "Video_4K");
        testData.put(AssetLicenseState.STANDARD_M, "Standard_M");
        testData.put(AssetLicenseState.EMPTY, "");
    }

    @Test(groups = "AssetLicenseState.toString")
    public void toString_should_return_string_equivalent_value_of_AssetLicenseState_enum() {
        for (AssetLicenseState env : AssetLicenseState.values()) {
            AssetLicenseState.valueOf(env.name());
            Assert.assertEquals(env.toString(), testData.get(env));
        }
    }
    
    @Test(groups = "AssetLicenseState.fromString")
    public void fromString_should_return_AssetLicenseState_enum_equivalent_value_of_string() {
        for (AssetLicenseState env : AssetLicenseState.values()) {
            Assert.assertEquals(env, AssetLicenseState.fromString(testData.get(env)));
        }
        
        for (AssetLicenseState env : AssetLicenseState.values()) {
            Assert.assertNull(AssetLicenseState.fromString("\0"+testData.get(env)));
        }
        
        Assert.assertNull(AssetLicenseState.fromString("\0Unknown"));
    }
}
