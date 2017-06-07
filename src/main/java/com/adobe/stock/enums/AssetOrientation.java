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
