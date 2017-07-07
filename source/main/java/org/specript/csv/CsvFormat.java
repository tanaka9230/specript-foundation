/* ======================================---------------------------

  --+ Specript CSV Utilities v3.0 +--

  Copyright (c) 2007-2017, Koichi Tanaka (@Tanaka9230)

    ---------------------------====================================== */

package org.specript.csv;

import static org.specript.foundation.Ops.mandatory;
import org.apache.commons.csv.CSVFormat;
import java.io.Serializable;

public class CsvFormat implements Serializable {
    private final char thisDelimiter;
    private final String thisLineSeparator;
    private final String thisNullString;
    private final boolean trimming;
    private final boolean handlingHeader;
    private final String[] thisFieldNames;
    private final CSVFormat thisFormat;

    public CsvFormat(final char aDelimiter, final String aLineSeparator, final String aNullString, final boolean trimming, final boolean handlingHeader, final String[] someFieldNames) throws IllegalArgumentException {
        thisDelimiter = aDelimiter;
        thisLineSeparator = mandatory(aLineSeparator, true);
        thisNullString = aNullString != null ? aNullString : "";
        this.trimming = trimming;
        this.handlingHeader = handlingHeader;
        thisFieldNames = mandatory(someFieldNames);
        thisFormat = theFormat(thisDelimiter, thisLineSeparator, thisNullString, this.trimming, thisFieldNames);
    }

    private static CSVFormat theFormat(final char aDelimiter, final String aLineSeparator, final String aNullString, final boolean trimming, final String[] someFieldNames) throws IllegalArgumentException {
        return CSVFormat.newFormat(aDelimiter)
                .withRecordSeparator(aLineSeparator)
                .withIgnoreSurroundingSpaces(trimming).withTrim(trimming)
                .withNullString(aNullString);
    }

    char Delimiter() {
        return thisDelimiter;
    }

    String LineSeparator() {
        return thisLineSeparator;
    }

    boolean trimming() {
        return this.trimming;
    }

    boolean handlingHeader() {
        return this.handlingHeader;
    }

    String[] FieldNames() {
        return thisFieldNames;
    }

    CSVFormat Format() {
        return thisFormat;
    }
}
