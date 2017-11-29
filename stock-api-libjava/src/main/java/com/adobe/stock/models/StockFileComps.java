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
 * StockFileComps stores the properties of complementary asset.
 */
@JsonIgnoreProperties(ignoreUnknown = true)
public final class StockFileComps {
    /**
     * Standard format property of complementary asset.
     */
    private StockFileCompProp mStandard;
    /**
     * Video_HD format property of complementary asset.
     */
    private StockFileCompProp mVideoHD;
    /**
     * Video_4k format property of complementary asset.
     */
    private StockFileCompProp mVideo4K;

    /**
     * Default constructor.
     */
    public StockFileComps() {
    }

    /**
     * Get Standard format property of complementary asset.
     *
     * @return Standard format property of complementary asset.
     */
    public StockFileCompProp getStandard() {
        return mStandard;
    }

    /**
     * Sets Standard format property of complementary asset.
     *
     * @param standard
     *            Standard format property of complementary asset.
     */
    @JsonSetter("Standard")
    public void setStandard(final StockFileCompProp standard) {
        this.mStandard = standard;
    }

    /**
     * Get Video_HD format property of complementary asset.
     *
     * @return Video_HD format property of complementary asset.
     */
    public StockFileCompProp getVideoHD() {
        return mVideoHD;
    }

    /**
     * Sets Video_HD format property of complementary asset.
     *
     * @param videoHD
     *            Video_HD format property of complementary asset.
     */
    @JsonSetter("Video_HD")
    public void setVideoHD(final StockFileCompProp videoHD) {
        this.mVideoHD = videoHD;
    }

    /**
     * Get Video_4k format property of complementary asset.
     *
     * @return Video_4k format property of complementary asset.
     */
    public StockFileCompProp getVideo4K() {
        return mVideo4K;
    }

    /**
     * Sets Video_4k format property of complementary asset.
     *
     * @param video4K
     *            Video_4k format property of complementary asset.
     */
    @JsonSetter("Video_4K")
    public void setVideo4K(final StockFileCompProp video4K) {
        this.mVideo4K = video4K;
    }
}
