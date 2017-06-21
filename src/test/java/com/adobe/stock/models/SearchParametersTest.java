package com.adobe.stock.models;

import java.lang.reflect.Field;

import org.testng.Assert;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;

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

@Listeners({ com.adobe.stock.logger.TestCustomLogger.class,
        com.adobe.stock.logger.TestSuiteLogger.class })
@Test(suiteName = "SearchParameters")
public class SearchParametersTest {

    SearchParameters param = new SearchParameters();

    @BeforeSuite
    void initialize_instance_of_SearchParameters_successfully() {
        SearchParameters param = new SearchParameters();
        Assert.assertNotNull(param);
    }

    @Test(groups = { "Setters" })
    void setCreatorId_should_set_media_creator_id_and_should_return_instanceof_SearchParameters()
            throws IllegalAccessException,
            NoSuchFieldException {
        Assert.assertNotNull(param.setCreatorId(127));
        Field f = param.getClass().getDeclaredField("mCreator");
        f.setAccessible(true);
        Assert.assertTrue((f.get(param)).equals(127));
    }

    @Test(groups = { "Getters" })
    void getCreatorId_should_return_media_creator_id_of_Type_Integer()
            throws NoSuchFieldException,
            IllegalAccessException {
        Assert.assertTrue(param.getCreatorId() == null);
        Field f = param.getClass().getDeclaredField("mCreator");
        f.setAccessible(true);
        f.set(param, 127);
        Assert.assertEquals(127, param.getCreatorId().intValue());
    }

    @Test(groups = { "Exceptions" }, expectedExceptions = IllegalArgumentException.class, expectedExceptionsMessageRegExp = "Should be a valid creator Id")
    void setCreatorId_should_throw_exception_if_creator_Id_is_set_with_negative_value() {
        param.setCreatorId(-1);
    }

    @Test(groups = { "Setters" })
    void setMediaId_should_set_media_Media_id_and_should_return_instanceof_SearchParameters()
            throws IllegalAccessException,
            NoSuchFieldException {
        Assert.assertNotNull(param.setMediaId(127));
        Field f = param.getClass().getDeclaredField("mMediaId");
        f.setAccessible(true);
        Assert.assertTrue((f.get(param)).equals(127));
    }

    @Test(groups = { "Getters" })
    void getMediaId_should_return_media_Media_id_of_Type_Integer()
            throws NoSuchFieldException,
            IllegalAccessException {
        Assert.assertTrue(param.getMediaId() == null);
        Field f = param.getClass().getDeclaredField("mMediaId");
        f.setAccessible(true);
        f.set(param, 127);
        Assert.assertEquals(127, param.getMediaId().intValue());
    }

    @Test(groups = { "Exceptions" }, expectedExceptions = IllegalArgumentException.class, expectedExceptionsMessageRegExp = "Should be a valid Media Id")
    void setMediaId_should_throw_exception_if_Media_Id_is_set_with_negative_value() {
        param.setMediaId(-1);
    }

    @Test(groups = { "Setters" })
    void setModelId_should_set_media_Model_id_and_should_return_instanceof_SearchParameters()
            throws IllegalAccessException,
            NoSuchFieldException {
        Assert.assertNotNull(param.setModelId(127));
        Field f = param.getClass().getDeclaredField("mModelId");
        f.setAccessible(true);
        Assert.assertTrue((f.get(param)).equals(127));
    }

    @Test(groups = { "Getters" })
    void getModelId_should_return_media_Model_id_of_Type_Integer()
            throws NoSuchFieldException,
            IllegalAccessException {
        Assert.assertTrue(param.getModelId() == null);
        Field f = param.getClass().getDeclaredField("mModelId");
        f.setAccessible(true);
        f.set(param, 127);
        Assert.assertEquals(127, param.getModelId().intValue());
    }

    @Test(groups = { "Exceptions" }, expectedExceptions = IllegalArgumentException.class, expectedExceptionsMessageRegExp = "Should be a valid Model Id")
    void setModelId_should_throw_exception_if_Model_Id_is_set_with_negative_value() {
        param.setModelId(-1);
    }

    @Test(groups = { "Setters" })
    void setSerieId_should_set_media_Series_id_and_should_return_instanceof_SearchParameters()
            throws IllegalAccessException,
            NoSuchFieldException {
        Assert.assertNotNull(param.setSerieId(127));
        Field f = param.getClass().getDeclaredField("mSerieId");
        f.setAccessible(true);
        Assert.assertTrue((f.get(param)).equals(127));
    }

    @Test(groups = { "Getters" })
    void getSerieId_should_return_media_Series_id_of_Type_Integer()
            throws NoSuchFieldException,
            IllegalAccessException {
        Assert.assertTrue(param.getSerieId() == null);
        Field f = param.getClass().getDeclaredField("mSerieId");
        f.setAccessible(true);
        f.set(param, 127);
        Assert.assertEquals(127, param.getSerieId().intValue());
    }

    @Test(groups = { "Exceptions" }, expectedExceptions = IllegalArgumentException.class, expectedExceptionsMessageRegExp = "Should be a valid Series Id")
    void setSerieId_should_throw_exception_if_Series_Id_is_set_with_negative_value() {
        param.setSerieId(-1);
    }

    @Test(groups = { "Setters" })
    void setSimilarId_should_set_Similar_id_and_should_return_instanceof_SearchParameters()
            throws IllegalAccessException,
            NoSuchFieldException {
        Assert.assertNotNull(param.setSimilar(127));
        Field f = param.getClass().getDeclaredField("mSimilar");
        f.setAccessible(true);
        Assert.assertTrue((f.get(param)).equals(127));
    }

    @Test(groups = { "Getters" })
    void getSimilarId_should_return_media_Similar_id_of_Type_Integer()
            throws NoSuchFieldException,
            IllegalAccessException {
        Assert.assertTrue(param.getSimilar() == null);
        Field f = param.getClass().getDeclaredField("mSimilar");
        f.setAccessible(true);
        f.set(param, 127);
        Assert.assertEquals(127, param.getSimilar().intValue());
    }

