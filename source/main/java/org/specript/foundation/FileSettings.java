/* ======================================---------------------------

  --+ Specript Foundation Library v3.0 +--

  Copyright (c) 2007-2017, Koichi Tanaka (@Tanaka9230)

    ---------------------------====================================== */

package org.specript.foundation;

import static org.specript.foundation.Ops.mandatory;
import static org.specript.foundation.Ops.optional;
import static java.nio.file.StandardOpenOption.APPEND;
import static java.nio.file.StandardOpenOption.CREATE;
import static java.nio.file.StandardOpenOption.TRUNCATE_EXISTING;
import static java.nio.file.StandardOpenOption.WRITE;
import java.io.Serializable;
import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;
import java.nio.file.OpenOption;
import java.nio.file.Path;

public class FileSettings implements Serializable {
    final java.io.File File;
    final Path Path;
    final Charset Charset;
    final boolean appending;
    final OpenOption[] OpenOptions;

    public FileSettings(final String aFileNamePath, final Charset itsCharset, final boolean appending) {
        this(new java.io.File(mandatory(aFileNamePath)), itsCharset, appending);
    }

    public FileSettings(final java.io.File aFile, final Charset itsCharset, final boolean appending) {
        File = mandatory(aFile);
        Path = this.File.toPath();
        Charset = optional(itsCharset).orElse(StandardCharsets.UTF_8);
        this.appending = appending;
        OpenOptions = theOpenOptions(appending);
    }

    private static OpenOption[] theOpenOptions(final boolean appending) {
        return new OpenOption[] {
            WRITE, CREATE,
            appending ? APPEND : TRUNCATE_EXISTING
        };
    }
}
