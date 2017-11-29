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
 * StockLicenseHistoryFile stores stock media file properties returned from
 * LicenseHistory stock api.
 */
public final class StockLicenseHistoryFile {

    /**
     * License State.
     */
    private String mLicenseState;
    /**
     * License Date.
     */
    private String mLicenseDate;
    /**
     * Download url of asset.
     */
    private String mDownloadUrl;
    /**
     * Media unique ID.
     */
    private Integer mId;
    /**
     * Media title.
     */
    private String mTitle;
    /**
     * Media creator unique id.
     */
    private Integer mCreatorId;
    /**
     * Media creator name.
     */
    private String mCreatorName;
    /**
     * If the file is a vector indicates if its a "svg" or a ai/eps (reported as
     * "zip").
     */
    private String mVectorType;
    /**
     * Mime type of the asset's content.
     */
    private String mContentType;
    /**
     * Asset Media Type Id.
     */
    private Integer mMediaTypeId;
    /**
     * Original width of the file in pixels.
     */
    private Integer mWidth;
    /**
     * Original height of the file in pixels.
     */
    private Integer mHeight;
    /**
     * Url of content.
     */
    private String mContentUrl;
    /**
     * Url to stock details page for the asset.
     */
    private String mDetailsUrl;
    /**
     * URL for the default-sized asset thumbnail.
     */
    private String mThumbnailUrl;
    /**
     * Media thumbnail width in pixels.
     */
    private Integer mThumbnailWidth;
    /**
     * Media thumbnail height in pixels.
     */
    private Integer mThumbnailHeight;
    /**
     * Url for 110px thumbnail.
     */
    private String mThumbnail110Url;
    /**
     * Width for 110px thumbnail.
     */
    private Double mThumbnail110Width;
    /**
     * Height for 110px thumbnail.
     */
    private Integer mThumbnail110Height;
    /**
     * Url for 160px thumbnail.
     */
    private String mThumbnail160Url;
    /**
     * Width for 160px thumbnail.
     */
    private Double mThumbnail160Width;
    /**
     * Height for 160px thumbnail.
     */
    private Integer mThumbnail160Height;
    /**
     * Url for 220px thumbnail.
     */
    private String mThumbnail220Url;
    /**
     * Width for 220px thumbnail.
     */
    private Double mThumbnail220Width;
    /**
     * Height for 220px thumbnail.
     */
    private Integer mThumbnail220Height;
    /**
     * Url for 240px thumbnail.
     */
    private String mThumbnail240Url;
    /**
     * Width for 240px thumbnail.
     */
    private Double mThumbnail240Width;
    /**
     * Height for 240px thumbnail.
     */
    private Integer mThumbnail240Height;
    /**
     * Url for 500px thumbnail.
     */
    private String mThumbnail500Url;
    /**
     * Width for 500px thumbnail.
     */
    private Double mThumbnail500Width;
    /**
     * Height for 500px thumbnail.
     */
    private Integer mThumbnail500Height;
    /**
     * Url for 1000px thumbnail.
     */
    private String mThumbnail1000Url;
    /**
     * Width for 1000px thumbnail.
     */
    private Double mThumbnail1000Width;
    /**
     * Height for 1000px thumbnail.
     */
    private Integer mThumbnail1000Height;

    /**
     * @return LicenseState of type String
     */
    public String getLicenseState() {
        return mLicenseState;
    }
    /**
     * @return LicenseDate
     */
    public String getLicenseDate() {
        return mLicenseDate;
    }
    /**
     * @return DownloadUrl
     */
    public String getDownloadUrl() {
        return mDownloadUrl;
    }
    /**
     * Get media unique ID.
     *
     * @return ID of type Integer
     */
    public Integer getId() {
        return mId;
    }

    /**
     * Get media title (used for title tag).
     *
     * @return title of type String
     */
    public String getTitle() {
        return mTitle;
    }

    /**
     * Get media creator unique id.
     *
     * @return creator id of type Integer
     */
    public Integer getCreatorId() {
        return mCreatorId;
    }
    /**
     * Get media creator name.
     *
     * @return name of type String
     */
    public String getCreatorName() {
        return mCreatorName;
    }
    /**
     * @return VectorType
     */
    public String getVectorType() {
        return mVectorType;
    }
    /**
     * @return ContentType
     */
    public String getContentType() {
        return mContentType;
    }
    /**
     * @return MediaTypeId
     */
    public Integer getMediaTypeId() {
        return mMediaTypeId;
    }
    /**
     * Get media thumbnail width in pixels.
     *
     * @return width of type Integer
     */
    public Integer getThumbnailWidth() {
        return mThumbnailWidth;
    }

