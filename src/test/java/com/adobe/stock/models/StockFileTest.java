/*******************************************************************************
 * Copyright 2017 Adobe Systems Incorporated. All rights reserved.
 * This file is licensed to you under the Apache License, Version 2.0
 * (the "License") you may not use this file except in compliance with the
 * License. You may obtain a copy of the License at
 * http://www.apache.org/licenses/LICENSE-2.0
 *******************************************************************************/
package com.adobe.stock.models;

import java.io.IOException;
import java.lang.reflect.Field;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

import org.testng.Assert;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;

import com.adobe.stock.enums.AssetLicenseState;
import com.adobe.stock.enums.AssetPremiumLevel;
import com.adobe.stock.enums.AssetType;
import com.adobe.stock.enums.AssetTemplateCategory;
import com.adobe.stock.enums.AssetTemplatesType;
import com.adobe.stock.exception.StockException;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ArrayNode;

@Listeners({ com.adobe.stock.logger.TestCustomLogger.class,
        com.adobe.stock.logger.TestSuiteLogger.class })
@Test(suiteName = "StockFile")
public class StockFileTest {
    StockFile stockFile;

    @BeforeSuite()
    void create_StockFile_instance_successfully() {
        stockFile = new StockFile();
    }

    @Test(groups = { "Getters" })
    void getId_should_return_media_unique_ID_of_Type_Integer_StockFile()
            throws NoSuchFieldException, IllegalAccessException {
        Field f = stockFile.getClass().getDeclaredField("mId");
        f.setAccessible(true);
        f.set(stockFile, 100);
        Assert.assertEquals(100, stockFile.getId().intValue());
    }

    @Test(groups = { "Setters" })
    void setId_should_set_media_unique_ID_of_Type_Integer_StockFile()
            throws NoSuchFieldException, IllegalAccessException {
        stockFile.setId(1000);
        Field f = stockFile.getClass().getDeclaredField("mId");
        f.setAccessible(true);
        Assert.assertEquals(1000, f.get(stockFile));
    }

    @Test(groups = { "Getters" })
    void getCreatorId_should_return_media_unique_creator_ID_of_Type_Integer_StockFile()
            throws NoSuchFieldException, IllegalAccessException {
        Field f = stockFile.getClass().getDeclaredField("mCreatorId");
        f.setAccessible(true);
        f.set(stockFile, 100);
        Assert.assertEquals(100, stockFile.getCreatorId().intValue());
    }

    @Test(groups = { "Setters" })
    void setCreatorId_should_set_media_unique_creator_ID_of_Type_Integer_StockFile()
            throws NoSuchFieldException, IllegalAccessException {
        stockFile.setCreatorId(1000);
        Field f = stockFile.getClass().getDeclaredField("mCreatorId");
        f.setAccessible(true);
        Assert.assertEquals(1000, f.get(stockFile));
    }

    @Test(groups = { "Getters" })
    void getThumbnailWidth_should_return_media_ThumbnailWidth_inPixels_of_Type_Integer_StockFile()
            throws NoSuchFieldException, IllegalAccessException {
        Field f = stockFile.getClass().getDeclaredField("mThumbnailWidth");
        f.setAccessible(true);
        f.set(stockFile, 100);
        Assert.assertEquals(100, stockFile.getThumbnailWidth().intValue());
    }

    @Test(groups = { "Setters" })
    void setThumbnailWidth_should_set_media_ThumbnailWidth_inPixels_of_Type_Integer_StockFile()
            throws NoSuchFieldException, IllegalAccessException {
        stockFile.setThumbnailWidth(1000);
        Field f = stockFile.getClass().getDeclaredField("mThumbnailWidth");
        f.setAccessible(true);
        Assert.assertEquals(1000, f.get(stockFile));
    }

    @Test(groups = { "Getters" })
    void getThumbnailHeight_should_return_media_ThumbnailHeight_inPixels_of_Type_Integer_StockFile()
            throws NoSuchFieldException, IllegalAccessException {
        Field f = stockFile.getClass().getDeclaredField("mThumbnailHeight");
        f.setAccessible(true);
        f.set(stockFile, 100);
        Assert.assertEquals(100, stockFile.getThumbnailHeight().intValue());
    }

    @Test(groups = { "Setters" })
    void setThumbnailHeight_should_set_media_ThumbnailHeight_inPixels_of_Type_Integer_StockFile()
            throws NoSuchFieldException, IllegalAccessException {
        stockFile.setThumbnailHeight(1000);
        Field f = stockFile.getClass().getDeclaredField("mThumbnailHeight");
        f.setAccessible(true);
        Assert.assertEquals(1000, f.get(stockFile));
    }

    @Test(groups = { "Getters" })
    void getThumbnail110Width_should_return_width_of_110Pixels_Thumbnail_of_Type_Integer_StockFile()
            throws NoSuchFieldException, IllegalAccessException {
        Field f = stockFile.getClass().getDeclaredField("mThumbnail110Width");
        f.setAccessible(true);
        f.set(stockFile, 100.00);
        Assert.assertEquals(100.00, stockFile.getThumbnail110Width());
    }

    @Test(groups = { "Setters" })
    void setThumbnail110Width_should_set_width_of_110Pixels_Thumbnail_of_Type_Integer_StockFile()
            throws NoSuchFieldException, IllegalAccessException {
        stockFile.setThumbnail110Width(1000.00);
        Field f = stockFile.getClass().getDeclaredField("mThumbnail110Width");
        f.setAccessible(true);
        Assert.assertEquals(1000.00, f.get(stockFile));
    }

    @Test(groups = { "Getters" })
    void getThumbnail110Height_should_return_height_of_110Pixels_Thumbnail_of_Type_Integer_StockFile()
            throws NoSuchFieldException, IllegalAccessException {
        Field f = stockFile.getClass().getDeclaredField("mThumbnail110Height");
        f.setAccessible(true);
        f.set(stockFile, 100);
        Assert.assertEquals(100, stockFile.getThumbnail110Height().intValue());
    }

    @Test(groups = { "Setters" })
    void setThumbnail110Height_should_height_of_110Pixels_Thumbnail_of_Type_Integer_StockFile()
            throws NoSuchFieldException, IllegalAccessException {
        stockFile.setThumbnail110Height(1000);
        Field f = stockFile.getClass().getDeclaredField("mThumbnail110Height");
        f.setAccessible(true);
        Assert.assertEquals(1000, f.get(stockFile));
    }

    @Test(groups = { "Getters" })
    void getThumbnail160Width_should_return_width_of_160Pixels_Thumbnail_of_Type_Integer_StockFile()
            throws NoSuchFieldException, IllegalAccessException {
        Field f = stockFile.getClass().getDeclaredField("mThumbnail160Width");
        f.setAccessible(true);
        f.set(stockFile, 100.00);
        Assert.assertEquals(100.00, stockFile.getThumbnail160Width());
    }

    @Test(groups = { "Setters" })
    void setThumbnail160Width_should_set_width_of_160Pixels_Thumbnail_of_Type_Integer_StockFile()
            throws NoSuchFieldException, IllegalAccessException {
        stockFile.setThumbnail160Width(1000.00);
        Field f = stockFile.getClass().getDeclaredField("mThumbnail160Width");
        f.setAccessible(true);
        Assert.assertEquals(1000.00, f.get(stockFile));
    }

    @Test(groups = { "Getters" })
    void getThumbnail160Height_should_return_height_of_160Pixels_Thumbnail_of_Type_Integer_StockFile()
            throws NoSuchFieldException, IllegalAccessException {
        Field f = stockFile.getClass().getDeclaredField("mThumbnail160Height");
        f.setAccessible(true);
        f.set(stockFile, 100);
        Assert.assertEquals(100, stockFile.getThumbnail160Height().intValue());
    }

