
package org.specript.foundation;

import static org.specript.foundation.LongOps.from;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.fail;
import org.junit.Test;
import java.io.Serializable;
import java.math.BigDecimal;
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
            final Serializable arg = "12345.0";
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
            final Serializable arg = "9223372036854775808";
            try {
                @SuppressWarnings("unused")
                final Long ret = from(arg);
                fail();
            } catch (final ArithmeticException e) {
                assertEquals("Overflow", e.getMessage());
            }
        }
        {
            final Serializable arg = "piyo";
            try {
                @SuppressWarnings("unused")
                final Long ret = from(arg);
                fail();
            } catch (final NumberFormatException e) {
                assertEquals("java.lang.NumberFormatException", e.toString());
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
            assertEquals(Long.valueOf(12345L), ret);
        }
        {
            final String arg = "12345.0";
            final Long ret = from(arg);
            assertEquals(Long.valueOf(12345L), ret);
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
            final String arg = "9223372036854775808";
            try {
                @SuppressWarnings("unused")
                final Long ret = from(arg);
                fail();
            } catch (final ArithmeticException e) {
                assertEquals("Overflow", e.getMessage());
            }
        }
        {
            final String arg = "piyo";
            try {
                @SuppressWarnings("unused")
                final Long ret = from(arg);
                fail();
            } catch (final NumberFormatException e) {
                assertEquals("java.lang.NumberFormatException", e.toString());
            }
        }
    }

    @Test
    public void case_fromBoolean() {
        {
            final boolean arg = false;
            final Long ret = from(arg);
            assertEquals(Long.valueOf(0L), ret);
        }
        {
            final boolean arg = true;
            final Long ret = from(arg);
            assertEquals(Long.valueOf(1L), ret);
        }
        {
            final Boolean arg = null;
            final Long ret = from(arg);
            assertNull(ret);
        }
        {
            final Boolean arg = Boolean.FALSE;
            final Long ret = from(arg);
            assertEquals(Long.valueOf(0L), ret);
        }
        {
            final Boolean arg = Boolean.TRUE;
            final Long ret = from(arg);
            assertEquals(Long.valueOf(1L), ret);
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

    @Test
    public void case_fromBigDecimal() {
        {
            final BigDecimal arg = null;
            final Long ret = from(arg);
            assertNull(ret);
        }
        {
            final BigDecimal arg = BigDecimal.valueOf(Long.MAX_VALUE);
            final Long ret = from(arg);
            assertEquals(Long.valueOf(Long.MAX_VALUE), ret);
        }
        {
            final BigDecimal arg = new BigDecimal("12345.0");
            final Long ret = from(arg);
            assertEquals(Long.valueOf(12345L), ret);
        }
        {
            final BigDecimal arg = new BigDecimal("12345.6");
            try {
                @SuppressWarnings("unused")
                final Long ret = from(arg);
                fail();
            } catch (final ArithmeticException e) {
                assertEquals("Rounding necessary", e.getMessage());
            }
        }
        {
            final BigDecimal arg = BigDecimal.valueOf(Long.MAX_VALUE).add(BigDecimal.ONE);
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
    public void case_fromDouble() {
        {
            final double arg = 12345.0;
            final Long ret = from(arg);
            assertEquals(Long.valueOf(12345L), ret);
        }
        {
            final double arg = 12345.6;
            try {
                @SuppressWarnings("unused")
                final Long ret = from(arg);
                fail();
            } catch (final ArithmeticException e) {
                assertEquals("Rounding necessary", e.getMessage());
            }
        }
        {
            final Double arg = null;
            final Long ret = from(arg);
            assertNull(ret);
        }
        {
            final Double arg = Double.valueOf("12345.0");
            final Long ret = from(arg);
            assertEquals(Long.valueOf(12345L), ret);
        }
        {
            final Double arg = Double.valueOf("12345.6");
            try {
                @SuppressWarnings("unused")
                final Long ret = from(arg);
                fail();
            } catch (final ArithmeticException e) {
                assertEquals("Rounding necessary", e.getMessage());
            }
        }
    }
}
