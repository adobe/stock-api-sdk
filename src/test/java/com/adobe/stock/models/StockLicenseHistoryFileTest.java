/*******************************************************************************
 * Copyright 2017 Adobe Systems Incorporated. All rights reserved.
 * This file is licensed to you under the Apache License, Version 2.0
 * (the "License") you may not use this file except in compliance with the
 * License. You may obtain a copy of the License at
 * http://www.apache.org/licenses/LICENSE-2.0
 ******************************************************************************/
package com.adobe.stock.models;

import java.lang.reflect.Field;

import org.testng.Assert;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;

@Listeners({ com.adobe.stock.logger.TestCustomLogger.class,
    com.adobe.stock.logger.TestSuiteLogger.class })
@Test(suiteName = "StockLicenseHistoryFile")
public class StockLicenseHistoryFileTest {

    StockLicenseHistoryFile stocklicensehistoryfile;
    
    @BeforeSuite()
    void create_StockLicenseHistoryFile_instance_successfully() {
        stocklicensehistoryfile = new StockLicenseHistoryFile();
    }
    
    @Test(groups = { "Getters" })
    void getLicenseState_should_return_license_state_ofType_String_StockLicenseHistoryFile()
            throws NoSuchFieldException, IllegalAccessException {
        Field f = stocklicensehistoryfile.getClass().getDeclaredField("mLicenseState");
        f.setAccessible(true);
        f.set(stocklicensehistoryfile, "SomeText");
        Assert.assertTrue(stocklicensehistoryfile.getLicenseState().equals("SomeText"));
    }

    @Test(groups = { "Setters" })
    void setLicenseState_should_set_license_stateofType_String_StockLicenseHistoryFile()
            throws NoSuchFieldException, IllegalAccessException {
        stocklicensehistoryfile.setLicenseState("SomeText");
        Field f = stocklicensehistoryfile.getClass().getDeclaredField("mLicenseState");
        f.setAccessible(true);
        Assert.assertTrue(f.get(stocklicensehistoryfile).equals("SomeText"));
    }
    
    @Test(groups = { "Getters" })
    void getLicenseDate_should_return_license_date_ofType_String_StockLicenseHistoryFile()
            throws NoSuchFieldException, IllegalAccessException {
        Field f = stocklicensehistoryfile.getClass().getDeclaredField("mLicenseDate");
        f.setAccessible(true);
        f.set(stocklicensehistoryfile, "3/24/17 5:30 PM");
        Assert.assertTrue(stocklicensehistoryfile.getLicenseDate().equals("3/24/17 5:30 PM"));
    }

    @Test(groups = { "Setters" })
    void setLicenseDate_should_set_license_dateofType_String_StockLicenseHistoryFile()
            throws NoSuchFieldException, IllegalAccessException {
        stocklicensehistoryfile.setLicenseDate("3/24/17 5:30 PM");
        Field f = stocklicensehistoryfile.getClass().getDeclaredField("mLicenseDate");
        f.setAccessible(true);
        Assert.assertTrue(f.get(stocklicensehistoryfile).equals("3/24/17 5:30 PM"));
    }
    
    @Test(groups = { "Getters" })
    void getDownloadUrl_should_return_download_url_ofType_String_StockLicenseHistoryFile()
            throws NoSuchFieldException, IllegalAccessException {
        Field f = stocklicensehistoryfile.getClass().getDeclaredField("mDownloadUrl");
        f.setAccessible(true);
        f.set(stocklicensehistoryfile, "Some Text");
        Assert.assertTrue(stocklicensehistoryfile.getDownloadUrl().equals("Some Text"));
    }

    @Test(groups = { "Setters" })
    void setDownloadUrl_should_set_download_url_ofType_String_StockLicenseHistoryFile()
            throws NoSuchFieldException, IllegalAccessException {
        stocklicensehistoryfile.setDownloadUrl("Some Text");
        Field f = stocklicensehistoryfile.getClass().getDeclaredField("mDownloadUrl");
        f.setAccessible(true);
        Assert.assertTrue(f.get(stocklicensehistoryfile).equals("Some Text"));
    }
    
    @Test(groups = { "Getters" })
    void getVectorType_should_return_vector_type_ofType_String_StockLicenseHistoryFile()
            throws NoSuchFieldException, IllegalAccessException {
        Field f = stocklicensehistoryfile.getClass().getDeclaredField("mVectorType");
        f.setAccessible(true);
        f.set(stocklicensehistoryfile, "svg");
        Assert.assertTrue(stocklicensehistoryfile.getVectorType().equals("svg"));
    }

