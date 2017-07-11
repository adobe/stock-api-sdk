package com.adobe.stock.models;

import com.adobe.stock.enums.AssetLicenseSize;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonSetter;

/**
 * Licensing information for an asset for the user.
 */
@JsonIgnoreProperties(ignoreUnknown = true)
public final class LicenseContent {
    /**
     * Asset's unique identifier.
     */
    private String mContentId;
    /**
     * Information about the user's purchase/license of this asset.
     */
    private LicensePurchaseDetails mPurchaseDetails;
    /**
     * The size of the asset, indicating whether it is the free
     *  complementary size or the original full-sized asset.
     */
    private AssetLicenseSize mSize;
    /**
     * Information about the complementary or watermarked asset.
     */
    private LicenseComp mComp;
    /**
     * Information about the asset thumbnail.
     */
    private LicenseThumbnail mThumbnail;

    /**
     * Default Constructor.
     */
    public LicenseContent() {
    }
    /**
     * Get Asset's unique identifier.
     * @return Asset's unique identifier
     */
    public String getContentId() {
        return mContentId;
    }
    /**
     * Get Information about the user's purchase/license of this asset.
     * @return object of type {@link LicensePurchaseDetails}
     */
    public LicensePurchaseDetails getPurchaseDetails() {
        return mPurchaseDetails;
    }
    /**
     * Get the size of the asset, indicating whether it is the free
     *  complementary size or the original full-sized asset.
     * @return enum of type {@link AssetLicenseSize}
     */
    public AssetLicenseSize getSize() {
        return mSize;
    }

    /**
     * Get Information about the complementary or watermarked asset.
     * @return object of type {@link LicenseComp}
     */
    public LicenseComp getComp() {
        return mComp;
    }
    /**
     * Get information about the asset thumbnail.
     * @return object of type {@link LicenseThumbnail}
     */
    public LicenseThumbnail getThumbnail() {
        return mThumbnail;
    }
    /**
     * Sets Asset's unique identifier.
     * @param contentId Asset's unique identifier
     */
    @JsonSetter("content_id")
    public void setContentId(final String contentId) {
        this.mContentId = contentId;
    }
    /**
     * Sets Information about the user's purchase/license of this asset.
     * @param purchaseDetails Information about the user's purchase/license
     *  of this asset.
     */
    @JsonSetter("purchase_details")
    public void setPurchaseDetails(
            final LicensePurchaseDetails purchaseDetails) {
        this.mPurchaseDetails = purchaseDetails;
    }
    /**
     * Set the size of the asset, indicating whether it is the free
     *  complementary size or the original full-sized asset.
     * @param size Asset size of type {@link AssetLicenseSize}
     */
    @JsonSetter("size")
    public void setSize(final AssetLicenseSize size) {
        this.mSize = size;
    }

    /**
     * Sets Information about the complementary or watermarked asset.
     * @param comp Information about the complementary or watermarked asset.
     */
    @JsonSetter("comp")
    public void setComp(final LicenseComp comp) {
        this.mComp = comp;
    }
    /**
     * Sets information about the asset thumbnail.
     * @param thumbnail information about the asset thumbnail.
     */
    @JsonSetter("thumbnail")
    public void setThumbnail(final LicenseThumbnail thumbnail) {
        this.mThumbnail = thumbnail;
    }

}
