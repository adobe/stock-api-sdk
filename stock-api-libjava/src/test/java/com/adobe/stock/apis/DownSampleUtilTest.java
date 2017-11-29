/*******************************************************************************
 * Copyright 2017 Adobe Systems Incorporated. All rights reserved.
 * This file is licensed to you under the Apache License, Version 2.0
 * (the "License") you may not use this file except in compliance with the
 * License. You may obtain a copy of the License at
 * http://www.apache.org/licenses/LICENSE-2.0
 *******************************************************************************/
package com.adobe.stock.apis;

import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

import org.testng.Assert;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;

import com.adobe.stock.exception.StockException;

@Listeners({ com.adobe.stock.logger.TestCustomLogger.class,
    com.adobe.stock.logger.TestSuiteLogger.class })
@Test(suiteName = "DownSampleUtil")
public class DownSampleUtilTest {

    @Test(groups = { "Exceptions" }, expectedExceptions =
            StockException.class, expectedExceptionsMessageRegExp =
                                            "Image cannot be null")
    void downSampleImageUtil_should_throw_exception_if_null_image_is_passed_for_downSampling()
            throws IOException, StockException {
        byte[] downSampledImage = DownSampleUtil.downSampleImageUtil(null);
        Assert.assertNull(downSampledImage);
    }
    @Test(groups = { "Exceptions" }, expectedExceptions =
            StockException.class, expectedExceptionsMessageRegExp =
            "Image is too large for visual search!")
    void downSampleImageUtil_should_throw_exception_if_image_height_is_greater_than_23000_pixels() 
            throws IOException, StockException {
        BufferedImage img = new BufferedImage(1000, 24000, 1);
        byte[] downSampledImage = DownSampleUtil.downSampleImageUtil(imageToByteArray(img));
        Assert.assertNotNull(downSampledImage);
    }
    @Test(groups = { "Exceptions" }, expectedExceptions =
            StockException.class, expectedExceptionsMessageRegExp =
            "Image is too large for visual search!")
    void downSampleImageUtil_should_throw_exception_if_image_width_is_greater_than_23000_pixels() 
            throws IOException, StockException {
        BufferedImage img = new BufferedImage(24000, 1000, 1);
        byte[] downSampledImage = DownSampleUtil.downSampleImageUtil(imageToByteArray(img));
        Assert.assertNotNull(downSampledImage);
    }
    @Test
    void downSampleImageUtil_should_not_downSample_image_smaller_than_1000x1000_width_is_greater_than_height_pixels()
            throws IOException, StockException{
        BufferedImage img = new BufferedImage(950,800 , 1);
        byte[] downSampledImage = DownSampleUtil.downSampleImageUtil(
                imageToByteArray(img));
        ByteArrayInputStream bais = new ByteArrayInputStream(downSampledImage);
        BufferedImage downSampledImg =  ImageIO.read(bais);
        Assert.assertEquals(img.getWidth(), downSampledImg.getWidth());
        Assert.assertEquals(img.getHeight(), downSampledImg.getHeight());
    }
    @Test
    void downSampleImageUtil_should_not_downSample_image_smaller_than_1000x1000_height_is_greater_than_width_pixels()
            throws IOException, StockException{
        BufferedImage img = new BufferedImage(800,900 , 1);
        byte[] downSampledImage = DownSampleUtil.downSampleImageUtil(
                imageToByteArray(img));
        ByteArrayInputStream bais = new ByteArrayInputStream(downSampledImage);
        BufferedImage downSampledImg =  ImageIO.read(bais);
        Assert.assertEquals(img.getWidth(), downSampledImg.getWidth());
        Assert.assertEquals(img.getHeight(), downSampledImg.getHeight());
    }
    @Test
    void downSampleImageUtil_should_downSample_image_height_greater_than_1000_pixels()
            throws IOException, StockException{
        BufferedImage originalImage = ImageIO
                .read(new File("src/test/resources/OriginalImage.jpg"));
        byte[] downSampledImage = DownSampleUtil.downSampleImageUtil(
                imageToByteArray(originalImage));
        ByteArrayInputStream bais = new ByteArrayInputStream(downSampledImage);
        BufferedImage downSampledImg =  ImageIO.read(bais);
        ImageIO.write(downSampledImg, "jpg", new File("src/test/resources/DownSampledImage.jpg"));
        BufferedImage downSampleFromPhotoshop = ImageIO
                .read(new File("src/test/resources/DownSampleFromPhotoshop.jpg"));
        Assert.assertEquals(downSampleFromPhotoshop.getWidth(), downSampledImg.getWidth());
        Assert.assertEquals(downSampleFromPhotoshop.getHeight(), downSampledImg.getHeight());
    }
    @Test
    void downSampleImageUtil_should_downSample_image_width_greater_than_1000_pixels()
            throws IOException, StockException{
        BufferedImage originalImage = ImageIO
                .read(new File("src/test/resources/OriginalImageWidth.jpg"));
        byte[] downSampledImage = DownSampleUtil.downSampleImageUtil(
                imageToByteArray(originalImage));
        ByteArrayInputStream bais = new ByteArrayInputStream(downSampledImage);
        BufferedImage downSampledImg =  ImageIO.read(bais);
        ImageIO.write(downSampledImg, "jpg", new File("src/test/resources/DownSampledImageWidth.jpg"));
        Assert.assertEquals(1000, downSampledImg.getWidth());
        Assert.assertEquals(954, downSampledImg.getHeight());
    }
    @Test(groups = { "Exceptions" }, expectedExceptions  = java.lang.IllegalAccessException.class)
    void testValidatesThatClassDownSampleUtilIsNotInstantiable() 
            throws ClassNotFoundException, InstantiationException, IllegalAccessException {
      Class<?> cls = Class.forName("com.adobe.stock.apis.DownSampleUtil");
      cls.newInstance(); 
    }
    byte[] imageToByteArray(BufferedImage img) throws IOException{
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        ImageIO.write(img, "jpg", baos);
        baos.flush();
        byte[] imageInByte = baos.toByteArray();
        baos.close();
        return imageInByte;
    }

}