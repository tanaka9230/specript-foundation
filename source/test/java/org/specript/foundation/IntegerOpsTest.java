
package org.specript.foundation;

import static org.specript.foundation.IntegerOps.from;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.fail;
import org.junit.Test;
import java.io.Serializable;
import java.math.BigDecimal;
import java.math.BigInteger;

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
            final Serializable arg = "12345.0";
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
            final Serializable arg = "9223372036854775808";
            try {
                @SuppressWarnings("unused")
                final Integer ret = from(arg);
                fail();
            } catch (final ArithmeticException e) {
                assertEquals("Overflow", e.getMessage());
            }
        }
        {
            final Serializable arg = "piyo";
            try {
                @SuppressWarnings("unused")
                final Integer ret = from(arg);
                fail();
            } catch (final NumberFormatException e) {
                assertNull(e.getMessage());
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
            final String arg = "12345.0";
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
            final String arg = "9223372036854775808";
            try {
                @SuppressWarnings("unused")
                final Integer ret = from(arg);
                fail();
            } catch (final ArithmeticException e) {
                assertEquals("Overflow", e.getMessage());
            }
        }
        {
            final String arg = "piyo";
            try {
                @SuppressWarnings("unused")
                final Integer ret = from(arg);
                fail();
            } catch (final NumberFormatException e) {
                assertNull(e.getMessage());
            }
        }
    }

    @Test
    public void case_fromBoolean() {
        {
            final boolean arg = false;
            final Integer ret = from(arg);
            assertEquals(Integer.valueOf(0), ret);
        }
        {
            final boolean arg = true;
            final Integer ret = from(arg);
            assertEquals(Integer.valueOf(1), ret);
        }
        {
            final Boolean arg = null;
            final Integer ret = from(arg);
            assertNull(ret);
        }
        {
            final Boolean arg = Boolean.FALSE;
            final Integer ret = from(arg);
            assertEquals(Integer.valueOf(0), ret);
        }
        {
            final Boolean arg = Boolean.TRUE;
            final Integer ret = from(arg);
            assertEquals(Integer.valueOf(1), ret);
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

    @Test
    public void case_fromBigInteger() {
        {
            final BigInteger arg = null;
            final Integer ret = from(arg);
            assertNull(ret);
        }
        {
            final BigInteger arg = BigInteger.valueOf(Integer.MAX_VALUE);
            final Integer ret = from(arg);
            assertEquals(Integer.valueOf(Integer.MAX_VALUE), ret);
        }
        {
            final BigInteger arg = BigInteger.valueOf(Integer.MAX_VALUE).add(BigInteger.ONE);
            try {
                @SuppressWarnings("unused")
                final Integer ret = from(arg);
                fail();
            } catch (final ArithmeticException e) {
                assertEquals("BigInteger out of int range", e.getMessage());
            }
        }
    }

    @Test
    public void case_fromBigDecimal() {
        {
            final BigDecimal arg = null;
            final Integer ret = from(arg);
            assertNull(ret);
        }
        {
            final BigDecimal arg = BigDecimal.valueOf(Integer.MAX_VALUE);
            final Integer ret = from(arg);
            assertEquals(Integer.valueOf(Integer.MAX_VALUE), ret);
        }
        {
            final BigDecimal arg = new BigDecimal("12345.0");
            final Integer ret = from(arg);
            assertEquals(Integer.valueOf(12345), ret);
        }
        {
            final BigDecimal arg = new BigDecimal("12345.6");
            try {
                @SuppressWarnings("unused")
                final Integer ret = from(arg);
                fail();
            } catch (final ArithmeticException e) {
                assertEquals("Rounding necessary", e.getMessage());
            }
        }
        {
            final BigDecimal arg = BigDecimal.valueOf(Integer.MAX_VALUE).add(BigDecimal.ONE);
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
    public void case_fromDouble() {
        {
            final double arg = 12345.0;
            final Integer ret = from(arg);
            assertEquals(Integer.valueOf(12345), ret);
        }
        {
            final double arg = 12345.6;
            try {
                @SuppressWarnings("unused")
                final Integer ret = from(arg);
                fail();
            } catch (final ArithmeticException e) {
                assertEquals("Rounding necessary", e.getMessage());
            }
        }
        {
            final Double arg = null;
            final Integer ret = from(arg);
            assertNull(ret);
        }
        {
            final Double arg = Double.valueOf("12345.0");
            final Integer ret = from(arg);
            assertEquals(Integer.valueOf(12345), ret);
        }
        {
            final Double arg = Double.valueOf("12345.6");
            try {
                @SuppressWarnings("unused")
                final Integer ret = from(arg);
                fail();
            } catch (final ArithmeticException e) {
                assertEquals("Rounding necessary", e.getMessage());
            }
        }
    }
}
