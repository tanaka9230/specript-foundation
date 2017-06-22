/* ======================================---------------------------

  --+ Specript Foundation Library v3.0 +--

  Copyright (c) 2007-2017, Koichi Tanaka (@Tanaka9230)

    ---------------------------====================================== */

package org.specript.foundation;

import static org.specript.foundation.Ops.mandatory;
import java.io.BufferedReader;
import java.io.IOException;
import java.nio.file.Files;
import java.util.List;

public class ReadFile {
    public static ReadFile with(final ReadFileSettings someFileSettings) throws IllegalArgumentException {
        return new ReadFile(someFileSettings);
    }

    //
    //
    //

    private final ReadFileSettings thisFileSettings;

    private ReadFile(final ReadFileSettings someFileSettings) throws IllegalArgumentException {
        thisFileSettings = mandatory(someFileSettings);
    }

    public BufferedReader asReader() throws IOException {
        try {
            return Files.newBufferedReader(thisFileSettings.Path(), thisFileSettings.Charset());
        } catch (final IOException e) {
            throw e;
        } catch (final RuntimeException e) {
            throw new IOException(e);
        }
    }

    public List<String> asLines() throws IOException {
        try {
            return Files.readAllLines(thisFileSettings.Path(), thisFileSettings.Charset());
        } catch (final IOException e) {
            throw e;
        } catch (final RuntimeException e) {
            throw new IOException(e);
        }
    }
}
