package com.adobe.stock.apis;

import java.lang.reflect.Array;
import java.lang.reflect.Field;
import java.net.MalformedURLException;
import java.net.URISyntaxException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;

import org.apache.http.client.utils.URIBuilder;

import com.adobe.stock.annotations.SearchParamURLMapperInternal;
import com.adobe.stock.config.StockConfig;
import com.adobe.stock.enums.ResultColumn;
import com.adobe.stock.exception.StockException;
import com.adobe.stock.models.SearchFilesRequest;
import com.adobe.stock.models.SearchFilesResponse;
import com.adobe.stock.models.SearchParameters;

/**
 * The class defining helper methods for SearchFiles.
 */
final class SearchFilesAPIHelpers {
    /**
     * The locale query parameter for Search/Files api.
     */
    private static final String LOCALE = "locale";
    /**
     * The result columns query parameter for Search/Files api.
     */
    private static final String RESULT_COLUMNS = "result_columns[]";

    /**
     * The prefix for search query parameters for Search/Files api.
     */
    private static final String SEARCH_PARAMS_PREFIX = "search_parameters";

    /**
     * The private default constructor for utility class.
     */
    private SearchFilesAPIHelpers() {
    }

    /**
     * Helper method to insert nb_results column in list of ResultsColumn in
     * SearchFilesRequest request object.
     *
     * @param request
     *            the object of SearchFilesRequest in which nb_results
     *            ResultsColumn needs to be checked
     * @return object of SearchFilesRequest type
     * @see ResultsColumn
     * @see SearchFilesRequest
     */

    static SearchFilesRequest addResultColumnNBResults(
            final SearchFilesRequest request) {
        SearchFilesRequest lReq = (SearchFilesRequest) ModelsUtil
                .deepClone(request);

        // we need result column for hasMoreFiles & nextSearchFiles to work
        // properly
        if (lReq.getResultColumns() != null) {
            List<ResultColumn> list = new ArrayList<ResultColumn>(
                    Arrays.asList(lReq.getResultColumns()));
            list.add(ResultColumn.NB_RESULTS);

            lReq.setResultColumns(list.toArray(new ResultColumn[0]));
        }

        return lReq;
    }

    /**
     * Helper method to validate SearchFilesRequest object and accessToken
     * values.
     *
     * @param accessToken
     *            the access token passed
     * @param request
     *            the object of SearchFilesRequest in which nb_results
     *            ResultColumn needs to be checked
     * @throws StockException
     *             if the request object and accessToken represent invalid state
     * @see StockException
     * @see ResultColumn
     */
    static void validateSearchFileQueryParams(final SearchFilesRequest request,
            final String accessToken) throws StockException {
        if (request.getSearchParams() == null) {
            throw new StockException(
                    "Search parameter must be present in the request object");
        }
        // Check if is_licensed is requested in result_columns
        if (request.getResultColumns() != null
                && Arrays.asList(request.getResultColumns()).contains(
                        ResultColumn.IS_LICENSED) && accessToken == null) {
            throw new StockException(
                    "Access Token missing! Result Column 'is_licensed'"
                            + " requires authentication.");
        }

        if (request.getSearchParams().getSimilarImage() != null
                && request.getSearchParams().getSimilarImage()
                && request.getSimilarImage() == null) {
            throw new StockException(
                    "Image Data missing! Search parameter similar_image"
                            + " requires similar_image in query parameters");
        }
    }

