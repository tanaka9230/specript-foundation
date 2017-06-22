/* ======================================---------------------------

  --+ Specript Foundation Library v3.0 +--

  Copyright (c) 2007-2017, Koichi Tanaka (@Tanaka9230)

    ---------------------------====================================== */

package org.specript.foundation;

import static org.specript.foundation.Ops.mandatory;
import static org.specript.foundation.Ops.optional;
import java.io.File;
import java.io.Serializable;
import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;
import java.nio.file.Path;

public class ReadFileSettings implements Serializable {
    private final File thisFile;
    private final Path thisPath;
    private final Charset thisCharset;

    public ReadFileSettings(final String aFileNamePath, final Charset itsCharset) throws IllegalArgumentException {
        this(new File(mandatory(aFileNamePath)), itsCharset);
    }

    public ReadFileSettings(final File aFile, final Charset itsCharset) throws IllegalArgumentException {
        thisFile = mandatory(aFile);
        thisPath = thisFile.toPath(); // `toPath` may throw java.nio.file.InvalidPathException which is a subclass of IllegalArgumentException
        thisCharset = optional(itsCharset).orElse(StandardCharsets.UTF_8);
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
}