    @Test(groups = { "Exceptions" }, expectedExceptions = IllegalArgumentException.class, expectedExceptionsMessageRegExp = "Should be a valid previous mediaId")
    void setSimilarId_should_throw_exception_if_Similars_Id_is_set_with_negative_value() {
        param.setSimilar(-1);
    }

    @Test(groups = { "Setters" })
    void setCategory_should_set_media_category_and_should_return_instanceof_SearchParameters()
            throws IllegalAccessException,
            NoSuchFieldException {
        Assert.assertNotNull(param.setCategory(127));
        Field f = param.getClass().getDeclaredField("mCategory");
        f.setAccessible(true);
        Assert.assertTrue((f.get(param)).equals(127));
    }

    @Test(groups = { "Getters" })
    void getCategory_should_return_media_category_of_Type_Integer()
            throws NoSuchFieldException,
            IllegalAccessException {
        Assert.assertTrue(param.getCategory() == null);
        Field f = param.getClass().getDeclaredField("mCategory");
        f.setAccessible(true);
        f.set(param, 127);
        Assert.assertEquals(127, param.getCategory().intValue());
    }

    @Test(groups = { "Exceptions" }, expectedExceptions = IllegalArgumentException.class, expectedExceptionsMessageRegExp = "Should be a valid category")
    void setCategory_should_throw_exception_if_category_is_set_with_negative_value() {
        param.setCategory(-1);
    }

    @Test(groups = { "Setters" })
    void setLimit_should_set_media_Limit_and_should_return_instanceof_SearchParameters()
            throws IllegalAccessException,
            NoSuchFieldException {
        Assert.assertNotNull(param.setLimit(33));
        Field f = param.getClass().getDeclaredField("mLimit");
        f.setAccessible(true);
        Assert.assertTrue((f.get(param)).equals(33));
    }

    @Test(groups = { "Getters" })
    void getLimit_should_return_media_Limit_of_Type_Integer()
            throws NoSuchFieldException,
            IllegalAccessException {
        Assert.assertTrue(param.getLimit() == null);
        Field f = param.getClass().getDeclaredField("mLimit");
        f.setAccessible(true);
        f.set(param, 33);
        Assert.assertEquals(33, param.getLimit().intValue());
    }

    @Test(groups = { "Exceptions" }, expectedExceptions = IllegalArgumentException.class, expectedExceptionsMessageRegExp = "Limit should be greator than 1 and less than 64")
    void setLimit_should_throw_exception_if_limit_is_greator_than_64() {
        param.setLimit(200);
    }

    @Test(groups = { "Exceptions" }, expectedExceptions = IllegalArgumentException.class, expectedExceptionsMessageRegExp = "Limit should be greator than 1 and less than 64")
    void setLimit_should_throw_exception_if_limit_is_less_than_1() {
        param.setLimit(-1);
    }

    @Test(groups = { "Setters" })
    void setOffset_should_set_media_Offset_and_should_return_instanceof_SearchParameters()
            throws IllegalAccessException,
            NoSuchFieldException {
        Assert.assertNotNull(param.setOffset(127));
        Field f = param.getClass().getDeclaredField("mOffset");
        f.setAccessible(true);
        Assert.assertTrue((f.get(param)).equals(127));
    }

    @Test(groups = { "Getters" })
    void getOffset_should_return_media_Offset_of_Type_Integer()
            throws NoSuchFieldException,
            IllegalAccessException {
        Assert.assertTrue(param.getOffset() == null);
        Field f = param.getClass().getDeclaredField("mOffset");
        f.setAccessible(true);
        f.set(param, 127);
        Assert.assertEquals(127, param.getOffset().intValue());
    }

    @Test(groups = { "Exceptions" }, expectedExceptions = IllegalArgumentException.class, expectedExceptionsMessageRegExp = "Offset should be between 0 and MaxResults")
    void setOffset_should_throw_exception_if_Offset_is_not_between_1_and_64() {
        param.setOffset(-1);
    }

    @Test(groups = { "Setters" })
    void setWords_should_set_keywords_for_file_search_and_should_return_instanceof_SearchParameters()
            throws IllegalAccessException,
            NoSuchFieldException {
        Assert.assertNotNull(param.setWords("Some Text"));
        Field f = param.getClass().getDeclaredField("mWords");
        f.setAccessible(true);
        Assert.assertTrue(((String) f.get(param)).equals("Some Text"));
    }

    @Test(groups = { "Getters" })
    void getWords_should_rerturn_keywords_for_file_search_of_Type_String()
            throws NoSuchFieldException,
            IllegalAccessException {
        Assert.assertTrue(param.getWords() == null);
        Field f = param.getClass().getDeclaredField("mWords");
        f.setAccessible(true);
        f.set(param, "Some Text");
        Assert.assertEquals(param.getWords(), "Some Text");
    }

    @Test(groups = { "Exceptions" }, expectedExceptions = IllegalArgumentException.class, expectedExceptionsMessageRegExp = "Should not be blank or null values in kewywords field")
    void setWords_should_throw_exception_if_keywords_are_set_to_null() {
        param.setWords(null);
    }

    @Test(groups = { "Exceptions" }, expectedExceptions = IllegalArgumentException.class, expectedExceptionsMessageRegExp = "Should not be blank or null values in kewywords field")
    void setWords_should_throw_exception_if_keywords_are_set_to_empty() {
        param.setWords("");
    }

    @Test(groups = { "Setters" })
    void setSimilarURL_should_set_SimilarURL_for_file_search_and_should_return_instanceof_SearchParameters()
            throws IllegalAccessException,
            NoSuchFieldException {
        Assert.assertNotNull(param.setSimilarURL("Some Text"));
        Field f = param.getClass().getDeclaredField("mSimilarURL");
        f.setAccessible(true);
        Assert.assertTrue(((String) f.get(param)).equals("Some Text"));
    }

