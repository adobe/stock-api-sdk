/*******************************************************************************
 * Copyright 2017 Adobe Systems Incorporated. All rights reserved.
 * This file is licensed to you under the Apache License, Version 2.0
 * (the "License") you may not use this file except in compliance with the
 * License. You may obtain a copy of the License at
 * http://www.apache.org/licenses/LICENSE-2.0
 *******************************************************************************/
package com.adobe.stock.apis;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.Random;

import org.apache.commons.io.IOUtils;
import org.json.JSONArray;
import org.json.JSONObject;
import org.mockito.Mockito;
import org.powermock.api.mockito.PowerMockito;
import org.powermock.core.classloader.annotations.PrepareForTest;
import org.powermock.modules.testng.PowerMockObjectFactory;
import org.testng.Assert;
import org.testng.IObjectFactory;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Listeners;
import org.testng.annotations.ObjectFactory;
import org.testng.annotations.Test;

import com.adobe.stock.config.Endpoints;
import com.adobe.stock.enums.AssetTemplateCategory;
import com.adobe.stock.enums.Environment;
import com.adobe.stock.exception.StockException;
import com.adobe.stock.models.SearchFilesResponse;
import com.adobe.stock.models.StockFile;
import com.adobe.stock.models.StockFileComps;
import com.adobe.stock.models.StockFileKeyword;
import com.adobe.stock.models.StockFileLicenses;
import com.fasterxml.jackson.core.JsonProcessingException;

class TestBlankClass {
}


class TestClassWithoutGetterSetter {
    Integer intVal;
}
class TestClassWithConstructor {
    private int intVal;
    
    TestClassWithConstructor(){
        
    }
}
class Property {

    private String key;

    public String getKey() {
        return key;
    }

    public void setKey(String key) {
        this.key = key;
    }

}

class TestClass {

    private Integer id;

    private String description;

    private Property property;

    private ArrayList<Integer> array;

    private Boolean required;

    private Double precision;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Property getProperty() {
        return property;
    }

    public void setProperty(Property property) {
        this.property = property;
    }

    public ArrayList<Integer> getArray() {
        return array;
    }

    public void setArray(ArrayList<Integer> array) {
        this.array = array;
    }

    public Boolean getRequired() {
        return required;
    }

    public void setRequired(Boolean required) {
        this.required = required;
    }

    public Double getPrecision() {
        return precision;
    }

    public void setPrecision(Double precision) {
        this.precision = precision;
    }

}

@Listeners({ com.adobe.stock.logger.TestCustomLogger.class,
        com.adobe.stock.logger.TestSuiteLogger.class })
@PrepareForTest(JsonUtils.class)
@Test(suiteName = "JsonUtils")
public class JsonUtilsTest {
    private static final String TEST_JSON_FILE1 = "jsonUtilsTest1.json",TEST_JSON_FILE2 = "jsonUtilsTest2.json",TEST_JSON_FILE3 = "jsonUtilsTest3.json", TEST_JSON_FILE4 = "jsonUtilsTest4.json";

    @ObjectFactory
    public IObjectFactory getObjectFactory() {
        return new PowerMockObjectFactory();
    }
    @Test(expectedExceptions = { NullPointerException.class })
    public void parseJson_should_throw_exception_since_null_string_passed()
            throws StockException {
        JsonUtils.parseJson(TestBlankClass.class, null);
    }

    @Test(expectedExceptions = {
            StockException.class }, expectedExceptionsMessageRegExp = "Could not map the given json string to response object")
    public void parseJson_should_throw_exception_since_blank_string_passed()
            throws StockException {
        JsonUtils.parseJson(TestBlankClass.class, "");
    }
    @Test
    public void parseObjectToJson_should_throw_exception_since_blank_string_passed()
            throws StockException {
        JsonUtils.parseObjectToJson(null);
    }
    @Test(expectedExceptions = {
            StockException.class }, expectedExceptionsMessageRegExp = "Could not parse the given json string")
    public void parseJson_should_throw_exception_since_invalid_json_passed()
            throws StockException {
        JsonUtils.parseJson(TestBlankClass.class, "{");
    }

