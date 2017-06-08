
package org.specript.foundation;

import static org.specript.foundation.Ops.exists;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
import org.junit.Test;

public class OpsTest {
    @Test
    public void case_existsObject() {
        {
            final Integer arg = null;
            final boolean ret = exists(arg);
            assertFalse(ret);
        }
        {
            final Integer arg = Integer.valueOf(0);
            final boolean ret = exists(arg);
            assertTrue(ret);
        }
        {
            final Integer arg = Integer.valueOf(12345);
            final boolean ret = exists(arg);
            assertTrue(ret);
        }
        {
            final Object arg = "";
            final boolean ret = exists(arg);
            assertTrue(ret);
        }
        {
            final Object arg = "   ";
            final boolean ret = exists(arg);
            assertTrue(ret);
        }
        {
            final Object arg = "abc";
            final boolean ret = exists(arg);
            assertTrue(ret);
        }
    }

    @Test
    public void case_existsString() {
        {
            final String arg = null;
            final boolean ret = exists(arg);
            assertFalse(ret);
        }
        {
            final String arg = "";
            final boolean ret = exists(arg);
            assertFalse(ret);
        }
        {
            final String arg = "   ";
            final boolean ret = exists(arg);
            assertFalse(ret);
        }
        {
            final String arg = "  abc  ";
            final boolean ret = exists(arg);
            assertTrue(ret);
        }
        {
            final String arg = "abc";
            final boolean ret = exists(arg);
            assertTrue(ret);
        }
        {
            final String arg = null;
            final boolean ret = exists(arg, false);
            assertFalse(ret);
        }
        {
            final String arg = "";
            final boolean ret = exists(arg, false);
            assertFalse(ret);
        }
        {
            final String arg = "   ";
            final boolean ret = exists(arg, false);
            assertFalse(ret);
        }
        {
            final String arg = "  abc  ";
            final boolean ret = exists(arg, false);
            assertTrue(ret);
        }
        {
            final String arg = "abc";
            final boolean ret = exists(arg, false);
            assertTrue(ret);
        }
        {
            final String arg = null;
            final boolean ret = exists(arg, true);
            assertFalse(ret);
        }
        {
            final String arg = "";
            final boolean ret = exists(arg, true);
            assertFalse(ret);
        }
        {
            final String arg = "   ";
            final boolean ret = exists(arg, true);
            assertTrue(ret);
        }
        {
            final String arg = "  abc  ";
            final boolean ret = exists(arg, true);
            assertTrue(ret);
        }
        {
            final String arg = "abc";
            final boolean ret = exists(arg, true);
            assertTrue(ret);
        }
    }
}
