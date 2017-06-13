package com.adobe.stock.models;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

import com.adobe.stock.enums.AssetLicenseState;
import com.adobe.stock.enums.AssetPremiumLevel;
import com.adobe.stock.enums.AssetType;
import com.adobe.stock.enums.AssetTemplateCategory;
import com.adobe.stock.enums.AssetTemplatesType;
import com.adobe.stock.exception.StockException;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonSetter;
import com.fasterxml.jackson.databind.JsonNode;

/**
 * StockFile stores stock media file properties returned from
 * search/files stock api.
 */
@JsonIgnoreProperties(ignoreUnknown = true)
public final class StockFile {

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
     * Media creation date.
     */
    private Date mCreationDate;
    /**
     * Media creator country name.
     */
    private String mCountryName;
    /**
     * URL for the default-sized asset thumbnail.
     */
    private String mThumbnailUrl;
    /**
     * HTML <img> tag that you can use to display the default asset thumbnail.
     */
    private String mThumbnailHtmlTag;
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
     * Original width of the file in pixels.
     */
    private Integer mWidth;
    /**
     * Original height of the file in pixels.
     */
    private Integer mHeight;
    /**
     * Licensing state for the asset.
     */
    private AssetLicenseState mIsLicensed;
    /**
     * URL to the watermarked version of the asset.
     */
    private String mCompUrl;
    /**
     * Width in pixels of the asset's complementary (unlicensed) image.
     */
    private Integer mCompWidth;
    /**
     * Height in pixels of the asset's complementary (unlicensed) image.
     */
    private Integer mCompHeight;
    /**
     * Total views of the asset by all users.
     */
    private Integer mNbViews;
    /**
     * Total downloads of the asset by all users.
     */
    private Integer mNbDownloads;
    /**
     * Category of the media.
     */
    private StockFileCategory mCategory;
    /**
     * List of localised keywords for the file.
     */
    private ArrayList<StockFileKeyword> mKeywords;
    /**
     * Checks if content has any release IDs.
     */
    private Boolean mHasReleases;
    /**
     * Type of the asset.
     */
    private AssetType mAssetTypeId;
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
     * Frame rate for video.
     */
    private Double mFrameRate;
    /**
     * Duration of video in milliseconds.
     */
    private Integer mDuration;
    /**
     * Stock identifier.
     */
    private String mStockId;
    /**
     * Contains properties for complementary assets.
     */
    private StockFileComps mComps;
    /**
     * Url to stock details page for the asset.
     */
    private String mDetailsUrl;
    /**
     * (templates only) Id of the template.
     */
    private AssetTemplatesType mTemplateTypeId;
    /**
     * (templates only) ArrayList of template category ids.
     */
    private ArrayList<AssetTemplateCategory> mTemplateCategoryIds;
    /**
     * (templates only) Marketing text for template.
     */
    private String mMarketingText;
    /**
     * (templates only) Description text for template.
     */
    private String mDescription;
    /**
     * (templates only) Size of the template file in bytes.
     */
    private Integer mSizeBytes;
    /**
     * Identifies the asset premium(pricing) level.
     */
    private AssetPremiumLevel mPremiumLevelId;
    /**
     * True for premium assets (where premium_level_id > 1), false for standard
     * assets.
     */
    private Boolean mIsPremium;

    /**
     * Contains available licenses for the media.
     */
    private StockFileLicenses mLicenses;
    /**
     * (videos only) URL for the regular preview.
     */
    private String mVideoPreviewUrl;
    /**
     * (videos only) Height of the regular preview in pixels.
     */
    private Integer mVideoPreviewHeight;
    /**
     * (videos only) Width of the regular preview in pixels.
     */
    private Integer mVideoPreviewWidth;
    /**
     * (videos only) File size of the regular preview in bytes.
     */
    private Integer mVideoPreviewContentLength;
    /**
     * (videos only) Content type (i.e. MIME type) of the regular preview.
     */
    private String mVideoPreviewContentType;
    /**
     * (videos only) URL for the small preview.
     */
    private String mVideoSmallPreviewUrl;
    /**
     * (videos only) Height of the small preview in pixels.
     */
    private Integer mVideoSmallPreviewHeight;
    /**
     * (videos only) Width of the small preview in pixels.
     */
    private Integer mVideoSmallPreviewWidth;
    /**
     * (videos only) File size of the small preview in bytes.
     */
    private Integer mVideoSmallPreviewContentLength;
    /**
     * (videos only) Content type (i.e. MIME type) of the small preview.
     */
    private String mVideoSmallPreviewContentType;

