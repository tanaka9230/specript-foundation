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
 * Auxiliary Operations for BigInteger.
 *
 * <p>
 * All methods are null-safe.
 * </p>
 *
 **********************************************************************/
public class BigIntegerOps {
    public static final BigInteger ZERO = BigInteger.ZERO;
    public static final BigInteger ONE = BigInteger.ONE;

    private static final ValueConverter<BigInteger> thisConverters = new ValueConverter<>(BigInteger.class)
            .with(null, (arg) -> null)
            .with(String.class, BigIntegerOps::from)
            .with(Boolean.class, BigIntegerOps::from)
            .with(Integer.class, BigIntegerOps::from)
            .with(Long.class, BigIntegerOps::from)
            .with(BigInteger.class, BigIntegerOps::from)
            .with(BigDecimal.class, BigIntegerOps::from)
            .with(Double.class, BigIntegerOps::from);

    //
    //
    //

    /*
     * to prevent to be called.
     */
    private BigIntegerOps() {
    }

    public static BigInteger from(final Serializable arg) throws RuntimeException, UnsupportedOperationException {
        return thisConverters.from(arg);
    }

    public static BigInteger from2(final Serializable arg) throws ArithmeticException, NumberFormatException, UnsupportedOperationException {
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
            throw new UnsupportedOperationException(String.format("`BigIntegerOps::from` does not support the value type: %s", arg.getClass().getName()));
        }
    }

    public static BigInteger from(final String arg) throws ArithmeticException, NumberFormatException {
        return exists(arg) ? new BigDecimal(arg).toBigIntegerExact() : null;
    }

    public static BigInteger from(final boolean arg) {
        return arg ? ONE : ZERO;
    }

    public static BigInteger from(final Boolean arg) {
        return arg != null ? from(arg.booleanValue()) : null;
    }

    public static BigInteger from(final int arg) {
        return BigInteger.valueOf(arg);
    }

    public static BigInteger from(final Integer arg) {
        return arg != null ? from(arg.intValue()) : null;
    }

    public static BigInteger from(final long arg) {
        return BigInteger.valueOf(arg);
    }

    public static BigInteger from(final Long arg) {
        return arg != null ? from(arg.longValue()) : null;
    }

    public static BigInteger from(final BigInteger arg) {
        return arg;
    }

    public static BigInteger from(final BigDecimal arg) throws ArithmeticException {
        return arg != null ? arg.toBigIntegerExact() : null;
    }

    public static BigInteger from(final double arg) throws ArithmeticException, NumberFormatException {
        final BigDecimal tmp = BigDecimal.valueOf(arg);
        return tmp.toBigIntegerExact();
    }

    public static BigInteger from(final Double arg) throws ArithmeticException, NumberFormatException {
        return arg != null ? from(arg.doubleValue()) : null;
    }
}
