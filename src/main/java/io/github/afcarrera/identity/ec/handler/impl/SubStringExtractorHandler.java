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
 * Handler for extracting a substring from the identity document's value based on initial and final
 * indexes.
 *
 * <p>This class extends {@link AbstractIdentityHandler} and extracts a substring from the value of
 * the identity document using the provided indexes, then sets this value in the context.
 */
public class SubStringExtractorHandler extends AbstractIdentityHandler {

  /** Initial index for extracting the value. */
  private final Integer initialIndex;

  /** Final index for extracting the value. */
  private final Integer finalIndex;

  /**
   * Constructor for SubStringExtractorHandler.
   *
   * @param initialIndex The initial index for extracting the province code.
   * @param finalIndex The final index for extracting the province code.
   * @param provinceCodeSet The set of valid province codes.
   */
  public SubStringExtractorHandler(Integer initialIndex, Integer finalIndex) {
    this.initialIndex = initialIndex;
    this.finalIndex = finalIndex;
  }

  /**
   * Processes the identity document by extracting a sub value from its value.
   *
   * <p>The sub value is then set in the context.
   *
   * @param context The identity document to be processed.
   */
  @Override
  public void process(IdentityDocument context) {
    context.setValue(context.getValue().substring(initialIndex, finalIndex));
    checkNextHandler(context);
  }
}
