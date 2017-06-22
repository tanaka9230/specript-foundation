
package org.specript.foundation;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.fail;
import org.junit.Test;
import java.io.BufferedReader;
import java.io.IOException;
import java.nio.charset.Charset;

public class ReadFileTest {
    @Test
    public void case_asReader_Succeeded() throws IOException {
        final ReadFileSettings theFileSettings = new ReadFileSettings(
                Resource.forName("org/specript/foundation/TestResourceFile.txt").asFile(),
                Charset.forName("Shift-JIS") //
        );
        try (final BufferedReader theReader = ReadFile.with(theFileSettings).asReader()) {
            final String got = BufferedReaderOps.toString(theReader, "\n");
            assertEquals("エンコードSHIFT-JIS、\n改行コードCR+LF\nのテキストファイルです。\n\n↑に空行があります。\n", got);
        }
    }

    @Test
    public void case_asReader_NotFound() throws IOException {
        final ReadFileSettings theFileSettings = ReadFileSettingsOps.with(
                "/var/tmp/test/org.specript.foundation.NotFound.txt",
                Charset.forName("Shift-JIS") //
        );
        try {
            ReadFile.with(theFileSettings).asReader();
            fail();
        } catch (final IOException e) {
            assertEquals("java.nio.file.NoSuchFileException: /var/tmp/test/org.specript.foundation.NotFound.txt", e.toString());
        }
    }
}
