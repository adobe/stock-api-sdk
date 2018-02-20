/*******************************************************************************
 * Copyright 2017 Adobe Systems Incorporated. All rights reserved.
 * This file is licensed to you under the Apache License, Version 2.0
 * (the "License") you may not use this file except in compliance with the
 * License. You may obtain a copy of the License at
 * http://www.apache.org/licenses/LICENSE-2.0
 *******************************************************************************/
package com.adobe.stock.models;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import org.testng.Assert;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;

import com.adobe.stock.exception.StockException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

@Listeners({ com.adobe.stock.logger.TestCustomLogger.class,
    com.adobe.stock.logger.TestSuiteLogger.class })
@Test(suiteName = "LicenseResponse")
public class LicenseResponseTest {
    private LicenseResponse licenseResponse;
    
    @BeforeSuite
    void creating_LicenseResponse_InstanceSuccessfully() {
        licenseResponse = new LicenseResponse();
        Assert.assertNotNull(licenseResponse);
    }
    
    @Test(groups = { "Getters" })
    void getEntitlement_should_return_Entitlement_of_Type_LicenseEntitlement_LicenseResponse()
            throws NoSuchFieldException, IllegalAccessException {
        Field f = licenseResponse.getClass().getDeclaredField("mEntitlement");
        f.setAccessible(true);
        LicenseEntitlement entitlement = new LicenseEntitlement();
        entitlement.setQuota(100);
        f.set(licenseResponse, entitlement);
        Assert.assertEquals(licenseResponse.getEntitlement().getQuota().intValue(),100);
    }

    @Test(groups = { "Setters" })
    void setEntitlement_should_set_Entitlement_of_Type_LicenseEntitlement_LicenseResponse()
            throws NoSuchFieldException, IllegalAccessException {
        LicenseEntitlement entitlement = new LicenseEntitlement();
        entitlement.setQuota(100);
        licenseResponse.setEntitlement(entitlement);
        Field f = licenseResponse.getClass().getDeclaredField("mEntitlement");
        f.setAccessible(true);
        entitlement = (LicenseEntitlement) f.get(licenseResponse);
        Assert.assertEquals(entitlement.getQuota().intValue(),100);
    }

    @Test(groups = { "Getters" })
    void getPurchaseOptions_should_return_PurchaseOptions_of_Type_LicensePurchaseOptions_LicenseResponse()
            throws NoSuchFieldException, IllegalAccessException {
        Field f = licenseResponse.getClass().getDeclaredField("mPurchaseOptions");
        f.setAccessible(true);
        LicensePurchaseOptions purchaseOptions = new LicensePurchaseOptions();
        purchaseOptions.setMessage("test");
        f.set(licenseResponse, purchaseOptions);
        Assert.assertTrue(licenseResponse.getPurchaseOptions().getMessage().equals("test"));
    }

    @Test(groups = { "Setters" })
    void setPurchaseOptions_should_set_PurchaseOptions_of_Type_LicensePurchaseOptions_LicenseResponse()
            throws NoSuchFieldException, IllegalAccessException {
        LicensePurchaseOptions purchaseOptions = new LicensePurchaseOptions();
        purchaseOptions.setMessage("test");
        licenseResponse.setPurchaseOptions(purchaseOptions);
        Field f = licenseResponse.getClass().getDeclaredField("mPurchaseOptions");
        f.setAccessible(true);
        purchaseOptions = (LicensePurchaseOptions) f.get(licenseResponse);
        Assert.assertTrue(purchaseOptions.getMessage().equals("test"));
    }
    
    @Test(groups = { "Getters" })
    void getMemberInfo_should_return_MemberInfo_of_Type_LicenseMemberInfo_LicenseResponse()
            throws NoSuchFieldException, IllegalAccessException {
        Field f = licenseResponse.getClass().getDeclaredField("mMemberInfo");
        f.setAccessible(true);
        LicenseMemberInfo memberInfo = new LicenseMemberInfo();
        memberInfo.setStockId("testId");
        f.set(licenseResponse, memberInfo);
        Assert.assertEquals(licenseResponse.getMemberInfo().getStockId(),"testId");
    }

    @Test(groups = { "Setters" })
    void setMemberInfo_should_set_MemberInfo_of_Type_LicenseMemberInfo_LicenseResponse()
            throws NoSuchFieldException, IllegalAccessException {
        LicenseMemberInfo memberInfo = new LicenseMemberInfo();
        memberInfo.setStockId("testId");
        licenseResponse.setMemberInfo(memberInfo);
        Field f = licenseResponse.getClass().getDeclaredField("mMemberInfo");
        f.setAccessible(true);
        memberInfo = (LicenseMemberInfo) f.get(licenseResponse);
        Assert.assertEquals(memberInfo.getStockId(),"testId");
    }
    
