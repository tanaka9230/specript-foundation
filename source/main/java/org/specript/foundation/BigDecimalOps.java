/* ======================================---------------------------

  --+ Specript Foundation Library v3.0 +--

  Copyright (c) 2007-2017, Koichi Tanaka (@Tanaka9230)

    ---------------------------====================================== */

package org.specript.foundation;

import static org.specript.foundation.Ops.exists;
import java.io.Serializable;
import java.math.BigDecimal;
import java.math.BigInteger;

/**********************************************************************
 *
 * Auxiliary Operations for BigDecimal.
 *
 * <p>
 * All methods are null-safe.
 * </p>
 *
 **********************************************************************/
public class BigDecimalOps {
    public static final BigDecimal ZERO = BigDecimal.ZERO;
    public static final BigDecimal ONE = BigDecimal.ONE;

    //
    //
    //

    /*
     * to prevent to be called.
     */
    private BigDecimalOps() {
    }

    public static BigDecimal from(final Serializable arg) throws NumberFormatException, UnsupportedOperationException {
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
            throw new UnsupportedOperationException(String.format("`BigDecimalOps::from` does not support the value type: %s", arg.getClass().getName()));
        }
    }

    public static BigDecimal from(final String arg) throws NumberFormatException {
        return exists(arg) ? new BigDecimal(arg) : null;
    }

    public static BigDecimal from(final boolean arg) {
        return arg ? ONE : ZERO;
    }

    public static BigDecimal from(final Boolean arg) {
        return arg != null ? from(arg.booleanValue()) : null;
    }

    public static BigDecimal from(final int arg) {
        return BigDecimal.valueOf(arg);
    }

    public static BigDecimal from(final Integer arg) {
        return arg != null ? from(arg.intValue()) : null;
    }

    public static BigDecimal from(final long arg) {
        return BigDecimal.valueOf(arg);
    }

    public static BigDecimal from(final Long arg) {
        return arg != null ? from(arg.longValue()) : null;
    }

    public static BigDecimal from(final BigInteger arg) {
        return arg != null ? new BigDecimal(arg) : null;
    }

    public static BigDecimal from(final BigDecimal arg) {
        return arg;
    }

    public static BigDecimal from(final double arg) throws NumberFormatException {
        final BigDecimal tmp = BigDecimal.valueOf(arg);
        return tmp;
    }

    public static BigDecimal from(final Double arg) throws NumberFormatException {
        return arg != null ? from(arg.doubleValue()) : null;
    }
}
