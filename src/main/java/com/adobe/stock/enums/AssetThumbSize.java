package com.adobe.stock.enums;

/**
 * All specified thumbnail sizes that can be used in search parameters
 * for found assets.
 */
public enum AssetThumbSize {

    /**
     * Thumbnail size of 110 pixels.
     */
    MEDIUM(110),

    /**
     * Thumbnail size of 160 pixels.
     */
    BIG(160),

    /**
     * Thumbnail size of 500 pixels.
     */
    XL(500),

    /**
     * Thumbnail size of 1000 pixels.
     */
    XXL(1000);

    /**
     * Thumbnail size in pixels.
     */
    private int mValue;

    /**Constructor.
     * @param value integer value of AssetThumbSize enum.
     */
    AssetThumbSize(final int value) {
        this.mValue = value;
    }

    @Override
    public String toString() {
        return String.valueOf(mValue);
    }
}
