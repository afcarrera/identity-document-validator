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

import io.github.afcarrera.identity.ec.exception.IdentityDocumentException;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Singleton configuration class that holds instances of various configuration classes.
 *
 * <p>This class initializes and provides access to singleton instances of configuration classes
 * such as PropertiesConfig, CIPropertiesConfig, CIConfig, RUCNaturalPropertiesConfig, and
 * RUCNaturalConfig.
 */
public class DocumentValidatorConfig {

  /** Logger for logging error messages. */
  private static final Logger log = LoggerFactory.getLogger(DocumentValidatorConfig.class);

  /** Map holding singleton instances of configuration classes. */
  private static final Map<Class<?>, Object> SINGLETONS = new ConcurrentHashMap<>();

  /** Atomic boolean to ensure initialization happens only once. */
  private static volatile boolean initialized = false;

  /** Private constructor to prevent instantiation. */
  private DocumentValidatorConfig() {}

  /**
   * Method to get the singleton instance of a configuration class.
   *
   * @param clazz Class of the configuration to retrieve.
   * @param <T> Type of the configuration class.
   * @return Singleton instance of the specified configuration class.
   * @throws IdentityDocumentException if the class is not managed as a singleton.
   */
  public static <T> T getBean(Class<T> clazz) {
    if (!SINGLETONS.containsKey(clazz)) {
      throw new IdentityDocumentException(
          "Class " + clazz.getName() + " is not a managed singleton.");
    }
    return clazz.cast(SINGLETONS.get(clazz));
  }

  /** Initializes the singleton instances of configuration classes. */
  public static void init() {
    if (initialized) {
      return;
    }
    synchronized (DocumentValidatorConfig.class) {
      if (!initialized) {
        log.info("Initializing DocumentValidatorConfig singletons...");
        initialized = true;
        SINGLETONS.putIfAbsent(PropertiesConfig.class, new PropertiesConfig());
        SINGLETONS.putIfAbsent(CIPropertiesConfig.class, new CIPropertiesConfig());
        SINGLETONS.putIfAbsent(CIConfig.class, new CIConfig());
        SINGLETONS.putIfAbsent(RUCNaturalPropertiesConfig.class, new RUCNaturalPropertiesConfig());
        SINGLETONS.putIfAbsent(RUCNaturalConfig.class, new RUCNaturalConfig());
      }
    }
  }
}
