/*******************************************************************************
 * Copyright 2017 Adobe Systems Incorporated. All rights reserved.
 * This file is licensed to you under the Apache License, Version 2.0
 * (the "License") you may not use this file except in compliance with the
 * License. You may obtain a copy of the License at
 * http://www.apache.org/licenses/LICENSE-2.0
 ******************************************************************************/
package com.adobe.stock.models;

import com.adobe.stock.enums.AssetPurchaseState;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonSetter;
/**
 * Information about the user's purchasing options for the asset.
 */
@JsonIgnoreProperties(ignoreUnknown = true)
public final class LicensePurchaseOptions {
    /**
     * User's purchase relationship to an asset.
     */
    private AssetPurchaseState mPurchaseState;
    /**
     * Whether a purchase in process requires going to
     *  the Adobe Stock site for completion.
     */
    private Boolean mRequiresCheckout;
    /**
     * Message to display to your user in response to a licensing API query.
     */
    private String mMessage;
    /**
     * The URL to see purchase options plan.
     */
    private String mPurchaseUrl;
    /**
     * Default Constructor.
     */
    public LicensePurchaseOptions() {
    }
    /**
     * Get user's purchase relationship to an asset.
     * @return asset purchase state
     */
    public AssetPurchaseState getPurchaseState() {
        return mPurchaseState;
    }
    /**
     * Get whether a purchase in process requires going to
     *  the Adobe Stock site for completion.
     * @return true if requires going to Adobe Stock site else false
     */
    public Boolean getRequiresCheckout() {
        return mRequiresCheckout;
    }
    /**
     * Get Message to display to your user in response to a licensing API query.
     * @return message of type String
     */
    public String getMessage() {
        return mMessage;
    }
    /**
     * Get the URL to see purchase options plan.
     * @return url of type String
     */
    public String getPurchaseUrl() {
        return mPurchaseUrl;
    }
    /**
     * Sets user's purchase relationship to an asset.
     * @param purchaseState {@link AssetPurchaseState} object
     *  specifying asset purchase status
     */
    @JsonSetter("state")
    public void setPurchaseState(final AssetPurchaseState purchaseState) {
        this.mPurchaseState = purchaseState;
    }
    /**
     * Sets whether a purchase in process requires going to
     *  the Adobe Stock site for completion.
     * @param requiresCheckout true if purchase in process requires going to
     *  the Adobe Stock site else false
     */
    @JsonSetter("requires_checkout")
    public void setRequiresCheckout(final Boolean requiresCheckout) {
        this.mRequiresCheckout = requiresCheckout;
    }
    /**
     * Sets Message to display to your user in response to a
     * licensing API query.
     * @param message displayed message
     */
    @JsonSetter("message")
    public void setMessage(final String message) {
        this.mMessage = message;
    }
    /**
     * Sets the URL to see purchase options plan.
     * @param purchaseUrl URL to see purchase options plan
     */
    @JsonSetter("url")
    public void setPurchaseUrl(final String purchaseUrl) {
        this.mPurchaseUrl = purchaseUrl;
    }
}
