/*******************************************************************************
 * Copyright 2017 Adobe Systems Incorporated. All rights reserved.
 * This file is licensed to you under the Apache License, Version 2.0
 * (the "License") you may not use this file except in compliance with the
 * License. You may obtain a copy of the License at
 * http://www.apache.org/licenses/LICENSE-2.0
 ******************************************************************************/
package com.adobe.stock.enums;

/**
 * All specified thumbnail sizes that can be used in search parameters
 * for found assets.
 */
public enum LicenseHistoryThumbnailSize {

    /**
     * Thumbnail size of 110 pixels.
     */
    MEDIUM(110),

    /**
     * Thumbnail size of 160 pixels.
     */
    BIG(160),

    /**
     * Thumbnail size of 220 pixels.
     */
    LARGE(220),

    /**
     * Thumbnail size of 240 pixels.
     */
    VERY_LARGE(240),

    /**
     * Thumbnail size of 500 pixels.
     */
    XL(500),

    /**
     * Thumbnail size of 1000 pixels.
     */
    XXL(1000);

    /**
     * Thumbnail size in pixels.
     */
    private int mValue;

    /**Constructor.
     * @param value integer value of LicenseHistoryThumbnailSize enum.
     */
    LicenseHistoryThumbnailSize(final int value) {
        this.mValue = value;
    }

    @Override
    public String toString() {
        return String.valueOf(mValue);
    }
}