    @Test(groups = { "Setters" })
    void setVectorType_should_set_vector_type_ofType_String_StockLicenseHistoryFile()
            throws NoSuchFieldException, IllegalAccessException {
        stocklicensehistoryfile.setVectorType("svg");
        Field f = stocklicensehistoryfile.getClass().getDeclaredField("mVectorType");
        f.setAccessible(true);
        Assert.assertTrue(f.get(stocklicensehistoryfile).equals("svg"));
    }
    
    @Test(groups = { "Getters" })
    void getMediaTypeId_should_return_media_unique_ID_of_Type_Integer_StockLicenseHistoryFile()
            throws NoSuchFieldException, IllegalAccessException {
        Field f = stocklicensehistoryfile.getClass().getDeclaredField("mMediaTypeId");
        f.setAccessible(true);
        f.set(stocklicensehistoryfile, 1);
        Assert.assertEquals(1, stocklicensehistoryfile.getMediaTypeId().intValue());
    }

    @Test(groups = { "Setters" })
    void setMediaTypeId_should_set_media_unique_ID_of_Type_Integer_StockLicenseHistoryFile()
            throws NoSuchFieldException, IllegalAccessException {
        stocklicensehistoryfile.setMediaTypeId(1);
        Field f = stocklicensehistoryfile.getClass().getDeclaredField("mMediaTypeId");
        f.setAccessible(true);
        Assert.assertEquals(1, f.get(stocklicensehistoryfile));
    }
    
    @Test(groups = { "Getters" })
    void getId_should_return_media_unique_ID_of_Type_Integer_StockLicenseHistoryFile()
            throws NoSuchFieldException, IllegalAccessException {
        Field f = stocklicensehistoryfile.getClass().getDeclaredField("mId");
        f.setAccessible(true);
        f.set(stocklicensehistoryfile, 100);
        Assert.assertEquals(100, stocklicensehistoryfile.getId().intValue());
    }

    @Test(groups = { "Setters" })
    void setId_should_set_media_unique_ID_of_Type_Integer_StockLicenseHistoryFile()
            throws NoSuchFieldException, IllegalAccessException {
        stocklicensehistoryfile.setId(1000);
        Field f = stocklicensehistoryfile.getClass().getDeclaredField("mId");
        f.setAccessible(true);
        Assert.assertEquals(1000, f.get(stocklicensehistoryfile));
    }
    
    @Test(groups = { "Getters" })
    void getCreatorId_should_return_media_unique_creator_ID_of_Type_Integer_StockLicenseHistoryFile()
            throws NoSuchFieldException, IllegalAccessException {
        Field f = stocklicensehistoryfile.getClass().getDeclaredField("mCreatorId");
        f.setAccessible(true);
        f.set(stocklicensehistoryfile, 100);
        Assert.assertEquals(100, stocklicensehistoryfile.getCreatorId().intValue());
    }

    @Test(groups = { "Setters" })
    void setCreatorId_should_set_media_unique_creator_ID_of_Type_Integer_StockLicenseHistoryFile()
            throws NoSuchFieldException, IllegalAccessException {
        stocklicensehistoryfile.setCreatorId(1000);
        Field f = stocklicensehistoryfile.getClass().getDeclaredField("mCreatorId");
        f.setAccessible(true);
        Assert.assertEquals(1000, f.get(stocklicensehistoryfile));
    }
    
    @Test(groups = { "Getters" })
    void getThumbnailWidth_should_return_media_ThumbnailWidth_inPixels_of_Type_Integer_StockLicenseHistoryFile()
            throws NoSuchFieldException, IllegalAccessException {
        Field f = stocklicensehistoryfile.getClass().getDeclaredField("mThumbnailWidth");
        f.setAccessible(true);
        f.set(stocklicensehistoryfile, 100);
        Assert.assertEquals(100, stocklicensehistoryfile.getThumbnailWidth().intValue());
    }

    @Test(groups = { "Setters" })
    void setThumbnailWidth_should_set_media_ThumbnailWidth_inPixels_of_Type_Integer_StockLicenseHistoryFile()
            throws NoSuchFieldException, IllegalAccessException {
        stocklicensehistoryfile.setThumbnailWidth(1000);
        Field f = stocklicensehistoryfile.getClass().getDeclaredField("mThumbnailWidth");
        f.setAccessible(true);
        Assert.assertEquals(1000, f.get(stocklicensehistoryfile));
    }

    @Test(groups = { "Getters" })
    void getThumbnailHeight_should_return_media_ThumbnailHeight_inPixels_of_Type_Integer_StockLicenseHistoryFile()
            throws NoSuchFieldException, IllegalAccessException {
        Field f = stocklicensehistoryfile.getClass().getDeclaredField("mThumbnailHeight");
        f.setAccessible(true);
        f.set(stocklicensehistoryfile, 100);
        Assert.assertEquals(100, stocklicensehistoryfile.getThumbnailHeight().intValue());
    }

