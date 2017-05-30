package com.adobe.stock.enums;

/**.
 * All Environments that can be used for hitting stock APIs.
*/
public enum Environment {

    /**.
     *  Production Environment.
     */
    PROD(1),

    /**.
     * Stage Environment.
     */
    STAGE(2);

    /** Value of environment enums.*/
    private int mValue;

    /**.
     * Constructor for this enum.
     * @param value integer value of enums
     */
    Environment(final int value) {
        mValue = value;
    }

    @Override
    public String toString() {
        return String.valueOf(this.mValue);
    }
}
