/*******************************************************************************
 * Copyright 2017 Adobe Systems Incorporated. All rights reserved.
 * This file is licensed to you under the Apache License, Version 2.0
 * (the "License") you may not use this file except in compliance with the
 * License. You may obtain a copy of the License at
 * http://www.apache.org/licenses/LICENSE-2.0
 ******************************************************************************/
package com.adobe.stock.models;

import com.adobe.stock.annotations.SearchParamURLMapperInternal;
import com.adobe.stock.enums.Asset3DType;
import com.adobe.stock.enums.AssetAge;
import com.adobe.stock.enums.AssetHasReleases;
import com.adobe.stock.enums.AssetsOrder;
import com.adobe.stock.enums.AssetOrientation;
import com.adobe.stock.enums.AssetPremiumCategory;
import com.adobe.stock.enums.AssetTemplateCategory;
import com.adobe.stock.enums.AssetTemplatesType;
import com.adobe.stock.enums.AssetThumbSize;
import com.adobe.stock.enums.AssetVideoDuration;

/**
 * SearchParameters allows to set the various search_parameters
 * (URL query parameters) supported by Search/Files Stock api. This is
 * the class where you can actually set the search keywords, limit,
 * sort order, filters, media_id etc.
 */
public final class SearchParameters {

    /**
     * Maximum number of assets that can be returned
     * in search results.
     */
    private static final int MAX_LIMIT = 64;

    /**
     * Minimum number of assets that can be returned
     * in search results.
     */
    private static final int MIN_LIMIT = 1;

    /**
     * Specific asset creator's ID.
     */
    @SearchParamURLMapperInternal(value = "[creator_id]")
    private Integer mCreator;

    /**
     * Unique identifier of a specific asset.
     */
    @SearchParamURLMapperInternal(value = "[media_id]")
    private Integer mMediaId;

    /**
     * Specific person id.
     */
    @SearchParamURLMapperInternal(value = "[model_id")
    private Integer mModelId;

    /**
     * Asset's series Id.
     */
    @SearchParamURLMapperInternal(value = "[serie_id")
    private Integer mSerieId;

    /**
     * Similar media id for specific search.
     */
    @SearchParamURLMapperInternal(value = "[similar]")
    private Integer mSimilar;

    /**
     * Specific category id to search assests.
     */
    @SearchParamURLMapperInternal(value = "[category]")
    private Integer mCategory;

    /**
     * Maximum number of assets in result.
     */
    @SearchParamURLMapperInternal(value = "[limit]")
    private Integer mLimit;

    /**
     * Start position(index) in search results.
     */
    @SearchParamURLMapperInternal(value = "[offset]")
    private Integer mOffset;

    /**
     * Keywords that you want to include in your files search.
     */
    @SearchParamURLMapperInternal(value = "[words]")
    private String mWords;

    /**
     * URL for searching assests that are similar in appearance
     * to an image at a specific URL.
     */
    @SearchParamURLMapperInternal(value = "[similar_url")
    private String mSimilarURL;

    /**
     * Search assets that contain the specific colors.
     */
    @SearchParamURLMapperInternal(value = "[filters][colors]")
    private String mFilterColors;

    /**
     * Gallery Id filter for search params.
     */
    @SearchParamURLMapperInternal(value = "[gallery_id]")
    private String mGalleryId;

    /**
     * Image sizes in pixels for returned assets in search.
     */
    @SearchParamURLMapperInternal(value = "[filters][area_pixels]")
    private Long mFilterAreaPixels;

    /**
     * SimilarImage filter can be true or false to find
     * visualy similar images.
     */
    @SearchParamURLMapperInternal(value = "[similar_image]",
            type = SearchParamURLMapperInternal.BOOLEAN_TO_INTEGER)
    private Boolean mSimilarImage;

    /**
     * ContentTypePhoto filter can be true or false
     * to find assets that are photos.
     */
    @SearchParamURLMapperInternal(value = "[filters][content_type:photo]",
            type = SearchParamURLMapperInternal.BOOLEAN_TO_INTEGER)
    private Boolean mFilterContentTypePhoto;

    /**
     * ContentTypeIllustration filter can be true or false
     * to find assets that are illustrations.
     */
    @SearchParamURLMapperInternal(value = "[filters]"
            + "[content_type:illustration]", type =
            SearchParamURLMapperInternal.BOOLEAN_TO_INTEGER)
    private Boolean mFilterContentTypeIllustration;

