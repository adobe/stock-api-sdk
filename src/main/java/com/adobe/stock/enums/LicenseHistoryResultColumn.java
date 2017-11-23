/*******************************************************************************
 * Copyright 2017 Adobe Systems Incorporated. All rights reserved.
 * This file is licensed to you under the Apache License, Version 2.0
 * (the "License") you may not use this file except in compliance with the
 * License. You may obtain a copy of the License at
 * http://www.apache.org/licenses/LICENSE-2.0
 ******************************************************************************/
package com.adobe.stock.enums;

/**
 * Each enum represent one of the possible result columns which can be used
 * to request the specific information for the assets returned in the license
 * history results.
 */
public enum LicenseHistoryResultColumn {

    /**
     * URL for the requested thumbnail of the 110 px in size.
     */
    THUMBNAIL_110_URL("thumbnail_110_url"),

    /**
     * Width for the thumbnail of the 110px in size.
     */
    THUMBNAIL_110_WIDTH("thumbnail_110_width"),

    /**
     * Height for the thumbnail of the 110px size.
     */
    THUMBNAIL_110_HEIGHT("thumbnail_110_height"),

    /**
     * URL for the requested thumbnail of the 160 px in size.
     */
    THUMBNAIL_160_URL("thumbnail_160_url"),

    /**
     * Width for the thumbnail of the 160px in size.
     */
    THUMBNAIL_160_WIDTH("thumbnail_160_width"),

    /**
     * Height for the thumbnail of the 160px size.
     */
    THUMBNAIL_160_HEIGHT("thumbnail_160_height"),

    /**
     * URL for the requested thumbnail of the 220 px in size.
     */
    THUMBNAIL_220_URL("thumbnail_220_url"),

    /**
     * Width for the thumbnail of the 220px in size.
     */
    THUMBNAIL_220_WIDTH("thumbnail_220_width"),

    /**
     * Height for the thumbnail of the 220px size.
     */
    THUMBNAIL_220_HEIGHT("thumbnail_220_height"),

    /**
     * URL for the requested thumbnail of the 240 px in size.
     */
    THUMBNAIL_240_URL("thumbnail_240_url"),

    /**
     * Width for the thumbnail of the 240px in size.
     */
    THUMBNAIL_240_WIDTH("thumbnail_240_width"),

    /**
     * Height for the thumbnail of the 240px size.
     */
    THUMBNAIL_240_HEIGHT("thumbnail_240_height"),

    /**
     * URL for the requested thumbnail of the 500 px in size.
     */
    THUMBNAIL_500_URL("thumbnail_500_url"),

    /**
     * Width for the thumbnail of the 500px in size.
     */
    THUMBNAIL_500_WIDTH("thumbnail_500_width"),

    /**
     * Height for the thumbnail of the 500px size.
     */
    THUMBNAIL_500_HEIGHT("thumbnail_500_height"),

    /**
     * URL for the requested thumbnail of the 1000 px in size.
     */
    THUMBNAIL_1000_URL("thumbnail_1000_url"),

    /**
     * Width for the thumbnail of the 1000px in size.
     */
    THUMBNAIL_1000_WIDTH("thumbnail_1000_width"),

    /**
     * Height for the thumbnail of the 1000px size.
     */
    THUMBNAIL_1000_HEIGHT("thumbnail_1000_height");

    /**
     * The corresponding value for the LicenseHistroyResultColumn enum.
     */
    private String mValue;
    /**
     * Constructs the LicenseHistoryResultColumn enum with
     * provided string value.
     *
     * @param value
     *            the string value of the enum
     */
    LicenseHistoryResultColumn(final String value) {
        this.mValue = value;
    }

    /**
     * Returns the string value of the enum.
     */
    @Override
    public String toString() {
        return this.mValue;
    }
}
