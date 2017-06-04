/* ======================================---------------------------

  --+ Specript Foundation Library v3.0 +--

  Copyright (c) 2007-2017, Koichi Tanaka (@Tanaka9230)

    ---------------------------====================================== */

package org.specript.foundation;

import static org.specript.foundation.Ops.mandatory;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.util.List;

public class File {
    public static File with(final FileSettings aFileSetting) throws IllegalArgumentException {
        return new File(aFileSetting);
    }

    //
    //
    //

    private final FileSettings thisFileSettings;

    private File(final FileSettings aFileSetting) throws IllegalArgumentException {
        thisFileSettings = mandatory(aFileSetting);
    }

    public BufferedReader asReader() throws IOException {
        try {
            return Files.newBufferedReader(thisFileSettings.Path, thisFileSettings.Charset);
        } catch (final IOException e) {
            throw e;
        } catch (final RuntimeException e) {
            throw new IOException(e);
        }
    }

    public List<String> asLines() throws IOException {
        try {
            return Files.readAllLines(thisFileSettings.Path, thisFileSettings.Charset);
        } catch (final IOException e) {
            throw e;
        } catch (final RuntimeException e) {
            throw new IOException(e);
        }
    }

    public BufferedWriter asWriter() throws IOException {
        try {
            Files.createDirectories(thisFileSettings.Path.getParent());
            return Files.newBufferedWriter(thisFileSettings.Path, thisFileSettings.Charset, thisFileSettings.OpenOptions);
        } catch (final IOException e) {
            throw e;
        } catch (final RuntimeException e) {
            throw new IOException(e);
        }
    }
}