    @Test(groups = { "Setters" })
    void setThumbnail160Height_should_set_height_of_160Pixels_Thumbnail_of_Type_Integer_StockFile()
            throws NoSuchFieldException, IllegalAccessException {
        stockFile.setThumbnail160Height(1000);
        Field f = stockFile.getClass().getDeclaredField("mThumbnail160Height");
        f.setAccessible(true);
        Assert.assertEquals(1000, f.get(stockFile));
    }

    @Test(groups = { "Getters" })
    void getThumbnail220Width_should_return_height_of_220Pixels_Thumbnail_of_Type_Integer_StockFile()
            throws NoSuchFieldException, IllegalAccessException {
        Field f = stockFile.getClass().getDeclaredField("mThumbnail220Width");
        f.setAccessible(true);
        f.set(stockFile, 100.00);
        Assert.assertEquals(100.00, stockFile.getThumbnail220Width());
    }

    @Test(groups = { "Setters" })
    void setThumbnail220Width_should_set_width_of_220Pixels_Thumbnail_of_Type_Integer_StockFile()
            throws NoSuchFieldException, IllegalAccessException {
        stockFile.setThumbnail220Width(1000.00);
        Field f = stockFile.getClass().getDeclaredField("mThumbnail220Width");
        f.setAccessible(true);
        Assert.assertEquals(1000.00, f.get(stockFile));
    }

    @Test(groups = { "Getters" })
    void getThumbnail220Height_should_return_height_of_220Pixels_Thumbnail_of_Type_Integer_StockFile()
            throws NoSuchFieldException, IllegalAccessException {
        Field f = stockFile.getClass().getDeclaredField("mThumbnail220Height");
        f.setAccessible(true);
        f.set(stockFile, 100);
        Assert.assertEquals(100, stockFile.getThumbnail220Height().intValue());
    }

    @Test(groups = { "Setters" })
    void setThumbnail220Height_should_set_height_of_220Pixels_Thumbnail_of_Type_Integer_StockFile()
            throws NoSuchFieldException, IllegalAccessException {
        stockFile.setThumbnail220Height(1000);
        Field f = stockFile.getClass().getDeclaredField("mThumbnail220Height");
        f.setAccessible(true);
        Assert.assertEquals(1000, f.get(stockFile));
    }

    @Test(groups = { "Getters" })
    void getThumbnail240Width_should_return_height_of_240Pixels_Thumbnail_of_Type_Integer_StockFile()
            throws NoSuchFieldException, IllegalAccessException {
        Field f = stockFile.getClass().getDeclaredField("mThumbnail240Width");
        f.setAccessible(true);
        f.set(stockFile, 100.00);
        Assert.assertEquals(100.00, stockFile.getThumbnail240Width());
    }

    @Test(groups = { "Setters" })
    void setThumbnail240Width_should_set_width_of_240Pixels_Thumbnail_of_Type_Integer_StockFile()
            throws NoSuchFieldException, IllegalAccessException {
        stockFile.setThumbnail240Width(1000.00);
        Field f = stockFile.getClass().getDeclaredField("mThumbnail240Width");
        f.setAccessible(true);
        Assert.assertEquals(1000.00, f.get(stockFile));
    }

    @Test(groups = { "Getters" })
    void getThumbnail240Height_should_return_height_of_240Pixels_Thumbnail_of_Type_Integer_StockFile()
            throws NoSuchFieldException, IllegalAccessException {
        Field f = stockFile.getClass().getDeclaredField("mThumbnail240Height");
        f.setAccessible(true);
        f.set(stockFile, 100);
        Assert.assertEquals(100, stockFile.getThumbnail240Height().intValue());
    }

    @Test(groups = { "Setters" })
    void setThumbnail240Height_should_set_height_of_240Pixels_Thumbnail_of_Type_Integer_StockFile()
            throws NoSuchFieldException, IllegalAccessException {
        stockFile.setThumbnail240Height(1000);
        Field f = stockFile.getClass().getDeclaredField("mThumbnail240Height");
        f.setAccessible(true);
        Assert.assertEquals(1000, f.get(stockFile));
    }

    @Test(groups = { "Getters" })
    void getThumbnail500Width_should_return_height_of_500Pixels_Thumbnail_of_Type_Integer_StockFile()
            throws NoSuchFieldException, IllegalAccessException {
        Field f = stockFile.getClass().getDeclaredField("mThumbnail500Width");
        f.setAccessible(true);
        f.set(stockFile, 100.00);
        Assert.assertEquals(100.00, stockFile.getThumbnail500Width());
    }

    @Test(groups = { "Setters" })
    void setThumbnail500Width_should_set_width_of_500Pixels_Thumbnail_of_Type_Integer_StockFile()
            throws NoSuchFieldException, IllegalAccessException {
        stockFile.setThumbnail500Width(1000.00);
        Field f = stockFile.getClass().getDeclaredField("mThumbnail500Width");
        f.setAccessible(true);
        Assert.assertEquals(1000.00, f.get(stockFile));
    }

    @Test(groups = { "Getters" })
    void getThumbnail500Height_should_return_height_of_500Pixels_Thumbnail_of_Type_Integer_StockFile()
            throws NoSuchFieldException, IllegalAccessException {
        Field f = stockFile.getClass().getDeclaredField("mThumbnail500Height");
        f.setAccessible(true);
        f.set(stockFile, 100);
        Assert.assertEquals(100, stockFile.getThumbnail500Height().intValue());
    }

    @Test(groups = { "Setters" })
    void setThumbnail500Height_should_set_height_of_500Pixels_Thumbnail_of_Type_Integer_StockFile()
            throws NoSuchFieldException, IllegalAccessException {
        stockFile.setThumbnail500Height(1000);
        Field f = stockFile.getClass().getDeclaredField("mThumbnail500Height");
        f.setAccessible(true);
        Assert.assertEquals(1000, f.get(stockFile));
    }

    @Test(groups = { "Getters" })
    void getThumbnail1000Width_should_return_height_of_1000Pixels_Thumbnail_of_Type_Integer_StockFile()
            throws NoSuchFieldException, IllegalAccessException {
        Field f = stockFile.getClass().getDeclaredField("mThumbnail1000Width");
        f.setAccessible(true);
        f.set(stockFile, 100.00);
        Assert.assertEquals(100.00, stockFile.getThumbnail1000Width());
    }

    @Test(groups = { "Setters" })
    void setThumbnail1000Width_should_set_width_of_1000Pixels_Thumbnail_of_Type_Integer_StockFile()
            throws NoSuchFieldException, IllegalAccessException {
        stockFile.setThumbnail1000Width(1000.00);
        Field f = stockFile.getClass().getDeclaredField("mThumbnail1000Width");
        f.setAccessible(true);
        Assert.assertEquals(1000.00, f.get(stockFile));
    }

    @Test(groups = { "Getters" })
    void getThumbnail1000Height_should_return_height_of_1000Pixels_Thumbnail_of_Type_Integer_StockFile()
            throws NoSuchFieldException, IllegalAccessException {
        Field f = stockFile.getClass().getDeclaredField("mThumbnail1000Height");
        f.setAccessible(true);
        f.set(stockFile, 100);
        Assert.assertEquals(100, stockFile.getThumbnail1000Height().intValue());
    }

