package com.adobe.stock.models;

import java.lang.reflect.Field;

import org.testng.Assert;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;

import com.adobe.stock.enums.LicenseHistoryThumbnailSize;

@Listeners({ com.adobe.stock.logger.TestCustomLogger.class,
    com.adobe.stock.logger.TestSuiteLogger.class })
@Test(suiteName = "SearchParametersLicenseHistory")
public class SearchParametersLicenseHistoryTest {

    SearchParametersLicenseHistory param = new SearchParametersLicenseHistory();

    @BeforeSuite
    void initialize_instance_of_SearchParametersLicenseHistory_successfully() {
        SearchParametersLicenseHistory param = new SearchParametersLicenseHistory();
        Assert.assertNotNull(param);
    }
    
    @Test(groups = { "Setters" })
    void setLimit_should_set_media_Limit_and_should_return_instanceof_SearchParametersLicenseHistory()
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
    
    @Test(groups = { "Exceptions" }, expectedExceptions = IllegalArgumentException.class, expectedExceptionsMessageRegExp = "Limit should be greator than 1")
    void setLimit_should_throw_exception_if_limit_is_less_than_1() {
        param.setLimit(-1);
    }
    
    @Test(groups = { "Setters" })
    void setOffset_should_set_media_Offset_and_should_return_instanceof_SearchParametersLicenseHistory()
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
    void setOffset_should_throw_exception_if_Offset_is_negative() {
        param.setOffset(-1);
    }
    
    @Test(groups = { "Setters" })
    void setThumbnailSize_should_set_thumbnail_size_and_should_return_instanceof_SearchParametersLicenseHistory()
            throws IllegalAccessException,
            NoSuchFieldException {
        Assert.assertNotNull(param.setThumbnailSize(LicenseHistoryThumbnailSize.BIG));
        Field f = param.getClass().getDeclaredField("mThumbnailSize");
        f.setAccessible(true);
        Assert.assertTrue((f.get(param)).equals(LicenseHistoryThumbnailSize.BIG));
    }

    @Test(groups = { "Getters" })
    void getThumbnailSize_should_return_thumbnail_size_of_Type_LicenseHistoryThumbnailSize()
            throws NoSuchFieldException,
            IllegalAccessException {
        Assert.assertTrue(param.getThumbnailSize() == null);
        Field f = param.getClass().getDeclaredField("mThumbnailSize");
        f.setAccessible(true);
        f.set(param, LicenseHistoryThumbnailSize.BIG);
        Assert.assertEquals(LicenseHistoryThumbnailSize.BIG, param.getThumbnailSize());
    }
}
