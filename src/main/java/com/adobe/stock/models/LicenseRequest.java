/*******************************************************************************
 * Copyright 2017 Adobe Systems Incorporated. All rights reserved.
 * This file is licensed to you under the Apache License, Version 2.0
 * (the "License") you may not use this file except in compliance with the
 * License. You may obtain a copy of the License at
 * http://www.apache.org/licenses/LICENSE-2.0
 ******************************************************************************/
package com.adobe.stock.models;

import com.adobe.stock.annotations.SearchParamURLMapperInternal;
import com.adobe.stock.enums.AssetLicenseState;
import com.adobe.stock.enums.AssetPurchaseState;

/**
 * Request object : for licensing assets, for getting licensing information
 * about a specific asset for specific user, for notifying the system when a
 * user abandons a licensing operation, for getting the licensing capabilities
 * for a specific user.
 */
public final class LicenseRequest {
    /**
     * Asset's unique identifer.
     */
    @SearchParamURLMapperInternal(value = "content_id")
    private Integer mContentId;

    /**
     * The Adobe Stock licensing state for the asset. Use only with
     * Content/Info, Content/License, and Member/Profile. <b>Not required for
     * Member/Abondon api. If passed, field will be ignored while calling this
     * api.</b>
     * @see AssetLicenseState
     */
    @SearchParamURLMapperInternal(value = "license")
    private AssetLicenseState mLicenseState;

    /**
     * Use only with Member/Profile. Optional Location language code for the API
     * to use when returning localized messages. The API can usually get the
     * user's default locale through the Authorization header. This value
     * overrides that or provides a locale if not available through
     * Authorization
     */
    @SearchParamURLMapperInternal(value = "locale")
    private String mLocale;

    /**
     * Use only with Member/Abandon. The asset purchase state from the
     * Member/Profile results.
     * @see AssetPurchaseState
     */
    @SearchParamURLMapperInternal(value = "state")
    private AssetPurchaseState mPurchaseState;

    /**
     * Panel version >=2.10 will send this parameter to get the "message_ccx"
     * field in the JSON response.
     */
    @SearchParamURLMapperInternal(value = "format")
    private String mFormat;

    /**
     * Array of license references of type LicenseReference.
     * Must be in the POST body.
     * @see LicenseReference
     */
    private LicenseReference[] mLicenseReference;

    /**
     * @return Asset's unique identifer.
     */
    public Integer getContentId() {
        return mContentId;
    }
    /**
     * Default Constructor.
     */
    public LicenseRequest() {
    }
    /**
     * @param contentId
     *            Asset's unique identifer.
     * @throws IllegalArgumentException if content id is negative.
     * @return Object of LicenseRequest
     */
    public LicenseRequest setContentId(final int contentId) {
        if (contentId < 0) {
            throw new IllegalArgumentException("Should be a valid Content ID");
        }
        this.mContentId = contentId;
        return this;
    }

    /**
     * @return The Adobe Stock licensing state for the asset of type
     *         AssetLicenseState.<b>Not required for Member/Abondon api. If
     *         passed, field will be ignored while calling this api.</b>
     * @see AssetLicenseState
     */
    public AssetLicenseState getLicenseState() {
        return mLicenseState;
    }

    /**
     * @param licenseState
     *            The Adobe Stock licensing state for the asset of type
     *            AssetLicenseState.<b>Not required for Member/Abondon api. If
     *            passed, field will be ignored while calling this api.</b>
     * @return Object of LicenseRequest
     * @throws IllegalArgumentException if License state is set to null.
     * @see AssetLicenseState
     */
    public LicenseRequest setLicenseState(final AssetLicenseState
                                                licenseState) {
        if (licenseState == null) {
            throw new IllegalArgumentException("License State cannot be null");
        }
        this.mLicenseState = licenseState;
        return this;
    }

    /**
     * @return Location language code for the API.
     */
    public String getLocale() {
        return mLocale;
    }

    /**
     * @param locale
     *            Location language code for the API.
     * @throws IllegalArgumentException if locale is set to null or empty.
     * @return Object of LicenseRequest
     */
    public LicenseRequest setLocale(final String locale) {
        if (locale == null || locale.trim().isEmpty()) {
            throw new IllegalArgumentException(
                    "Locale cannot be null or empty");
        }
        this.mLocale = locale;
        return this;
    }

    /**
     * @return The asset purchase state from the Member/Profile results of type
     *         AssetPurchaseState.
     * @see AssetPurchaseState
     */
    public AssetPurchaseState getPurchaseState() {
        return mPurchaseState;
    }

    /**
     * @param state
     *            The asset purchase state from the Member/Profile results of
     *            type AssetPurchaseState.
     * @throws IllegalArgumentException if purchase state set to null
     * @return Object of LicenseRequest
     * @see AssetPurchaseState
     */
    public LicenseRequest setPurchaseState(final AssetPurchaseState state) {
        if (state == null) {
            throw new IllegalArgumentException(
                    "Purchase state cannot be null");
        }
        this.mPurchaseState = state;
        return this;
    }

    /**
     * @return true if format is set to message_ccx else return false;
     */
    public boolean getFormat() {
        return mFormat.equals("message_ccx");
    }

    /**
     * @param isSetformat
     *            sets format to message_ccx if it is true else null.
     * @throws IllegalArgumentException if null value is passed.
     * @return Object of LicenseRequest
     */
    public LicenseRequest setFormat(final Boolean isSetformat) {
        if (isSetformat == null) {
          throw new IllegalArgumentException(
                    "Format can be set to true or false only.");
        }
        if (isSetformat) {
            mFormat = "message_ccx";
        } else {
            mFormat = null;
        }
        return this;
    }

    /**
     * @return Array of licensing references having id and values pair of type
     *         LicenseReference.
     * @see LicenseReference
     */
    public LicenseReference[] getLicenseReference() {
        if (mLicenseReference == null) {
            return null;
        }
        return mLicenseReference.clone();
    }

    /**
     * @param licenseReference
     *            set array of id and values of type LicenseReference
     * @see LicenseReference
     * @throws IllegalArgumentException if null value is passed.
     * @return Object of LicenseRequest
     */
    public LicenseRequest setLicenseReference(final
            LicenseReference[] licenseReference) {
        if (licenseReference == null) {
            throw new IllegalArgumentException(
                    "LicenseReference array cannot be null");
        }

        this.mLicenseReference = licenseReference.clone();
        return this;
    }
}
