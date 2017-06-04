/* ======================================---------------------------

  --+ Specript Foundation Library v3.0 +--

  Copyright (c) 2007-2017, Koichi Tanaka (@Tanaka9230)

    ---------------------------====================================== */

package org.specript.foundation;

import static org.specript.foundation.Ops.mandatory;
import static org.specript.foundation.Ops.optional;
import org.specript.foundation.FileSettings.WritingMode;
import org.specript.foundation.exceptions.InvalidSettingsException;
import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;
import java.util.Properties;

public class FileSettingsOps {
    /*
     * to prevent to be called
     */
    private FileSettingsOps() {
    }

    public static FileSettings with(final String aFilePathName) throws IllegalArgumentException {
        return new FileSettings(aFilePathName, null, null);
    }

    public static FileSettings with(final String aFilePathName, final Charset itsCharset) throws IllegalArgumentException {
        return new FileSettings(aFilePathName, itsCharset, null);
    }

    public static FileSettings with(final String aFilePathName, final WritingMode itsWritingMode) throws IllegalArgumentException {
        return new FileSettings(aFilePathName, null, itsWritingMode);
    }

    public static FileSettings with(final String aFilePathName, final Charset itsCharset, final WritingMode itsWritingMode) throws IllegalArgumentException {
        return new FileSettings(aFilePathName, itsCharset, itsWritingMode);
    }

    public static FileSettings fromProperties(final Properties aFileSettingsProperties) throws InvalidSettingsException, IllegalArgumentException {
        mandatory(aFileSettingsProperties);
        try {
            final java.io.File theFile =
                    optional(aFileSettingsProperties.getProperty(FILE_PATH_SETTING_NAME))
                            .map(java.io.File::new)
                            .orElseGet(() ->
                                    optional(aFileSettingsProperties.getProperty(RESOURCE_NAME_PATH_SETTING_NAME))
                                            .map(it -> Resource.forName(it).asFile())
                                            .orElseThrow(() ->
                                                    new InvalidSettingsException("neither '" + FILE_PATH_SETTING_NAME + "' nor '" + RESOURCE_NAME_PATH_SETTING_NAME + "' were set")
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
            return new FileSettings(theFile, theCharset, theWritingMode);
        } catch (final InvalidSettingsException e) {
            throw e;
        } catch (final RuntimeException e) {
            throw new InvalidSettingsException(e);
        }
    }

    private static final String FILE_PATH_SETTING_NAME = "file_path";
    private static final String RESOURCE_NAME_PATH_SETTING_NAME = "resource_name_path";
    private static final String ENCODING_SETTING_NAME = "encoding";
    private static final String WRITING_MODE_SETTING_NAME = "writing_mode";
}
