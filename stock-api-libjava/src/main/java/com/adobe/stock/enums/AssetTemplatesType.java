package com.adobe.stock.enums;

import com.fasterxml.jackson.annotation.JsonCreator;

/**
 * All specified template types that can be used in Search
 * Parameters for searching assets, if asset is a template.
 */
public enum AssetTemplatesType {

    /**
     * Adobe Photoshop Template.
     */
    PSDT(1),

    /**
     * Adobe Illustrator Teamplate.
     */
    AIT(2),

    /**
     * Adobe InDesign Template.
     */
    INDT(3);

    /**
     * Value of Template Type.
     */
    private int mValue;

    /**Constructor.
     * @param value integer value of template type
     */
    AssetTemplatesType(final int value) {
        mValue = value;
    }

    @Override
    public String toString() {
        return String.valueOf(mValue);
    }

    /**
     * Method to convert string value to enum.
     * @param value string value of enum
     * @return enum of type AssetTemplatesType
     */
    @JsonCreator
    public static AssetTemplatesType fromString(final String value) {
        switch (value) {
        case "1":
            return AssetTemplatesType.PSDT;
        case "2":
            return AssetTemplatesType.AIT;
        case "3":
            return AssetTemplatesType.INDT;
        default:
            return null;
        }
    }
}
