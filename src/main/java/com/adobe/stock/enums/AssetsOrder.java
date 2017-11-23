/*******************************************************************************
 * Copyright 2017 Adobe Systems Incorporated. All rights reserved.
 * This file is licensed to you under the Apache License, Version 2.0
 * (the "License") you may not use this file except in compliance with the
 * License. You may obtain a copy of the License at
 * http://www.apache.org/licenses/LICENSE-2.0
 ******************************************************************************/
package com.adobe.stock.enums;
/**.
 * Sort order in which to return found assets
 */
public enum AssetsOrder {
    /**.
     *Matches your search request, closest matches first
     */
    RELEVANCE("relevance"),

    /**.
     * Matches your search request, newest first.
    */
    CREATION("creation"),

    /**.
     *In descending order by the number of views by all users.
     */
    POPULARITY("popularity"),

    /**.
     * In descending order by the number of downloads by all users
     * since the asset was added to Adobe Stock.
     */
    NB_DOWNLOADS("nb_downloads"),

    /**
     * Starting with assets that have not commonly been viewed or downloaded.
    */
    UNDISCOVERED("undiscovered");

    /**
     * Order in which you want to search files.
    */
    private String mValue;

    /**Constructor of this enum.
     * @param value String sort order
     */
    AssetsOrder(final String value) {
        this.mValue = value;
    }

    @Override
    public String toString() {
        return mValue;
    }

}
