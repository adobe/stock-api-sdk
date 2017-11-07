package com.adobe.stock.config;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

import com.adobe.stock.enums.Environment;
import com.adobe.stock.exception.StockException;

/**
 * Class for reading and storing endpoints from properties.
 */
public class Endpoints {
    /**
     * Properties file key for Search/Files endpoint.
     */
    private static final String SEARCH_FILES_ENDPOINT_KEY =
            "api.endpoints.search";
    /**
     * Properties file key for Search/Category endpoint.
     */
    private static final String SEARCH_CATEGORY_ENDPOINT_KEY =
            "api.endpoints.category";
    /**
     * Properties file key for Search/CategoryTree endpoint.
     */
    private static final String SEARCH_CATEGORY_TREE_ENDPOINT_KEY =
            "api.endpoints.categorytree";
    /**
     * Properties file key for Content/Info endpoint.
     */
    private static final String LICENSE_CONTENT_INFO_ENDPOINT_KEY =
            "api.endpoints.license.contentinfo";
    /**
     * Properties file key for Content/License endpoint.
     */
    private static final String LICENSE_CONTENT_LICENSE_ENDPOINT_KEY =
            "api.endpoints.license.contentlicense";
    /**
     * Properties file key for Member/Profile endpoint.
     */
    private static final String LICENSE_MEMBER_PROFILE_ENDPOINT_KEY =
            "api.endpoints.license.memberprofile";
    /**
     * Properties file key for Member/Abandon endpoint.
     */
    private static final String LICENSE_MEMBER_ABANDON_ENDPOINT_KEY =
            "api.endpoints.license.memberabandon";
    /**
     * Properties file name for prod environment.
     */
    private static final String PROPS_FILE_PROD = "prod-environment.properties";
    /**
     * Properties file name for stage environment.
     */
    private static final String PROPS_FILE_STAGE =
            "stage-environment.properties";

    /**
     * Environment stack.
     */
    private Environment mEnvironment;
    /**
     * Properties which stores endpoints.
     */
    private Properties mEndpoints;

    /**
     * Constructs the Endpoints.
     *
     * @param env
     *            the environment stack
     * @throws StockException
     *             if tried to set unsupported stack or null
     * @see Environment
     * @see StockException
     */
    public Endpoints(final Environment env) throws StockException {
        if (env == null) {
            throw new StockException("Environment can't be null");
        }
        InputStream input = null;

        try {
            if (env == Environment.PROD) {
                input = Endpoints
                        .getResourceAsStream(Endpoints.PROPS_FILE_PROD);
            } else {
                input = Endpoints
                        .getResourceAsStream(Endpoints.PROPS_FILE_STAGE);
            }

            if (input == null) {
                throw new StockException(
                        "Could not load the endpoint properties file");
            }

            this.mEnvironment = env;

            this.mEndpoints = new Properties();
            this.mEndpoints.load(input);
        } catch (StockException e) {
            throw e;
        } catch (IOException e) {
            throw new StockException("Could not initialize the endpoints");
        } finally {
            try {
                if (input != null) {
                    input.close();
                }
            } catch (IOException e) {
                throw new StockException(
                        "Could not load the endpoint properties file");
            }
        }
    }

    /**
     * Get resource as input stream.
     * @return input stream from resource
     * @param resource
     *            the name of resource
     */
    private static InputStream getResourceAsStream(final String resource) {
        return Endpoints.class.getClassLoader().getResourceAsStream(resource);
    }

    /**
     * Get the endpoint for Stock Search/Files API.
     * @return the endpoint for Stock Search/Files API
     */
    public final String getSearchFilesEndpoint() {
        return this.mEndpoints.getProperty(SEARCH_FILES_ENDPOINT_KEY);
    }

    /**
     * Get the endpoint for Stock Search/Category API.
     * @return the endpoint for Stock Search/Category API
     */
    public final String getSearchCategoryEndpoint() {
        return this.mEndpoints.getProperty(SEARCH_CATEGORY_ENDPOINT_KEY);
    }

    /**
     * Get the endpoint for Stock Search/CategoryTree API.
     * @return the endpoint for Stock Search/CategoryTree API
     */
    public final String getSearchCategoryTreeEndpoint() {
        return this.mEndpoints.getProperty(SEARCH_CATEGORY_TREE_ENDPOINT_KEY);
    }
    /**
     * Get the endpoint for Stock Content/Info API.
     * @return the endpoint for Stock License/ContentInfo API
     */
    public final String getLicenseContentInfoEndpoint() {
        return this.mEndpoints.getProperty(LICENSE_CONTENT_INFO_ENDPOINT_KEY);
    }
    /**
     * Get the endpoint for Stock Content/License API.
     * @return the endpoint for Stock License/ContentLicense API
     */
    public final String getLicenseContentLicenseEndpoint() {
        return this.mEndpoints.getProperty(
                LICENSE_CONTENT_LICENSE_ENDPOINT_KEY);
    }
    /**
     * Get the endpoint for Stock Member/Profile API.
     * @return the endpoint for Stock Member/Profile API
     */
    public final String getLicenseMemberProfileEndpoint() {
        return this.mEndpoints.getProperty(LICENSE_MEMBER_PROFILE_ENDPOINT_KEY);
    }
    /**
     * Get the endpoint for Stock Member/Abandon API.
     * @return the endpoint for Stock Member/Abandon API
     */
    public final String getLicenseMemberAbandonEndpoint() {
        return this.mEndpoints.getProperty(
                LICENSE_MEMBER_ABANDON_ENDPOINT_KEY);
    }
    /**
     * Get the environment stack.
     *
     * @return the environment stack
     */
    public final Environment getEnvironment() {
        return this.mEnvironment;
    }
}