    /**
     * Creates the search request url from search request.
     *
     * @param endpoint
     *            base url string
     * @param request
     *            request object containing search parameters
     * @return url containing all the user search parameters
     * @throws StockException
     *             if request object contains invalid parameters
     * @see SearchFilesRequest
     * @see StockException
     */
    static String createSearchFilesApiUrl(final String endpoint,
            final SearchFilesRequest request) throws StockException {

        try {
            URIBuilder uriBuilder = new URIBuilder(endpoint);
            if (!request.getLocale().isEmpty()) {
                uriBuilder.setParameter(LOCALE, request.getLocale());
            }
            SearchParameters searchParams = request.getSearchParams();
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
                    if (field.getType().isArray()) {
                        Object arrayObj = field.get(searchParams);
                        int length = Array.getLength(arrayObj);
                        for (int i = 0; i < length; i++) {
                            uriBuilder.setParameter(paramName,
                                    Array.get(arrayObj, i).toString());
                        }
                    } else if (paramAnnotation.type().equals(
                            SearchParamURLMapperInternal.BOOLEAN_TO_INTEGER)) {
                        Boolean bool = (Boolean) field.get(searchParams);
                        if (bool) {
                            uriBuilder.setParameter(paramName, "1");
                        } else {
                            uriBuilder.setParameter(paramName, "0");
                        }
                    } else {
                        uriBuilder.setParameter(paramName,
                                field.get(searchParams).toString());
                    }
                }
            }

            ResultColumn[] resultColumns = request.getResultColumns();
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
            throw new StockException("Could not create the search request url");
        }
    }

    /**
     * Method to get http method type based on search request.
     *
     * @param request
     *            object containing search request parameters
     * @return http method type GET or POST
     * @see SearchFilesRequest
     */
    static String getSearchFilesApiHttpMethod(
            final SearchFilesRequest request) {
        String method = HttpUtils.HTTP_GET;
        if ((request.getSearchParams().getSimilarImage() != null)
                && request.getSearchParams().getSimilarImage()) {
            method = HttpUtils.HTTP_POST;
        }
        return method;
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
     * @see SearchFilesResponse
     * @see SearchFilesRequest
     * @see StockConfig
     * @see StockException
     */
    static SearchFilesResponse searchFiles(final StockConfig config,
            final String accessToken, final SearchFilesRequest request)
            throws StockException {
        try {
            String httpMethod = getSearchFilesApiHttpMethod(request);
            String requestURL = createSearchFilesApiUrl(config.getEndpoints()
                    .getSearchFilesEndpoint(), request);
            Map<String, String> headers = ApiUtils.generateCommonAPIHeaders(
                    config, accessToken);
            String responseString = null;
            if (httpMethod == HttpUtils.HTTP_GET) {
                responseString = HttpUtils.doGet(requestURL, headers);
            } else {
                responseString = HttpUtils.doMultiPart(requestURL,
                        request.getSimilarImage(), headers);
            }
            SearchFilesResponse searchResponse = (SearchFilesResponse) JsonUtils
                    .parseJson(SearchFilesResponse.class, responseString);
            return searchResponse;

        } catch (StockException e) {
            throw e;
        }
    }
}

/**
 * SearchFiles api class will allow you to access the Search/Files Stock Api.
 * You can query Adobe Stock for assets that meet your specified search
 * criteria. You can construct the {@link SearchFilesRequest} object to set
 * filters, sort order, set search keywords etc. for the Search/Files api.
 * <p>
 * The SearchFiles provides paginated interface which allows you to call its
 * methods (for e.g. getNextResponse, getPreviousResponse etc.) multiple times
 * to retrieve the subsequent search results in order. It maintains the current
 * state of searchFiles request and initially, the state is pointing to an
 * invalid state of search files results. As soon as, the getNextReponse method
 * is called, it makes Search/Files api call and returns the results with
 * {@link SearchFilesResponse} object. The getNextResponse moves the state to
 * next page and fetch the response for the same. Similarly, the
 * getPreviousResponse and getResponsePage methods can be used to move one page
 * behind and skip to a particular search page index respectively.
 */
public final class SearchFiles {

    /**
     * Error code for SearchFiles invalid state.
     */
    public static final int SEARCH_FILES_RETURN_ERROR = -1;

    /**
     * Default limit for number of files in the results page.
     */
    private static final int DEFAULT_SEARCH_FILES_LIMIT = 32;

    /**
     * Stock api configuration.
     */
    private StockConfig mConfig;
    /**
     * Access token string to be used with api calls.
     */
    private String mAccessToken;
    /**
     * Request object to be used for search files api query.
     */
    private SearchFilesRequest mRequest;

