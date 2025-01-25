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

package io.github.afcarrera.identity.ec.validator;

import io.github.afcarrera.identity.ec.annotation.CI;
import io.github.afcarrera.identity.ec.config.CIConfig;
import io.github.afcarrera.identity.ec.domain.IdentityDocument;
import io.github.afcarrera.identity.ec.exception.IdentityDocumentException;
import io.github.afcarrera.identity.ec.handler.IdentityHandler;
import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Validator for CI (Identity Card) values.
 * This class implements {@link ConstraintValidator} to validate CI values using a chain of responsibility pattern.
 */
public class CIValidator implements ConstraintValidator<CI, String> {

    /**
     * Logger for logging validation errors.
     */
    private static final Logger log = LoggerFactory.getLogger(CIValidator.class);

    /**
     * Validates the given CI value.
     * @param value The CI value to validate.
     * @param context Context in which the constraint is evaluated.
     * @return `true` if the CI value is valid, otherwise `false`.
     */
    @Override
    public boolean isValid(String value, ConstraintValidatorContext context) {
        IdentityHandler<IdentityDocument> firstChainElement =
                CIConfig.getInstance().getFirstChainElement();
        IdentityDocument identityDocument = new IdentityDocument();
        identityDocument.setValue(value);
        try{
            firstChainElement.process(identityDocument);
        }catch (IdentityDocumentException identityDocumentException){
            log.error(identityDocumentException.getMessage());
            return false;
        }
        return true;
    }
}