    @Test(groups = { "Setters" })
    void setThumbnailHeight_should_set_media_ThumbnailHeight_inPixels_of_Type_Integer_StockLicenseHistoryFile()
            throws NoSuchFieldException, IllegalAccessException {
        stocklicensehistoryfile.setThumbnailHeight(1000);
        Field f = stocklicensehistoryfile.getClass().getDeclaredField("mThumbnailHeight");
        f.setAccessible(true);
        Assert.assertEquals(1000, f.get(stocklicensehistoryfile));
    }
    
    @Test(groups = { "Getters" })
    void getThumbnail110Width_should_return_width_of_110Pixels_Thumbnail_of_Type_Integer_StockLicenseHistoryFile()
            throws NoSuchFieldException, IllegalAccessException {
        Field f = stocklicensehistoryfile.getClass().getDeclaredField("mThumbnail110Width");
        f.setAccessible(true);
        f.set(stocklicensehistoryfile, 100.00);
        Assert.assertEquals(100.00, stocklicensehistoryfile.getThumbnail110Width());
    }

    @Test(groups = { "Setters" })
    void setThumbnail110Width_should_set_width_of_110Pixels_Thumbnail_of_Type_Integer_StockLicenseHistoryFile()
            throws NoSuchFieldException, IllegalAccessException {
        stocklicensehistoryfile.setThumbnail110Width(1000.00);
        Field f = stocklicensehistoryfile.getClass().getDeclaredField("mThumbnail110Width");
        f.setAccessible(true);
        Assert.assertEquals(1000.00, f.get(stocklicensehistoryfile));
    }

    @Test(groups = { "Getters" })
    void getThumbnail110Height_should_return_height_of_110Pixels_Thumbnail_of_Type_Integer_StockLicenseHistoryFile()
            throws NoSuchFieldException, IllegalAccessException {
        Field f = stocklicensehistoryfile.getClass().getDeclaredField("mThumbnail110Height");
        f.setAccessible(true);
        f.set(stocklicensehistoryfile, 100);
        Assert.assertEquals(100, stocklicensehistoryfile.getThumbnail110Height().intValue());
    }

    @Test(groups = { "Setters" })
    void setThumbnail110Height_should_height_of_110Pixels_Thumbnail_of_Type_Integer_StockLicenseHistoryFile()
            throws NoSuchFieldException, IllegalAccessException {
        stocklicensehistoryfile.setThumbnail110Height(1000);
        Field f = stocklicensehistoryfile.getClass().getDeclaredField("mThumbnail110Height");
        f.setAccessible(true);
        Assert.assertEquals(1000, f.get(stocklicensehistoryfile));
    }

    @Test(groups = { "Getters" })
    void getThumbnail160Width_should_return_width_of_160Pixels_Thumbnail_of_Type_Integer_StockLicenseHistoryFile()
            throws NoSuchFieldException, IllegalAccessException {
        Field f = stocklicensehistoryfile.getClass().getDeclaredField("mThumbnail160Width");
        f.setAccessible(true);
        f.set(stocklicensehistoryfile, 100.00);
        Assert.assertEquals(100.00, stocklicensehistoryfile.getThumbnail160Width());
    }

    @Test(groups = { "Setters" })
    void setThumbnail160Width_should_set_width_of_160Pixels_Thumbnail_of_Type_Integer_StockLicenseHistoryFile()
            throws NoSuchFieldException, IllegalAccessException {
        stocklicensehistoryfile.setThumbnail160Width(1000.00);
        Field f = stocklicensehistoryfile.getClass().getDeclaredField("mThumbnail160Width");
        f.setAccessible(true);
        Assert.assertEquals(1000.00, f.get(stocklicensehistoryfile));
    }

    @Test(groups = { "Getters" })
    void getThumbnail160Height_should_return_height_of_160Pixels_Thumbnail_of_Type_Integer_StockLicenseHistoryFile()
            throws NoSuchFieldException, IllegalAccessException {
        Field f = stocklicensehistoryfile.getClass().getDeclaredField("mThumbnail160Height");
        f.setAccessible(true);
        f.set(stocklicensehistoryfile, 100);
        Assert.assertEquals(100, stocklicensehistoryfile.getThumbnail160Height().intValue());
    }

