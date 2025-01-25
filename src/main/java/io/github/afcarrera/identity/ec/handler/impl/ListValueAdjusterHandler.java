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
package io.github.afcarrera.identity.ec.handler.impl;

import io.github.afcarrera.identity.ec.domain.IdentityDocument;
import io.github.afcarrera.identity.ec.handler.AbstractIdentityHandler;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Handler for adjusting values in the multiplication list of an identity document.
 *
 * <p>This class extends {@link AbstractIdentityHandler} and adjusts values in the multiplication
 * list based on the specified conditions, then sets the modified list in the context.
 */
public class ListValueAdjusterHandler extends AbstractIdentityHandler {

  /** Flag indicating whether the values need to be adjusted. */
  private final boolean hasToAdjust;

  /** The maximum value threshold for adjustment. */
  private final Integer maxValue;

  /** The value to subtract from elements that meet the adjustment condition. */
  private final Integer subtrahend;

  /**
   * Constructor for ListValueAdjusterHandler.
   *
   * @param hasToAdjust Flag indicating whether the values need to be adjusted.
   * @param maxValue The maximum value threshold for adjustment.
   * @param subtrahend The value to subtract from elements that meet the adjustment condition.
   */
  public ListValueAdjusterHandler(boolean hasToAdjust, Integer maxValue, Integer subtrahend) {
    this.hasToAdjust = hasToAdjust;
    this.maxValue = maxValue;
    this.subtrahend = subtrahend;
  }

  /**
   * Processes the identity document by adjusting values in its multiplication list.
   *
   * <p>If the value is greater than or equal to maxValue, subtracts subtrahend from it.
   *
   * <p>The modified list is then set in the context.
   *
   * @param context The identity document to be processed.
   */
  @Override
  public void process(IdentityDocument context) {
    if (hasToAdjust) {
      List<Integer> modList =
          context.getMultiplicationList().stream()
              .map(value -> value >= maxValue ? value - subtrahend : value)
              .collect(Collectors.toList());
      context.setMultiplicationList(modList);
    }
    checkNextHandler(context);
  }
}
