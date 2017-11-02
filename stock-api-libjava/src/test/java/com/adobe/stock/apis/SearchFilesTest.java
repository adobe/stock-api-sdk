package com.adobe.stock.apis;

import java.lang.reflect.Field;
import java.util.Arrays;
import java.util.Map;

import org.mockito.Matchers;
import org.mockito.Mockito;
import org.powermock.api.mockito.PowerMockito;
import org.powermock.core.classloader.annotations.PowerMockIgnore;
import org.powermock.core.classloader.annotations.PrepareForTest;
import org.powermock.modules.testng.PowerMockObjectFactory;
import org.testng.Assert;
import org.testng.IObjectFactory;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Listeners;
import org.testng.annotations.ObjectFactory;
import org.testng.annotations.Test;

import com.adobe.stock.config.StockConfig;
import com.adobe.stock.enums.ResultColumn;
import com.adobe.stock.enums.AssetOrientation;
import com.adobe.stock.enums.AssetTemplateCategory;
import com.adobe.stock.enums.AssetTemplatesType;
import com.adobe.stock.exception.StockException;
import com.adobe.stock.models.SearchFilesRequest;
import com.adobe.stock.models.SearchFilesResponse;
import com.adobe.stock.models.SearchParameters;

@PowerMockIgnore({ "javax.management.*", "javax.xml.parsers.*",
        "com.sun.org.apache.xerces.internal.jaxp.*", "ch.qos.logback.*",
        "org.slf4j.*", "javax.net.ssl.*" })
@Listeners({ com.adobe.stock.logger.TestCustomLogger.class,
        com.adobe.stock.logger.TestSuiteLogger.class })
@PrepareForTest({ HttpUtils.class })
@Test(suiteName = "SearchFiles")
public class SearchFilesTest {

    private static final String TEST_RESPONSE = "{\"nb_results\":79247672,\"files\":[{\"id\":148563830,\"title\":\"Red, white, and blue American flag for Memorial day or Veteran\'s day background\"}]}";
    private static final Integer TEST_NB_RESULTS = 79247672,
            TEST_FILE_ID = 148563830, TEST_DEFAULT_LIMIT = 32,
            TEST_TOTAL_PAGES = (TEST_NB_RESULTS / TEST_DEFAULT_LIMIT) + 1;
    private static final String TEST_FILE_TITLE = "Red, white, and blue American flag for Memorial day or Veteran\'s day background";
    private SearchFilesRequest searchRequest;
    private StockConfig config;

    @ObjectFactory
    public IObjectFactory getObjectFactory() {
        return new PowerMockObjectFactory();
    }
    @Test(groups = "SearchFiles.constructor")
    public void SearchFiles_should_return_new_object_of_SearchFiles_class() {
        try {
            ResultColumn[] resultColumns = { ResultColumn.NB_RESULTS,
                    ResultColumn.ID, ResultColumn.TITLE,
                    ResultColumn.IS_LICENSED };
            searchRequest.setResultColumns(resultColumns);
            SearchFiles api = new SearchFiles(config, "abc", searchRequest);
            Assert.assertNotNull(api);
            Field fConfig = SearchFiles.class.getDeclaredField("mConfig");
            fConfig.setAccessible(true);
            StockConfig mConfig = (StockConfig) fConfig.get(api);
            Assert.assertEquals(mConfig.getApiKey(), config.getApiKey());
            Assert.assertEquals(mConfig.getProduct(), config.getProduct());
            Assert.assertEquals(mConfig.getTargetEnvironment(),
                    config.getTargetEnvironment());

            Field fRequest = SearchFiles.class.getDeclaredField("mRequest");
            fRequest.setAccessible(true);
            SearchFilesRequest mRequest = (SearchFilesRequest) fRequest
                    .get(api);
            Assert.assertNotNull(mRequest);
            Assert.assertNotNull(mRequest.getSearchParams());
            Assert.assertEquals(mRequest.getLocale(), "");
            Assert.assertEquals(mRequest.getSimilarImage(), null);

            Assert.assertEquals(mRequest.getResultColumns().length,
                    searchRequest.getResultColumns().length);
            Assert.assertTrue(Arrays.equals(mRequest.getResultColumns(),
                    searchRequest.getResultColumns()));

            Assert.assertEquals(mRequest.getSearchParams().getMediaId(),
                    searchRequest.getSearchParams().getMediaId());
            Assert.assertEquals(mRequest.getSearchParams().getCategory(),
                    searchRequest.getSearchParams().getCategory());
            Assert.assertEquals(mRequest.getSearchParams().getWords(),
                    searchRequest.getSearchParams().getWords());
            Assert.assertEquals(mRequest.getSearchParams().getSimilarImage(),
                    searchRequest.getSearchParams().getSimilarImage());
            Assert.assertEquals(mRequest.getSearchParams()
                    .getFilterOrientation(), searchRequest.getSearchParams()
                    .getFilterOrientation());

            Assert.assertEquals(mRequest.getSearchParams().getFilterColors(),
                    searchRequest.getSearchParams().getFilterColors());
            Assert.assertEquals(mRequest.getSearchParams().getSimilarURL(),
                    searchRequest.getSearchParams().getSimilarURL());
            Assert.assertEquals(
                    mRequest.getSearchParams().getFilter3DTypeIds(),
                    searchRequest.getSearchParams().getFilter3DTypeIds());

        } catch (Exception e) {
            Assert.fail("Didn't expect the exception here!", e);
        }
    }

