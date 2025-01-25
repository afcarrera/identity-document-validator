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
package io.github.afcarrera.identity.ec.domain;

import java.util.List;

/**
 * Class representing an identity document.
 *
 * <p>This class contains various fields related to the identity document's value, including lists
 * of integers and specific digits used for validation.
 */
public class IdentityDocument {

  /** The value of the identity document as a string. */
  private String value;

  /** A list of integer values derived from the identity document. */
  private List<Integer> valueList;

  /** A sublist of integer values derived from the identity document. */
  private List<Integer> valueSublist;

  /** A list of integer values used for multiplication in validation processes. */
  private List<Integer> multiplicationList;

  /** The last check digit of the identity document. */
  private Integer lastCheckDigit;

  /** The last digit of the identity document. */
  private Integer lastDigit;

  /** Default constructor. */
  public IdentityDocument() {}

  /**
   * Gets the value of the identity document.
   *
   * @return The value of the identity document.
   */
  public String getValue() {
    return value;
  }

  /**
   * Sets the value of the identity document.
   *
   * @param value The value to set.
   */
  public void setValue(String value) {
    this.value = value;
  }

  /**
   * Gets the list of integer values derived from the identity document.
   *
   * @return The list of integer values.
   */
  public List<Integer> getValueList() {
    return valueList;
  }

  /**
   * Sets the list of integer values derived from the identity document.
   *
   * @param valueList The list of integer values to set.
   */
  public void setValueList(List<Integer> valueList) {
    this.valueList = valueList;
  }

  /**
   * Gets the sublist of integer values derived from the identity document.
   *
   * @return The sublist of integer values.
   */
  public List<Integer> getValueSublist() {
    return valueSublist;
  }

  /**
   * Sets the sublist of integer values derived from the identity document.
   *
   * @param valueSublist The sublist of integer values to set.
   */
  public void setValueSublist(List<Integer> valueSublist) {
    this.valueSublist = valueSublist;
  }

  /**
   * Gets the list of integer values used for multiplication in validation processes.
   *
   * @return The list of integer values used for multiplication.
   */
  public List<Integer> getMultiplicationList() {
    return multiplicationList;
  }

  /**
   * Sets the list of integer values used for multiplication in validation processes.
   *
   * @param multiplicationList The list of integer values to set.
   */
  public void setMultiplicationList(List<Integer> multiplicationList) {
    this.multiplicationList = multiplicationList;
  }

  /**
   * Gets the last check digit of the identity document.
   *
   * @return The last check digit.
   */
  public Integer getLastCheckDigit() {
    return lastCheckDigit;
  }

  /**
   * Sets the last check digit of the identity document.
   *
   * @param lastCheckDigit The last check digit to set.
   */
  public void setLastCheckDigit(Integer lastCheckDigit) {
    this.lastCheckDigit = lastCheckDigit;
  }

  /**
   * Gets the last digit of the identity document.
   *
   * @return The last digit.
   */
  public Integer getLastDigit() {
    return lastDigit;
  }

  /**
   * Sets the last digit of the identity document.
   *
   * @param lastDigit The last digit to set.
   */
  public void setLastDigit(Integer lastDigit) {
    this.lastDigit = lastDigit;
  }
}
