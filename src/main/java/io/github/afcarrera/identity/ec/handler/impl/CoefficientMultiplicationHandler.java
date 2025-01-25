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

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

/**
 * Handler for multiplying elements of a sublist from the identity document with a list of coefficients.
 * <p>This class extends {@link AbstractIdentityHandler} and performs element-wise multiplication
 * of the identity document's sublist with the provided coefficient list,
 * then sets the resulting list in the context.</p>
 */
public class CoefficientMultiplicationHandler extends AbstractIdentityHandler {

    /**
     * List of coefficients used for multiplication.
     */
    private final List<Integer> coefficientList;

    /**
     * Constructor for CoefficientMultiplicationHandler.
     * @param coefficientList The list of coefficients to be used for multiplication.
     */
    public CoefficientMultiplicationHandler(List<Integer> coefficientList) {
        this.coefficientList = coefficientList;
    }

    /**
     * Processes the identity document by multiplying its sublist with the coefficient list.
     * <p>The resulting multiplication list is then set in the context.</p>
     * @param context The identity document to be processed.
     * @throws IdentityDocumentException If the size of the coefficient list does not match the size of the sublist.
     */
    @Override
    public void process(IdentityDocument context) {
        if (coefficientList.size() != context.getValueSublist().size()){
            throw new IdentityDocumentException("Failed to multiply coefficients.");
        }
        List<Integer> multiplicationList = getMultipliedList(context.getValueSublist());
        context.setMultiplicationList(multiplicationList);
        checkNextHandler(context);
    }

    /**
     * Multiplies elements of the sublist with the corresponding elements of the coefficient list.
     * @param subValueList The sublist of integers to be multiplied.
     * @return The list of multiplied values.
     */
    private List<Integer> getMultipliedList(List<Integer> subValueList){
        return IntStream.range(0, coefficientList.size())
                .map(i -> coefficientList.get(i) * subValueList.get(i))
                .boxed()
                .collect(Collectors.toList());
    }
}
