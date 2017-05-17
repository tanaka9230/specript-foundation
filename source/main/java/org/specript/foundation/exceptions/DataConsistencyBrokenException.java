/* ======================================---------------------------

  --+ Specript Foundation Library v3.0 +--

  Copyright (c) 2007-2017, Koichi Tanaka (@Tanaka9230)

    ---------------------------====================================== */

package org.specript.foundation.exceptions;

public class DataConsistencyBrokenException extends RuntimeException {
    public DataConsistencyBrokenException() {
        super("TOO BAD!");
    }

    public DataConsistencyBrokenException(final String aMessage) {
        super(aMessage);
    }

    public DataConsistencyBrokenException(final Throwable aCause) {
        super(aCause);
    }

    public DataConsistencyBrokenException(final String aMessage, final Throwable aCause) {
        super(aMessage, aCause);
    }
}
