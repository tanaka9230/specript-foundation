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
 * Supplementary Operations for Double.
 *
 * <p>
 * All methods are null-safe.
 * </p>
 *
 **********************************************************************/
public class DoubleOps {
    public static final Double ZERO = Double.valueOf(0);
    public static final Double ONE = Double.valueOf(1);

    //
    //
    //

    /*
     * to prevent to be called.
     */
    private DoubleOps() {
    }

    public static Double from(final Serializable arg) throws ArithmeticException, NumberFormatException, UnsupportedOperationException {
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
            throw new UnsupportedOperationException(String.format("`Double::from` does not support the value type: %s", arg.getClass().getName()));
        }
    }

    public static Double from(final String arg) throws ArithmeticException, NumberFormatException {
        if (exists(arg)) {
            final Double ret = Double.valueOf(arg); // NumberFormatException may be thrown
            if (ret.isInfinite()) {
                throw new ArithmeticException(String.format("too large exponent part as a double value: %s", arg));
            }
            return ret;
        } else {
            return null;
        }
    }

    public static Double from(final boolean arg) {
        return arg ? ONE : ZERO;
    }

    public static Double from(final Boolean arg) {
        return arg != null ? from(arg.booleanValue()) : null;
    }

    public static Double from(final int arg) {
        return Double.valueOf(arg); // int-to-double cast never causes over/underflow
    }

    public static Double from(final Integer arg) {
        return arg != null ? from(arg.intValue()) : null;
    }

    public static Double from(final long arg) {
        return Double.valueOf(arg); // long-to-double cast never causes over/underflow (the precision will be lost)
    }

    public static Double from(final Long arg) {
        return arg != null ? from(arg.longValue()) : null;
    }

    public static Double from(final BigInteger arg) throws ArithmeticException {
        if (arg != null) {
            final double tmp = arg.doubleValue();
            if (Double.isInfinite(tmp)) {
                throw new ArithmeticException(String.format("over/underflow occurred during BigInteger-to-Double cast: %s", arg.toString()));
            }
            return Double.valueOf(tmp);
        } else {
            return null;
        }
    }

    public static Double from(final BigDecimal arg) throws ArithmeticException {
        if (arg != null) {
            final double tmp = arg.doubleValue();
            if (Double.isInfinite(tmp)) {
                throw new ArithmeticException(String.format("over/underflow occurred during BigDecimal-to-Double cast: %s", arg.toPlainString()));
            }
            return Double.valueOf(tmp);
        } else {
            return null;
        }
    }

    public static Double from(final double arg) {
        return Double.valueOf(arg);
    }

    public static Double from(final Double arg) {
        return arg;
    }
}
