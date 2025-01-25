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

package io.github.afcarrera.identity.ec.exception;

/**
 * Custom exception class for identity document errors.
 * <p>This class extends RuntimeException and is used to signal issues related to identity documents.</p>
 */
public class IdentityDocumentException extends RuntimeException {

    /**
     * Constructor for IdentityDocumentException.
     * @param message The error message to be associated with this exception.
     */
    public IdentityDocumentException(String message) {
        super(message);
    }
}
