package com.adobe.stock.enums;

/**
 * All specified thumbnail sizes that can be used in search parameters
 * for found assets.
 */
public enum LicenseHistoryThumbnailSize {

    /**
     * Thumbnail size of 110 pixels.
     */
    MEDIUM(110),

    /**
     * Thumbnail size of 160 pixels.
     */
    BIG(160),

    /**
     * Thumbnail size of 220 pixels.
     */
    LARGE(220),

    /**
     * Thumbnail size of 240 pixels.
     */
    VERY_LARGE(240),

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
     * @param value integer value of LicenseHistoryThumbnailSize enum.
     */
    LicenseHistoryThumbnailSize(final int value) {
        this.mValue = value;
    }

    @Override
    public String toString() {
        return String.valueOf(mValue);
    }
}
