/* ======================================---------------------------

  --+ Specript Foundation Library v3.0 +--

  Copyright (c) 2007-2017, Koichi Tanaka (@Tanaka9230)

    ---------------------------====================================== */

package org.specript.foundation.exceptions;

public class InsufficientConfigurationException extends RuntimeException {
    public InsufficientConfigurationException() {
        super("OOPS!");
    }

    public InsufficientConfigurationException(final String aMessage) {
        super(aMessage);
    }

    public InsufficientConfigurationException(final Throwable aCause) {
        super(aCause);
    }

    public InsufficientConfigurationException(final String aMessage, final Throwable aCause) {
        super(aMessage, aCause);
    }
}