    @Test(groups = { "Getters" })
    void getSimilarURL_should_rerturn_SimilarURL_for_file_search_of_Type_String()
            throws NoSuchFieldException,
            IllegalAccessException {
        Assert.assertTrue(param.getSimilarURL() == null);
        Field f = param.getClass().getDeclaredField("mSimilarURL");
        f.setAccessible(true);
        f.set(param, "Some Text");
        Assert.assertEquals(param.getSimilarURL(), "Some Text");
    }

    @Test(groups = { "Exceptions" }, expectedExceptions = IllegalArgumentException.class, expectedExceptionsMessageRegExp = "Should not be blank or null values in similarURL field")
    void setSimilarURL_should_throw_exception_if_SimilarURL_is_set_to_null() {
        param.setSimilarURL(null);
    }

    @Test(groups = { "Exceptions" }, expectedExceptions = IllegalArgumentException.class, expectedExceptionsMessageRegExp = "Should not be blank or null values in similarURL field")
    void setSimilarURL_should_throw_exception_if_SimilarURL_is_set_to_empty() {
        param.setSimilarURL("");
    }

    @Test(groups = { "Setters" })
    void setFilterColors_should_set_Color_Filters_for_file_search_and_should_return_instanceof_SearchParameters()
            throws IllegalAccessException,
            NoSuchFieldException {
        Assert.assertNotNull(param.setFilterColors("Some Text"));
        Field f = param.getClass().getDeclaredField("mFilterColors");
        f.setAccessible(true);
        Assert.assertTrue(((String) f.get(param)).equals("Some Text"));
    }

    @Test(groups = { "Getters" })
    void getFilterColors_should_rerturn_Colors_Filters_for_file_search_of_Type_String()
            throws NoSuchFieldException,
            IllegalAccessException {
        Assert.assertTrue(param.getFilterColors() == null);
        Field f = param.getClass().getDeclaredField("mFilterColors");
        f.setAccessible(true);
        f.set(param, "Some Text");
        Assert.assertEquals(param.getFilterColors(), "Some Text");
    }

    @Test(groups = { "Exceptions" }, expectedExceptions = IllegalArgumentException.class, expectedExceptionsMessageRegExp = "Should not be blank or null values in filterColors field")
    void setFilterColors_should_throw_exception_if_FilterColors_is_set_to_null() {
        param.setFilterColors(null);
    }

    @Test(groups = { "Exceptions" }, expectedExceptions = IllegalArgumentException.class, expectedExceptionsMessageRegExp = "Should not be blank or null values in filterColors field")
    void setFilterColors_should_throw_exception_if_FilterColors_is_set_to_empty() {
        param.setFilterColors("");
    }

    @Test(groups = { "Setters" })
    void setGalleryId_should_set_Gallery_Filters_for_file_search_and_should_return_instanceof_SearchParameters()
            throws IllegalAccessException,
            NoSuchFieldException {
        Assert.assertNotNull(param.setGalleryId("Some Text"));
        Field f = param.getClass().getDeclaredField("mGalleryId");
        f.setAccessible(true);
        Assert.assertTrue(((String) f.get(param)).equals("Some Text"));
    }

    @Test(groups = { "Getters" })
    void getGalleryId_should_rerturn_Gallery_Filters_for_file_search_of_Type_String()
            throws NoSuchFieldException,
            IllegalAccessException {
        Assert.assertTrue(param.getGalleryId() == null);
        Field f = param.getClass().getDeclaredField("mGalleryId");
        f.setAccessible(true);
        f.set(param, "Some Text");
        Assert.assertEquals(param.getGalleryId(), "Some Text");
    }

    @Test(groups = { "Exceptions" }, expectedExceptions = IllegalArgumentException.class, expectedExceptionsMessageRegExp = "Should not be blank or null values in galleryId field")
    void setGalleryId_should_throw_exception_if_GalleryId_is_set_to_null() {
        param.setGalleryId(null);
    }

    @Test(groups = { "Exceptions" }, expectedExceptions = IllegalArgumentException.class, expectedExceptionsMessageRegExp = "Should not be blank or null values in galleryId field")
    void setGalleryId_should_throw_exception_if_GalleryId_is_set_to_empty() {
        param.setGalleryId("");
    }

    @Test(groups = { "Setters" })
    void setFilterAreaPixels_should_set_media_Filter_AreaPixels_and_should_return_instanceof_SearchParameters()
            throws IllegalAccessException,
            NoSuchFieldException {
        Assert.assertNotNull(param.setFilterAreaPixels((long) 127.00));
        Field f = param.getClass().getDeclaredField("mFilterAreaPixels");
        f.setAccessible(true);
        Assert.assertTrue((f.get(param)).equals((long) 127.00));
    }

    @Test(groups = { "Getters" })
    void getFilterAreaPixels_should_return_media_Filter_AreaPixels_of_Type_Long()
            throws NoSuchFieldException,
            IllegalAccessException {
        Assert.assertTrue(param.getFilterAreaPixels() == null);
        Field f = param.getClass().getDeclaredField("mFilterAreaPixels");
        f.setAccessible(true);
        f.set(param, (long) 127.00);
        Assert.assertEquals((long) 127.00, param.getFilterAreaPixels()
                .longValue());
    }

    @Test(groups = { "Exceptions" }, expectedExceptions = IllegalArgumentException.class, expectedExceptionsMessageRegExp = "FilterAreaPixels should be greater than zero")
    void setFilterAreaPixels_should_throw_exception_if_filter_AreaPixels_is_set_with_negative_value() {
        param.setFilterAreaPixels(-1);
    }

    @Test(groups = { "Setters" })
    void setSimilarImage_should_set_media_Filter_SimilarImage_and_should_return_instanceof_SearchParameters()
            throws IllegalAccessException,
            NoSuchFieldException {
        Assert.assertNotNull(param.setSimilarImage(true));
        Field f = param.getClass().getDeclaredField("mSimilarImage");
        f.setAccessible(true);
        Assert.assertTrue((f.get(param)).equals(true));
    }

