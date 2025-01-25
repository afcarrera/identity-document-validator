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

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Handler for validating identity document values against a regular expression.
 * <p>This class extends {@link AbstractIdentityHandler} and checks if the value of the identity document
 * matches the provided regular expression.</p>
 */
public class RegexHandler extends AbstractIdentityHandler {

    /**
     * The regular expression used for validation.
     */
    private final String regex;

    /**
     * Constructor for RegexHandler.
     * @param regex The regular expression to be used for validation.
     */
    public RegexHandler(String regex) {
        this.regex = regex;
    }

    /**
     * Processes the identity document by validating its value against the regular expression.
     * @param context The identity document to be processed.
     * @throws IdentityDocumentException If the value does not match the regular expression.
     */
    @Override
    public void process(IdentityDocument context) {
        Pattern pattern = Pattern.compile(regex);
        Matcher matcher = pattern.matcher(context.getValue());
        if (!matcher.matches()){
            throw new IdentityDocumentException("Invalid regex.");
        }
        checkNextHandler(context);
    }
}
