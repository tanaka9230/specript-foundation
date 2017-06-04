
package org.specript.foundation.exceptions;

import static org.junit.Assert.assertEquals;
import org.junit.Test;
import java.util.MissingResourceException;

public class MissingResourceExceptionOpsTest {
    @Test
    public void case_withMessage() {
        final MissingResourceException ret = MissingResourceExceptionOps.with("the exception with a message");
        ret.printStackTrace();
        assertEquals("java.util.MissingResourceException: the exception with a message", ret.toString());
    }

    @Test
    public void case_withCause() {
        final MissingResourceException ret = MissingResourceExceptionOps.with(new IllegalArgumentException("something wrong"));
        ret.printStackTrace();
        assertEquals("java.util.MissingResourceException: Caused by: java.lang.IllegalArgumentException: something wrong", ret.toString());
    }

    @Test
    public void case_withMessageAndCause() {
        final MissingResourceException ret = MissingResourceExceptionOps.with("the exception with a message and a cause", new IllegalArgumentException("something wrong"));
        ret.printStackTrace();
        assertEquals("java.util.MissingResourceException: the exception with a message and a cause", ret.toString());
    }
}