    @Test(groups = { "Getters" })
    void getSimilarImage_should_return_media_Filter_SimilarImage_of_Type_Boolean()
            throws NoSuchFieldException,
            IllegalAccessException {
        Assert.assertTrue(param.getSimilarImage() == null);
        Field f = param.getClass().getDeclaredField("mSimilarImage");
        f.setAccessible(true);
        f.set(param, true);
        Assert.assertEquals(true, param.getSimilarImage().booleanValue());
    }

    @Test(groups = { "Exceptions" }, expectedExceptions = IllegalArgumentException.class, expectedExceptionsMessageRegExp = "SimilarImage filter should be true or false")
    void setSimilarImage_should_throw_exception_if_SimilarImage_filter_set_to_null() {
        param.setSimilarImage(null);
    }

    @Test(groups = { "Setters" })
    void setFilterContentTypePhoto_should_set_media_Filter_ContentTypePhoto_and_should_return_instanceof_SearchParameters()
            throws IllegalAccessException,
            NoSuchFieldException {
        Assert.assertNotNull(param.setFilterContentTypePhoto(true));
        Field f = param.getClass().getDeclaredField("mFilterContentTypePhoto");
        f.setAccessible(true);
        Assert.assertTrue((f.get(param)).equals(true));
    }

    @Test(groups = { "Getters" })
    void getFilterContentTypePhoto_should_return_media_Filter_ContentTypePhoto_of_Type_Boolean()
            throws NoSuchFieldException,
            IllegalAccessException {
        Assert.assertTrue(param.getFilterContentTypePhoto() == null);
        Field f = param.getClass().getDeclaredField("mFilterContentTypePhoto");
        f.setAccessible(true);
        f.set(param, true);
        Assert.assertEquals(true, param.getFilterContentTypePhoto()
                .booleanValue());
    }

    @Test(groups = { "Exceptions" }, expectedExceptions = IllegalArgumentException.class, expectedExceptionsMessageRegExp = "ContentTypePhoto filter should be true or false")
    void setContentTypePhoto_should_throw_exception_if_ContentTypePhoto_filter_set_to_null() {
        param.setFilterContentTypePhoto(null);
    }

    @Test(groups = { "Setters" })
    void setFilterContentTypeIllustration_should_set_media_Filter_ContentTypeIllustration_and_should_return_instanceof_SearchParameters()
            throws IllegalAccessException,
            NoSuchFieldException {
        Assert.assertNotNull(param.setFilterContentTypeIllustration(true));
        Field f = param.getClass().getDeclaredField(
                "mFilterContentTypeIllustration");
        f.setAccessible(true);
        Assert.assertTrue((f.get(param)).equals(true));
    }

    @Test(groups = { "Getters" })
    void getFilterContentTypeIllustration_should_return_media_Filter_ContentTypeIllustration_of_Type_Boolean()
            throws NoSuchFieldException,
            IllegalAccessException {
        Assert.assertTrue(param.getFilterContentTypeIllustration() == null);
        Field f = param.getClass().getDeclaredField(
                "mFilterContentTypeIllustration");
        f.setAccessible(true);
        f.set(param, true);
        Assert.assertEquals(true, param.getFilterContentTypeIllustration()
                .booleanValue());
    }

    @Test(groups = { "Exceptions" }, expectedExceptions = IllegalArgumentException.class, expectedExceptionsMessageRegExp = "Illustration filter should be true or false")
    void setContentTypeIllustration_should_throw_exception_if_ContentTypeIllustration_filter_set_to_null() {
        param.setFilterContentTypeIllustration(null);
    }

    @Test(groups = { "Setters" })
    void setFilterContentTypeVector_should_set_media_Filter_ContentTypeVector_and_should_return_instanceof_SearchParameters()
            throws IllegalAccessException,
            NoSuchFieldException {
        Assert.assertNotNull(param.setFilterContentTypeVector(true));
        Field f = param.getClass().getDeclaredField("mFilterContentTypeVector");
        f.setAccessible(true);
        Assert.assertTrue((f.get(param)).equals(true));
    }

    @Test(groups = { "Getters" })
    void getFilterContentTypeVector_should_return_media_Filter_ContentTypeVector_of_Type_Boolean()
            throws NoSuchFieldException,
            IllegalAccessException {
        Assert.assertTrue(param.getFilterContentTypeVector() == null);
        Field f = param.getClass().getDeclaredField("mFilterContentTypeVector");
        f.setAccessible(true);
        f.set(param, true);
        Assert.assertEquals(true, param.getFilterContentTypeVector()
                .booleanValue());
    }

    @Test(groups = { "Exceptions" }, expectedExceptions = IllegalArgumentException.class, expectedExceptionsMessageRegExp = "Vector filter should be true or false")
    void setContentTypeVector_should_throw_exception_if_ContentTypeVector_filter_set_to_null() {
        param.setFilterContentTypeVector(null);
    }

    @Test(groups = { "Setters" })
    void setFilterContentTypeVideo_should_set_media_Filter_ContentTypeVideo_and_should_return_instanceof_SearchParameters()
            throws IllegalAccessException,
            NoSuchFieldException {
        Assert.assertNotNull(param.setFilterContentTypeVideo(true));
        Field f = param.getClass().getDeclaredField("mFilterContentTypeVideo");
        f.setAccessible(true);
        Assert.assertTrue((f.get(param)).equals(true));
    }

    @Test(groups = { "Getters" })
    void getFilterContentTypeVideo_should_return_media_Filter_ContentTypeVideo_of_Type_Boolean()
            throws NoSuchFieldException,
            IllegalAccessException {
        Assert.assertTrue(param.getFilterContentTypeVideo() == null);
        Field f = param.getClass().getDeclaredField("mFilterContentTypeVideo");
        f.setAccessible(true);
        f.set(param, true);
        Assert.assertEquals(true, param.getFilterContentTypeVideo()
                .booleanValue());
    }

