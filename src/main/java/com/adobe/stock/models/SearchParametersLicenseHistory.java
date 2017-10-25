package com.adobe.stock.models;

import com.adobe.stock.annotations.SearchParamURLMapperInternal;
import com.adobe.stock.enums.LicenseHistoryThumbnailSize;

/**
* SearchParametersLicenseHistory allows to set the various search_parameters
* (URL query parameters) supported by LicenseHistory Stock api. This is
* the class where you can actually set the limit, offset and
* thumbnail_size.
*/
public final class SearchParametersLicenseHistory {

    /**
     * Minimum number of assets that can be returned
     * in results.
     */
    private static final int MIN_LIMIT = 1;

    /**
     * Maximum number of assets in result.
     */
    @SearchParamURLMapperInternal(value = "[limit]")
    private Integer mLimit;

    /**
     * Start position(index) in search results.
     */
    @SearchParamURLMapperInternal(value = "[offset]")
    private Integer mOffset;

    /**
     * Thumbnail Size in the results.
     */
    @SearchParamURLMapperInternal(value = "[thumbnail_size]")
    private LicenseHistoryThumbnailSize mThumbnailSize;

    /**
     * Get maximum number of assets that return in the api call.
     * @return limit of type Integer
     */
    public Integer getLimit() {
        return mLimit;
    }

    /**
     * Sets maximum number of assets in search Params that you wants to return
     * in the api call.
     * @param limit
     *            : maximum number of assets that return in the api call
     * @return SearchParametersLicenseHistory object
     * @throws IllegalArgumentException
     *             if limit is less than 1
     */
    public SearchParametersLicenseHistory setLimit(final int limit) {
        if (limit < MIN_LIMIT) {
            throw new IllegalArgumentException(
                    "Limit should be greator than 1");
        }
        this.mLimit = limit;
        return this;
    }

    /**
     * Get start position(index) in search results.
     * @return offset of type Integer
     */
    public Integer getOffset() {
        return mOffset;
    }

    /**
     * Sets the start position(index) in search results.
     * @param offset
     *            : starting index in the search results
     * @return SearchParametersLicenseHistory object
     * @throws IllegalArgumentException
     *             if offset is not positive
     */
    public SearchParametersLicenseHistory setOffset(final int offset) {
        if (offset < 0) {
            throw new IllegalArgumentException(
                    "Offset should be between 0 and MaxResults");
        }
        this.mOffset = offset;
        return this;
    }

    /**
     * Get thumbnail size of asset.
     * @return offset
     */
    public LicenseHistoryThumbnailSize getThumbnailSize() {
        return mThumbnailSize;
    }

    /**
     * Sets thumbnail size of asset.
     * @param size thumbnail size
     * @return SearchParametersLicenseHistory object
     */
    public SearchParametersLicenseHistory setThumbnailSize(
                        final LicenseHistoryThumbnailSize size) {
        this.mThumbnailSize = size;
        return this;
    }

}
