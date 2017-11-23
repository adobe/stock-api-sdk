/*******************************************************************************
 * Copyright 2017 Adobe Systems Incorporated. All rights reserved.
 * This file is licensed to you under the Apache License, Version 2.0
 * (the "License") you may not use this file except in compliance with the
 * License. You may obtain a copy of the License at
 * http://www.apache.org/licenses/LICENSE-2.0
 *******************************************************************************/
package com.adobe.stock.apis;

import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.Stack;

import org.testng.Assert;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;

import com.adobe.stock.enums.ResultColumn;
import com.adobe.stock.enums.Asset3DType;
import com.adobe.stock.enums.AssetHasReleases;
import com.adobe.stock.enums.AssetOrientation;
import com.adobe.stock.models.SearchFilesRequest;
import com.adobe.stock.models.SearchParameters;

@Listeners({ com.adobe.stock.logger.TestCustomLogger.class,
        com.adobe.stock.logger.TestSuiteLogger.class })
@Test(suiteName = "ModelsUtils")
public class ModelsUtilTest {
    @Test(groups = "ModelsUtils.deepClone")
    void testNullObjects() {
        SearchParameters newObj = (SearchParameters) ModelsUtil.deepClone(null);
        Assert.assertEquals(null, newObj);
    }

    @Test
    public void ModelsUtil_instance_should_be_created_using_reflection()
            throws NoSuchMethodException, IllegalAccessException,
            InvocationTargetException, InstantiationException {
        Constructor<ModelsUtil> constructor = ModelsUtil.class
                .getDeclaredConstructor();
        constructor.setAccessible(true);
        ModelsUtil instance = constructor.newInstance();
        Assert.assertNotNull(instance);
    }

    @Test(groups = "ModelsUtils.deepClone")
    void deepClone_should_clone_Primitives_and_return_cloned_Object()
            throws IllegalAccessException {

        PrimitivesOnly oldObj = new PrimitivesOnly();
        oldObj.initializePrimitives();
        PrimitivesOnly newObj = (PrimitivesOnly) ModelsUtil.deepClone(oldObj);
        Assert.assertNotEquals(oldObj, newObj);
        validateFields(oldObj, newObj);

        oldObj.stringVal = "NewValue";
        newObj.byteVal = 100;
        Assert.assertNotEquals(oldObj.stringVal, newObj.stringVal);
        Assert.assertNotEquals(oldObj.byteVal, newObj.byteVal);
    }

    @Test(groups = "ModelsUtils.deepClone")
    void deepClone_should_clone_Collectionsand_return_cloned_Object()
            throws IllegalAccessException {
        CollectionsOnly oldObj = new CollectionsOnly();
        oldObj.initializeCollections();
        CollectionsOnly newObj = (CollectionsOnly) ModelsUtil.deepClone(oldObj);
        Assert.assertNotEquals(oldObj, newObj);
        Assert.assertEquals(true, validateFields(oldObj, newObj));
        newObj.list.add(1, 10);
        newObj.set.add('z');
        Assert.assertEquals(false, validateFields(oldObj, newObj));
        Assert.assertNotEquals(oldObj.list.get(1), newObj.list.get(1));
        Assert.assertNotEquals(newObj.set.contains('z'),
                oldObj.set.contains('z'));
    }

    @Test(groups = "ModelsUtils.deepClone")
    void deepClone_should_clone_ArraysAndEnums_and_return_cloned_Object()
            throws IllegalAccessException {
        SearchParameters oldObj = new SearchParameters();
        ModelsUtilTest.initializeSearchParameters(oldObj);
        SearchParameters newObj = (SearchParameters) ModelsUtil
                .deepClone(oldObj);
        Assert.assertNotEquals(oldObj, newObj);
        Assert.assertEquals(true, validateFields(oldObj, newObj));
        Asset3DType[] filter3dTypeIds = { Asset3DType.LIGHTS };
        newObj.setFilter3DTypeIds(filter3dTypeIds);
        oldObj.setFilterContentTypeAll(false);
        Assert.assertEquals(false, validateFields(oldObj, newObj));
        Assert.assertNotEquals(oldObj.getFilter3DTypeIds(),
                newObj.getFilter3DTypeIds());
        Assert.assertNotEquals(oldObj.getFilterContentTypeAll(),
                newObj.getFilterContentTypeAll());
    }

    @Test(groups = "ModelsUtils.deepClone")
    void deepClone_should_clone_NestedClasses_WithArrays_Enums_Primitives_and_return_cloned_Object()
            throws IllegalAccessException {
        SearchFilesRequest oldObj = new SearchFilesRequest();
        ModelsUtilTest.initializeSearchFilesObject(oldObj);
        SearchFilesRequest newObj = (SearchFilesRequest) ModelsUtil
                .deepClone(oldObj);
        Assert.assertNotEquals(oldObj, newObj);
        Assert.assertTrue(validateFields(oldObj.getSearchParams(),
                newObj.getSearchParams()));
        Assert.assertTrue(validateEnumArrays(oldObj.getResultColumns(),
                newObj.getResultColumns()));
        newObj.getSearchParams().setFilterIsolatedOn(true);
        Assert.assertFalse(validateFields(oldObj.getSearchParams(),
                newObj.getSearchParams()));
        ResultColumn[] newRes = { ResultColumn.COMPS };
        oldObj.setResultColumns(newRes);
        boolean res = validateEnumArrays(oldObj.getResultColumns(),
                newObj.getResultColumns());
        Assert.assertFalse(res);

    }

