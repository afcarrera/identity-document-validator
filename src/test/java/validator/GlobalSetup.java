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
package validator;

import io.github.afcarrera.identity.ec.config.DocumentValidatorConfig;

/**
 * Global setup class to initialize the DocumentValidatorConfig once before running tests.
 */
public class GlobalSetup {

    /** Flag to indicate if the setup has been initialized. */
    private static volatile boolean initialized = false;

    /** Private constructor to prevent instantiation. */
    private GlobalSetup() {
    }

    /**
     * Sets up the global configuration by initializing the DocumentValidatorConfig.
     * This method ensures that the initialization happens only once in a thread-safe manner.
     */
    public static void setup() {
        if (initialized) {
            return;
        }
        synchronized (GlobalSetup.class) {
            if (!initialized) {
                initialized = true;
                DocumentValidatorConfig.init();
            }
        }
    }
}