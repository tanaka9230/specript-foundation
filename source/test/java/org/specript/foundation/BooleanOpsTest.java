
package org.specript.foundation;

import static org.specript.foundation.BooleanOps.from;
import static org.specript.foundation.BooleanOps.truthy;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;
import org.junit.Test;
import java.io.Serializable;
import java.math.BigDecimal;
import java.math.BigInteger;

public class BooleanOpsTest {
    @Test
    public void case_fromObject() {
        {
            final Serializable arg = null;
            final Boolean ret = from(arg);
            assertNull(ret);
        }
        {
            final Serializable arg = "";
            final Boolean ret = from(arg);
            assertNull(ret);
        }
        {
            final Serializable arg = "  ";
            final Boolean ret = from(arg);
            assertNull(ret);
        }
        {
            final Serializable arg = "0";
            try {
                @SuppressWarnings("unused")
                final Boolean ret = from(arg);
                fail();
            } catch (final NumberFormatException e) {
                assertEquals("java.lang.NumberFormatException: not a boolean value: \"0\"", e.toString());
            }
        }
        {
            final Serializable arg = "0.0";
            try {
                @SuppressWarnings("unused")
                final Boolean ret = from(arg);
                fail();
            } catch (final NumberFormatException e) {
                assertEquals("java.lang.NumberFormatException: not a boolean value: \"0.0\"", e.toString());
            }
        }
        {
            final Serializable arg = "12345";
            try {
                @SuppressWarnings("unused")
                final Boolean ret = from(arg);
                fail();
            } catch (final NumberFormatException e) {
                assertEquals("java.lang.NumberFormatException: not a boolean value: \"12345\"", e.toString());
            }
        }
        {
            final Serializable arg = "12345.6";
            try {
                @SuppressWarnings("unused")
                final Boolean ret = from(arg);
                fail();
            } catch (final NumberFormatException e) {
                assertEquals("java.lang.NumberFormatException: not a boolean value: \"12345.6\"", e.toString());
            }
        }
        {
            final Serializable arg = "False";
            final Boolean ret = from(arg);
            assertFalse(ret.booleanValue());
        }
        {
            final Serializable arg = "True";
            final Boolean ret = from(arg);
            assertTrue(ret.booleanValue());
        }
        {
            final Serializable arg = "piyo";
            try {
                @SuppressWarnings("unused")
                final Boolean ret = from(arg);
                fail();
            } catch (final NumberFormatException e) {
                assertEquals("java.lang.NumberFormatException: not a boolean value: \"piyo\"", e.toString());
            }
        }
        {
            final Serializable arg = Integer.valueOf(0);
            final Boolean ret = from(arg);
            assertFalse(ret.booleanValue());
        }
        {
            final Serializable arg = Integer.valueOf(12345);
            final Boolean ret = from(arg);
            assertTrue(ret.booleanValue());
        }
        {
            final Serializable arg = Long.valueOf(0L);
            final Boolean ret = from(arg);
            assertFalse(ret.booleanValue());
        }
        {
            final Serializable arg = Long.valueOf(12345L);
            final Boolean ret = from(arg);
            assertTrue(ret.booleanValue());
        }
        {
            final Serializable arg = new BigInteger("0");
            final Boolean ret = from(arg);
            assertFalse(ret.booleanValue());
        }
        {
            final Serializable arg = new BigInteger("12345");
            final Boolean ret = from(arg);
            assertTrue(ret.booleanValue());
        }
        {
            final Serializable arg = new BigDecimal("0.0");
            final Boolean ret = from(arg);
            assertFalse(ret.booleanValue());
        }
        {
            final Serializable arg = new BigDecimal("12345.6");
            final Boolean ret = from(arg);
            assertTrue(ret.booleanValue());
        }
        {
            final Serializable arg = Double.valueOf(0.0);
            final Boolean ret = from(arg);
            assertFalse(ret.booleanValue());
        }
        {
            final Serializable arg = Double.valueOf(12345.6);
            final Boolean ret = from(arg);
            assertTrue(ret.booleanValue());
        }
    }

