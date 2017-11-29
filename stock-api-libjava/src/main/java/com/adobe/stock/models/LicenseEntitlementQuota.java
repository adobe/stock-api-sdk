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
 * Full quota of the user available entitlement.
 */
@JsonIgnoreProperties(ignoreUnknown = true)
public final class LicenseEntitlementQuota {
    /**
     * Image quota for CCI, CCT and CCE 1st and 2nd generation.
     */
    private Integer mImageQuota;
    /**
     * Video quota for CCE 1st generation.
     */
    private Integer mVideoQuota;
    /**
     * Credits quota for CCE 2nd generation.
     */
    private Integer mCreditsQuota;
    /**
     * Standard credits quota for CCE 3rd generation.
     */
    private Integer mStandardCreditsQuota;
    /**
     * Premium credits quota for CCE 3rd generation.
     */
    private Integer mPremiumCreditsQuota;
    /**
     * Universal credits quota for CCE 3rd generation.
     */
    private Integer mUniversalCreditsQuota;

    /**
     * Default Constructor.
     */
    public LicenseEntitlementQuota() {
    }

    /**
     * Get Image quota for CCI, CCT and CCE 1st and 2nd generation.
     * @return image quota of type Integer
     */
    public Integer getImageQuota() {
        return mImageQuota;
    }
    /**
     * Get Video quota for CCE 1st generation.
     * @return video quota of type Integer
     */
    public Integer getVideoQuota() {
        return mVideoQuota;
    }
    /**
     * Get Credits quota for CCE 2nd generation.
     * @return credits quota of type Integer
     */
    public Integer getCreditsQuota() {
        return mCreditsQuota;
    }
    /**
     * Get Standard credits quota for CCE 3rd generation.
     * @return standard credits quota of type Integer
     */
    public Integer getStandardCreditsQuota() {
        return mStandardCreditsQuota;
    }
    /**
     * Get Premium credits quota for CCE 3rd generation.
     * @return premium credits quota of type Integer
     */
    public Integer getPremiumCreditsQuota() {
        return mPremiumCreditsQuota;
    }
    /**
     * Get Universal credits quota for CCE 3rd generation.
     * @return universal credits quota of type Integer
     */
    public Integer getUniversalCreditsQuota() {
        return mUniversalCreditsQuota;
    }
    /**
     * Sets Image quota for CCI, CCT and CCE 1st and 2nd generation.
     * @param imageQuota image quota
     */
    @JsonSetter("image_quota")
    public void setImageQuota(final Integer imageQuota) {
        this.mImageQuota = imageQuota;
    }
    /**
     * Sets Video quota for CCE 1st generation.
     * @param videoQuota video quota
     */
    @JsonSetter("video_quota")
    public void setVideoQuota(final Integer videoQuota) {
        this.mVideoQuota = videoQuota;
    }
    /**
     * Sets Credits quota for CCE 2nd generation.
     * @param creditsQuota credits quota
     */
    @JsonSetter("credits_quota")
    public void setCreditsQuota(final Integer creditsQuota) {
        this.mCreditsQuota = creditsQuota;
    }
    /**
     * Sets Standard credits quota for CCE 3rd generation.
     * @param standardCreditsQuota standard credits quota
     */
    @JsonSetter("standard_credits_quota")
    public void setStandardCreditsQuota(final Integer standardCreditsQuota) {
        this.mStandardCreditsQuota = standardCreditsQuota;
    }
    /**
     * Sets Premium credits quota for CCE 3rd generation.
     * @param premiumCreditsQuota premium credits quota
     */
    @JsonSetter("premium_credits_quota")
    public void setPremiumCreditsQuota(final Integer premiumCreditsQuota) {
        this.mPremiumCreditsQuota = premiumCreditsQuota;
    }
    /**
     * Sets Universal credits quota for CCE 3rd generation.
     * @param universalCreditsQuota universal credits quota
     */
    @JsonSetter("universal_credits_quota")
    public void setUniversalCreditsQuota(final Integer universalCreditsQuota) {
        this.mUniversalCreditsQuota = universalCreditsQuota;
    }

}
