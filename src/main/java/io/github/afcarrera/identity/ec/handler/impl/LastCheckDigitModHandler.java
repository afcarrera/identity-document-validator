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
package io.github.afcarrera.identity.ec.handler.impl;

import io.github.afcarrera.identity.ec.domain.IdentityDocument;
import io.github.afcarrera.identity.ec.handler.AbstractIdentityHandler;

/**
 * Handler for modifying the last check digit of an identity document.
 *
 * <p>This class extends {@link AbstractIdentityHandler} and modifies the last check digit if it is
 * greater than or equal to a specified maximum value, using a given divisor.
 */
public class LastCheckDigitModHandler extends AbstractIdentityHandler {

  /** The maximum value threshold for modification. */
  private final Integer maxValue;

  /** The divisor used for the modification. */
  private final Integer divisor;

  /**
   * Constructor for LastCheckDigitModHandler.
   *
   * @param maxValue The maximum value threshold for modification.
   * @param divisor The divisor used for the modification.
   */
  public LastCheckDigitModHandler(Integer maxValue, Integer divisor) {
    this.maxValue = maxValue;
    this.divisor = divisor;
  }

  /**
   * Processes the identity document by modifying its last check digit.
   *
   * <p>If the last check digit is greater than or equal to maxValue, it is replaced with the
   * remainder of its division by divisor.
   *
   * @param context The identity document to be processed.
   */
  @Override
  public void process(IdentityDocument context) {
    Integer lastCheckDigit = context.getLastCheckDigit();
    if (lastCheckDigit >= maxValue) {
      context.setLastCheckDigit(lastCheckDigit % divisor);
    }
    checkNextHandler(context);
  }
}
