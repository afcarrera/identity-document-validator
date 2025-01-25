/**
 * Copyright 2025 afcarrera
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package io.github.afcarrera.identity.ec.handler.impl;

import io.github.afcarrera.identity.ec.domain.IdentityDocument;
import io.github.afcarrera.identity.ec.handler.AbstractIdentityHandler;

import java.util.List;
import java.util.stream.Collectors;

/**
 * Handler for transforming the string value of an identity document to a list of integers.
 * <p>This class extends {@link AbstractIdentityHandler} and converts the string value
 * of the identity document to a list of integers, then sets this list in the context.</p>
 */
public class StringToIntegerHandler extends AbstractIdentityHandler {

    /**
     * Processes the identity document by transforming its string value to a list of integers.
     * <p>The list of integers is then set in the context.</p>
     * @param context The identity document to be processed.
     */
    @Override
    public void process(IdentityDocument context) {
        List<Integer> valueList = transformStringToInteger(context.getValue());
        context.setValueList(valueList);
        checkNextHandler(context);
    }

    /**
     * Transforms a string value to a list of integers.
     * <p>Each character in the string is converted to its numeric value.</p>
     * @param value The string value to be transformed.
     * @return The list of integers corresponding to the numeric values of the characters in the string.
     */
    private List<Integer> transformStringToInteger(String value){
        return value.chars()
                .map(Character::getNumericValue)
                .boxed()
                .collect(Collectors.toList());
    }
}
