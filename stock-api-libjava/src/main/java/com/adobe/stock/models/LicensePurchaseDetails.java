/*******************************************************************************
 * Copyright 2017 Adobe Systems Incorporated. All rights reserved.
 * This file is licensed to you under the Apache License, Version 2.0
 * (the "License") you may not use this file except in compliance with the
 * License. You may obtain a copy of the License at
 * http://www.apache.org/licenses/LICENSE-2.0
 ******************************************************************************/
package com.adobe.stock.models;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import com.adobe.stock.enums.AssetLicenseState;
import com.adobe.stock.enums.AssetPurchaseState;
import com.adobe.stock.exception.StockException;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonSetter;

/**
 * LicensePurchaseDetails stores the information about the
 *  user's purchase/license of this asset.
 */
@JsonIgnoreProperties(ignoreUnknown = true)
public final class LicensePurchaseDetails {
    /**
     * Date when the asset was purchased or licensed.
     */
    private Date mDate;
    /**
     * Date when the asset purchase/license was cancelled.
     */
    private Date mCancelledDate;
    /**
     * Asset licensing state.
     */
    private AssetLicenseState mLicenseState;
    /**
     * Asset purchase state.
     */
    private AssetPurchaseState mPurchaseState;
    /**
     * The URL from which the licensed asset can be downloaded.
     */
    private String mUrl;

    /**
     * Type of the licensed asset.
     */
    private String mContentType;
    /**
     * Width of asset in pixels.
     */
    private Integer mWidth;
    /**
     * Height of asset in pixels.
     */
    private Integer mHeight;
    /**
     * Frame rate for video.
     */
    private Double mFrameRate;
    /**
     * Size of asset file in bytes.
     */
    private Integer mContentLength;
    /**
     * Duration of video in milliseconds.
     */
    private Integer mDuration;
    /**
     * Default Constructor.
     */
    public LicensePurchaseDetails() {
    }
    /**
     * Get Date when the asset was purchased or licensed.
     * @return purchased/licensed date of type Date
     */
    public Date getDate() {
        return mDate;
    }
    /**
     * Get Date when the asset purchase/license was cancelled.
     * @return cancelled date of type Date
     */
    public Date getCancelledDate() {
        return mCancelledDate;
    }
    /**
     * Get Asset licensing state.
     * @return enum of type {@link AssetLicenseState}
     */
    public AssetLicenseState getLicenseState() {
        return mLicenseState;
    }
    /**
     * Get Asset purchase state.
     * @return enum of type {@link AssetPurchaseState}
     */
    public AssetPurchaseState getPurchaseState() {
        return mPurchaseState;
    }
    /**
     * Get URL from which the licensed asset can be downloaded.
     * @return URL of type String
     */
    public String getUrl() {
        return mUrl;
    }

    /**
     * Get Type of the licensed asset.
     * @return asset type of type String
     */
    public String getContentType() {
        return mContentType;
    }
    /**
     * Get Width of asset in pixels.
     * @return width of type Integer
     */
    public Integer getWidth() {
        return mWidth;
    }
    /**
     * Get Height of asset in pixels.
     * @return Height of type Integer
     */
    public Integer getHeight() {
        return mHeight;
    }
    /**
     * Get Frame rate for video.
     * @return frame rate of type double
     */
    public Double getFrameRate() {
        return mFrameRate;
    }
    /**
     * Gets Size of asset file in bytes.
     * @return asset size of type Integer
     */
    public Integer getContentLength() {
        return mContentLength;
    }

    /**
     * Get Duration of video in milliseconds.
     * @return duration of type Integer
     */
    public Integer getDuration() {
        return mDuration;
    }
    /**
     * Sets Date when the asset was purchased or licensed.
     * @param date Date when the asset was purchased or licensed.
     * @throws StockException if date format is not valid
     */
    @JsonSetter("date")
    public void setDate(final String date) throws StockException {
        String formatString = "yyyy-MM-dd hh:mm:ss";
        // date format with milliseconds
        SimpleDateFormat format = new SimpleDateFormat(
                formatString + ".SSS");
        // date format without milliseconds
        SimpleDateFormat formatWithoutMS = new SimpleDateFormat(
                formatString);
        try {
            if (date.length() <= formatString.length()) {
                this.mDate = formatWithoutMS.parse(date);
            } else {
                this.mDate = format.parse(date);
            }
        } catch (ParseException e) {
            throw new StockException("Could not parse the date string");
        }
    }
    /**
     * Sets Date when the asset purchase/license was cancelled.
     * @param cancelledDate Date when the asset purchase/license was cancelled.
     * @throws StockException if date format is not valid
     */
    @JsonSetter("cancelled")
    public void setCancelledDate(final String cancelledDate)
            throws StockException {
        String formatString = "yyyy-MM-dd hh:mm:ss";
        // date format with milliseconds
        SimpleDateFormat format = new SimpleDateFormat(
                formatString + ".SSS");
        // date format without milliseconds
        SimpleDateFormat formatWithoutMS = new SimpleDateFormat(
                formatString);
        try {
            if (cancelledDate.length() <= formatString.length()) {
                this.mCancelledDate = formatWithoutMS.parse(cancelledDate);
            } else {
                this.mCancelledDate = format.parse(cancelledDate);
            }
        } catch (ParseException e) {
            throw new StockException("Could not parse the date string");
        }
    }
    /**
     * Sets Asset licensing state.
     * @param licenseState Asset licensing state
     */
    @JsonSetter("license")
    public void setLicenseState(final AssetLicenseState licenseState) {
        this.mLicenseState = licenseState;
    }
    /**
     * Sets Asset purchase state.
     * @param purchaseState Asset purchase state.
     */
    @JsonSetter("state")
    public void setPurchaseState(final AssetPurchaseState purchaseState) {
        this.mPurchaseState = purchaseState;
    }
    /**
     * Sets URL from which the licensed asset can be downloaded.
     * @param url URL from which the licensed asset can be downloaded.
     */
    @JsonSetter("url")
    public void setUrl(final String url) {
        this.mUrl = url;
    }

    /**
     * Sets Type of the licensed asset.
     * @param contentType Type of the licensed asset.
     */
    @JsonSetter("content_type")
    public void setContentType(final String contentType) {
        this.mContentType = contentType;
    }
    /**
     * Sets Width of asset in pixels.
     * @param width Width of asset in pixels.
     */
    @JsonSetter("width")
    public void setWidth(final Integer width) {
        this.mWidth = width;
    }
    /**
     * Set Height of asset in pixels.
     * @param height Height of asset in pixels.
     */
    @JsonSetter("height")
    public void setHeight(final Integer height) {
        this.mHeight = height;
    }
    /**
     * Sets Frame rate for video.
     * @param frameRate Frame rate for video.
     */
    @JsonSetter("framerate")
    public void setFrameRate(final Double frameRate) {
        this.mFrameRate = frameRate;
    }
    /**
     * Sets Size of asset file in bytes.
     * @param contentLength Size of asset file in bytes.
     */
    @JsonSetter("content_length")
    public void setContentLength(final Integer contentLength) {
        this.mContentLength = contentLength;
    }

    /**
     * Sets Duration of video in milliseconds.
     * @param duration Duration of video in milliseconds.
     */
    @JsonSetter("duration")
    public void setDuration(final Integer duration) {
        this.mDuration = duration;
    }
}