    /**
     * Get media thumbnail height in pixels.
     *
     * @return height of type Integer
     */
    public Integer getThumbnailHeight() {
        return mThumbnailHeight;
    }
    /**
     * @return ContentUrl
     */
    public String getContentUrl() {
        return mContentUrl;
    }
    /**
     * @return DetailsUrl
     */
    public String getDetailsUrl() {
        return mDetailsUrl;
    }
    /**
     * @return ThumbnailUrl
     */
    public String getThumbnailUrl() {
        return mThumbnailUrl;
    }
    /**
     * Get original width of the file in pixels.
     *
     * @return width of type Integer
     */
    public Integer getWidth() {
        return mWidth;
    }

    /**
     * Get original height of the file in pixels.
     *
     * @return height of type Integer
     */
    public Integer getHeight() {
        return mHeight;
    }
    /**
     * Get url for 110px thumbnail.
     *
     * @return url of type String
     */
    public String getThumbnail110Url() {
        return mThumbnail110Url;
    }

    /**
     * Get width for 110px thumbnail.
     *
     * @return width of type Double
     */
    public Double getThumbnail110Width() {
        return mThumbnail110Width;
    }

    /**
     * Get height for 110px thumbnail.
     *
     * @return height of type Integer
     */
    public Integer getThumbnail110Height() {
        return mThumbnail110Height;
    }

    /**
     * Get url for 160px thumbnail.
     *
     * @return url of type String
     */
    public String getThumbnail160Url() {
        return mThumbnail160Url;
    }

    /**
     * Get width for 160px thumbnail.
     *
     * @return width of type Double
     */
    public Double getThumbnail160Width() {
        return mThumbnail160Width;
    }

    /**
     * Get height for 160px thumbnail.
     *
     * @return height of type Integer
     */
    public Integer getThumbnail160Height() {
        return mThumbnail160Height;
    }

    /**
     * Get url for 20px thumbnail.
     *
     * @return url of type String
     */
    public String getThumbnail220Url() {
        return mThumbnail220Url;
    }

    /**
     * Get width for 20px thumbnail.
     *
     * @return width of type Double
     */
    public Double getThumbnail220Width() {
        return mThumbnail220Width;
    }

    /**
     * Get height for 220px thumbnail.
     *
     * @return height of type Integer
     */
    public Integer getThumbnail220Height() {
        return mThumbnail220Height;
    }

    /**
     * Get url for 240px thumbnail.
     *
     * @return url of type String
     */
    public String getThumbnail240Url() {
        return mThumbnail240Url;
    }

    /**
     * Get width for 240px thumbnail.
     *
     * @return width of type Double
     */
    public Double getThumbnail240Width() {
        return mThumbnail240Width;
    }

    /**
     * Get height for 240px thumbnail.
     *
     * @return height of type Integer
     */
    public Integer getThumbnail240Height() {
        return mThumbnail240Height;
    }

    /**
     * Get url for 500px thumbnail.
     *
     * @return url of type String
     */
    public String getThumbnail500Url() {
        return mThumbnail500Url;
    }

    /**
     * Get width for 500px thumbnail.
     *
     * @return width of type Double
     */
    public Double getThumbnail500Width() {
        return mThumbnail500Width;
    }

    /**
     * Get height for 500px thumbnail.
     *
     * @return height of type Integer
     */
    public Integer getThumbnail500Height() {
        return mThumbnail500Height;
    }

    /**
     * Get url for 1000px thumbnail.
     *
     * @return url of type String
     */
    public String getThumbnail1000Url() {
        return mThumbnail1000Url;
    }

    /**
     * Get width for 1000px thumbnail.
     *
     * @return width of type Double
     */
    public Double getThumbnail1000Width() {
        return mThumbnail1000Width;
    }

    /**
     * Get height for 1000px thumbnail.
     *
     * @return height of type Integer
     */
    public Integer getThumbnail1000Height() {
        return mThumbnail1000Height;
    }
    /**
     * @param licenseState License State
     */
    @JsonSetter("license")
    public void setLicenseState(final String licenseState) {
        this.mLicenseState = licenseState;
    }
    /**
     * @param licenseDate License Date
     */
    @JsonSetter("license_date")
    public void setLicenseDate(final String licenseDate) {
        this.mLicenseDate = licenseDate;
    }
    /**
     * @param downloadUrl Download url of asset
     */
    @JsonSetter("download_url")
    public void setDownloadUrl(final String downloadUrl) {
        this.mDownloadUrl = downloadUrl;
    }
    /**
     * Sets media unique ID.
     *
     * @param id
     *            media unique ID
     */
    @JsonSetter("id")
    public void setId(final Integer id) {
        this.mId = id;
    }

