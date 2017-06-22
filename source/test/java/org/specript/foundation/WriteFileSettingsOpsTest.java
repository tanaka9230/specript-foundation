
package org.specript.foundation;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.fail;
import org.specript.foundation.WriteFileSettings.WritingMode;
import org.specript.foundation.exceptions.InvalidSettingsException;
import org.junit.Test;
import java.io.File;
import java.nio.charset.Charset;

public class WriteFileSettingsOpsTest {
    @Test
    public void case_fromProperties_SetFilePath_and_DefaultEncoding_DefaultWritingMode_then_Succeeded() {
        final WriteFileSettings theFileSettings = WriteFileSettingsOps.fromProperties(Resource.forName("org/specript/foundation/FileSettingsOpsTest1.properties").asProperties());
        assertEquals(new File("/var/tmp/test/org.specript.foundation.TestFile.txt").toString(), theFileSettings.File().toString());
        assertEquals(Charset.forName("UTF-8"), theFileSettings.Charset());
        assertEquals(WritingMode.DEFAULT, theFileSettings.WritingMode());
    }

    @Test
    public void case_fromProperties_SetFilePath_and_SetEncodingAndWritingMode_then_Succeeded() {
        final WriteFileSettings theFileSettings = WriteFileSettingsOps.fromProperties(Resource.forName("org/specript/foundation/FileSettingsOpsTest2w.properties").asProperties());
        assertEquals(new File("/var/tmp/test/org.specript.foundation.TestFile.txt").toString(), theFileSettings.File().toString());
        assertEquals(Charset.forName("Shift-JIS"), theFileSettings.Charset());
        assertEquals(WritingMode.APPENDING, theFileSettings.WritingMode());
    }

    @Test
    public void case_fromProperties_SetNotExistingFilePath_then_Succeeded() {
        final WriteFileSettings theFileSettings = WriteFileSettingsOps.fromProperties(Resource.forName("org/specript/foundation/FileSettingsOpsTest3.properties").asProperties());
        assertEquals(new File("/var/tmp/test/org.specript.foundation.NotFound.txt").toString(), theFileSettings.File().toString());
        // no error occurs when the file specified with 'FilePath' does not exist
    }

    @Test
    public void case_fromProperties_SetResourceNamePathButFilePath_then_Failed() {
        try {
            @SuppressWarnings("unused")
            final WriteFileSettings theFileSettings = WriteFileSettingsOps.fromProperties(Resource.forName("org/specript/foundation/FileSettingsOpsTest4.properties").asProperties());
            fail();
        } catch (final InvalidSettingsException e) {
            // WriteFileSettingsOps does not treat 'ResourceNamePath'
            assertEquals("org.specript.foundation.exceptions.InvalidSettingsException: 'FilePath' was not set", e.toString());
        }
    }

    @Test
    public void case_fromProperties_5etNeitherFilePathNorResourceNamePath_then_Failed() {
        try {
            WriteFileSettingsOps.fromProperties(Resource.forName("org/specript/foundation/FileSettingsOpsTest5.properties").asProperties());
            fail();
        } catch (final InvalidSettingsException e) {
            // WriteFileSettingsOps does not treat 'ResourceNamePath'
            assertEquals("org.specript.foundation.exceptions.InvalidSettingsException: 'FilePath' was not set", e.toString());
        }
    }

    @Test
    public void case_fromProperties_SetUnknownEncoding_then_Failed() {
        try {
            WriteFileSettingsOps.fromProperties(Resource.forName("org/specript/foundation/FileSettingsOpsTest6.properties").asProperties());
            fail();
        } catch (final InvalidSettingsException e) {
            assertEquals("org.specript.foundation.exceptions.InvalidSettingsException: java.nio.charset.UnsupportedCharsetException: HogeHoge", e.toString());
        }
    }

    @Test
    public void case_fromProperties_SetUnknownWritingMode_then_Failed() {
        try {
            WriteFileSettingsOps.fromProperties(Resource.forName("org/specript/foundation/FileSettingsOpsTest7.properties").asProperties());
            fail();
        } catch (final InvalidSettingsException e) {
            e.printStackTrace();
            assertEquals("org.specript.foundation.exceptions.InvalidSettingsException: java.lang.IllegalArgumentException: No enum constant org.specript.foundation.WriteFileSettings.WritingMode.HOGEHOGE", e.toString());
        }
    }
}
