/*******************************************************************************
 * Copyright 2017 Adobe Systems Incorporated. All rights reserved.
 * This file is licensed to you under the Apache License, Version 2.0
 * (the "License") you may not use this file except in compliance with the
 * License. You may obtain a copy of the License at
 * http://www.apache.org/licenses/LICENSE-2.0
 ******************************************************************************/
package com.adobe.stock.models;

import com.fasterxml.jackson.annotation.JsonSetter;

/**
 * LicenseThumbnail stores information about thumbnail of
 * the asset.
 */
public final class LicenseThumbnail {
    /**
     * The URL from which the thumbnail can be downloaded.
     */
    private String mUrl;
    /**
     * Type of the asset thumbnail.
     */
    private String mContentType;
    /**
     * Width of asset thumbnail in pixels.
     */
    private Integer mWidth;
    /**
     * Height of asset thumbnail in pixels.
     */
    private Integer mHeight;

    /**
     * Get URL from which the thumbnail can be downloaded.
     * @return URL of type String
     */
    public String getUrl() {
        return mUrl;
    }

    /**
     * Get Type of the asset thumbnail.
     * @return  thumbnail type of type String
     */
    public String getContentType() {
        return mContentType;
    }
    /**
     * Get Width of asset thumbnail in pixels.
     * @return width of type Integer
     */
    public Integer getWidth() {
        return mWidth;
    }
    /**
     * Get Height of asset thumbnail in pixels.
     * @return Height of type Integer
     */
    public Integer getHeight() {
        return mHeight;
    }

    /**
     * Sets URL from which the thumbnail can be downloaded.
     * @param url URL from which the thumbnail can be downloaded.
     */
    @JsonSetter("url")
    public void setUrl(final String url) {
        this.mUrl = url;
    }

    /**
     * Sets Type of the asset thumbnail.
     * @param contentType Type of the  asset thumbnail.
     */
    @JsonSetter("content_type")
    public void setContentType(final String contentType) {
        this.mContentType = contentType;
    }
    /**
     * Sets Width of asset thumbnail in pixels.
     * @param width Width of asset thumbnail in pixels.
     */
    @JsonSetter("width")
    public void setWidth(final Integer width) {
        this.mWidth = width;
    }
    /**
     * Sets Height of asset thumbnail in pixels.
     * @param height Height of asset thumbnail in pixels.
     */
    @JsonSetter("height")
    public void setHeight(final Integer height) {
        this.mHeight = height;
    }
}
