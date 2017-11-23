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
 * Asset's premium (pricing) level.
 */
public enum AssetPremiumLevel {
    /**
     * Core asset.
     */
    CORE(0),
    /**
     * Free asset.
     */
    FREE(1),
    /**
     * Premium level 1 asset.
     */
    PREMIUM1(2),
    /**
     * Premium level 2 asset.
     */
    PREMIUM2(3),
    /**
     * Premium level 3 asset.
     */
    PREMIUM3(4);

    /**
     * Integer value of premium level.
     */
    private int mValue;
    /**
     * Constructor for this enum.
     * @param value premium level integer value
     */
    AssetPremiumLevel(final int value) {
        this.mValue = value;
    }

    @Override
    public String toString() {
        return String.valueOf(this.mValue);
    }

    /**
     * Method to convert string value to enum.
     * @param value string value of enum
     * @return enum of type {@link AssetPremiumLevel}
     */
    @JsonCreator
    public static AssetPremiumLevel fromString(final String value) {
        switch (value) {
        case "0":
            return AssetPremiumLevel.CORE;
        case "1":
            return AssetPremiumLevel.FREE;
        case "2":
            return AssetPremiumLevel.PREMIUM1;
        case "3":
            return AssetPremiumLevel.PREMIUM2;
        case "4":
            return AssetPremiumLevel.PREMIUM3;
        default:
            return null;
        }
    }
}
