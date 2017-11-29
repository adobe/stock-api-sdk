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
 * Adobe Stock licensing state for the asset.
 */
public enum AssetLicenseState {
    /**
     * License for the full-resolution asset.
     */
    STANDARD("Standard"),
    /**
     * Extended license.
     */
    EXTENDED("Extended"),
    /**
     * Video HD license.
     */
    VIDEO_HD("Video_HD"),
    /**
     * Video 4K license.
     */
    VIDEO_4K("Video_4K"),
    /**
     * License for a medium-size asset approximately 1600x1200 pixels.
     */
    STANDARD_M("Standard_M"),
    /**
     * No license applies.
     */
    EMPTY("");

    /**
     * String value of license state.
     */
    private String mValue;

    /**
     * Constructor for this enum.
     * @param value license state of type String
     */
    AssetLicenseState(final String value) {
        this.mValue = value;
    }

    @Override
    public String toString() {
        return this.mValue;
    }

    /**
     * Method to convert string value to enum.
     *
     * @param value
     *            string value of enum
     * @return enum of type {@link AssetLicenseState}
     */
    @JsonCreator
    public static AssetLicenseState fromString(final String value) {
        switch (value) {
        case "Standard":
            return AssetLicenseState.STANDARD;
        case "Extended":
            return AssetLicenseState.EXTENDED;
        case "Video_HD":
            return AssetLicenseState.VIDEO_HD;
        case "Video_4K":
            return AssetLicenseState.VIDEO_4K;
        case "Standard_M":
            return AssetLicenseState.STANDARD_M;
        case "":
            return AssetLicenseState.EMPTY;
        default:
            return null;
        }
    }
}