    @Test(groups = { "Setters" })
    void setThumbnail160Height_should_set_height_of_160Pixels_Thumbnail_of_Type_Integer_StockLicenseHistoryFile()
            throws NoSuchFieldException, IllegalAccessException {
        stocklicensehistoryfile.setThumbnail160Height(1000);
        Field f = stocklicensehistoryfile.getClass().getDeclaredField("mThumbnail160Height");
        f.setAccessible(true);
        Assert.assertEquals(1000, f.get(stocklicensehistoryfile));
    }

    @Test(groups = { "Getters" })
    void getThumbnail220Width_should_return_height_of_220Pixels_Thumbnail_of_Type_Integer_StockLicenseHistoryFile()
            throws NoSuchFieldException, IllegalAccessException {
        Field f = stocklicensehistoryfile.getClass().getDeclaredField("mThumbnail220Width");
        f.setAccessible(true);
        f.set(stocklicensehistoryfile, 100.00);
        Assert.assertEquals(100.00, stocklicensehistoryfile.getThumbnail220Width());
    }

    @Test(groups = { "Setters" })
    void setThumbnail220Width_should_set_width_of_220Pixels_Thumbnail_of_Type_Integer_StockLicenseHistoryFile()
            throws NoSuchFieldException, IllegalAccessException {
        stocklicensehistoryfile.setThumbnail220Width(1000.00);
        Field f = stocklicensehistoryfile.getClass().getDeclaredField("mThumbnail220Width");
        f.setAccessible(true);
        Assert.assertEquals(1000.00, f.get(stocklicensehistoryfile));
    }

    @Test(groups = { "Getters" })
    void getThumbnail220Height_should_return_height_of_220Pixels_Thumbnail_of_Type_Integer_StockLicenseHistoryFile()
            throws NoSuchFieldException, IllegalAccessException {
        Field f = stocklicensehistoryfile.getClass().getDeclaredField("mThumbnail220Height");
        f.setAccessible(true);
        f.set(stocklicensehistoryfile, 100);
        Assert.assertEquals(100, stocklicensehistoryfile.getThumbnail220Height().intValue());
    }

    @Test(groups = { "Setters" })
    void setThumbnail220Height_should_set_height_of_220Pixels_Thumbnail_of_Type_Integer_StockLicenseHistoryFile()
            throws NoSuchFieldException, IllegalAccessException {
        stocklicensehistoryfile.setThumbnail220Height(1000);
        Field f = stocklicensehistoryfile.getClass().getDeclaredField("mThumbnail220Height");
        f.setAccessible(true);
        Assert.assertEquals(1000, f.get(stocklicensehistoryfile));
    }

    @Test(groups = { "Getters" })
    void getThumbnail240Width_should_return_height_of_240Pixels_Thumbnail_of_Type_Integer_StockLicenseHistoryFile()
            throws NoSuchFieldException, IllegalAccessException {
        Field f = stocklicensehistoryfile.getClass().getDeclaredField("mThumbnail240Width");
        f.setAccessible(true);
        f.set(stocklicensehistoryfile, 100.00);
        Assert.assertEquals(100.00, stocklicensehistoryfile.getThumbnail240Width());
    }

    @Test(groups = { "Setters" })
    void setThumbnail240Width_should_set_width_of_240Pixels_Thumbnail_of_Type_Integer_StockLicenseHistoryFile()
            throws NoSuchFieldException, IllegalAccessException {
        stocklicensehistoryfile.setThumbnail240Width(1000.00);
        Field f = stocklicensehistoryfile.getClass().getDeclaredField("mThumbnail240Width");
        f.setAccessible(true);
        Assert.assertEquals(1000.00, f.get(stocklicensehistoryfile));
    }

    @Test(groups = { "Getters" })
    void getThumbnail240Height_should_return_height_of_240Pixels_Thumbnail_of_Type_Integer_StockLicenseHistoryFile()
            throws NoSuchFieldException, IllegalAccessException {
        Field f = stocklicensehistoryfile.getClass().getDeclaredField("mThumbnail240Height");
        f.setAccessible(true);
        f.set(stocklicensehistoryfile, 100);
        Assert.assertEquals(100, stocklicensehistoryfile.getThumbnail240Height().intValue());
    }

    @Test(groups = { "Setters" })
    void setThumbnail240Height_should_set_height_of_240Pixels_Thumbnail_of_Type_Integer_StockLicenseHistoryFile()
            throws NoSuchFieldException, IllegalAccessException {
        stocklicensehistoryfile.setThumbnail240Height(1000);
        Field f = stocklicensehistoryfile.getClass().getDeclaredField("mThumbnail240Height");
        f.setAccessible(true);
        Assert.assertEquals(1000, f.get(stocklicensehistoryfile));
    }

