
package org.specript.foundation;

import static org.specript.foundation.IntegerOps.from;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.fail;
import org.junit.Test;
import java.io.Serializable;

public class IntegerOpsTest {
    @Test
    public void case_fromObject() {
        {
            final Serializable arg = null;
            final Integer ret = from(arg);
            assertNull(ret);
        }
        {
            final Serializable arg = "";
            final Integer ret = from(arg);
            assertNull(ret);
        }
        {
            final Serializable arg = "  ";
            final Integer ret = from(arg);
            assertNull(ret);
        }
        {
            final Serializable arg = "12345";
            final Integer ret = from(arg);
            assertEquals(Integer.valueOf(12345), ret);
        }
        {
            final Serializable arg = "12345.6";
            try {
                @SuppressWarnings("unused")
                final Integer ret = from(arg);
                fail();
            } catch (final ArithmeticException e) {
                assertEquals("Rounding necessary", e.getMessage());
            }
        }
        {
            final Serializable arg = Integer.valueOf(12345);
            final Integer ret = from(arg);
            assertEquals(Integer.valueOf(12345), ret);
        }
        {
            final Serializable arg = Long.valueOf(Integer.MAX_VALUE);
            final Integer ret = from(arg);
            assertEquals(Integer.valueOf(Integer.MAX_VALUE), ret);
        }
        {
            final Serializable arg = Long.valueOf(Integer.MAX_VALUE + 1L);
            try {
                @SuppressWarnings("unused")
                final Integer ret = from(arg);
                fail();
            } catch (final ArithmeticException e) {
                assertEquals("integer overflow", e.getMessage());
            }
        }
    }

    @Test
    public void case_fromString() {
        {
            final String arg = null;
            final Integer ret = from(arg);
            assertNull(ret);
        }
        {
            final String arg = "";
            final Integer ret = from(arg);
            assertNull(ret);
        }
        {
            final String arg = "  ";
            final Integer ret = from(arg);
            assertNull(ret);
        }
        {
            final String arg = "12345";
            final Integer ret = from(arg);
            assertEquals(Integer.valueOf(12345), ret);
        }
        {
            final String arg = "12345.6";
            try {
                @SuppressWarnings("unused")
                final Integer ret = from(arg);
                fail();
            } catch (final ArithmeticException e) {
                assertEquals("Rounding necessary", e.getMessage());
            }
        }
        {
            final String arg = "12345678901234567890";
            try {
                @SuppressWarnings("unused")
                final Integer ret = from(arg);
                fail();
            } catch (final ArithmeticException e) {
                assertEquals("Overflow", e.getMessage());
            }
        }
    }

    @Test
    public void case_fromInteger() {
        {
            final int arg = 12345;
            final Integer ret = from(arg);
            assertEquals(Integer.valueOf(12345), ret);
        }
        {
            final Integer arg = null;
            final Integer ret = from(arg);
            assertNull(ret);
        }
        {
            final Integer arg = Integer.valueOf(12345);
            final Integer ret = from(arg);
            assertEquals(Integer.valueOf(12345), ret);
        }
    }

    @Test
    public void case_fromLong() {
        {
            final long arg = Integer.MAX_VALUE;
            final Integer ret = from(arg);
            assertEquals(Integer.valueOf(Integer.MAX_VALUE), ret);
        }
        {
            final long arg = Integer.MAX_VALUE + 1L;
            try {
                @SuppressWarnings("unused")
                final Integer ret = from(arg);
                fail();
            } catch (final ArithmeticException e) {
                assertEquals("integer overflow", e.getMessage());
            }
        }
        {
            final Long arg = null;
            final Integer ret = from(arg);
            assertNull(ret);
        }
        {
            final Long arg = Long.valueOf(Integer.MAX_VALUE);
            final Integer ret = from(arg);
            assertEquals(Integer.valueOf(Integer.MAX_VALUE), ret);
        }
        {
            final Long arg = Long.valueOf(Integer.MAX_VALUE + 1L);
            try {
                @SuppressWarnings("unused")
                final Integer ret = from(arg);
                fail();
            } catch (final ArithmeticException e) {
                assertEquals("integer overflow", e.getMessage());
            }
        }
    }
}
