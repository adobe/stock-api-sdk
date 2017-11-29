/*******************************************************************************
 * Copyright 2017 Adobe Systems Incorporated. All rights reserved.
 * This file is licensed to you under the Apache License, Version 2.0
 * (the "License") you may not use this file except in compliance with the
 * License. You may obtain a copy of the License at
 * http://www.apache.org/licenses/LICENSE-2.0
 ******************************************************************************/
package com.adobe.stock.models;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonSetter;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
/**
 * Information about licenses available for the user.
 */
@JsonIgnoreProperties(ignoreUnknown = true)
public final class LicenseEntitlement {
    /**
     * Quantity of remaining licenses available for the user.
     */
    private Integer mQuota;
    /**
     * Stock Internal ID to know which kind of product can
     *  be used for licensing.
     */
    private Integer mLicenseTypeId;
    /**
     * true if the selected entitlement is for an organization
     *  and this organization is generation 2.
     */
    private Boolean mHasCreditModel;
    /**
     * true if the selected entitlement is for an organization
     * and this organization is generation 3.
     */
    private Boolean mHasAgencyModel;
    /**
     * true if the selected entitlement for purchasing is one
     *  of an organization.
     */
    private Boolean mIsCce;

    /**
     * Full quota of the user available entitlements.
     */
    private LicenseEntitlementQuota mFullEntitlementQuota;

    /**
     * Default Constructor.
     */
    public LicenseEntitlement() {
    }
    /**
     * Get quantity of remaining licenses available for the user.
     * @return license quantity of type Integer
     */
    public Integer getQuota() {
        return mQuota;
    }

    /**
     * Sets quantity of remaining licenses available for the user.
     * @param quota license quantity
     */
    @JsonSetter("quota")
    public void setQuota(final Integer quota) {
        this.mQuota = quota;
    }
    /**
     * Get Full quota of the user available entitlements.
     * @return object of type {@link LicenseEntitlementQuota}
     */
    public LicenseEntitlementQuota getFullEntitlementQuota() {
        return mFullEntitlementQuota;
    }
    /**
     * Sets Full quota of the user available entitlements.
     * @param node object containing full quota of user available entitlements.
     * @throws Exception if could not parse node object
     */
    @JsonSetter("full_entitlement_quota")
    public void setFullEntitlementQuota(
            final JsonNode node) throws Exception {
        if (node != null && node.isObject()) {
                ObjectMapper mapper = new ObjectMapper();
                this.mFullEntitlementQuota = mapper.readValue(
                        node.toString(), LicenseEntitlementQuota.class);
        }
    }
    /**
     * Get Stock Internal ID to know which kind of product
     *  can be used for licensing.
     * @return Stock Internal ID of type Integer
     */
    public Integer getLicenseTypeId() {
        return mLicenseTypeId;
    }
    /**
     * Sets Stock Internal ID to know which kind of product
     *  can be used for licensing.
     * @param licenseTypeId Stock Internal ID.
     */
    @JsonSetter("license_type_id")
    public void setLicenseTypeId(final Integer licenseTypeId) {
        this.mLicenseTypeId = licenseTypeId;
    }
    /**
     * Returns true if the selected entitlement is for an organization
     *  and this organization is generation 2.
     * @return true if the selected entitlement is for an organization
     *  and this organization is generation 2 else false
     */
    public Boolean getHasCreditModel() {
        return mHasCreditModel;
    }
    /**
     * Sets true if the selected entitlement is for an organization and
     *  this organization is generation 2.
     * @param hasCreditModel true if the selected entitlement is for an
     *  organization and this organization is generation 2 else false
     */
    @JsonSetter("has_credit_model")
    public void setHasCreditModel(final Boolean hasCreditModel) {
        this.mHasCreditModel = hasCreditModel;
    }
    /**
     * Returns true if the selected entitlement is for an organization
     *  and this organization is generation 3.
     * @return true if the selected entitlement is for an organization
     *  and this organization is generation 3 else false
     */
    public Boolean getHasAgencyModel() {
        return mHasAgencyModel;
    }
    /**
     * Sets true if the selected entitlement is for an organization and
     *  this organization is generation 3.
     * @param hasAgencyModel true if the selected entitlement is for an
     *  organization and this organization is generation 3 else false
     */
    @JsonSetter("has_agency_model")
    public void setHasAgencyModel(final Boolean hasAgencyModel) {
        this.mHasAgencyModel = hasAgencyModel;
    }
    /**
     * Get true if the selected entitlement for purchasing is one
     *  of an organization.
     * @return true if the selected entitlement for purchasing is one
     *  of an organization else false
     */
    public Boolean getIsCce() {
        return mIsCce;
    }
    /**
     * Sets true if the selected entitlement for purchasing is one of
     *  an organization.
     *  @param isCce true if the selected entitlement for purchasing
     *   is one of an organization else false
     */
    @JsonSetter("is_cce")
    public void setIsCce(final Boolean isCce) {
        this.mIsCce = isCce;
    }

}
