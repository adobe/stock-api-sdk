/*******************************************************************************
 * Copyright 2017 Adobe Systems Incorporated. All rights reserved.
 * This file is licensed to you under the Apache License, Version 2.0
 * (the "License") you may not use this file except in compliance with the
 * License. You may obtain a copy of the License at
 * http://www.apache.org/licenses/LICENSE-2.0
 *******************************************************************************/
package com.adobe.stock.enums;

import java.util.HashMap;

import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;

import com.adobe.stock.enums.ResultColumn;

@Listeners({ com.adobe.stock.logger.TestCustomLogger.class,
        com.adobe.stock.logger.TestSuiteLogger.class })
@Test(suiteName = "ResultColumn")
public class ResultColumnTest {

    private HashMap<ResultColumn, String> testData = new HashMap<ResultColumn, String>();

    @BeforeClass
    public void initializeTestData() {
        testData.put(ResultColumn.NB_RESULTS, "nb_results");
        testData.put(ResultColumn.ID, "id");
        testData.put(ResultColumn.TITLE, "title");
        testData.put(ResultColumn.CREATOR_NAME, "creator_name");
        testData.put(ResultColumn.CREATOR_ID, "creator_id");
        testData.put(ResultColumn.COUNTRY_NAME, "country_name");
        testData.put(ResultColumn.WIDTH, "width");
        testData.put(ResultColumn.HEIGHT, "height");
        testData.put(ResultColumn.THUMBNAIL_URL, "thumbnail_url");
        testData.put(ResultColumn.THUMBNAIL_HTML_TAG, "thumbnail_html_tag");
        testData.put(ResultColumn.THUMBNAIL_WIDTH, "thumbnail_width");
        testData.put(ResultColumn.THUMBNAIL_HEIGHT, "thumbnail_height");
        testData.put(ResultColumn.THUMBNAIL_110_URL, "thumbnail_110_url");
        testData.put(ResultColumn.THUMBNAIL_110_WIDTH, "thumbnail_110_width");
        testData.put(ResultColumn.THUMBNAIL_110_HEIGHT, "thumbnail_110_height");
        testData.put(ResultColumn.THUMBNAIL_160_URL, "thumbnail_160_url");
        testData.put(ResultColumn.THUMBNAIL_160_WIDTH, "thumbnail_160_width");
        testData.put(ResultColumn.THUMBNAIL_160_HEIGHT, "thumbnail_160_height");
        testData.put(ResultColumn.THUMBNAIL_220_URL, "thumbnail_220_url");
        testData.put(ResultColumn.THUMBNAIL_220_WIDTH, "thumbnail_220_width");
        testData.put(ResultColumn.THUMBNAIL_220_HEIGHT, "thumbnail_220_height");
        testData.put(ResultColumn.THUMBNAIL_240_URL, "thumbnail_240_url");
        testData.put(ResultColumn.THUMBNAIL_240_WIDTH, "thumbnail_240_width");
        testData.put(ResultColumn.THUMBNAIL_240_HEIGHT, "thumbnail_240_height");
        testData.put(ResultColumn.THUMBNAIL_500_URL, "thumbnail_500_url");
        testData.put(ResultColumn.THUMBNAIL_500_WIDTH, "thumbnail_500_width");
        testData.put(ResultColumn.THUMBNAIL_500_HEIGHT, "thumbnail_500_height");
        testData.put(ResultColumn.THUMBNAIL_1000_URL, "thumbnail_1000_url");
        testData.put(ResultColumn.THUMBNAIL_1000_WIDTH, "thumbnail_1000_width");
        testData.put(ResultColumn.THUMBNAIL_1000_HEIGHT,
                "thumbnail_1000_height");
        testData.put(ResultColumn.MEDIA_TYPE_ID, "media_type_id");
        testData.put(ResultColumn.CATEGORY, "category");
        testData.put(ResultColumn.CATEGORY_HIERARCHY, "category_hierarchy");
        testData.put(ResultColumn.NB_VIEWS, "nb_views");
        testData.put(ResultColumn.NB_DOWNLOADS, "nb_downloads");
        testData.put(ResultColumn.CREATION_DATE, "creation_date");
        testData.put(ResultColumn.KEYWORDS, "keywords");
        testData.put(ResultColumn.HAS_RELEASES, "has_releases");
        testData.put(ResultColumn.COMP_URL, "comp_url");
        testData.put(ResultColumn.COMP_WIDTH, "comp_width");
        testData.put(ResultColumn.COMP_HEIGHT, "comp_height");
        testData.put(ResultColumn.IS_LICENSED, "is_licensed");
        testData.put(ResultColumn.VECTOR_TYPE, "vector_type");
        testData.put(ResultColumn.CONTENT_TYPE, "content_type");
        testData.put(ResultColumn.FRAMERATE, "framerate");
        testData.put(ResultColumn.DURATION, "duration");
        testData.put(ResultColumn.STOCK_ID, "stock_id");
        testData.put(ResultColumn.COMPS, "comps");
        testData.put(ResultColumn.DETAILS_URL, "details_url");
        testData.put(ResultColumn.TEMPLATE_TYPE_ID, "template_type_id");
        testData.put(ResultColumn.TEMPLATE_CATEGORY_IDS,
                "template_category_ids");
        testData.put(ResultColumn.MARKETING_TEXT, "marketing_text");
        testData.put(ResultColumn.DESCRIPTION, "description");
        testData.put(ResultColumn.SIZE_BYTES, "size_bytes");
        testData.put(ResultColumn.PREMIUM_LEVEL_ID, "premium_level_id");
        testData.put(ResultColumn.IS_EDITORIAL, "is_editorial");
        testData.put(ResultColumn.IS_PREMIUM, "is_premium");
        testData.put(ResultColumn.LICENSES, "licenses");
        testData.put(ResultColumn.VIDEO_PREVIEW_URL, "video_preview_url");
        testData.put(ResultColumn.VIDEO_PREVIEW_WIDTH, "video_preview_width");
        testData.put(ResultColumn.VIDEO_PREVIEW_HEIGHT, "video_preview_height");
        testData.put(ResultColumn.VIDEO_PREVIEW_CONTENT_LENGTH,
                "video_preview_content_length");
        testData.put(ResultColumn.VIDEO_PREVIEW_CONTENT_TYPE,
                "video_preview_content_type");
        testData.put(ResultColumn.VIDEO_SMALL_PREVIEW_URL,
                "video_small_preview_url");
        testData.put(ResultColumn.VIDEO_SMALL_PREVIEW_WIDTH,
                "video_small_preview_width");
        testData.put(ResultColumn.VIDEO_SMALL_PREVIEW_HEIGHT,
                "video_small_preview_height");
        testData.put(ResultColumn.VIDEO_SMALL_PREVIEW_CONTENT_LENGTH,
                "video_small_preview_content_length");
        testData.put(ResultColumn.VIDEO_SMALL_PREVIEW_CONTENT_TYPE,
                "video_small_preview_content_type");
    }

    @Test(groups = "ResultColumn.toString")
    public void toString_should_return_string_equivalent_value_of_ResultColumn_enum() {
        for (ResultColumn env : ResultColumn.values()) {
            ResultColumn.valueOf(env.name());
            Assert.assertEquals(env.toString(), testData.get(env));
        }
    }
}