/*******************************************************************************
 * Copyright 2017 Adobe Systems Incorporated. All rights reserved.
 * This file is licensed to you under the Apache License, Version 2.0
 * (the "License") you may not use this file except in compliance with the
 * License. You may obtain a copy of the License at
 * http://www.apache.org/licenses/LICENSE-2.0
 ******************************************************************************/
package com.adobe.stock.enums;

import com.fasterxml.jackson.annotation.JsonCreator;

/**
 * User's purchase relationship to an asset.
 */
public enum AssetPurchaseState {

    /**
     * User has not at any time in the past purchased the asset.
     */
    NOT_PURCHASED("not_purchased"),

    /**
     * User has at some time in the past purchased the asset.
     */
    PURCHASED("purchased"),

    /**
     * User attempted to buy the asset and for some reason
     * the order did not go through.
     */
    CANCELLED("cancelled"),

    /**
     * User must go to the Adobe Stock site to buy plan or asset.
     */
    NOT_POSSIBLE("not_possible"),

    /**
     * User bought asset within the current session.
     */
    JUST_PURCHASED("just_purchased"),

    /**
     * Adobe Stock has a payment instrument on file for the
     * user and can bill the user for additional purchases.
     */
    OVERAGE("overage");

    /**
     * Asset Purchase state.
     */
    private String mState;

    /**
     * @param assetState Asset Purchase state.
     */
    AssetPurchaseState(final String assetState) {
        mState = assetState;
    }

    @Override
    public String toString() {
        return mState;
    }
    /**
     * Method to convert string value to enum.
     * @param value string value of enum
     * @return enum of type AssetPurchaseState
     */
    @JsonCreator
    public static AssetPurchaseState fromString(final String value) {
        switch (value) {
        case "not_purchased":
            return AssetPurchaseState.NOT_PURCHASED;
        case "purchased":
            return AssetPurchaseState.PURCHASED;
        case "cancelled":
            return AssetPurchaseState.CANCELLED;
        case "not_possible":
            return AssetPurchaseState.NOT_POSSIBLE;
        case "just_purchased":
            return AssetPurchaseState.JUST_PURCHASED;
        case "overage":
            return AssetPurchaseState.OVERAGE;
        default:
            return null;
        }
    }
}