    /**
     * Default constructor.
     */
    public StockFile() {
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
     * Get media creation date.
     *
     * @return creation date of type Date
     */
    public Date getCreationDate() {
        return mCreationDate;
    }

    /**
     * Get media creator country name.
     *
     * @return country of type String
     */
    public String getCountryName() {
        return mCountryName;
    }

    /**
     * Get URL for the default-sized asset thumbnail.
     *
     * @return asset thumbnail url of type String
     */
    public String getThumbnailUrl() {
        return mThumbnailUrl;
    }

    /**
     * Get HTML &lt;img&gt; tag that you can use to display the
     * default asset thumbnail.
     *
     * @return HTML tag of type String
     */
    public String getThumbnailHtmlTag() {
        return mThumbnailHtmlTag;
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
     * Get licensing state for the asset.
     *
     * @return license state of type {@link AssetLicenseState}
     */
    public AssetLicenseState getIsLicensed() {
        return mIsLicensed;
    }

    /**
     * Get URL to the watermarked version of the asset.
     *
     * @return url of type String
     */
    public String getCompUrl() {
        return mCompUrl;
    }

    /**
     * Get width in pixels of the asset's complementary (unlicensed) image.
     *
     * @return width of type Integer
     */
    public Integer getCompWidth() {
        return mCompWidth;
    }

    /**
     * Get height in pixels of the asset's complementary (unlicensed) image.
     *
     * @return height of type Integer
     */
    public Integer getCompHeight() {
        return mCompHeight;
    }

    /**
     * Get total views of the asset by all users.
     *
     * @return views count of type Integer
     */
    public Integer getNbViews() {
        return mNbViews;
    }

    /**
     * Get total downloads of the asset by all users.
     *
     * @return downloads of type Integer
     */
    public Integer getNbDownloads() {
        return mNbDownloads;
    }

    /**
     * Get category of the media.
     *
     * @return category of type {@link StockFileCategory}
     * @see StockFileCategory
     */
    public StockFileCategory getCategory() {
        return mCategory;
    }

    /**
     * Get list of localised keywords for the file.
     *
     * @return keywords of type {@link StockFileKeyword}
     * @see StockFileKeyword
     */
    public ArrayList<StockFileKeyword> getKeywords() {
        return mKeywords;
    }

    /**
     * Checks if content has any release IDs.
     *
     * @return true if content has any release IDs else false
     */
    public Boolean getHasReleases() {
        return mHasReleases;
    }

    /**
     * Get type of the asset.
     *
     * @return enum of type {@link AssetType}
     */
    public AssetType getAssetTypeId() {
        return mAssetTypeId;
    }

    /**
     * If the asset is a vector, this returns whether it is an
     * SVG or an AI/EPS (zip) asset.
     *
     * @return vector type of type String
     */
    public String getVectorType() {
        return mVectorType;
    }

    /**
     * Get mime type of the asset's content.
     *
     * @return mime type of type String
     */
    public String getContentType() {
        return mContentType;
    }

    /**
     * Get frame rate for video.
     *
     * @return frame rate of type Double
     */
    public Double getFrameRate() {
        return mFrameRate;
    }

    /**
     * Get duration of video in milliseconds.
     *
     * @return duration of type Integer
     */
    public Integer getDuration() {
        return mDuration;
    }

    /**
     * Get stock identifier.
     *
     * @return stock identifier of type String
     */
    public String getStockId() {
        return mStockId;
    }

    /**
     * Get properties for complementary assets.
     *
     * @return properties for complementary assets
     * @see StockFileComps
     */
    public StockFileComps getComps() {
        return mComps;
    }

    /**
     * Get url of stock details page for the asset.
     *
     * @return url of type String
     */
    public String getDetailsUrl() {
        return mDetailsUrl;
    }

    /**
     * Get id of the template type if the returned asset is a template.
     *
     * @return id of type {@link AssetTemplatesType}
     */
    public AssetTemplatesType getTemplateTypeId() {
        return mTemplateTypeId;
    }

    /**
     * Get array of template category ids if the returned asset is a template.
     *
     * @return Arraylist of ids of type {@link AssetTemplateCategory}
     */
    public ArrayList<AssetTemplateCategory> getTemplateCategoryIds() {
        return mTemplateCategoryIds;
    }

    /**
     * Get marketing text if the returned asset is a template.
     *
     * @return text of type String
     */
    public String getMarketingText() {
        return mMarketingText;
    }

    /**
     * Get description text if the returned asset is a template.
     *
     * @return description of type String
     */
    public String getDescription() {
        return mDescription;
    }

    /**
     * Get size of the template file in bytes if the
     *  returned asset is a template.
     *
     * @return size of type Integer
     */
    public Integer getSizeBytes() {
        return mSizeBytes;
    }

    /**
     * Get premium level id.
     *
     * @return enum of type {@link AssetPremiumLevel}
     */
    public AssetPremiumLevel getPremiumLevelId() {
        return mPremiumLevelId;
    }

    /**
     * Checks if premium asset.
     *
     * @return true for premium assets and false for standard assets
     */
    public Boolean getIsPremium() {
        return mIsPremium;
    }

    /**
     * Get available licenses for the media.
     *
     * @return license object of type {@link StockFileLicenses}
     */
    public StockFileLicenses getLicenses() {
        return mLicenses;
    }

    /**
     * Get url for the video preview.
     *
     * @return url of type String
     */
    public String getVideoPreviewUrl() {
        return mVideoPreviewUrl;
    }

    /**
     * Get height for the video preview in pixels.
     *
     * @return height of type Integer
     */
    public Integer getVideoPreviewHeight() {
        return mVideoPreviewHeight;
    }

    /**
     * Get width for the video preview in pixels.
     *
     * @return width of type Integer
     */
    public Integer getVideoPreviewWidth() {
        return mVideoPreviewWidth;
    }

    /**
     * Get file size of video preview in bytes.
     *
     * @return size of type Integer
     */
    public Integer getVideoPreviewContentLength() {
        return mVideoPreviewContentLength;
    }

    /**
     * Get content type of video preview.
     *
     * @return content type of type String
     */
    public String getVideoPreviewContentType() {
        return mVideoPreviewContentType;
    }

    /**
     * Get url of the small video preview.
     *
     * @return url of type Integer
     */
    public String getVideoSmallPreviewUrl() {
        return mVideoSmallPreviewUrl;
    }

    /**
     * Get height of the small video preview in pixels.
     *
     * @return height of type Integer
     */
    public Integer getVideoSmallPreviewHeight() {
        return mVideoSmallPreviewHeight;
    }

    /**
     * Get width of the small video preview in pixels.
     *
     * @return width of type Integer
     */
    public Integer getVideoSmallPreviewWidth() {
        return mVideoSmallPreviewWidth;
    }

    /**
     * Get file size of the small video preview in bytes.
     *
     * @return size of type Integer
     */
    public Integer getVideoSmallPreviewContentLength() {
        return mVideoSmallPreviewContentLength;
    }

    /**
     * Get content type of the small video preview in bytes.
     *
     * @return content type of type String
     */
    public String getVideoSmallPreviewContentType() {
        return mVideoSmallPreviewContentType;
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
     * Sets media creation date.
     *
     * @param creationDate
     *            media creation date string
     * @throws StockException
     *             if date string format is not correct
     */
    @JsonSetter("creation_date")
    public void setCreationDate(final String creationDate)
            throws StockException {
        String formatString = "yyyy-MM-dd hh:mm:ss";
        // date format with milliseconds
        SimpleDateFormat format = new SimpleDateFormat(
                formatString + ".SSS");
        // date format without milliseconds
        SimpleDateFormat formatWithoutMS = new SimpleDateFormat(
                formatString);
        try {
            if (creationDate.length() <= formatString.length()) {
                this.mCreationDate = formatWithoutMS.parse(creationDate);
            } else {
                this.mCreationDate = format.parse(creationDate);
            }
        } catch (ParseException e) {
            throw new StockException("Could not parse date string");
        }
    }

    /**
     * Sets media creator country name.
     *
     * @param countryName
     *            media creator country name
     */
    @JsonSetter("country_name")
    public void setCountryName(final String countryName) {
        this.mCountryName = countryName;
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
     * Sets HTML &lt;img&gt; tag that you can use to display the default
     * asset thumbnail.
     *
     * @param thumbnailHtmlTag
     *            media thumbnail html tag
     */
    @JsonSetter("thumbnail_html_tag")
    public void setThumbnailHtmlTag(final String thumbnailHtmlTag) {
        this.mThumbnailHtmlTag = thumbnailHtmlTag;
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
     * Sets licensing state for the asset.
     *
     * @param isLicensed
     *            licensing state for the asset
     */
    @JsonSetter("is_licensed")
    public void setIsLicensed(final AssetLicenseState isLicensed) {
        this.mIsLicensed = isLicensed;
    }

    /**
     * Sets URL to the watermarked version of the asset.
     *
     * @param compUrl
     *            URL to the watermarked version of the asset
     */
    @JsonSetter("comp_url")
    public void setCompUrl(final String compUrl) {
        this.mCompUrl = compUrl;
    }

    /**
     * Sets width in pixels of the asset's complementary (unlicensed) image.
     *
     * @param compWidth
     *           width in pixels
     */
    @JsonSetter("comp_width")
    public void setCompWidth(final Integer compWidth) {
        this.mCompWidth = compWidth;
    }

    /**
     * Sets height in pixels of the asset's complementary (unlicensed) image.
     *
     * @param compHeight
     *            height in pixels
     */
    @JsonSetter("comp_height")
    public void setCompHeight(final Integer compHeight) {
        this.mCompHeight = compHeight;
    }

    /**
     * Sets total views of the asset by all users.
     *
     * @param nbViews
     *            total views of the asset by all users
     */
    @JsonSetter("nb_views")
    public void setNbViews(final Integer nbViews) {
        this.mNbViews = nbViews;
    }

    /**
     * Sets total downloads of the asset by all users.
     *
     * @param nbDownloads
     *            total downloads of the asset by all users
     */
    @JsonSetter("nb_downloads")
    public void setNbDownloads(final Integer nbDownloads) {
        this.mNbDownloads = nbDownloads;
    }

    /**
     * Sets category of the media.
     *
     * @param node
     *            category of the media
     */
    @JsonSetter("category")
    public void setCategory(final JsonNode node) {
        if (node != null) {
            if (node.isObject()) {
                Integer id = node.get("id").intValue();
                String name = node.get("name").asText();
                this.mCategory = new StockFileCategory(id, name);
            }
        }
    }

    /**
     * Sets list of localised keywords for the file.
     *
     * @param keywords
     *            arraylist of localised keywords for the file
     */
    @JsonSetter("keywords")
    public void setKeywords(final ArrayList<StockFileKeyword> keywords) {
        this.mKeywords = keywords;
    }

    /**
     * Sets if content has any release IDs.
     *
     * @param hasReleases
     *               "1" if content has any release IDs else "0"
     */
    @JsonSetter("has_releases")
    public void setHasReleases(final Boolean hasReleases) {
        this.mHasReleases = hasReleases;
    }

    /**
     * Sets type of the asset.
     *
     * @param assetType
     *            type of the asset
     */
    @JsonSetter("media_type_id")
    public void setAssetTypeId(final AssetType assetType) {
        this.mAssetTypeId = assetType;
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
     * Sets frame rate for video.
     *
     * @param frameRate
     *            frame rate for video
     */
    @JsonSetter("framerate")
    public void setFrameRate(final Double frameRate) {
        this.mFrameRate = frameRate;
    }

    /**
     * Sets duration of video in milliseconds.
     *
     * @param duration
     *            duration of video in milliseconds
     */
    @JsonSetter("duration")
    public void setDuration(final Integer duration) {
        this.mDuration = duration;
    }

    /**
     * Sets stock identifier.
     *
     * @param stockId
     *            stock identifier
     */
    @JsonSetter("stock_id")
    public void setStockId(final String stockId) {
        this.mStockId = stockId;
    }

    /**
     * Sets properties for complementary assets.
     *
     * @param comps
     *            properties for complementary assets
     */
    @JsonSetter("comps")
    public void setComps(final StockFileComps comps) {
        this.mComps = comps;
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
     * Sets id of the template.
     *
     * @param templateTypeId
     *            id of the template
     */
    @JsonSetter("template_type_id")
    public void setTemplateTypeId(
            final AssetTemplatesType templateTypeId) {
        this.mTemplateTypeId = templateTypeId;
    }

    /**
     * Sets array of template category ids.
     *
     * @param templateCategoryIds
     *            arraylist of template category ids
     */
    @JsonSetter("template_category_ids")
    public void setTemplateCategoryIds(
            final ArrayList<AssetTemplateCategory> templateCategoryIds) {
        this.mTemplateCategoryIds = templateCategoryIds;
    }

    /**
     * Sets marketing text for template.
     *
     * @param marketingText
     *            marketing text for template
     */
    @JsonSetter("marketing_text")
    public void setMarketingText(final String marketingText) {
        this.mMarketingText = marketingText;
    }

    /**
     * Sets description text for template.
     *
     * @param description
     *            description text for template
     */
    @JsonSetter("description")
    public void setDescription(final String description) {
        this.mDescription = description;
    }

    /**
     * Sets size of the template file in bytes.
     *
     * @param sizeBytes
     *            size of the template file in bytes
     */
    @JsonSetter("size_bytes")
    public void setSizeBytes(final Integer sizeBytes) {
        this.mSizeBytes = sizeBytes;
    }

    /**
     * Sets premium level id.
     *
     * @param premiumLevelId
     *            premium level id of type {@link AssetPremiumLevel}
     */
    @JsonSetter("premium_level_id")
    public void setPremiumLevelId(final AssetPremiumLevel premiumLevelId) {
        this.mPremiumLevelId = premiumLevelId;
    }

    /**
     * Sets if premium asset.
     *
     * @param isPremium
     *            true for premium assets and false for standard assets
     */
    @JsonSetter("is_premium")
    public void setIsPremium(final Boolean isPremium) {
        this.mIsPremium = isPremium;
    }

    /**
     * Sets available licenses for the media.
     *
     * @param licenses
     *            available licenses for the media
     */
    @JsonSetter("licenses")
    public void setLicenses(final StockFileLicenses licenses) {
        this.mLicenses = licenses;
    }

    /**
     * Sets url for the video preview.
     *
     * @param videoPreviewUrl
     *            url for the video preview
     */
    @JsonSetter("video_preview_url")
    public void setVideoPreviewUrl(final String videoPreviewUrl) {
        this.mVideoPreviewUrl = videoPreviewUrl;
    }

    /**
     * Sets height for the video preview in pixels.
     *
     * @param videoPreviewHeight
     *            height for the video preview in pixels
     */
    @JsonSetter("video_preview_height")
    public void setVideoPreviewHeight(final Integer videoPreviewHeight) {
        this.mVideoPreviewHeight = videoPreviewHeight;
    }

    /**
     * Sets width for the video preview in pixels.
     *
     * @param videoPreviewWidth
     *            width for the video preview in pixels
     */
    @JsonSetter("video_preview_width")
    public void setVideoPreviewWidth(final Integer videoPreviewWidth) {
        this.mVideoPreviewWidth = videoPreviewWidth;
    }

    /**
     * Sets file size of video preview in bytes.
     *
     * @param videoPreviewContentLength
     *            file size of video preview in bytes
     */
    @JsonSetter("video_preview_content_length")
    public void setVideoPreviewContentLength(
            final Integer videoPreviewContentLength) {
        this.mVideoPreviewContentLength = videoPreviewContentLength;
    }

    /**
     * Sets content type of video preview.
     *
     * @param videoPreviewContentType
     *            content type of video preview
     */
    @JsonSetter("video_preview_content_type")
    public void setVideoPreviewContentType(
            final String videoPreviewContentType) {
        this.mVideoPreviewContentType = videoPreviewContentType;
    }

    /**
     * Sets url of the small video preview.
     *
     * @param videoSmallPreviewUrl
     *            url of the small video preview
     */
    @JsonSetter("video_small_preview_url")
    public void setVideoSmallPreviewUrl(final String videoSmallPreviewUrl) {
        this.mVideoSmallPreviewUrl = videoSmallPreviewUrl;
    }

    /**
     * Sets height of the small video preview in pixels.
     *
     * @param videoSmallPreviewHeight
     *            height of the small video preview in pixels
     */
    @JsonSetter("video_small_preview_height")
    public void setVideoSmallPreviewHeight(
            final Integer videoSmallPreviewHeight) {
        this.mVideoSmallPreviewHeight = videoSmallPreviewHeight;
    }

    /**
     * Sets width of the small video preview in pixels.
     *
     * @param videoSmallPreviewWidth
     *            width of the small video preview in pixels
     */
    @JsonSetter("video_small_preview_width")
    public void setVideoSmallPreviewWidth(
            final Integer videoSmallPreviewWidth) {
        this.mVideoSmallPreviewWidth = videoSmallPreviewWidth;
    }

    /**
     * Sets file size of the small video preview in bytes.
     *
     * @param videoSmallPreviewContentLength
     *            file size of the small video preview in bytes
     */
    @JsonSetter("video_small_preview_content_length")
    public void setVideoSmallPreviewContentLength(
            final Integer videoSmallPreviewContentLength) {
        this.mVideoSmallPreviewContentLength = videoSmallPreviewContentLength;
    }

    /**
     * Sets content type of the small video preview in bytes.
     *
     * @param videoSmallPreviewContentType
     *            content type of the small video preview in bytes
     */
    @JsonSetter("video_small_preview_content_type")
    public void setVideoSmallPreviewContentType(
            final String videoSmallPreviewContentType) {
        this.mVideoSmallPreviewContentType = videoSmallPreviewContentType;
    }

}