    @Test(expectedExceptions = {
            StockException.class }, expectedExceptionsMessageRegExp = "Could not map the given json string to response object")
    public void parseJson_should_throw_exception_since_class_getter_setter_not_defined()
            throws StockException {
        String jsonString = "{\"intVal\":1}";
        JsonUtils.parseJson(TestClassWithoutGetterSetter.class, jsonString);
    }
    @Test(expectedExceptions = {StockException.class },
    expectedExceptionsMessageRegExp = "Could not parse the given object to JSON")
    public void parseObjectToJson_should_throw_exception_since_object_is_null() throws StockException{
        String jsonString = JsonUtils.parseObjectToJson(new TestClassWithoutGetterSetter());
    }
    @Test
    public void parseJson_should_return_correct_testclass_object()
            throws StockException {
        String jsonString = "{  \"id\":1234,  \"description\":\"Sample description\", \"property\": {     \"key\": \"test key\"  },  \"array\":[3,5,9,10],  \"required\":true,  \"precision\":10.12}";
        JSONObject testObj = new JSONObject(jsonString);
        TestClass parsedObj = (TestClass) JsonUtils.parseJson(TestClass.class,
                jsonString);

        Assert.assertEquals(testObj.opt("id"), checkNull(parsedObj.getId()));
        Assert.assertEquals(testObj.opt("description"),
                checkNull(parsedObj.getDescription()));
        Assert.assertEquals(testObj.opt("required"),
                checkNull(parsedObj.getRequired()));

        if (!testObj.isNull("array")) {
            JSONArray objArray = (JSONArray) testObj.opt("array");
            ArrayList<Integer> parsedArray = parsedObj.getArray();
            for (int i = 0; i < objArray.length(); i++) {
                Assert.assertTrue(parsedArray.contains(objArray.get(i)));
            }
        }
        if (!testObj.isNull("property")) {
            JSONObject obj = (JSONObject) testObj.opt("property");
            Assert.assertNotNull(parsedObj.getProperty());
            Assert.assertEquals(checkNull(obj.opt("key")),
                    parsedObj.getProperty().getKey());

        }
    }

