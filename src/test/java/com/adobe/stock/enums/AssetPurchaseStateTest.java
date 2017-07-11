package com.adobe.stock.enums;

import java.util.HashMap;

import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;

@Listeners({ com.adobe.stock.logger.TestCustomLogger.class,
    com.adobe.stock.logger.TestSuiteLogger.class })
@Test(suiteName = "AssetPremiumLevel")
public class AssetPurchaseStateTest {

private HashMap<AssetPurchaseState, String> testData = new HashMap<AssetPurchaseState, String>();

@BeforeClass
public void initializeTestData() {
    testData.put(AssetPurchaseState.CANCELLED, "cancelled");
    testData.put(AssetPurchaseState.JUST_PURCHASED, "just_purchased");
    testData.put(AssetPurchaseState.NOT_POSSIBLE, "not_possible");
    testData.put(AssetPurchaseState.NOT_PURCHASED, "not_purchased");
    testData.put(AssetPurchaseState.OVERAGE, "overage");
    testData.put(AssetPurchaseState.PURCHASED, "purchased");
}

@Test(groups = "AssetPurchaseState.toString")
public void toString_should_return_string_equivalent_value_of_AssetPurchaseState_enum() {
    for (AssetPurchaseState env : AssetPurchaseState.values()) {
        AssetPurchaseState.valueOf(env.name());
        Assert.assertEquals(env.toString(), testData.get(env));
    }
}

@Test(groups = "AssetPurchaseState.fromString")
public void fromString_should_return_AssetPurchaseState_enum_equivalent_value_of_string() {
    for (AssetPurchaseState env : AssetPurchaseState.values()) {
        Assert.assertEquals(env, AssetPurchaseState.fromString(testData.get(env)));
    }
    
    for (AssetPurchaseState env : AssetPurchaseState.values()) {
        Assert.assertNull(AssetPurchaseState.fromString("\0"+testData.get(env)));
    }
    
    Assert.assertNull(AssetPurchaseState.fromString("\0Unknown"));
}
}