    @Test(groups = { "Setters" })
    void setThumbnail1000Height_should_set_height_of_1000Pixels_Thumbnail_of_Type_Integer_StockFile()
            throws NoSuchFieldException, IllegalAccessException {
        stockFile.setThumbnail1000Height(1000);
        Field f = stockFile.getClass().getDeclaredField("mThumbnail1000Height");
        f.setAccessible(true);
        Assert.assertEquals(1000, f.get(stockFile));
    }

    @Test(groups = { "Getters" })
    void getWidth_should_return_original_width_of_the_file_px_of_Type_Integer_StockFile()
            throws NoSuchFieldException, IllegalAccessException {
        Field f = stockFile.getClass().getDeclaredField("mWidth");
        f.setAccessible(true);
        f.set(stockFile, 100);
        Assert.assertEquals(100, stockFile.getWidth().intValue());
    }

    @Test(groups = { "Setters" })
    void setWidth_should_set_original_width_of_the_file_px_of_Type_Integer_StockFile()
            throws NoSuchFieldException, IllegalAccessException {
        stockFile.setWidth(1000);
        Field f = stockFile.getClass().getDeclaredField("mWidth");
        f.setAccessible(true);
        Assert.assertEquals(1000, f.get(stockFile));
    }

    @Test(groups = { "Getters" })
    void getHeight_should_return_original_height_of_the_file_px_of_Type_Integer_StockFile()
            throws NoSuchFieldException, IllegalAccessException {
        Field f = stockFile.getClass().getDeclaredField("mHeight");
        f.setAccessible(true);
        f.set(stockFile, 100);
        Assert.assertEquals(100, stockFile.getHeight().intValue());
    }

    @Test(groups = { "Setters" })
    void setHeight_should_set_original_height_of_the_file_px_of_Type_Integer_StockFile()
            throws NoSuchFieldException, IllegalAccessException {
        stockFile.setHeight(1000);
        Field f = stockFile.getClass().getDeclaredField("mHeight");
        f.setAccessible(true);
        Assert.assertEquals(1000, f.get(stockFile));
    }

    @Test(groups = { "Getters" })
    void getCompWidth_should_return_Comp_height_of_Type_Integer_StockFile()
            throws NoSuchFieldException, IllegalAccessException {
        Field f = stockFile.getClass().getDeclaredField("mCompWidth");
        f.setAccessible(true);
        f.set(stockFile, 100);
        Assert.assertEquals(100, stockFile.getCompWidth().intValue());
    }

    @Test(groups = { "Setters" })
    void setCompWidth_should_set_Comp_height_of_Type_Integer_StockFile()
            throws NoSuchFieldException, IllegalAccessException {
        stockFile.setCompWidth(1000);
        Field f = stockFile.getClass().getDeclaredField("mCompWidth");
        f.setAccessible(true);
        Assert.assertEquals(1000, f.get(stockFile));
    }

    @Test(groups = { "Getters" })
    void getCompHeight_should_return_Comp_height_of_Type_Integer_StockFile()
            throws NoSuchFieldException, IllegalAccessException {
        Field f = stockFile.getClass().getDeclaredField("mCompHeight");
        f.setAccessible(true);
        f.set(stockFile, 100);
        Assert.assertEquals(100, stockFile.getCompHeight().intValue());
    }

    @Test(groups = { "Setters" })
    void setCompHeight_should_set_Comp_height_of_Type_Integer_StockFile()
            throws NoSuchFieldException, IllegalAccessException {
        stockFile.setCompHeight(1000);
        Field f = stockFile.getClass().getDeclaredField("mCompHeight");
        f.setAccessible(true);
        Assert.assertEquals(1000, f.get(stockFile));
    }

    @Test(groups = { "Getters" })
    void getNbDownloads_should_return_total_downloads_for_media_of_Type_Integer_StockFile()
            throws NoSuchFieldException, IllegalAccessException {
        Field f = stockFile.getClass().getDeclaredField("mNbDownloads");
        f.setAccessible(true);
        f.set(stockFile, 100);
        Assert.assertEquals(100, stockFile.getNbDownloads().intValue());
    }

    @Test(groups = { "Setters" })
    void setNbDownloads_should_set_total_downloads_for_media_of_Type_Integer_StockFile()
            throws NoSuchFieldException, IllegalAccessException {
        stockFile.setNbDownloads(1000);
        Field f = stockFile.getClass().getDeclaredField("mNbDownloads");
        f.setAccessible(true);
        Assert.assertEquals(1000, f.get(stockFile));
    }

    @Test(groups = { "Getters" })
    void getAssetTypeId_should_return_type_of_media_of_Type_AssetType_StockFile()
            throws NoSuchFieldException, IllegalAccessException {
        Field f = stockFile.getClass().getDeclaredField("mAssetTypeId");
        f.setAccessible(true);
        f.set(stockFile, AssetType.PHOTOS);
        Assert.assertEquals(AssetType.PHOTOS, stockFile.getAssetTypeId());
    }

    @Test(groups = { "Setters" })
    void setAssetTypeId_should_set_type_of_media_of_Type_AssetType_StockFile()
            throws NoSuchFieldException, IllegalAccessException {
        stockFile.setAssetTypeId(AssetType.PHOTOS);
        Field f = stockFile.getClass().getDeclaredField("mAssetTypeId");
        f.setAccessible(true);
        Assert.assertEquals(AssetType.PHOTOS, f.get(stockFile));
    }

    @Test(groups = { "Setters" })
    void setNbViews_should_set_total_number_of_views_of_media_of_Type_Integer_StockFile()
            throws NoSuchFieldException, IllegalAccessException {
        stockFile.setNbViews(1000);
        Field f = stockFile.getClass().getDeclaredField("mNbViews");
        f.setAccessible(true);
        Assert.assertEquals(1000, f.get(stockFile));
    }

    @Test(groups = { "Getters" })
    void getNbViews_should_return_total_number_of_views_of_media_of_Type_Integer_StockFile()
            throws NoSuchFieldException, IllegalAccessException {
        Field f = stockFile.getClass().getDeclaredField("mNbViews");
        f.setAccessible(true);
        f.set(stockFile, 100);
        Assert.assertEquals(100, stockFile.getNbViews().intValue());
    }

    @Test(groups = { "Getters" })
    void getDuration_should_return_duration_of_video_in_ms_of_Type_Integer_StockFile()
            throws NoSuchFieldException, IllegalAccessException {
        Field f = stockFile.getClass().getDeclaredField("mDuration");
        f.setAccessible(true);
        f.set(stockFile, 100);
        Assert.assertEquals(100, stockFile.getDuration().intValue());
    }

    @Test(groups = { "Setters" })
    void setDuration_should_set_duration_of_video_in_ms_of_Type_Integer_StockFile()
            throws NoSuchFieldException, IllegalAccessException {
        stockFile.setDuration(1000);
        Field f = stockFile.getClass().getDeclaredField("mDuration");
        f.setAccessible(true);
        Assert.assertEquals(1000, f.get(stockFile));
    }

    @Test(groups = { "Getters" })
    void getTemplateTypeId_should_return_id_of_template_of_Type_Integer_StockFile()
            throws NoSuchFieldException, IllegalAccessException {
        Field f = stockFile.getClass().getDeclaredField("mTemplateTypeId");
        f.setAccessible(true);
        f.set(stockFile, AssetTemplatesType.PSDT);
        Assert.assertEquals(AssetTemplatesType.PSDT, stockFile.getTemplateTypeId());
    }

    @Test(groups = { "Setters" })
    void setTemplateTypeId_should_set_id_of_template_of_Type_Integer_StockFile()
            throws NoSuchFieldException, IllegalAccessException {
        stockFile.setTemplateTypeId(AssetTemplatesType.PSDT);
        Field f = stockFile.getClass().getDeclaredField("mTemplateTypeId");
        f.setAccessible(true);
        Assert.assertEquals(AssetTemplatesType.PSDT, f.get(stockFile));
    }

