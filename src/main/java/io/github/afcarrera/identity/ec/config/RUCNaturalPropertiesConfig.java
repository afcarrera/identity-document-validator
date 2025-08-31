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
 * Singleton configuration class for accessing RUCNatural properties.
 *
 * <p>This class retrieves properties from a properties file and provides methods to access various
 * RUCNatural-related configuration values.
 */
public class RUCNaturalPropertiesConfig {

  /** Properties object containing the configuration properties. */
  private final Properties properties;

  /** Prefix used for RUCNatural-related properties. */
  private final String rucNaturalPrefix;

  /** Initializes the properties object and sets the RUCNatural prefix. */
  RUCNaturalPropertiesConfig() {
    rucNaturalPrefix = prefixValuePath.concat("ruc-natural.");
    properties = PropertiesConfig.getInstance().getProperties();
  }

  /**
   * Method to get the singleton instance of RUCNaturalPropertiesConfig.
   *
   * @return Singleton instance of RUCNaturalPropertiesConfig.
   */
  public static RUCNaturalPropertiesConfig getInstance() {
    return DocumentValidatorConfig.getBean(RUCNaturalPropertiesConfig.class);
  }

  /**
   * Method to get the regular expression property.
   *
   * @return Regular expression property value.
   */
  public String getRegexp() {
    return properties.getProperty(rucNaturalPrefix.concat("regexp"));
  }

  /**
   * Method to get the CI initial index property.
   *
   * @return CI initial index property value.
   */
  public String getCIInitialIndex() {
    return properties.getProperty(rucNaturalPrefix.concat("ci.index.initial"));
  }

  /**
   * Method to get the CI final index property.
   *
   * @return CI final index property value.
   */
  public String getCIFinalIndex() {
    return properties.getProperty(rucNaturalPrefix.concat("ci.index.final"));
  }
}
