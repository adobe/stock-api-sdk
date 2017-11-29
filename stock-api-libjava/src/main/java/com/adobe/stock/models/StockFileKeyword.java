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
