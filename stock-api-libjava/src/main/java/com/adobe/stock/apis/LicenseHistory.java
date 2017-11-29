/*******************************************************************************
 * Copyright 2017 Adobe Systems Incorporated. All rights reserved.
 * This file is licensed to you under the Apache License, Version 2.0
 * (the "License") you may not use this file except in compliance with the
 * License. You may obtain a copy of the License at
 * http://www.apache.org/licenses/LICENSE-2.0
 ******************************************************************************/
package com.adobe.stock.apis;

import java.lang.reflect.Array;
import java.lang.reflect.Field;
import java.net.MalformedURLException;
import java.net.URISyntaxException;
import java.util.Map;

import org.apache.http.client.utils.URIBuilder;

import com.adobe.stock.annotations.SearchParamURLMapperInternal;
import com.adobe.stock.config.StockConfig;
import com.adobe.stock.enums.LicenseHistoryResultColumn;
import com.adobe.stock.exception.StockException;
import com.adobe.stock.models.LicenseHistoryRequest;
import com.adobe.stock.models.LicenseHistoryResponse;
import com.adobe.stock.models.SearchParametersLicenseHistory;

/**
 * The class defining helper methods for LicenseHistory.
 */
final class LicenseHistoryAPIHelpers {
    /**
     * The locale query parameter for LicenseHistory api.
     */
    private static final String LOCALE = "locale";
    /**
     * The result columns query parameter for LicenseHistory api.
     */
    private static final String RESULT_COLUMNS = "result_columns[]";

    /**
     * The prefix for search query parameters for LicenseHistory api.
     */
    private static final String SEARCH_PARAMS_PREFIX = "search_parameters";

    /**
     * The private default constructor for utility class.
     */
    private LicenseHistoryAPIHelpers() {
    }

    /**
     * Helper method to validate LicenseHistoryRequest object and accessToken
     * values.
     *
     * @param request
     *            the object of LicenseHistoryRequest
     * @throws StockException
     *             if the request object and accessToken represent invalid state
     * @see StockException
     */
    static void validateLicenseHistoryQueryParams(final LicenseHistoryRequest
            request) throws StockException {
        if (request.getSearchParams() == null) {
            throw new StockException(
                    "Search parameter must be present in the request object");
        }
    }

    /**
     * Creates the url from LicenseHistory request.
     *
     * @param endpoint
     *            base url string
     * @param request
     *            request object containing search parameters
     * @return url containing all the user search parameters
     * @throws StockException
     *             if request object contains invalid parameters
     * @see LicenseHistoryRequest
     * @see StockException
     */
    static String createLicenseHistoryApiUrl(final String endpoint,
            final LicenseHistoryRequest request) throws StockException {

        try {
            URIBuilder uriBuilder = new URIBuilder(endpoint);
            if (!request.getLocale().isEmpty()) {
                uriBuilder.setParameter(LOCALE, request.getLocale());
            }
            SearchParametersLicenseHistory searchParams =
                    request.getSearchParams();
            for (Field field : searchParams.getClass().getDeclaredFields()) {
                field.setAccessible(true);

                if (field.get(searchParams) == null) {
                    continue;
                }

                SearchParamURLMapperInternal paramAnnotation = field
                        .getAnnotation(SearchParamURLMapperInternal.class);
                if (paramAnnotation != null) {
                    String paramName = SEARCH_PARAMS_PREFIX
                            + paramAnnotation.value();
                    uriBuilder.setParameter(paramName,
                            field.get(searchParams).toString());
                }
            }

            LicenseHistoryResultColumn[] resultColumns =
                    request.getResultColumns();
            if (resultColumns != null) {
                for (int i = 0; i < Array.getLength(resultColumns); i++) {
                    uriBuilder.addParameter(RESULT_COLUMNS,
                            resultColumns[i].toString());
                }
            }
            String url = uriBuilder.build().toURL().toString();
            return url;
        } catch (NullPointerException | IllegalArgumentException
                | IllegalAccessException | MalformedURLException
                | URISyntaxException e) {
            throw new StockException("Could not create the LicenseHistory"
                    + " request url");
        }
    }

