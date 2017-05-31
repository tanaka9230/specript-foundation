
package org.specript.foundation;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.fail;
import org.junit.Test;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.nio.charset.Charset;

public class FileTest {
    @Test
    public void case_asReader_NormalEnd() throws IOException {
        final FileSettings theFileSetting = new FileSettings(
                Resource.forName("org/specript/foundation/TestResourceFile.txt").asFile(),
                Charset.forName("Shift-JIS"),
                false //
        );
        try (final BufferedReader theReader = File.with(theFileSetting).asReader()) {
            final String got = BufferedReaderOps.toString(theReader, "\n");
            assertEquals("エンコードSHIFT-JIS、\n改行コードCR+LF\nのテキストファイルです。\n\n↑に空行があります。\n", got);
        }
    }

    @Test
    public void case_asReader_NotFound() throws IOException {
        final FileSettings theFileSetting = new FileSettings(
                "/var/tmp/test/org.specript.foundation.NotFound.txt",
                Charset.forName("Shift-JIS"),
                false //
        );
        try {
            File.with(theFileSetting).asReader();
            fail();
        } catch (final IOException e) {
            assertEquals("java.nio.file.NoSuchFileException: /var/tmp/test/org.specript.foundation.NotFound.txt", e.toString());
        }
    }

    @Test
    public void case_asWriter_NormalEnd() throws IOException {
        final String theFilePathName = "/var/tmp/test/org.specript.foundation.TestWrittenFile.txt";
        {
            final FileSettings theFileSetting = new FileSettings(
                    theFilePathName,
                    Charset.forName("UTF-8"),
                    false //
            );
            try (final BufferedWriter theWriter = File.with(theFileSetting).asWriter()) {
                theWriter.write("1行目書いた\n");
            }
        }
        {
            final FileSettings theFileSetting = new FileSettings(
                    theFilePathName,
                    Charset.forName("UTF-8"),
                    false //
            );
            try (final BufferedWriter theWriter = File.with(theFileSetting).asWriter()) {
                theWriter.write("2行目書いた\n");
            }
        }
        {
            final FileSettings theFileSetting = new FileSettings(
                    theFilePathName,
                    Charset.forName("UTF-8"),
                    true //
            );
            try (final BufferedWriter theWriter = File.with(theFileSetting).asWriter()) {
                theWriter.write("3行目書いた\n");
            }
        }
        {
            final FileSettings theFileSetting = new FileSettings(
                    theFilePathName,
                    Charset.forName("UTF-8"),
                    false //
            );
            try (BufferedReader theReader = File.with(theFileSetting).asReader()) {
                final String got = BufferedReaderOps.toString(theReader, "\n");
                assertEquals("2行目書いた\n3行目書いた\n", got);
            }
        }
    }
}
