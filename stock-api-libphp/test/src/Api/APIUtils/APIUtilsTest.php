<?php
/*************************************************************************
 * ADOBE CONFIDENTIAL
 * ___________________
 *
 *  Copyright 2017 Adobe Systems Incorporated
 *  All Rights Reserved.
 *
 * NOTICE:  All information contained herein is, and remains
 * the property of Adobe Systems Incorporated and its suppliers,
 * if any.  The intellectual and technical concepts contained
 * herein are proprietary to Adobe Systems Incorporated and its
 * suppliers and are protected by all applicable intellectual property
 * laws, including trade secret and copyright laws.
 * Dissemination of this information or reproduction of this material
 * is strictly forbidden unless prior written permission is obtained
 * from Adobe Systems Incorporated.
 **************************************************************************/

namespace AdobeStock\Api\Test;

use \PHPUnit\Framework\TestCase;
use \AdobeStock\Api\Core\Config as CoreConfig;
use \AdobeStock\Api\Utils\APIUtils;

class APIUtilsTest extends TestCase
{
    /**
     * @test
     */
    public function generateCommonAPIHeadersShouldGenerateHeadersArrayFromConfigAndAcessToken()
    {
        $config = new CoreConfig('APIKey', 'Product', 'STAGE');
        $headers = APIUtils::generateCommonAPIHeaders($config, '');
        $this->assertEquals('APIKey', $headers['headers']['x-api-key']);
    }
    
    /**
     * @test
     */
    public function downSampleImageShouldResizeImagetoExpectedDimensionsIfWidthisGreaterThanHeight()
    {
        $image = APIUtils::downSampleImage('test/resources/TestFileWidth.png');
        $this->assertNotNull($image);
    }
    
    /**
     * @test
     */
    public function downSampleImageShouldResizeImagetoExpectedDimensionsIfHeightisGreaterThanWidth()
    {
        $image = APIUtils::downSampleImage('test/resources/TestFile.png');
        $this->assertNotNull($image);
    }
    
    /**
     * @test
     */
    public function downSampleImageShouldNotDownSampleSmallImage()
    {
        $image = APIUtils::downSampleImage('test/resources/SmallImage.jpg');
        $this->assertNotNull($image);
    }
    
    /**
     * @test
     * @expectedException \AdobeStock\Api\Exception\StockApi
     */
    public function downSampleImageShouldThrowExceptionIfImageIsNotSupported()
    {
        $image = APIUtils::downSampleImage('test/resources/UnsupportedBMP.bmp');
    }
    
    /**
     * @test
     * @expectedException \AdobeStock\Api\Exception\StockApi
     */
    public function downSampleImageShouldThrowExceptionIfImageIsBiggerThanExpected()
    {
        $image = APIUtils::downSampleImage('test/resources/BigImage.jpg');
    }
}
