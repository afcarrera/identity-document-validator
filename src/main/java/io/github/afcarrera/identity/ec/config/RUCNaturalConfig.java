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
package io.github.afcarrera.identity.ec.config;

import io.github.afcarrera.identity.ec.domain.IdentityDocument;
import io.github.afcarrera.identity.ec.handler.IdentityHandler;
import io.github.afcarrera.identity.ec.handler.impl.*;
import java.util.List;

/**
 * Singleton configuration class for setting up the identity validation chain.
 *
 * <p>This class initializes and links various handlers to form a chain for validating identity
 * documents based on configured properties.
 */
public final class RUCNaturalConfig {

  /** First element in the identity handler chain. */
  private final IdentityHandler<IdentityDocument> firstChainElement;

  /**
   * <p>Retrieves configuration properties
   *
   * <p>Initialize the first element in the chain with various handlers
   */
  RUCNaturalConfig() {
    RUCNaturalPropertiesConfig rucNaturalPropertiesConfig =
        RUCNaturalPropertiesConfig.getInstance();
    firstChainElement =
        IdentityConfigUtil.linkChain(
            new RegexHandler(rucNaturalPropertiesConfig.getRegexp()),
            List.of(
                new SubStringExtractorHandler(
                    Integer.parseInt(rucNaturalPropertiesConfig.getCIInitialIndex()),
                    Integer.parseInt(rucNaturalPropertiesConfig.getCIFinalIndex()))));
  }

  /**
   * Method to get the singleton instance of RUCNaturalConfig.
   *
   * @return Singleton instance of RUCNaturalConfig.
   */
  public static RUCNaturalConfig getInstance() {
    return DocumentValidatorConfig.getBean(RUCNaturalConfig.class);
  }

  /**
   * Method to get the first element in the identity handler chain.
   *
   * @return First element in the identity handler chain.
   */
  public IdentityHandler<IdentityDocument> getFirstChainElement() {
    return firstChainElement;
  }
}