    @Test(groups = { "Getters" })
    void getSizeBytes_should_return_size_of_template_inBytes_of_Type_Integer_StockFile()
            throws NoSuchFieldException, IllegalAccessException {
        Field f = stockFile.getClass().getDeclaredField("mSizeBytes");
        f.setAccessible(true);
        f.set(stockFile, 100);
        Assert.assertEquals(100, stockFile.getSizeBytes().intValue());
    }

    @Test(groups = { "Setters" })
    void setSizeBytes_should_set_size_of_template_inBytes_of_Type_Integer_StockFile()
            throws NoSuchFieldException, IllegalAccessException {
        stockFile.setSizeBytes(1000);
        Field f = stockFile.getClass().getDeclaredField("mSizeBytes");
        f.setAccessible(true);
        Assert.assertEquals(1000, f.get(stockFile));
    }

    @Test(groups = { "Getters" })
    void getPremiumLevelId_should_return_premium_level_of_Type_Integer_StockFile()
            throws NoSuchFieldException, IllegalAccessException {
        Field f = stockFile.getClass().getDeclaredField("mPremiumLevelId");
        f.setAccessible(true);
        f.set(stockFile, AssetPremiumLevel.CORE);
        Assert.assertEquals(AssetPremiumLevel.CORE, stockFile.getPremiumLevelId());
    }

    @Test(groups = { "Setters" })
    void setPremiumLevelId_should_set_premium_level_of_Type_Integer_StockFile()
            throws NoSuchFieldException, IllegalAccessException {
        stockFile.setPremiumLevelId(AssetPremiumLevel.FREE);
        Field f = stockFile.getClass().getDeclaredField("mPremiumLevelId");
        f.setAccessible(true);
        Assert.assertEquals(AssetPremiumLevel.FREE, f.get(stockFile));
    }

    @Test(groups = { "Getters" })
    void getVideoPreviewHeight_should_return_height_of_the_regular_video_preview_in_pixels_of_Type_Integer_StockFile()
            throws NoSuchFieldException, IllegalAccessException {
        Field f = stockFile.getClass().getDeclaredField("mVideoPreviewHeight");
        f.setAccessible(true);
        f.set(stockFile, 100);
        Assert.assertEquals(100, stockFile.getVideoPreviewHeight().intValue());
    }

    @Test(groups = { "Setters" })
    void setVideoPreviewHeight_should_set_height_of_the_regular_video_preview_in_pixels_of_Type_Integer_StockFile()
            throws NoSuchFieldException, IllegalAccessException {
        stockFile.setVideoPreviewHeight(1000);
        Field f = stockFile.getClass().getDeclaredField("mVideoPreviewHeight");
        f.setAccessible(true);
        Assert.assertEquals(1000, f.get(stockFile));
    }

    @Test(groups = { "Getters" })
    void getVideoPreviewWidth_should_return_width_of_the_regular_video_preview_in_pixels_of_Type_Integer_StockFile()
            throws NoSuchFieldException, IllegalAccessException {
        Field f = stockFile.getClass().getDeclaredField("mVideoPreviewWidth");
        f.setAccessible(true);
        f.set(stockFile, 100);
        Assert.assertEquals(100, stockFile.getVideoPreviewWidth().intValue());
    }

    @Test(groups = { "Setters" })
    void setVideoPreviewWidth_should_set_width_of_the_regular_video_preview_in_pixels_of_Type_Integer_StockFile()
            throws NoSuchFieldException, IllegalAccessException {
        stockFile.setVideoPreviewWidth(1000);
        Field f = stockFile.getClass().getDeclaredField("mVideoPreviewWidth");
        f.setAccessible(true);
        Assert.assertEquals(1000, f.get(stockFile));
    }

    @Test(groups = { "Getters" })
    void getVideoPreviewContentLength_should_return_fileSize_of_the_regular_video_preview_in_bytes_of_Type_Integer_StockFile()
            throws NoSuchFieldException, IllegalAccessException {
        Field f = stockFile.getClass().getDeclaredField(
                "mVideoPreviewContentLength");
        f.setAccessible(true);
        f.set(stockFile, 100);
        Assert.assertEquals(100, stockFile.getVideoPreviewContentLength()
                .intValue());
    }

    @Test(groups = { "Setters" })
    void setVideoPreviewContentLength_should_set_fileSize_of_the_regular_video_preview_in_bytes_of_Type_Integer_StockFile()
            throws NoSuchFieldException, IllegalAccessException {
        stockFile.setVideoPreviewContentLength(1000);
        Field f = stockFile.getClass().getDeclaredField(
                "mVideoPreviewContentLength");
        f.setAccessible(true);
        Assert.assertEquals(1000, f.get(stockFile));
    }

    @Test(groups = { "Getters" })
    void getVideoSmallPreviewHeight_should_return_height_of_the_small_video_preview_in_pixels_of_Type_Integer_StockFile()
            throws NoSuchFieldException, IllegalAccessException {
        Field f = stockFile.getClass().getDeclaredField(
                "mVideoSmallPreviewHeight");
        f.setAccessible(true);
        f.set(stockFile, 100);
        Assert.assertEquals(100, stockFile.getVideoSmallPreviewHeight()
                .intValue());
    }

    @Test(groups = { "Setters" })
    void setVideoSmallPreviewHeight_should_set_height_of_the_small_video_preview_in_pixels_of_Type_Integer_StockFile()
            throws NoSuchFieldException, IllegalAccessException {
        stockFile.setVideoSmallPreviewHeight(1000);
        Field f = stockFile.getClass().getDeclaredField(
                "mVideoSmallPreviewHeight");
        f.setAccessible(true);
        Assert.assertEquals(1000, f.get(stockFile));
    }

    @Test(groups = { "Getters" })
    void getVideoSmallPreviewWidth_should_return_Width_of_the_small_video_preview_in_pixels_of_Type_Integer_StockFile()
            throws NoSuchFieldException, IllegalAccessException {
        Field f = stockFile.getClass().getDeclaredField(
                "mVideoSmallPreviewWidth");
        f.setAccessible(true);
        f.set(stockFile, 100);
        Assert.assertEquals(100, stockFile.getVideoSmallPreviewWidth()
                .intValue());
    }

    @Test(groups = { "Setters" })
    void setVideoSmallPreviewWidth_should_set_Width_of_the_small_video_preview_in_pixels_of_Type_Integer_StockFile()
            throws NoSuchFieldException, IllegalAccessException {
        stockFile.setVideoSmallPreviewWidth(1000);
        Field f = stockFile.getClass().getDeclaredField(
                "mVideoSmallPreviewWidth");
        f.setAccessible(true);
        Assert.assertEquals(1000, f.get(stockFile));
    }

    @Test(groups = { "Getters" })
    void getVideoSmallPreviewContentLength_should_return_fileSize_of_the_small_video_preview_in_bytes_of_Type_Integer_StockFile()
            throws NoSuchFieldException, IllegalAccessException {
        Field f = stockFile.getClass().getDeclaredField(
                "mVideoSmallPreviewContentLength");
        f.setAccessible(true);
        f.set(stockFile, 100);
        Assert.assertEquals(100, stockFile.getVideoSmallPreviewContentLength()
                .intValue());
    }

