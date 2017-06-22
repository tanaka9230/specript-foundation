/* ======================================---------------------------

  --+ Specript Foundation Library v3.0 +--

  Copyright (c) 2007-2017, Koichi Tanaka (@Tanaka9230)

    ---------------------------====================================== */

package org.specript.foundation;

import static org.specript.foundation.Ops.exists;
import static org.specript.foundation.Ops.mandatory;
import static org.specript.foundation.Ops.optional;
import static java.nio.file.StandardOpenOption.APPEND;
import static java.nio.file.StandardOpenOption.CREATE;
import static java.nio.file.StandardOpenOption.CREATE_NEW;
import static java.nio.file.StandardOpenOption.TRUNCATE_EXISTING;
import static java.nio.file.StandardOpenOption.WRITE;
import java.io.File;
import java.io.Serializable;
import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;
import java.nio.file.OpenOption;
import java.nio.file.Path;

public class WriteFileSettings implements Serializable {
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

    private final File thisFile;
    private final Path thisPath;
    private final Charset thisCharset;
    private final WritingMode thisWritingMode;
    private final OpenOption[] thisOpenOptions;

    public WriteFileSettings(final String aFileNamePath, final Charset itsCharset, final WritingMode itsWritingMode) throws IllegalArgumentException {
        this(new File(mandatory(aFileNamePath)), itsCharset, itsWritingMode);
    }

    public WriteFileSettings(final File aFile, final Charset itsCharset, final WritingMode itsWritingMode) throws IllegalArgumentException {
        thisFile = mandatory(aFile);
        thisPath = thisFile.toPath(); // `toPath` may throw java.nio.file.InvalidPathException which is a subclass of IllegalArgumentException
        thisCharset = optional(itsCharset).orElse(StandardCharsets.UTF_8);
        thisWritingMode = optional(itsWritingMode).orElse(WritingMode.DEFAULT);
        thisOpenOptions = theOpenOptions(thisWritingMode);
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

    File File() {
        return thisFile;
    };

    Path Path() {
        return thisPath;
    };

    Charset Charset() {
        return thisCharset;
    };

    WritingMode WritingMode() {
        return thisWritingMode;
    };

    OpenOption[] OpenOptions() {
        return thisOpenOptions;
    }
}
