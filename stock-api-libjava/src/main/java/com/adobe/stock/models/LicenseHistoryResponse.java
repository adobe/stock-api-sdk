/*******************************************************************************
 * Copyright 2017 Adobe Systems Incorporated. All rights reserved.
 * This file is licensed to you under the Apache License, Version 2.0
 * (the "License") you may not use this file except in compliance with the
 * License. You may obtain a copy of the License at
 * http://www.apache.org/licenses/LICENSE-2.0
 ******************************************************************************/
package com.adobe.stock.models;

import java.util.ArrayList;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonSetter;

/**
 * LicenseHistoryResponse stores the response from LicenseHistory API call.
 */
@JsonIgnoreProperties(ignoreUnknown = true)
public final class LicenseHistoryResponse {

    /**
     * Total number of found assets in the License History results.
     */
    private Integer mNbResults;
    /**
     * ArrayList of stock media files.
     */
    private ArrayList<StockLicenseHistoryFile> mFiles;

    /**
     * Default constructor.
     */
    public LicenseHistoryResponse() {
    }

    /**
     * Get total number of found assets in the results.
     *
     * @return total number of assets of type Integer.
     */
    public Integer getNbResults() {
        return mNbResults;
    }

    /**
     * Sets total number of found assets in the results.
     *
     * @param nbResults
     *            total number of assets
     */
    @JsonSetter("nb_results")
    public void setNbResults(final Integer nbResults) {
        this.mNbResults = nbResults;
    }

    /**
     * Get list of stock media files.
     *
     * @return stock media files of type ArrayList
     */
    public ArrayList<StockLicenseHistoryFile> getFiles() {
        return mFiles;
    }

    /**
     * Sets list of stock media files.
     *
     * @param files
     *            ArrayList of stock media files
     */
    @JsonSetter("files")
    public void setFiles(final ArrayList<StockLicenseHistoryFile> files) {
        this.mFiles = files;
    }
}