    @Test(groups = "SearchFiles.constructor")
    public void SearchFiles_should_throw_stockexception_since_config_parameter_is_null() {
        try {
            new SearchFiles(null, "", searchRequest);
            Assert.fail("StockException was expected since the config parameter in null!");
        } catch (StockException e) {
            Assert.assertEquals(e.getMessage(), "config can't be null");
        }
    }

    @Test(groups = "SearchFiles.constructor")
    public void SearchFiles_should_throw_stockexception_since_searchRequest_parameter_is_null() {
        try {
            new SearchFiles(config, "", null);
            Assert.fail("StockException was expected since the search files request parameter in null!");
        } catch (StockException e) {
            Assert.assertEquals(e.getMessage(), "request can't be null");
        }
    }

    @Test(groups = "SearchFiles.constructor")
    public void SearchFiles_should_throw_stockexception_since_searchparameters_field_of_request_parameter_is_null() {
        try {
            new SearchFiles(config, "",
                    new SearchFilesRequest().setLocale("en-US"));
            Assert.fail("StockException was expected since the search_parameters is null in request parameter!");
        } catch (StockException e) {
            Assert.assertEquals(e.getMessage(),
                    "Search parameter must be present in the request object");
        }
    }

    @Test(groups = "SearchFiles.constructor")
    public void SearchFiles_should_throw_stockexception_since_is_licensed_requested_in_results_and_access_token_is_null() {
        try {
            ResultColumn[] resultColumns = { ResultColumn.NB_RESULTS,
                    ResultColumn.ID, ResultColumn.TITLE,
                    ResultColumn.IS_LICENSED };
            new SearchFiles(config, null,
                    searchRequest.setResultColumns(resultColumns));
            Assert.fail("StockException was expected since the access token is null and is_licensed result column is requested");
        } catch (StockException e) {
            Assert.assertEquals(e.getMessage(),
                    "Access Token missing! Result Column 'is_licensed' requires authentication.");
        }
    }

    @Test(groups = "SearchFiles.constructor")
    public void SearchFiles_should_throw_stockexception_since_search_parameters_similar_image_is_true_but_similar_image_data_is_null() {
        try {
            new SearchFiles(config, null,
                    new SearchFilesRequest()
                            .setSearchParams(new SearchParameters()
                                    .setSimilarImage(true)));
            Assert.fail("StockException was expected since the similar image search parameter is true and similar image data is null");
        } catch (StockException e) {
            Assert.assertEquals(
                    e.getMessage(),
                    "Image Data missing! Search parameter similar_image requires similar_image in query parameters");
        }
    }

    @BeforeMethod
    public void beforeEachMethod() {
        try {
            PowerMockito.mockStatic(HttpUtils.class);
            ResultColumn[] resultColumns = { ResultColumn.NB_RESULTS,
                    ResultColumn.ID, ResultColumn.TITLE };
            config = new StockConfig().setApiKey("TestApiKey").setProduct(
                    "TestProduct");
            SearchParameters params = new SearchParameters().setMediaId(2349)
                    .setWords("Sample Example").setCategory(10)
                    .setSimilarImage(false)
                    .setFilterOrientation(AssetOrientation.HORIZONTAL);
            searchRequest = new SearchFilesRequest().setSearchParams(params)
                    .setResultColumns(resultColumns);

        } catch (Exception e) {
            Assert.fail("Didn't expect the Exception!", e);
        }
    }

