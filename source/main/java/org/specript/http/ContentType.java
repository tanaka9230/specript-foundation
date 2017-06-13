/* ======================================---------------------------

  --+ Specript HTTP Library v3.0 +--

  Copyright (c) 2007-2017, Koichi Tanaka (@Tanaka9230)

    ---------------------------====================================== */

package org.specript.http;

import static org.specript.foundation.Ops.exists;
import static org.specript.foundation.Ops.mandatory;
import static org.specript.foundation.Ops.optional;
import java.nio.charset.Charset;
import java.util.Collections;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.stream.Collectors;

public class ContentType {
    private final String thisMediaType;
    private final Map<String, String> thisParameters;
    private final Charset thisCharset;
    private final String thisContentTypeLiteral;

    public ContentType(final String aContentTypeLiteral) throws IllegalArgumentException {
        if (!exists(aContentTypeLiteral)) {
            thisMediaType = null;
            thisParameters = Collections.emptyMap();
            thisCharset = null;
            thisContentTypeLiteral = "";
        } else {
            // TODO to support the quoted-string parameter value
            final String[] tmps = aContentTypeLiteral.split("\\;");
            thisMediaType = tmps[0].trim().toLowerCase();
            thisParameters = Collections.unmodifiableMap(new LinkedHashMap<String, String>() {
                {
                    for (int i = 1; i < tmps.length; i++) {
                        final String tmp = tmps[i];
                        if (exists(tmp)) {
                            final String[] aParameter = tmp.split("\\=", 2);
                            final String theName = aParameter[0].trim().toLowerCase();
                            final String theValue = aParameter.length >= 2 ? aParameter[1].trim() : null;
                            put(theName, theValue);
                        }
                    }
                }
            });
            thisCharset = optional(thisParameters.get("charset")).map(Charset::forName).orElse(null);
            //
            thisContentTypeLiteral = theContentTypeLiteral(thisMediaType, thisParameters);
        }
    }

    private static String theContentTypeLiteral(final String aMediaType, final Map<String, String> someParameters) {
        return optional(aMediaType).orElse("") +
                someParameters.entrySet().stream().map(it -> {
                    final String theKey = optional(it.getKey()).orElse("");
                    final String theValue = it.getValue();
                    if (theValue != null) {
                        return String.format("; %s=%s", theKey, theValue);
                    } else {
                        return String.format("; %s", theKey);
                    }
                }).collect(Collectors.joining());
    }

    /**********************************************************************
     *
     * the media-type.
     *
     **********************************************************************/
    public String MediaType() {
        return thisMediaType;
    }

    /**********************************************************************
     *
     * the "charset" parameter.
     *
     **********************************************************************/
    public Charset Charset() {
        return thisCharset;
    }

    /**********************************************************************
     *
     * the "boundary" parameter.
     *
     **********************************************************************/
    public String Boundary() {
        return Parameter("boundary");
    }

    /**********************************************************************
     *
     * a parameter.
     *
     **********************************************************************/
    public String Parameter(final String someName) throws IllegalArgumentException {
        return thisParameters.get(mandatory(someName).toLowerCase());
    }

    @Override
    public String toString() {
        return thisContentTypeLiteral;
    }
}
