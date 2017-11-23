/*******************************************************************************
 * Copyright 2017 Adobe Systems Incorporated. All rights reserved.
 * This file is licensed to you under the Apache License, Version 2.0
 * (the "License") you may not use this file except in compliance with the
 * License. You may obtain a copy of the License at
 * http://www.apache.org/licenses/LICENSE-2.0
 ******************************************************************************/
package com.adobe.stock.enums;

/**
 * Asset's model or property releases that can be used in search parameters for
 * searching assets.
 */
public enum AssetHasReleases {

    /**
     * Assets with releases.
     */
    TRUE("true"),

    /**
     * Assets without releases.
     */
    FALSE("false"),

    /**
     * Regardless of release status.
     */
    ALL("all");

    /**
     * String value of 'HasReleases' enum.
     */

    private String mValue;

    /**
     * Constructor of this enum.
     *
     * @param value
     *            String value of 'HasReleases' enum
     */
    AssetHasReleases(final String value) {
        this.mValue = value;
    }

    @Override
    public String toString() {
        return mValue;
    }

}
