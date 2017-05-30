package com.adobe.stock.models;

import java.util.ArrayList;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonSetter;

/**
 * SearchFilesResponse stores the response from search/files API call.
 */
@JsonIgnoreProperties(ignoreUnknown = true)
public final class SearchFilesResponse {
    /**
     * Total number of found assets in the search results.
     */
    private Integer mNbResults;
    /**
     * ArrayList of stock media files.
     */
    private ArrayList<StockFile> mFiles;

    /**
     * Default constructor.
     */
    public SearchFilesResponse() {
    }

    /**
     * Get total number of found assets in the search results.
     *
     * @return total number of assets of type Integer
     */
    public Integer getNbResults() {
        return mNbResults;
    }

    /**
     * Sets total number of found assets in the search results.
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
    public ArrayList<StockFile> getFiles() {
        return mFiles;
    }

    /**
     * Sets list of stock media files.
     *
     * @param files
     *            ArrayList of stock media files
     */
    @JsonSetter("files")
    public void setFiles(final ArrayList<StockFile> files) {
        this.mFiles = files;
    }

}
