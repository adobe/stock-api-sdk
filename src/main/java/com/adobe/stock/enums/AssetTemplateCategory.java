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
 * All specified template category identifiers that can be used in Search
 * Parameters for searching assets, if asset is a template.
 */
public enum AssetTemplateCategory {

    /**
     * Mobile Template.
     */
    MOBILE(1),

    /**
     * Web Template.
     */
    WEB(2),

    /**
     * Print Template.
     */
    PRINT(3),

    /**
     * Photo Template.
     */
    PHOTO(4),

    /**
     * Film Template.
     */
    FILM(5),

    /**
     * Art Template.
     */
    ART(6);

    /**
     * Value of Template Category Id.
     */
    private int mValue;

    /**Constructor.
     * @param value integer value of template category id
     */
    AssetTemplateCategory(final int value) {
        mValue = value;
    }

    @Override
    public String toString() {
        return String.valueOf(mValue);
    }

    /**
     * Method to convert string value to enum.
     * @param value string value of enum
     * @return enum of type AssetTemplateCategory
     */
    @JsonCreator
    public static AssetTemplateCategory fromString(final String value) {
        switch (value) {
        case "1":
            return AssetTemplateCategory.MOBILE;
        case "2":
            return AssetTemplateCategory.WEB;
        case "3":
            return AssetTemplateCategory.PRINT;
        case "4":
            return AssetTemplateCategory.PHOTO;
        case "5":
            return AssetTemplateCategory.FILM;
        case "6":
            return AssetTemplateCategory.ART;
        default:
            return null;
        }
    }

}
