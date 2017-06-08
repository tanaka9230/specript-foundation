/* ======================================---------------------------

  --+ Specript Foundation Library v3.0 +--

  Copyright (c) 2007-2017, Koichi Tanaka (@Tanaka9230)

    ---------------------------====================================== */

package org.specript.foundation;

import java.util.Collection;
import java.util.Iterator;
import java.util.Optional;

public class Ops {
    /*
     * to prevent to be called.
     */
    private Ops() {
    }

    /**********************************************************************
     *
     * tests if an object is not null.
     *
     **********************************************************************/
    public static boolean exists(final Object arg) {
        return arg != null;
    }

    /**********************************************************************
     *
     * tests if a string is not null and not empty.
     *
     * <p>
     * Same behavior to {@code exists(arg, false)}.
     * </p>
     *
     * @param arg
     *            a string to test
     *
     **********************************************************************/
    public static boolean exists(final String arg) {
        return arg != null && arg.trim().length() != 0;
    }

    /**********************************************************************
     *
     * tests if a string is not null and not empty.
     *
     * @param arg
     *            a string to test
     * @param preservingWhitespaces
     *            {@code false} : judges the `arg` empty when the
     *            argument is not empty but consists of only
     *            whitespaces; {@code true} : judges the `arg` empty
     *            when the `arg` argument is only surely empty,
     *            "whitespaces-only" is not considered as empty
     *
     **********************************************************************/
    public static boolean exists(final String arg, boolean preservingWhitespaces) {
        if (preservingWhitespaces) {
            return arg != null && arg.length() != 0;
        } else {
            return exists(arg);
        }
    }

    /**********************************************************************
     *
     * tests if an array is not null and not empty.
     *
     **********************************************************************/
    public static <T> boolean exists(final T[] arg) {
        return arg != null && arg.length != 0;
    }

    /**********************************************************************
     *
     * tests if a Collection is not null and not empty.
     *
     **********************************************************************/
    public static boolean exists(final Collection<?> arg) {
        return arg != null && !arg.isEmpty();
    }

    /**********************************************************************
     *
     * tests if an Iterator is not null and not empty.
     *
     **********************************************************************/
    public static boolean exists(final Iterator<?> arg) {
        return arg != null && arg.hasNext();
    }

    /**********************************************************************
     *
     * tests if an Optional is not null and not empty.
     *
     **********************************************************************/
    public static boolean exists(final Optional<?> arg) {
        return arg != null && arg.isPresent();
    }

    /**********************************************************************
     *
     * returns the just passed `arg`, only when the `arg` "exists".
     *
     * <p>
     * An exception will be thrown when does not "exist".
     * </p>
     *
     **********************************************************************/
    public static <T> T mandatory(final T arg) throws IllegalArgumentException {
        if (!exists(arg)) {
            throw new IllegalArgumentException("the object `arg` was null");
        }
        return arg;
    }

    /**********************************************************************
     *
     * returns the just passed string `arg`, only when the `arg`
     * "exists".
     *
     * <p>
     * Same behavior to {@code mandatory(arg, false)}.
     * </p>
     *
     * @param arg
     *            a string to test
     *
     **********************************************************************/
    public static String mandatory(final String arg) throws IllegalArgumentException {
        if (!exists(arg)) {
            throw new IllegalArgumentException("the string `arg` was null or empty");
        }
        return arg;
    }

    /**********************************************************************
     *
     * returns the just passed string `arg`, only when the `arg`
     * "exists".
     *
     * @param arg
     *            a string to test
     * @param preservingWhitespaces
     *            {@code false} : judges the `arg` empty when the
     *            argument is not empty but consists of only
     *            whitespaces; {@code true} : judges the `arg` empty
     *            when the `arg` argument is only surely empty,
     *            "whitespaces-only" is not considered as empty
     *
     * @return the string `arg` itself, when it "exists"
     *
     * @throws IllegalArgumentException
     *             it will be thrown when the string `arg` does not
     *             "exist"
     *
     **********************************************************************/
    public static String mandatory(final String arg, boolean preservingWhitespaces) throws IllegalArgumentException {
        if (!exists(arg, preservingWhitespaces)) {
            throw new IllegalArgumentException("the string `arg` was null or empty");
        }
        return arg;
    }

