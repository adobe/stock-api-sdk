/*******************************************************************************
 * Copyright 2017 Adobe Systems Incorporated. All rights reserved.
 * This file is licensed to you under the Apache License, Version 2.0
 * (the "License") you may not use this file except in compliance with the
 * License. You may obtain a copy of the License at
 * http://www.apache.org/licenses/LICENSE-2.0
 ******************************************************************************/
package com.adobe.stock.models;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

import com.adobe.stock.exception.StockException;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonSetter;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

/**
 * LicenseResponse stores the response from the following Stock API calls:
 * Content/Info, Content/License and Member/Profile.
 */
@JsonIgnoreProperties(ignoreUnknown = true)
public final class LicenseResponse {
    /**
     * Information about licenses available for the user.
     */
    private LicenseEntitlement mEntitlement;
    /**
     * Information about the user's purchasing options for the asset.
     */
    private LicensePurchaseOptions mPurchaseOptions;
    /**
     * Information about the user.
     */
    private LicenseMemberInfo mMemberInfo;
    /**
     * List of license references of the user.
     */
    private ArrayList<LicenseReferenceResponse> mLicenseReferences;
    /**
     * Mapping from Asset unique identifier to Asset Licensing information.
     */
    private Map<String, LicenseContent> mContentMap;

    /**
     * Default Constructor.
     */
    public LicenseResponse() {
    }

    /**
     * Get Information about licenses available for the user.
     *
     * @return object of type {@link LicenseEntitlement}
     */
    public LicenseEntitlement getEntitlement() {
        return mEntitlement;
    }

    /**
     * Get Information about the user's purchasing options for the asset.
     *
     * @return object of type {@link LicensePurchaseOptions}
     */
    public LicensePurchaseOptions getPurchaseOptions() {
        return mPurchaseOptions;
    }

    /**
     * Get Information about the user.
     *
     * @return object of type {@link LicenseMemberInfo}
     */
    public LicenseMemberInfo getMemberInfo() {
        return mMemberInfo;
    }

    /**
     * Get List of license references of the user.
     *
     * @return Arraylist of object of type {@link LicenseReferenceResponse}
     */
    public ArrayList<LicenseReferenceResponse> getLicenseReferences() {
        return mLicenseReferences;
    }

    /**
     * Get Licensing information for the given unique asset identifier.
     * @param contentId
     *            Asset unique identifier
     * @return object of type {@link LicenseContent}
     * @throws StockException
     *             if licensing information is not available for given asset
     *             identifier
     *
     */
    public LicenseContent getContent(final String contentId)
            throws StockException {
        if (this.mContentMap.containsKey(contentId)) {
            return this.mContentMap.get(contentId);
        } else {
            throw new StockException(
                    "Content not found for the given content id");
        }
    }

    /**
     * Get list of licensing information for all assets.
     * @return ArrayList of licensing information
     */
    public ArrayList<LicenseContent> getContents() {
        ArrayList<LicenseContent> contentList = new ArrayList<LicenseContent>(
                this.mContentMap.values());
        return contentList;
    }

    /**
     * Sets Information about licenses available for the user.
     *
     * @param entitlement
     *            Information about licenses available for the user
     */
    @JsonSetter("available_entitlement")
    public void setEntitlement(final LicenseEntitlement entitlement) {
        this.mEntitlement = entitlement;
    }

    /**
     * Sets Information about the user's purchasing options for the asset.
     *
     * @param purchaseOptions
     *            Information about the user's purchasing options for the asset.
     */
    @JsonSetter("purchase_options")
    public void setPurchaseOptions(
            final LicensePurchaseOptions purchaseOptions) {
        this.mPurchaseOptions = purchaseOptions;
    }

    /**
     * Sets Information about the user.
     *
     * @param memberInfo
     *            Information about the user.
     */
    @JsonSetter("member")
    public void setMemberInfo(final LicenseMemberInfo memberInfo) {
        this.mMemberInfo = memberInfo;
    }

    /**
     * Sets List of license references of the user.
     *
     * @param licenseReferences
     *            List of license references of the user.
     */
    @JsonSetter("cce_agency")
    public void setLicenseReferences(
            final ArrayList<LicenseReferenceResponse> licenseReferences) {
        this.mLicenseReferences = licenseReferences;
    }

    /**
     * Sets Mapping from Asset unique identifier to Asset Licensing information.
     * @param contentsNode object containing licensing information
     * @throws Exception if could not parse contentsNode object
     */
    @JsonSetter("contents")
    public void setContent(final JsonNode contentsNode) throws Exception {

            Iterator<String> nodeIterator = contentsNode.fieldNames();
            ObjectMapper mapper = new ObjectMapper();
            this.mContentMap = new HashMap<String, LicenseContent>();
            while (nodeIterator.hasNext()) {
                String key = nodeIterator.next();
                JsonNode contentNode = contentsNode.get(key);
                LicenseContent licenseContent = mapper
                  .readValue(contentNode.toString(), LicenseContent.class);
                this.mContentMap.put(key, licenseContent);
            }
    }
}
