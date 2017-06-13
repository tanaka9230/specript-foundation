
package org.specript.http;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.fail;
import org.junit.Test;
import java.nio.charset.StandardCharsets;
import java.nio.charset.UnsupportedCharsetException;

public class ContentTypeTest {
    @Test
    public void case_Null() {
        final ContentType got = new ContentType(null);
        assertNull(got.MediaType());
        assertNull(got.Charset());
        assertEquals("", got.toString());
    }

    @Test
    public void case_Empty() {
        final ContentType got = new ContentType("");
        assertNull(got.MediaType());
        assertNull(got.Charset());
        assertEquals("", got.toString());
    }

    @Test
    public void case_NoParameter() {
        final ContentType got = new ContentType("image/png");
        assertEquals("image/png", got.MediaType());
        assertNull(got.Charset());
        assertEquals("image/png", got.toString());
    }

    @Test
    public void case_NoParameter_WithExtraSpaces() {
        final ContentType got = new ContentType("  image/png  ");
        assertEquals("image/png", got.MediaType());
        assertNull(got.Charset());
        assertEquals("image/png", got.toString());
    }

    @Test
    public void case_WithParameter() {
        final ContentType got = new ContentType("hoge/fuga; foo=bar");
        assertEquals("hoge/fuga", got.MediaType());
        assertNull(got.Charset());
        assertEquals("hoge/fuga; foo=bar", got.toString());
    }

    @Test
    public void case_WithParameter_WithExtraSpaces() {
        final ContentType got = new ContentType("  hoge/fuga  ;  foo = bar  ");
        assertEquals("hoge/fuga", got.MediaType());
        assertNull(got.Charset());
        assertEquals("hoge/fuga; foo=bar", got.toString());
    }

    @Test
    public void case_WithCharsetParameter() {
        final ContentType got = new ContentType("text/html; charset=UTF-8");
        assertEquals("text/html", got.MediaType());
        assertEquals(StandardCharsets.UTF_8, got.Charset());
        assertEquals("text/html; charset=UTF-8", got.toString());
    }

    @Test
    public void case_WithCharsetParameter_WithExtraSpaces() {
        final ContentType got = new ContentType("  text/html  ;  charset = UTF-8  ");
        assertEquals("text/html", got.MediaType());
        assertEquals(StandardCharsets.UTF_8, got.Charset());
        assertEquals("text/html; charset=UTF-8", got.toString());
    }

    @Test
    public void case_WithCharsetParameter_CapitalLetter_WithExtraSpaces() {
        final ContentType got = new ContentType("  TEXT/HTML  ;  CHARSET = UTF-8  ");
        assertEquals("text/html", got.MediaType());
        assertEquals(StandardCharsets.UTF_8, got.Charset());
        assertEquals("text/html; charset=UTF-8", got.toString());
    }

    @Test
    public void case_WithUnknownCharset() {
        try {
            @SuppressWarnings("unused")
            final ContentType tmp = new ContentType("text/html; charset=XXYYZZ");
            fail();
        } catch (final UnsupportedCharsetException e) {
            assertEquals("java.nio.charset.UnsupportedCharsetException: XXYYZZ", e.toString());
        }
    }

    @Test
    public void case_WithCharsetParameter_WithExtraUnknownParmeter_then_KeywordUncapitalized_ValueCapitalizationPreserved_TrailingSemicolonsRemoved_AllTrimmed() {
        final ContentType got = new ContentType("  text/html ;  charset  =utf-8; FOO = BAR ; Hoge =  ; Piyo ; ;;");
        assertEquals("text/html", got.MediaType());
        assertEquals(StandardCharsets.UTF_8, got.Charset());
        assertEquals("text/html; charset=utf-8; foo=BAR; hoge=; piyo", got.toString());
    }
}