    private static void checkTestResponse(SearchFilesResponse response) {
        Assert.assertNotNull(response);
        Assert.assertEquals(response.getNbResults(), TEST_NB_RESULTS);
        Assert.assertEquals(response.getFiles().get(0).getId(), TEST_FILE_ID);
        Assert.assertEquals(response.getFiles().get(0).getTitle(),
                TEST_FILE_TITLE);
    }

    @Test(groups = "SearchFiles.getNextResponse")
    public void getNextResponse_should_return_valid_response() {
        try {

            SearchFiles searchFiles = new SearchFiles(config, null,
                    searchRequest);

            PowerMockito.when(
                    HttpUtils.doGet(Mockito.anyString(),
                            Matchers.<Map<String, String>> any())).thenReturn(
                    TEST_RESPONSE);

            SearchFilesResponse response = searchFiles.getNextResponse();
            SearchFilesTest.checkTestResponse(response);
            Assert.assertEquals(searchFiles.currentSearchPageIndex(), 0);
        } catch (StockException e) {
            Assert.fail("Didn't expect the StockException!", e);
        }
    }

    @Test(groups = "SearchFiles.getNextResponse", expectedExceptions = { StockException.class }, expectedExceptionsMessageRegExp = "No more search results available!")
    public void getNextResponse_should_throw_exception_since_offset_exceed_result_count()
            throws StockException {

        searchRequest.getSearchParams().setOffset(
                TEST_NB_RESULTS - TEST_DEFAULT_LIMIT);
        SearchFiles searchFiles = new SearchFiles(config, null, searchRequest);

        PowerMockito.when(
                HttpUtils.doGet(Mockito.anyString(),
                        Matchers.<Map<String, String>> any())).thenReturn(
                TEST_RESPONSE);
        searchFiles.getNextResponse();
        // calling next again will exceed results count limit
        searchFiles.getNextResponse();
    }

    @Test(groups = "SearchFiles.getNextResponse")
    public void getNextResponse_should_revert_to_last_request_state_when_search_api_fails() {
        try {
            searchRequest.getSearchParams().setLimit(10);
            SearchFiles searchFiles = new SearchFiles(config, null,
                    searchRequest);

            PowerMockito.when(
                    HttpUtils.doGet(Mockito.anyString(),
                            Matchers.<Map<String, String>> any())).thenReturn(
                    TEST_RESPONSE);

            SearchFilesResponse response = searchFiles.getNextResponse();
            SearchFilesTest.checkTestResponse(response);
            Assert.assertEquals(searchFiles.currentSearchPageIndex(), 0);

            PowerMockito.when(
                    HttpUtils.doGet(Mockito.anyString(),
                            Matchers.<Map<String, String>> any())).thenThrow(
                    new StockException("API returned with Server Error"));
            try {
                response = searchFiles.getNextResponse();
                Assert.fail("Should have thrown StockException");
            } catch (StockException e) {
                Assert.assertEquals(searchFiles.currentSearchPageIndex(), 0);
                SearchFilesTest
                        .checkTestResponse(searchFiles.getLastResponse());
            }
            PowerMockito.mockStatic(HttpUtils.class);
            PowerMockito.when(
                    HttpUtils.doGet(Mockito.anyString(),
                            Matchers.<Map<String, String>> any())).thenReturn(
                    TEST_RESPONSE);
            response = searchFiles.getNextResponse();
            SearchFilesTest.checkTestResponse(response);
            Assert.assertEquals(searchFiles.currentSearchPageIndex(), 1);

        } catch (StockException e) {
            Assert.fail("Didn't expect the StockException!", e);
        }
    }

    @Test(groups = "SearchFiles.getNextResponse", expectedExceptions = { StockException.class }, expectedExceptionsMessageRegExp = "No more search results available!")
    public void getNextResponse_should_throw_exception_when_result_count_zero()
            throws StockException {
        String responseString = "{\"nb_results\":0,\"files\":[] }";

        searchRequest.getSearchParams().setSimilarImage(true);
        searchRequest.setLocale("en-US");
        searchRequest.setSimilarImage(new byte[10]);
        SearchFiles searchFiles = new SearchFiles(config, "accessToken",
                searchRequest);

        PowerMockito.when(
                HttpUtils.doMultiPart(Mockito.anyString(),
                        Mockito.any(byte[].class),
                        Matchers.<Map<String, String>> any())).thenReturn(
                responseString);

        searchFiles.getNextResponse();
        searchFiles.getNextResponse();
    }

