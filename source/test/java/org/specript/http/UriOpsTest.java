
package org.specript.http;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.fail;
import org.junit.Test;
import java.net.URI;
import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;
import java.util.LinkedHashMap;

public class UriOpsTest {
    @Test
    public void case_Normal() {
        final URI got = UriOps.with(
                "http://example.com:8000/MyService",
                "/customers",
                new LinkedHashMap<String, String>() {
                    {
                        put("key1", "value1");
                        put("key2", "ぴよ");
                    }
                },
                StandardCharsets.UTF_8 //
        );
        assertEquals("http://example.com:8000/MyService/customers?key1=value1&key2=%E3%81%B4%E3%82%88", got.toString());
    }

    @Test
    public void case_OtherEncoding() {
        final URI got = UriOps.with(
                "http://example.com:8000/MyService",
                "/customers",
                new LinkedHashMap<String, String>() {
                    {
                        put("key1", "value1");
                        put("key2", "ぴよ");
                    }
                },
                Charset.forName("Shift-JIS") //
        );
        assertEquals("http://example.com:8000/MyService/customers?key1=value1&key2=%82%D2%82%E6", got.toString());
    }

    @Test
    public void case_NoPathInfo() {
        final URI got = UriOps.with(
                "http://example.com:8000/MyService",
                null,
                new LinkedHashMap<String, String>() {
                    {
                        put("key1", "value1");
                        put("key2", "ぴよ");
                    }
                },
                StandardCharsets.UTF_8 //
        );
        assertEquals("http://example.com:8000/MyService?key1=value1&key2=%E3%81%B4%E3%82%88", got.toString());
    }

    @Test
    public void case_NoQueryParameters() {
        final URI got = UriOps.with(
                "http://example.com:8000/MyService",
                "/customers",
                null,
                null //
        );
        assertEquals("http://example.com:8000/MyService/customers", got.toString());
    }

    @Test
    public void case_EmptyQueryParameters() {
        final URI got = UriOps.with(
                "http://example.com:8000/MyService",
                "/customers",
                new LinkedHashMap<String, String>() {
                },
                StandardCharsets.UTF_8 //
        );
        assertEquals("http://example.com:8000/MyService/customers", got.toString());
    }

    @Test
    public void case_PathInfoNotStartsWithSlash() {
        final URI got = UriOps.with(
                "http://example.com:8000/MyService",
                "customers",
                new LinkedHashMap<String, String>() {
                    {
                        put("key1", "value1");
                        put("key2", "ぴよ");
                    }
                },
                StandardCharsets.UTF_8 //
        );
        assertEquals("http://example.com:8000/MyServicecustomers?key1=value1&key2=%E3%81%B4%E3%82%88", got.toString());
    }

    @Test
    public void case_NoBaseUri() {
        try {
            @SuppressWarnings("unused")
            final URI got = UriOps.with(
                    null,
                    "/customers",
                    new LinkedHashMap<String, String>() {
                        {
                            put("key1", "value1");
                            put("key2", "ぴよ");
                        }
                    },
                    StandardCharsets.UTF_8 //
            );
            fail();
        } catch (final IllegalArgumentException e) {
            e.printStackTrace();
            assertEquals("java.lang.IllegalArgumentException: the string `arg` was null or empty", e.toString());
        }
    }

    @Test
    public void case_InvalidUri() {
        try {
            @SuppressWarnings("unused")
            final URI got = UriOps.with(
                    ":",
                    "/customers",
                    new LinkedHashMap<String, String>() {
                        {
                            put("key1", "value1");
                            put("key2", "ぴよ");
                        }
                    },
                    StandardCharsets.UTF_8 //
            );
            fail();
        } catch (final IllegalArgumentException e) {
            e.printStackTrace();
            assertEquals("java.lang.IllegalArgumentException: java.net.URISyntaxException: Expected scheme name at index 0: :/customers", e.toString());
        }
    }
}
