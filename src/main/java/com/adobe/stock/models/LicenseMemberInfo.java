package com.adobe.stock.models;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonSetter;
/**
 * Information about the user.
 */
@JsonIgnoreProperties(ignoreUnknown = true)
public final class LicenseMemberInfo {
    /**
     * User's Adobe Stock member identifier.
     */
    private String mStockId;
    /**
     * Default Constructor.
     */
    public LicenseMemberInfo() {
    }
    /**
     * Get user's Adobe Stock member identifier.
     * @return Adobe Stock member identifier as String
     */
    public String getStockId() {
        return mStockId;
    }
    /**
     * Sets Adobe Stock member identifier.
     * @param stockId Adobe Stock member identifier
     */
    @JsonSetter("stock_id")
    public void setStockId(final String stockId) {
        this.mStockId = stockId;
    }

}
