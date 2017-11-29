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
 * StockFileCompProp stores the properties of complementary asset properties.
 */
@JsonIgnoreProperties(ignoreUnknown = true)
public final class StockFileCompProp {
    /**
     * Width in pixels of the asset's complementary (unlicensed) image.
     */
    private Integer mWidth;
    /**
     * Height in pixels of the asset's complementary (unlicensed) image.
     */
    private Integer mHeight;
    /**
     * URL to the watermarked version of the asset.
     */
    private String mUrl;

    /**
     * Default constructor.
     */
    public StockFileCompProp() {
    }

    /**
     * Get width of complementary image.
     *
     * @return width of complementary image
     */
    public Integer getWidth() {
        return mWidth;
    }

    /**
     * Sets width of complementary image.
     *
     * @param width
     *            width of complementary image.
     */
    @JsonSetter("width")
    public void setWidth(final Integer width) {
        this.mWidth = width;
    }

    /**
     * Get height of complementary image.
     *
     * @return height of complementary image.
     */
    public Integer getHeight() {
        return mHeight;
    }

    /**
     * Sets height of complementary image.
     *
     * @param height
     *            height of complementary image.
     */
    @JsonSetter("height")
    public void setHeight(final Integer height) {
        this.mHeight = height;
    }

    /**
     * Get url of complementary image.
     *
     * @return url of complementary image.
     */
    public String getUrl() {
        return mUrl;
    }

    /**
     * Sets url of complementary image.
     *
     * @param url
     *            url of complementary image.
     */
    @JsonSetter("url")
    public void setUrl(final String url) {
        this.mUrl = url;
    }

}
