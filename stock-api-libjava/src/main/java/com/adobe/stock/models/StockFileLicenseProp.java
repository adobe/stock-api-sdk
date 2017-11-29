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
 * StockFileLicenseProp stores the properties of media licenses.
 */
@JsonIgnoreProperties(ignoreUnknown = true)
public final class StockFileLicenseProp {
    /**
     * width property of license.
     */
    private Integer mWidth;
    /**
     * height property of license.
     */
    private Integer mHeight;
    /**
     * licensing url.
     */
    private String mUrl;

    /**
     * Default constructor.
     */
    public StockFileLicenseProp() {
    }

    /**
     * Get width property of license.
     *
     * @return width property of license
     */
    public Integer getWidth() {
        return mWidth;
    }

    /**
     * Sets width property of license.
     *
     * @param width
     *            width property of license
     */
    @JsonSetter("width")
    public void setWidth(final Integer width) {
        this.mWidth = width;
    }

    /**
     * Get height property of license.
     *
     * @return height property of license
     */
    public Integer getHeight() {
        return mHeight;
    }

    /**
     * Sets height property of license.
     *
     * @param height
     *            height property of license
     */
    @JsonSetter("height")
    public void setHeight(final Integer height) {
        this.mHeight = height;
    }

    /**
     * Get licensing url.
     *
     * @return licensing url
     */
    public String getUrl() {
        return mUrl;
    }

    /**
     * Sets licensing url.
     *
     * @param url
     *            licensing url
     */
    @JsonSetter("url")
    public void setUrl(final String url) {
        this.mUrl = url;
    }

}
