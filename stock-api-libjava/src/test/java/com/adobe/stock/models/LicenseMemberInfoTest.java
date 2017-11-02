package com.adobe.stock.models;

import java.lang.reflect.Field;

import org.testng.Assert;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;

@Listeners({ com.adobe.stock.logger.TestCustomLogger.class,
    com.adobe.stock.logger.TestSuiteLogger.class })
@Test(suiteName = "LicenseMemberInfo")
public class LicenseMemberInfoTest {
    private LicenseMemberInfo member;

    @BeforeSuite()
    void create_LicenseMemberInfo_Instance_successfully() {
        member = new LicenseMemberInfo();
        Assert.assertNotNull(member);
    }

    @Test(groups = { "Getters" })
    void getStockId_should_return_stockId_of_Type_String_LicenseMemberInfo()
            throws NoSuchFieldException, IllegalAccessException {
        Field f = member.getClass().getDeclaredField("mStockId");
        f.setAccessible(true);
        f.set(member, "testId");
        Assert.assertEquals("testId", member.getStockId());
    }

    @Test(groups = { "Setters" })
    void setStockId_should_set_stockId_of_Type_String_LicenseMemberInfo()
            throws NoSuchFieldException, IllegalAccessException {
        member.setStockId("testId");
        Field f = member.getClass().getDeclaredField("mStockId");
        f.setAccessible(true);
        Assert.assertEquals("testId", f.get(member));
    }
}
