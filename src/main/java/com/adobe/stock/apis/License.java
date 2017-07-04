package com.adobe.stock.apis;

import java.lang.reflect.Field;
import java.net.MalformedURLException;
import java.net.URISyntaxException;
import java.util.Map;

import org.apache.http.HttpStatus;
import org.apache.http.client.utils.URIBuilder;
import org.apache.http.entity.ContentType;

import com.adobe.stock.annotations.SearchParamURLMapperInternal;
import com.adobe.stock.config.StockConfig;
import com.adobe.stock.exception.StockException;
import com.adobe.stock.models.LicenseRequest;
import com.adobe.stock.models.LicenseResponse;
/**
 * The class defining helper methods for licensing apis.
 */
final class LicenseAPIHelpers {
    /**
     * The private default constructor for utility class.
     */
    private LicenseAPIHelpers() {
    }

    /**
     * Helper method to validate LicenseRequest object for
     * Licensing APIs.
     * @param request the object of LicenseRequest which needs
     * to be validated
     * @param accessToken ims user access token
     * @throws StockException if category id is not present in the request
     * @see LicenseRequest
     * @see StockException
     */
    static void validateLicenseQueryParams(
            final LicenseRequest request, final String accessToken)
                    throws StockException {
        if (request == null) {
            throw new StockException("Request can't be null");
        }

        if (request.getContentId() == null) {
            throw new StockException(
                    "Asset Content id must be present in the license request");
        }
        if (request.getLicenseState() == null) {
            throw new StockException(
                    "Licensing state must be present in the license request");
        }
        if (accessToken == null || accessToken.isEmpty()) {
            throw new StockException(
                    "Access token can't be null or empty");
        }
    }
    /**
     * Helper method to create request URL from LicenseRequest object.
     * @param endPoint base URL string
     * @param request the object of LicenseRequest used for creating URL
     * @return URL containing all the parameters from LicenseRequest
     * @throws StockException if request contains invalid parameters
     * @see LicenseRequest
     * @see StockException
     */
    static String createLicenseApiUrl(final String endPoint,
            final LicenseRequest request) throws StockException {
        try {
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
            String url = uriBuilder.build().toURL().toString();
            return url;
        } catch (NullPointerException | IllegalArgumentException
                | IllegalAccessException | MalformedURLException
                | URISyntaxException e) {
            throw new StockException("Could not create the license"
                    + " request url");
        }
    }

    /**
     * Method to get http method type based on license request.
     *
     * @param request
     *            object containing license request parameters
     * @return http method type GET or POST
     * @see LicenseRequest
     */
    static String getLicenseApiHttpMethod(
            final LicenseRequest request) {
        String method = HttpUtils.HTTP_GET;
        if (request.getLicenseReference() != null) {
            method = HttpUtils.HTTP_POST;
        }
        return method;
    }
}

/**
 *
 * License API helps
 * for licensing assets,
 * for getting licensing information about a specific asset for specific user,
 * for notifying the system when a user abandons a licensing operation,
 * for getting the licensing capabilities for a specific user.
 */
