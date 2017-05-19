/* ======================================---------------------------

  --+ Specript Foundation Library v3.0 +--

  Copyright (c) 2007-2017, Koichi Tanaka (@Tanaka9230)

    ---------------------------====================================== */

package org.specript.foundation.exceptions;

public class LogicIntegrityCorruptedException extends RuntimeException {
    public LogicIntegrityCorruptedException() {
        super("CAN'T BE!");
    }

    public LogicIntegrityCorruptedException(final String aMessage) {
        super(aMessage);
    }

    public LogicIntegrityCorruptedException(final Throwable aCause) {
        super(aCause);
    }

    public LogicIntegrityCorruptedException(final String aMessage, final Throwable aCause) {
        super(aMessage, aCause);
    }
}
