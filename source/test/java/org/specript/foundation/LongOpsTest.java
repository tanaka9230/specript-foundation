
package org.specript.foundation;

import static org.specript.foundation.LongOps.from;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.fail;
import org.junit.Test;
import java.io.Serializable;
import java.math.BigInteger;

public class LongOpsTest {
    @Test
    public void case_fromObject() {
        {
            final Serializable arg = null;
            final Long ret = from(arg);
            assertNull(ret);
        }
        {
            final Serializable arg = "";
            final Long ret = from(arg);
            assertNull(ret);
        }
        {
            final Serializable arg = "  ";
            final Long ret = from(arg);
            assertNull(ret);
        }
        {
            final Serializable arg = "12345";
            final Long ret = from(arg);
            assertEquals(Long.valueOf(12345L), ret);
        }
        {
            final Serializable arg = "12345.6";
            try {
                @SuppressWarnings("unused")
                final Long ret = from(arg);
                fail();
            } catch (final ArithmeticException e) {
                assertEquals("Rounding necessary", e.getMessage());
            }
        }
        {
            final Serializable arg = Long.valueOf(12345L);
            final Long ret = from(arg);
            assertEquals(Long.valueOf(12345L), ret);
        }
        {
            final Serializable arg = BigInteger.valueOf(Long.MAX_VALUE);
            final Long ret = from(arg);
            assertEquals(Long.valueOf(Long.MAX_VALUE), ret);
        }
        {
            final Serializable arg = BigInteger.valueOf(Long.MAX_VALUE).add(BigInteger.ONE);
            try {
                @SuppressWarnings("unused")
                final Long ret = from(arg);
                fail();
            } catch (final ArithmeticException e) {
                assertEquals("BigInteger out of long range", e.getMessage());
            }
        }
    }

    @Test
    public void case_fromString() {
        {
            final String arg = null;
            final Long ret = from(arg);
            assertNull(ret);
        }
        {
            final String arg = "";
            final Long ret = from(arg);
            assertNull(ret);
        }
        {
            final String arg = "  ";
            final Long ret = from(arg);
            assertNull(ret);
        }
        {
            final String arg = "12345";
            final Long ret = from(arg);
            assertEquals(Long.valueOf(12345), ret);
        }
        {
            final String arg = "12345.6";
            try {
                @SuppressWarnings("unused")
                final Long ret = from(arg);
                fail();
            } catch (final ArithmeticException e) {
                assertEquals("Rounding necessary", e.getMessage());
            }
        }
        {
            final String arg = "12345678901234567890";
            try {
                @SuppressWarnings("unused")
                final Long ret = from(arg);
                fail();
            } catch (final ArithmeticException e) {
                assertEquals("Overflow", e.getMessage());
            }
        }
    }

    @Test
    public void case_fromLong() {
        {
            final long arg = 12345L;
            final Long ret = from(arg);
            assertEquals(Long.valueOf(12345L), ret);
        }
        {
            final Long arg = null;
            final Long ret = from(arg);
            assertNull(ret);
        }
        {
            final Long arg = Long.valueOf(12345L);
            final Long ret = from(arg);
            assertEquals(Long.valueOf(12345L), ret);
        }
    }

    @Test
    public void case_fromBigInteger() {
        {
            final BigInteger arg = null;
            final Long ret = from(arg);
            assertNull(ret);
        }
        {
            final BigInteger arg = BigInteger.valueOf(Long.MAX_VALUE);
            final Long ret = from(arg);
            assertEquals(Long.valueOf(Long.MAX_VALUE), ret);
        }
        {
            final BigInteger arg = BigInteger.valueOf(Long.MAX_VALUE).add(BigInteger.ONE);
            try {
                @SuppressWarnings("unused")
                final Long ret = from(arg);
                fail();
            } catch (final ArithmeticException e) {
                assertEquals("BigInteger out of long range", e.getMessage());
            }
        }
    }
}