    @Test(groups = "ModelsUtils.deepClone")
    void deepClone_should_clone_Arraysof_NestedObjects_and_return_cloned_Object()
            throws IllegalAccessException {
        SearchFilesRequest oldObj1 = new SearchFilesRequest();
        ModelsUtilTest.initializeSearchFilesObject(oldObj1);
        SearchFilesRequest oldObj2 = new SearchFilesRequest();
        ModelsUtilTest.initializeSearchFilesObject(oldObj2);
        oldObj2.getSearchParams().setFilterContentTypePhoto(true);
        SearchFilesRequest[] oldRequestArray = { oldObj1, oldObj2 };
        SearchFilesRequest[] newRequestArray = (SearchFilesRequest[]) ModelsUtil
                .deepClone(oldRequestArray);
        Assert.assertNotEquals(oldRequestArray, newRequestArray);
        Assert.assertTrue(validateFields(oldRequestArray[0].getSearchParams(),
                newRequestArray[0].getSearchParams()));
        newRequestArray[0].getSearchParams().setFilterOrientation(
                AssetOrientation.HORIZONTAL);
        oldRequestArray[0].getSearchParams().setFilterOrientation(
                AssetOrientation.HORIZONTAL);
        Assert.assertNotEquals(oldRequestArray, newRequestArray);
    }

    public boolean validateFields(Object oldObject, Object newObject)
            throws IllegalAccessException {
        for (Field field : oldObject.getClass().getDeclaredFields()) {
            field.setAccessible(true);
            if (field.get(oldObject) == null && field.get(newObject) != null) {
                return false;
            } else if (field.get(oldObject) != null
                    && field.get(newObject) == null) {
                return false;
            } else if ((field.get(oldObject) == null && field.get(newObject) == null)
                    || field.getName().equals("this$0") == true) {
                continue;
            } else if (field.getType().isArray()) {
                if (field.get(oldObject) instanceof Object[]) {
                    if (Arrays.toString((Object[]) field.get(oldObject))
                            .equals(Arrays.toString((Object[]) field
                                    .get(newObject))) == false)
                        return false;
                }
            } else {
                if (field.get(oldObject).equals(field.get(newObject)) == false) {
                    return false;
                }
            }
        }
        return true;
    }

    public boolean validateEnumArrays(ResultColumn[] oldObj,
            ResultColumn[] newObj) {

        return Arrays.equals(oldObj, newObj);
    }

    class PrimitivesOnly {
        byte byteVal;
        short shortVal;
        int intVal;
        long longVal;
        float floatVal;
        double doubleVal;
        boolean booleanVal;
        char c;
        String stringVal;

        void initializePrimitives() {
            this.byteVal = 127;
            this.shortVal = 1;
            this.intVal = 2;
            this.longVal = 3;
            this.floatVal = (float) 4.4;
            this.doubleVal = 5.5;
            this.booleanVal = true;
            this.c = 'A';
            this.stringVal = "TestPrimitives";
        }

    }

    class CollectionsOnly {

        List<Integer> list = new ArrayList<Integer>();
        Set<Character> set = new HashSet<Character>();
        Map<String, String> map = new HashMap<String, String>();
        Stack<Integer> stack = new Stack<Integer>();

        void initializeCollections() {
            for (int i = 0; i < 10; i++) {
                list.add(i);
                set.add((char) (65 + i));
                stack.add(i);

            }
            map.put("key1", "value1");
            map.put("key2", "value2");
            map.put("key3", "value3");
        }

    }

    private static void initializeSearchParameters(SearchParameters params) {
        Asset3DType[] filter3dTypeIds = { Asset3DType.LIGHTS,
                Asset3DType.MATERIALS };
        params.setFilter3DTypeIds(filter3dTypeIds);
        params.setFilterContentTypeAll(true);
        params.setFilterHasReleases(AssetHasReleases.ALL);
    }

    private static void initializeSearchFilesObject(SearchFilesRequest obj) {
        obj.setLocale("enUS");
        SearchParameters searchParams = new SearchParameters();
        ModelsUtilTest.initializeSearchParameters(searchParams);
        ResultColumn[] result = { ResultColumn.CATEGORY_HIERARCHY,
                ResultColumn.COMP_URL, ResultColumn.COUNTRY_NAME };
        obj.setResultColumns(result);
        obj.setSearchParams(searchParams);
    }
}
