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