    @Test(groups = { "Exceptions" }, expectedExceptions = IllegalArgumentException.class, expectedExceptionsMessageRegExp = "Video filter should be true or false")
    void setContentTypeVideo_should_throw_exception_if_ContentTypeVideo_filter_set_to_null() {
        param.setFilterContentTypeVideo(null);
    }

    @Test(groups = { "Setters" })
    void setFilterContentTypeTemplate_should_set_media_Filter_ContentTypeTemplate_and_should_return_instanceof_SearchParameters()
            throws IllegalAccessException,
            NoSuchFieldException {
        Assert.assertNotNull(param.setFilterContentTypeTemplate(true));
        Field f = param.getClass().getDeclaredField(
                "mFilterContentTypeTemplate");
        f.setAccessible(true);
        Assert.assertTrue((f.get(param)).equals(true));
    }

    @Test(groups = { "Getters" })
    void getFilterContentTypeTemplate_should_return_media_Filter_ContentTypeTemplate_of_Type_Boolean()
            throws NoSuchFieldException,
            IllegalAccessException {
        Assert.assertTrue(param.getFilterContentTypeTemplate() == null);
        Field f = param.getClass().getDeclaredField(
                "mFilterContentTypeTemplate");
        f.setAccessible(true);
        f.set(param, true);
        Assert.assertEquals(true, param.getFilterContentTypeTemplate()
                .booleanValue());
    }

    @Test(groups = { "Exceptions" }, expectedExceptions = IllegalArgumentException.class, expectedExceptionsMessageRegExp = "Template filter should be true or false")
    void setContentTypeTemplate_should_throw_exception_if_ContentTypeTemplate_filter_set_to_null() {
        param.setFilterContentTypeTemplate(null);
    }

    @Test(groups = { "Setters" })
    void setFilterContentType3D_should_set_media_Filter_ContentType3D_and_should_return_instanceof_SearchParameters()
            throws IllegalAccessException,
            NoSuchFieldException {
        Assert.assertNotNull(param.setFilterContentType3D(true));
        Field f = param.getClass().getDeclaredField("mFilterContentType3D");
        f.setAccessible(true);
        Assert.assertTrue((f.get(param)).equals(true));
    }

    @Test(groups = { "Getters" })
    void getFilterContentType3D_should_return_media_Filter_ContentType3D_of_Type_Boolean()
            throws NoSuchFieldException,
            IllegalAccessException {
        Assert.assertTrue(param.getFilterContentType3D() == null);
        Field f = param.getClass().getDeclaredField("mFilterContentType3D");
        f.setAccessible(true);
        f.set(param, true);
        Assert.assertEquals(true, param.getFilterContentType3D().booleanValue());
    }

    @Test(groups = { "Exceptions" }, expectedExceptions = IllegalArgumentException.class, expectedExceptionsMessageRegExp = "3D filter should be true or false")
    void setContentType3D_should_throw_exception_if_ContentType3D_filter_set_to_null() {
        param.setFilterContentType3D(null);
    }

    @Test(groups = { "Setters" })
    void setFilterContentTypeAll_should_set_media_Filter_ContentTypeAll_and_should_return_instanceof_SearchParameters()
            throws IllegalAccessException,
            NoSuchFieldException {
        Assert.assertNotNull(param.setFilterContentTypeAll(true));
        Field f = param.getClass().getDeclaredField("mFilterContentTypeAll");
        f.setAccessible(true);
        Assert.assertTrue((f.get(param)).equals(true));
    }

    @Test(groups = { "Getters" })
    void getFilterContentTypeAll_should_return_media_Filter_ContentTypeAll_of_Type_Boolean()
            throws NoSuchFieldException,
            IllegalAccessException {
        Assert.assertTrue(param.getFilterContentTypeAll() == null);
        Field f = param.getClass().getDeclaredField("mFilterContentTypeAll");
        f.setAccessible(true);
        f.set(param, true);
        Assert.assertEquals(true, param.getFilterContentTypeAll()
                .booleanValue());
    }

    @Test(groups = { "Exceptions" }, expectedExceptions = IllegalArgumentException.class, expectedExceptionsMessageRegExp = "Type All filter should be true or false")
    void setContentTypeAll_should_throw_exception_if_ContentTypeAll_filter_set_to_null() {
        param.setFilterContentTypeAll(null);
    }

    @Test(groups = { "Setters" })
    void setFilterEditorial_should_set_media_Filter_Editorial_and_should_return_instanceof_SearchParameters()
            throws IllegalAccessException,
            NoSuchFieldException {
        Assert.assertNotNull(param.setFilterEditorial(true));
        Field f = param.getClass().getDeclaredField("mFilterEditorial");
        f.setAccessible(true);
        Assert.assertTrue((f.get(param)).equals(true));
    }

    @Test(groups = { "Getters" })
    void getFilterEditorial_should_return_media_Filter_Editorial_of_Type_Boolean()
            throws NoSuchFieldException,
            IllegalAccessException {
        Assert.assertTrue(param.getFilterEditorial() == null);
        Field f = param.getClass().getDeclaredField("mFilterEditorial");
        f.setAccessible(true);
        f.set(param, true);
        Assert.assertEquals(true, param.getFilterEditorial().booleanValue());
    }

    @Test(groups = { "Exceptions" }, expectedExceptions = IllegalArgumentException.class, expectedExceptionsMessageRegExp = "Editorial filter should be OFF or ON")
    void setEditorial_should_throw_exception_if_Editorial_filter_set_to_null() {
        param.setFilterEditorial(null);
    }

    @Test(groups = { "Setters" })
    void setFilterOffensive2_should_set_media_Filter_Offensive2_and_should_return_instanceof_SearchParameters()
            throws IllegalAccessException,
            NoSuchFieldException {
        Assert.assertNotNull(param.setFilterOffensive2(true));
        Field f = param.getClass().getDeclaredField("mFilterOffensive2");
        f.setAccessible(true);
        Assert.assertTrue((f.get(param)).equals(true));
    }

