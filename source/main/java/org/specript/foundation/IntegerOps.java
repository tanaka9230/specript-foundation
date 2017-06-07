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
 * Supplementary Operations for Integer.
 *
 * <p>
 * All methods are null-safe.
 * </p>
 *
 **********************************************************************/
public class IntegerOps {
    public static final Integer ZERO = Integer.valueOf(0);
    public static final Integer ONE = Integer.valueOf(1);

    //
    //
    //

    /*
     * to prevent to be called.
     */
    private IntegerOps() {
    }

    public static Integer from(final Serializable arg) throws ArithmeticException, NumberFormatException, UnsupportedOperationException {
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
            throw new UnsupportedOperationException(String.format("`IntegerOps::from` does not support the value type: %s", arg.getClass().getName()));
        }
    }

    public static Integer from(final String arg) throws ArithmeticException, NumberFormatException {
        return exists(arg) ? Integer.valueOf(new BigDecimal(arg).intValueExact()) : null;
    }

    public static Integer from(final boolean arg) {
        return arg ? ONE : ZERO;
    }

    public static Integer from(final Boolean arg) {
        return arg != null ? from(arg.booleanValue()) : null;
    }

    public static Integer from(final int arg) {
        return Integer.valueOf(arg);
    }

    public static Integer from(final Integer arg) {
        return arg;
    }

    public static Integer from(final long arg) throws ArithmeticException {
        return Integer.valueOf(Math.toIntExact(arg));
    }

    public static Integer from(final Long arg) throws ArithmeticException {
        return arg != null ? from(arg.longValue()) : null;
    }

    public static Integer from(final BigInteger arg) throws ArithmeticException {
        return arg != null ? Integer.valueOf(arg.intValueExact()) : null;
    }

    public static Integer from(final BigDecimal arg) throws ArithmeticException {
        return arg != null ? Integer.valueOf(arg.intValueExact()) : null;
    }

    public static Integer from(final double arg) throws ArithmeticException, NumberFormatException {
        final BigDecimal tmp = BigDecimal.valueOf(arg);
        return Integer.valueOf(tmp.intValueExact());
    }

    public static Integer from(final Double arg) throws ArithmeticException, NumberFormatException {
        return arg != null ? from(arg.doubleValue()) : null;
    }
}
