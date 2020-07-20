package org.example;

/** Some application Exceptions should be unchecked.
 * Gosling et. al. are wrong on this point.
 * The only way to do that is descent from RuntimeException.
 */

class ModelException extends RuntimeException {
    public ModelException(String reason) {
        super(reason);
    }
}
