/*******************************************************************************
 * Copyright 2017 Adobe Systems Incorporated. All rights reserved.
 * This file is licensed to you under the Apache License, Version 2.0
 * (the "License") you may not use this file except in compliance with the
 * License. You may obtain a copy of the License at
 * http://www.apache.org/licenses/LICENSE-2.0
 ******************************************************************************/
package com.adobe.stock.apis;

import java.lang.reflect.Field;
import java.net.MalformedURLException;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Map;

import org.apache.http.client.utils.URIBuilder;

import com.adobe.stock.annotations.SearchParamURLMapperInternal;
import com.adobe.stock.config.StockConfig;
import com.adobe.stock.exception.StockException;
import com.adobe.stock.models.SearchCategoryRequest;
import com.adobe.stock.models.StockFileCategory;

/**
 * The class defining helper methods for SearchCategory.
 */
final class SearchCategoryAPIHelpers {
    /**
     * The private default constructor for utility class.
     */
    private SearchCategoryAPIHelpers() {
    }

    /**
     * Helper method to validate SearchCategoryRequest object for
     * Search/Category API.
     * @param request the object of SearchCategoryRequest which needs
     * to be validated
     * @throws StockException if category id is not present in the request
     * @see SearchCategoryRequest
     * @see StockException
     */
    static void validateSearchCategoryQueryParams(
            final SearchCategoryRequest request) throws StockException {
        if (request.getCategoryId() == null) {
            throw new StockException(
                    "Category id must be present in the search request");
        }
    }
    /**
     * Helper method to create request URL from SearchCategoryRequest object.
     * @param endPoint base URL string
     * @param request the object of SearchCategoryRequest used for creating URL
     * @return URL containing all the parameters from SearchCategoryRequest
     * @throws StockException if request contains invalid parameters
     * @see SearchCategoryRequest
     * @see StockException
     */
    static String createSearchCategoryApiUrl(final String endPoint,
            final SearchCategoryRequest request) throws StockException {
        try {
            new URI(endPoint).toURL();
            URIBuilder uriBuilder = new URIBuilder(endPoint);
            for (Field field : request.getClass().getDeclaredFields()) {
                field.setAccessible(true);
                if (field.get(request) == null) {
                    continue;
                }
                SearchParamURLMapperInternal paramAnnotation = field
                        .getAnnotation(SearchParamURLMapperInternal.class);
                if (paramAnnotation != null) {
                    uriBuilder.setParameter(paramAnnotation.value(),
                            field.get(request).toString());
                }
            }
            String url = uriBuilder.toString();
            return url;
        } catch (NullPointerException | IllegalArgumentException
                | IllegalAccessException | MalformedURLException
                | URISyntaxException e) {
            throw new StockException("Could not create the search request url");
        }
    }
}

/**
 * SearchCategory API class allows you to access Search/Category and
 * Search/CategoryTree stock APIs. Each stock asset is placed into a
 * category that classifies the asset, such as "Travel" or
 * "Hobbies and Leisure" and each category has a unique identifying
 * number, a name, and a path that you can use to access other assets
 * in the same category.<br>
 * You can construct the {@link SearchCategoryRequest} object to specify
 * category identifier and locale information. Then you can call
 *  <b>getCategory</b> method to get information about a category of stock
 * assets in the form of {@link StockFileCategory} object. You can also
 * call <b>getCategoryTree</b> method to retrieve information for zero or more
 * category identifiers.
 */
public final class SearchCategory {
    /**
     * Stock api configuration.
     */
    private StockConfig mConfig;
    /**
     * Constructs an api object for {@link SearchCategory}.
     * @param config stock api configuration
     * @throws StockException if config is null or not initialized
     * @see StockConfig
     * @see StockException
     */
    public SearchCategory(final StockConfig config) throws StockException {
        if (config == null) {
            throw new StockException("Config can't be null");
        }

        this.mConfig = new StockConfig().setApiKey(config.getApiKey())
                .setProduct(config.getProduct())
                .setTargetEnvironment(config.getTargetEnvironment())
                .setProductLocation(config.getProductLocation());
    }
    /**
     * Get information about a category of Stock assets, such as travel
     *  or animals for a specified category identifier, optionally localized.
     * @param request {@link SearchCategoryRequest} object containing
     *  category-id and locale
     * @return {@link StockFileCategory} object containing information about
     *  asset category
     * @throws StockException if request is null/invalid or api returns
     *  with error
     * @see SearchCategoryRequest
     * @see StockFileCategory
     * @see StockException
     */
    public StockFileCategory getCategory(
            final SearchCategoryRequest request)
            throws StockException {
        if (request == null) {
            throw new StockException("Request can't be null");
        }
        SearchCategoryAPIHelpers.validateSearchCategoryQueryParams(request);

        String requestURL = SearchCategoryAPIHelpers.createSearchCategoryApiUrl(
                this.mConfig.getEndpoints().getSearchCategoryEndpoint(),
                request);
        Map<String, String> headers = ApiUtils
                .generateCommonAPIHeaders(this.mConfig, null);
        String responseString = HttpUtils.doGet(requestURL, headers);

        StockFileCategory category = (StockFileCategory) JsonUtils
                .parseJson(StockFileCategory.class, responseString);
        return category;
    }
    /**
     * Get category information for zero or more category identifiers.
     * If you request information without specifying a category,
     *  this returns a list of all stock categories
     * @param request {@link SearchCategoryRequest} object containing
     *  category-id and locale
     * @return ArrayList of {@link StockFileCategory} object each containing
     *  information about asset category
     * @throws StockException if request is null/invalid or api returns
     *  with error
     * @see SearchCategoryRequest
     * @see StockFileCategory
     * @see StockException
     */
    public ArrayList<StockFileCategory> getCategoryTree(
            final SearchCategoryRequest request)
            throws StockException {
        if (request == null) {
            throw new StockException("Request can't be null");
        }

        String requestURL = SearchCategoryAPIHelpers.createSearchCategoryApiUrl(
                this.mConfig.getEndpoints().getSearchCategoryTreeEndpoint(),
                request);
        Map<String, String> headers = ApiUtils
                .generateCommonAPIHeaders(this.mConfig, null);
        String responseString = HttpUtils.doGet(requestURL, headers);

        StockFileCategory[] searchResponse = (StockFileCategory[]) JsonUtils
                .parseJson(StockFileCategory[].class, responseString);
        ArrayList<StockFileCategory> categoryList =
                new ArrayList<StockFileCategory>(Arrays.asList(searchResponse));
        return categoryList;
    }
}
