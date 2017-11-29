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
 * Type of the asset.
 */
public enum AssetType {
    /**
     * Asset of type photos.
     */
    PHOTOS(1),
    /**
     * Asset of type illustrations.
     */
    ILLUSTRATIONS(2),
    /**
     * Asset of type vectors.
     */
    VECTORS(3),
    /**
     * Asset of type videos.
     */
    VIDEOS(4),
    /**
     * Asset of type instant photos.
     */
    INSTANT_PHOTOS(5),
    /**
     * Asset of type 3D.
     */
    THREE_DIMENSIONAL(6),
    /**
     * Asset of type template.
     */
    TEMPLATES(7);

    /**
     * Integer value of media type.
     */
    private int mValue;

    /**
     * Constructor for this enum.
     * @param value media type integer value
     */
    AssetType(final int value) {
        this.mValue = value;
    }

    @Override
    public String toString() {
        return String.valueOf(this.mValue);
    }

    /**
     * Method to convert string value to enum.
     * @param value string value of enum
     * @return enum of type AssetType
     */
    @JsonCreator
    public static AssetType fromString(final String value) {
        switch (value) {
        case "1":
            return AssetType.PHOTOS;
        case "2":
            return AssetType.ILLUSTRATIONS;
        case "3":
            return AssetType.VECTORS;
        case "4":
            return AssetType.VIDEOS;
        case "5":
            return AssetType.INSTANT_PHOTOS;
        case "6":
            return AssetType.THREE_DIMENSIONAL;
        case "7":
            return AssetType.TEMPLATES;
        default:
            return null;
        }
    }
}
