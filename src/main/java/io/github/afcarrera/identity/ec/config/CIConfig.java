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
public final class CIConfig {

  /** Singleton instance of CIConfig. */
  private static volatile CIConfig instance;

  /** First element in the identity handler chain. */
  private final IdentityHandler<IdentityDocument> firstChainElement;

  /**
   * Private constructor to prevent instantiation.
   *
   * <p>Retrieves configuration properties
   *
   * <p>Initialize the first element in the chain with various handlers
   */
  private CIConfig() {
    CIPropertiesConfig ciPropertiesConfig = CIPropertiesConfig.getInstance();
    firstChainElement =
        IdentityConfigUtil.linkChain(
            new RegexHandler(ciPropertiesConfig.getRegexp()),
            List.of(
                new ProvinceCodeHandler(
                    Integer.parseInt(ciPropertiesConfig.getProvinceInitialIndex()),
                    Integer.parseInt(ciPropertiesConfig.getProvinceFinalIndex()),
                    IdentityConfigUtil.getStringSetFromProperty(
                        ciPropertiesConfig.getProvinceList())),
                new StringToIntegerHandler(),
                new SubListExtractorHandler(
                    IdentityConfigUtil.getIntegerListFromProperty(
                        ciPropertiesConfig.getIndexList())),
                new CoefficientMultiplicationHandler(
                    IdentityConfigUtil.getIntegerListFromProperty(
                        ciPropertiesConfig.getCoefficientList())),
                new ListValueAdjusterHandler(
                    Boolean.parseBoolean(ciPropertiesConfig.getAdjuster()),
                    Integer.parseInt(ciPropertiesConfig.getAdjusterMaxValue()),
                    Integer.parseInt(ciPropertiesConfig.getAdjusterSubtrahend())),
                new ListSumHandler(),
                new LastCheckDigitModHandler(
                    Integer.parseInt(ciPropertiesConfig.getLastCheckDigitMaxValue()),
                    Integer.parseInt(ciPropertiesConfig.getLastCheckDigitDivisor())),
                new LastDigitHandler(Integer.parseInt(ciPropertiesConfig.getLastCheckDigitIndex())),
                new FirstComparisonHandler(
                    Integer.parseInt(ciPropertiesConfig.getFirstComparisonValue())),
                new SecondComparisonHandler(
                    Integer.parseInt(ciPropertiesConfig.getSecondComparisonMinuend()))));
  }

  /**
   * Method to get the singleton instance of CIConfig.
   *
   * @return Singleton instance of CIConfig.
   */
  public static CIConfig getInstance() {
    CIConfig result = instance;
    if (result != null) {
      return result;
    }
    synchronized (CIConfig.class) {
      if (instance == null) {
        instance = new CIConfig();
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