    /**
     * if nb_results is present in user's search files requested result columns.
     */
    private boolean mNBResultsPresent = true;

    /**
     * Stores last search files request.
     */
    private SearchFilesRequest mLastRequest;
    /**
     * Checks if any search files api request is in progress.
     */
    private boolean mApiInProgress;
    /**
     * Stores response from last search files api call.
     */
    private SearchFilesResponse mResponse;

    /**
     * Flag to show if it initial invalid state.
     */
    private boolean mInitialInvalidState = true;

    /**
     * Constructs an api object for {@link SearchFiles}.
     *
     * @param config
     *            config for stock api
     * @param accessToken
     *            ims user access token
     * @param request
     *            object containing search files request parameters
     * @throws StockException
     *             if input parameters are invalid
     * @see SearchFilesRequest
     * @see StockConfig
     * @see StockException
     */
    public SearchFiles(final StockConfig config, final String accessToken,
            final SearchFilesRequest request) throws StockException {
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

        SearchFilesAPIHelpers.validateSearchFileQueryParams(request,
                accessToken);

        if (request.getResultColumns() == null) {
            this.mNBResultsPresent = true;
        } else {
            List<ResultColumn> list = Arrays.asList(request.getResultColumns());
            this.mNBResultsPresent = list.contains(ResultColumn.NB_RESULTS);
        }

        if (!this.mNBResultsPresent) {
            this.mRequest = SearchFilesAPIHelpers
                    .addResultColumnNBResults(request);
        } else {
            this.mRequest = (SearchFilesRequest) ModelsUtil.deepClone(request);
        }

        this.mAccessToken = accessToken;
        this.mApiInProgress = false;

        if (this.mRequest.getSearchParams().getLimit() == null) {
            this.mRequest.getSearchParams()
                    .setLimit(DEFAULT_SEARCH_FILES_LIMIT);
        }

        if (this.mRequest.getSearchParams().getOffset() == null) {
            this.mRequest.getSearchParams().setOffset(0);
        }
    }

    /**
     * Method to store current search files request.
     */
    private void storeSearchRequestState() {
        this.mLastRequest = this.mRequest;
    }

