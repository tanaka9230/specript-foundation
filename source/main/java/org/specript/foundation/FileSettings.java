/* ======================================---------------------------

  --+ Specript Foundation Library v3.0 +--

  Copyright (c) 2007-2017, Koichi Tanaka (@Tanaka9230)

    ---------------------------====================================== */

package org.specript.foundation;

import static org.specript.foundation.FileSettings.WritingMode.DEFAULT;
import static org.specript.foundation.Ops.exists;
import static org.specript.foundation.Ops.mandatory;
import static org.specript.foundation.Ops.optional;
import static java.nio.file.StandardOpenOption.APPEND;
import static java.nio.file.StandardOpenOption.CREATE;
import static java.nio.file.StandardOpenOption.CREATE_NEW;
import static java.nio.file.StandardOpenOption.TRUNCATE_EXISTING;
import static java.nio.file.StandardOpenOption.WRITE;
import java.io.Serializable;
import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;
import java.nio.file.OpenOption;
import java.nio.file.Path;

public class FileSettings implements Serializable {
    public static enum WritingMode {
        /** error when already exists **/
        DEFAULT,
        /** to overwrite when already exists **/
        OVERWRITING,
        /** to append when already exists **/
        APPENDING;

        //
        //
        //

        public static WritingMode from(final String arg) throws IllegalArgumentException {
            return exists(arg) ? valueOf(arg.toUpperCase()) : null;
        }
    }

    //
    //
    //

    final java.io.File File;
    final Path Path;
    final Charset Charset;
    final WritingMode WritingMode;
    final OpenOption[] OpenOptions;

    /**********************************************************************
     *
     * constructs the {@code FileSettings}.
     *
     * @throws IllegalArgumentException
     *             {@code aFileNamePath} is null or empty, or doesn't
     *             represent the valid file path
     *
     **********************************************************************/
    public FileSettings(final String aFileNamePath, final Charset itsCharset, final WritingMode itsWritingMode) throws IllegalArgumentException {
        this(new java.io.File(mandatory(aFileNamePath)), itsCharset, itsWritingMode);
    }

    /**********************************************************************
     *
     * constructs the {@code FileSettings}.
     *
     * @throws IllegalArgumentException
     *             {@code aFile} is null or doesn't represent the valid
     *             file path
     *
     **********************************************************************/
    public FileSettings(final java.io.File aFile, final Charset itsCharset, final WritingMode itsWritingMode) throws IllegalArgumentException {
        File = mandatory(aFile);
        Path = this.File.toPath();
        Charset = optional(itsCharset).orElse(StandardCharsets.UTF_8);
        WritingMode = optional(itsWritingMode).orElse(DEFAULT);
        OpenOptions = theOpenOptions(this.WritingMode);
    }

    private static OpenOption[] theOpenOptions(final WritingMode aWritingMode) {
        switch (aWritingMode) {
            case OVERWRITING:
                return new OpenOption[] {
                    WRITE, CREATE, TRUNCATE_EXISTING
                };
            case APPENDING:
                return new OpenOption[] {
                    WRITE, CREATE, APPEND
                };
            default:
                return new OpenOption[] {
                    WRITE, CREATE_NEW
                };
        }
    }
}
