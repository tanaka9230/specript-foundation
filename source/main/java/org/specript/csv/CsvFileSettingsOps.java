/* ======================================---------------------------

  --+ Specript CSV Library v3.0 +--

  Copyright (c) 2007-2017, Koichi Tanaka (@Tanaka9230)

    ---------------------------====================================== */

package org.specript.csv;

import static org.specript.foundation.Ops.mandatory;
import org.specript.foundation.FileSettingsOps;
import org.specript.foundation.exceptions.InvalidSettingsException;
import java.util.Properties;

/**********************************************************************
 *
 * Supplementary Operations for {@link CsvFileSettings}.
 *
 **********************************************************************/
public class CsvFileSettingsOps {
    /*
     * to prevent to be called
     */
    private CsvFileSettingsOps() {
    }

    /**********************************************************************
     *
     * obtains the {@link CsvFileSettings}, that comes from the
     * {@link Properties}.
     *
     * @param someProperties
     *            source {@link Properties}
     *
     * @return obtained {@link CsvFileSettings}
     *
     * @throws InvalidSettingsException
     *             content of the {@link Properties} was immature to be
     *             read as a {@link CsvFileSettings}; some mandatory
     *             setting is missing, format of the entry is invalid,
     *             etc.
     *
     **********************************************************************/
    public static CsvFileSettings fromProperties(final Properties someProperties) throws InvalidSettingsException, IllegalArgumentException {
        mandatory(someProperties);
        try {
            return new CsvFileSettings(
                    FileSettingsOps.fromProperties(someProperties),
                    CsvFormatOps.fromProperties(someProperties) //
            );
        } catch (final InvalidSettingsException e) {
            throw e;
        } catch (final RuntimeException e) {
            throw new InvalidSettingsException(e);
        }
    }
}