    @DataProvider
    public Object[][] jsonStringProvider() {
        Object[][] data = null;
        try {
            
            data = new Object[][] { 
                /**
                 * Test case 1 (template)
                 * https://stock-stage.adobe.io/Rest/Media/1/Search/Files?search_parameters[words]=play&result_columns[]=nb_results&result_columns[]=is_licensed&result_columns[]=comp_url&result_columns[]=comp_width&result_columns[]=creator_name&result_columns[]=comp_height&result_columns[]=nb_views&result_columns[]=nb_downloads&result_columns[]=thumbnail_url&result_columns[]=thumbnail_html_tag&result_columns[]=framerate&result_columns[]=thumbnail_height&result_columns[]=thumbnail_110_url&result_columns[]=thumbnail_110_width&result_columns[]=thumbnail_110_height&result_columns[]=video_small_preview_content_length&result_columns[]=video_small_preview_width&result_columns[]=video_small_preview_height&result_columns[]=video_small_preview_url&result_columns[]=video_preview_content_type&result_columns[]=video_preview_content_length&result_columns[]=video_preview_width&result_columns[]=video_preview_height&result_columns[]=video_preview_url&result_columns[]=licenses&result_columns[]=size_bytes&result_columns[]=description&result_columns[]=template_type_id&result_columns[]=details_url&result_columns[]=duration&result_columns[]=has_releases&result_columns[]=thumbnail_1000_height&result_columns[]=thumbnail_1000_width&result_columns[]=thumbnail_1000_url&result_columns[]=thumbnail_500_height&result_columns[]=thumbnail_500_width&result_columns[]=thumbnail_500_url&result_columns[]=thumbnail_240_height&result_columns[]=thumbnail_240_width&result_columns[]=thumbnail_240_url&result_columns[]=thumbnail_220_url&result_columns[]=thumbnail_220_height&result_columns[]=thumbnail_220_width&result_columns[]=thumbnail_160_height&result_columns[]=thumbnail_160_width&result_columns[]=thumbnail_160_url&result_columns[]=country_name&search_parameters[limit]=64&result_columns[]=template_category_ids&search_parameters[filters][template_type_id][]=1&search_parameters[filters][template_type_id][]=2&search_parameters[filters][template_category_id][]=1&search_parameters[filters][template_category_id][]=2&search_parameters%5Bfilters%5D%5Bcontent_type%3Atemplate%5D=1&result_columns[]=category&result_columns[]=creator_id&result_columns[]=thumbnail_width&result_columns[]=creation_date&result_columns[]=width&result_columns[]=height&result_columns[]=comp_url&result_columns[]=keywords&result_columns[]=media_type_id&result_columns[]=vector_type&result_columns[]=content_type&result_columns[]=stock_id&result_columns[]=comps
                 */
                { JsonUtilsTest.getResourceAsString(TEST_JSON_FILE1) },
                /**
                 * Test case 2 
                 * https://stock-stage.adobe.io/Rest/Media/1/Search/Files?search_parameters[words]=play&result_columns[]=nb_results&result_columns[]=is_licensed&result_columns[]=comp_url&result_columns[]=comp_width&result_columns[]=creator_name&result_columns[]=comp_height&result_columns[]=nb_views&result_columns[]=nb_downloads&result_columns[]=thumbnail_url&result_columns[]=thumbnail_html_tag&result_columns[]=framerate&result_columns[]=thumbnail_height&result_columns[]=thumbnail_110_url&result_columns[]=thumbnail_110_width&result_columns[]=thumbnail_110_height&result_columns[]=video_small_preview_content_length&result_columns[]=video_small_preview_width&result_columns[]=video_small_preview_height&result_columns[]=video_small_preview_url&result_columns[]=video_preview_content_type&result_columns[]=video_preview_content_length&result_columns[]=video_preview_width&result_columns[]=video_preview_height&result_columns[]=video_preview_url&result_columns[]=licenses&result_columns[]=size_bytes&result_columns[]=description&result_columns[]=template_type_id&result_columns[]=details_url&result_columns[]=duration&result_columns[]=has_releases&result_columns[]=thumbnail_1000_height&result_columns[]=thumbnail_1000_width&result_columns[]=thumbnail_1000_url&result_columns[]=thumbnail_500_height&result_columns[]=thumbnail_500_width&result_columns[]=thumbnail_500_url&result_columns[]=thumbnail_240_height&result_columns[]=thumbnail_240_width&result_columns[]=thumbnail_240_url&result_columns[]=thumbnail_220_url&result_columns[]=thumbnail_220_height&result_columns[]=thumbnail_220_width&result_columns[]=thumbnail_160_height&result_columns[]=thumbnail_160_width&result_columns[]=thumbnail_160_url&result_columns[]=country_name&search_parameters[limit]=64&result_columns[]=template_category_ids&result_columns[]=category&result_columns[]=creator_id&result_columns[]=thumbnail_width&result_columns[]=creation_date&result_columns[]=width&result_columns[]=height&result_columns[]=comp_url&result_columns[]=keywords&result_columns[]=media_type_id&result_columns[]=vector_type&result_columns[]=content_type&result_columns[]=stock_id&result_columns[]=comps&result_columns[]=premium_level_id&result_columns[]=is_premium&result_columns[]=licenses&result_columns[]=video_small_preview_content_type&result_columns[]=marketing_text&search_parameters[limit]=20
                 */
                {JsonUtilsTest.getResourceAsString(TEST_JSON_FILE2) },
                /**
                 * Test Case 3 (video)
                 * https://stock-stage.adobe.io/Rest/Media/1/Search/Files?search_parameters[words]=play&result_columns[]=nb_results&result_columns[]=is_licensed&result_columns[]=comp_url&result_columns[]=comp_width&result_columns[]=creator_name&result_columns[]=comp_height&result_columns[]=nb_views&result_columns[]=nb_downloads&result_columns[]=thumbnail_url&result_columns[]=thumbnail_html_tag&result_columns[]=framerate&result_columns[]=thumbnail_height&result_columns[]=thumbnail_110_url&result_columns[]=thumbnail_110_width&result_columns[]=thumbnail_110_height&result_columns[]=video_small_preview_content_length&result_columns[]=video_small_preview_width&result_columns[]=video_small_preview_height&result_columns[]=video_small_preview_url&result_columns[]=video_preview_content_type&result_columns[]=video_preview_content_length&result_columns[]=video_preview_width&result_columns[]=video_preview_height&result_columns[]=video_preview_url&result_columns[]=licenses&result_columns[]=size_bytes&result_columns[]=description&result_columns[]=template_type_id&result_columns[]=details_url&result_columns[]=duration&result_columns[]=has_releases&result_columns[]=thumbnail_1000_height&result_columns[]=thumbnail_1000_width&result_columns[]=thumbnail_1000_url&result_columns[]=thumbnail_500_height&result_columns[]=thumbnail_500_width&result_columns[]=thumbnail_500_url&result_columns[]=thumbnail_240_height&result_columns[]=thumbnail_240_width&result_columns[]=thumbnail_240_url&result_columns[]=thumbnail_220_url&result_columns[]=thumbnail_220_height&result_columns[]=thumbnail_220_width&result_columns[]=thumbnail_160_height&result_columns[]=thumbnail_160_width&result_columns[]=thumbnail_160_url&result_columns[]=country_name&search_parameters[limit]=64&result_columns[]=template_category_ids&result_columns[]=category&result_columns[]=creator_id&result_columns[]=thumbnail_width&result_columns[]=creation_date&result_columns[]=width&result_columns[]=height&result_columns[]=comp_url&result_columns[]=keywords&result_columns[]=media_type_id&result_columns[]=vector_type&result_columns[]=content_type&result_columns[]=stock_id&result_columns[]=comps&result_columns[]=premium_level_id&result_columns[]=is_premium&result_columns[]=licenses&result_columns[]=video_small_preview_content_type&result_columns[]=marketing_text&search_parameters[limit]=20&search_parameters[filters][content_type:video]=1
                 */
                {JsonUtilsTest.getResourceAsString(TEST_JSON_FILE3) },
                /**
                 * Test case 4 (licensed)
                 * https://stock-stage.adobe.io/Rest/Media/1/Search/Files?search_parameters[words]=tree&result_columns[]=nb_results&result_columns[]=is_licensed&result_columns[]=comp_url&result_columns[]=comp_width&result_columns[]=creator_name&result_columns[]=comp_height&result_columns[]=nb_views&result_columns[]=nb_downloads&result_columns[]=thumbnail_url&result_columns[]=thumbnail_html_tag&result_columns[]=framerate&result_columns[]=thumbnail_height&result_columns[]=thumbnail_110_url&result_columns[]=thumbnail_110_width&result_columns[]=thumbnail_110_height&result_columns[]=video_small_preview_content_length&result_columns[]=video_small_preview_width&result_columns[]=video_small_preview_height&result_columns[]=video_small_preview_url&result_columns[]=video_preview_content_type&result_columns[]=video_preview_content_length&result_columns[]=video_preview_width&result_columns[]=video_preview_height&result_columns[]=video_preview_url&result_columns[]=licenses&result_columns[]=size_bytes&result_columns[]=description&result_columns[]=template_type_id&result_columns[]=details_url&result_columns[]=duration&result_columns[]=has_releases&result_columns[]=thumbnail_1000_height&result_columns[]=thumbnail_1000_width&result_columns[]=thumbnail_1000_url&result_columns[]=thumbnail_500_height&result_columns[]=thumbnail_500_width&result_columns[]=thumbnail_500_url&result_columns[]=thumbnail_240_height&result_columns[]=thumbnail_240_width&result_columns[]=thumbnail_240_url&result_columns[]=thumbnail_220_url&result_columns[]=thumbnail_220_height&result_columns[]=thumbnail_220_width&result_columns[]=thumbnail_160_height&result_columns[]=thumbnail_160_width&result_columns[]=thumbnail_160_url&result_columns[]=country_name&search_parameters[limit]=64&result_columns[]=template_category_ids&result_columns[]=category&result_columns[]=creator_id&result_columns[]=thumbnail_width&result_columns[]=creation_date&result_columns[]=width&result_columns[]=height&result_columns[]=comp_url&result_columns[]=keywords&result_columns[]=media_type_id&result_columns[]=vector_type&result_columns[]=content_type&result_columns[]=stock_id&result_columns[]=comps&result_columns[]=premium_level_id&result_columns[]=is_premium&result_columns[]=licenses&result_columns[]=video_small_preview_content_type&result_columns[]=marketing_text
                 */
                {JsonUtilsTest.getResourceAsString(TEST_JSON_FILE4) }
            };
        } catch (IOException e) {
            Assert.fail("Didn't expect the exception!", e);
        }
        return data;
    }

