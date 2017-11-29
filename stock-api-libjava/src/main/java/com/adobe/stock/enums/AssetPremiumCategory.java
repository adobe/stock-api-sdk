/*******************************************************************************
 * Copyright 2017 Adobe Systems Incorporated. All rights reserved.
 * This file is licensed to you under the Apache License, Version 2.0
 * (the "License") you may not use this file except in compliance with the
 * License. You may obtain a copy of the License at
 * http://www.apache.org/licenses/LICENSE-2.0
 ******************************************************************************/
package com.adobe.stock.enums;

/**
 * Asset's premium (pricing) level that can be used in SearchParameters
 * for searching assets.
 */

public enum AssetPremiumCategory {

    /**
     *Assets with a premium level greater than 1.
     */
    TRUE("true"),

    /**
     *Assets with a premium level of either 0 (core) or 1 (free).
     */
    FALSE("false"),

    /**
     * Everything.
     */
    ALL("all");


    /**
     * String value of AssetPremiumCategory enum.
     */
    private String mValue;

    /**Constructor.
     * @param value String value of AssetPremiumCategory enum
     */
    AssetPremiumCategory(final String value) {
        this.mValue = value;

    }

    @Override
    public String toString() {
        return mValue;
    }
}
