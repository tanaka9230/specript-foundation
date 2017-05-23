/* ======================================---------------------------

  --+ Specript Foundation Library v3.0 +--

  Copyright (c) 2007-2016, Koichi Tanaka (@Tanaka9230)

    ---------------------------====================================== */

package org.specript.foundation;

import static org.specript.foundation.Ops.exists;
import java.io.Serializable;
import java.math.BigDecimal;
import java.math.BigInteger;

/**********************************************************************
 *
 * Auxiliary Operations for Long.
 *
 * <p>
 * All methods are null-safe.
 * </p>
 *
 **********************************************************************/
public class LongOps {
    /*
     * to prevent to be called.
     */
    private LongOps() {
    }

    public static Long from(final Serializable arg) throws ArithmeticException, NumberFormatException, UnsupportedOperationException {
        if (arg == null) {
            return null;
        } else if (arg instanceof String) {
            return from((String)arg);
        } else if (arg instanceof Boolean) {
            return from((Boolean)arg);
        } else if (arg instanceof Integer) {
            return from((Integer)arg);
        } else if (arg instanceof Long) {
            return from((Long)arg);
        } else if (arg instanceof BigInteger) {
            return from((BigInteger)arg);
        } else if (arg instanceof BigDecimal) {
            return from((BigDecimal)arg);
        } else if (arg instanceof Double) {
            return from((Double)arg);
        } else {
            throw new UnsupportedOperationException(String.format("`LongOps::from` does not support the value type: %s", arg.getClass().getName()));
        }
    }

    public static Long from(final String arg) throws ArithmeticException, NumberFormatException {
        return exists(arg) ? Long.valueOf(new BigDecimal(arg).longValueExact()) : null;
    }

    public static Long from(final boolean arg) {
        return arg ? Long.valueOf(1L) : Long.valueOf(0L);
    }

    public static Long from(final Boolean arg) {
        return arg != null ? from(arg.booleanValue()) : null;
    }

    public static Long from(final int arg) {
        return Long.valueOf((long)arg);
    }

    public static Long from(final Integer arg) {
        return arg != null ? from(arg.intValue()) : null;
    }

    public static Long from(final long arg) {
        return Long.valueOf(arg);
    }

    public static Long from(final Long arg) {
        return arg;
    }

    public static Long from(final BigInteger arg) throws ArithmeticException {
        return arg != null ? Long.valueOf(arg.longValueExact()) : null;
    }

    public static Long from(final BigDecimal arg) throws ArithmeticException {
        return arg != null ? Long.valueOf(arg.longValueExact()) : null;
    }

    public static Long from(final double arg) throws ArithmeticException, NumberFormatException {
        final BigDecimal tmp = BigDecimal.valueOf(arg);
        return Long.valueOf(tmp.longValueExact());
    }

    public static Long from(final Double arg) throws ArithmeticException, NumberFormatException {
        return arg != null ? from(arg.doubleValue()) : null;
    }
}