    @Test(groups = { "Getters" })
    void getThumbnail500Width_should_return_height_of_500Pixels_Thumbnail_of_Type_Integer_StockLicenseHistoryFile()
            throws NoSuchFieldException, IllegalAccessException {
        Field f = stocklicensehistoryfile.getClass().getDeclaredField("mThumbnail500Width");
        f.setAccessible(true);
        f.set(stocklicensehistoryfile, 100.00);
        Assert.assertEquals(100.00, stocklicensehistoryfile.getThumbnail500Width());
    }

    @Test(groups = { "Setters" })
    void setThumbnail500Width_should_set_width_of_500Pixels_Thumbnail_of_Type_Integer_StockLicenseHistoryFile()
            throws NoSuchFieldException, IllegalAccessException {
        stocklicensehistoryfile.setThumbnail500Width(1000.00);
        Field f = stocklicensehistoryfile.getClass().getDeclaredField("mThumbnail500Width");
        f.setAccessible(true);
        Assert.assertEquals(1000.00, f.get(stocklicensehistoryfile));
    }

    @Test(groups = { "Getters" })
    void getThumbnail500Height_should_return_height_of_500Pixels_Thumbnail_of_Type_Integer_StockLicenseHistoryFile()
            throws NoSuchFieldException, IllegalAccessException {
        Field f = stocklicensehistoryfile.getClass().getDeclaredField("mThumbnail500Height");
        f.setAccessible(true);
        f.set(stocklicensehistoryfile, 100);
        Assert.assertEquals(100, stocklicensehistoryfile.getThumbnail500Height().intValue());
    }

    @Test(groups = { "Setters" })
    void setThumbnail500Height_should_set_height_of_500Pixels_Thumbnail_of_Type_Integer_StockLicenseHistoryFile()
            throws NoSuchFieldException, IllegalAccessException {
        stocklicensehistoryfile.setThumbnail500Height(1000);
        Field f = stocklicensehistoryfile.getClass().getDeclaredField("mThumbnail500Height");
        f.setAccessible(true);
        Assert.assertEquals(1000, f.get(stocklicensehistoryfile));
    }

    @Test(groups = { "Getters" })
    void getThumbnail1000Width_should_return_height_of_1000Pixels_Thumbnail_of_Type_Integer_StockLicenseHistoryFile()
            throws NoSuchFieldException, IllegalAccessException {
        Field f = stocklicensehistoryfile.getClass().getDeclaredField("mThumbnail1000Width");
        f.setAccessible(true);
        f.set(stocklicensehistoryfile, 100.00);
        Assert.assertEquals(100.00, stocklicensehistoryfile.getThumbnail1000Width());
    }

    @Test(groups = { "Setters" })
    void setThumbnail1000Width_should_set_width_of_1000Pixels_Thumbnail_of_Type_Integer_StockLicenseHistoryFile()
            throws NoSuchFieldException, IllegalAccessException {
        stocklicensehistoryfile.setThumbnail1000Width(1000.00);
        Field f = stocklicensehistoryfile.getClass().getDeclaredField("mThumbnail1000Width");
        f.setAccessible(true);
        Assert.assertEquals(1000.00, f.get(stocklicensehistoryfile));
    }

    @Test(groups = { "Getters" })
    void getThumbnail1000Height_should_return_height_of_1000Pixels_Thumbnail_of_Type_Integer_StockLicenseHistoryFile()
            throws NoSuchFieldException, IllegalAccessException {
        Field f = stocklicensehistoryfile.getClass().getDeclaredField("mThumbnail1000Height");
        f.setAccessible(true);
        f.set(stocklicensehistoryfile, 100);
        Assert.assertEquals(100, stocklicensehistoryfile.getThumbnail1000Height().intValue());
    }

    @Test(groups = { "Setters" })
    void setThumbnail1000Height_should_set_height_of_1000Pixels_Thumbnail_of_Type_Integer_StockLicenseHistoryFile()
            throws NoSuchFieldException, IllegalAccessException {
        stocklicensehistoryfile.setThumbnail1000Height(1000);
        Field f = stocklicensehistoryfile.getClass().getDeclaredField("mThumbnail1000Height");
        f.setAccessible(true);
        Assert.assertEquals(1000, f.get(stocklicensehistoryfile));
    }

    @Test(groups = { "Getters" })
    void getWidth_should_return_original_width_of_the_file_px_of_Type_Integer_StockLicenseHistoryFile()
            throws NoSuchFieldException, IllegalAccessException {
        Field f = stocklicensehistoryfile.getClass().getDeclaredField("mWidth");
        f.setAccessible(true);
        f.set(stocklicensehistoryfile, 100);
        Assert.assertEquals(100, stocklicensehistoryfile.getWidth().intValue());
    }

