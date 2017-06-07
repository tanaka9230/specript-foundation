
package org.specript.foundation;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.fail;
import org.specript.foundation.FileSettings.WritingMode;
import org.junit.BeforeClass;
import org.junit.Test;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;

public class FileTest {
    @BeforeClass
    public static void setup() {
        FileSettings.defaultCharset = StandardCharsets.UTF_8;
    }

    @Test
    public void case_asReader_Succeeded() throws IOException {
        final FileSettings theFileSettings = new FileSettings(
                Resource.forName("org/specript/foundation/TestResourceFile.txt").asFile(),
                Charset.forName("Shift-JIS"),
                null //
        );
        try (final BufferedReader theReader = File.with(theFileSettings).asReader()) {
            final String got = BufferedReaderOps.toString(theReader, "\n");
            assertEquals("エンコードSHIFT-JIS、\n改行コードCR+LF\nのテキストファイルです。\n\n↑に空行があります。\n", got);
        }
    }

    @Test
    public void case_asReader_NotFound() throws IOException {
        final FileSettings theFileSettings = FileSettingsOps.with(
                "/var/tmp/test/org.specript.foundation.NotFound.txt",
                Charset.forName("Shift-JIS") //
        );
        try {
            File.with(theFileSettings).asReader();
            fail();
        } catch (final IOException e) {
            assertEquals("java.nio.file.NoSuchFileException: /var/tmp/test/org.specript.foundation.NotFound.txt", e.toString());
        }
    }

    @Test
    public void case_asWriter_DefaultWritingMode() throws IOException {
        final String theFilePathName = "/var/tmp/test/org.specript.foundation.FileTest.txt";
        {
            Files.deleteIfExists(Paths.get(theFilePathName));
        }
        {
            final FileSettings theFileSettings = FileSettingsOps.with(
                    theFilePathName,
                    WritingMode.DEFAULT //
            );
            try (final BufferedWriter theWriter = File.with(theFileSettings).asWriter()) {
                theWriter.write("line one\n");
            }
        }
        {
            final FileSettings theFileSettings = FileSettingsOps.with(theFilePathName);
            try (BufferedReader theReader = File.with(theFileSettings).asReader()) {
                final String got = BufferedReaderOps.toString(theReader, "\n");
                assertEquals("line one\n", got);
            }
        }
        {
            final FileSettings theFileSettings = FileSettingsOps.with(
                    theFilePathName,
                    WritingMode.DEFAULT //
            );
            try {
                File.with(theFileSettings).asWriter();
                fail();
            } catch (final IOException e) {
                assertEquals("java.nio.file.FileAlreadyExistsException: /var/tmp/test/org.specript.foundation.FileTest.txt", e.toString());
            }
        }
    }

    @Test
    public void case_asWriter_OverwritingMode() throws IOException {
        final String theFilePathName = "/var/tmp/test/org.specript.foundation.FileTest.txt";
        {
            final FileSettings theFileSettings = FileSettingsOps.with(
                    theFilePathName,
                    WritingMode.OVERWRITING //
            );
            try (final BufferedWriter theWriter = File.with(theFileSettings).asWriter()) {
                theWriter.write("overwriting: line one\n");
            }
        }
        {
            final FileSettings theFileSettings = FileSettingsOps.with(
                    theFilePathName,
                    WritingMode.OVERWRITING //
            );
            try (final BufferedWriter theWriter = File.with(theFileSettings).asWriter()) {
                theWriter.write("overwriting: line two\n");
            }
        }
        {
            final FileSettings theFileSettings = FileSettingsOps.with(theFilePathName);
            try (BufferedReader theReader = File.with(theFileSettings).asReader()) {
                final String got = BufferedReaderOps.toString(theReader, "\n");
                assertEquals("overwriting: line two\n", got);
            }
        }
        {
            final FileSettings theFileSettings = FileSettingsOps.with(
                    theFilePathName,
                    WritingMode.OVERWRITING //
            );
            try (final BufferedWriter theWriter = File.with(theFileSettings).asWriter()) {
            }
        }
        {
            final FileSettings theFileSettings = FileSettingsOps.with(theFilePathName);
            try (BufferedReader theReader = File.with(theFileSettings).asReader()) {
                final String got = BufferedReaderOps.toString(theReader, "\n");
                assertEquals("\n", got);
            }
        }
    }

    @Test
    public void case_asWriter_AppendingMode() throws IOException {
        final String theFilePathName = "/var/tmp/test/org.specript.foundation.FileTest.txt";
        {
            final FileSettings theFileSettings = FileSettingsOps.with(
                    theFilePathName,
                    WritingMode.OVERWRITING //
            );
            try (final BufferedWriter theWriter = File.with(theFileSettings).asWriter()) {
                theWriter.write("appending: line one\n");
            }
        }
        {
            final FileSettings theFileSettings = FileSettingsOps.with(
                    theFilePathName,
                    WritingMode.APPENDING //
            );
            try (final BufferedWriter theWriter = File.with(theFileSettings).asWriter()) {
                theWriter.write("appending: line two\n");
            }
        }
        {
            final FileSettings theFileSettings = FileSettingsOps.with(theFilePathName);
            try (BufferedReader theReader = File.with(theFileSettings).asReader()) {
                final String got = BufferedReaderOps.toString(theReader, "\n");
                assertEquals("appending: line one\nappending: line two\n", got);
            }
        }
        {
            final FileSettings theFileSettings = FileSettingsOps.with(
                    theFilePathName,
                    WritingMode.APPENDING //
            );
            try (final BufferedWriter theWriter = File.with(theFileSettings).asWriter()) {
            }
        }
        {
            final FileSettings theFileSettings = FileSettingsOps.with(theFilePathName);
            try (BufferedReader theReader = File.with(theFileSettings).asReader()) {
                final String got = BufferedReaderOps.toString(theReader, "\n");
                assertEquals("appending: line one\nappending: line two\n", got);
            }
        }
    }
}
