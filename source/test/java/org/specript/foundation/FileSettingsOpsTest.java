
package org.specript.foundation;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.fail;
import org.specript.foundation.FileSettings.WritingMode;
import org.specript.foundation.exceptions.InvalidSettingsException;
import org.junit.BeforeClass;
import org.junit.Test;
import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;

public class FileSettingsOpsTest {
    @BeforeClass
    public static void setup() {
        FileSettings.defaultCharset = StandardCharsets.UTF_8;
    }

    @Test
    public void case_fromProperties_SetFilePath_and_DefaultEncoding_DefaultWritingMode_then_Succeeded() {
        final FileSettings theFileSettings = FileSettingsOps.fromProperties(Resource.forName("org/specript/foundation/FileSettingsOpsTest1.properties").asProperties());
        assertEquals(new java.io.File("/var/tmp/test/org.specript.foundation.TestFile.txt").toString(), theFileSettings.File().toString());
        assertEquals(Charset.forName("UTF-8"), theFileSettings.Charset());
        assertEquals(WritingMode.DEFAULT, theFileSettings.WritingMode());
    }

    @Test
    public void case_fromProperties_SetResourceNamePath_and_SetEncodingAndWritingMode_then_Succeeded() {
        final FileSettings theFileSettings = FileSettingsOps.fromProperties(Resource.forName("org/specript/foundation/FileSettingsOpsTest2.properties").asProperties());
        assertEquals(Resource.forName("org/specript/foundation/TestResourceFile.txt").asFile().toString(), theFileSettings.File().toString());
        assertEquals(Charset.forName("Shift-JIS"), theFileSettings.Charset());
        assertEquals(WritingMode.APPENDING, theFileSettings.WritingMode());
    }

    @Test
    public void case_fromProperties_SetNotExistingFilePath_then_Succeeded() {
        final FileSettings theFileSettings = FileSettingsOps.fromProperties(Resource.forName("org/specript/foundation/FileSettingsOpsTest3.properties").asProperties());
        assertEquals(new java.io.File("/var/tmp/test/org.specript.foundation.NotFound.txt").toString(), theFileSettings.File().toString());
        // no error occurs when the file specified with 'file_path' does not exist
    }

    @Test
    public void case_fromProperties_SetNotExistingResourceNamePath_then_Failed() {
        try {
            @SuppressWarnings("unused")
            final FileSettings theFileSettings = FileSettingsOps.fromProperties(Resource.forName("org/specript/foundation/FileSettingsOpsTest4.properties").asProperties());
            fail();
        } catch (final InvalidSettingsException e) {
            // an error occurs when the file specified with 'resource_name_path' does not exist
            assertEquals("org.specript.foundation.exceptions.InvalidSettingsException: java.util.MissingResourceException: the resource 'org/specript/foundation/NotFound.txt' not found", e.toString());
        }
    }

    @Test
    public void case_fromProperties_5etNeitherFilePathNorResourceNamePath_then_Failed() {
        try {
            FileSettingsOps.fromProperties(Resource.forName("org/specript/foundation/FileSettingsOpsTest5.properties").asProperties());
            fail();
        } catch (final InvalidSettingsException e) {
            assertEquals("org.specript.foundation.exceptions.InvalidSettingsException: neither 'FilePath' nor 'ResourceNamePath' were set", e.toString());
        }
    }

    @Test
    public void case_fromProperties_SetUnknownEncoding_then_Failed() {
        try {
            FileSettingsOps.fromProperties(Resource.forName("org/specript/foundation/FileSettingsOpsTest6.properties").asProperties());
            fail();
        } catch (final InvalidSettingsException e) {
            assertEquals("org.specript.foundation.exceptions.InvalidSettingsException: java.nio.charset.UnsupportedCharsetException: HogeHoge", e.toString());
        }
    }

    @Test
    public void case_fromProperties_SetUnknownWritingMode_then_Failed() {
        try {
            FileSettingsOps.fromProperties(Resource.forName("org/specript/foundation/FileSettingsOpsTest7.properties").asProperties());
            fail();
        } catch (final InvalidSettingsException e) {
            e.printStackTrace();
            assertEquals("org.specript.foundation.exceptions.InvalidSettingsException: java.lang.IllegalArgumentException: No enum constant org.specript.foundation.FileSettings.WritingMode.HOGEHOGE", e.toString());
        }
    }
}