    /**
     * ContentTypeVector filter can be true or false
     * to find assets that are vectors.
     */
    @SearchParamURLMapperInternal(value = "[filters][content_type:vector]",
            type = SearchParamURLMapperInternal.BOOLEAN_TO_INTEGER)
    private Boolean mFilterContentTypeVector;

    /**
     * ContentTypeVideo filter can be true or false
     * to find assets that are videos.
     */
    @SearchParamURLMapperInternal(value = "[filters][content_type:video]",
            type = SearchParamURLMapperInternal.BOOLEAN_TO_INTEGER)
    private Boolean mFilterContentTypeVideo;

    /**
     * ContentTypeTemplate filter can be true or false
     * to find assets that are templates.
     */
    @SearchParamURLMapperInternal(value = "[filters][content_type:template]",
            type = SearchParamURLMapperInternal.BOOLEAN_TO_INTEGER)
    private Boolean mFilterContentTypeTemplate;

    /**
     * ContentType3D filter can be true or false
     * to find assets that are 3D.
     */
    @SearchParamURLMapperInternal(value = "[filters][content_type:3d]",
            type = SearchParamURLMapperInternal.BOOLEAN_TO_INTEGER)
    private Boolean mFilterContentType3D;

    /**
     * ContentTypeAll filter can be true or false
     * to find assets that are of all types.
     */
    @SearchParamURLMapperInternal(value = "[filters][content_type:all]",
            type = SearchParamURLMapperInternal.BOOLEAN_TO_INTEGER)
    private Boolean mFilterContentTypeAll;

    /**
     * Editorial filter can be true or false
     * to find assets that are editorial.
     */
    @SearchParamURLMapperInternal(value = "[filters][editorial]",
            type = SearchParamURLMapperInternal.BOOLEAN_TO_INTEGER)
    private Boolean mFilterEditorial;

    /**
     * Offensive2 filter can be true or false to find assets only if they
     * are flagged as including Explicit/Nudity/Violence.
     */
    @SearchParamURLMapperInternal(value = "[filters][offensive:2]",
            type = SearchParamURLMapperInternal.BOOLEAN_TO_INTEGER)
    private Boolean mFilterOffensive2;

    /**
     * IsolatedOn filter can be true or false to find assets only if the
     * subject is isolated from the background by being on a uniformly colored
     * background.
     */
    @SearchParamURLMapperInternal(value = "[filters][isolated:on]",
            type = SearchParamURLMapperInternal.BOOLEAN_TO_INTEGER)
    private Boolean mFilterIsolatedOn;

    /**
     * PanoromicOn filter can be true or false to find assets
     * that are panoromic.
     */
    @SearchParamURLMapperInternal(value = "[filters][panoramic:on]",
            type = SearchParamURLMapperInternal.BOOLEAN_TO_INTEGER)
    private Boolean mFilterPanoromicOn;

    /**
     * Size of thumbnail(in pixels) for each found asset in search
     * results.
     */
    @SearchParamURLMapperInternal(value = "[filters][thumbnail_size]")
    private AssetThumbSize mThumbnailSize;

    /**
     * Orientation filter in search Params for searching files of specific
     * orientation.
     */
    @SearchParamURLMapperInternal(value = "[filters][orientation]")
    private AssetOrientation mFilterOrientation;

    /**
     * Specified asset age groups that can be used in search parameters
     * for searching assets.
     */
    @SearchParamURLMapperInternal(value = "[filters][age]")
    private AssetAge mFilterAge;

    /**
     * Specified video duration that can be used in search parameters
     * for searching videos whose duration is no longer than the specified
     * duration in seconds.
     */
    @SearchParamURLMapperInternal(value = "[filters][video_duration]")
    private AssetVideoDuration mFilterVideoDuration;

    /**
     * specified template types that can be used in Search
     * Parameters for searching assets, if asset is a template.
     */
    @SearchParamURLMapperInternal(value = "[filters][template_type_id][]")
    private AssetTemplatesType[] mFilterTemplateTypes;

    /**
     * 3D types that can be used in search parameters for
     * searching assets.
     */
    @SearchParamURLMapperInternal(value = "[filters][3d_type_id][]")
    private Asset3DType[] mFilter3DTypeIds;

    /**
     * Specified template category identifiers that can be used in Search
     * Parameters for searching assets, if asset is a template.
     */
    @SearchParamURLMapperInternal(value = "[filters][template_category_id][]")
    private AssetTemplateCategory[] mFilterTemplateCategoryIds;

    /**
     * Sort order in which to return found assets.
     */
    @SearchParamURLMapperInternal(value = "[order]")
    private AssetsOrder mOrder;

