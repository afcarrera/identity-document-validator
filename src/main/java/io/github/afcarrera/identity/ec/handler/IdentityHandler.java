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
package io.github.afcarrera.identity.ec.handler;

/**
 * Interface for handling identity document processing.
 *
 * <p>This interface supports setting the next handler in a chain, processing the identity document,
 * and checking the next handler.
 *
 * @param <T> The type of the context being processed.
 */
public interface IdentityHandler<T> {

  /**
   * Sets the next handler in the chain of responsibility.
   *
   * @param nextHandler The next handler in the chain.
   */
  void setNextHandler(IdentityHandler<T> nextHandler);

  /**
   * Processes the identity document context.
   *
   * @param context The context to be processed.
   */
  void process(T context);

  /**
   * Checks the next handler in the chain of responsibility.
   *
   * @param context The context to be processed by the next handler.
   */
  void checkNextHandler(T context);
}
