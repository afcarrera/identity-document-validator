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

/**
 * Handler for summing elements in the multiplication list of an identity document.
 * <p>This class extends {@link AbstractIdentityHandler} and calculates the sum of the elements
 * in the multiplication list, then sets this sum as the last check digit in the context.</p>
 */
public class ListSumHandler extends AbstractIdentityHandler {

    /**
     * Processes the identity document by summing its multiplication list.
     * <p>The sum is then set as the last check digit in the context.</p>
     * @param context The identity document to be processed.
     */
    @Override
    public void process(IdentityDocument context) {
        Integer sum = getSum(context.getMultiplicationList());
        context.setLastCheckDigit(sum);
        checkNextHandler(context);
    }

    /**
     * Sums the elements of the multiplication list.
     * @param multiplicationList The list of integers to be summed.
     * @return The sum of the elements in the list.
     */
    private Integer getSum(List<Integer> multiplicationList){
        return multiplicationList
                .stream()
                .reduce(0, Integer::sum);
    }
}