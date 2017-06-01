/* ======================================---------------------------

  --+ Specript Foundation Library v3.0 +--

  Copyright (c) 2007-2017, Koichi Tanaka (@Tanaka9230)

    ---------------------------====================================== */

package org.specript.foundation.exceptions;

public class InvalidSettingsException extends InfrastructureException {
    public InvalidSettingsException(final String aMessage) {
        super(aMessage);
    }

    public InvalidSettingsException(final Throwable aCause) {
        super(aCause);
    }

    public InvalidSettingsException(final String aMessage, final Throwable aCause) {
        super(aMessage, aCause);
    }
}
