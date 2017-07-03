/* ======================================---------------------------

  --+ Specript Foundation Library v3.0 +--

  Copyright (c) 2007-2017, Koichi Tanaka (@Tanaka9230)

    ---------------------------====================================== */

package org.specript.foundation;

import static org.specript.foundation.Ops.mandatory;
import static org.specript.foundation.Ops.optional;
import org.specript.foundation.WriteFileSettings.WritingMode;
import org.specript.foundation.exceptions.InvalidSettingsException;
import java.io.File;
import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;
import java.util.Properties;

/**********************************************************************
 *
 * Supplementary Operations for {@link WriteFileSettings}.
 *
 **********************************************************************/
public class WriteFileSettingsOps {
    /*
     * to prevent to be called
     */
    private WriteFileSettingsOps() {
    }

    public static WriteFileSettings with(final String aFilePathName) throws IllegalArgumentException {
        return new WriteFileSettings(aFilePathName, null, null);
    }

    public static WriteFileSettings with(final String aFilePathName, final Charset itsCharset) throws IllegalArgumentException {
        return new WriteFileSettings(aFilePathName, itsCharset, null);
    }

    public static WriteFileSettings with(final String aFilePathName, final WritingMode itsWritingMode) throws IllegalArgumentException {
        return new WriteFileSettings(aFilePathName, null, itsWritingMode);
    }

    public static WriteFileSettings with(final String aFilePathName, final Charset itsCharset, final WritingMode itsWritingMode) throws IllegalArgumentException {
        return new WriteFileSettings(aFilePathName, itsCharset, itsWritingMode);
    }

    public static WriteFileSettings with(final File aFile) throws IllegalArgumentException {
        return new WriteFileSettings(aFile, null, null);
    }

    public static WriteFileSettings with(final File aFile, final Charset itsCharset) throws IllegalArgumentException {
        return new WriteFileSettings(aFile, itsCharset, null);
    }

    public static WriteFileSettings with(final File aFile, final WritingMode itsWritingMode) throws IllegalArgumentException {
        return new WriteFileSettings(aFile, null, itsWritingMode);
    }

    public static WriteFileSettings with(final File aFile, final Charset itsCharset, final WritingMode itsWritingMode) throws IllegalArgumentException {
        return new WriteFileSettings(aFile, itsCharset, itsWritingMode);
    }

    /**********************************************************************
     *
     * obtains the {@link WriteFileSettings}, that comes from the
     * {@link Properties}.
     *
     * @param someProperties
     *            source {@link Properties}
     * 
     * @return obtained {@link WriteFileSettings}
     * 
     * @throws InvalidSettingsException
     *             content of the {@link Properties} was immature to be
     *             read as a {@link WriteFileSettings}; some mandatory
     *             setting is missing, format of the entry is invalid,
     *             etc.
     *
     **********************************************************************/
    public static WriteFileSettings from(final Properties aFileSettingsProperties) throws InvalidSettingsException, IllegalArgumentException {
        return from(null, aFileSettingsProperties);
    }

    public static WriteFileSettings from(File aFile, final Properties aFileSettingsProperties) throws InvalidSettingsException, IllegalArgumentException {
        mandatory(aFileSettingsProperties);
        try {
            final File theFile =
                    optional(aFile)
                            .orElseGet(() ->
                                    optional(aFileSettingsProperties.getProperty(FILE_PATH_SETTING_NAME))
                                            .map(File::new)
                                            .orElseThrow(() ->
                                                    new InvalidSettingsException("'" + FILE_PATH_SETTING_NAME + "' was not set")
                                            )
                            );
            final Charset theCharset =
                    optional(aFileSettingsProperties.getProperty(ENCODING_SETTING_NAME))
                            .map(Charset::forName)
                            .orElse(StandardCharsets.UTF_8);
            final WritingMode theWritingMode =
                    optional(aFileSettingsProperties.getProperty(WRITING_MODE_SETTING_NAME))
                            .map(WritingMode::from)
                            .orElse(WritingMode.DEFAULT);
            return new WriteFileSettings(theFile, theCharset, theWritingMode);
        } catch (final InvalidSettingsException e) {
            throw e;
        } catch (final RuntimeException e) {
            throw new InvalidSettingsException(e);
        }
    }

    private static final String FILE_PATH_SETTING_NAME = "FilePath";
    private static final String ENCODING_SETTING_NAME = "Encoding";
    private static final String WRITING_MODE_SETTING_NAME = "WritingMode";
}
