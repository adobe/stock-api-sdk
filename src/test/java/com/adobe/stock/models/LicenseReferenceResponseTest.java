/*******************************************************************************
 * Copyright 2017 Adobe Systems Incorporated. All rights reserved.
 * This file is licensed to you under the Apache License, Version 2.0
 * (the "License") you may not use this file except in compliance with the
 * License. You may obtain a copy of the License at
 * http://www.apache.org/licenses/LICENSE-2.0
 *******************************************************************************/
package com.adobe.stock.models;

import java.lang.reflect.Field;

import org.testng.Assert;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;

@Listeners({ com.adobe.stock.logger.TestCustomLogger.class,
    com.adobe.stock.logger.TestSuiteLogger.class })
@Test(suiteName = "LicenseReferenceResponse")
public class LicenseReferenceResponseTest {
    private LicenseReferenceResponse reference;
    
    @BeforeSuite()
    void create_LicenseReferenceResponse_Instance_successfully() {
        reference = new LicenseReferenceResponse();
        Assert.assertNotNull(reference);
    }

    @Test(groups = { "Getters" })
    void getId_should_return_Id_of_Type_Integer_LicenseReferenceResponse()
            throws NoSuchFieldException, IllegalAccessException {
        Field f = reference.getClass().getDeclaredField("mId");
        f.setAccessible(true);
        f.set(reference, 100);
        Assert.assertEquals(100, reference.getId().intValue());
    }

    @Test(groups = { "Setters" })
    void setId_should_set_Id_of_Type_Integer_LicenseReferenceResponse()
            throws NoSuchFieldException, IllegalAccessException {
        reference.setId(100);
        Field f = reference.getClass().getDeclaredField("mId");
        f.setAccessible(true);
        Assert.assertEquals(100, f.get(reference));
    }

    @Test(groups = { "Getters" })
    void getText_should_return_Text_of_Type_String_LicenseReferenceResponse()
            throws NoSuchFieldException, IllegalAccessException {
        Field f = reference.getClass().getDeclaredField("mText");
        f.setAccessible(true);
        f.set(reference, "text");
        Assert.assertTrue(reference.getText().equals("text"));
    }

    @Test(groups = { "Setters" })
    void setText_should_set_Text_of_Type_String_LicenseReferenceResponse()
            throws NoSuchFieldException, IllegalAccessException {
        reference.setText("text");
        Field f = reference.getClass().getDeclaredField("mText");
        f.setAccessible(true);
        Assert.assertTrue(f.get(reference).equals("text"));
    }

    @Test(groups = { "Getters" })
    void getRequired_should_return_required_of_Type_Boolean_LicenseReferenceResponse()
            throws NoSuchFieldException, IllegalAccessException {
        Field f = reference.getClass().getDeclaredField("mRequired");
        f.setAccessible(true);
        f.set(reference, true);
        Assert.assertEquals(true, reference.getRequired().booleanValue());
    }

    @Test(groups = { "Setters" })
    void setRequired_should_set_required_of_Type_Boolean_LicenseReferenceResponse()
            throws NoSuchFieldException, IllegalAccessException {
        reference.setRequired(false);
        Field f = reference.getClass().getDeclaredField("mRequired");
        f.setAccessible(true);
        Assert.assertEquals(false, f.get(reference));
    }
}