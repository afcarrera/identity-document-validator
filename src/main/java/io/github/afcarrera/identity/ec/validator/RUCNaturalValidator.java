/**
 * Copyright 2025 afcarrera
 *
 * <p>Licensed under the Apache License, Version 2.0 (the "License"); you may not use this file
 * except in compliance with the License. You may obtain a copy of the License at
 *
 * <p>http://www.apache.org/licenses/LICENSE-2.0
 *
 * <p>Unless required by applicable law or agreed to in writing, software distributed under the
 * License is distributed on an "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either
 * express or implied. See the License for the specific language governing permissions and
 * limitations under the License.
 */
package io.github.afcarrera.identity.ec.validator;

import io.github.afcarrera.identity.ec.annotation.RUCNatural;
import io.github.afcarrera.identity.ec.processor.IdentityProcessor;
import io.github.afcarrera.identity.ec.processor.impl.RUCNaturalProcessor;
import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

/**
 * Validator for RUC (Single Taxpayer Registry) natural values. This class implements {@link
 * ConstraintValidator} to validate RUCNatural values using a chain of responsibility pattern and a
 * template method.
 */
public class RUCNaturalValidator implements ConstraintValidator<RUCNatural, String> {

  /**
   * Validates the given RUCNatural value.
   *
   * @param value The RUCNatural value to validate.
   * @param context Context in which the constraint is evaluated.
   * @return `true` if the RUCNatural value is valid, otherwise `false`.
   */
  @Override
  public boolean isValid(String value, ConstraintValidatorContext context) {
    IdentityProcessor identityProcessor = new RUCNaturalProcessor();
    CIValidator ciValidator = new CIValidator();
    return identityProcessor.process(value)
        && ciValidator.isValid(identityProcessor.getIdentityDocument().getValue(), context);
  }
}