    @Test(groups = { "Setters" })
    void setWidth_should_set_original_width_of_the_file_px_of_Type_Integer_StockLicenseHistoryFile()
            throws NoSuchFieldException, IllegalAccessException {
        stocklicensehistoryfile.setWidth(1000);
        Field f = stocklicensehistoryfile.getClass().getDeclaredField("mWidth");
        f.setAccessible(true);
        Assert.assertEquals(1000, f.get(stocklicensehistoryfile));
    }

    @Test(groups = { "Getters" })
    void getHeight_should_return_original_height_of_the_file_px_of_Type_Integer_StockLicenseHistoryFile()
            throws NoSuchFieldException, IllegalAccessException {
        Field f = stocklicensehistoryfile.getClass().getDeclaredField("mHeight");
        f.setAccessible(true);
        f.set(stocklicensehistoryfile, 100);
        Assert.assertEquals(100, stocklicensehistoryfile.getHeight().intValue());
    }

    @Test(groups = { "Setters" })
    void setHeight_should_set_original_height_of_the_file_px_of_Type_Integer_StockLicenseHistoryFile()
            throws NoSuchFieldException, IllegalAccessException {
        stocklicensehistoryfile.setHeight(1000);
        Field f = stocklicensehistoryfile.getClass().getDeclaredField("mHeight");
        f.setAccessible(true);
        Assert.assertEquals(1000, f.get(stocklicensehistoryfile));
    }

    @Test(groups = { "Getters" })
    void getTitle_should_return_media_title_ofType_String_StockLicenseHistoryFile()
            throws NoSuchFieldException, IllegalAccessException {
        Field f = stocklicensehistoryfile.getClass().getDeclaredField("mTitle");
        f.setAccessible(true);
        f.set(stocklicensehistoryfile, "SomeText");
        Assert.assertTrue(stocklicensehistoryfile.getTitle().equals("SomeText"));
    }

    @Test(groups = { "Setters" })
    void setTitle_should_set_media_title_ofType_String_StockLicenseHistoryFile()
            throws NoSuchFieldException, IllegalAccessException {
        stocklicensehistoryfile.setTitle("SomeText");
        Field f = stocklicensehistoryfile.getClass().getDeclaredField("mTitle");
        f.setAccessible(true);
        Assert.assertTrue(f.get(stocklicensehistoryfile).equals("SomeText"));
    }
    
    @Test(groups = { "Getters" })
    void getCreatorName_should_return_media_CreatorName_ofType_String_StockLicenseHistoryFile()
            throws NoSuchFieldException, IllegalAccessException {
        Field f = stocklicensehistoryfile.getClass().getDeclaredField("mCreatorName");
        f.setAccessible(true);
        f.set(stocklicensehistoryfile, "SomeText");
        Assert.assertTrue(stocklicensehistoryfile.getCreatorName().equals("SomeText"));
    }

    @Test(groups = { "Setters" })
    void setCreatorName_should_set_media_CreatorName_ofType_String_StockLicenseHistoryFile()
            throws NoSuchFieldException, IllegalAccessException {
        stocklicensehistoryfile.setCreatorName("SomeText");
        Field f = stocklicensehistoryfile.getClass().getDeclaredField("mCreatorName");
        f.setAccessible(true);
        Assert.assertTrue(f.get(stocklicensehistoryfile).equals("SomeText"));
    }
    
    @Test(groups = { "Getters" })
    void getThumbnailUrl_should_return_media_ThumbnailUrl_ofType_String_StockLicenseHistoryFile()
            throws NoSuchFieldException, IllegalAccessException {
        Field f = stocklicensehistoryfile.getClass().getDeclaredField("mThumbnailUrl");
        f.setAccessible(true);
        f.set(stocklicensehistoryfile, "SomeText");
        Assert.assertTrue(stocklicensehistoryfile.getThumbnailUrl().equals("SomeText"));
    }

    @Test(groups = { "Setters" })
    void setThumbnailUrl_should_set_media_ThumbnailUrl_ofType_String_StockLicenseHistoryFile()
            throws NoSuchFieldException, IllegalAccessException {
        stocklicensehistoryfile.setThumbnailUrl("SomeText");
        Field f = stocklicensehistoryfile.getClass().getDeclaredField("mThumbnailUrl");
        f.setAccessible(true);
        Assert.assertTrue(f.get(stocklicensehistoryfile).equals("SomeText"));
    }
    
