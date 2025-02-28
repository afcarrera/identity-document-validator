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

import io.github.afcarrera.identity.ec.annotation.CI;
import io.github.afcarrera.identity.ec.processor.IdentityProcessor;
import io.github.afcarrera.identity.ec.processor.impl.CIProcessor;
import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

/**
 * Validator for CI (Identity Card) values. This class implements {@link ConstraintValidator} to
 * validate CI values using a chain of responsibility pattern and a template method.
 */
public class CIValidator implements ConstraintValidator<CI, String> {

  /**
   * Validates the given CI value.
   *
   * @param value The CI value to validate.
   * @param context Context in which the constraint is evaluated.
   * @return `true` if the CI value is valid, otherwise `false`.
   */
  @Override
  public boolean isValid(String value, ConstraintValidatorContext context) {
    IdentityProcessor identityProcessor = new CIProcessor();
    return identityProcessor.process(value);
  }
}
