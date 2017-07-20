/* ======================================---------------------------

  --+ Specript Foundation Library v3.0 +--

  Copyright (c) 2007-2017, Koichi Tanaka (@Tanaka9230)

    ---------------------------====================================== */

package org.specript.foundation;

import static org.specript.foundation.Ops.mandatory;
import java.util.function.Supplier;

/**********************************************************************
 *
 * Memoized Object.
 *
 * <p>
 * Remembers (caches) the return value of the source {@link Supplier}'s
 * {@code get} method at the first call. Returns the cached value after
 * the second call.
 * </p>
 * <p>
 * Notice this {@link Memoized} is NOT thread-safe.
 * </p>
 *
 **********************************************************************/
public class Memoized<T> implements Supplier<T> {
    /**********************************************************************
     *
     * to obtain the {@link Memoized} with the source {@link Supplier}.
     *
     * @param source
     *            the source {@link Supplier} to be memoized
     *
     **********************************************************************/
    public static <T> Memoized<T> of(final Supplier<T> source) throws IllegalArgumentException {
        return new Memoized<T>(source);
    }

    //
    //
    //

    private final Supplier<T> source;
    private T ret = null;

    private boolean got = false;

    private Memoized(final Supplier<T> source) throws IllegalArgumentException {
        this.source = mandatory(source);
    }

    @Override
    public T get() {
        if (!got) {
            ret = source.get();
        }
        got = true;
        return ret;
    }
}