    /**
     * Method to create and send request to the apis and parse result to
     * response object.
     *
     * @param config
     *            stock api configuration set by the user
     * @param accessToken
     *            access token string to be used with api calls
     * @param request
     *            object containing search request parameters
     * @return response object from the api call
     * @throws StockException
     *             in case of invalid request or api returns with error
     * @see LicenseHistoryResponse
     * @see LicenseHistoryRequest
     * @see StockConfig
     * @see StockException
     */
    static LicenseHistoryResponse licenseHistory(final StockConfig config,
            final String accessToken, final LicenseHistoryRequest request)
            throws StockException {
        String requestURL = createLicenseHistoryApiUrl(config.getEndpoints()
                .getLicenseHistoryEndpoint(), request);
        Map<String, String> headers = ApiUtils.generateCommonAPIHeaders(
                config, accessToken);
        String responseString = null;
        responseString = HttpUtils.doGet(requestURL, headers);
        LicenseHistoryResponse licenseHistoryResponse =
                (LicenseHistoryResponse) JsonUtils.parseJson(
                LicenseHistoryResponse.class, responseString);
        return licenseHistoryResponse;
    }
}
/**
 * The class defining helper methods for LicenseHistory.
 */
public final class LicenseHistory {
    /**
     * Error code for LicenseHistory invalid state.
     */
    public static final int LICENSE_HISTORY_RETURN_ERROR = -1;

    /**
     * Default limit for number of files in the results page.
     */
    private static final int DEFAULT_LICENSE_HISTORY_FILES_LIMIT = 100;

    /**
     * Stock api configuration.
     */
    private StockConfig mConfig;
    /**
     * Access token string to be used with api calls.
     */
    private String mAccessToken;
    /**
     * Request object to be used for LicenseHistory files api query.
     */
    private LicenseHistoryRequest mRequest;
    /**
     * Stores last request.
     */
    private LicenseHistoryRequest mLastRequest;
    /**
     * Checks if any LicenseHistory api request is in progress.
     */
    private boolean mApiInProgress;
    /**
     * Stores response from last LicenseHistory api call.
     */
    private LicenseHistoryResponse mResponse;

    /**
     * Flag to show if it initial invalid state.
     */
    private boolean mInitialInvalidState = true;

    /**
     * Constructs an api object for {@link LicenseHistory}.
     *
     * @param config
     *            config for stock api
     * @param accessToken
     *            ims user access token
     * @param request
     *            object containing LicenseHistory request parameters
     * @throws StockException
     *             if input parameters are invalid
     * @see LicenseHistoryRequest
     * @see StockConfig
     * @see StockException
     */
    public LicenseHistory(final StockConfig config, final String accessToken,
            final LicenseHistoryRequest request) throws StockException {
        if (config == null) {
            throw new StockException("config can't be null");
        }

        this.mConfig = new StockConfig().setApiKey(config.getApiKey())
                .setProduct(config.getProduct())
                .setTargetEnvironment(config.getTargetEnvironment())
                .setProductLocation(config.getProductLocation());

        if (request == null) {
            throw new StockException("request can't be null");
        }

        LicenseHistoryAPIHelpers.validateLicenseHistoryQueryParams(request);

        this.mRequest = (LicenseHistoryRequest) ModelsUtil.deepClone(request);
        this.mAccessToken = accessToken;
        this.mApiInProgress = false;

        if (this.mRequest.getSearchParams().getLimit() == null) {
            this.mRequest.getSearchParams()
                    .setLimit(DEFAULT_LICENSE_HISTORY_FILES_LIMIT);
        }

        if (this.mRequest.getSearchParams().getOffset() == null) {
            this.mRequest.getSearchParams().setOffset(0);
        }
    }

    /**
     * Method to store current license history request.
     */
    private void storeLicenseHistoryRequestState() {
        this.mLastRequest = this.mRequest;
    }

    /**
     * Method to restore last license history request.
     */
    private void revertToLastLicenseHistoryRequestState() {
        if (this.mLastRequest != null) {
            this.mRequest = this.mLastRequest;
        }
        this.mLastRequest = null;
    }

    /**
     * Method to do stuff on api call success.
     *
     * @param response
     *            object from API call
     * @see LicenseHistoryResponse
     */
    private void doOnSuccess(final LicenseHistoryResponse response) {
        this.mResponse = response;
        this.mApiInProgress = false;

        if (this.mInitialInvalidState) {
            this.mInitialInvalidState = false;
        }
    }

    /**
     * Method to do stuff on api call failure.
     */
    private void doOnError() {
        this.revertToLastLicenseHistoryRequestState();
        this.mApiInProgress = false;
    }

    /**
     * Method to do license history api call.
     * @param request
     *            license history request object
     * @return response object from license history api call
     * @throws StockException
     *             if api returns with error
     * @see LicenseHistoryRequest
     * @see LicenseHistoryResponse
     * @see StockException
     */
    private LicenseHistoryResponse doApiCall(
            final LicenseHistoryRequest request) throws StockException {
        if (this.mApiInProgress) {
            throw new StockException(
                    "Some other search is already in progress!");
        }

        try {
            this.mApiInProgress = true;
            this.storeLicenseHistoryRequestState();
            this.mRequest = request;
            LicenseHistoryResponse response = LicenseHistoryAPIHelpers.
                    licenseHistory(this.mConfig, this.mAccessToken, request);
            this.doOnSuccess(response);

        } catch (StockException e) {
            this.doOnError();
            throw e;
        }
        return this.getLastLicenseHistory();
    }

