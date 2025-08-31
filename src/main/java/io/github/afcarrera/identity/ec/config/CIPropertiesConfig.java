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

import static io.github.afcarrera.identity.ec.config.IdentityConfigUtil.prefixValuePath;

import java.util.Properties;

/**
 * Singleton configuration class for accessing CI properties.
 *
 * <p>This class retrieves properties from a properties file and provides methods to access various
 * CI-related configuration values.
 */
public class CIPropertiesConfig {

  /** Properties object containing the configuration properties. */
  private final Properties properties;

  /** Prefix used for CI-related properties. */
  private final String ciPrefix;

  /**
   * Initializes the properties object and sets the CI prefix.
   */
  CIPropertiesConfig() {
    ciPrefix = prefixValuePath.concat("ci.");
    properties = PropertiesConfig.getInstance().getProperties();
  }

  /**
   * Method to get the singleton instance of CIPropertiesConfig.
   *
   * @return Singleton instance of CIPropertiesConfig.
   */
  public static CIPropertiesConfig getInstance() {
    return DocumentValidatorConfig.getBean(CIPropertiesConfig.class);
  }

  /**
   * Method to get the regular expression property.
   *
   * @return Regular expression property value.
   */
  public String getRegexp() {
    return properties.getProperty(ciPrefix.concat("regexp"));
  }

  /**
   * Method to get the province initial index property.
   *
   * @return Province initial index property value.
   */
  public String getProvinceInitialIndex() {
    return properties.getProperty(ciPrefix.concat("province.index.initial"));
  }

  /**
   * Method to get the province final index property.
   *
   * @return Province final index property value.
   */
  public String getProvinceFinalIndex() {
    return properties.getProperty(ciPrefix.concat("province.index.final"));
  }

  /**
   * Method to get the province list property.
   *
   * @return Province list property value.
   */
  public String getProvinceList() {
    return properties.getProperty(ciPrefix.concat("list.province"));
  }

  /**
   * Method to get the index list property.
   *
   * @return Index list property value.
   */
  public String getIndexList() {
    return properties.getProperty(ciPrefix.concat("list.index"));
  }

  /**
   * Method to get the coefficient list property.
   *
   * @return Coefficient list property value.
   */
  public String getCoefficientList() {
    return properties.getProperty(ciPrefix.concat("list.coefficient"));
  }

  /**
   * Method to get the adjuster property.
   *
   * @return Adjuster property value.
   */
  public String getAdjuster() {
    return properties.getProperty(ciPrefix.concat("adjuster"));
  }

  /**
   * Method to get the last check digit index property.
   *
   * @return Last check digit index property value.
   */
  public String getLastCheckDigitIndex() {
    return properties.getProperty(ciPrefix.concat("last-check-digit.index"));
  }

  /**
   * Method to get the adjuster max value property.
   *
   * @return Adjuster max value property value.
   */
  public String getAdjusterMaxValue() {
    return properties.getProperty(ciPrefix.concat("adjuster.max-value"));
  }

  /**
   * Method to get the adjuster subtrahend property.
   *
   * @return Adjuster subtrahend property value.
   */
  public String getAdjusterSubtrahend() {
    return properties.getProperty(ciPrefix.concat("adjuster.subtrahend"));
  }

  /**
   * Method to get the last check digit max value property.
   *
   * @return Last check digit max value property value.
   */
  public String getLastCheckDigitMaxValue() {
    return properties.getProperty(ciPrefix.concat("last-check-digit.max-value"));
  }

  /**
   * Method to get the last check digit divisor property.
   *
   * @return Last check digit divisor property value.
   */
  public String getLastCheckDigitDivisor() {
    return properties.getProperty(ciPrefix.concat("last-check-digit.divisor"));
  }

  /**
   * Method to get the first comparison value property.
   *
   * @return First comparison value property value.
   */
  public String getFirstComparisonValue() {
    return properties.getProperty(ciPrefix.concat("comparison.value"));
  }

  /**
   * Method to get the second comparison minuend property.
   *
   * @return Second comparison minuend property value.
   */
  public String getSecondComparisonMinuend() {
    return properties.getProperty(ciPrefix.concat("comparison.minuend"));
  }
}