    @Test(groups = { "Getters" })
    void getThumbnail110Url_should_return_media_Thumbnail110Url_ofType_String_StockLicenseHistoryFile()
            throws NoSuchFieldException, IllegalAccessException {
        Field f = stocklicensehistoryfile.getClass().getDeclaredField("mThumbnail110Url");
        f.setAccessible(true);
        f.set(stocklicensehistoryfile, "SomeText");
        Assert.assertTrue(stocklicensehistoryfile.getThumbnail110Url().equals("SomeText"));
    }

    @Test(groups = { "Setters" })
    void setThumbnail110Url_should_set_media_Thumbnail110Url_ofType_String_StockLicenseHistoryFile()
            throws NoSuchFieldException, IllegalAccessException {
        stocklicensehistoryfile.setThumbnail110Url("SomeText");
        Field f = stocklicensehistoryfile.getClass().getDeclaredField("mThumbnail110Url");
        f.setAccessible(true);
        Assert.assertTrue(f.get(stocklicensehistoryfile).equals("SomeText"));
    }

    @Test(groups = { "Getters" })
    void getThumbnail160Url_should_return_media_Url_for_160px_thumbnaill_ofType_String_StockLicenseHistoryFile()
            throws NoSuchFieldException, IllegalAccessException {
        Field f = stocklicensehistoryfile.getClass().getDeclaredField("mThumbnail160Url");
        f.setAccessible(true);
        f.set(stocklicensehistoryfile, "SomeText");
        Assert.assertTrue(stocklicensehistoryfile.getThumbnail160Url().equals("SomeText"));
    }

    @Test(groups = { "Setters" })
    void setThumbnail160Url_should_set_media_Url_for_160px_thumbnaill_ofType_String_StockLicenseHistoryFile()
            throws NoSuchFieldException, IllegalAccessException {
        stocklicensehistoryfile.setThumbnail160Url("SomeText");
        Field f = stocklicensehistoryfile.getClass().getDeclaredField("mThumbnail160Url");
        f.setAccessible(true);
        Assert.assertTrue(f.get(stocklicensehistoryfile).equals("SomeText"));
    }

    @Test(groups = { "Getters" })
    void getThumbnail220Url_should_return_media_Url_for_220px_thumbnaill_ofType_String_StockLicenseHistoryFile()
            throws NoSuchFieldException, IllegalAccessException {
        Field f = stocklicensehistoryfile.getClass().getDeclaredField("mThumbnail220Url");
        f.setAccessible(true);
        f.set(stocklicensehistoryfile, "SomeText");
        Assert.assertTrue(stocklicensehistoryfile.getThumbnail220Url().equals("SomeText"));
    }

    @Test(groups = { "Setters" })
    void setThumbnail220Url_should_set_media_Url_for_220px_thumbnaill_ofType_String_StockLicenseHistoryFile()
            throws NoSuchFieldException, IllegalAccessException {
        stocklicensehistoryfile.setThumbnail220Url("SomeText");
        Field f = stocklicensehistoryfile.getClass().getDeclaredField("mThumbnail220Url");
        f.setAccessible(true);
        Assert.assertTrue(f.get(stocklicensehistoryfile).equals("SomeText"));
    }

    @Test(groups = { "Getters" })
    void getThumbnail240Url_should_return_media_Url_for_240px_thumbnaill_ofType_String_StockLicenseHistoryFile()
            throws NoSuchFieldException, IllegalAccessException {
        Field f = stocklicensehistoryfile.getClass().getDeclaredField("mThumbnail240Url");
        f.setAccessible(true);
        f.set(stocklicensehistoryfile, "SomeText");
        Assert.assertTrue(stocklicensehistoryfile.getThumbnail240Url().equals("SomeText"));
    }

    @Test(groups = { "Setters" })
    void setThumbnail240Url_should_set_media_Url_for_240px_thumbnaill_ofType_String_StockLicenseHistoryFile()
            throws NoSuchFieldException, IllegalAccessException {
        stocklicensehistoryfile.setThumbnail240Url("SomeText");
        Field f = stocklicensehistoryfile.getClass().getDeclaredField("mThumbnail240Url");
        f.setAccessible(true);
        Assert.assertTrue(f.get(stocklicensehistoryfile).equals("SomeText"));
    }

    @Test(groups = { "Getters" })
    void getThumbnail500Url_should_return_media_Url_for_500px_thumbnaill_ofType_String_StockLicenseHistoryFile()
            throws NoSuchFieldException, IllegalAccessException {
        Field f = stocklicensehistoryfile.getClass().getDeclaredField("mThumbnail500Url");
        f.setAccessible(true);
        f.set(stocklicensehistoryfile, "SomeText");
        Assert.assertTrue(stocklicensehistoryfile.getThumbnail500Url().equals("SomeText"));
    }

