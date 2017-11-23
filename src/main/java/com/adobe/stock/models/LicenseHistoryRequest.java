/*******************************************************************************
 * Copyright 2017 Adobe Systems Incorporated. All rights reserved.
 * This file is licensed to you under the Apache License, Version 2.0
 * (the "License") you may not use this file except in compliance with the
 * License. You may obtain a copy of the License at
 * http://www.apache.org/licenses/LICENSE-2.0
 ******************************************************************************/
package com.adobe.stock.models;

import java.util.HashSet;
import java.util.Set;

import com.adobe.stock.enums.LicenseHistoryResultColumn;

/**
 * LicenseHistoryRequest Object that can be used to call LicenseHistory API in
 * order to find licensed assets of user.It consists of locale,search
 * parameters(offset,limit), result columns to include in results.
 */
public final class LicenseHistoryRequest {
    /**
     * Location language code.
     */
    private String mLocale = "";

    /**
     * SearchParameters object that consists of various search params that
     * can be  included for your license history files search.
     */
    private SearchParametersLicenseHistory mSearchParams;

    /**
     * ResultColumns array consisting of result column enums that you want
     * to include in your license history files search.
     */
    private LicenseHistoryResultColumn[] mResultColumns;

    /**
     * Default Constructor.
     */
    public LicenseHistoryRequest() {
    }

    /**
     * Get current Location language code.
     * @return Locale field of type String
     */
    public String getLocale() {
        return mLocale;
    }

    /**
     * Sets location language code to include in LicenseHistoryRequest object.
     * @param locale
     *            : location language code
     * @return LicenseHistoryRequest object
     * @throws IllegalArgumentException
     *             if locale passed is blank or null
     */
    public LicenseHistoryRequest setLocale(final String locale) {
        if (locale == null || locale.isEmpty()) {
            throw new IllegalArgumentException("Should be a valid locale");
        }
        this.mLocale = locale;
        return this;
    }

    /**
     * Get SearchParametersLicenseHistory object that consists of various
     * search params that you have included for your search.
     * @return SearchParametersLicenseHistory object
     * @see SearchParametersLicenseHistory
     */
    public SearchParametersLicenseHistory getSearchParams() {
        return mSearchParams;
    }

    /**
     * Sets SearchParameters object that consists of various search params that
     * you want to include for your files search.
     * @param searchParams
     *            : SearchParametersLicenseHistory object
     * @return LicenseHistoryRequest object
     * @throws IllegalArgumentException
     *             if SearchParameters passed is null
     */
    public LicenseHistoryRequest setSearchParams(
            final SearchParametersLicenseHistory searchParams) {
        if (searchParams == null) {
            throw new IllegalArgumentException("Search Params cannot be null");
        }
        this.mSearchParams = searchParams;
        return this;
    }

    /**
     * Get ResultColumns array that you have included for columns that you want
     * to include in your results.
     * @return LicenseHistoryResultColumn Array
     * @see LicenseHistoryResultColumn
     */
    public LicenseHistoryResultColumn[] getResultColumns() {
        return mResultColumns;
    }

    /**
     * Set ResultColumns array consisting of result column enums that you want
     * to include in your search results.
     * @param resultColumns
     *            : Array of LicenseHistoryResultColumn enums
     * @return LicenseHistoryRequest object
     * @throws IllegalArgumentException
     *             if resultColumn is null
     * @see LicenseHistoryResultColumn
     */
    public LicenseHistoryRequest setResultColumns(
            final LicenseHistoryResultColumn[] resultColumns) {
        if (resultColumns == null) {
            throw new IllegalArgumentException(
                    "Request Columns cannot be null");
        }

        Set<LicenseHistoryResultColumn> resultColumnsSet =
                new HashSet<LicenseHistoryResultColumn>();
        for (LicenseHistoryResultColumn col : resultColumns) {
            resultColumnsSet.add(col);
        }
        this.mResultColumns = resultColumnsSet.toArray(
                new LicenseHistoryResultColumn[resultColumnsSet.size()]);
        return this;
    }

}
