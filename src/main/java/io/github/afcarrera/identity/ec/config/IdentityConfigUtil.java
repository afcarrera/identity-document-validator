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

package io.github.afcarrera.identity.ec.config;

import io.github.afcarrera.identity.ec.domain.IdentityDocument;
import io.github.afcarrera.identity.ec.handler.IdentityHandler;

import java.util.Arrays;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

/**
 * Utility class for identity configuration.
 * <p>Provides methods to link handlers in a chain and to retrieve configuration properties.</p>
 */
public class IdentityConfigUtil {

    /**
     * Name of the configuration properties file.
     */
    public static final String configPropertiesName = "config.properties";

    /**
     * Prefix used for configuration properties.
     */
    public static final String prefixValuePath = "io.github.afcarrera.identity.ec.";

    /**
     * Links a list of identity handlers into a chain.
     * @param first The first handler in the chain.
     * @param chain The list of handlers to be linked.
     * @return The first handler in the chain.
     */
    public static IdentityHandler<IdentityDocument> linkChain(IdentityHandler<IdentityDocument> first,
                                                              List<IdentityHandler<IdentityDocument>> chain) {
        IdentityHandler<IdentityDocument> head = first;
        for (IdentityHandler<IdentityDocument> nextInChain: chain) {
            head.setNextHandler(nextInChain);
            head = nextInChain;
        }
        return first;
    }

    /**
     * Converts a comma-separated string property into a list of integers.
     * @param property The comma-separated string property.
     * @return List of integers parsed from the property.
     */
    public static List<Integer> getIntegerListFromProperty(String property){
        return Arrays.stream(property.split(","))
                .map(Integer::parseInt)
                .collect(Collectors.toList());
    }

    /**
     * Converts a comma-separated string property into a set of strings.
     * @param property The comma-separated string property.
     * @return Set of strings parsed from the property.
     */
    public static Set<String> getStringSetFromProperty(String property){
        return Set.of(property.split(","));
    }
}