    @Test(groups = { "Setters" })
    void setVideoPreviewContentLength_should_set_fileSize_of_the_small_video_preview_in_bytes_of_Type_Integer_StockFile()
            throws NoSuchFieldException, IllegalAccessException {
        stockFile.setVideoSmallPreviewContentLength(1000);
        Field f = stockFile.getClass().getDeclaredField(
                "mVideoSmallPreviewContentLength");
        f.setAccessible(true);
        Assert.assertEquals(1000, f.get(stockFile));
    }

    @Test(groups = { "Getters" })
    void getTitle_should_return_media_title_ofType_String_StockFile()
            throws NoSuchFieldException, IllegalAccessException {
        Field f = stockFile.getClass().getDeclaredField("mTitle");
        f.setAccessible(true);
        f.set(stockFile, "SomeText");
        Assert.assertTrue(stockFile.getTitle().equals("SomeText"));
    }

    @Test(groups = { "Setters" })
    void setTitle_should_set_media_title_ofType_String_StockFile()
            throws NoSuchFieldException, IllegalAccessException {
        stockFile.setTitle("SomeText");
        Field f = stockFile.getClass().getDeclaredField("mTitle");
        f.setAccessible(true);
        Assert.assertTrue(f.get(stockFile).equals("SomeText"));
    }

    @Test(groups = { "Getters" })
    void getCreatorName_should_return_media_CreatorName_ofType_String_StockFile()
            throws NoSuchFieldException, IllegalAccessException {
        Field f = stockFile.getClass().getDeclaredField("mCreatorName");
        f.setAccessible(true);
        f.set(stockFile, "SomeText");
        Assert.assertTrue(stockFile.getCreatorName().equals("SomeText"));
    }

    @Test(groups = { "Setters" })
    void setCreatorName_should_set_media_CreatorName_ofType_String_StockFile()
            throws NoSuchFieldException, IllegalAccessException {
        stockFile.setCreatorName("SomeText");
        Field f = stockFile.getClass().getDeclaredField("mCreatorName");
        f.setAccessible(true);
        Assert.assertTrue(f.get(stockFile).equals("SomeText"));
    }

    @Test(groups = { "Getters" })
    void getThumbnailUrl_should_return_media_ThumbnailUrl_ofType_String_StockFile()
            throws NoSuchFieldException, IllegalAccessException {
        Field f = stockFile.getClass().getDeclaredField("mThumbnailUrl");
        f.setAccessible(true);
        f.set(stockFile, "SomeText");
        Assert.assertTrue(stockFile.getThumbnailUrl().equals("SomeText"));
    }

    @Test(groups = { "Setters" })
    void setThumbnailUrl_should_set_media_ThumbnailUrl_ofType_String_StockFile()
            throws NoSuchFieldException, IllegalAccessException {
        stockFile.setThumbnailUrl("SomeText");
        Field f = stockFile.getClass().getDeclaredField("mThumbnailUrl");
        f.setAccessible(true);
        Assert.assertTrue(f.get(stockFile).equals("SomeText"));
    }

    @Test(groups = { "Getters" })
    void getThumbnailHtmlTag_should_return_media_ThumbnailHtmlTag_ofType_String_StockFile()
            throws NoSuchFieldException, IllegalAccessException {
        Field f = stockFile.getClass().getDeclaredField("mThumbnailHtmlTag");
        f.setAccessible(true);
        f.set(stockFile, "SomeText");
        Assert.assertTrue(stockFile.getThumbnailHtmlTag().equals("SomeText"));
    }

    @Test(groups = { "Setters" })
    void setThumbnailHtmlTag_should_set_media_ThumbnailHtmlTag_ofType_String_StockFile()
            throws NoSuchFieldException, IllegalAccessException {
        stockFile.setThumbnailHtmlTag("SomeText");
        Field f = stockFile.getClass().getDeclaredField("mThumbnailHtmlTag");
        f.setAccessible(true);
        Assert.assertTrue(f.get(stockFile).equals("SomeText"));
    }

    @Test(groups = { "Getters" })
    void getThumbnail110Url_should_return_media_Thumbnail110Url_ofType_String_StockFile()
            throws NoSuchFieldException, IllegalAccessException {
        Field f = stockFile.getClass().getDeclaredField("mThumbnail110Url");
        f.setAccessible(true);
        f.set(stockFile, "SomeText");
        Assert.assertTrue(stockFile.getThumbnail110Url().equals("SomeText"));
    }

    @Test(groups = { "Setters" })
    void setThumbnail110Url_should_set_media_Thumbnail110Url_ofType_String_StockFile()
            throws NoSuchFieldException, IllegalAccessException {
        stockFile.setThumbnail110Url("SomeText");
        Field f = stockFile.getClass().getDeclaredField("mThumbnail110Url");
        f.setAccessible(true);
        Assert.assertTrue(f.get(stockFile).equals("SomeText"));
    }

    @Test(groups = { "Getters" })
    void getThumbnail160Url_should_return_media_Url_for_160px_thumbnaill_ofType_String_StockFile()
            throws NoSuchFieldException, IllegalAccessException {
        Field f = stockFile.getClass().getDeclaredField("mThumbnail160Url");
        f.setAccessible(true);
        f.set(stockFile, "SomeText");
        Assert.assertTrue(stockFile.getThumbnail160Url().equals("SomeText"));
    }

    @Test(groups = { "Setters" })
    void setThumbnail160Url_should_set_media_Url_for_160px_thumbnaill_ofType_String_StockFile()
            throws NoSuchFieldException, IllegalAccessException {
        stockFile.setThumbnail160Url("SomeText");
        Field f = stockFile.getClass().getDeclaredField("mThumbnail160Url");
        f.setAccessible(true);
        Assert.assertTrue(f.get(stockFile).equals("SomeText"));
    }

    @Test(groups = { "Getters" })
    void getThumbnail220Url_should_return_media_Url_for_220px_thumbnaill_ofType_String_StockFile()
            throws NoSuchFieldException, IllegalAccessException {
        Field f = stockFile.getClass().getDeclaredField("mThumbnail220Url");
        f.setAccessible(true);
        f.set(stockFile, "SomeText");
        Assert.assertTrue(stockFile.getThumbnail220Url().equals("SomeText"));
    }

    @Test(groups = { "Setters" })
    void setThumbnail220Url_should_set_media_Url_for_220px_thumbnaill_ofType_String_StockFile()
            throws NoSuchFieldException, IllegalAccessException {
        stockFile.setThumbnail220Url("SomeText");
        Field f = stockFile.getClass().getDeclaredField("mThumbnail220Url");
        f.setAccessible(true);
        Assert.assertTrue(f.get(stockFile).equals("SomeText"));
    }

    @Test(groups = { "Getters" })
    void getThumbnail240Url_should_return_media_Url_for_240px_thumbnaill_ofType_String_StockFile()
            throws NoSuchFieldException, IllegalAccessException {
        Field f = stockFile.getClass().getDeclaredField("mThumbnail240Url");
        f.setAccessible(true);
        f.set(stockFile, "SomeText");
        Assert.assertTrue(stockFile.getThumbnail240Url().equals("SomeText"));
    }

    @Test(groups = { "Setters" })
    void setThumbnail240Url_should_set_media_Url_for_240px_thumbnaill_ofType_String_StockFile()
            throws NoSuchFieldException, IllegalAccessException {
        stockFile.setThumbnail240Url("SomeText");
        Field f = stockFile.getClass().getDeclaredField("mThumbnail240Url");
        f.setAccessible(true);
        Assert.assertTrue(f.get(stockFile).equals("SomeText"));
    }

