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

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import io.github.afcarrera.identity.ec.validator.CIValidator;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

/** Unit tests for the {@link CIValidator} class. */
public class CIValidatorTest {

  /** Instance of the CIValidator to be tested. */
  CIValidator validator;

  /** Initializes the global setup before all tests are run. */
  @BeforeAll
  static void init() {
    GlobalSetup.setup();
  }

  /** Sets up the test environment by initializing the CIValidator instance. */
  @BeforeEach
  void setup() {
    validator = new CIValidator();
  }

  /** Tests the isValid method with a valid CI value. */
  @Test
  void testIsValid() {
    // A valid CI value
    String validValue = "2222222222";

    // Validate the CI value
    boolean result = validator.isValid(validValue, null);

    // Assert that the result is true
    assertTrue(result);
  }

  /**
   * Tests the isValid method with several invalid CI values.
   *
   * @param invalidValue The invalid CI value to be tested.
   */
  @ParameterizedTest
  @ValueSource(strings = {"1", "9999999999", "1717430101", "2222222223"})
  void testIsInvalid(String invalidValue) {
    // Validate the CI value
    boolean result = validator.isValid(invalidValue, null);

    // Assert that the result is false
    assertFalse(result);
  }
}
