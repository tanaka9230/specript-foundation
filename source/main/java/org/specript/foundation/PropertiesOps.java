/* ======================================---------------------------

  --+ Specript Foundation Library v3.0 +--

  Copyright (c) 2007-2017, Koichi Tanaka (@Tanaka9230)

    ---------------------------====================================== */

package org.specript.foundation;

import static org.specript.foundation.Ops.mandatory;
import java.io.IOException;
import java.io.Reader;
import java.util.Properties;

public class PropertiesOps {
    /*
     * to prevent to be called.
     */
    private PropertiesOps() {
    }

    public static Properties from(final Reader aReader) throws IOException, IllegalArgumentException {
        final Properties ret = new Properties();
        try (final Reader theReader = mandatory(aReader)) { // the Reader is to be closed
            ret.load(theReader);
        } catch (final RuntimeException e) {
            throw new IOException(e);
        }
        return ret;
    }
}
