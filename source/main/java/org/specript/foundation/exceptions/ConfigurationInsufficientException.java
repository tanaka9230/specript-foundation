/* ======================================---------------------------

  --+ Specript Foundation Library v3.0 +--

  Copyright (c) 2007-2017, Koichi Tanaka (@Tanaka9230)

    ---------------------------====================================== */

package org.specript.foundation.exceptions;

public class ConfigurationInsufficientException extends RuntimeException {
    public ConfigurationInsufficientException() {
        super("OOPS!");
    }

    public ConfigurationInsufficientException(final String aMessage) {
        super(aMessage);
    }

    public ConfigurationInsufficientException(final Throwable aCause) {
        super(aCause);
    }

    public ConfigurationInsufficientException(final String aMessage, final Throwable aCause) {
        super(aMessage, aCause);
    }
}
