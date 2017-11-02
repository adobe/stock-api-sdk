package com.adobe.stock.enums;
/**.
 * All 3D types that can be used in search parameters for searching assets.
 */
public enum Asset3DType {

    /**
    * 3D Models Type Asset.
    */
    MODELS(1),

    /**.
     * 3D Lighiting Type Asset.
     */
    LIGHTS(2),

    /**.
     * 3D Materials Type Asset.
     */
    MATERIALS(3);

    /**.
     * Value of 3D type enums.
     */
    private int mValue;

    /**.
     * Constructor of this enum.
     * @param value integer value of enums
     */
    Asset3DType(final int value) {
        this.mValue = value;
    }

    @Override
    public String toString() {
        return String.valueOf(this.mValue);
    }
}
