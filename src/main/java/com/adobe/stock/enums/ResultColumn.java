/*******************************************************************************
 * Copyright 2017 Adobe Systems Incorporated. All rights reserved.
 * This file is licensed to you under the Apache License, Version 2.0
 * (the "License") you may not use this file except in compliance with the
 * License. You may obtain a copy of the License at
 * http://www.apache.org/licenses/LICENSE-2.0
 ******************************************************************************/
package com.adobe.stock.enums;

/**
 * Search result columns for Search/Files api. Each enum represent one of the
 * possible result columns which can be used to request the specific information
 * for the assets returned in the search results.
 */
public enum ResultColumn {

    /**
     * Total number of found assets in the search result.
     */
    NB_RESULTS("nb_results"),

    /**
     * Asset's unique identifier.
     */
    ID("id"),

    /**
     * Asset's title.
     */
    TITLE("title"),

    /**
     * Asset creator's name.
     */
    CREATOR_NAME("creator_name"),

    /**
     * Unique identifier for the asset's creator.
     */
    CREATOR_ID("creator_id"),

    /**
     * Country in which the asset's creator lives.
     */
    COUNTRY_NAME("country_name"),

    /**
     * Width in pixels of the full-sized original asset.
     */
    WIDTH("width"),

    /**
     * Height in pixels of the full-sized original asset.
     */
    HEIGHT("height"),

    /**
     * URL for the default-sized asset thumbnail. You can use this to display
     * the thumbnail on your page using your preferred display method.
     * Alternatively, use thumbnail_html_tag.
     */
    THUMBNAIL_URL("thumbnail_url"),

    /**
     * HTML &lt;img&gt; tag that you can use to display the default
     * asset thumbnail.This is a convenience for displaying the thumbnail
     * and references the thumbnail_url.
     * <p>
     * For example:
     * <p>
     * &#x3C;img src=&#x22;https://as1.ftcdn.net/jpg/00/86/76/04/
     * 110_F_86760419_NEhOeuriYu82RwfgDqjTeIL9yx7ih5iv.jpg&#x22;
     * alt=&#x22;German Shepherd Dog Sticking Head Out Driving Car Window&#x22;
     * title=&#x22;Photo: German Shepherd Dog Sticking Head Out Driving Car
     * Window&#x22; /&#x3E;
     */
    THUMBNAIL_HTML_TAG("thumbnail_html_tag"),

    /**
     * Thumbnail's width in pixels.
     */
    THUMBNAIL_WIDTH("thumbnail_width"),

    /**
     * Thumbnail's height in pixels.
     */
    THUMBNAIL_HEIGHT("thumbnail_height"),

    /**
     * URL for the requested thumbnail of the 110 px in size.
     */
    THUMBNAIL_110_URL("thumbnail_110_url"),

    /**
     * Width for the thumbnail of the 110px in size.
     */
    THUMBNAIL_110_WIDTH("thumbnail_110_width"),

    /**
     * Height for the thumbnail of the 110px size.
     */
    THUMBNAIL_110_HEIGHT("thumbnail_110_height"),

    /**
     * URL for the requested thumbnail of the 160 px in size.
     */
    THUMBNAIL_160_URL("thumbnail_160_url"),

    /**
     * Width for the thumbnail of the 160px in size.
     */
    THUMBNAIL_160_WIDTH("thumbnail_160_width"),

    /**
     * Height for the thumbnail of the 160px size.
     */
    THUMBNAIL_160_HEIGHT("thumbnail_160_height"),

    /**
     * URL for the requested thumbnail of the 220 px in size.
     */
    THUMBNAIL_220_URL("thumbnail_220_url"),

    /**
     * Width for the thumbnail of the 220px in size.
     */
    THUMBNAIL_220_WIDTH("thumbnail_220_width"),

    /**
     * Height for the thumbnail of the 220px size.
     */
    THUMBNAIL_220_HEIGHT("thumbnail_220_height"),

    /**
     * URL for the requested thumbnail of the 240 px in size.
     */
    THUMBNAIL_240_URL("thumbnail_240_url"),

    /**
     * Width for the thumbnail of the 240px in size.
     */
    THUMBNAIL_240_WIDTH("thumbnail_240_width"),

    /**
     * Height for the thumbnail of the 240px size.
     */
    THUMBNAIL_240_HEIGHT("thumbnail_240_height"),

    /**
     * URL for the requested thumbnail of the 500 px in size.
     */
    THUMBNAIL_500_URL("thumbnail_500_url"),

    /**
     * Width for the thumbnail of the 500px in size.
     */
    THUMBNAIL_500_WIDTH("thumbnail_500_width"),

    /**
     * Height for the thumbnail of the 500px size.
     */
    THUMBNAIL_500_HEIGHT("thumbnail_500_height"),

    /**
     * URL for the requested thumbnail of the 1000 px in size.
     */
    THUMBNAIL_1000_URL("thumbnail_1000_url"),

    /**
     * Width for the thumbnail of the 1000px in size.
     */
    THUMBNAIL_1000_WIDTH("thumbnail_1000_width"),

    /**
     * Height for the thumbnail of the 1000px size.
     */
    THUMBNAIL_1000_HEIGHT("thumbnail_1000_height"),

    /**
     * Type of the asset.
     */
    MEDIA_TYPE_ID("media_type_id"),

    /**
     * The category assigned to the asset.Category consists of - Identifier for
     * the category assigned to the asset. - Localised name of the asset's
     * category.
     */
    CATEGORY("category"),