    /**
     * Asset's premium (pricing) level that can be used in SearchParameters
     * for searching assets.
     */
    @SearchParamURLMapperInternal(value = "[filters][premium]")
    private AssetPremiumCategory mFilterPremium;

    /**
     * Asset's model or property releases that can be used in
     * search parameters for searching assets.
     */
    @SearchParamURLMapperInternal(value = "[filters][has_releases]")
    private AssetHasReleases mFilterHasReleases;

    /**
     * Default Constructor for SearchParameters.
     */
    public SearchParameters() {
    }

    /**
     * Get a specific asset creator's ID.
     * @return creator Id of type Integer
     */
    public Integer getCreatorId() {
        return mCreator;
    }

    /**
     * Sets a specific asset creator's ID in search Params for searching files.
     * @param creatorId
     *            : Specific asset creator's ID
     * @return SearchParameters object
     * @throws IllegalArgumentException
     *             if creator Id is not positive
     */
    public SearchParameters setCreatorId(final int creatorId) {

        if (creatorId <= 0) {
            throw new IllegalArgumentException("Should be a valid creator Id");
        }
        this.mCreator = creatorId;
        return this;
    }

    /**
     * Get unique identifier of a specific asset (media Id).
     * @return mediaId of type Integer
     */
    public Integer getMediaId() {
        return mMediaId;
    }

    /**
     * Sets unique identifier of a specific asset in search Params for searching
     * files.
     * @param mediaId
     *            : unique identifier of a asset
     * @return SearchParameters object
     * @throws IllegalArgumentException
     *             if media Id is not positive
     */
    public SearchParameters setMediaId(final int mediaId) {

        if (mediaId <= 0) {
            throw new IllegalArgumentException("Should be a valid Media Id");
        }
        this.mMediaId = mediaId;
        return this;
    }

    /**
     * Get a specific person (model) using the model's ID.
     * @return ModelId of type Integer
     */
    public Integer getModelId() {
        return mModelId;
    }

    /**
     * Sets a specific person (model) Id in search Params for searching files.
     * @param modelId
     *            : a specific person (model) Id
     * @return SearchParameters object
     * @throws IllegalArgumentException
     *             if model Id is not positive
     */
    public SearchParameters setModelId(final int modelId) {
        if (modelId <= 0) {
            throw new IllegalArgumentException("Should be a valid Model Id");
        }
        this.mModelId = modelId;
        return this;
    }

    /**
     * Get current asset's series Id.
     * @return SerieId of type Integer
     */
    public Integer getSerieId() {
        return mSerieId;
    }

    /**
     * Sets specific series for assets in search Params that you want to search.
     * @param serieId
     *            : Specific series Id of assests
     * @return SearchParameters object
     * @throws IllegalArgumentException
     *             if serie Id is not positive
     */
    public SearchParameters setSerieId(final int serieId) {
        if (serieId <= 0) {
            throw new IllegalArgumentException("Should be a valid Series Id");
        }
        this.mSerieId = serieId;
        return this;
    }

    /**
     * Get a specific media ID for similar search.
     * @return similarId of type Integer
     */
    public Integer getSimilar() {
        return mSimilar;
    }

    /**
     * Sets specific media ID that is similar in appearance to an asset in
     * search Params for searching similar files.
     * @param similar
     *            : specific media ID
     * @return SearchParameters object
     * @throws IllegalArgumentException
     *             if similar is not positive
     */
    public SearchParameters setSimilar(final int similar) {
        if (similar <= 0) {
            throw new IllegalArgumentException(
                    "Should be a valid previous mediaId");
        }
        this.mSimilar = similar;
        return this;
    }

    /**
     * Get a specific category ID.
     * @return categoryId of type Integer
     */
    public Integer getCategory() {
        return mCategory;
    }

    /**
     * Sets a specific category ID in search Params for searching files of this
     * category.
     * @param category
     *            : specific category ID
     * @return SearchParameters object
     * @throws IllegalArgumentException
     *             if category Id is not positive
     */
    public SearchParameters setCategory(final int category) {
        if (category <= 0) {
            throw new IllegalArgumentException("Should be a valid category");
        }
        this.mCategory = category;
        return this;
    }

    /**
     * Get maximum number of assets that return in the api call.
     * @return limit of type Integer
     */
    public Integer getLimit() {
        return mLimit;
    }

