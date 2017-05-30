package com.adobe.stock.models;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonSetter;
/**
 * StockFileKeyword stores the properties of asset keywords.
 */
@JsonIgnoreProperties(ignoreUnknown = true)
public final class StockFileKeyword {
    /**
     * name of media keyword.
     */
    private String mName;

    /**
     * Default constructor.
     */
    public StockFileKeyword() {
    }

    /**
     * Get name of media keyword.
     *
     * @return name of media keyword
     */
    public String getName() {
        return mName;
    }

    /**
     * Sets name of media keyword.
     *
     * @param name
     *            name of media keyword
     */
    @JsonSetter("name")
    public void setName(final String name) {
        this.mName = name;
    }

}
