/*******************************************************************************
 * Copyright 2017 Adobe Systems Incorporated. All rights reserved.
 * This file is licensed to you under the Apache License, Version 2.0
 * (the "License") you may not use this file except in compliance with the
 * License. You may obtain a copy of the License at
 * http://www.apache.org/licenses/LICENSE-2.0
 ******************************************************************************/
package com.adobe.stock.enums;
/**
 * All specified asset age groups that can be used in search parameters
 * for searching assets.
 */
public enum AssetAge {

    /**
     * Only assets up to 1 week old.
     */
    ONE_WEEK("1w"),

    /**
     * Only assets up to 1 month old.
     */
    ONE_MONTH("1m"),

    /**
     * Only assets up to 6 month old.
     */
    SIX_MONTH("6m"),

    /**
     * Only assets up to 1 year old.
    */
    ONE_YEAR("1y"),

    /**
     * Only assets up to 2 year old.
     */
    TWO_YEAR("2y"),

    /**
     * Assets of any age.
     */
    ALL("all");

    /**
     * String value of age enum.
     */
    private String mValue;

    /**
     * Construct of this enum.
     * @param value string value of age enum
     */
    AssetAge(final String value) {
        this.mValue = value;
    }

    @Override
    public String toString() {
        return this.mValue;
    }
}