    /**
     * Sets maximum number of assets in search Params that you wants to return
     * in the api call.
     * @param limit
     *            : maximum number of assets that return in the api call
     * @return SearchParameters object
     * @throws IllegalArgumentException
     *             if limit doesn't lie between 1 and 64
     */
    public SearchParameters setLimit(final int limit) {
        if (limit < MIN_LIMIT || limit > MAX_LIMIT) {
            throw new IllegalArgumentException(
                    "Limit should be greator than 1 and less than 64");
        }
        this.mLimit = limit;
        return this;
    }

    /**
     * Get start position(index) in search results.
     * @return offset of type Integer
     */
    public Integer getOffset() {
        return mOffset;
    }

    /**
     * Sets the start position(index) in search results.
     * @param offset
     *            : starting index in the search results
     * @return SearchParameters object
     * @throws IllegalArgumentException
     *             if offset is not positive
     */
    public SearchParameters setOffset(final int offset) {
        if (offset < 0) {
            throw new IllegalArgumentException(
                    "Offset should be between 0 and MaxResults");
        }
        this.mOffset = offset;
        return this;
    }

    /**
     * Get keywords that you included in your specific files search.
     * @return words of type String
     */
    public String getWords() {
        return mWords;
    }

    /**
     * Sets keywords in search Params for searching files.
     * @param words
     *            : keywords that you want to search
     * @return SearchParameters object
     * @throws IllegalArgumentException
     *             if words is null or blank
     */
    public SearchParameters setWords(final String words) {
        if (words == null || words.isEmpty()) {
            throw new IllegalArgumentException(
                    "Should not be blank or null values in kewywords field");
        }
        this.mWords = words;
        return this;
    }

    /**
     * Get URL that you have set for searching assests that are similar in
     * appearance to an image at a specific URL.
     * @return similarURL of type String
     */
    public String getSimilarURL() {
        return mSimilarURL;
    }

    /**
     * Sets a URL for searching assests that are similar in appearance to an
     * image at a specific URL.
     * @param similarURL
     *            : URL to search a similar file
     * @return SearchParameters object
     * @throws IllegalArgumentException
     *             if similarURL is null or blank
     */
    public SearchParameters setSimilarURL(final String similarURL) {
        if (similarURL == null || similarURL.isEmpty()) {
            throw new IllegalArgumentException(
                    "Should not be blank or null values in similarURL field");
        }
        this.mSimilarURL = similarURL;
        return this;
    }

    /**
     * Get color that you have included in search params to search assets that
     * contain the specified colors.
     * @return filterColors of type String
     */
    public String getFilterColors() {
        return mFilterColors;
    }

    /**
     * Sets Color filter in search Params to search assets that contain the
     * specific colors.
     * @param filterColors
     *            Hexadecimal value of color
     * @return SearchParameters object
     * @throws IllegalArgumentException
     *             if filterColors is null or blank
     */
    public SearchParameters setFilterColors(final String filterColors) {
        if (filterColors == null || filterColors.isEmpty()) {
            throw new IllegalArgumentException(
                    "Should not be blank or null values in filterColors field");
        }
        this.mFilterColors = filterColors;
        return this;
    }

    /**
     * Get current galleryId filter.
     * @return galleryId of type String
     */
    public String getGalleryId() {
        return mGalleryId;
    }

    /**
     * Sets galleryId filter in search Params for searching files.
     * @param galleryId
     *            : specific gallery Id
     * @return SearchParameters object
     * @throws IllegalArgumentException
     *             if galleryId is null or blank
     */
    public SearchParameters setGalleryId(final String galleryId) {
        if (galleryId == null || galleryId.isEmpty()) {
            throw new IllegalArgumentException(
                    "Should not be blank or null values in galleryId field");
        }
        this.mGalleryId = galleryId;
        return this;
    }

    /**
     * Get image sizes in pixels that you have set in search parameters for
     * returned assets.
     * @return filterAreaPixels of type Long
     */
    public Long getFilterAreaPixels() {
        return mFilterAreaPixels;
    }

    /**
     * Sets image sizes in pixels for returned assets in search parameters.
     * @param filterAreaPixels
     *            : Image Size in pixels
     * @return SearchParameters object
     * @throws IllegalArgumentException
     *             if filterAreaPixels is not positive
     */
    public SearchParameters setFilterAreaPixels(final long filterAreaPixels) {
        if (filterAreaPixels < 0) {
            throw new IllegalArgumentException(
                    "FilterAreaPixels should be greater than zero");
        }
        this.mFilterAreaPixels = filterAreaPixels;
        return this;
    }

