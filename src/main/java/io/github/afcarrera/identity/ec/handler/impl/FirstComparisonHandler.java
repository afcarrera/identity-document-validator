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
import io.github.afcarrera.identity.ec.exception.IdentityDocumentException;
import io.github.afcarrera.identity.ec.handler.AbstractIdentityHandler;

import java.util.Objects;

/**
 * Handler for performing a first comparison check on the identity document.
 * <p>This class extends {@link AbstractIdentityHandler} and compares the last check digit
 * with a specified value. If the last check digit matches the specified value, it further
 * checks if the last digit also matches. If not, an exception is thrown.</p>
 */
public class FirstComparisonHandler extends AbstractIdentityHandler {

    /**
     * The value to compare against the last check digit.
     */
    private final Integer firstComparisonValue;

    /**
     * Constructor for FirstComparisonHandler.
     * @param firstComparisonValue The value to compare against the last check digit.
     */
    public FirstComparisonHandler(Integer firstComparisonValue) {
        this.firstComparisonValue = firstComparisonValue;
    }

    /**
     * Processes the identity document by performing the first comparison check.
     * <p>If the last check digit matches the specified value, it further checks if the last digit also matches.</p>
     * @param context The identity document to be processed.
     * @throws IdentityDocumentException If the last check digit matches the specified value but the last digit does not.
     */
    @Override
    public void process(IdentityDocument context) {
        if (Objects.equals(context.getLastCheckDigit(), firstComparisonValue)) {
            if (!context.getLastDigit().equals(firstComparisonValue)) {
                throw new IdentityDocumentException("Invalid last check digit on first comparison.");
            }
        } else {
            checkNextHandler(context);
        }
    }
}