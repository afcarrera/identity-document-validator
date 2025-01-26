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
package io.github.afcarrera.identity.ec.processor;

import io.github.afcarrera.identity.ec.domain.IdentityDocument;
import io.github.afcarrera.identity.ec.exception.IdentityDocumentException;
import io.github.afcarrera.identity.ec.handler.IdentityHandler;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Abstract base class for processing identity values and managing identity documents.
 *
 * <p>This class implements {@link IdentityProcessor} and provides common functionality for
 * processing the identity document.
 */
public abstract class AbstractIdentityProcessor implements IdentityProcessor {

  /** Identity document associated with the processor. */
  private IdentityDocument identityDocument;

  /** Logger for logging validation errors. */
  private static final Logger log = LoggerFactory.getLogger(AbstractIdentityProcessor.class);

  /** Constructor for AbstractIdentityProcessor. */
  protected AbstractIdentityProcessor() {
    this.identityDocument = new IdentityDocument();
  }

  /** {@inheritDoc} */
  @Override
  public final boolean process(String value) {
    IdentityHandler<IdentityDocument> firstChainElement = getFirstChainElement();
    setIdentityDocumentValue(value);
    return processChain(firstChainElement, identityDocument);
  }

  /** {@inheritDoc} */
  @Override
  public IdentityDocument getIdentityDocument() {
    return identityDocument;
  }

  /**
   * Gets the first element in the chain of identity handlers.
   *
   * @return The first identity handler in the chain.
   */
  protected abstract IdentityHandler<IdentityDocument> getFirstChainElement();

  /**
   * Sets the value of the identity document.
   *
   * @param value The value to be set in the identity document.
   * @return The updated identity document.
   */
  protected IdentityDocument setIdentityDocumentValue(String value) {
    this.identityDocument.setValue(value);
    return identityDocument;
  }

  /**
   * Processes the identity document through the chain of handlers.
   *
   * @param firstChainElement The first element in the chain of identity handlers.
   * @param identityDocument The identity document to be processed.
   * @return `true` if the processing is successful, otherwise `false`.
   */
  protected boolean processChain(
      IdentityHandler<IdentityDocument> firstChainElement, IdentityDocument identityDocument) {
    try {
      firstChainElement.process(identityDocument);
    } catch (IdentityDocumentException identityDocumentException) {
      log.error(identityDocumentException.getMessage());
      return false;
    }
    return true;
  }
}
