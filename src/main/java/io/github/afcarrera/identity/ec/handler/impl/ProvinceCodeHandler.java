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
import java.util.Set;

/**
 * Handler for validating the province code in an identity document.
 *
 * <p>This class extends {@link AbstractIdentityHandler} and checks if the province code within the
 * identity document is valid based on a set of allowed province codes.
 */
public class ProvinceCodeHandler extends AbstractIdentityHandler {

  /** Initial index for extracting the province code. */
  private final Integer initialIndex;

  /** Final index for extracting the province code. */
  private final Integer finalIndex;

  /** Set of valid province codes. */
  private final Set<String> provinceCodeSet;

  /**
   * Constructor for ProvinceCodeHandler.
   *
   * @param initialIndex The initial index for extracting the province code.
   * @param finalIndex The final index for extracting the province code.
   * @param provinceCodeSet The set of valid province codes.
   */
  public ProvinceCodeHandler(
      Integer initialIndex, Integer finalIndex, Set<String> provinceCodeSet) {
    this.initialIndex = initialIndex;
    this.finalIndex = finalIndex;
    this.provinceCodeSet = provinceCodeSet;
  }

  /**
   * Processes the identity document by validating its province code.
   *
   * @param context The identity document to be processed.
   * @throws IdentityDocumentException If the province code is not valid.
   */
  @Override
  public void process(IdentityDocument context) {
    String code = context.getValue().substring(initialIndex, finalIndex);
    if (!provinceCodeSet.contains(code)) {
      throw new IdentityDocumentException("Invalid province code.");
    }
    checkNextHandler(context);
  }
}
