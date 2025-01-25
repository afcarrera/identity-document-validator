/**
 * Copyright 2025 afcarrera
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package io.github.afcarrera.identity.ec.config;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

import static io.github.afcarrera.identity.ec.config.IdentityConfigUtil.configPropertiesName;

/**
 * Singleton configuration class for managing properties.
 * This class loads properties from a configuration file and provides
 * methods to access these properties.
 */
public class PropertiesConfig {

    /**
     * Logger for logging error messages.
     */
    private static final Logger log = LoggerFactory.getLogger(PropertiesConfig.class);

    /**
     * Singleton instance of PropertiesConfig.
     */
    private static volatile PropertiesConfig instance;

    /**
     * Properties object containing the configuration properties.
     */
    private final Properties properties;

    /**
     * Private constructor to prevent instantiation.
     * <p>Loads properties from the configuration file.</p>
     */
    public PropertiesConfig() {
        properties = new Properties();
        try (InputStream inputStream = getClass().getClassLoader().getResourceAsStream(configPropertiesName)) {
            properties.load(inputStream);
        } catch (IOException e) {
            log.error(e.getMessage());
        }
    }

    /**
     * Method to get the singleton instance of PropertiesConfig.
     * @return Singleton instance of PropertiesConfig.
     */
    public static PropertiesConfig getInstance(){
        PropertiesConfig result = instance;
        if (result != null) {
            return result;
        }
        synchronized(CIConfig.class) {
            if (instance == null) {
                instance = new PropertiesConfig();
            }
            return instance;
        }
    }

    /**
     * Method to get the properties object.
     * @return Properties object containing the configuration properties.
     */
    public Properties getProperties() {
        return properties;
    }
}
