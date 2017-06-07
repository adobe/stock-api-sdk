package com.adobe.stock.config;

import com.adobe.stock.enums.Environment;
import com.adobe.stock.exception.StockException;

/**
 * Class for storing stock api configuration.
 */
public final class StockConfig {
    /**
     * Environment stack.
     */
    private Environment mTargetEnvironment;
    /**
     *  Stock API Endpoints.
     */
    private Endpoints mEndpoints;
    /**
     * Stock API key.
     */
    private String mApiKey;
    /**
     * Product for stock API access.
     */
    private String mProduct;

    /**
     * Constructs an object of StockConfig.
     *
     * @throws StockException
     *             if StockConfig couldn't initialize the endpoints properly
     * @see StockException
     */
    public StockConfig() throws StockException {
        this.mTargetEnvironment = Environment.STAGE;
        try {
            this.mEndpoints = new Endpoints(this.mTargetEnvironment);
        } catch (StockException e) {
            throw e;
        }
    }

    /**
     * Checks if the config is initialized/set.
     *
     * @return true if config is initialized/set, false otherwise
     */
    public boolean isConfigInitialized() {
        // vsuthar - No need for this.mTargetEnvironment != null &&
        // this.mEndpoints != null condition check since mTargetEnvironment and
        // mEndpoints are initialized with default values and can't be set to
        // null
        if (this.mApiKey != null && this.mProduct != null) {
            return true;
        }
        return false;
    }

    /**
     * Get the target stack environment.
     *
     * @return the target environment
     */
    public Environment getTargetEnvironment() {
        return mTargetEnvironment;
    }

    /**
     * Set the target environment for StockConfig.
     *
     * @param targetEnvironment
     *            the environment stack
     * @return the instance of this StockConfig
     * @throws StockException
     *             if tried to set unsupported stack or null
     * @see Environment
     * @see StockException
     */
    public StockConfig setTargetEnvironment(final Environment targetEnvironment)
            throws StockException {
        if (targetEnvironment != null
                && targetEnvironment != this.mTargetEnvironment) {
            this.mTargetEnvironment = targetEnvironment;
            this.mEndpoints = new Endpoints(this.mTargetEnvironment);
        }
        return this;
    }

    /**
     * Get the Stock API Endpoints.
     *
     * @return the Stock API Endpoints
     */
    public Endpoints getEndpoints() {
        return mEndpoints;
    }

    /**
     * Get the Api Key set to be used for Stock APIs. It is used as x-api-key
     * header with the Stock APIs.
     *
     * @return the Stock Api Key
     */
    public String getApiKey() {
        return mApiKey;
    }

    /**
     * Set the Api key for Stock API access. It is used to set as x-api-key
     * header while htting the Stock APIs.
     *
     * @param apiKey
     *            the Stock Api Key
     * @return the instance of this StockConig
     * @throws StockException
     *             if Api Key is null
     * @see StockException
     */
    public StockConfig setApiKey(final String apiKey) throws StockException {
        if (apiKey != null) {
            this.mApiKey = apiKey;
        } else {
            throw new StockException("Api Key configuration can't be null!");
        }
        return this;
    }

    /**
     * Get the product name set to be used for Stock APIs. It is used to set as
     * x-product header while htting the Stock APIs.
     *
     * @return the Stock Api Key
     */
    public String getProduct() {
        return mProduct;
    }

    /**
     * Set the product name for Stock API access. It is used to set as x-product
     * header while htting the Stock APIs.
     *
     * @param product
     *            the stock client's product name
     * @return the instance of this StockConig
     * @throws StockException
     *             if product name is null
     * @see StockException
     */
    public StockConfig setProduct(final String product) throws StockException {
        if (product != null) {
            this.mProduct = product;
        } else {
            throw new StockException("Product configuration can't be null!");
        }
        return this;
    }
}
