/*******************************************************************************
 * Copyright 2017 Adobe Systems Incorporated. All rights reserved.
 * This file is licensed to you under the Apache License, Version 2.0
 * (the "License") you may not use this file except in compliance with the
 * License. You may obtain a copy of the License at
 * http://www.apache.org/licenses/LICENSE-2.0
 ******************************************************************************/
package com.adobe.stock.enums;
/**
 * All specified orientation that can be used in search parameters.
*/
public enum AssetOrientation {

    /**
     * Only horizontal images.
     */
    HORIZONTAL("horizontal"),

    /**
     * Only vertical images.
     */
    VERTICAL("vertical"),

    /**
     * Only square images.
     */
    SQUARE("square"),

    /**
     *  All image orientations .
     */
    ALL("all");

    /**
     * String value of orientation enum.
     */
    private String mValue;

    /**
     * Constructor.
     * @param value String value of this enum
     */
    AssetOrientation(final String value) {
        this.mValue = value;
    }

    @Override
    public String toString() {
        return mValue;
    }
}