    @Test(groups = { "Getters" })
    void getThumbnail500Url_should_return_media_Url_for_500px_thumbnaill_ofType_String_StockFile()
            throws NoSuchFieldException, IllegalAccessException {
        Field f = stockFile.getClass().getDeclaredField("mThumbnail500Url");
        f.setAccessible(true);
        f.set(stockFile, "SomeText");
        Assert.assertTrue(stockFile.getThumbnail500Url().equals("SomeText"));
    }

    @Test(groups = { "Setters" })
    void setThumbnail500Url_should_set_media_Url_for_500px_thumbnaill_ofType_String_StockFile()
            throws NoSuchFieldException, IllegalAccessException {
        stockFile.setThumbnail500Url("SomeText");
        Field f = stockFile.getClass().getDeclaredField("mThumbnail500Url");
        f.setAccessible(true);
        Assert.assertTrue(f.get(stockFile).equals("SomeText"));
    }

    @Test(groups = { "Getters" })
    void getThumbnail1000Url_should_return_media_Url_for_1000px_thumbnaill_ofType_String_StockFile()
            throws NoSuchFieldException, IllegalAccessException {
        Field f = stockFile.getClass().getDeclaredField("mThumbnail1000Url");
        f.setAccessible(true);
        f.set(stockFile, "SomeText");
        Assert.assertTrue(stockFile.getThumbnail1000Url().equals("SomeText"));
    }

    @Test(groups = { "Setters" })
    void setThumbnail1000Url_should_set_media_Url_for_1000px_thumbnaill_ofType_String_StockFile()
            throws NoSuchFieldException, IllegalAccessException {
        stockFile.setThumbnail1000Url("SomeText");
        Field f = stockFile.getClass().getDeclaredField("mThumbnail1000Url");
        f.setAccessible(true);
        Assert.assertTrue(f.get(stockFile).equals("SomeText"));
    }

    @Test(groups = { "Getters" })
    void getIsLicensed_should_return_license_state_of_user_ofType_AssetLicenseState_StockFile()
            throws NoSuchFieldException, IllegalAccessException {
        Field f = stockFile.getClass().getDeclaredField("mIsLicensed");
        f.setAccessible(true);
        f.set(stockFile, AssetLicenseState.STANDARD);
        Assert.assertTrue(stockFile.getIsLicensed().equals(AssetLicenseState.STANDARD));
    }

    @Test(groups = { "Setters" })
    void setIsLicensed_should_set_license_state_of_user_ofType_AssetLicenseState_StockFile()
            throws NoSuchFieldException, IllegalAccessException {
        stockFile.setIsLicensed(AssetLicenseState.EXTENDED);
        Field f = stockFile.getClass().getDeclaredField("mIsLicensed");
        f.setAccessible(true);
        Assert.assertTrue(f.get(stockFile).equals(AssetLicenseState.EXTENDED));
    }

    @Test(groups = { "Getters" })
    void getCompUrl_should_return_Comp_Url_ofType_String_StockFile()
            throws NoSuchFieldException, IllegalAccessException {
        Field f = stockFile.getClass().getDeclaredField("mCompUrl");
        f.setAccessible(true);
        f.set(stockFile, "SomeText");
        Assert.assertTrue(stockFile.getCompUrl().equals("SomeText"));
    }

    @Test(groups = { "Setters" })
    void setCompUrl_should_set_Comp_Url_ofType_String_StockFile()
            throws NoSuchFieldException, IllegalAccessException {
        stockFile.setCompUrl("SomeText");
        Field f = stockFile.getClass().getDeclaredField("mCompUrl");
        f.setAccessible(true);
        Assert.assertTrue(f.get(stockFile).equals("SomeText"));
    }

    @Test(groups = { "Getters" })
    void getHasReleases_should_return_media_HasReleases_ofType_Boolean_StockFile()
            throws NoSuchFieldException, IllegalAccessException {
        Field f = stockFile.getClass().getDeclaredField("mHasReleases");
        f.setAccessible(true);
        f.set(stockFile, true);
        Assert.assertTrue(stockFile.getHasReleases().equals(true));
    }

    @Test(groups = { "Setters" })
    void setHasReleases_should_set_media_HasReleases_ofType_Boolean_StockFile()
            throws NoSuchFieldException, IllegalAccessException {
        stockFile.setHasReleases(false);
        Field f = stockFile.getClass().getDeclaredField("mHasReleases");
        f.setAccessible(true);
        Assert.assertTrue(f.get(stockFile).equals(false));
    }

    @Test(groups = { "Getters" })
    void getVectorType_should_return_svgOrAI_if_file_is_vector_ofType_String_StockFile()
            throws NoSuchFieldException, IllegalAccessException {
        Field f = stockFile.getClass().getDeclaredField("mVectorType");
        f.setAccessible(true);
        f.set(stockFile, "SomeText");
        Assert.assertTrue(stockFile.getVectorType().equals("SomeText"));
    }

    @Test(groups = { "Setters" })
    void setVectorType_should_set_svgOrAI_if_file_is_vector_ofType_ofType_String_StockFile()
            throws NoSuchFieldException, IllegalAccessException {
        stockFile.setVectorType("SomeText");
        Field f = stockFile.getClass().getDeclaredField("mVectorType");
        f.setAccessible(true);
        Assert.assertTrue(f.get(stockFile).equals("SomeText"));
    }

    @Test(groups = { "Getters" })
    void getContentType_should_return_ContentType_of_file_ofType_String_StockFile()
            throws NoSuchFieldException, IllegalAccessException {
        Field f = stockFile.getClass().getDeclaredField("mContentType");
        f.setAccessible(true);
        f.set(stockFile, "SomeText");
        Assert.assertTrue(stockFile.getContentType().equals("SomeText"));
    }

    @Test(groups = { "Setters" })
    void setContentType_should_set_ContentType_of_file_ofType_String_StockFile()
            throws NoSuchFieldException, IllegalAccessException {
        stockFile.setContentType("SomeText");
        Field f = stockFile.getClass().getDeclaredField("mContentType");
        f.setAccessible(true);
        Assert.assertTrue(f.get(stockFile).equals("SomeText"));
    }

    @Test(groups = { "Getters" })
    void getStockId_should_return_StockId_ofType_String_StockFile()
            throws NoSuchFieldException, IllegalAccessException {
        Field f = stockFile.getClass().getDeclaredField("mStockId");
        f.setAccessible(true);
        f.set(stockFile, "SomeText");
        Assert.assertTrue(stockFile.getStockId().equals("SomeText"));
    }

    @Test(groups = { "Setters" })
    void setStockId_should_set_StockId_ofType_String_StockFile()
            throws NoSuchFieldException, IllegalAccessException {
        stockFile.setStockId("SomeText");
        Field f = stockFile.getClass().getDeclaredField("mStockId");
        f.setAccessible(true);
        Assert.assertTrue(f.get(stockFile).equals("SomeText"));
    }

    @Test(groups = { "Getters" })
    void getDetailsUrl_should_return_Url_to_stockDetails_ofType_String_StockFile()
            throws NoSuchFieldException, IllegalAccessException {
        Field f = stockFile.getClass().getDeclaredField("mDetailsUrl");
        f.setAccessible(true);
        f.set(stockFile, "SomeText");
        Assert.assertTrue(stockFile.getDetailsUrl().equals("SomeText"));
    }

    @Test(groups = { "Setters" })
    void setDetailsUrl_should_set_Url_to_stockDetails_ofType_String_StockFile()
            throws NoSuchFieldException, IllegalAccessException {
        stockFile.setDetailsUrl("SomeText");
        Field f = stockFile.getClass().getDeclaredField("mDetailsUrl");
        f.setAccessible(true);
        Assert.assertTrue(f.get(stockFile).equals("SomeText"));
    }