    /**
     * Method to get total license history files available.
     *
     * @return total license history files if available else return error(-1)
     */
    public int getTotalLicenseHistoryFiles() {
        if (!this.mInitialInvalidState
                && (this.mResponse.getNbResults() != null)) {
            return this.mResponse.getNbResults();
        }
        return LICENSE_HISTORY_RETURN_ERROR;
    }

    /**
     * Method to get total license history pages.
     *
     * @return total license history pages if available else return error(-1)
     */
    public int getTotalLicenseHistoryPages() {
        if (!this.mInitialInvalidState
                && (this.mResponse.getNbResults() != null)) {
            return (int) Math.ceil((double) this.mResponse.getNbResults()
                    / this.mRequest.getSearchParams().getLimit());
        }
        return LICENSE_HISTORY_RETURN_ERROR;
    }

    /**
     * Method to get current license history results page index.
     *
     * @return index of current license history results (page) if available else
     *       return error(-1)
     */
    public int currentLicenseHistoryPageIndex() {
        if (!this.mInitialInvalidState
                && (this.mResponse.getNbResults() != null)) {
            int offset = this.mRequest.getSearchParams().getOffset();
            return (int) Math.ceil((double) offset
                    / this.mRequest.getSearchParams().getLimit());
        }
        return LICENSE_HISTORY_RETURN_ERROR;
    }

    /**
     * Method to get response from last api call.
     *
     * @return response object from last api call
     * @see LicenseHistoryResponse
     */
    public LicenseHistoryResponse getLastLicenseHistory() {
        LicenseHistoryResponse userResponse = null;
        if (!this.mInitialInvalidState) {
            userResponse = (LicenseHistoryResponse) ModelsUtil
                    .deepClone(this.mResponse);
        }
        return userResponse;
    }

    /**
     * Method to get next license history response page.
     *
     * @return response from license history api
     * @throws StockException
     *             if results not available or api returns with error
     * @see LicenseHistoryResponse
     * @see StockException
     */
    public LicenseHistoryResponse getNextLicenseHistory() throws
        StockException {
        LicenseHistoryRequest request = (LicenseHistoryRequest) ModelsUtil
                .deepClone(this.mRequest);

        if (!this.mInitialInvalidState) {
            int limit = request.getSearchParams().getLimit();
            int offset = request.getSearchParams().getOffset();

            request.getSearchParams().setOffset(offset + limit);

            if ((this.mResponse.getNbResults() == null)
                    || (request.getSearchParams().getOffset()
                            >= this.mResponse.getNbResults())) {
                throw new StockException(
                        ("No more results available!"));
            }
        }

        return this.doApiCall(request);
    }

    /**
     * Method to get to previous search files response page.
     * @return response from license history api
     * @throws StockException
     *             if results not available or api returns with error
     * @see LicenseHistoryResponse
     * @see StockException
     */
    public LicenseHistoryResponse getPreviousLicenseHistory()
            throws StockException {
        try {
            LicenseHistoryRequest request = (LicenseHistoryRequest) ModelsUtil
                    .deepClone(this.mRequest);

            int offset = request.getSearchParams().getOffset();
            request.getSearchParams().setOffset(
                    offset - request.getSearchParams().getLimit());

            return this.doApiCall(request);

        } catch (IllegalArgumentException e) {
            throw new StockException("No more results available!");
        }
    }

    /**
     * Method to skip to a specific search files response page.
     *
     * @param pageIndex
     *            (required) the index of response page to skip on
     * @return response for specific license history response page
     * @throws StockException
     *             If arguments are invalid or api returns with error
     * @see LicenseHistoryResponse
     * @see StockException
     */
    public LicenseHistoryResponse getLicenseHistoryPage(final int pageIndex)
            throws StockException {
        LicenseHistoryRequest request = (LicenseHistoryRequest) ModelsUtil
                .deepClone(this.mRequest);

        int totalPages = this.getTotalLicenseHistoryPages();
        if ((pageIndex < 0)
                || (totalPages != LICENSE_HISTORY_RETURN_ERROR
                    && pageIndex >= totalPages)) {
            throw new StockException("Page index out of bounds");
        }

        request.getSearchParams().setOffset(
                pageIndex * request.getSearchParams().getLimit());

        return this.doApiCall(request);
    }
}
