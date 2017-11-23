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
 * The size of the asset, indicating whether it is the free
 *  complementary size or the original full-sized asset.
 */
public enum AssetLicenseSize {
    /**
     * Free complementary sized Asset.
     */
    COMPLEMENTARY("Comp"),
    /**
     * Original full-sized asset.
     */
    ORIGINAL("Original");
    /**
     * String value of {@link AssetLicenseSize} enum.
     */
    private String mValue;
    /**
     * Constructor.
     * @param value String value of {@link AssetLicenseSize} enum
     */
    AssetLicenseSize(final String value) {
        this.mValue = value;
    }

    @Override
    public String toString() {
        return this.mValue;
    }
    /**
     * Method to convert String value to enum.
     * @param value string value of enum
     * @return enum of type {@link AssetLicenseSize}
     */
    @JsonCreator
    public static AssetLicenseSize fromString(final String value) {
        switch (value) {
        case "Comp":
            return AssetLicenseSize.COMPLEMENTARY;
        case "Original":
            return AssetLicenseSize.ORIGINAL;
        default:
            return null;
        }
    }

}
