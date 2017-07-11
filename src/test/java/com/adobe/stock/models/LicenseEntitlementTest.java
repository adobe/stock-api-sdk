package com.adobe.stock.models;

import java.lang.reflect.Field;

import org.testng.Assert;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

@Listeners({ com.adobe.stock.logger.TestCustomLogger.class,
    com.adobe.stock.logger.TestSuiteLogger.class })
@Test(suiteName = "LicenseEntitlement")
public class LicenseEntitlementTest {
    private LicenseEntitlement entitlement;
    
    @BeforeSuite
    void creating_LicenseEntitlement_InstanceSuccessfully() {
        entitlement = new LicenseEntitlement();
        Assert.assertNotNull(entitlement);
    }
    
    @Test(groups = { "Getters" })
    void getQuota_should_return_Quota_of_Type_Integer_LicenseEntitlement()
            throws NoSuchFieldException, IllegalAccessException {
        Field f = entitlement.getClass().getDeclaredField("mQuota");
        f.setAccessible(true);
        f.set(entitlement, 200);
        Assert.assertEquals(200, entitlement.getQuota().intValue());
    }

    @Test(groups = { "Setters" })
    void setQuota_should_set_Quota_of_Type_Integer_LicenseEntitlement()
            throws NoSuchFieldException, IllegalAccessException {
        entitlement.setQuota(500);
        Field f = entitlement.getClass().getDeclaredField("mQuota");
        f.setAccessible(true);
        Assert.assertEquals(500, f.get(entitlement));
    }
    
    @Test(groups = { "Getters" })
    void getFullEntitlementQuota_should_return_EntitlementQuota_of_Type_LicenseEntitlementQuota_LicenseEntitlement()
            throws NoSuchFieldException, IllegalAccessException {
        Field f = entitlement.getClass().getDeclaredField("mFullEntitlementQuota");
        LicenseEntitlementQuota entitlementQuota = new LicenseEntitlementQuota();
        entitlementQuota.setImageQuota(10);
        f.setAccessible(true);
        f.set(entitlement, entitlementQuota);
        Assert.assertEquals(10, entitlement.getFullEntitlementQuota().getImageQuota().intValue());
    }

    @Test(groups = { "Setters" })
    void setFullEntitlementQuota_should_set_EntitlementQuota_of_Type_LicenseEntitlementQuota_LicenseEntitlement()
            throws Exception {
        String jsonString = "{ \"standard_credits_quota\": 5, \"premium_credits_quota\": 42 }";
        ObjectMapper mapper = new ObjectMapper();
        Field f = entitlement.getClass().getDeclaredField("mFullEntitlementQuota");
        f.setAccessible(true);
        f.set(entitlement, null);
        JsonNode node = mapper.readTree("[]");
        entitlement.setFullEntitlementQuota(node);
        LicenseEntitlementQuota quota = (LicenseEntitlementQuota) f.get(entitlement);
        Assert.assertNull(quota);
        
        entitlement.setFullEntitlementQuota(null);
        quota = (LicenseEntitlementQuota) f.get(entitlement);
        Assert.assertNull(quota);
        
        node = mapper.readTree(jsonString);
        entitlement.setFullEntitlementQuota(node);
        quota = (LicenseEntitlementQuota) f.get(entitlement);
        Assert.assertEquals(quota.getStandardCreditsQuota().intValue(), 5);
        Assert.assertEquals(quota.getPremiumCreditsQuota().intValue(), 42);
    }

    @Test(groups = { "Getters" })
    void getLicenseTypeId_should_return_LicenseTypeId_of_Type_Integer_LicenseEntitlement()
            throws NoSuchFieldException, IllegalAccessException {
        Field f = entitlement.getClass().getDeclaredField("mLicenseTypeId");
        f.setAccessible(true);
        f.set(entitlement, 200);
        Assert.assertEquals(200, entitlement.getLicenseTypeId().intValue());
    }

    @Test(groups = { "Setters" })
    void setLicenseTypeId_should_set_LicenseTypeId_of_Type_Integer_LicenseEntitlement()
            throws NoSuchFieldException, IllegalAccessException {
        entitlement.setLicenseTypeId(500);
        Field f = entitlement.getClass().getDeclaredField("mLicenseTypeId");
        f.setAccessible(true);
        Assert.assertEquals(500, f.get(entitlement));
    }

    @Test(groups = { "Getters" })
    void getHasCreditModel_should_return_HasCreditModel_of_Type_Boolean_LicenseEntitlement()
            throws NoSuchFieldException, IllegalAccessException {
        Field f = entitlement.getClass().getDeclaredField("mHasCreditModel");
        f.setAccessible(true);
        f.set(entitlement, true);
        Assert.assertEquals(true, entitlement.getHasCreditModel().booleanValue());
    }

    @Test(groups = { "Setters" })
    void setHasCreditModel_should_set_HasCreditModel_of_Type_Boolean_LicenseEntitlement()
            throws NoSuchFieldException, IllegalAccessException {
        entitlement.setHasCreditModel(true);
        Field f = entitlement.getClass().getDeclaredField("mHasCreditModel");
        f.setAccessible(true);
        Assert.assertEquals(true, f.get(entitlement));
    }

    @Test(groups = { "Getters" })
    void getHasAgencyModel_should_return_HasAgencyModel_of_Type_Boolean_LicenseEntitlement()
            throws NoSuchFieldException, IllegalAccessException {
        Field f = entitlement.getClass().getDeclaredField("mHasAgencyModel");
        f.setAccessible(true);
        f.set(entitlement, true);
        Assert.assertEquals(true, entitlement.getHasAgencyModel().booleanValue());
    }

    @Test(groups = { "Setters" })
    void setHasAgencyModel_should_set_HasAgencyModel_of_Type_Boolean_LicenseEntitlement()
            throws NoSuchFieldException, IllegalAccessException {
        entitlement.setHasAgencyModel(true);
        Field f = entitlement.getClass().getDeclaredField("mHasAgencyModel");
        f.setAccessible(true);
        Assert.assertEquals(true, f.get(entitlement));
    }

    @Test(groups = { "Getters" })
    void getIsCce_should_return_IsCce_of_Type_Boolean_LicenseEntitlement()
            throws NoSuchFieldException, IllegalAccessException {
        Field f = entitlement.getClass().getDeclaredField("mIsCce");
        f.setAccessible(true);
        f.set(entitlement, true);
        Assert.assertEquals(true, entitlement.getIsCce().booleanValue());
    }

    @Test(groups = { "Setters" })
    void setIsCce_should_set_IsCce_of_Type_Boolean_LicenseEntitlement()
            throws NoSuchFieldException, IllegalAccessException {
        entitlement.setIsCce(true);
        Field f = entitlement.getClass().getDeclaredField("mIsCce");
        f.setAccessible(true);
        Assert.assertEquals(true, f.get(entitlement));
    }
}
