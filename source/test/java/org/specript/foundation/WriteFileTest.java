
package org.specript.foundation;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.fail;
import org.specript.foundation.WriteFileSettings.WritingMode;
import org.junit.Test;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

public class WriteFileTest {
    @Test
    public void case_asWriter_DefaultWritingMode() throws IOException {
        final String theFilePathName = "/var/tmp/test/org.specript.foundation.FileTest.txt";
        {
            Files.deleteIfExists(Paths.get(theFilePathName));
        }
        {
            final WriteFileSettings theFileSettings = WriteFileSettingsOps.with(
                    theFilePathName,
                    WritingMode.DEFAULT //
            );
            try (final BufferedWriter theWriter = WriteFile.with(theFileSettings).asWriter()) {
                theWriter.write("line one\n");
            }
        }
        {
            final ReadFileSettings theFileSettings = ReadFileSettingsOps.with(theFilePathName);
            try (BufferedReader theReader = ReadFile.with(theFileSettings).asReader()) {
                final String got = BufferedReaderOps.toString(theReader, "\n");
                assertEquals("line one\n", got);
            }
        }
        {
            final WriteFileSettings theFileSettings = WriteFileSettingsOps.with(
                    theFilePathName,
                    WritingMode.DEFAULT //
            );
            try {
                WriteFile.with(theFileSettings).asWriter();
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
            final WriteFileSettings theFileSettings = WriteFileSettingsOps.with(
                    theFilePathName,
                    WritingMode.OVERWRITING //
            );
            try (final BufferedWriter theWriter = WriteFile.with(theFileSettings).asWriter()) {
                theWriter.write("overwriting: line one\n");
            }
        }
        {
            final WriteFileSettings theFileSettings = WriteFileSettingsOps.with(
                    theFilePathName,
                    WritingMode.OVERWRITING //
            );
            try (final BufferedWriter theWriter = WriteFile.with(theFileSettings).asWriter()) {
                theWriter.write("overwriting: line two\n");
            }
        }
        {
            final ReadFileSettings theFileSettings = ReadFileSettingsOps.with(theFilePathName);
            try (BufferedReader theReader = ReadFile.with(theFileSettings).asReader()) {
                final String got = BufferedReaderOps.toString(theReader, "\n");
                assertEquals("overwriting: line two\n", got);
            }
        }
        {
            final WriteFileSettings theFileSettings = WriteFileSettingsOps.with(
                    theFilePathName,
                    WritingMode.OVERWRITING //
            );
            try (final BufferedWriter theWriter = WriteFile.with(theFileSettings).asWriter()) {
            }
        }
        {
            final ReadFileSettings theFileSettings = ReadFileSettingsOps.with(theFilePathName);
            try (BufferedReader theReader = ReadFile.with(theFileSettings).asReader()) {
                final String got = BufferedReaderOps.toString(theReader, "\n");
                assertEquals("\n", got);
            }
        }
    }

    @Test
    public void case_asWriter_AppendingMode() throws IOException {
        final String theFilePathName = "/var/tmp/test/org.specript.foundation.FileTest.txt";
        {
            final WriteFileSettings theFileSettings = WriteFileSettingsOps.with(
                    theFilePathName,
                    WritingMode.OVERWRITING //
            );
            try (final BufferedWriter theWriter = WriteFile.with(theFileSettings).asWriter()) {
                theWriter.write("appending: line one\n");
            }
        }
        {
            final WriteFileSettings theFileSettings = WriteFileSettingsOps.with(
                    theFilePathName,
                    WritingMode.APPENDING //
            );
            try (final BufferedWriter theWriter = WriteFile.with(theFileSettings).asWriter()) {
                theWriter.write("appending: line two\n");
            }
        }
        {
            final ReadFileSettings theFileSettings = ReadFileSettingsOps.with(theFilePathName);
            try (BufferedReader theReader = ReadFile.with(theFileSettings).asReader()) {
                final String got = BufferedReaderOps.toString(theReader, "\n");
                assertEquals("appending: line one\nappending: line two\n", got);
            }
        }
        {
            final WriteFileSettings theFileSettings = WriteFileSettingsOps.with(
                    theFilePathName,
                    WritingMode.APPENDING //
            );
            try (final BufferedWriter theWriter = WriteFile.with(theFileSettings).asWriter()) {
            }
        }
        {
            final ReadFileSettings theFileSettings = ReadFileSettingsOps.with(theFilePathName);
            try (BufferedReader theReader = ReadFile.with(theFileSettings).asReader()) {
                final String got = BufferedReaderOps.toString(theReader, "\n");
                assertEquals("appending: line one\nappending: line two\n", got);
            }
        }
    }
}