    /**
     * Get whether similarImage filter is on/off to find visualy similar Images.
     * @return whether similarImage filter is on or off
     */
    public Boolean getSimilarImage() {
        return mSimilarImage;
    }

    /**
     * Sets filter(true/false) for finding visual similar Images.
     * @param similarImage
     *            : True or false value
     * @return SearchParameters object
     * @throws IllegalArgumentException
     *             if similarImage is null
     */
    public SearchParameters setSimilarImage(final Boolean similarImage) {
        if (similarImage == null) {
            throw new IllegalArgumentException(
                    "SimilarImage filter should be true or false");
        }
        this.mSimilarImage = similarImage;
        return this;
    }

    /**
     * Get whether photo filter is on/off to find assets that are photos.
     * @return whether ContentTypePhoto filter is on or off
     */
    public Boolean getFilterContentTypePhoto() {
        return mFilterContentTypePhoto;
    }

    /**
     * Sets ContentTypePhoto filter in search Params to find assets that are
     * photos.
     * @param filterContentTypePhoto
     *            : True or False value
     * @return SearchParameters object
     * @throws IllegalArgumentException
     *             if filterContentTypePhoto is null
     */
    public SearchParameters setFilterContentTypePhoto(
            final Boolean filterContentTypePhoto) {
        if (filterContentTypePhoto == null) {
            throw new IllegalArgumentException(
                    "ContentTypePhoto filter should be true or false");
        }
        this.mFilterContentTypePhoto = filterContentTypePhoto;
        return this;
    }

    /**
     * Get whether Illustration filter is on/off to find assets that are
     * illustrations.
     * @return whether ContentTypeIllustration filter is on or off
     */
    public Boolean getFilterContentTypeIllustration() {
        return mFilterContentTypeIllustration;
    }

    /**
     * Sets ContentTypeIllustration filter in search Params to find assets that
     * are illustrations.
     * @param filterContentTypeIllustration
     *            : True or False value
     * @return SearchParameters object
     * @throws IllegalArgumentException
     *             if filterContentTypeIllustration is null
     */
    public SearchParameters setFilterContentTypeIllustration(
            final Boolean filterContentTypeIllustration) {
        if (filterContentTypeIllustration == null) {
            throw new IllegalArgumentException(
                    "Illustration filter should be true or false");
        }
        this.mFilterContentTypeIllustration = filterContentTypeIllustration;
        return this;
    }

    /**
     * Get whether Vector filter is on/off to find assets that are vectors.
     * @return whether ContentTypeVector filter is on or off
     */
    public Boolean getFilterContentTypeVector() {
        return mFilterContentTypeVector;
    }

    /**
     * Sets ContentTypeVector filter in search Params to find assets that are
     * vectors.
     * @param filterContentTypeVector
     *            : True or False value
     * @return SearchParameters object
     * @throws IllegalArgumentException
     *             if filterContentTypeVector is null
     */
    public SearchParameters setFilterContentTypeVector(
            final Boolean filterContentTypeVector) {
        if (filterContentTypeVector == null) {
            throw new IllegalArgumentException(
                    "Vector filter should be true or false");
        }
        this.mFilterContentTypeVector = filterContentTypeVector;
        return this;
    }

    /**
     * Get whether Videos filter is on/off to find assets that are videos.
     * @return whether ContentTypeVideo filter is on or off
     */
    public Boolean getFilterContentTypeVideo() {
        return mFilterContentTypeVideo;
    }

    /**
     * Sets ContentTypeVideo filter in search Params to find assets that are
     * videos.
     * @param filterContentTypeVideo
     *            : True or False value
     * @return SearchParameters object
     * @throws IllegalArgumentException
     *             if ContentTypeVideo is null
     */
    public SearchParameters setFilterContentTypeVideo(
            final Boolean filterContentTypeVideo) {
        if (filterContentTypeVideo == null) {
            throw new IllegalArgumentException(
                    "Video filter should be true or false");
        }
        this.mFilterContentTypeVideo = filterContentTypeVideo;
        return this;
    }

    /**
     * Get whether ContentTypeTemplate filter is on/off to find assets that are
     * videos.
     * @return whether ContentTypeTemplate filter is on or off
     */
    public Boolean getFilterContentTypeTemplate() {
        return mFilterContentTypeTemplate;
    }