    @Test(groups = "SearchFiles.getNextResponse", expectedExceptions = { StockException.class }, expectedExceptionsMessageRegExp = "No more search results available!")
    public void getNextResponse_should_throw_exception_when_results_count_not_present_response()
            throws StockException {
        String responseString = "{}";

        searchRequest.getSearchParams().setSimilarImage(true);
        searchRequest.setSimilarImage(new byte[10]);
        SearchFiles searchFiles = new SearchFiles(config, "accessToken",
                searchRequest);

        PowerMockito.when(
                HttpUtils.doMultiPart(Mockito.anyString(),
                        Mockito.any(byte[].class),
                        Matchers.<Map<String, String>> any())).thenReturn(
                responseString);

        SearchFilesResponse response = searchFiles.getNextResponse();
        Assert.assertEquals(searchFiles.totalSearchFiles(),
                SearchFiles.SEARCH_FILES_RETURN_ERROR);
        Assert.assertEquals(searchFiles.totalSearchPages(),
                SearchFiles.SEARCH_FILES_RETURN_ERROR);
        Assert.assertEquals(searchFiles.currentSearchPageIndex(),
                SearchFiles.SEARCH_FILES_RETURN_ERROR);
        Assert.assertNull(response.getNbResults());
        searchFiles.getNextResponse();

    }

    @Test(groups = "SearchFiles.getPreviousResponse", expectedExceptions = { StockException.class }, expectedExceptionsMessageRegExp = "No more search results available!")
    public void getPreviousResponse_should_throw_exception_when_called_from_first_page()
            throws StockException {
        SearchFiles searchFiles = new SearchFiles(config, null, searchRequest);

        PowerMockito.when(
                HttpUtils.doGet(Mockito.anyString(),
                        Matchers.<Map<String, String>> any())).thenReturn(
                TEST_RESPONSE);

        try {
            searchFiles.getPreviousResponse();
            Assert.fail("Should have thrown StockException");
        } catch (StockException e) {
            Assert.assertEquals(e.getMessage(),
                    "No more search results available!");
        }
        searchFiles.getNextResponse();
        // calling previous again from first page
        searchFiles.getPreviousResponse();
    }

    @Test(groups = "SearchFiles.getPreviousResponse")
    public void getPreviousResponse_should_return_valid_response() {
        try {
            searchRequest.getSearchParams().setOffset(TEST_DEFAULT_LIMIT);
            SearchFiles searchFiles = new SearchFiles(config, null,
                    searchRequest);

            PowerMockito.when(
                    HttpUtils.doGet(Mockito.anyString(),
                            Matchers.<Map<String, String>> any())).thenReturn(
                    TEST_RESPONSE);

            SearchFilesResponse response = searchFiles.getPreviousResponse();

            SearchFilesTest.checkTestResponse(response);
            Assert.assertEquals(searchFiles.currentSearchPageIndex(), 0);
        } catch (StockException e) {
            Assert.fail("Didn't expect the StockException!", e);
        }
    }

    @Test(groups = "SearchFiles.getPreviousResponse")
    public void getPreviousResponse_should_revert_to_last_request_state_when_search_api_fails() {
        try {
            int pageIndex = (int) (Math.random() * TEST_TOTAL_PAGES);
            searchRequest.getSearchParams().setOffset(
                    pageIndex * TEST_DEFAULT_LIMIT);
            SearchFiles searchFiles = new SearchFiles(config, null,
                    searchRequest);

            PowerMockito.when(
                    HttpUtils.doGet(Mockito.anyString(),
                            Matchers.<Map<String, String>> any())).thenReturn(
                    TEST_RESPONSE);

            SearchFilesResponse response = searchFiles.getPreviousResponse();
            SearchFilesTest.checkTestResponse(response);
            Assert.assertEquals(searchFiles.currentSearchPageIndex(),
                    pageIndex - 1);

            PowerMockito.when(
                    HttpUtils.doGet(Mockito.anyString(),
                            Matchers.<Map<String, String>> any())).thenThrow(
                    new StockException("API returned with Server Error"));
            try {
                response = searchFiles.getPreviousResponse();
                Assert.fail("Should have thrown StockException");
            } catch (StockException e) {
                Assert.assertEquals(searchFiles.currentSearchPageIndex(),
                        pageIndex - 1);
                SearchFilesTest
                        .checkTestResponse(searchFiles.getLastResponse());
            }
            PowerMockito.mockStatic(HttpUtils.class);
            PowerMockito.when(
                    HttpUtils.doGet(Mockito.anyString(),
                            Matchers.<Map<String, String>> any())).thenReturn(
                    TEST_RESPONSE);
            response = searchFiles.getPreviousResponse();
            SearchFilesTest.checkTestResponse(response);
            Assert.assertEquals(searchFiles.currentSearchPageIndex(),
                    pageIndex - 2);

        } catch (StockException e) {
            Assert.fail("Didn't expect the StockException!", e);
        }
    }