    @Test(groups = { "Getters" })
    void getFilterOffensive2_should_return_media_Filter_Offensive2_of_Type_Boolean()
            throws NoSuchFieldException,
            IllegalAccessException {
        Assert.assertTrue(param.getFilterOffensive2() == null);
        Field f = param.getClass().getDeclaredField("mFilterOffensive2");
        f.setAccessible(true);
        f.set(param, true);
        Assert.assertEquals(true, param.getFilterOffensive2().booleanValue());
    }

    @Test(groups = { "Exceptions" }, expectedExceptions = IllegalArgumentException.class, expectedExceptionsMessageRegExp = "Offensive2 filter should be OFF or ON")
    void setOffensive2_should_throw_exception_if_Offensive2_filter_set_to_null() {
        param.setFilterOffensive2(null);
    }

    @Test(groups = { "Setters" })
    void setFilterIsolatedOn_should_set_media_Filter_IsolatedOn_and_should_return_instanceof_SearchParameters()
            throws IllegalAccessException,
            NoSuchFieldException {
        Assert.assertNotNull(param.setFilterIsolatedOn(true));
        Field f = param.getClass().getDeclaredField("mFilterIsolatedOn");
        f.setAccessible(true);
        Assert.assertTrue((f.get(param)).equals(true));
    }

    @Test(groups = { "Getters" })
    void getFilterIsolatedOn_should_return_media_Filter_IsolatedOn_of_Type_Boolean()
            throws NoSuchFieldException,
            IllegalAccessException {
        Assert.assertTrue(param.getFilterIsolatedOn() == null);
        Field f = param.getClass().getDeclaredField("mFilterIsolatedOn");
        f.setAccessible(true);
        f.set(param, true);
        Assert.assertEquals(true, param.getFilterIsolatedOn().booleanValue());
    }

    @Test(groups = { "Exceptions" }, expectedExceptions = IllegalArgumentException.class, expectedExceptionsMessageRegExp = "Isolated filter should be OFF or ON")
    void setIsolatedOn_should_throw_exception_if_IsolatedOn_filter_set_to_null() {
        param.setFilterIsolatedOn(null);
    }

    @Test(groups = { "Setters" })
    void setFilterPanoromicOn_should_set_media_Filter_PanoromicOn_and_should_return_instanceof_SearchParameters()
            throws IllegalAccessException,
            NoSuchFieldException {
        Assert.assertNotNull(param.setFilterPanoromicOn(true));
        Field f = param.getClass().getDeclaredField("mFilterPanoromicOn");
        f.setAccessible(true);
        Assert.assertTrue((f.get(param)).equals(true));
    }

    @Test(groups = { "Getters" })
    void getFilterPanoromicOn_should_return_media_Filter_PanoromicOn_of_Type_Boolean()
            throws NoSuchFieldException,
            IllegalAccessException {
        Assert.assertTrue(param.getFilterPanoromicOn() == null);
        Field f = param.getClass().getDeclaredField("mFilterPanoromicOn");
        f.setAccessible(true);
        f.set(param, true);
        Assert.assertEquals(true, param.getFilterPanoromicOn().booleanValue());
    }

    @Test(groups = { "Exceptions" }, expectedExceptions = IllegalArgumentException.class, expectedExceptionsMessageRegExp = "Panoromic filter should be OFF or ON")
    void setPanoromicOn_should_throw_exception_if_PanoromicOn_filter_set_to_null() {
        param.setFilterPanoromicOn(null);
    }

    @Test(groups = { "Setters" })
    void setThumbnailSize_should_set_media_ThumbnailSize_and_should_return_instanceof_SearchParameters()
            throws IllegalAccessException,
            NoSuchFieldException {
        Assert.assertNotNull(param.setThumbnailSize(AssetThumbSize.BIG));
        Field f = param.getClass().getDeclaredField("mThumbnailSize");
        f.setAccessible(true);
        Assert.assertTrue((f.get(param)).equals(AssetThumbSize.BIG));
    }

    @Test(groups = { "Getters" })
    void getThumbnailSize_should_return_media_Filter_ThumbnailSize_of_Type_AssetThumbSize()
            throws NoSuchFieldException,
            IllegalAccessException {
        Assert.assertTrue(param.getThumbnailSize() == null);
        Field f = param.getClass().getDeclaredField("mThumbnailSize");
        f.setAccessible(true);
        f.set(param, AssetThumbSize.BIG);
        Assert.assertEquals(param.getThumbnailSize(), AssetThumbSize.BIG);
    }

    @Test(groups = { "Exceptions" }, expectedExceptions = IllegalArgumentException.class, expectedExceptionsMessageRegExp = "Thumbnail size should not be null")
    void setThumbnailSize_should_throw_exception_if_ThumbnailSize_set_to_null() {
        param.setThumbnailSize(null);
    }

    @Test(groups = { "Setters" })
    void setFilterOrientation_should_set_media_FilterOrientation_and_should_return_instanceof_SearchParameters()
            throws IllegalAccessException,
            NoSuchFieldException {
        Assert.assertNotNull(param
                .setFilterOrientation(AssetOrientation.SQUARE));
        Field f = param.getClass().getDeclaredField("mFilterOrientation");
        f.setAccessible(true);
        Assert.assertTrue((f.get(param)).equals(AssetOrientation.SQUARE));
    }

    @Test(groups = { "Getters" })
    void getFilterOrientation_should_return_media_Filter_Orientation_of_Type_AssetOrientation()
            throws NoSuchFieldException,
            IllegalAccessException {
        Assert.assertTrue(param.getFilterOrientation() == null);
        Field f = param.getClass().getDeclaredField("mFilterOrientation");
        f.setAccessible(true);
        f.set(param, AssetOrientation.SQUARE);
        Assert.assertEquals(param.getFilterOrientation(),
                AssetOrientation.SQUARE);
    }

