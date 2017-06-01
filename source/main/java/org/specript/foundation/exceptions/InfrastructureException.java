/* ======================================---------------------------

  --+ Specript Foundation Library v3.0 +--

  Copyright (c) 2007-2017, Koichi Tanaka (@Tanaka9230)

    ---------------------------====================================== */

package org.specript.foundation.exceptions;

public class InfrastructureException extends RuntimeException {
    public InfrastructureException(final String aMessage) {
        super(aMessage);
    }

    public InfrastructureException(final Throwable aCause) {
        super(aCause);
    }

    public InfrastructureException(final String aMessage, final Throwable aCause) {
        super(aMessage, aCause);
    }
}
