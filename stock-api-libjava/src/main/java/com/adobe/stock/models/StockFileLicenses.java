/*******************************************************************************
 * Copyright 2017 Adobe Systems Incorporated. All rights reserved.
 * This file is licensed to you under the Apache License, Version 2.0
 * (the "License") you may not use this file except in compliance with the
 * License. You may obtain a copy of the License at
 * http://www.apache.org/licenses/LICENSE-2.0
 ******************************************************************************/
package com.adobe.stock.models;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonSetter;
/**
 * StockFileLicenses stores the licenses type for media.
 */
@JsonIgnoreProperties(ignoreUnknown = true)
public final class StockFileLicenses {
    /**
     * Standard license type.
     */
    private StockFileLicenseProp mStandard;
    /**
     * Half-priced premium license type.
     */
    private StockFileLicenseProp mStandardM;

    /**
     * Default constructor.
     */
    public StockFileLicenses() {
    }

    /**
     * Get Standard license type.
     *
     * @return Standard license type
     */
    public StockFileLicenseProp getStandard() {
        return mStandard;
    }

    /**
     * Sets Standard license type.
     *
     * @param standard
     *            Standard license type
     */
    @JsonSetter("Standard")
    public void setStandard(final StockFileLicenseProp standard) {
        this.mStandard = standard;
    }

    /**
     * Get half-priced premium license type.
     *
     * @return half-priced premium license type
     */
    public StockFileLicenseProp getStandardM() {
        return mStandardM;
    }

    /**
     * Sets half-priced premium license type.
     *
     * @param standardM
     *            half-priced premium license type
     */
    @JsonSetter("Standard-M")
    public void setStandardM(final StockFileLicenseProp standardM) {
        this.mStandardM = standardM;
    }

}