    @Test(groups = { "Exceptions" }, expectedExceptions = IllegalArgumentException.class, expectedExceptionsMessageRegExp = "Orientation should not be null")
    void setFilterOrientation_should_throw_exception_if_FilterOrientation_set_to_null() {
        param.setFilterOrientation(null);
    }

    @Test(groups = { "Setters" })
    void setFilterAge_should_set_media_FilterAge_and_should_return_instanceof_SearchParameters()
            throws IllegalAccessException,
            NoSuchFieldException {
        Assert.assertNotNull(param.setFilterAge(AssetAge.ONE_YEAR));
        Field f = param.getClass().getDeclaredField("mFilterAge");
        f.setAccessible(true);
        Assert.assertTrue((f.get(param)).equals(AssetAge.ONE_YEAR));
    }

    @Test(groups = { "Getters" })
    void getFilterAge_should_return_media_Filter_Age_of_Type_AssetAge()
            throws NoSuchFieldException,
            IllegalAccessException {
        Assert.assertTrue(param.getFilterAge() == null);
        Field f = param.getClass().getDeclaredField("mFilterAge");
        f.setAccessible(true);
        f.set(param, AssetAge.ONE_YEAR);
        Assert.assertEquals(param.getFilterAge(), AssetAge.ONE_YEAR);
    }

    @Test(groups = { "Exceptions" }, expectedExceptions = IllegalArgumentException.class, expectedExceptionsMessageRegExp = "Age should not be null")
    void setFilterAge_should_throw_exception_if_FilterAge_set_to_null() {
        param.setFilterAge(null);
    }

    @Test(groups = { "Setters" })
    void setFilterVideoDuration_should_set_media_FilterVideoDuration_and_should_return_instanceof_SearchParameters()
            throws IllegalAccessException,
            NoSuchFieldException {
        Assert.assertNotNull(param
                .setFilterVideoDuration(AssetVideoDuration.THIRTY));
        Field f = param.getClass().getDeclaredField("mFilterVideoDuration");
        f.setAccessible(true);
        Assert.assertTrue((f.get(param))
                .equals(AssetVideoDuration.THIRTY));
    }

    @Test(groups = { "Getters" })
    void getFilterVideoDuration_should_return_media_Filter_VideoDuration_of_Type_AssetVideoDuration()
            throws NoSuchFieldException,
            IllegalAccessException {
        Assert.assertTrue(param.getFilterVideoDuration() == null);
        Field f = param.getClass().getDeclaredField("mFilterVideoDuration");
        f.setAccessible(true);
        f.set(param, AssetVideoDuration.THIRTY);
        Assert.assertEquals(param.getFilterVideoDuration(),
                AssetVideoDuration.THIRTY);
    }

    @Test(groups = { "Exceptions" }, expectedExceptions = IllegalArgumentException.class, expectedExceptionsMessageRegExp = "Video duration should not be null")
    void setFilterVideoDuration_should_throw_exception_if_FilterVideoDuration_set_to_null() {
        param.setFilterVideoDuration(null);
    }

    @Test(groups = { "Setters" })
    void setFilterTemplateTypes_should_set_media_FilterTemplateTypes_and_should_return_instanceof_SearchParameters()
            throws IllegalAccessException,
            NoSuchFieldException {
        AssetTemplatesType[] val = { AssetTemplatesType.AIT };
        Assert.assertNotNull(param.setFilterTemplateTypes(val));
        Field f = param.getClass().getDeclaredField("mFilterTemplateTypes");
        f.setAccessible(true);
        Assert.assertTrue((f.get(param)) != null);
    }

    @Test(groups = { "Getters" })
    void getFilterTemplateTypes_should_return_media_Filter_TemplateTypes_of_Type_AssetTemplatesType()
            throws NoSuchFieldException,
            IllegalAccessException {
        Assert.assertTrue(param.getFilterTemplateTypes() == null);
        Field f = param.getClass().getDeclaredField("mFilterTemplateTypes");
        f.setAccessible(true);
        AssetTemplatesType[] val = { AssetTemplatesType.AIT };
        f.set(param, val);
        Assert.assertEquals(param.getFilterTemplateTypes()[0],
                AssetTemplatesType.AIT);
    }

    @Test(groups = { "Exceptions" }, expectedExceptions = IllegalArgumentException.class, expectedExceptionsMessageRegExp = "Template Types should not be null")
    void setFilterTemplateTypes_should_throw_exception_if_FilterTemplateTypes_set_to_null() {
        param.setFilterTemplateTypes(null);
    }

    @Test(groups = { "Setters" })
    void setFilter3DTypeIds_should_set_media_Filter3DTypeIds_and_should_return_instanceof_SearchParameters()
            throws IllegalAccessException,
            NoSuchFieldException {
        Asset3DType[] val = { Asset3DType.LIGHTS };
        Assert.assertNotNull(param.setFilter3DTypeIds(val));
        Field f = param.getClass().getDeclaredField("mFilter3DTypeIds");
        f.setAccessible(true);
        Assert.assertTrue((f.get(param)) != null);
    }

    @Test(groups = { "Getters" })
    void getFilter3DTypeIds_should_return_media_Filter_3DTypeId_of_Type_Asset3DType()
            throws NoSuchFieldException,
            IllegalAccessException {
        Assert.assertTrue(param.getFilter3DTypeIds() == null);
        Field f = param.getClass().getDeclaredField("mFilter3DTypeIds");
        f.setAccessible(true);
        Asset3DType[] val = { Asset3DType.LIGHTS };
        f.set(param, val);
        Assert.assertEquals(param.getFilter3DTypeIds()[0],
                Asset3DType.LIGHTS);
    }

