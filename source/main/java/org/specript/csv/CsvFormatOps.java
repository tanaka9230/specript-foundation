/* ======================================---------------------------

  --+ Specript CSV Library v3.0 +--

  Copyright (c) 2007-2017, Koichi Tanaka (@Tanaka9230)

    ---------------------------====================================== */

package org.specript.csv;

import static org.specript.foundation.Ops.mandatory;
import static org.specript.foundation.Ops.optional;
import org.specript.foundation.BooleanOps;
import org.specript.foundation.StringOps;
import org.specript.foundation.exceptions.InvalidSettingsException;
import java.util.Properties;

/**********************************************************************
 *
 * Supplementary Operations for {@link CsvFormat}.
 *
 **********************************************************************/
public class CsvFormatOps {
    /*
     * to prevent to be called
     */
    private CsvFormatOps() {
    }

    /**********************************************************************
     *
     * obtains the {@link CsvFormat}, that comes from the
     * {@link Properties}.
     *
     * @param someProperties
     *            source {@link Properties}
     * 
     * @return obtained {@link CsvFormat}
     * 
     * @throws InvalidSettingsException
     *             content of the {@link Properties} was immature to be
     *             read as a {@link CsvFormat}; some mandatory setting
     *             is missing, format of the entry is invalid, etc.
     *
     **********************************************************************/
    public static CsvFormat fromProperties(final Properties someProperties) throws InvalidSettingsException, IllegalArgumentException {
        mandatory(someProperties);
        try {
            final char theDelimiter =
                    optional(someProperties.getProperty(DELIMITER_SETTING_NAME), true)
                            .map(it -> Character.valueOf(theDelimiter(it)))
                            .orElse(Character.valueOf(DEFAULT_DELIMITER))
                            .charValue();
            final String theLineSeparator =
                    mandatory(someProperties.getProperty(LINE_SEPARATOR_SETTING_NAME), true);
            final boolean trimming =
                    optional(someProperties.getProperty(TRIMMING_SETTING_NAME))
                            .map(BooleanOps::from)
                            .orElse(Boolean.FALSE)
                            .booleanValue();
            final boolean handlingHeader =
                    optional(someProperties.getProperty(HANDLING_HEADER_SETTING_NAME))
                            .map(BooleanOps::from)
                            .orElse(Boolean.FALSE)
                            .booleanValue();
            final String[] theFieldNames =
                    mandatory(StringOps.split(someProperties.getProperty(FIELDS_SETTING_NAME), "\\,"));
            return new CsvFormat(theDelimiter, theLineSeparator, trimming, handlingHeader, theFieldNames);
        } catch (final InvalidSettingsException e) {
            throw e;
        } catch (final RuntimeException e) {
            throw new InvalidSettingsException(e);
        }
    }

    private static char theDelimiter(final String arg) throws InvalidSettingsException {
        if (arg != null && arg.length() == 1) {
            return arg.charAt(0);
        } else {
            throw new InvalidSettingsException("'" + DELIMITER_SETTING_NAME + "' must be set only one char");
        }
    }

    private static final char DEFAULT_DELIMITER = ',';

    private static final String DELIMITER_SETTING_NAME = "Delimiter";
    private static final String LINE_SEPARATOR_SETTING_NAME = "LineSeparator";
    private static final String TRIMMING_SETTING_NAME = "trimming";
    private static final String HANDLING_HEADER_SETTING_NAME = "handlingHeader";
    private static final String FIELDS_SETTING_NAME = "Fields";
}
