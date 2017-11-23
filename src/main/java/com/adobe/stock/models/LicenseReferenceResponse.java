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
 * License references of the user.
 * License references marked as "required" must be submitted when licensing
 * the image using the corresponding "id" attributes. See
 * "/Rest/Libraries/1/Content/License" for more information.
 */
@JsonIgnoreProperties(ignoreUnknown = true)
public final class LicenseReferenceResponse {
    /**
     * License reference id.
     */
    private Integer mId;
    /**
     * License reference description.
     */
    private String mText;
    /**
     * Whether license reference must be submitted
     * when licensing the image.
     */
    private Boolean mRequired;

    /**
     * Default Constructor.
     */
    public LicenseReferenceResponse() {
    }

    /**
     * Get License reference id.
     * @return id of type Integer
     */
    public Integer getId() {
        return mId;
    }
    /**
     * Get License reference description.
     * @return description of type String.
     */
    public String getText() {
        return mText;
    }
    /**
     * Get whether license reference must be submitted
     * when licensing the image.
     * @return true if license reference must be submitted else false
     */
    public Boolean getRequired() {
        return mRequired;
    }
    /**
     * Sets License reference id.
     * @param id License reference id.
     */
    @JsonSetter("id")
    public void setId(final Integer id) {
        this.mId = id;
    }
    /**
     * Sets License reference description.
     * @param text License reference description.
     */
    @JsonSetter("text")
    public void setText(final String text) {
        this.mText = text;
    }
    /**
     * Sets whether license reference must be submitted
     * when licensing the image.
     * @param required true if license reference must be submitted else false
     */
    @JsonSetter("required")
    public void setRequired(final Boolean required) {
        this.mRequired = required;
    }
}
