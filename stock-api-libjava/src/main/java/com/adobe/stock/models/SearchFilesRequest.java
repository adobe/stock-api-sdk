/*******************************************************************************
 * Copyright 2017 Adobe Systems Incorporated. All rights reserved.
 * This file is licensed to you under the Apache License, Version 2.0
 * (the "License") you may not use this file except in compliance with the
 * License. You may obtain a copy of the License at
 * http://www.apache.org/licenses/LICENSE-2.0
 ******************************************************************************/
package com.adobe.stock.models;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

import com.adobe.stock.enums.ResultColumn;

/**
 * SearchFiles Request Object that can be used to call SearchFiles API in order
 * to search assets.It consists of locale,search parameters(soring order,
 * filters), result columns to include in results, and byte data to
 * search for visual similar images.
 */
public final class SearchFilesRequest {

    /**
     * Location language code.
     */
    private String mLocale = "";

    /**
     * SearchParameters object that consists of various search params that
     * can be  included for your files search.
     */
    private SearchParameters mSearchParams;

    /**
     * ResultColumns array consisting of result column enums that you want
     * to include in your search results.
     */
    private ResultColumn[] mResultColumns;

    /**
     * Byte array for searchinbg visualy similar Image.
     */
    private byte[] mSimilarImage;

    /**
     * Default Constructor.
     */
    public SearchFilesRequest() {
    }

    /**
     * Get current Location language code.
     * @return Locale field of type String
     */
    public String getLocale() {
        return mLocale;
    }

    /**
     * Sets location language code to include in SearchFilesRequest object.
     * @param locale
     *            : location language code
     * @return SearchFilesRequest object
     * @throws IllegalArgumentException
     *             if locale passed is blank or null
     */
    public SearchFilesRequest setLocale(final String locale) {
        if (locale == null || locale.isEmpty()) {
            throw new IllegalArgumentException("Should be a valid locale");
        }
        this.mLocale = locale;
        return this;
    }

    /**
     * Get SearchParameters object that consists of various search params that
     * you have included for your files search.
     * @return SearchParameters object
     * @see SearchParameters
     */
    public SearchParameters getSearchParams() {
        return mSearchParams;
    }

    /**
     * Sets SearchParameters object that consists of various search params that
     * you want to include for your files search.
     * @param searchParams
     *            : SearchParmeters object
     * @return SearchFilesRequest object
     * @throws IllegalArgumentException
     *             if SearchParameters passed is null
     */
    public SearchFilesRequest setSearchParams(
            final SearchParameters searchParams) {
        if (searchParams == null) {
            throw new IllegalArgumentException("Search Params cannot be null");
        }
        this.mSearchParams = searchParams;
        return this;
    }

    /**
     * Get ResultColumns array that you have included for columns that you want
     * to include in your search results.
     * @return ResultColumn Array
     * @see ResultColumn
     */
    public ResultColumn[] getResultColumns() {
        if (mResultColumns == null) {
            return null;
        }
        return mResultColumns.clone();
    }

    /**
     * Set ResultColumns array consisting of result column enums that you want
     * to include in your search results.
     * @param resultColumns
     *            : Array of ResultColumn enums
     * @return SearchFilesRequest object
     * @throws IllegalArgumentException
     *             if resultColumn is null
     * @see ResultColumn
     */
    public SearchFilesRequest setResultColumns(
            final ResultColumn[] resultColumns) {
        if (resultColumns == null) {
            throw new IllegalArgumentException(
                    "Request Columns cannot be null");
        }

        Set<ResultColumn> resultColumnsSet = new HashSet<ResultColumn>();
        for (ResultColumn col : resultColumns) {
            resultColumnsSet.add(col);
        }
        this.mResultColumns = resultColumnsSet
                .toArray(new ResultColumn[resultColumnsSet.size()]);
        return this;
    }

    /**
     * Get byte array that user sends for searching similar image.
     *  @return Image object
     */
    public byte[] getSimilarImage() {
        if (mSimilarImage == null) {
            return null;
        }
        return Arrays.copyOf(mSimilarImage, mSimilarImage.length);
    }

    /**
     * Sets byte array image if user wants to search for similar Images.
     * @param similarImage
     *            : byte array of image that user sets to search for similar
     *            Images
     * @return SearchFilesRequest object
     * @throws IllegalArgumentException
     *             if Image passed is null
     */
    public SearchFilesRequest setSimilarImage(final byte[] similarImage) {
        if (similarImage == null) {
            throw new IllegalArgumentException("Should be a valid Image");
        }
        this.mSimilarImage = Arrays.copyOf(similarImage, similarImage.length);
        return this;
    }

}