    @Test
    public void case_fromString() {
        {
            final String arg = null;
            final Boolean ret = from(arg);
            assertNull(ret);
        }
        {
            final String arg = "";
            final Boolean ret = from(arg);
            assertNull(ret);
        }
        {
            final String arg = "  ";
            final Boolean ret = from(arg);
            assertNull(ret);
        }
        {
            final String arg = "0";
            try {
                @SuppressWarnings("unused")
                final Boolean ret = from(arg);
                fail();
            } catch (final NumberFormatException e) {
                assertEquals("java.lang.NumberFormatException: not a boolean value: \"0\"", e.toString());
            }
        }
        {
            final String arg = "0.0";
            try {
                @SuppressWarnings("unused")
                final Boolean ret = from(arg);
                fail();
            } catch (final NumberFormatException e) {
                assertEquals("java.lang.NumberFormatException: not a boolean value: \"0.0\"", e.toString());
            }
        }
        {
            final String arg = "12345";
            try {
                @SuppressWarnings("unused")
                final Boolean ret = from(arg);
                fail();
            } catch (final NumberFormatException e) {
                assertEquals("java.lang.NumberFormatException: not a boolean value: \"12345\"", e.toString());
            }
        }
        {
            final String arg = "12345.6";
            try {
                @SuppressWarnings("unused")
                final Boolean ret = from(arg);
                fail();
            } catch (final NumberFormatException e) {
                assertEquals("java.lang.NumberFormatException: not a boolean value: \"12345.6\"", e.toString());
            }
        }
        {
            final String arg = "False";
            final Boolean ret = from(arg);
            assertFalse(ret.booleanValue());
        }
        {
            final String arg = "piyo";
            try {
                @SuppressWarnings("unused")
                final Boolean ret = from(arg);
                fail();
            } catch (final NumberFormatException e) {
                assertEquals("java.lang.NumberFormatException: not a boolean value: \"piyo\"", e.toString());
            }
        }
    }

    @Test
    public void case_fromInteger() {
        {
            final int arg = 0;
            final Boolean ret = from(arg);
            assertFalse(ret.booleanValue());
        }
        {
            final int arg = 12345;
            final Boolean ret = from(arg);
            assertTrue(ret.booleanValue());
        }
        {
            final Integer arg = null;
            final Boolean ret = from(arg);
            assertNull(ret);
        }
        {
            final Integer arg = Integer.valueOf(0);
            final Boolean ret = from(arg);
            assertFalse(ret.booleanValue());
        }
        {
            final Integer arg = Integer.valueOf(12345);
            final Boolean ret = from(arg);
            assertTrue(ret.booleanValue());
        }
    }

    @Test
    public void case_fromLong() {
        {
            final long arg = 0L;
            final Boolean ret = from(arg);
            assertFalse(ret.booleanValue());
        }
        {
            final long arg = 12345L;
            final Boolean ret = from(arg);
            assertTrue(ret.booleanValue());
        }
        {
            final Long arg = null;
            final Boolean ret = from(arg);
            assertNull(ret);
        }
        {
            final Long arg = Long.valueOf(0L);
            final Boolean ret = from(arg);
            assertFalse(ret.booleanValue());
        }
        {
            final Long arg = Long.valueOf(12345L);
            final Boolean ret = from(arg);
            assertTrue(ret.booleanValue());
        }
    }

    @Test
    public void case_fromBigInteger() {
        {
            final BigInteger arg = null;
            final Boolean ret = from(arg);
            assertNull(ret);
        }
        {
            final BigInteger arg = new BigInteger("0");
            final Boolean ret = from(arg);
            assertFalse(ret.booleanValue());
        }
        {
            final BigInteger arg = new BigInteger("12345");
            final Boolean ret = from(arg);
            assertTrue(ret.booleanValue());
        }
    }

    @Test
    public void case_fromBigDecimal() {
        {
            final BigDecimal arg = null;
            final Boolean ret = from(arg);
            assertNull(ret);
        }
        {
            final BigDecimal arg = new BigDecimal("0.0");
            final Boolean ret = from(arg);
            assertFalse(ret.booleanValue());
        }
        {
            final BigDecimal arg = new BigDecimal("12345.6");
            final Boolean ret = from(arg);
            assertTrue(ret.booleanValue());
        }
    }

    @Test
    public void case_fromDouble() {
        {
            final double arg = 0.0;
            final Boolean ret = from(arg);
            assertFalse(ret.booleanValue());
        }
        {
            final double arg = 12345.6;
            final Boolean ret = from(arg);
            assertTrue(ret.booleanValue());
        }
        {
            final Double arg = null;
            final Boolean ret = from(arg);
            assertNull(ret);
        }
        {
            final Double arg = Double.valueOf(0.0);
            final Boolean ret = from(arg);
            assertFalse(ret.booleanValue());
        }
        {
            final Double arg = Double.valueOf(12345.6);
            final Boolean ret = from(arg);
            assertTrue(ret.booleanValue());
        }
    }