    /**
     * Method to restore last search request.
     */
    private void revertToLastSearchRequestState() {
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
     * @see SearchFilesResponse
     */
    private void doOnSuccess(final SearchFilesResponse response) {
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
        this.revertToLastSearchRequestState();
        this.mApiInProgress = false;
    }

    /**
     * Method to do search files api call.
     *
     * @param request
     *            search request object
     * @return response object from search files api call
     * @throws StockException
     *             if api returns with error
     * @see SearchFilesRequest
     * @see SearchFilesResponse
     * @see StockException
     */
    private SearchFilesResponse doApiCall(final SearchFilesRequest request)
            throws StockException {
        if (this.mApiInProgress) {
            throw new StockException(
                    "Some other search is already in progress!");
        }

        try {
            this.mApiInProgress = true;
            this.storeSearchRequestState();
            this.mRequest = request;
            SearchFilesResponse response = SearchFilesAPIHelpers.searchFiles(
                    this.mConfig, this.mAccessToken, request);
            this.doOnSuccess(response);

        } catch (StockException e) {
            this.doOnError();
            throw e;
        }
        return this.getLastResponse();
    }

    /**
     * Method to get total search files available.
     *
     * @return total search files if available else return error(-1)
     */
    public int totalSearchFiles() {
        if (!this.mInitialInvalidState
                && (this.mResponse.getNbResults() != null)) {
            return this.mResponse.getNbResults();
        }
        return SEARCH_FILES_RETURN_ERROR;
    }

    /**
     * Method to get total search results pages.
     *
     * @return total search results pages if available else return error(-1)
     */
    public int totalSearchPages() {
        if (!this.mInitialInvalidState
                && (this.mResponse.getNbResults() != null)) {
            return (int) Math.ceil((double) this.mResponse.getNbResults()
                    / this.mRequest.getSearchParams().getLimit());
        }
        return SEARCH_FILES_RETURN_ERROR;
    }

    /**
     * Method to get current search results page index.
     *
     * @return index of current search results (page) if available else return
     *         error(-1)
     */
    public int currentSearchPageIndex() {
        if (!this.mInitialInvalidState
                && (this.mResponse.getNbResults() != null)) {
            int offset = this.mRequest.getSearchParams().getOffset();
            return (int) Math.ceil((double) offset
                    / this.mRequest.getSearchParams().getLimit());
        }
        return SEARCH_FILES_RETURN_ERROR;
    }

    /**
     * Method to get response from last api call.
     *
     * @return response object from last api call
     * @see SearchFilesResponse
     */
    public SearchFilesResponse getLastResponse() {
        SearchFilesResponse userResponse = null;
        if (!this.mInitialInvalidState) {
            userResponse = (SearchFilesResponse) ModelsUtil
                    .deepClone(this.mResponse);
            if (!this.mNBResultsPresent) {
                userResponse.setNbResults(null);
            }
        }
        return userResponse;
    }

    /**
     * Method to get next search files response page.
     *
     * @return response from search files api
     * @throws StockException
     *             if results not available or api returns with error
     * @see SearchFilesResponse
     * @see StockException
     */
    public SearchFilesResponse getNextResponse() throws StockException {
        try {

            SearchFilesRequest request = (SearchFilesRequest) ModelsUtil
                    .deepClone(this.mRequest);

            if (!this.mInitialInvalidState) {
                int limit = request.getSearchParams().getLimit();
                int offset = request.getSearchParams().getOffset();

                request.getSearchParams().setOffset(offset + limit);

                if ((this.mResponse.getNbResults() == null)
                        || (request.getSearchParams().getOffset()
                                >= this.mResponse.getNbResults())) {
                    throw new StockException(
                            ("No more search results available!"));
                }
            }

            return this.doApiCall(request);
        } catch (StockException e) {
            throw e;
        }
    }

    /**
     * Method to get to previous search files response page.
     *
     * @return response from search files api
     * @throws StockException
     *             if results not available or api returns with error
     * @see SearchFilesResponse
     * @see StockException
     */
    public SearchFilesResponse getPreviousResponse() throws StockException {
        try {
            SearchFilesRequest request = (SearchFilesRequest) ModelsUtil
                    .deepClone(this.mRequest);

            int offset = request.getSearchParams().getOffset();
            request.getSearchParams().setOffset(
                    offset - request.getSearchParams().getLimit());

            return this.doApiCall(request);

        } catch (IllegalArgumentException e) {
            throw new StockException("No more search results available!");
        } catch (StockException e) {
            throw e;
        }
    }

    /**
     * Method to skip to a specific search files response page.
     *
     * @param pageIndex
     *            (required) the index of response page to skip on
     * @return response for specific search files response page
     * @throws StockException
     *             If arguments are invalid or api returns with error
     * @see SearchFilesResponse
     * @see StockException
     */
    public SearchFilesResponse getResponsePage(final int pageIndex)
            throws StockException {
        try {
            SearchFilesRequest request = (SearchFilesRequest) ModelsUtil
                    .deepClone(this.mRequest);

            int totalPages = this.totalSearchPages();
            if ((pageIndex < 0)
                    || (totalPages != SEARCH_FILES_RETURN_ERROR
                        && pageIndex >= totalPages)) {
                throw new StockException("Page index out of bounds");
            }

            request.getSearchParams().setOffset(
                    pageIndex * request.getSearchParams().getLimit());

            return this.doApiCall(request);
        } catch (StockException e) {
            throw e;
        }
    }

    /**
     * Method to set the ims user access token.
     *
     * @param accessToken
     *            ims user access token to set
     * @throws StockException
     *             if access token is null and 'is_licensed' exists in requested
     *             result columns
     */
    public void setAccessToken(final String accessToken) throws StockException {
        SearchFilesAPIHelpers.validateSearchFileQueryParams(this.mRequest,
                accessToken);

        this.mAccessToken = accessToken;
    }
}
