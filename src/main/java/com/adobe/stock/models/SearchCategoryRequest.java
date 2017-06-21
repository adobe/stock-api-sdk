package com.adobe.stock.models;

import com.adobe.stock.annotations.SearchParamURLMapperInternal;

/**
 * SearchCategory Request Object that can be used to call SearchCategory
 * or CategoryTree API in order to get information about a category of
 *  Adobe Stock assets or entire stock category tree.
 * It consists of locale and category-id information.
 */
public final class SearchCategoryRequest {
    /**
     * Location language code (optional).
     */
    @SearchParamURLMapperInternal(value = "locale")
    private String mLocale;
    /**
     * Unique identifier for an existing category.
     */
    @SearchParamURLMapperInternal(value = "category_id")
    private Integer mCategoryId;

    /**
     * Get current Location language code.
     * @return Locale field of type String
     */
    public String getLocale() {
        return mLocale;
    }
    /**
     * Get unique identifier for an existing category.
     * @return category id of type Integer
     */
    public Integer getCategoryId() {
        return mCategoryId;
    }

    /**
     * Sets location language code to include in SearchCategoryRequest object.
     * @param locale location language code
     * @return SearchCategoryRequest object
     * @throws IllegalArgumentException if locale is null or empty
     */
    public SearchCategoryRequest setLocale(final String locale)
            throws IllegalArgumentException {
        if (locale == null || locale.isEmpty()) {
            throw new IllegalArgumentException("Should be a valid locale");
        }
        this.mLocale = locale;
        return this;
    }
    /**
     * Sets unique identifier for an existing category in SearchCategoryRequest
     *  object.
     * @param categoryId unique identifier for an existing category
     * @return SearchCategoryRequest object
     * @throws IllegalArgumentException if categoryId is negative
     */
    public SearchCategoryRequest setCategoryId(final int categoryId)
            throws IllegalArgumentException {
        if (categoryId < 0) {
            throw new IllegalArgumentException("Should be a valid category id");
        }
        this.mCategoryId = categoryId;
        return this;
    }

}
