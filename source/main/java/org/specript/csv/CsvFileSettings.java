/* ======================================---------------------------

  --+ Specript CSV Library v3.0 +--

  Copyright (c) 2007-2017, Koichi Tanaka (@Tanaka9230)

    ---------------------------====================================== */

package org.specript.csv;

import static org.specript.foundation.Ops.mandatory;
import org.specript.foundation.FileSettings;
import java.io.Serializable;

public class CsvFileSettings implements Serializable {
    private final FileSettings thisFileSettings;
    private final CsvFormat thisCsvFormat;

    public CsvFileSettings(final FileSettings someFileSettings, final CsvFormat aCsvFormat) throws IllegalArgumentException {
        thisFileSettings = mandatory(someFileSettings);
        thisCsvFormat = mandatory(aCsvFormat);
    }

    public FileSettings FileSettings() {
        return thisFileSettings;
    }

    public CsvFormat CsvFormat() {
        return thisCsvFormat;
    }
}