    @Test(groups = { "Getters" })
    void getMarketingText_should_return_marketing_text_for_template_ofType_String_StockFile()
            throws NoSuchFieldException, IllegalAccessException {
        Field f = stockFile.getClass().getDeclaredField("mMarketingText");
        f.setAccessible(true);
        f.set(stockFile, "SomeText");
        Assert.assertTrue(stockFile.getMarketingText().equals("SomeText"));
    }

    @Test(groups = { "Setters" })
    void setMarketingText_should_set_marketing_text_for_template_ofType_String_StockFile()
            throws NoSuchFieldException, IllegalAccessException {
        stockFile.setMarketingText("SomeText");
        Field f = stockFile.getClass().getDeclaredField("mMarketingText");
        f.setAccessible(true);
        Assert.assertTrue(f.get(stockFile).equals("SomeText"));
    }

    @Test(groups = { "Getters" })
    void getDescription_should_return_description_text_for_template_ofType_String_StockFile()
            throws NoSuchFieldException, IllegalAccessException {
        Field f = stockFile.getClass().getDeclaredField("mDescription");
        f.setAccessible(true);
        f.set(stockFile, "SomeText");
        Assert.assertTrue(stockFile.getDescription().equals("SomeText"));
    }

    @Test(groups = { "Setters" })
    void setDescription_should_set_description_text_for_template_ofType_String_StockFile()
            throws NoSuchFieldException, IllegalAccessException {
        stockFile.setDescription("SomeText");
        Field f = stockFile.getClass().getDeclaredField("mDescription");
        f.setAccessible(true);
        Assert.assertTrue(f.get(stockFile).equals("SomeText"));
    }

    @Test(groups = { "Getters" })
    void getVideoPreviewUrl_should_return_URL_for_the_regular_preview_ofType_String_StockFile()
            throws NoSuchFieldException, IllegalAccessException {
        Field f = stockFile.getClass().getDeclaredField("mVideoPreviewUrl");
        f.setAccessible(true);
        f.set(stockFile, "SomeText");
        Assert.assertTrue(stockFile.getVideoPreviewUrl().equals("SomeText"));
    }

    @Test(groups = { "Setters" })
    void setVideoPreviewUrl_should_set_URL_for_the_regular_preview_ofType_String_StockFile()
            throws NoSuchFieldException, IllegalAccessException {
        stockFile.setVideoPreviewUrl("SomeText");
        Field f = stockFile.getClass().getDeclaredField("mVideoPreviewUrl");
        f.setAccessible(true);
        Assert.assertTrue(f.get(stockFile).equals("SomeText"));
    }

    @Test(groups = { "Getters" })
    void getVideoPreviewContentType_should_return_ContentType_for_the_regular_preview_ofType_String_StockFile()
            throws NoSuchFieldException, IllegalAccessException {
        Field f = stockFile.getClass().getDeclaredField(
                "mVideoPreviewContentType");
        f.setAccessible(true);
        f.set(stockFile, "SomeText");
        Assert.assertTrue(stockFile.getVideoPreviewContentType().equals(
                "SomeText"));
    }

    @Test(groups = { "Setters" })
    void setVideoPreviewContentType_should_set_ContentType_for_the_regular_preview_ofType_String_StockFile()
            throws NoSuchFieldException, IllegalAccessException {
        stockFile.setVideoPreviewContentType("SomeText");
        Field f = stockFile.getClass().getDeclaredField(
                "mVideoPreviewContentType");
        f.setAccessible(true);
        Assert.assertTrue(f.get(stockFile).equals("SomeText"));
    }

    @Test(groups = { "Getters" })
    void getVideoSmallPreviewUrl_should_return_URL_for_the_small_preview_ofType_String_StockFile()
            throws NoSuchFieldException, IllegalAccessException {
        Field f = stockFile.getClass()
                .getDeclaredField("mVideoSmallPreviewUrl");
        f.setAccessible(true);
        f.set(stockFile, "SomeText");
        Assert.assertTrue(stockFile.getVideoSmallPreviewUrl()
                .equals("SomeText"));
    }

    @Test(groups = { "Setters" })
    void setVideoSmallPreviewUrl_should_set_URL_for_the_small_preview_ofType_String_StockFile()
            throws NoSuchFieldException, IllegalAccessException {
        stockFile.setVideoSmallPreviewUrl("SomeText");
        Field f = stockFile.getClass()
                .getDeclaredField("mVideoSmallPreviewUrl");
        f.setAccessible(true);
        Assert.assertTrue(f.get(stockFile).equals("SomeText"));
    }

    @Test(groups = { "Getters" })
    void getVideoSmallPreviewContentType_should_return_ContentType_for_the_small_preview_ofType_String_StockFile()
            throws NoSuchFieldException, IllegalAccessException {
        Field f = stockFile.getClass().getDeclaredField(
                "mVideoSmallPreviewContentType");
        f.setAccessible(true);
        f.set(stockFile, "SomeText");
        Assert.assertTrue(stockFile.getVideoSmallPreviewContentType().equals(
                "SomeText"));
    }

    @Test(groups = { "Setters" })
    void setVideoSmallPreviewContentType_should_set_ContentType_for_the_small_preview_ofType_String_StockFile()
            throws NoSuchFieldException, IllegalAccessException {
        stockFile.setVideoSmallPreviewContentType("SomeText");
        Field f = stockFile.getClass().getDeclaredField(
                "mVideoSmallPreviewContentType");
        f.setAccessible(true);
        Assert.assertTrue(f.get(stockFile).equals("SomeText"));
    }

    @Test(groups = { "Getters" })
    void getIsPremium_returns_true_for_premium_assests()
            throws NoSuchFieldException, IllegalAccessException {
        Field f = stockFile.getClass().getDeclaredField("mIsPremium");
        f.setAccessible(true);
        f.set(stockFile, true);
        Assert.assertTrue(stockFile.getIsPremium());
    }

    @Test(groups = { "Setters" })
    void setIsPremium_set_true_for_premium_assests()
            throws NoSuchFieldException, IllegalAccessException {
        stockFile.setIsPremium(true);
        Field f = stockFile.getClass().getDeclaredField("mIsPremium");
        f.setAccessible(true);
        Assert.assertEquals(true, f.get(stockFile));
    }

    @Test(groups = { "Getters" })
    void getCreationDate_returns_media_creation_date()
            throws NoSuchFieldException, IllegalAccessException {
        Field f = stockFile.getClass().getDeclaredField("mCreationDate");
        f.setAccessible(true);
        Date date = new Date();
        f.set(stockFile, date);
        Assert.assertTrue(stockFile.getCreationDate().toString()
                .equals(date.toString()));
    }

    @Test(groups = { "Setters" }, expectedExceptions = { StockException.class }, expectedExceptionsMessageRegExp = "Could not parse date string")
    void setCreationDate_should_throw_exception_invalid_date_string()
            throws StockException {
        String dateString = "test_date";
        stockFile.setCreationDate(dateString);
    }

    @Test(groups = { "Setters" })
    void setCreationDate_set_media_creation_date() throws NoSuchFieldException,
            IllegalAccessException {
        String dateString = "2017-05-18 05:57:21.246303";
        SimpleDateFormat format = new SimpleDateFormat(
                "yyyy-MM-dd hh:mm:ss.SSS");
        Date date = null;
        try {
            date = format.parse(dateString);
            stockFile.setCreationDate(dateString);
        } catch (Exception e) {
            Assert.fail("Didn't expect the Exception!");
        }
        Field f = stockFile.getClass().getDeclaredField("mCreationDate");
        f.setAccessible(true);
        Date newDate = (Date) f.get(stockFile);
        Assert.assertTrue(newDate.equals(date));
    }

