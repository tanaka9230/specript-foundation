
package org.specript.foundation;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.fail;
import org.junit.Test;
import java.util.MissingResourceException;
import java.util.Properties;

public class ResourceTest {
    @Test
    public void case_Succeeded() {
        final Properties theProperties = Resource.forName("org/specript/foundation/ResourceTest.properties").asProperties();
        assertEquals("123", theProperties.getProperty("key1"));
        assertEquals("hogehoge", theProperties.getProperty("group.key2"));
        assertEquals("佐々木希", theProperties.getProperty("key3"));
    }

    @Test
    public void case_NotFound() {
        try {
            Resource.forName("org/specript/foundation/NotFound.properties");
            fail();
        } catch (final MissingResourceException e) {
            assertEquals("the resource 'org/specript/foundation/NotFound.properties' not found", e.getMessage());
        }
    }

    @Test
    public void case_TheNamePathStartsWithSlash_then_NotFound() {
        try {
            Resource.forName("/org/specript/foundation/ResourceTest.properties");
            fail();
        } catch (final MissingResourceException e) {
            assertEquals("the resource '/org/specript/foundation/ResourceTest.properties' not found", e.getMessage());
        }
    }
}
