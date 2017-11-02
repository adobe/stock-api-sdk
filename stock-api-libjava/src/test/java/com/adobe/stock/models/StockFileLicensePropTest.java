package com.adobe.stock.models;

import java.lang.reflect.Field;

import org.testng.Assert;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;

@Listeners({ com.adobe.stock.logger.TestCustomLogger.class,
        com.adobe.stock.logger.TestSuiteLogger.class })
@Test(suiteName = "StockFileLicenseProp")
public class StockFileLicensePropTest {

    StockFileLicenseProp prop;

    @BeforeSuite()
    void create_StockFileLicenseProp_Instance_successfully() {
        prop = new StockFileLicenseProp();
    }

    @Test(groups = { "Getters" })
    void getWidth_should_return_width_of_Type_Integer_StockFileLicenseProp()
            throws NoSuchFieldException, IllegalAccessException {
        Field f = prop.getClass().getDeclaredField("mWidth");
        f.setAccessible(true);
        f.set(prop, 100);
        Assert.assertEquals(100, prop.getWidth().intValue());
    }

    @Test(groups = { "Setters" })
    void setWidth_should_set_width_of_Type_Integer_StockFileLicenseProp()
            throws NoSuchFieldException, IllegalAccessException {
        prop.setWidth(1000);
        Field f = prop.getClass().getDeclaredField("mWidth");
        f.setAccessible(true);
        Assert.assertEquals(1000, f.get(prop));
    }

    @Test(groups = { "Getters" })
    void getHeight_should_return_Height_of_Type_Integer_StockFileLicenseProp()
            throws NoSuchFieldException, IllegalAccessException {
        Field f = prop.getClass().getDeclaredField("mHeight");
        f.setAccessible(true);
        f.set(prop, 100);
        Assert.assertEquals(100, prop.getHeight().intValue());
    }

    @Test(groups = { "Setters" })
    void setHeight_should_set_height_of_Type_Integer_StockFileLicenseProp()
            throws NoSuchFieldException, IllegalAccessException {
        prop.setHeight(1000);
        Field f = prop.getClass().getDeclaredField("mHeight");
        f.setAccessible(true);
        Assert.assertEquals(1000, f.get(prop));
    }

    @Test(groups = { "Getters" })
    void getUrl_should_return_Url_of_Type_String_StockFileLicenseProp()
            throws NoSuchFieldException, IllegalAccessException {
        Field f = prop.getClass().getDeclaredField("mUrl");
        f.setAccessible(true);
        f.set(prop, "www.example.com");
        Assert.assertTrue(prop.getUrl().equals("www.example.com"));
    }

    @Test(groups = { "Setters" })
    void setUrl_should_set_Url_of_Type_String_StockFileLicenseProp()
            throws NoSuchFieldException, IllegalAccessException {
        prop.setUrl("www.example.com");
        Field f = prop.getClass().getDeclaredField("mUrl");
        f.setAccessible(true);
        Assert.assertTrue(f.get(prop).equals("www.example.com"));
    }
}