public final class License {
    /**
     * Stock api configuration.
     */
    private StockConfig mConfig;
    /**
     * Constructs an api object for {@link License}.
     * @param config stock api configuration
     * @throws StockException if config is null or not initialized
     * @see StockConfig
     * @see StockException
     */
    public License(final StockConfig config) throws StockException {
        if (config == null) {
            throw new StockException("Config can't be null");
        }

        this.mConfig = new StockConfig().setApiKey(config.getApiKey())
                .setProduct(config.getProduct())
                .setTargetEnvironment(config.getTargetEnvironment())
                .setProductLocation(config.getProductLocation());
    }
    /**
     * Requests licensing information about a specific asset for a specific
     * user.
     * @param request {@link LicenseRequest} object containing
     *  content_id and license.
     * @param accessToken ims user access token
     * @return {@link LicenseResponse} object containing information about
     *  licensing information.
     * @throws StockException if request is null/invalid or api returns
     *  with error
     * @see LicenseRequest
     * @see License Response
     * @see StockException
     */
    public LicenseResponse getContentInfo(
            final LicenseRequest request, final String accessToken)
            throws StockException {
        LicenseAPIHelpers.validateLicenseQueryParams(request, accessToken);

        String requestURL = LicenseAPIHelpers.createLicenseApiUrl(
                this.mConfig.getEndpoints().getLicenseContentInfoEndpoint(),
                request);
        Map<String, String> headers = ApiUtils
                .generateCommonAPIHeaders(this.mConfig, accessToken);
        String responseString = HttpUtils.doGet(requestURL, headers);

        LicenseResponse reponse = (LicenseResponse) JsonUtils
                .parseJson(LicenseResponse.class, responseString);
        return reponse;
    }
    /**
     * Requests a license for an asset for a specific user.
     * @param request {@link LicenseRequest} object containing
     *  content_id ,License Reference and license.
     *  @param accessToken ims user access token
     * @return {@link LicenseResponse} object containing information about
     *  licensing information.
     * @throws StockException if request is null/invalid or api returns
     *  with error
     * @see LicenseRequest
     * @see LicenseResponse
     * @see StockException
     */
    public LicenseResponse getContentLicense(
            final LicenseRequest request, final String accessToken)
            throws StockException {

        String responseString = "";
        LicenseAPIHelpers.validateLicenseQueryParams(request, accessToken);

        String requestURL = LicenseAPIHelpers.createLicenseApiUrl(
                this.mConfig.getEndpoints().getLicenseContentLicenseEndpoint(),
                request);
        Map<String, String> headers = ApiUtils
                .generateCommonAPIHeaders(this.mConfig, accessToken);

        if (LicenseAPIHelpers.getLicenseApiHttpMethod(request)
                == HttpUtils.HTTP_GET) {
             responseString = HttpUtils.doGet(requestURL, headers);
        } else {
            String jsonString = JsonUtils.parseObjectToJson(
                    request.getLicenseReference());
             responseString = HttpUtils.doPost(requestURL, headers,
                    jsonString.getBytes(), ContentType.APPLICATION_JSON);
            }

        LicenseResponse reponse = (LicenseResponse) JsonUtils
                .parseJson(LicenseResponse.class, responseString);
        return reponse;
    }
    /**
     * It can be used to get the licensing capabilities for a specific user.
     * This API returns the user's available purchase quota, the member
     * identifier, and information that you can use to present licensing
     *  options to the user when the user next requests an asset purchase.
     *  In this 3 cases can occur -
     *  User has enough quota to license the next asset.
     *  User doesn't have enough quota and is set up to handle overage.
     *  User doesn't have quota and there is no overage plan.
     *
     * @param request {@link LicenseRequest} object containing
     *  content_id, license state and locale.
     * @param accessToken ims user access token
     * @return {@link LicenseResponse} object containing information about
     *  member profile.
     * @throws StockException if request is null/invalid or api returns
     *  with an error
     */
    public LicenseResponse getMemberProfile(final LicenseRequest request,
            final String accessToken) throws StockException {
        LicenseAPIHelpers.validateLicenseQueryParams(request, accessToken);
        String requestURL = LicenseAPIHelpers.createLicenseApiUrl(
                this.mConfig.getEndpoints().getLicenseMemberProfileEndpoint(),
                request);
        Map<String, String> headers = ApiUtils
                .generateCommonAPIHeaders(this.mConfig, accessToken);
        String responseString = HttpUtils.doGet(requestURL, headers);

        LicenseResponse reponse = (LicenseResponse) JsonUtils
                .parseJson(LicenseResponse.class, responseString);
        return reponse;
    }
    /**
     * Notifies the system when a user cancels a licensing operation.
     * It can be used if the user refuses the opportunity to purchase
     *  or license the requested asset.
     * @param request {@link LicenseRequest} object containing
     *  content_id and license state.
     * @param accessToken ims user access token
     * @throws StockException if request is null/invalid or api returns
     *  with an error
     */
    public void abandonLicense(final LicenseRequest request,
            final String accessToken) throws StockException {
        LicenseAPIHelpers.validateLicenseQueryParams(request, accessToken);
        String requestURL = LicenseAPIHelpers.createLicenseApiUrl(
                this.mConfig.getEndpoints().getLicenseMemberAbandonEndpoint(),
                request);
        Map<String, String> headers = ApiUtils
                .generateCommonAPIHeaders(this.mConfig, accessToken);
        String responseString = HttpUtils.doGet(requestURL, headers);
        if (!responseString.equals(String.valueOf(HttpStatus.SC_NO_CONTENT))) {
            throw new StockException("Stock API returned with an error");
        }
    }

}
