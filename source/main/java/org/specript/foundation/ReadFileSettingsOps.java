/* ======================================---------------------------

  --+ Specript Foundation Library v3.0 +--

  Copyright (c) 2007-2017, Koichi Tanaka (@Tanaka9230)

    ---------------------------====================================== */

package org.specript.foundation;

import static org.specript.foundation.Ops.mandatory;
import static org.specript.foundation.Ops.optional;
import org.specript.foundation.exceptions.InvalidSettingsException;
import java.io.File;
import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;
import java.util.Properties;

/**********************************************************************
 *
 * Supplementary Operations for {@link ReadFileSettings}.
 *
 **********************************************************************/
public class ReadFileSettingsOps {
    /*
     * to prevent to be called
     */
    private ReadFileSettingsOps() {
    }

    public static ReadFileSettings with(final String aFilePathName) throws IllegalArgumentException {
        return new ReadFileSettings(aFilePathName, null);
    }

    public static ReadFileSettings with(final String aFilePathName, final Charset itsCharset) throws IllegalArgumentException {
        return new ReadFileSettings(aFilePathName, itsCharset);
    }

    public static ReadFileSettings with(final File aFile) throws IllegalArgumentException {
        return new ReadFileSettings(aFile, null);
    }

    public static ReadFileSettings with(final File aFile, final Charset itsCharset) throws IllegalArgumentException {
        return new ReadFileSettings(aFile, itsCharset);
    }

    /**********************************************************************
     *
     * obtains the {@link ReadFileSettings}, that comes from the
     * {@link Properties}.
     *
     * @param someProperties
     *            source {@link Properties}
     *
     * @return obtained {@link ReadFileSettings}
     *
     * @throws InvalidSettingsException
     *             content of the {@link Properties} was immature to be
     *             read as a {@link ReadFileSettings}; some mandatory
     *             setting is missing, format of the entry is invalid,
     *             etc.
     *
     **********************************************************************/
    public static ReadFileSettings from(final Properties aFileSettingsProperties) throws InvalidSettingsException, IllegalArgumentException {
        return from(null, aFileSettingsProperties);
    }

    public static ReadFileSettings from(final File aFile, final Properties aFileSettingsProperties) throws InvalidSettingsException, IllegalArgumentException {
        mandatory(aFileSettingsProperties);
        try {
            final File theFile =
                    optional(aFile)
                            .orElseGet(() ->
                                    optional(aFileSettingsProperties.getProperty(FILE_PATH_SETTING_NAME))
                                            .map(File::new)
                                            .orElseGet(() ->
                                                    optional(aFileSettingsProperties.getProperty(RESOURCE_NAME_PATH_SETTING_NAME))
                                                            .map(it -> Resource.forName(it).asFile())
                                                            .orElseThrow(() ->
                                                                    new InvalidSettingsException("neither '" + FILE_PATH_SETTING_NAME + "' nor '" + RESOURCE_NAME_PATH_SETTING_NAME + "' were set")
                                                            )
                                            )
                            );
            final Charset theCharset =
                    optional(aFileSettingsProperties.getProperty(ENCODING_SETTING_NAME))
                            .map(Charset::forName)
                            .orElse(StandardCharsets.UTF_8);
            return new ReadFileSettings(theFile, theCharset);
        } catch (final InvalidSettingsException e) {
            throw e;
        } catch (final RuntimeException e) {
            throw new InvalidSettingsException(e);
        }
    }

    private static final String FILE_PATH_SETTING_NAME = "FilePath";
    private static final String RESOURCE_NAME_PATH_SETTING_NAME = "ResourceNamePath";
    private static final String ENCODING_SETTING_NAME = "Encoding";
}
