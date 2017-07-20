
package org.specript.foundation;

import static org.junit.Assert.assertEquals;
import org.junit.Test;

public class MemoizedTest {
    int thisCalledCount = 0;

    Memoized<Integer> thisMemoizedInteger = Memoized.of(() -> {
        ++thisCalledCount;
        System.out.println(thisCalledCount);
        return IntegerOps.from(thisCalledCount);
    });

    @Test
    public void test() {
        final Integer got1 = thisMemoizedInteger.get();
        assertEquals(IntegerOps.from(1), got1);
        final Integer got2 = thisMemoizedInteger.get();
        assertEquals(IntegerOps.from(1), got2);
        final Integer got3 = thisMemoizedInteger.get();
        assertEquals(IntegerOps.from(1), got3);
    }
}