    private static String getResourceAsString(String fileName)
            throws IOException {
        InputStream inputStream = JsonUtilsTest.class
                .getResourceAsStream(fileName);
        return IOUtils.toString(inputStream);
    }

    @Test(dataProvider = "jsonStringProvider")
    public void parseJson_should_return_correct_search_response_object(
            final String jsonString) throws StockException {

        // Get Json object from jsonString
        JSONObject responseObj = new JSONObject(jsonString);
        JSONArray filesArray = null;
        if (!responseObj.isNull("files"))
            filesArray = responseObj.getJSONArray("files");

        // Get response object from JsonUtils
        SearchFilesResponse response = (SearchFilesResponse) JsonUtils
                .parseJson(SearchFilesResponse.class, jsonString);

        Assert.assertEquals(response.getNbResults(),
                checkNull(responseObj.opt("nb_results")));
        if (filesArray != null) {
            int maxFilesLength = filesArray.length();
            Random rand = new Random();
            int randomIndex = rand.nextInt(maxFilesLength);
            JSONObject fileObj = (JSONObject) filesArray.opt(randomIndex);

            StockFile stockFile = response.getFiles().get(randomIndex);

            // Tests
            Assert.assertEquals(stockFile.getId(),
                    checkNull(fileObj.opt("id")));
            Assert.assertEquals(stockFile.getTitle(),
                    checkNull(fileObj.opt("title")));
            Assert.assertEquals(stockFile.getCreatorId(),
                    checkNull(fileObj.opt("creator_id")));
            Assert.assertEquals(stockFile.getCreatorName(),
                    checkNull(fileObj.opt("creator_name")));
            if (!fileObj.isNull("creation_date")) {
                try {
                    SimpleDateFormat format = new SimpleDateFormat(
                            "yyyy-MM-dd hh:mm:ss.SSS");
                    Date date = format
                            .parse((String) fileObj.opt("creation_date"));

                    Assert.assertEquals(stockFile.getCreationDate(), date);
                } catch (Exception e) {
                    Assert.fail("Didn't expect the Exception!");
                }
            }
            Assert.assertEquals(stockFile.getCountryName(),
                    checkNull(fileObj.opt("country_name")));
            Assert.assertEquals(stockFile.getThumbnailUrl(),
                    checkNull(fileObj.opt("thumbnail_url")));
            Assert.assertEquals(stockFile.getThumbnailHtmlTag(),
                    checkNull(fileObj.opt("thumbnail_html_tag")));
            Assert.assertEquals(stockFile.getThumbnailWidth(),
                    checkNull(fileObj.opt("thumbnail_width")));
            Assert.assertEquals(stockFile.getThumbnailHeight(),
                    checkNull(fileObj.opt("thumbnail_height")));

            Assert.assertEquals(stockFile.getThumbnail110Url(),
                    checkNull(fileObj.opt("thumbnail_110_url")));
            if (!fileObj.isNull("thumbnail_110_width"))
                Assert.assertEquals(stockFile.getThumbnail110Width(),
                        fileObj.getDouble("thumbnail_110_width"));
            Assert.assertEquals(stockFile.getThumbnail110Height(),
                    checkNull(fileObj.opt("thumbnail_110_height")));

            Assert.assertEquals(stockFile.getThumbnail160Url(),
                    checkNull(fileObj.opt("thumbnail_160_url")));
            if (!fileObj.isNull("thumbnail_160_width"))
                Assert.assertEquals(stockFile.getThumbnail160Width(),
                        fileObj.getDouble("thumbnail_160_width"));
            Assert.assertEquals(stockFile.getThumbnail160Height(),
                    checkNull(fileObj.opt("thumbnail_160_height")));

            Assert.assertEquals(stockFile.getThumbnail220Url(),
                    checkNull(fileObj.opt("thumbnail_220_url")));
            if (!fileObj.isNull("thumbnail_220_width"))
                Assert.assertEquals(stockFile.getThumbnail220Width(),
                        fileObj.getDouble("thumbnail_220_width"));
            Assert.assertEquals(stockFile.getThumbnail220Height(),
                    checkNull(fileObj.opt("thumbnail_220_height")));

            Assert.assertEquals(stockFile.getThumbnail240Url(),
                    checkNull(fileObj.opt("thumbnail_240_url")));
            if (!fileObj.isNull("thumbnail_240_width"))
                Assert.assertEquals(stockFile.getThumbnail240Width(),
                        fileObj.getDouble("thumbnail_240_width"));
            Assert.assertEquals(stockFile.getThumbnail240Height(),
                    checkNull(fileObj.opt("thumbnail_240_height")));

            Assert.assertEquals(stockFile.getThumbnail500Url(),
                    checkNull(fileObj.opt("thumbnail_500_url")));
            if (!fileObj.isNull("thumbnail_500_width"))
                Assert.assertEquals(stockFile.getThumbnail500Width(),
                        fileObj.getDouble("thumbnail_500_width"));
            Assert.assertEquals(stockFile.getThumbnail500Height(),
                    checkNull(fileObj.opt("thumbnail_500_height")));

            Assert.assertEquals(stockFile.getThumbnail1000Url(),
                    checkNull(fileObj.opt("thumbnail_1000_url")));
            if (!fileObj.isNull("thumbnail_1000_width"))
                Assert.assertEquals(stockFile.getThumbnail1000Width(),
                        fileObj.getDouble("thumbnail_1000_width"));
            Assert.assertEquals(stockFile.getThumbnail1000Height(),
                    checkNull(fileObj.opt("thumbnail_1000_height")));

            Assert.assertEquals(stockFile.getWidth(),
                    checkNull(fileObj.opt("width")));
            Assert.assertEquals(stockFile.getHeight(),
                    checkNull(fileObj.opt("height")));
            if (!fileObj.isNull("is_licenses"))
                Assert.assertEquals(stockFile.getIsLicensed(),
                        fileObj.optString("is_licenses"));
            Assert.assertEquals(stockFile.getCompUrl(),
                    checkNull(fileObj.opt("comp_url")));
            Assert.assertEquals(stockFile.getCompWidth(),
                    checkNull(fileObj.opt("comp_width")));
            Assert.assertEquals(stockFile.getCompHeight(),
                    checkNull(fileObj.opt("comp_height")));
            Assert.assertEquals(stockFile.getNbViews(),
                    checkNull(fileObj.opt("nb_views")));
            Assert.assertEquals(stockFile.getNbDownloads(),
                    checkNull(fileObj.opt("nb_downloads")));

            if (!fileObj.isNull("category")
                    && !(fileObj.opt("category") instanceof JSONArray)) {
                JSONObject category = (JSONObject) fileObj.opt("category");
                Assert.assertNotNull(stockFile.getCategory());
                Assert.assertEquals(stockFile.getCategory().getName(),
                        checkNull(category.opt("name")));
                Assert.assertEquals(stockFile.getCategory().getId(),
                        checkNull(category.opt("id")));
            }

            if (!fileObj.isNull("keywords")) {
                JSONArray keywordsArray = (JSONArray) fileObj.opt("keywords");
                if (keywordsArray.length() > 0) {
                    ArrayList<StockFileKeyword> keywords = stockFile
                            .getKeywords();
                    Assert.assertNotNull(keywords);
                    Assert.assertEquals(keywords.size(),
                            keywordsArray.length());

                    ArrayList<String> keywordsStringList = new ArrayList<String>();
                    for (StockFileKeyword keyword : keywords) {
                        keywordsStringList.add(keyword.getName());
                    }

                    for (int i = 0; i < keywordsArray.length(); i++) {
                        JSONObject keywordObj = (JSONObject) keywordsArray
                                .opt(i);
                        Assert.assertTrue(keywordsStringList
                                .contains(keywordObj.opt("name")));
                    }
                }
            }
            if (!fileObj.isNull("has_releases"))
                Assert.assertEquals(stockFile.getHasReleases() ? "1":"0",
                        fileObj.optString("has_releases"));
            if (!fileObj.isNull("media_type_id"))
                Assert.assertEquals(stockFile.getAssetTypeId().toString(),
                        fileObj.optString("media_type_id"));

            Assert.assertEquals(stockFile.getVectorType(),
                    checkNull(fileObj.opt("vector_type")));
            Assert.assertEquals(stockFile.getContentType(),
                    checkNull(fileObj.opt("content_type")));
            if (!fileObj.isNull("framerate"))
                Assert.assertEquals(stockFile.getFrameRate(),
                        fileObj.getDouble("framerate"));
            Assert.assertEquals(stockFile.getDuration(),
                    checkNull(fileObj.opt("duration")));
            Assert.assertEquals(stockFile.getStockId(),
                    checkNull(fileObj.opt("stock_id")));

            if (!fileObj.isNull("comps")) {
                JSONObject compsObj = (JSONObject) fileObj.opt("comps");
                StockFileComps stockFileComps = stockFile.getComps();
                Assert.assertNotNull(stockFileComps);

                if (!compsObj.isNull("Standard")) {
                    JSONObject standard = (JSONObject) compsObj.opt("Standard");
                    Assert.assertNotNull(stockFileComps.getStandard());
                    Assert.assertEquals(checkNull(standard.opt("width")),
                            stockFileComps.getStandard().getWidth());
                    Assert.assertEquals(checkNull(standard.opt("height")),
                            stockFileComps.getStandard().getHeight());
                    Assert.assertEquals(checkNull(standard.opt("url")),
                            stockFileComps.getStandard().getUrl());
                }

                if (!compsObj.isNull("Video_HD")) {
                    JSONObject videoHD = (JSONObject) compsObj.opt("Video_HD");
                    Assert.assertNotNull(stockFileComps.getVideoHD());
                    Assert.assertEquals(checkNull(videoHD.opt("width")),
                            stockFileComps.getVideoHD().getWidth());
                    Assert.assertEquals(checkNull(videoHD.opt("height")),
                            stockFileComps.getVideoHD().getHeight());
                    Assert.assertEquals(checkNull(videoHD.opt("url")),
                            stockFileComps.getVideoHD().getUrl());
                }

                if (!compsObj.isNull("Video_4K")) {
                    JSONObject video4K = (JSONObject) compsObj.opt("Video_4K");
                    Assert.assertNotNull(stockFileComps.getVideo4K());
                    Assert.assertEquals(checkNull(video4K.opt("width")),
                            stockFileComps.getVideo4K().getWidth());
                    Assert.assertEquals(checkNull(video4K.opt("height")),
                            stockFileComps.getVideo4K().getHeight());
                    Assert.assertEquals(checkNull(video4K.opt("url")),
                            stockFileComps.getVideo4K().getUrl());
                }
            }

            Assert.assertEquals(stockFile.getDetailsUrl(),
                    checkNull(fileObj.opt("details_url")));
            if (!fileObj.isNull("template_type_id"))
                Assert.assertEquals(stockFile.getTemplateTypeId().toString(),
                        fileObj.optString("template_type_id"));

            if (!fileObj.isNull("template_category_ids")) {

                JSONArray tempCategoryIds = (JSONArray) fileObj
                        .opt("template_category_ids");
                ArrayList<AssetTemplateCategory> stockTempCategoryIdList = stockFile
                        .getTemplateCategoryIds();
                Assert.assertNotNull(stockTempCategoryIdList);
                Assert.assertEquals(stockTempCategoryIdList.size(),
                        tempCategoryIds.length());

                for (int i = 0; i < tempCategoryIds.length(); i++) {
                    Assert.assertTrue(stockTempCategoryIdList
                            .contains(AssetTemplateCategory
                                    .fromString(tempCategoryIds.optString(i))));
                }
            }

            Assert.assertEquals(stockFile.getMarketingText(),
                    checkNull(fileObj.opt("marketing_text")));
            Assert.assertEquals(stockFile.getDescription(),
                    checkNull(fileObj.opt("description")));
            Assert.assertEquals(stockFile.getSizeBytes(),
                    checkNull(fileObj.opt("size_bytes")));
            if (!fileObj.isNull("premium_level_id"))
                Assert.assertEquals(stockFile.getPremiumLevelId().toString(),
                        fileObj.optString("premium_level_id"));
            Assert.assertEquals(stockFile.getIsPremium(),
                    checkNull(fileObj.opt("is_premium")));

            if (!fileObj.isNull("licenses")) {

                JSONObject licensesObj = (JSONObject) fileObj.opt("licenses");
                StockFileLicenses stockFileLicenses = stockFile.getLicenses();
                Assert.assertNotNull(stockFileLicenses);

                if (!licensesObj.isNull("Standard")) {
                    JSONObject standard = (JSONObject) licensesObj
                            .opt("Standard");
                    Assert.assertNotNull(stockFileLicenses.getStandard());
                    Assert.assertEquals(checkNull(standard.opt("width")),
                            stockFileLicenses.getStandard().getWidth());
                    Assert.assertEquals(checkNull(standard.opt("height")),
                            stockFileLicenses.getStandard().getHeight());
                    Assert.assertEquals(checkNull(standard.opt("url")),
                            stockFileLicenses.getStandard().getUrl());
                }

                if (!licensesObj.isNull("Standard-M")) {
                    JSONObject standardM = (JSONObject) licensesObj
                            .opt("Standard-M");
                    Assert.assertNotNull(stockFileLicenses.getStandardM());
                    Assert.assertEquals(checkNull(standardM.opt("width")),
                            stockFileLicenses.getStandardM().getWidth());
                    Assert.assertEquals(checkNull(standardM.opt("height")),
                            stockFileLicenses.getStandardM().getHeight());
                    Assert.assertEquals(checkNull(standardM.opt("url")),
                            stockFileLicenses.getStandardM().getUrl());
                }
            }

            Assert.assertEquals(stockFile.getVideoPreviewUrl(),
                    checkNull(fileObj.opt("video_preview_url")));
            Assert.assertEquals(stockFile.getVideoPreviewHeight(),
                    checkNull(fileObj.opt("video_preview_height")));
            Assert.assertEquals(stockFile.getVideoPreviewWidth(),
                    checkNull(fileObj.opt("video_preview_width")));
            Assert.assertEquals(stockFile.getVideoPreviewContentLength(),
                    checkNull(fileObj.opt("video_preview_content_length")));
            Assert.assertEquals(stockFile.getVideoPreviewContentType(),
                    checkNull(fileObj.opt("video_preview_content_type")));
            Assert.assertEquals(stockFile.getVideoSmallPreviewUrl(),
                    checkNull(fileObj.opt("video_small_preview_url")));
            Assert.assertEquals(stockFile.getVideoSmallPreviewHeight(),
                    checkNull(fileObj.opt("video_small_preview_height")));
            Assert.assertEquals(stockFile.getVideoSmallPreviewWidth(),
                    checkNull(fileObj.opt("video_small_preview_width")));
            Assert.assertEquals(stockFile.getVideoSmallPreviewContentLength(),
                    checkNull(
                            fileObj.opt("video_small_preview_content_length")));
            Assert.assertEquals(stockFile.getVideoSmallPreviewContentType(),
                    checkNull(fileObj.opt("video_small_preview_content_type")));

        }
    }

    private Object checkNull(Object obj) {
        if (obj == JSONObject.NULL) {
            return null;
        }
        return obj;
    }
    
    @Test
    public void JsonUtils_instance_should_be_created_using_reflection()
            throws NoSuchMethodException, IllegalAccessException,
            InvocationTargetException, InstantiationException {
        Constructor<JsonUtils> constructor = JsonUtils.class
                .getDeclaredConstructor();
        constructor.setAccessible(true);
        JsonUtils instance = constructor.newInstance();
        Assert.assertNotNull(instance);
    }
}