    /**
     * Sets media title.
     *
     * @param title
     *            media title
     */
    @JsonSetter("title")
    public void setTitle(final String title) {
        this.mTitle = title;
    }

    /**
     * Sets media creator unique id.
     *
     * @param creatorId
     *            media creator unique id
     */
    @JsonSetter("creator_id")
    public void setCreatorId(final Integer creatorId) {
        this.mCreatorId = creatorId;
    }

    /**
     * Sets media creator name.
     *
     * @param creatorName
     *            media creator name
     */
    @JsonSetter("creator_name")
    public void setCreatorName(final String creatorName) {
        this.mCreatorName = creatorName;
    }

    /**
     * If the asset is a vector, sets whether it is an SVG or an AI/EPS asset.
     *
     * @param vectorType
     *            file vector type
     */
    @JsonSetter("vector_type")
    public void setVectorType(final String vectorType) {
        this.mVectorType = vectorType;
    }

    /**
     * Sets mime type of the asset's content.
     *
     * @param contentType
     *            mime type of the asset's content
     */
    @JsonSetter("content_type")
    public void setContentType(final String contentType) {
        this.mContentType = contentType;
    }

    /**
     * @param id Media Type Id
     */
    @JsonSetter("media_type_id")
    public void setMediaTypeId(final int id) {
        this.mMediaTypeId = id;
    }
    /**
     * Sets original width of the file in pixels.
     *
     * @param width
     *            original width of the file
     */
    @JsonSetter("width")
    public void setWidth(final Integer width) {
        this.mWidth = width;
    }

    /**
     * Sets original height of the file in pixels.
     *
     * @param height
     *            original height of the file
     */
    @JsonSetter("height")
    public void setHeight(final Integer height) {
        this.mHeight = height;
    }

    /**
     * @param url Url of content
     */
    @JsonSetter("content_url")
    public void setContentUrl(final String url) {
        this.mContentUrl = url;
    }
    /**
     * Sets url of stock details page for the asset.
     *
     * @param detailsUrl
     *            url of stock details page for the asset
     */
    @JsonSetter("details_url")
    public void setDetailsUrl(final String detailsUrl) {
        this.mDetailsUrl = detailsUrl;
    }
    /**
     * Sets URL for the default-sized asset thumbnail.
     *
     * @param thumbnailUrl
     *            media thumbnail url
     */
    @JsonSetter("thumbnail_url")
    public void setThumbnailUrl(final String thumbnailUrl) {
        this.mThumbnailUrl = thumbnailUrl;
    }
    /**
     * Sets media thumbnail width in pixels.
     *
     * @param thumbnailWidth
     *            media thumbnail width in pixels
     */
    @JsonSetter("thumbnail_width")
    public void setThumbnailWidth(final Integer thumbnailWidth) {
        this.mThumbnailWidth = thumbnailWidth;
    }

    /**
     * Sets media thumbnail height in pixels.
     *
     * @param thumbnailHeight
     *            media thumbnail height in pixels
     */
    @JsonSetter("thumbnail_height")
    public void setThumbnailHeight(final Integer thumbnailHeight) {
        this.mThumbnailHeight = thumbnailHeight;
    }

    /**
     * Sets url for 110px thumbnail.
     *
     * @param thumbnail110Url
     *            url for 110px thumbnail
     */
    @JsonSetter("thumbnail_110_url")
    public void setThumbnail110Url(final String thumbnail110Url) {
        this.mThumbnail110Url = thumbnail110Url;
    }

    /**
     * Sets width for 110px thumbnail.
     *
     * @param thumbnail110Width
     *            width for 110px thumbnail
     */
    @JsonSetter("thumbnail_110_width")
    public void setThumbnail110Width(final Double thumbnail110Width) {
        this.mThumbnail110Width = thumbnail110Width;
    }

    /**
     * Sets height for 110px thumbnail.
     *
     * @param thumbnail110Height
     *            height for 110px thumbnail
     */
    @JsonSetter("thumbnail_110_height")
    public void setThumbnail110Height(final Integer thumbnail110Height) {
        this.mThumbnail110Height = thumbnail110Height;
    }

    /**
     * Sets url for 160px thumbnail.
     *
     * @param thumbnail160Url
     *            url for 160px thumbnail
     */
    @JsonSetter("thumbnail_160_url")
    public void setThumbnail160Url(final String thumbnail160Url) {
        this.mThumbnail160Url = thumbnail160Url;
    }

