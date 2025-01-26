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
package io.github.afcarrera.identity.ec.annotation;

import io.github.afcarrera.identity.ec.validator.RUCNaturalValidator;
import jakarta.validation.Constraint;
import jakarta.validation.Payload;
import java.lang.annotation.*;

/** Custom annotation for validating identification. */
@Constraint(validatedBy = RUCNaturalValidator.class)
@Retention(RetentionPolicy.RUNTIME)
@Target({ElementType.METHOD, ElementType.FIELD, ElementType.PARAMETER})
@Repeatable(RUCNatural.List.class)
public @interface RUCNatural {

  /**
   * Error message that will be shown if the validation fails.
   *
   * @return Default error message.
   */
  String message() default "Invalid identification.";

  /**
   * Allows specifying validation groups, if needed.
   *
   * @return Array of group classes.
   */
  Class<?>[] groups() default {};

  /**
   * Allows adding additional information about the payload of the annotation.
   *
   * @return Array of payload classes.
   */
  Class<? extends Payload>[] payload() default {};

  /** Internal annotation that allows repeating the @RUCNatural annotation on the same element. */
  @Retention(RetentionPolicy.RUNTIME)
  @Target({ElementType.METHOD, ElementType.FIELD, ElementType.PARAMETER})
  @interface List {

    /**
     * Array of @RUCNatural annotations.
     *
     * @return Array of @RUCNatural annotations.
     */
    RUCNatural[] value();
  }
}