    @Test(groups = "SearchFiles.getResponsePage", expectedExceptions = { StockException.class }, expectedExceptionsMessageRegExp = "Page index out of bounds")
    public void getResponsePage_should_throw_exception_when_invalid_pageindex()
            throws StockException {
        SearchFiles searchFiles = new SearchFiles(config, null, searchRequest);

        PowerMockito.when(
                HttpUtils.doGet(Mockito.anyString(),
                        Matchers.<Map<String, String>> any())).thenReturn(
                TEST_RESPONSE);
        int pageIndex = -1;
        searchFiles.getNextResponse();
        try {
            searchFiles.getResponsePage(pageIndex);
            Assert.fail("Should have thrown StockException");
        } catch (StockException e) {
            Assert.assertEquals("Page index out of bounds", e.getMessage());
        }
        searchFiles.getResponsePage(TEST_TOTAL_PAGES);
    }

    @Test(groups = "SearchFiles.getResponsePage", expectedExceptions = { StockException.class }, expectedExceptionsMessageRegExp = "Could not create the search request url")
    public void getResponsePage_should_throw_exception_when_null_values_present_search_parameter()
            throws StockException {
        AssetTemplateCategory[] categories = {
                AssetTemplateCategory.MOBILE,
                AssetTemplateCategory.PHOTO, null };
        searchRequest.getSearchParams()
                .setFilterTemplateCategoryIds(categories);
        SearchFiles searchFiles = new SearchFiles(config, null, searchRequest);

        PowerMockito.when(
                HttpUtils.doGet(Mockito.anyString(),
                        Matchers.<Map<String, String>> any())).thenReturn(
                TEST_RESPONSE);
        int pageIndex = (int) (Math.random() * TEST_TOTAL_PAGES);
        searchFiles.getResponsePage(pageIndex);

    }

    @Test(groups = "SearchFiles.getResponsePage")
    public void getResponsePage_should_return_valid_response() {
        try {
            AssetTemplatesType[] templates = {
                    AssetTemplatesType.PSDT,
                    AssetTemplatesType.PSDT };
            searchRequest.getSearchParams().setFilterTemplateTypes(templates);
            searchRequest.getSearchParams().setFilterPanoromicOn(true);
            SearchFiles searchFiles = new SearchFiles(config, null,
                    searchRequest);

            PowerMockito.when(
                    HttpUtils.doGet(Mockito.anyString(),
                            Matchers.<Map<String, String>> any())).thenReturn(
                    TEST_RESPONSE);

            int pageIndex = (int) (Math.random() * TEST_TOTAL_PAGES);
            SearchFilesResponse response = searchFiles
                    .getResponsePage(pageIndex);

            SearchFilesTest.checkTestResponse(response);
            Assert.assertEquals(searchFiles.currentSearchPageIndex(), pageIndex);
            SearchFilesTest.checkTestResponse(searchFiles.getLastResponse());
        } catch (StockException e) {
            Assert.fail("Didn't expect the StockException!", e);
        }
    }

    @Test(groups = "SearchFiles.getResponsePage")
    public void getResponsePage_should_revert_to_last_request_state_when_search_api_fails() {
        try {
            int pageIndex = (int) (Math.random() * TEST_TOTAL_PAGES);
            int previousPageIndex = pageIndex;
            SearchFiles searchFiles = new SearchFiles(config, null,
                    searchRequest);

            PowerMockito.when(
                    HttpUtils.doGet(Mockito.anyString(),
                            Matchers.<Map<String, String>> any())).thenReturn(
                    TEST_RESPONSE);

            SearchFilesResponse response = searchFiles
                    .getResponsePage(pageIndex);
            Assert.assertEquals(searchFiles.currentSearchPageIndex(), pageIndex);

            PowerMockito.when(
                    HttpUtils.doGet(Mockito.anyString(),
                            Matchers.<Map<String, String>> any())).thenThrow(
                    new StockException("API returned with Server Error"));
            pageIndex = (int) (Math.random() * TEST_TOTAL_PAGES);
            try {
                response = searchFiles.getResponsePage(pageIndex);
                Assert.fail("Should have thrown StockException");
            } catch (StockException e) {
                SearchFilesTest.checkTestResponse(response);
                Assert.assertEquals(searchFiles.currentSearchPageIndex(),
                        previousPageIndex);
            }

        } catch (StockException e) {
            Assert.fail("Didn't expect the StockException!", e);
        }
    }

