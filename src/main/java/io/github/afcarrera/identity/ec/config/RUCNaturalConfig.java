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

  /** Singleton instance of RUCNaturalConfig. */
  private static volatile RUCNaturalConfig instance;

  /** First element in the identity handler chain. */
  private final IdentityHandler<IdentityDocument> firstChainElement;

  /**
   * Private constructor to prevent instantiation.
   *
   * <p>Retrieves configuration properties
   *
   * <p>Initialize the first element in the chain with various handlers
   */
  private RUCNaturalConfig() {
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
    RUCNaturalConfig result = instance;
    if (result != null) {
      return result;
    }
    synchronized (RUCNaturalConfig.class) {
      if (instance == null) {
        instance = new RUCNaturalConfig();
      }
      return instance;
    }
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