    /**
     * Sets width for 160px thumbnail.
     *
     * @param thumbnail160Width
     *            width for 160px thumbnail
     */
    @JsonSetter("thumbnail_160_width")
    public void setThumbnail160Width(final Double thumbnail160Width) {
        this.mThumbnail160Width = thumbnail160Width;
    }

    /**
     * Sets height for 160px thumbnail.
     *
     * @param thumbnail160Height
     *            height for 160px thumbnail
     */
    @JsonSetter("thumbnail_160_height")
    public void setThumbnail160Height(final Integer thumbnail160Height) {
        this.mThumbnail160Height = thumbnail160Height;
    }

    /**
     * Sets url for 20px thumbnail.
     *
     * @param thumbnail220Url
     *            url for 220px thumbnail
     */
    @JsonSetter("thumbnail_220_url")
    public void setThumbnail220Url(final String thumbnail220Url) {
        this.mThumbnail220Url = thumbnail220Url;
    }

    /**
     * Sets width for 220px thumbnail.
     *
     * @param thumbnail220Width
     *            width for 220px thumbnail
     */
    @JsonSetter("thumbnail_220_width")
    public void setThumbnail220Width(final Double thumbnail220Width) {
        this.mThumbnail220Width = thumbnail220Width;
    }

    /**
     * Sets height for 220px thumbnail.
     *
     * @param thumbnail220Height
     *            height for 220px thumbnail
     */
    @JsonSetter("thumbnail_220_height")
    public void setThumbnail220Height(final Integer thumbnail220Height) {
        this.mThumbnail220Height = thumbnail220Height;
    }

    /**
     * Sets url for 240px thumbnail.
     *
     * @param thumbnail240Url
     *            url for 240px thumbnail
     */
    @JsonSetter("thumbnail_240_url")
    public void setThumbnail240Url(final String thumbnail240Url) {
        this.mThumbnail240Url = thumbnail240Url;
    }

    /**
     * Sets width for 240px thumbnail.
     *
     * @param thumbnail240Width
     *            width for 240px thumbnail
     */
    @JsonSetter("thumbnail_240_width")
    public void setThumbnail240Width(final Double thumbnail240Width) {
        this.mThumbnail240Width = thumbnail240Width;
    }

    /**
     * Sets height for 240px thumbnail.
     *
     * @param thumbnail240Height
     *            height for 240px thumbnail
     */
    @JsonSetter("thumbnail_240_height")
    public void setThumbnail240Height(final Integer thumbnail240Height) {
        this.mThumbnail240Height = thumbnail240Height;
    }

    /**
     * Sets url for 500px thumbnail.
     *
     * @param thumbnail500Url
     *            url for 500px thumbnail
     */
    @JsonSetter("thumbnail_500_url")
    public void setThumbnail500Url(final String thumbnail500Url) {
        this.mThumbnail500Url = thumbnail500Url;
    }

    /**
     * Sets width for 500px thumbnail.
     *
     * @param thumbnail500Width
     *            width for 500px thumbnail
     */
    @JsonSetter("thumbnail_500_width")
    public void setThumbnail500Width(final Double thumbnail500Width) {
        this.mThumbnail500Width = thumbnail500Width;
    }

    /**
     * Sets height for 500px thumbnail.
     *
     * @param thumbnail500Height
     *            height for 500px thumbnail
     */
    @JsonSetter("thumbnail_500_height")
    public void setThumbnail500Height(final Integer thumbnail500Height) {
        this.mThumbnail500Height = thumbnail500Height;
    }

    /**
     * Sets url for 1000px thumbnail.
     *
     * @param thumbnail1000Url
     *            url for 1000px thumbnail
     */
    @JsonSetter("thumbnail_1000_url")
    public void setThumbnail1000Url(final String thumbnail1000Url) {
        this.mThumbnail1000Url = thumbnail1000Url;
    }

    /**
     * Sets width for 1000px thumbnail.
     *
     * @param thumbnail1000Width
     *            width for 1000px thumbnail
     */
    @JsonSetter("thumbnail_1000_width")
    public void setThumbnail1000Width(final Double thumbnail1000Width) {
        this.mThumbnail1000Width = thumbnail1000Width;
    }

    /**
     * Sets height for 1000px thumbnail.
     *
     * @param thumbnail1000Height
     *            height for 1000px thumbnail
     */
    @JsonSetter("thumbnail_1000_height")
    public void setThumbnail1000Height(final Integer thumbnail1000Height) {
        this.mThumbnail1000Height = thumbnail1000Height;
    }
}
