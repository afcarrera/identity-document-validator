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
package io.github.afcarrera.identity.ec.processor;

import io.github.afcarrera.identity.ec.domain.IdentityDocument;

/** Interface for processing identity documents. */
public interface IdentityProcessor {

  /**
   * Processes the given identity value.
   *
   * @param value The identity value to be processed.
   * @return `true` if the processing is successful, otherwise `false`.
   */
  boolean process(String value);

  /**
   * Retrieves the identity document associated with the processor.
   *
   * @return The identity document.
   */
  IdentityDocument getIdentityDocument();
}
