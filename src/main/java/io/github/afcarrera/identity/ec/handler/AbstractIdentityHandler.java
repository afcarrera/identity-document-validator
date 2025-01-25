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

package io.github.afcarrera.identity.ec.handler;

import io.github.afcarrera.identity.ec.domain.IdentityDocument;

/**
 * Abstract class for handling identity document processing.
 * <p>Implements {@link IdentityHandler} for setting and checking the next handler in the chain.</p>
 */
public abstract class AbstractIdentityHandler implements IdentityHandler<IdentityDocument> {

    /**
     * The next handler in the chain of responsibility.
     */
    private IdentityHandler<IdentityDocument> nextHandler;

    /**
     * Checks the next handler in the chain of responsibility.
     * <p>If the next handler is not null, it processes the given context.</p>
     * @param context The context to be processed by the next handler.
     */
    @Override
    public void checkNextHandler(IdentityDocument context){
        if (nextHandler != null){
            nextHandler.process(context);
        }
    }

    /**
     * Sets the next handler in the chain of responsibility.
     * @param nextHandler The next handler in the chain.
     */
    @Override
    public void setNextHandler(IdentityHandler<IdentityDocument> nextHandler){
        this.nextHandler = nextHandler;
    }
}