    /**
     * Sets ContentTypeTemplate filter in search Params to find assets that are
     * templates.
     * @param filterContentTypeTemplate
     *            : True or False value
     * @return SearchParameters object
     * @throws IllegalArgumentException
     *             if ContentTypeTemplate is null
     */
    public SearchParameters setFilterContentTypeTemplate(
            final Boolean filterContentTypeTemplate) {
        if (filterContentTypeTemplate == null) {
            throw new IllegalArgumentException(
                    "Template filter should be true or false");
        }
        this.mFilterContentTypeTemplate = filterContentTypeTemplate;
        return this;
    }

    /**
     * Get whether ContentType3D filter is on/off to find assets that are of 3D
     * type.
     * @return whether ContentType3D filter is on or off
     */
    public Boolean getFilterContentType3D() {
        return mFilterContentType3D;
    }

    /**
     * Sets ContentType3D filter in search Params to find assets that are of 3D
     * type.
     * @param filterContentType3D
     *            : True or False value
     * @return SearchParameters object
     * @throws IllegalArgumentException
     *             if ContentType3D is null
     */
    public SearchParameters setFilterContentType3D(
            final Boolean filterContentType3D) {
        if (filterContentType3D == null) {
            throw new IllegalArgumentException(
                    "3D filter should be true or false");
        }
        this.mFilterContentType3D = filterContentType3D;
        return this;
    }

    /**
     * Get whether ContentTypeAll filter is on/off to find assets that are of
     * all types.
     * @return whether ContentTypeAll filter is on or off
     */
    public Boolean getFilterContentTypeAll() {
        return mFilterContentTypeAll;
    }

    /**
     * Sets ContentTypeAll filter in search Params to find assets that are of
     * all types.
     * @param filterContentTypeAll
     *            : True or False value
     * @return SearchParameters object
     * @throws IllegalArgumentException
     *             if ContentTypeAll is null
     */
    public SearchParameters setFilterContentTypeAll(
            final Boolean filterContentTypeAll) {
        if (filterContentTypeAll == null) {
            throw new IllegalArgumentException(
                    "Type All filter should be true or false");
        }
        this.mFilterContentTypeAll = filterContentTypeAll;
        return this;
    }

    /**
     * Get Editorial filter.
     * @return whether Editorial filter is on or off
     */
    public Boolean getFilterEditorial() {
        return mFilterEditorial;
    }

    /**
     * Sets Editorial filter in search Params for searching files.
     * @param filterEditorial
     *            : True or False value
     * @return SearchParameters object
     * @throws IllegalArgumentException
     *             if Editorial is null
     */
    public SearchParameters setFilterEditorial(final Boolean filterEditorial) {
        if (filterEditorial == null) {
            throw new IllegalArgumentException(
                    "Editorial filter should be OFF or ON");
        }
        this.mFilterEditorial = filterEditorial;
        return this;
    }

    /**
     * Get whether Offensive2 filter is on or off to find assets only if they
     * are flagged as including Explicit/Nudity/Violence.
     * @return whether Offensive2 filter is on or off
     */
    public Boolean getFilterOffensive2() {
        return mFilterOffensive2;
    }

    /**
     * Sets Offensive2 filter in search Params to find assets only if they are
     * flagged as including Explicit/Nudity/Violence.
     * @param filterOffensive2
     *            : True or False Value
     * @return SearchParameters object
     * @throws IllegalArgumentException
     *             if Offensive2 is null
     */
    public SearchParameters setFilterOffensive2(
                        final Boolean filterOffensive2) {
        if (filterOffensive2 == null) {
            throw new IllegalArgumentException(
                    "Offensive2 filter should be OFF or ON");
        }
        this.mFilterOffensive2 = filterOffensive2;
        return this;
    }

    /**
     * Get whether IsolatedOn filter is on or off to find assets only if the
     * subject is isolated from the background by being on a uniformly colored
     * background.
     * @return whether IsolatedOn filter is on or off
     */
    public Boolean getFilterIsolatedOn() {
        return mFilterIsolatedOn;
    }

    /**
     * Sets IsolatedOn filter in search Params to find assets only if the
     * subject is isolated from the background by being on a uniformly colored
     * background.
     * @param filterIsolatedOn
     *            : True or False value
     * @return SearchParameters object
     * @throws IllegalArgumentException
     *             if IsolatedOn is null
     */
    public SearchParameters setFilterIsolatedOn(
            final Boolean filterIsolatedOn) {
        if (filterIsolatedOn == null) {
            throw new IllegalArgumentException(
                    "Isolated filter should be OFF or ON");
        }
        this.mFilterIsolatedOn = filterIsolatedOn;
        return this;
    }

