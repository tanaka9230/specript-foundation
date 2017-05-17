/* ======================================---------------------------

  --+ Specript Foundation Library v3.0 +--

  Copyright (c) 2007-2017, Koichi Tanaka (@Tanaka9230)

    ---------------------------====================================== */

package org.specript.foundation.exceptions;

import java.util.MissingResourceException;

public class MissingResourceExceptionOps {
    /*
     * to prevent to be called.
     */
    private MissingResourceExceptionOps() {
    }

    public static MissingResourceException with(final String aMessage) {
        return with(aMessage, null);
    }

    public static MissingResourceException with(final Throwable aCause) {
        return with("Caused by: " + aCause.toString(), aCause);
    }

    public static MissingResourceException with(final String aMessage, final Throwable aCause) {
        return (MissingResourceException)new MissingResourceException(aMessage, null, null).initCause(aCause);
    }
}