    @Test(groups = { "Getters" })
    void getCategory_returns_the_category_of_Media_of_type_StockFileCategory()
            throws NoSuchFieldException, IllegalAccessException {
        StockFileCategory category = new StockFileCategory();
        category.setId(1);
        category.setName("Text");
        Field f = stockFile.getClass().getDeclaredField("mCategory");
        f.setAccessible(true);
        f.set(stockFile, category);
        Assert.assertEquals(category.getId(), stockFile.getCategory().getId());
        Assert.assertTrue(category.getName().equals(
                stockFile.getCategory().getName()));

    }

    @Test(groups = { "Setters" })
    void setCategory_sets_the_category_of_Media_of_type_StockFileCategory()
            throws NoSuchFieldException, IllegalAccessException,
            JsonProcessingException, IOException {
        stockFile.setCategory(null);
        ObjectMapper mapper = new ObjectMapper();
        ArrayNode node = mapper.createArrayNode();
        stockFile.setCategory(node);
        String jsonString = "{\"name\":\"Text\", \"id\":1}";
        JsonNode jsonNode = mapper.readTree(jsonString);
        stockFile.setCategory(jsonNode);
        Field f = stockFile.getClass().getDeclaredField("mCategory");
        f.setAccessible(true);
        StockFileCategory category = (StockFileCategory) f.get(stockFile);
        Assert.assertEquals(category.getId().intValue(), 1);
        Assert.assertTrue(category.getName().equals("Text"));

    }

    @Test(groups = { "Getters" })
    void getKeywords_returns_localised_list_of_keywords_from_file()
            throws NoSuchFieldException, IllegalAccessException {
        ArrayList<StockFileKeyword> list = new ArrayList<StockFileKeyword>();
        StockFileKeyword keyword = new StockFileKeyword();
        keyword.setName("Text");
        list.add(keyword);
        Field f = stockFile.getClass().getDeclaredField("mKeywords");
        f.setAccessible(true);
        f.set(stockFile, list);
        list.add(keyword);
        list = stockFile.getKeywords();
        if (list != null)
            Assert.assertTrue(list.get(0).getName().equals(keyword.getName()));
    }

    @SuppressWarnings("unchecked")
    @Test(groups = { "Setters" })
    void setKeywords_sets_localised_list_of_keywords_from_file()
            throws NoSuchFieldException, IllegalAccessException {
        ArrayList<StockFileKeyword> list = new ArrayList<StockFileKeyword>();
        StockFileKeyword keyword = new StockFileKeyword();
        keyword.setName("Text");
        list.add(keyword);
        stockFile.setKeywords(list);
        Field f = stockFile.getClass().getDeclaredField("mKeywords");
        f.setAccessible(true);
        list = (ArrayList<StockFileKeyword>) f.get(stockFile);
        // Assert.assertTrue(list.get(0).equals("Text"));

    }

    @Test(groups = { "Setters" })
    void setFrameRate_should_set_FrameRate_of_Video_Type_Double()
            throws NoSuchFieldException, IllegalAccessException {
        stockFile.setFrameRate(100.00);
        Field f = stockFile.getClass().getDeclaredField("mFrameRate");
        f.setAccessible(true);
        Assert.assertEquals(100.00, f.get(stockFile));
    }

    @Test(groups = { "Getters" })
    void getFrameRate_should_set_FrameRate_of_Video_Type_Double()
            throws NoSuchFieldException, IllegalAccessException {
        Field f = stockFile.getClass().getDeclaredField("mFrameRate");
        f.setAccessible(true);
        f.set(stockFile, 100.00);
        Assert.assertEquals(100.00, stockFile.getFrameRate().doubleValue());
    }

    @Test(groups = { "Setters" })
    void setComps_should_set_comp_version_of_Type_StockFileComps()
            throws NoSuchFieldException, IllegalAccessException {
        StockFileComps comp = new StockFileComps();
        StockFileCompProp prop = new StockFileCompProp();
        prop.setHeight(100);
        comp.setStandard(prop);
        stockFile.setComps(comp);
        Field f = stockFile.getClass().getDeclaredField("mComps");
        f.setAccessible(true);
        StockFileComps newComp = new StockFileComps();
        newComp = (StockFileComps) f.get(stockFile);
        Assert.assertTrue(newComp.getStandard().getHeight() == 100);
    }

    @Test(groups = { "Getters" })
    void getComps_should_return_comp_version_of_Type_StockFileComps()
            throws NoSuchFieldException, IllegalAccessException {
        Field f = stockFile.getClass().getDeclaredField("mComps");
        StockFileComps comp = new StockFileComps();
        StockFileCompProp prop = new StockFileCompProp();
        prop.setHeight(100);
        comp.setStandard(prop);
        f.setAccessible(true);
        f.set(stockFile, comp);
        Assert.assertEquals(100, stockFile.getComps().getStandard().getHeight()
                .intValue());
    }

    @SuppressWarnings("unchecked")
    @Test(groups = { "Setters" })
    void setTemplateCategoryIds_should_set_array_of_template_Ids()
            throws NoSuchFieldException, IllegalAccessException {
        ArrayList<AssetTemplateCategory> list = new ArrayList<AssetTemplateCategory>();
        list.add(AssetTemplateCategory.ART);
        stockFile.setTemplateCategoryIds(list);
        Field f = stockFile.getClass().getDeclaredField("mTemplateCategoryIds");
        f.setAccessible(true);
        list = (ArrayList<AssetTemplateCategory>) f.get(stockFile);
        Assert.assertEquals(list.get(0),AssetTemplateCategory.ART);
    }

    @Test(groups = { "Getters" })
    void getTemplateCategoryIds_should_set_array_of_template_Ids()
            throws NoSuchFieldException, IllegalAccessException {
        Field f = stockFile.getClass().getDeclaredField("mTemplateCategoryIds");
        ArrayList<AssetTemplateCategory> list = new ArrayList<AssetTemplateCategory>();
        list.add(AssetTemplateCategory.ART);
        f.setAccessible(true);
        f.set(stockFile, list);
        Assert.assertEquals(AssetTemplateCategory.ART, stockFile.getTemplateCategoryIds().get(0));
    }

    @Test(groups = { "Setters" })
    void setLicenses_should_set_available_licenses_of_Type_StockLicenses()
            throws NoSuchFieldException, IllegalAccessException {
        StockFileLicenseProp standard = new StockFileLicenseProp();
        standard.setHeight(100);
        StockFileLicenses stockFileLicenses = new StockFileLicenses();
        stockFileLicenses.setStandard(standard);
        stockFile.setLicenses(stockFileLicenses);
        Field f = stockFile.getClass().getDeclaredField("mLicenses");
        f.setAccessible(true);
        StockFileLicenses license = new StockFileLicenses();
        license = (StockFileLicenses) f.get(stockFile);
        Assert.assertTrue(license.getStandard().getHeight() == 100);
    }

    @Test(groups = { "Getters" })
    void getLicenses_should_set_available_licenses_of_Type_StockLicenses()
            throws NoSuchFieldException, IllegalAccessException {
        StockFileLicenseProp standard = new StockFileLicenseProp();
        standard.setHeight(100);
        StockFileLicenses stockFileLicenses = new StockFileLicenses();
        stockFileLicenses.setStandard(standard);
        Field f = stockFile.getClass().getDeclaredField("mLicenses");
        f.setAccessible(true);
        f.set(stockFile, stockFileLicenses);
        Assert.assertEquals(100, stockFile.getLicenses().getStandard()
                .getHeight().intValue());
    }

}