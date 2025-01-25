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
 * Handler for extracting a sublist from the identity document's value list based on specified indexes.
 * <p>This class extends {@link AbstractIdentityHandler} and extracts elements from the value list
 * of the identity document using the provided indexes, then sets this sublist in the context.</p>
 */
public class SublistExtractorHandler extends AbstractIdentityHandler {

    /**
     * List of indexes to extract elements from the original list.
     */
    private final List<Integer> indexList;

    /**
     * Constructor for SublistExtractorHandler.
     * @param indexList The list of indexes to extract elements from the original list.
     */
    public SublistExtractorHandler(List<Integer> indexList) {
        this.indexList = indexList;
    }

    /**
     * Processes the identity document by extracting a sublist from its value list.
     * <p>The sublist is then set in the context.</p>
     * @param context The identity document to be processed.
     */
    @Override
    public void process(IdentityDocument context) {
        List<Integer> subValueList = getElementsByIndexes(context.getValueList(), indexList);
        context.setValueSublist(subValueList);
        checkNextHandler(context);
    }

    /**
     * Extracts elements from the original list based on the provided indexes.
     * @param originalList The original list from which elements are to be extracted.
     * @param indexes The list of indexes to use for extracting elements.
     * @return The list of extracted elements.
     */
    private List<Integer> getElementsByIndexes(List<Integer> originalList, List<Integer> indexes) {
        return indexes.stream()
                .filter(index -> index >= 0 && index < originalList.size())
                .map(originalList::get)
                .collect(Collectors.toList());
    }
}
