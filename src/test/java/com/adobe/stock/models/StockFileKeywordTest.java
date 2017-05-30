package com.adobe.stock.models;

import java.lang.reflect.Field;

import org.testng.Assert;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;

@Listeners({ com.adobe.stock.logger.TestCustomLogger.class,
        com.adobe.stock.logger.TestSuiteLogger.class })
@Test(suiteName = "StockFileKeyword")
public class StockFileKeywordTest {
    StockFileKeyword stockFileKeyword;

    @BeforeSuite()
    void create_StockFileLicenseProp_Instance_successfully() {
        stockFileKeyword = new StockFileKeyword();
    }

    @Test(groups = { "Getters" })
    void getName_should_return_name_of_media_keyword_ofType_String_StockFileKeyword()
            throws NoSuchFieldException, IllegalAccessException {
        Field f = stockFileKeyword.getClass().getDeclaredField("mName");
        f.setAccessible(true);
        f.set(stockFileKeyword, "SomeText");
        Assert.assertTrue(stockFileKeyword.getName().equals("SomeText"));
    }

    @Test(groups = { "Setters" })
    void setName_should_set_name_of_media_keyword_ofType_String_StockFileKeyword()
            throws NoSuchFieldException, IllegalAccessException {
        stockFileKeyword.setName("SomeText");
        Field f = stockFileKeyword.getClass().getDeclaredField("mName");
        f.setAccessible(true);
        Assert.assertTrue(f.get(stockFileKeyword).equals("SomeText"));
    }
}