    /**
     * Get whether PanoromicOn filter is on or off to find assets only if they
     * are panoramic.
     * @return whether PanoromicOn filter is on or off
     */
    public Boolean getFilterPanoromicOn() {
        return mFilterPanoromicOn;
    }

    /**
     * Sets PanoromicOn filter in search Params to find assets only if they are
     * panoramic.
     * @param filterPanoromicOn
     *            : True or false value
     * @return SearchParameters object
     * @throws IllegalArgumentException
     *             if PanoromicOn is null
     */
    public SearchParameters setFilterPanoromicOn(
            final Boolean filterPanoromicOn) {
        if (filterPanoromicOn == null) {
            throw new IllegalArgumentException(
                    "Panoromic filter should be OFF or ON");
        }
        this.mFilterPanoromicOn = filterPanoromicOn;
        return this;
    }

    /**
     * Get the size of thumbnail(in pixels) for each found asset in search
     * results.
     * @return thumbnailSize of type AssetThumbSize enum
     */
    public AssetThumbSize getThumbnailSize() {
        return mThumbnailSize;
    }

    /**
     * Sets the size of thumbnail(in pixels) for each found asset in search
     * results.
     * @param thumbnailSize
     *            : Object of AssetThumbSize
     * @return SearchParameters object
     * @throws IllegalArgumentException
     *             if thumbnailSize is null
     * @see AssetThumbSize
     */
    public SearchParameters setThumbnailSize(
            final AssetThumbSize thumbnailSize) {
        if (thumbnailSize == null) {
            throw new IllegalArgumentException(
                    "Thumbnail size should not be null");
        }
        this.mThumbnailSize = thumbnailSize;
        return this;
    }

    /**
     * Get specified orientation of the returned assets.
     * @return Orientation of type AssetOrientation enum
     */
    public AssetOrientation getFilterOrientation() {
        return mFilterOrientation;
    }

    /**
     * Sets Orientation filter in search Params for searching files of specific
     * orientation.
     * @param filterOrientation
     *            : Object of AssetOrientation
     * @return SearchParameters object
     * @throws IllegalArgumentException
     *             if Orientation is null
     * @see AssetOrientation
     */
    public SearchParameters setFilterOrientation(
            final AssetOrientation filterOrientation) {
        if (filterOrientation == null) {
            throw new IllegalArgumentException(
                    "Orientation should not be null");
        }
        this.mFilterOrientation = filterOrientation;
        return this;
    }

    /**
     * Get age filter to find assets of the specified age.
     * @return Age of type AssetAge enum
     */
    public AssetAge getFilterAge() {
        return mFilterAge;
    }

    /**
     * Sets Age filter in search Params to find assets of the specified age.
     * @param filterAge
     *            : Object of AssetAge
     * @return SearchParameters object
     * @throws IllegalArgumentException
     *             if AgeFilter is null
     * @see AssetAge
     */
    public SearchParameters setFilterAge(final AssetAge filterAge) {
        if (filterAge == null) {
            throw new IllegalArgumentException("Age should not be null");
        }
        this.mFilterAge = filterAge;
        return this;
    }

    /**
     * Get VideoDuration that you have set to find videos whose duration is no
     * longer than the specified duration in seconds.
     * @return VideoDuration of type AssetVideoDuration enum
     */
    public AssetVideoDuration getFilterVideoDuration() {
        return mFilterVideoDuration;
    }

    /**
     * Sets VideoDuration to find videos whose duration is no longer than the
     * specified duration in seconds.
     * @param filterVideoDuration
     *            : Object of AssetVideoDuration
     * @return SearchParameters object
     * @throws IllegalArgumentException
     *             if VideoDuration is null
     * @see AssetVideoDuration
     */
    public SearchParameters setFilterVideoDuration(
            final AssetVideoDuration filterVideoDuration) {
        if (filterVideoDuration == null) {
            throw new IllegalArgumentException(
                    "Video duration should not be null");
        }
        this.mFilterVideoDuration = filterVideoDuration;
        return this;
    }

    /**
     * Get array specifying which template types to return in search results.
     * @return TemplateTypes Array of type AssetTemplatesType enum
     */
    public AssetTemplatesType[] getFilterTemplateTypes() {
        if (mFilterTemplateTypes == null) {
            return null;
        }
        return mFilterTemplateTypes.clone();
    }

