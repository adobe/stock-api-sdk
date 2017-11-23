/*******************************************************************************
 * Copyright 2017 Adobe Systems Incorporated. All rights reserved.
 * This file is licensed to you under the Apache License, Version 2.0
 * (the "License") you may not use this file except in compliance with the
 * License. You may obtain a copy of the License at
 * http://www.apache.org/licenses/LICENSE-2.0
 ******************************************************************************/
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
