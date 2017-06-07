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
 * Supplementary Operations for Boolean.
 *
 * <p>
 * All methods are null-safe.
 * </p>
 *
 **********************************************************************/
public class BooleanOps {
    /*
     * to prevent to be called.
     */
    private BooleanOps() {
    }

    public static Boolean from(final Serializable arg) throws NumberFormatException, UnsupportedOperationException {
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
            throw new UnsupportedOperationException(String.format("`BooleanOps::from` does not support the value type: %s", arg.getClass().getName()));
        }
    }

    /**********************************************************************
     *
     * returns the boolean value obtained by parsing the string
     * argument.
     *
     * @return {@code null} when the argument is null or empty string,
     *         {@code true} when the argument is a string of "true"
     *         (case-ignoring), {@code false} when the argument is a
     *         string of "false" (case-ignoring)
     *
     * @exception NumberFormatException
     *                the argument does not represent a boolean value
     *
     **********************************************************************/
    public static Boolean from(final String arg) throws NumberFormatException {
        if (!exists(arg)) {
            return null;
        } else if (arg.equalsIgnoreCase("true")) {
            return Boolean.TRUE;
        } else if (arg.equalsIgnoreCase("false")) {
            return Boolean.FALSE;
        } else {
            throw new NumberFormatException(String.format("not a boolean value: \"%s\"", arg));
        }
    }

    /**********************************************************************
     *
     * returns the boxed boolean.
     *
     **********************************************************************/
    public static Boolean from(final boolean arg) {
        return Boolean.valueOf(arg);
    }

    /**********************************************************************
     *
     * returns the specified boolean itself.
     *
     **********************************************************************/
    public static Boolean from(final Boolean arg) {
        return arg;
    }

    /**********************************************************************
     *
     * returns the boolean value from an integer argument.
     *
     * @return {@code false} when the integer argument just equals to
     *         zero, {@code true} otherwise
     *
     **********************************************************************/
    public static Boolean from(final int arg) {
        return Boolean.valueOf(arg != 0);
    }

    /**********************************************************************
     *
     * returns the boolean value from an integer argument.
     *
     * @return {@code null} when the argument is null, {@code false}
     *         when the integer argument just equals to zero,
     *         {@code true} otherwise
     *
     **********************************************************************/
    public static Boolean from(final Integer arg) {
        return arg != null ? from(arg.intValue()) : null;
    }

    public static Boolean from(final long arg) {
        return Boolean.valueOf(arg != 0L);
    }

    public static Boolean from(final Long arg) {
        return arg != null ? from(arg.longValue()) : null;
    }

    public static Boolean from(final BigInteger arg) {
        return arg != null ? Boolean.valueOf(arg.compareTo(BigInteger.ZERO) != 0) : null;
    }

    public static Boolean from(final BigDecimal arg) {
        return arg != null ? Boolean.valueOf(arg.compareTo(BigDecimal.ZERO) != 0) : null;
    }

    public static Boolean from(final double arg) {
        return Boolean.valueOf(arg != 0.0);
    }

    public static Boolean from(final Double arg) {
        return arg != null ? from(arg.doubleValue()) : null;
    }

    public static boolean falsy(final Serializable arg) throws UnsupportedOperationException {
        if (arg == null) {
            return true;
        } else if (arg instanceof String) {
            return falsy((String)arg);
        } else if (arg instanceof Boolean) {
            return falsy((Boolean)arg);
        } else if (arg instanceof Integer) {
            return falsy((Integer)arg);
        } else if (arg instanceof Long) {
            return falsy((Long)arg);
        } else if (arg instanceof BigInteger) {
            return falsy((BigInteger)arg);
        } else if (arg instanceof BigDecimal) {
            return falsy((BigDecimal)arg);
        } else if (arg instanceof Double) {
            return falsy((Double)arg);
        } else {
            throw new UnsupportedOperationException(String.format("`BooleanOps::falsy` does not support the value type: %s", arg.getClass().getName()));
        }
    }

    /**********************************************************************
     *
     * returns true when the argument seems to be a "false".
     *
     * @return {@code true}, if the `arg` is null or empty, or equals,
     *         ignoring case, to "false", or the `arg` can be parsed as
     *         a number (BigDecimal) that value is zero.
     *
     **********************************************************************/
    public static boolean falsy(final String arg) {
        if (!exists(arg)) {
            return true;
        } else if (arg.equalsIgnoreCase("false")) {
            return true;
        } else {
            try {
                return new BigDecimal(arg).compareTo(BigDecimal.ZERO) == 0;
            } catch (final NumberFormatException e) {
                return false;
            }
        }
    }

    /**********************************************************************
     *
     * returns true when the `arg`'s value is false.
     *
     **********************************************************************/
    public static boolean falsy(final boolean arg) {
        return !arg;
    }

    /**********************************************************************
     *
     * returns true when the `arg` is null or it's value is false.
     *
     **********************************************************************/
    public static boolean falsy(final Boolean arg) {
        return arg == null || !arg.booleanValue();
    }

    /**********************************************************************
     *
     * returns true when the `arg`'s value equals to zero.
     *
     **********************************************************************/
    public static boolean falsy(final int arg) {
        return arg == 0;
    }

    /**********************************************************************
     *
     * returns true when the `arg` is null or it's value equals to zero.
     *
     **********************************************************************/
    public static boolean falsy(final Integer arg) {
        return arg == null || arg.intValue() == 0;
    }

    /**********************************************************************
     *
     * returns true when the `arg`'s value equals to zero.
     *
     **********************************************************************/
    public static boolean falsy(final long arg) {
        return arg == 0L;
    }

    /**********************************************************************
     *
     * returns true when the `arg` is null or it's value equals to zero.
     *
     **********************************************************************/
    public static boolean falsy(final Long arg) {
        return arg == null || arg.longValue() == 0L;
    }

    /**********************************************************************
     *
     * returns true when the `arg` is null or it's value equals to zero.
     *
     **********************************************************************/
    public static boolean falsy(final BigInteger arg) {
        return arg == null || arg.compareTo(BigInteger.ZERO) == 0;
    }

    /**********************************************************************
     *
     * returns true when the `arg` is null or it's value equals to zero.
     *
     **********************************************************************/
    public static boolean falsy(final BigDecimal arg) {
        return arg == null || arg.compareTo(BigDecimal.ZERO) == 0;
    }

    /**********************************************************************
     *
     * returns true when the `arg`'s value equals to zero.
     *
     **********************************************************************/
    public static boolean falsy(final double arg) {
        return arg == 0.0;
    }

    /**********************************************************************
     *
     * returns true when the `arg` is null or it's value equals to zero.
     *
     **********************************************************************/
    public static boolean falsy(final Double arg) {
        return arg == null || arg.doubleValue() == 0.0;
    }

    /**********************************************************************
     *
     * logical "not" of {@link BooleanOps#falsy(Serializable)}.
     *
     **********************************************************************/
    public static boolean truthy(final Serializable arg) {
        return !falsy(arg);
    }

    /**********************************************************************
     *
     * logical "not" of {@link BooleanOps#falsy(String)}.
     *
     **********************************************************************/
    public static boolean truthy(final String arg) {
        return !falsy(arg);
    }

    /**********************************************************************
     *
     * logical "not" of {@link BooleanOps#falsy(boolean)}.
     *
     **********************************************************************/
    public static boolean truthy(final boolean arg) {
        return !falsy(arg);
    }

    /**********************************************************************
     *
     * logical "not" of {@link BooleanOps#falsy(Boolean)}.
     *
     **********************************************************************/
    public static boolean truthy(final Boolean arg) {
        return !falsy(arg);
    }

    /**********************************************************************
     *
     * logical "not" of {@link BooleanOps#falsy(int)}.
     *
     **********************************************************************/
    public static boolean truthy(final int arg) {
        return !falsy(arg);
    }

    /**********************************************************************
     *
     * logical "not" of {@link BooleanOps#falsy(Integer)}.
     *
     **********************************************************************/
    public static boolean truthy(final Integer arg) {
        return !falsy(arg);
    }

    /**********************************************************************
     *
     * logical "not" of {@link BooleanOps#falsy(long)}.
     *
     **********************************************************************/
    public static boolean truthy(final long arg) {
        return !falsy(arg);
    }

    /**********************************************************************
     *
     * logical "not" of {@link BooleanOps#falsy(Long)}.
     *
     **********************************************************************/
    public static boolean truthy(final Long arg) {
        return !falsy(arg);
    }

    /**********************************************************************
     *
     * logical "not" of {@link BooleanOps#falsy(BigInteger)}.
     *
     **********************************************************************/
    public static boolean truthy(final BigInteger arg) {
        return !falsy(arg);
    }

    /**********************************************************************
     *
     * logical "not" of {@link BooleanOps#falsy(BigDecimal)}.
     *
     **********************************************************************/
    public static boolean truthy(final BigDecimal arg) {
        return !falsy(arg);
    }

    /**********************************************************************
     *
     * logical "not" of {@link BooleanOps#falsy(double)}.
     *
     **********************************************************************/
    public static boolean truthy(final double arg) {
        return !falsy(arg);
    }

    /**********************************************************************
     *
     * logical "not" of {@link BooleanOps#falsy(Double)}.
     *
     **********************************************************************/
    public static boolean truthy(final Double arg) {
        return !falsy(arg);
    }
}
