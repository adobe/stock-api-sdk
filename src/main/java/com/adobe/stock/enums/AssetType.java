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
    VIDEOS(4);
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
        default:
            return null;
        }
    }
}