    /**********************************************************************
     *
     * returns the just passed array `arg`, only when the `arg`
     * "exists".
     *
     * <p>
     * An exception will be thrown when does not "exist".
     * </p>
     *
     **********************************************************************/
    public static <T> T[] mandatory(final T[] arg) throws IllegalArgumentException {
        if (!exists(arg)) {
            throw new IllegalArgumentException("the array `arg` was null or empty");
        }
        return arg;
    }

    /**********************************************************************
     *
     * returns the just passed Collection `arg`, only when the `arg`
     * "exists".
     *
     * <p>
     * An exception will be thrown when does not "exist".
     * </p>
     *
     **********************************************************************/
    public static <C extends Collection<T>, T> C mandatory(final C arg) throws IllegalArgumentException {
        if (!exists(arg)) {
            throw new IllegalArgumentException("the Collection `arg` was null or empty");
        }
        return arg;
    }

    /**********************************************************************
     *
     * returns the just passed Iterator `arg`, only when the `arg`
     * "exists".
     *
     * <p>
     * An exception will be thrown when does not "exist".
     * </p>
     *
     **********************************************************************/
    public static <I extends Iterator<T>, T> I mandatory(final I arg) throws IllegalArgumentException {
        if (!exists(arg)) {
            throw new IllegalArgumentException("the Iterator `arg` was null or empty");
        }
        return arg;
    }

    /**********************************************************************
     *
     * returns the just passed Optional `arg`, only when the `arg`
     * "exists".
     *
     * <p>
     * An exception will be thrown when does not "exist".
     * </p>
     *
     **********************************************************************/
    public static <T> Optional<T> mandatory(final Optional<T> arg) throws IllegalArgumentException {
        if (!exists(arg)) {
            throw new IllegalArgumentException("the Optional `arg` was null or empty");
        }
        return arg;
    }

    /**********************************************************************
     *
     * returns the Optional of the passed `arg`.
     *
     **********************************************************************/
    public static <T> Optional<T> optional(final T arg) {
        return exists(arg) ? Optional.of(arg) : Optional.empty();
    }

    /**********************************************************************
     *
     * returns the Optional of the passed string `arg`.
     *
     * <p>
     * Same behavior to {@code optional(arg, false)}.
     * </p>
     *
     * @param arg
     *            source string
     * 
     * @return the {@link Optional} of the string
     * 
     **********************************************************************/
    public static Optional<String> optional(final String arg) {
        return exists(arg) ? Optional.of(arg) : Optional.empty();
    }

    /**********************************************************************
     *
     * returns the Optional of the passed string `arg`.
     *
     * @param arg
     *            source string
     * @param preservingWhitespaces
     *            {@code false} : judges the `arg` empty when the
     *            argument is not empty but consists of only
     *            whitespaces; {@code true} : judges the `arg` empty
     *            when the `arg` argument is only surely empty,
     *            "whitespaces-only" is not considered as empty
     * 
     * @return the {@link Optional} of the string
     *
     **********************************************************************/
    public static Optional<String> optional(final String arg, boolean preservingWhitespaces) {
        return exists(arg, preservingWhitespaces) ? Optional.of(arg) : Optional.empty();
    }

    /**********************************************************************
     *
     * returns the Optional of the passed array `arg`.
     *
     **********************************************************************/
    public static <T> Optional<T[]> optional(final T[] arg) {
        return exists(arg) ? Optional.of(arg) : Optional.empty();
    }

    /**********************************************************************
     *
     * returns the Optional of the passed Collection `arg`.
     *
     **********************************************************************/
    public static <C extends Collection<T>, T> Optional<C> optional(final C arg) {
        return exists(arg) ? Optional.of(arg) : Optional.empty();
    }

    /**********************************************************************
     *
     * returns the Optional of the passed Iterator `arg`.
     *
     **********************************************************************/
    public static <I extends Iterator<T>, T> Optional<I> optional(final I arg) {
        return exists(arg) ? Optional.of(arg) : Optional.empty();
    }

    /**********************************************************************
     *
     * returns the passed Optional `arg`.
     *
     **********************************************************************/
    public static <T> Optional<T> optional(final Optional<T> arg) {
        return exists(arg) ? arg : Optional.empty();
    }
}