    @Test(groups = { "Exceptions" }, expectedExceptions = IllegalArgumentException.class, expectedExceptionsMessageRegExp = "3D Types should not be null")
    void setFilter3DTypeIds_should_throw_exception_if_Filter3DTypeIds_set_to_null() {
        param.setFilter3DTypeIds(null);
    }

    @Test(groups = { "Setters" })
    void setFilterTemplateCategoryIds_should_set_media_FilterTemplaterCategoryIds_and_should_return_instanceof_SearchParameters()
            throws IllegalAccessException,
            NoSuchFieldException {
        AssetTemplateCategory[] val = {
                AssetTemplateCategory.ART,
                AssetTemplateCategory.FILM };
        Assert.assertNotNull(param.setFilterTemplateCategoryIds(val));
        Field f = param.getClass().getDeclaredField(
                "mFilterTemplateCategoryIds");
        f.setAccessible(true);
        Assert.assertTrue((f.get(param)) != null);
    }

    @Test(groups = { "Getters" })
    void getFilterTemplateCategoryIds_should_return_media_Filter_TemplateCategoryIds_of_Type_AssetTemplateCategory()
            throws NoSuchFieldException,
            IllegalAccessException {
        Assert.assertTrue(param.getFilterTemplateCategoryIds() == null);
        Field f = param.getClass().getDeclaredField(
                "mFilterTemplateCategoryIds");
        f.setAccessible(true);
        AssetTemplateCategory[] val = {
                AssetTemplateCategory.ART,
                AssetTemplateCategory.FILM };
        f.set(param, val);
        Assert.assertEquals(param.getFilterTemplateCategoryIds()[0],
                AssetTemplateCategory.ART);
    }

    @Test(groups = { "Exceptions" }, expectedExceptions = IllegalArgumentException.class, expectedExceptionsMessageRegExp = "TemplateCategory Ids should not be null")
    void setFilterTemplaterCategoryIds_should_throw_exception_if_FilterTemplaterCategoryIds_set_to_null() {
        param.setFilterTemplateCategoryIds(null);
    }

    @Test(groups = { "Setters" })
    void setOrder_should_set_media_Order_and_should_return_instanceof_SearchParameters()
            throws IllegalAccessException,
            NoSuchFieldException {
        AssetsOrder val = AssetsOrder.NB_DOWNLOADS;
        Assert.assertNotNull(param.setOrder(val));
        Field f = param.getClass().getDeclaredField("mOrder");
        f.setAccessible(true);
        Assert.assertTrue((f.get(param)) != null);
    }

    @Test(groups = { "Getters" })

    void getOrder_should_return_media_Filter_Order_of_Type_AssetsOrder()
            throws NoSuchFieldException,
            IllegalAccessException {
        Assert.assertTrue(param.getOrder() == null);
        Field f = param.getClass().getDeclaredField("mOrder");
        f.setAccessible(true);
        AssetsOrder val = AssetsOrder.NB_DOWNLOADS;
        f.set(param, val);
        Assert.assertEquals(param.getOrder(), AssetsOrder.NB_DOWNLOADS);
    }

    @Test(groups = { "Exceptions" }, expectedExceptions = IllegalArgumentException.class, expectedExceptionsMessageRegExp = "Orders should not be null")
    void setOrder_should_throw_exception_if_Order_set_to_null() {
        param.setOrder(null);
    }

    @Test(groups = { "Setters" })
    void setPremium_should_set_media_FilterTemplaterCategoryIds_and_should_return_instanceof_SearchParameters()
            throws IllegalAccessException,
            NoSuchFieldException {
        Assert.assertNotNull(param.setFilterPremium(AssetPremiumCategory.TRUE));
        Field f = param.getClass().getDeclaredField("mFilterPremium");
        f.setAccessible(true);
        Assert.assertTrue((f.get(param)).equals(AssetPremiumCategory.TRUE));
    }

    @Test(groups = { "Getters" })
    void getFilterPremium_should_return_media_Filter_Premium_of_Type_AssetPremiumCategory()
            throws NoSuchFieldException,
            IllegalAccessException {
        Assert.assertTrue(param.getFilterPremium() == null);
        Field f = param.getClass().getDeclaredField("mFilterPremium");
        f.setAccessible(true);
        f.set(param, AssetPremiumCategory.TRUE);
        Assert.assertEquals(param.getFilterPremium(), AssetPremiumCategory.TRUE);
    }

    @Test(groups = { "Exceptions" }, expectedExceptions = IllegalArgumentException.class, expectedExceptionsMessageRegExp = "Premium Filters should not be null")
    void setFilterPremium_should_throw_exception_if_FilterPremium_set_to_null() {
        param.setFilterPremium(null);
    }

    @Test(groups = { "Setters" })
    void setHasReleases_should_set_media_Filter_HasReleases_and_should_return_instanceof_SearchParameters()
            throws IllegalAccessException,
            NoSuchFieldException {
        Assert.assertNotNull(param
                .setFilterHasReleases(AssetHasReleases.ALL));
        Field f = param.getClass().getDeclaredField("mFilterHasReleases");
        f.setAccessible(true);
        Assert.assertTrue((f.get(param)).equals(AssetHasReleases.ALL));
    }

    @Test(groups = { "Getters" })
    void getFilterHasReleases_should_return_media_Filter_HasReleases_of_Type_AssetHasReleases()
            throws NoSuchFieldException,
            IllegalAccessException {
        Assert.assertTrue(param.getFilterHasReleases() == null);
        Field f = param.getClass().getDeclaredField("mFilterHasReleases");
        f.setAccessible(true);
        f.set(param, AssetHasReleases.ALL);
        Assert.assertEquals(param.getFilterHasReleases(),
                AssetHasReleases.ALL);
    }

    @Test(groups = { "Exceptions" }, expectedExceptions = IllegalArgumentException.class, expectedExceptionsMessageRegExp = "Filter Releases should not be null")
    void setFilterHasReleases_should_throw_exception_if_FilterHasReleases_set_to_null() {
        param.setFilterHasReleases(null);
    }

}
