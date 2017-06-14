
package org.specript.csv;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.fail;
import org.specript.foundation.Resource;
import org.specript.foundation.exceptions.InvalidSettingsException;
import org.junit.Test;
import java.util.Arrays;

public class CsvFormatOpsTest {
    @Test
    public void case_fromProperties_Normal_then_Succeeded() {
        final CsvFormat theCsvFormat = CsvFormatOps.fromProperties(Resource.forName("org/specript/csv/CsvFormatOpsTest1.properties").asProperties());
        assertEquals('\t', theCsvFormat.Delimiter());
        assertEquals("\r\n", theCsvFormat.LineSeparator());
        assertEquals(Boolean.TRUE, Boolean.valueOf(theCsvFormat.trimming()));
        assertEquals(Boolean.TRUE, Boolean.valueOf(theCsvFormat.handlingHeader()));
        assertEquals(Arrays.asList("Foo", "Bar", "ほげ"), Arrays.asList(theCsvFormat.FieldNames()));
        assertEquals('\t', theCsvFormat.Format().getDelimiter());
        assertEquals("\r\n", theCsvFormat.Format().getRecordSeparator());
        assertEquals(Boolean.TRUE, Boolean.valueOf(theCsvFormat.Format().getIgnoreSurroundingSpaces()));
        assertEquals(Boolean.TRUE, Boolean.valueOf(theCsvFormat.Format().getTrim()));
        assertEquals(Boolean.FALSE, Boolean.valueOf(theCsvFormat.Format().getSkipHeaderRecord()));
        assertEquals("", theCsvFormat.Format().getNullString());
        assertEquals(Arrays.asList("Foo", "Bar", "ほげ"), Arrays.asList(theCsvFormat.Format().getHeader()));
    }

    @Test
    public void case_fromProperties_SomeDefaultValuesEmployed_then_Succeeded() {
        final CsvFormat theCsvFormat = CsvFormatOps.fromProperties(Resource.forName("org/specript/csv/CsvFormatOpsTest2.properties").asProperties());
        assertEquals(',', theCsvFormat.Delimiter());
        assertEquals("\n", theCsvFormat.LineSeparator());
        assertEquals(Boolean.FALSE, Boolean.valueOf(theCsvFormat.trimming()));
        assertEquals(Boolean.FALSE, Boolean.valueOf(theCsvFormat.handlingHeader()));
        assertEquals(Arrays.asList("Foo", "Bar", "ほげ"), Arrays.asList(theCsvFormat.FieldNames()));
        assertEquals(',', theCsvFormat.Format().getDelimiter());
        assertEquals("\n", theCsvFormat.Format().getRecordSeparator());
        assertEquals(Boolean.FALSE, Boolean.valueOf(theCsvFormat.Format().getIgnoreSurroundingSpaces()));
        assertEquals(Boolean.FALSE, Boolean.valueOf(theCsvFormat.Format().getTrim()));
        assertEquals(Boolean.FALSE, Boolean.valueOf(theCsvFormat.Format().getSkipHeaderRecord()));
        assertEquals("", theCsvFormat.Format().getNullString());
        assertEquals(Arrays.asList("Foo", "Bar", "ほげ"), Arrays.asList(theCsvFormat.Format().getHeader()));
    }

    @Test
    public void case_fromProperties_MandatoryLineSparatorNotSet_then_Failed() {
        try {
            CsvFormatOps.fromProperties(Resource.forName("org/specript/csv/CsvFormatOpsTest3.properties").asProperties());
            fail();
        } catch (final InvalidSettingsException e) {
            e.printStackTrace();
            assertEquals("org.specript.foundation.exceptions.InvalidSettingsException: java.lang.IllegalArgumentException: the string `arg` was null or empty", e.toString());
        }
    }

    @Test
    public void case_fromProperties_MandatoryFieldsNotSet_then_Failed() {
        try {
            CsvFormatOps.fromProperties(Resource.forName("org/specript/csv/CsvFormatOpsTest4.properties").asProperties());
            fail();
        } catch (final InvalidSettingsException e) {
            e.printStackTrace();
            assertEquals("org.specript.foundation.exceptions.InvalidSettingsException: java.lang.IllegalArgumentException: the array `arg` was null or empty", e.toString());
        }
    }

    @Test
    public void case_fromProperties_MandatoryFieldsExistsButEmpty_then_Failed() {
        try {
            CsvFormatOps.fromProperties(Resource.forName("org/specript/csv/CsvFormatOpsTest5.properties").asProperties());
            fail(); // TODO this test ends in success
        } catch (final InvalidSettingsException e) {
            e.printStackTrace();
            assertEquals("org.specript.foundation.exceptions.InvalidSettingsException: java.lang.IllegalArgumentException: the array `arg` was null or empty", e.toString());
        }
    }

    @Test
    public void case_fromProperties_TwoCharsDelimiter_then_Failed() {
        try {
            CsvFormatOps.fromProperties(Resource.forName("org/specript/csv/CsvFormatOpsTest6.properties").asProperties());
            fail();
        } catch (final InvalidSettingsException e) {
            e.printStackTrace();
            assertEquals("org.specript.foundation.exceptions.InvalidSettingsException: 'Delimiter' must be set only one char", e.toString());
        }
    }

    @Test
    public void case_fromProperties_OnlyOneField_then_Succeeded() {
        final CsvFormat theCsvFormat = CsvFormatOps.fromProperties(Resource.forName("org/specript/csv/CsvFormatOpsTest7.properties").asProperties());
        assertEquals(Arrays.asList("piyo"), Arrays.asList(theCsvFormat.FieldNames()));
        assertEquals(Arrays.asList("piyo"), Arrays.asList(theCsvFormat.Format().getHeader()));
    }
}
