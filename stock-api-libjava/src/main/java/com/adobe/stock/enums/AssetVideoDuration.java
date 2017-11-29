/*******************************************************************************
 * Copyright 2017 Adobe Systems Incorporated. All rights reserved.
 * This file is licensed to you under the Apache License, Version 2.0
 * (the "License") you may not use this file except in compliance with the
 * License. You may obtain a copy of the License at
 * http://www.apache.org/licenses/LICENSE-2.0
 ******************************************************************************/
package com.adobe.stock.enums;

/**All specified video duration that can be used in search parameters
 * for searching videos whose duration is no longer than the specified
 * duration in seconds.
 */
public enum AssetVideoDuration {

    /**
     * Only videos up to 10 seconds.
     */
    TEN("10"),

    /**
     * Only videos up to 20 seconds.
     */
    TWENTY("20"),

    /**
     * Only videos up to 30 seconds.
     */
    THIRTY("30"),

    /**
     * Only videos longer than 30 seconds.
     */
    ABOVE_THIRTY("30-"),

    /**
     * Videos of all durations.
     */
    ALL("all");

    /**
     * String value of video duration.
     */
    private String mValue;

    /**Constructor.
     * @param value String value of AssetVideoDuration enum.
     */
    AssetVideoDuration(final String value) {
        this.mValue = value;
    }

    @Override
    public String toString() {
        return mValue;
    }
}
