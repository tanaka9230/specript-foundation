/* ======================================---------------------------

  --+ Specript HTTP Utilities v3.0 +--

  Copyright (c) 2007-2017, Koichi Tanaka (@Tanaka9230)

    ---------------------------====================================== */

package org.specript.http;

import static org.specript.foundation.Ops.exists;
import static org.specript.foundation.Ops.mandatory;
import static org.specript.foundation.Ops.optional;
import org.apache.http.client.utils.URIBuilder;
import java.net.URI;
import java.net.URISyntaxException;
import java.nio.charset.Charset;
import java.util.Map;
import java.util.Map.Entry;

public class UriOps {
    /**
     * to prevent to be used.
     */
    private UriOps() {
    }

    /**********************************************************************
     *
     * builds the URI(URL).
     *
     * <p>
     * To build the {@link URI} object from the parts of the URL.
     * </p>
     * <p>
     * This is the wrapper of
     * {@link org.apache.http.client.utils.URIBuilder}.
     * </p>
     *
     * @param aRequestUriBase
     *            the host and the port followed by "SCRIPT_NAME"; shoud
     *            NOT end with "/"; e.g.)
     *            {@code "http://example.com:8000/MyService"}
     * @param aPathInfo
     *            the "PATH_INFO"; shoud start with "/", can be null;
     *            e.g.) {@code "/customers"}
     * @param someQueryParameters
     *            the query component's parameters for the
     *            "QUERY_STRING"; can be null
     * @param itsCharset
     *            the charset to be used for the URL encoding of
     *            "QUERY_STRING"; can be null when
     *            {@code someQueryParameters} does not exist
     *
     * @throws IllegalArgumentException
     *             couldn't build the valid {@link URI} from the
     *             specified arguments, some preconditions were not
     *             satisfied
     *
     **********************************************************************/
    public static URI with(final String aRequestUriBase, final String aPathInfo, final Map<String, String> someQueryParameters, final Charset itsCharset) throws IllegalArgumentException {
        try {
            final URIBuilder tmp = new URIBuilder(mandatory(aRequestUriBase) + optional(aPathInfo).orElse("")) {
                {
                    if (exists(itsCharset)) {
                        setCharset(itsCharset);
                    }
                    if (exists(someQueryParameters)) {
                        // TODO to support repeated keys
                        for (final Entry<String, String> it : someQueryParameters.entrySet()) {
                            setParameter(it.getKey(), it.getValue());
                        }
                    }
                }
            };
            final URI ret = tmp.build();
            return ret;
        } catch (final URISyntaxException e) {
            throw new IllegalArgumentException(e);
        }
    }
}
