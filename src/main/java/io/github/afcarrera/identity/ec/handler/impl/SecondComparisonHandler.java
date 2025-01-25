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
import io.github.afcarrera.identity.ec.exception.IdentityDocumentException;
import io.github.afcarrera.identity.ec.handler.AbstractIdentityHandler;

/**
 * Handler for performing a second comparison check on the identity document.
 *
 * <p>This class extends {@link AbstractIdentityHandler} and compares the result of subtracting the
 * last check digit from a specified minuend with the last digit. If they do not match, an exception
 * is thrown.
 */
public class SecondComparisonHandler extends AbstractIdentityHandler {

  /** The minuend used for the subtraction in the comparison. */
  private final Integer minuend;

  /**
   * Constructor for SecondComparisonHandler.
   *
   * @param minuend The minuend used for the subtraction in the comparison.
   */
  public SecondComparisonHandler(Integer minuend) {
    this.minuend = minuend;
  }

  /**
   * Processes the identity document by performing the second comparison check.
   *
   * <p>Compares the result of subtracting the last check digit from the minuend with the last
   * digit.
   *
   * @param context The identity document to be processed.
   * @throws IdentityDocumentException If the result of the subtraction does not match the last
   *     digit.
   */
  @Override
  public void process(IdentityDocument context) {
    Integer subtract = minuend - context.getLastCheckDigit();
    if (!subtract.equals(context.getLastDigit())) {
      throw new IdentityDocumentException("Invalid last check digit on second comparison.");
    }
    checkNextHandler(context);
  }
}