    /**
     * The category hierarchy associated with the asset.
     */
    CATEGORY_HIERARCHY("category_hierarchy"),

    /**
     * Total views of the asset by all users.
     */
    NB_VIEWS("nb_views"),

    /**
     * Total downloads of the asset by all users.
     */
    NB_DOWNLOADS("nb_downloads"),

    /**
     * Date on which the asset was created.
     */
    CREATION_DATE("creation_date"),

    /**
     * List of localised keywords for the asset.
     */
    KEYWORDS("keywords"),

    /**
     * Model or property releases of asset.
     */
    HAS_RELEASES("has_releases"),

    /**
     * URL to the watermarked version of the asset.
     */
    COMP_URL("comp_url"),

    /**
     * Width in pixels of the asset's complementary (unlicensed) image.
     */
    COMP_WIDTH("comp_width"),

    /**
     * Height in pixels of the asset's complementary (unlicensed) image.
     */
    COMP_HEIGHT("comp_height"),

    /**
     * The Adobe Stock licensing state for the asset.
     */
    IS_LICENSED("is_licensed"),

    /**
     * If the asset is a vector, this indicates whether it is an SVG or an
     * AI/EPS asset.
     */
    VECTOR_TYPE("vector_type"),

    /**
     * Mime type of the asset's content.
     */
    CONTENT_TYPE("content_type"),

    /**
     * Frame rate for the asset if it is a video.
     */
    FRAMERATE("framerate"),

    /**
     * Duration in milliseconds of the asset if it is a video.
     */
    DURATION("duration"),

    /**
     * The Stock identifier of the asset.
     */
    STOCK_ID("stock_id"),

    /**
     * StockFileComps object contains one or more of the following properties
     * for complementary assets if applicable - Standard, Video_HD, or Video_4K.
     * The properties contain width, height, comp URL.
     */
    COMPS("comps"),

    /**
     * URL to the Adobe Stock details page for the asset. If the access token is
     * passed with api, Adobe Stock generates an SSO jump URL.
     */
    DETAILS_URL("details_url"),

    /**
     * The ID of the template type, if the returned asset is a template.
     */
    TEMPLATE_TYPE_ID("template_type_id"),

    /**
     * List of template category identifiers for the asset if the asset is a
     * template.
     */
    TEMPLATE_CATEGORY_IDS("template_category_ids"),

    /**
     * Marketing text for the template in Markdown format, if the found asset is
     * a template.
     */
    MARKETING_TEXT("marketing_text"),

    /**
     * Description text for the template in Markdown format, if the found asset
     * is a template.
     */
    DESCRIPTION("description"),

    /**
     * Size of the template file in bytes, if the found asset is a template.
     */
    SIZE_BYTES("size_bytes"),

    /**
     * Asset's premium (pricing) level.
     */
    PREMIUM_LEVEL_ID("premium_level_id"),

    /**
     * if the asset is editorial.
     */
    IS_EDITORIAL("is_editorial"),

    /**
     * true for premium assets (where premium_level_id greater than 1),
     * false for standard assets.
     */
    IS_PREMIUM("is_premium"),

    /**
     * Contains available licenses with the following types: "Standard" and (if
     * half-priced premium available) "Standard-M". Each type is an object of
     * StockFileCompProp contains the following properties: width, height, and
     * url. URL corresponds to the licensing URL.
     */
    LICENSES("licenses"),

    /**
     * For Video assets only, URL for the regular preview.
     */
    VIDEO_PREVIEW_URL("video_preview_url"),

    /**
     * For Video assets only, Width of the regular preview in pixels.
     */
    VIDEO_PREVIEW_WIDTH("video_preview_width"),

    /**
     * For Video assets only, Height of the regular preview in pixels.
     */
    VIDEO_PREVIEW_HEIGHT("video_preview_height"),

    /**
     * For Video assets only, File size of the regular preview in bytes.
     */
    VIDEO_PREVIEW_CONTENT_LENGTH("video_preview_content_length"),

    /**
     * For Video assets only, Content type (i.e. MIME type) of the regular.
     * preview
     */
    VIDEO_PREVIEW_CONTENT_TYPE("video_preview_content_type"),

    /**
     * For Video assets only, URL for the small preview.
     */
    VIDEO_SMALL_PREVIEW_URL("video_small_preview_url"),

    /**
     * For Video assets only, Width of the small preview in pixels.
     */
    VIDEO_SMALL_PREVIEW_WIDTH("video_small_preview_width"),

    /**
     * For Video assets only, Height of the small preview in pixels.
     */
    VIDEO_SMALL_PREVIEW_HEIGHT("video_small_preview_height"),

    /**
     * For Video assets only, File size of the small preview in bytes.
     */
    VIDEO_SMALL_PREVIEW_CONTENT_LENGTH("video_small_preview_content_length"),

    /**
     * For Video assets only, Content type (i.e. MIME type) of the small
     * preview.
     */
    VIDEO_SMALL_PREVIEW_CONTENT_TYPE("video_small_preview_content_type");

    /**
     * The corresponding value for the ResultColumn enum.
     */
    private String mValue;

    /**
     * Constructs the ResultColumn enum with provided string value.
     *
     * @param value
     *            the string value of the enum
     */
    ResultColumn(final String value) {
        this.mValue = value;
    }

    /**
     * Returns the string value of the enum.
     */
    @Override
    public String toString() {
        return this.mValue;
    }
}