    @Test(groups = "SearchFiles.getLastResponse")
    public void getLastResponse_should_not_return_nb_results_when_not_present_result_columns() {
        try {
            ResultColumn[] resultColumns = { ResultColumn.ID,
                    ResultColumn.TITLE };
            searchRequest.setResultColumns(resultColumns);
            SearchFiles searchFiles = new SearchFiles(config, null,
                    searchRequest);

            PowerMockito.when(
                    HttpUtils.doGet(Mockito.anyString(),
                            Matchers.<Map<String, String>> any())).thenReturn(
                    TEST_RESPONSE);

            searchFiles.getNextResponse();
            Assert.assertNull(searchFiles.getLastResponse().getNbResults());
        } catch (StockException e) {
            Assert.fail("Didn't expect the StockException!", e);
        }
    }

    @Test(groups = "SearchFiles.getLastResponse")
    public void getLastResponse_should_return_null_response_without_api_call() {
        try {
            SearchFiles searchFiles = new SearchFiles(config, null,
                    searchRequest);
            Assert.assertNull(searchFiles.getLastResponse());
        } catch (StockException e) {
            Assert.fail("Didn't expect the StockException!", e);
        }
    }

    @Test(groups = "SearchFiles.currentSearchPageIndex")
    public void currentSearchPageIndex_should_return_error_when_called_without_api_call() {
        try {
            SearchFiles searchFiles = new SearchFiles(config, null,
                    searchRequest);
            Assert.assertEquals(searchFiles.currentSearchPageIndex(),
                    SearchFiles.SEARCH_FILES_RETURN_ERROR);
        } catch (StockException e) {
            Assert.fail("Didn't expect the StockException!", e);
        }
    }

    @Test(groups = "SearchFiles.totalSearchFiles")
    public void totalSearchFiles_should_return_error_when_called_without_api_call() {
        try {
            SearchFiles searchFiles = new SearchFiles(config, null,
                    searchRequest);
            Assert.assertEquals(searchFiles.totalSearchFiles(),
                    SearchFiles.SEARCH_FILES_RETURN_ERROR);
        } catch (StockException e) {
            Assert.fail("Didn't expect the StockException!", e);
        }
    }

    @Test(groups = "SearchFiles.totalSearchFiles")
    public void totalSearchFiles_should_return_total_files() {
        try {
            SearchFilesRequest searchRequest = new SearchFilesRequest()
                    .setSearchParams(new SearchParameters());
            SearchFiles searchFiles = new SearchFiles(config, null,
                    searchRequest);

            PowerMockito.when(
                    HttpUtils.doGet(Mockito.anyString(),
                            Matchers.<Map<String, String>> any())).thenReturn(
                    TEST_RESPONSE);

            searchFiles.getNextResponse();
            Assert.assertEquals(searchFiles.totalSearchFiles(),
                    TEST_NB_RESULTS.intValue());
        } catch (StockException e) {
            Assert.fail("Didn't expect the StockException!", e);
        }
    }

    @Test(groups = "SearchFiles.setAccessToken")
    public void setAccessToken_should_set_valid_access_token() {
        try {
            SearchFiles searchFiles = new SearchFiles(config, null,
                    searchRequest);
            String accessToken = "accessToken";
            searchFiles.setAccessToken(accessToken);
            Field f = searchFiles.getClass().getDeclaredField("mAccessToken");
            f.setAccessible(true);
            Assert.assertEquals(accessToken, f.get(searchFiles));
            searchFiles.setAccessToken(null);
            Assert.assertNull(f.get(searchFiles));
        } catch (Exception e) {
            Assert.fail("Didn't expect the Exception!", e);
        }
    }

    @Test(groups = "SearchFiles.setAccessToken", expectedExceptions = { StockException.class }, expectedExceptionsMessageRegExp = "Access Token missing! Result Column 'is_licensed' requires authentication.")
    public void setAccessToken_should_throw_exception_access_token_is_null_for_licensed_request()
            throws Exception {
        ResultColumn[] resultsColumns = { ResultColumn.NB_RESULTS,
                ResultColumn.IS_LICENSED };
        searchRequest.setResultColumns(resultsColumns);
        SearchFiles searchFiles = new SearchFiles(config, null, searchRequest);
        searchFiles.setAccessToken(null);
    }
}