    @Test(groups = { "Getters" })
    void getLicenseReferences_should_return_arraylist_of_license_references_LicenseResponse()
            throws NoSuchFieldException, IllegalAccessException {
        Field f = licenseResponse.getClass().getDeclaredField("mLicenseReferences");
        f.setAccessible(true);
        LicenseReferenceResponse reference = new LicenseReferenceResponse();
        reference.setId(1000);
        ArrayList<LicenseReferenceResponse> references = new ArrayList<LicenseReferenceResponse>();
        references.add(reference);
        references.add(reference);
        f.set(licenseResponse, references);
        Assert.assertEquals(licenseResponse.getLicenseReferences().get(0).getId().intValue(), 1000);
    }

    @SuppressWarnings("unchecked")
    @Test(groups = { "Setters" })
    void setLicenseReferences_should_set_arraylist_of_license_references_LicenseResponse()
            throws NoSuchFieldException, IllegalAccessException {
        LicenseReferenceResponse reference = new LicenseReferenceResponse();
        reference.setId(1000);
        ArrayList<LicenseReferenceResponse> references = new ArrayList<LicenseReferenceResponse>();
        references.add(reference);
        references.add(reference);
        licenseResponse.setLicenseReferences(references);
        Field f = licenseResponse.getClass().getDeclaredField("mLicenseReferences");
        f.setAccessible(true);
        references = (ArrayList<LicenseReferenceResponse>) f.get(licenseResponse);
        Assert.assertEquals(references.get(0).getId().intValue(), 1000);
    }

    @Test(groups = { "Getters" })
    void getContent_should_return_content_of_type_LicenseContent_LicenseResponse()
            throws NoSuchFieldException, IllegalAccessException, StockException {
        Field f = licenseResponse.getClass().getDeclaredField("mContentMap");
        f.setAccessible(true);
        Map<String, LicenseContent> contentMap = new HashMap<String, LicenseContent>();
        LicenseContent content = new LicenseContent();
        content.setContentId("testId");
        contentMap.put("testId", content);
        f.set(licenseResponse, contentMap);
        Assert.assertTrue(licenseResponse.getContent("testId").getContentId().equals("testId"));
    }

    @Test(groups = { "Getters" }, expectedExceptions = {
            StockException.class }, expectedExceptionsMessageRegExp = "Content not found for the given content id")
    void getContent_should_throw_exception_invalid_contentId_LicenseResponse()
            throws Exception {
        Field f = licenseResponse.getClass().getDeclaredField("mContentMap");
        f.setAccessible(true);
        Map<String, LicenseContent> contentMap = new HashMap<String, LicenseContent>();
        LicenseContent content = new LicenseContent();
        content.setContentId("testId");
        contentMap.put("testId", content);
        f.set(licenseResponse, contentMap);
        licenseResponse.getContent("invalidID");
    }
    
    @Test(groups = { "Getters" })
    void getContents_should_return_arraylist_of_content_of_type_LicenseContent_LicenseResponse()
            throws NoSuchFieldException, IllegalAccessException, StockException {
        Field f = licenseResponse.getClass().getDeclaredField("mContentMap");
        f.setAccessible(true);
        Map<String, LicenseContent> contentMap = new HashMap<String, LicenseContent>();
        LicenseContent content = new LicenseContent();
        content.setContentId("testId");
        contentMap.put("testId", content);
        f.set(licenseResponse, contentMap);
        Assert.assertTrue(licenseResponse.getContents().get(0).getContentId().equals("testId"));
    }

    @SuppressWarnings("unchecked")
    @Test(groups = { "Setters" })
    void setContent_should_set_content_of_type_JsonNode_LicenseResponse()
            throws Exception {
        String jsonString = "{ \"67342001\": { \"content_id\": 67342001, \"purchase_details\": { \"date\": \"2015-02-10 15:00:00\", \"license\": \"Extended\", \"state\": \"purchased\", \"url\": \"http://stock.adobe.com/asset/67342001\" }, \"size\": \"Original\" } } ";
        ObjectMapper mapper = new ObjectMapper();
        JsonNode node = mapper.readTree(jsonString);
        licenseResponse.setContent(node);
        Field f = licenseResponse.getClass().getDeclaredField("mContentMap");
        f.setAccessible(true);
        Map<String, LicenseContent> contentMap = (Map<String, LicenseContent>) f.get(licenseResponse);
        Assert.assertTrue(contentMap.get("67342001").getContentId().equals("67342001"));
    }
}