    @Test(groups = { "Setters" })
    void setThumbnail500Url_should_set_media_Url_for_500px_thumbnaill_ofType_String_StockLicenseHistoryFile()
            throws NoSuchFieldException, IllegalAccessException {
        stocklicensehistoryfile.setThumbnail500Url("SomeText");
        Field f = stocklicensehistoryfile.getClass().getDeclaredField("mThumbnail500Url");
        f.setAccessible(true);
        Assert.assertTrue(f.get(stocklicensehistoryfile).equals("SomeText"));
    }

    @Test(groups = { "Getters" })
    void getThumbnail1000Url_should_return_media_Url_for_1000px_thumbnaill_ofType_String_StockLicenseHistoryFile()
            throws NoSuchFieldException, IllegalAccessException {
        Field f = stocklicensehistoryfile.getClass().getDeclaredField("mThumbnail1000Url");
        f.setAccessible(true);
        f.set(stocklicensehistoryfile, "SomeText");
        Assert.assertTrue(stocklicensehistoryfile.getThumbnail1000Url().equals("SomeText"));
    }

    @Test(groups = { "Setters" })
    void setThumbnail1000Url_should_set_media_Url_for_1000px_thumbnaill_ofType_String_StockLicenseHistoryFile()
            throws NoSuchFieldException, IllegalAccessException {
        stocklicensehistoryfile.setThumbnail1000Url("SomeText");
        Field f = stocklicensehistoryfile.getClass().getDeclaredField("mThumbnail1000Url");
        f.setAccessible(true);
        Assert.assertTrue(f.get(stocklicensehistoryfile).equals("SomeText"));
    }
    
    @Test(groups = { "Getters" })
    void getContentType_should_return_ContentType_of_file_ofType_String_StockLicenseHistoryFile()
            throws NoSuchFieldException, IllegalAccessException {
        Field f = stocklicensehistoryfile.getClass().getDeclaredField("mContentType");
        f.setAccessible(true);
        f.set(stocklicensehistoryfile, "SomeText");
        Assert.assertTrue(stocklicensehistoryfile.getContentType().equals("SomeText"));
    }

    @Test(groups = { "Setters" })
    void setContentType_should_set_ContentType_of_file_ofType_String_StockLicenseHistoryFile()
            throws NoSuchFieldException, IllegalAccessException {
        stocklicensehistoryfile.setContentType("SomeText");
        Field f = stocklicensehistoryfile.getClass().getDeclaredField("mContentType");
        f.setAccessible(true);
        Assert.assertTrue(f.get(stocklicensehistoryfile).equals("SomeText"));
    }
    
    @Test(groups = { "Getters" })
    void getDetailsUrl_should_return_Url_to_stockDetails_ofType_String_StockLicenseHistoryFile()
            throws NoSuchFieldException, IllegalAccessException {
        Field f = stocklicensehistoryfile.getClass().getDeclaredField("mDetailsUrl");
        f.setAccessible(true);
        f.set(stocklicensehistoryfile, "SomeText");
        Assert.assertTrue(stocklicensehistoryfile.getDetailsUrl().equals("SomeText"));
    }

    @Test(groups = { "Setters" })
    void setDetailsUrl_should_set_Url_to_stockDetails_ofType_String_StockLicenseHistoryFile()
            throws NoSuchFieldException, IllegalAccessException {
        stocklicensehistoryfile.setDetailsUrl("SomeText");
        Field f = stocklicensehistoryfile.getClass().getDeclaredField("mDetailsUrl");
        f.setAccessible(true);
        Assert.assertTrue(f.get(stocklicensehistoryfile).equals("SomeText"));
    }
    
    @Test(groups = { "Getters" })
    void getContentUrl_should_return_Url_ofType_String_StockLicenseHistoryFile()
            throws NoSuchFieldException, IllegalAccessException {
        Field f = stocklicensehistoryfile.getClass().getDeclaredField("mContentUrl");
        f.setAccessible(true);
        f.set(stocklicensehistoryfile, "SomeText");
        Assert.assertTrue(stocklicensehistoryfile.getContentUrl().equals("SomeText"));
    }

    @Test(groups = { "Setters" })
    void setContentUrl_should_set_Url_ofType_String_StockLicenseHistoryFile()
            throws NoSuchFieldException, IllegalAccessException {
        stocklicensehistoryfile.setContentUrl("SomeText");
        Field f = stocklicensehistoryfile.getClass().getDeclaredField("mContentUrl");
        f.setAccessible(true);
        Assert.assertTrue(f.get(stocklicensehistoryfile).equals("SomeText"));
    }
}
