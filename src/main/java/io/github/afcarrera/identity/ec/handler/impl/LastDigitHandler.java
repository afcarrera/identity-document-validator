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
 * Handler for extracting the last digit from a specific index in the identity document's value.
 *
 * <p>This class extends {@link AbstractIdentityHandler} and extracts the digit at the specified
 * index in the identity document's value, then sets this digit as the last digit in the context.
 */
public class LastDigitHandler extends AbstractIdentityHandler {

  /** The index from which to extract the last digit. */
  private final Integer index;

  /**
   * Constructor for LastDigitHandler.
   *
   * @param index The index from which to extract the last digit.
   */
  public LastDigitHandler(Integer index) {
    this.index = index;
  }

  /**
   * Processes the identity document by extracting the digit at the specified index.
   *
   * <p>The extracted digit is then set as the last digit in the context.
   *
   * @param context The identity document to be processed.
   */
  @Override
  public void process(IdentityDocument context) {
    String value = context.getValue();
    char lastChar = value.charAt(index);
    Integer lastDigit = Character.getNumericValue(lastChar);
    context.setLastDigit(lastDigit);
    checkNextHandler(context);
  }
}
