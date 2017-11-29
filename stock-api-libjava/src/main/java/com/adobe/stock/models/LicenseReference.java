/*******************************************************************************
 * Copyright 2017 Adobe Systems Incorporated. All rights reserved.
 * This file is licensed to you under the Apache License, Version 2.0
 * (the "License") you may not use this file except in compliance with the
 * License. You may obtain a copy of the License at
 * http://www.apache.org/licenses/LICENSE-2.0
 ******************************************************************************/
package com.adobe.stock.models;

import com.adobe.stock.annotations.SearchParamURLMapperInternal;

/**
 * @author pragatijohri
 * Specifies license references.
 * The license reference "id" values can be found with
 * the "/Rest/Libraries/1/Member/Profile" API.
 * Must be in the POST body.
 */
public final class LicenseReference {
    /**
     * The license reference id.
     */
    @SearchParamURLMapperInternal(value = "id")
    private Integer mId;

    /**
     * Value of license reference "id" .
     * Value can be found with the "/Rest/Libraries/1/Member/Profile" API.
     */
    @SearchParamURLMapperInternal(value = "value")
    private String mValue;

    /**
     * Default constructor.
     */
    public LicenseReference() {
    }
    /**
     * @return license reference id.
     */
    public Integer getLicenseReferenceId() {
        return mId;
    }

    /**
     * @param licenseId license reference id.
     * @return Object of LicenseReference
     */
    public LicenseReference setLicenseReferenceId(final int licenseId) {
        if (licenseId < 0) {
            throw new IllegalArgumentException("Should be a valid"
                    + " license reference id");
        }
        this.mId = licenseId;
        return this;
    }

    /**
     * @return Value of corresponding license id.
     */
    public String getLicenseReferenceValue() {
        return mValue;
    }
    /**
     * @param licenseValue Value of license reference id.
     * @return Object of LicenseReference
     */
    public LicenseReference setLicenseReferenceValue(
            final String licenseValue) {
        if (licenseValue == null || licenseValue == "") {
            throw new IllegalArgumentException(""
                    + "License reference value cannot be null or empty");
        }
        this.mValue = licenseValue;
        return this;
    }

}
