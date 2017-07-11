package com.adobe.stock.models;

import com.fasterxml.jackson.annotation.JsonSetter;
/**
 * LicenseComp stores the information about complementary
 * or watermarked asset.
 */
public final class LicenseComp {
    /**
     * The URL from which the complementary asset can be downloaded.
     */
    private String mUrl;
    /**
     * Type of the complementary asset.
     */
    private String mContentType;
    /**
     * Width of complementary asset in pixels.
     */
    private Integer mWidth;
    /**
     * Height of complementary asset in pixels.
     */
    private Integer mHeight;
    /**
     * Frame rate for video.
     */
    private Double mFrameRate;
    /**
     * Size of complementary asset file in bytes.
     */
    private Integer mContentLength;
    /**
     * Duration of video in milliseconds.
     */
    private Integer mDuration;


    /**
     * Get URL from which the complementary asset can be downloaded.
     * @return URL of type String
     */
    public String getUrl() {
        return mUrl;
    }

    /**
     * Get Type of the complementary asset.
     * @return asset type of type String
     */
    public String getContentType() {
        return mContentType;
    }
    /**
     * Get Width of complementary asset in pixels.
     * @return width of type Integer
     */
    public Integer getWidth() {
        return mWidth;
    }
    /**
     * Get Height of complementary asset in pixels.
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
     * Sets URL from which the complementary asset can be downloaded.
     * @param url URL from which the complementary asset can be downloaded.
     */
    @JsonSetter("url")
    public void setUrl(final String url) {
        this.mUrl = url;
    }

    /**
     * Sets Type of the complementary asset.
     * @param contentType Type of the complementary asset.
     */
    @JsonSetter("content_type")
    public void setContentType(final String contentType) {
        this.mContentType = contentType;
    }
    /**
     * Sets Width of complementary asset in pixels.
     * @param width Width of complementary asset in pixels.
     */
    @JsonSetter("width")
    public void setWidth(final Integer width) {
        this.mWidth = width;
    }
    /**
     * Set Height of complementary asset in pixels.
     * @param height Height of complementary asset in pixels.
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
     * Sets Size of complementary asset file in bytes.
     * @param contentLength Size of complementary asset file in bytes.
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
