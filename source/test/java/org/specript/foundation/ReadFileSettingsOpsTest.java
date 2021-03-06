
package org.specript.foundation;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.fail;
import org.specript.foundation.exceptions.InvalidSettingsException;
import org.junit.Test;
import java.io.File;
import java.nio.charset.Charset;

public class ReadFileSettingsOpsTest {
    @Test
    public void case_fromProperties_SetFilePath_and_DefaultEncoding_then_Succeeded() {
        final ReadFileSettings theFileSettings = ReadFileSettingsOps.from(Resource.forName("org/specript/foundation/FileSettingsOpsTest1.properties").asProperties());
        assertEquals(new File("/var/tmp/test/org.specript.foundation.TestFile.txt").toString(), theFileSettings.File().toString());
        assertEquals(Charset.forName("UTF-8"), theFileSettings.Charset());
    }

    @Test
    public void case_fromProperties_SetResourceNamePath_and_SetEncoding_then_Succeeded() {
        final ReadFileSettings theFileSettings = ReadFileSettingsOps.from(Resource.forName("org/specript/foundation/FileSettingsOpsTest2r.properties").asProperties());
        assertEquals(Resource.forName("org/specript/foundation/TestResourceFile.txt").asFile().toString(), theFileSettings.File().toString());
        assertEquals(Charset.forName("Shift-JIS"), theFileSettings.Charset());
        // ReadFileSettings does not treat 'WritingMode'
    }

    @Test
    public void case_fromProperties_SetNotExistingFilePath_then_Succeeded() {
        final ReadFileSettings theFileSettings = ReadFileSettingsOps.from(Resource.forName("org/specript/foundation/FileSettingsOpsTest3.properties").asProperties());
        assertEquals(new File("/var/tmp/test/org.specript.foundation.NotFound.txt").toString(), theFileSettings.File().toString());
        // no error occurs when the file specified with 'FilePath' does not exist
    }

    @Test
    public void case_fromProperties_SetNotExistingResourceNamePath_then_Failed() {
        try {
            @SuppressWarnings("unused")
            final ReadFileSettings theFileSettings = ReadFileSettingsOps.from(Resource.forName("org/specript/foundation/FileSettingsOpsTest4.properties").asProperties());
            fail();
        } catch (final InvalidSettingsException e) {
            // an error occurs when the file specified with 'ResourceNamePath' does not exist
            assertEquals("org.specript.foundation.exceptions.InvalidSettingsException: java.util.MissingResourceException: the resource 'org/specript/foundation/NotFound.txt' not found", e.toString());
        }
    }

    @Test
    public void case_fromProperties_5etNeitherFilePathNorResourceNamePath_then_Failed() {
        try {
            ReadFileSettingsOps.from(Resource.forName("org/specript/foundation/FileSettingsOpsTest5.properties").asProperties());
            fail();
        } catch (final InvalidSettingsException e) {
            assertEquals("org.specript.foundation.exceptions.InvalidSettingsException: neither 'FilePath' nor 'ResourceNamePath' were set", e.toString());
        }
    }

    @Test
    public void case_fromProperties_SetUnknownEncoding_then_Failed() {
        try {
            ReadFileSettingsOps.from(Resource.forName("org/specript/foundation/FileSettingsOpsTest6.properties").asProperties());
            fail();
        } catch (final InvalidSettingsException e) {
            assertEquals("org.specript.foundation.exceptions.InvalidSettingsException: java.nio.charset.UnsupportedCharsetException: HogeHoge", e.toString());
        }
    }

    @Test
    public void case_fromProperties_SetUnknownWritingMode_then_Succeeded() {
        ReadFileSettingsOps.from(Resource.forName("org/specript/foundation/FileSettingsOpsTest7.properties").asProperties());
        // no error occurs (ReadFileSettingsOps ignores 'WritingMode')
    }
}