    /**
     * Sets array specifying which template types to return in search results.
     * @param filterTemplateTypes
     *            : Array of AssetTemplatesType values
     * @return SearchParameters object
     * @throws IllegalArgumentException
     *             if TemplateTypes is null
     * @see AssetTemplatesType
     */
    public SearchParameters setFilterTemplateTypes(
            final AssetTemplatesType[] filterTemplateTypes) {
        if (filterTemplateTypes == null) {
            throw new IllegalArgumentException(
                    "Template Types should not be null");
        }
        this.mFilterTemplateTypes = filterTemplateTypes;
        return this;
    }

    /**
     * Get array specifying which 3D types to return in search results.
     * @return 3dTypeIds Array of type Asset3DType enum
     */
    public Asset3DType[] getFilter3DTypeIds() {
        if (mFilter3DTypeIds == null) {
            return null;
        }
        return mFilter3DTypeIds.clone();
    }

    /**
     * Sets array specifying which 3D types to return in search results.
     * @param filter3dTypeIds
     *            : Array of Asset3DType values
     * @return SearchParameters object
     * @throws IllegalArgumentException
     *             if 3dTypeIds is null
     * @see Asset3DType
     */
    public SearchParameters setFilter3DTypeIds(
            final Asset3DType[] filter3dTypeIds) {
        if (filter3dTypeIds == null) {
            throw new IllegalArgumentException("3D Types should not be null");
        }
        mFilter3DTypeIds = filter3dTypeIds;
        return this;
    }

    /**
     * Get array specifying which template categories to return.
     * @return TemplateCategoryIds Array of type AssetTemplateCategoryIds
     *         enum
     */
    public AssetTemplateCategory[] getFilterTemplateCategoryIds() {
        if (mFilterTemplateCategoryIds == null) {
            return null;
        }
        return mFilterTemplateCategoryIds.clone();
    }

    /**
     * Sets array specifying which template categories to return.
     * @param filterTemplateCategoryIds
     *            : Array of AssetTemplateCategory values
     * @return SearchParameters object
     * @throws IllegalArgumentException
     *             if TemplateCategoryIds is null
     * @see AssetTemplateCategory
     */
    public SearchParameters setFilterTemplateCategoryIds(
            final AssetTemplateCategory[] filterTemplateCategoryIds) {
        if (filterTemplateCategoryIds == null) {
            throw new IllegalArgumentException(
                    "TemplateCategory Ids should not be null");
        }
        this.mFilterTemplateCategoryIds = filterTemplateCategoryIds;
        return this;
    }

    /**
     * Get sorting order in which it will return found assets.
     * @return Object of AssetsOrder enum
     */
    public AssetsOrder getOrder() {
        return mOrder;
    }

    /**
     * Sets sorting order in which it will return found assets.
     * @param order
     *            : Object of AssetsOrder
     * @return SearchParameters object
     * @throws IllegalArgumentException
     *             if order is null
     * @see AssetsOrder
     */
    public SearchParameters setOrder(final AssetsOrder order) {
        if (order == null) {
            throw new IllegalArgumentException("Orders should not be null");
        }
        this.mOrder = order;
        return this;
    }

    /**
     * Get premium (pricing) level to find the assests.
     * @return Object of type AssetPremiumCategory enum
     */
    public AssetPremiumCategory getFilterPremium() {
        return mFilterPremium;
    }

    /**
     * Sets premium (pricing) level to find the assests.
     * @param filterPremium
     *            : Object of AssetPremiumCategory
     * @return SearchParameters object
     * @throws IllegalArgumentException
     *             if Premium is null
     * @see AssetPremiumCategory
     */
    public SearchParameters setFilterPremium(
            final AssetPremiumCategory filterPremium) {
        if (filterPremium == null) {
            throw new IllegalArgumentException(
                    "Premium Filters should not be null");
        }
        this.mFilterPremium = filterPremium;
        return this;
    }

    /**
     * Get HasReleases filter that you have set to find assets which has model
     * or property releases.
     * @return Object of type AssetPremiumCategory enum
     */
    public AssetHasReleases getFilterHasReleases() {
        return mFilterHasReleases;
    }

    /**
     * Sets HasReleases filter to find assets which has model or property
     * releases.
     * @param filterHasReleases
     *            : Object of AssetHasReleases
     * @return SearchParameters object
     * @throws IllegalArgumentException
     *             if HasReleases is null
     * @see AssetHasReleases
     */

    public SearchParameters setFilterHasReleases(
            final AssetHasReleases filterHasReleases) {
        if (filterHasReleases == null) {
            throw new IllegalArgumentException(
                    "Filter Releases should not be null");
        }
        this.mFilterHasReleases = filterHasReleases;
        return this;
    }

    // return this in constructors

}
