/*******************************************************************************
 * Copyright 2017 Adobe Systems Incorporated. All rights reserved.
 * This file is licensed to you under the Apache License, Version 2.0
 * (the "License") you may not use this file except in compliance with the
 * License. You may obtain a copy of the License at
 * http://www.apache.org/licenses/LICENSE-2.0
 ******************************************************************************/
package com.adobe.stock.annotations;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * Marker annotation that can be used to define the string equivalent field of
 * search_parameters query parameter for Search/Files api.
 * <p>
 * This annotation allows to define the type if it's different than defined one
 * which is taken cared while serialization of query parameter for api url.
 * <p>
 * Note: The value must include the square brackets as per the Search/Files api
 * documentations. For e.g. If the API query parameter is like
 * search_parameters[words]. Here the value for the annotation should be -
 *
 * {@literal @}SearchParamURLMapperInternal(value = "[words]")
 */
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.FIELD)
public @interface SearchParamURLMapperInternal {
    /**
     * The type conversion constant to define that the boolean variable needs to
     * be converted into integer type while api URL serialization.
     * <p>
     * Must be used for boolean or Boolean variables only.
     */
    String BOOLEAN_TO_INTEGER = "boolean_integer";

    /**
     * The default type conversion constant which defines that no type
     * conversion needed while api URL serialization for the defined variable.
     */
    String NORMAL = "normal";

    /**
     * Gets the defined string equivalent search parameter field name for the
     * corresponding variable.
     *
     * @return the string equivalent search parameter field name
     */
    String value();

    /**
     * Gets the type conversion constant defined for the corresponding variable.
     * If not defined the default SearchParamURLMapperInternal.NORMAL will be
     * returned.
     *
     * @return the type conversion constant defined
     */
    String type() default SearchParamURLMapperInternal.NORMAL;
}
