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
 * StockFileCategory stores the category information of media.
 */
@JsonIgnoreProperties(ignoreUnknown = true)
public final class StockFileCategory {
    /**
     * ID of the category.
     */
    private Integer mId;
    /**
     * Localised name of the category.
     */
    private String mName;
    /**
     * Path of the category.
     * Note: This is used only for Search Category/CategoryTree APIs
     */
    private String mLink;

    /**
     * Default constructor.
     */
    public StockFileCategory() {
    }

    /**
     * Constructor for {@link StockFileCategory}.
     *
     * @param id
     *            category id
     * @param name
     *            localised category name
     */
    public StockFileCategory(final Integer id, final String name) {
        super();
        this.mId = id;
        this.mName = name;
    }

    /**
     * Get category id.
     *
     * @return category id of type Integer
     */
    public Integer getId() {
        return mId;
    }

    /**
     * Sets category id.
     *
     * @param id
     *            category id
     */
    @JsonSetter("id")
    public void setId(final Integer id) {
        this.mId = id;
    }

    /**
     * Get localised name of the category.
     *
     * @return category name of type String
     */
    public String getName() {
        return mName;
    }

    /**
     * Sets localised name of the category.
     *
     * @param name
     *            category name
     */
    @JsonSetter("name")
    public void setName(final String name) {
        this.mName = name;
    }
    /**
     * Get path of the category.
     * @return category path of type String
     */
    public String getLink() {
        return mLink;
    }
    /**
     * Sets path of the catepory.
     * @param link category path
     */
    @JsonSetter("link")
    public void setLink(final String link) {
        this.mLink = link;
    }
}