    @Test
    public void case_truthy_Object() {
        {
            final Serializable arg = null;
            final boolean ret = truthy(arg);
            assertFalse(ret);
        }
        {
            final Serializable arg = "";
            final boolean ret = truthy(arg);
            assertFalse(ret);
        }
        {
            final Serializable arg = "  ";
            final boolean ret = truthy(arg);
            assertFalse(ret);
        }
        {
            final Serializable arg = "0";
            final boolean ret = truthy(arg);
            assertFalse(ret);
        }
        {
            final Serializable arg = "0.0";
            final boolean ret = truthy(arg);
            assertFalse(ret);
        }
        {
            final Serializable arg = "12345";
            final boolean ret = truthy(arg);
            assertTrue(ret);
        }
        {
            final Serializable arg = "12345.6";
            final boolean ret = truthy(arg);
            assertTrue(ret);
        }
        {
            final Serializable arg = "False";
            final boolean ret = truthy(arg);
            assertFalse(ret);
        }
        {
            final Serializable arg = "True";
            final boolean ret = truthy(arg);
            assertTrue(ret);
        }
        {
            final Serializable arg = "piyo";
            final boolean ret = truthy(arg);
            assertTrue(ret);
        }
        {
            final Serializable arg = Boolean.FALSE;
            final boolean ret = truthy(arg);
            assertFalse(ret);
        }
        {
            final Serializable arg = Boolean.TRUE;
            final boolean ret = truthy(arg);
            assertTrue(ret);
        }
        {
            final Serializable arg = Integer.valueOf(0);
            final boolean ret = truthy(arg);
            assertFalse(ret);
        }
        {
            final Serializable arg = Integer.valueOf(12345);
            final boolean ret = truthy(arg);
            assertTrue(ret);
        }
        {
            final Serializable arg = Long.valueOf(0L);
            final boolean ret = truthy(arg);
            assertFalse(ret);
        }
        {
            final Serializable arg = Long.valueOf(12345L);
            final boolean ret = truthy(arg);
            assertTrue(ret);
        }
        {
            final Serializable arg = new BigInteger("0");
            final boolean ret = truthy(arg);
            assertFalse(ret);
        }
        {
            final Serializable arg = new BigInteger("12345");
            final boolean ret = truthy(arg);
            assertTrue(ret);
        }
        {
            final Serializable arg = new BigDecimal("0.0");
            final boolean ret = truthy(arg);
            assertFalse(ret);
        }
        {
            final Serializable arg = new BigDecimal("12345.6");
            final boolean ret = truthy(arg);
            assertTrue(ret);
        }
        {
            final Serializable arg = Double.valueOf(0.0);
            final boolean ret = truthy(arg);
            assertFalse(ret);
        }
        {
            final Serializable arg = Double.valueOf(12345.6);
            final boolean ret = truthy(arg);
            assertTrue(ret);
        }
    }

    @Test
    public void case_truthy_String() {
        {
            final String arg = null;
            final boolean ret = truthy(arg);
            assertFalse(ret);
        }
        {
            final String arg = "";
            final boolean ret = truthy(arg);
            assertFalse(ret);
        }
        {
            final String arg = "  ";
            final boolean ret = truthy(arg);
            assertFalse(ret);
        }
        {
            final String arg = "0";
            final boolean ret = truthy(arg);
            assertFalse(ret);
        }
        {
            final String arg = "0.0";
            final boolean ret = truthy(arg);
            assertFalse(ret);
        }
        {
            final String arg = "12345";
            final boolean ret = truthy(arg);
            assertTrue(ret);
        }
        {
            final String arg = "12345.6";
            final boolean ret = truthy(arg);
            assertTrue(ret);
        }
        {
            final String arg = "False";
            final boolean ret = truthy(arg);
            assertFalse(ret);
        }
        {
            final String arg = "True";
            final boolean ret = truthy(arg);
            assertTrue(ret);
        }
        {
            final String arg = "piyo";
            final boolean ret = truthy(arg);
            assertTrue(ret);
        }
    }

    @Test
    public void case_truthy_Integer() {
        {
            final int arg = 0;
            final boolean ret = truthy(arg);
            assertFalse(ret);
        }
        {
            final int arg = 12345;
            final boolean ret = truthy(arg);
            assertTrue(ret);
        }
        {
            final Integer arg = null;
            final boolean ret = truthy(arg);
            assertFalse(ret);
        }
        {
            final Integer arg = Integer.valueOf(0);
            final boolean ret = truthy(arg);
            assertFalse(ret);
        }
        {
            final Integer arg = Integer.valueOf(12345);
            final boolean ret = truthy(arg);
            assertTrue(ret);
        }
    }

    // TODO Long, BigInteger, BigDecimal cases

    @Test
    public void case_truthy_Double() {
        {
            final double arg = 0.0;
            final boolean ret = truthy(arg);
            assertFalse(ret);
        }
        {
            final double arg = 12345.6;
            final boolean ret = truthy(arg);
            assertTrue(ret);
        }
        {
            final Double arg = null;
            final boolean ret = truthy(arg);
            assertFalse(ret);
        }
        {
            final Double arg = Double.valueOf(0.0);
            final boolean ret = truthy(arg);
            assertFalse(ret);
        }
        {
            final Double arg = Double.valueOf(12345.6);
            final boolean ret = truthy(arg);
            assertTrue(ret);
        }
    }
}
