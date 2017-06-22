/* ======================================---------------------------

  --+ Specript Foundation Library v3.0 +--

  Copyright (c) 2007-2017, Koichi Tanaka (@Tanaka9230)

    ---------------------------====================================== */

package org.specript.foundation;

import static org.specript.foundation.Ops.mandatory;
import java.io.BufferedWriter;
import java.io.IOException;
import java.nio.file.Files;

public class WriteFile {
    public static WriteFile with(final WriteFileSettings someFileSettings) throws IllegalArgumentException {
        return new WriteFile(someFileSettings);
    }

    //
    //
    //

    private final WriteFileSettings thisFileSettings;

    private WriteFile(final WriteFileSettings someFileSettings) throws IllegalArgumentException {
        thisFileSettings = mandatory(someFileSettings);
    }

    public BufferedWriter asWriter() throws IOException {
        try {
            Files.createDirectories(thisFileSettings.Path().getParent());
            return Files.newBufferedWriter(thisFileSettings.Path(), thisFileSettings.Charset(), thisFileSettings.OpenOptions());
        } catch (final IOException e) {
            throw e;
        } catch (final RuntimeException e) {
            throw new IOException(e);
        }
    }
}
