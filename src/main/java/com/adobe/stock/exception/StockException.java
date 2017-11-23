/*******************************************************************************
 * Copyright 2017 Adobe Systems Incorporated. All rights reserved.
 * This file is licensed to you under the Apache License, Version 2.0
 * (the "License") you may not use this file except in compliance with the
 * License. You may obtain a copy of the License at
 * http://www.apache.org/licenses/LICENSE-2.0
 ******************************************************************************/
package com.adobe.stock.exception;

/**
 * Exception for Stock APIs Java SDK. In case of any error or illegal arguments
 * or the api throws any error, this exception will be thrown from the sdk
 * classes.
 */
public final class StockException extends Exception {

    private static final long serialVersionUID = 2462467797311665109L;

    /**
     * The error code in case of it is an Stock API exception.
     */
    private int mErrorCode;

    /**
     * Constructs the StockException with error code and message. Mostly used
     * for the api exceptions.
     *
     * @param code
     *            the error code returned from api
     * @param message
     *            the error message
     */
    public StockException(final int code, final String message) {
        super(message);
        mErrorCode = code;
    }

    /**
     * Constructs the StockException with message.
     *
     * @param message
     *            the error message
     */
    public StockException(final String message) {
        super(message);
        mErrorCode = -1; // Unknown
    }

    /**
     * Get the error code if any otherwise returns -1 for default.
     *
     * @return the error code
     */
    public int getCode() {
        return mErrorCode;
    }

}
