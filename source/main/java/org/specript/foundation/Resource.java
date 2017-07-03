/* ======================================---------------------------

  --+ Specript Foundation Library v3.0 +--

  Copyright (c) 2007-2017, Koichi Tanaka (@Tanaka9230)

    ---------------------------====================================== */

package org.specript.foundation;

import static org.specript.foundation.Ops.mandatory;
import org.specript.foundation.exceptions.LogicIntegrityCorruptedException;
import org.specript.foundation.exceptions.MissingResourceExceptionOps;
import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.URL;
import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;
import java.nio.file.FileSystemNotFoundException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import java.util.MissingResourceException;
import java.util.Properties;
import java.util.function.Supplier;

/**********************************************************************
 *
 * {@link Resource} is a file found on the classpath, loaded by the
 * {@link ClassLoader}.
 *
 **********************************************************************/
public class Resource {
    public static Resource forName(final String aResourceNamePath) throws MissingResourceException, IllegalArgumentException {
        return new Resource(aResourceNamePath, () -> Thread.currentThread().getContextClassLoader());
    }

    public static Resource forName(final String aResourceNamePath, final ClassLoader theClassLoader) throws MissingResourceException, IllegalArgumentException {
        return new Resource(aResourceNamePath, () -> theClassLoader);
    }

    //
    //
    //

    // followings are "contexts"
    private final Supplier<ClassLoader> thisClassLoader;
    // followings are "attributes"
    private final String thisResourceNamePath;
    private final URL thisUrl;

    private Resource(final String aResourceNamePath, final Supplier<ClassLoader> theClassLoader) throws MissingResourceException, IllegalArgumentException {
        thisClassLoader = mandatory(theClassLoader);
        //
        thisResourceNamePath = mandatory(aResourceNamePath);
        thisUrl = theResourceUrl();
    }

    private URL theResourceUrl() throws MissingResourceException {
        final URL theResourceUrl = thisClassLoader.get().getResource(thisResourceNamePath);
        if (theResourceUrl == null) {
            throw MissingResourceExceptionOps.with(String.format("the resource '%s' not found", thisResourceNamePath));
        }
        return theResourceUrl;
    }

    /**********************************************************************
     *
     * obtains this resource as a {@link java.net.URL}.
     *
     **********************************************************************/
    public URL asUrl() {
        return thisUrl;
    }

    /**********************************************************************
     *
     * obtains this resource as a {@link java.net.URI}.
     *
     **********************************************************************/
    public URI asUri() {
        final URL theResourceUrl = asUrl();
        try {
            return theResourceUrl.toURI();
        } catch (final URISyntaxException e) {
            throw new LogicIntegrityCorruptedException(e); // will never happen
        }
    }

    /**********************************************************************
     *
     * obtains this resource as a {@link java.io.File}.
     *
     **********************************************************************/
    public File asFile() throws MissingResourceException {
        final URI theResourceUri = asUri();
        try {
            return new File(theResourceUri);
        } catch (final RuntimeException e) {
            throw MissingResourceExceptionOps.with(String.format("can't access the resource '%s'", theResourceUri.toString()), e);
        }
    }

    /**********************************************************************
     *
     * obtains this resource as a {@link java.nio.file.Path}.
     *
     **********************************************************************/
    public Path asPath() throws MissingResourceException {
        final URI theResourceUri = asUri();
        try {
            return Paths.get(theResourceUri);
        } catch (final FileSystemNotFoundException e) { // FileSystemNotFoundException is a subclass of RuntimeException
            throw MissingResourceExceptionOps.with(String.format("can't access the resource '%s'", theResourceUri.toString()), e);
        } catch (final RuntimeException e) {
            throw MissingResourceExceptionOps.with(String.format("can't access the resource '%s'", theResourceUri.toString()), e);
        }
    }

    /**********************************************************************
     *
     * obtains this resource as a {@link java.io.InputStream}.
     *
     **********************************************************************/
    public InputStream asInputStream() throws MissingResourceException {
        final Path theResourcePath = asPath();
        try {
            return Files.newInputStream(theResourcePath);
        } catch (final IOException e) {
            throw MissingResourceExceptionOps.with(e);
        } catch (final RuntimeException e) {
            throw MissingResourceExceptionOps.with(e);
        }
    }

    /**********************************************************************
     *
     * obtains this resource as a {@link java.io.BufferedReader}.
     *
     **********************************************************************/
    public BufferedReader asReader() throws MissingResourceException {
        return asReader(StandardCharsets.UTF_8);
    }

    /**********************************************************************
     *
     * obtains this resource as a {@link java.io.BufferedReader}.
     *
     **********************************************************************/
    public BufferedReader asReader(final Charset aCharset) throws MissingResourceException {
        final Path theResourcePath = asPath();
        try {
            return Files.newBufferedReader(theResourcePath, aCharset);
        } catch (final IOException e) {
            throw MissingResourceExceptionOps.with(e);
        } catch (final RuntimeException e) {
            throw MissingResourceExceptionOps.with(e);
        }
    }

    /**********************************************************************
     *
     * obtains this resource as a list of strings, each string
     * represents a line of the file.
     *
     **********************************************************************/
    public List<String> asLines() throws MissingResourceException {
        return asLines(StandardCharsets.UTF_8);
    }

    /**********************************************************************
     *
     * obtains this resource as a list of strings, each string
     * represents a line of the file.
     *
     **********************************************************************/
    public List<String> asLines(final Charset aCharset) throws MissingResourceException {
        final Path theResourcePath = asPath();
        try {
            final List<String> ret = Files.readAllLines(theResourcePath, aCharset);
            return ret;
        } catch (final IOException e) {
            throw MissingResourceExceptionOps.with(e);
        } catch (final RuntimeException e) {
            throw MissingResourceExceptionOps.with(e);
        }
    }

    /**********************************************************************
     *
     * obtains this resource as a {@link java.util.Properties}.
     *
     **********************************************************************/
    public Properties asProperties() throws MissingResourceException {
        return asProperties(StandardCharsets.UTF_8);
    }

    /**********************************************************************
     *
     * obtains this resource as a {@link java.util.Properties}.
     *
     **********************************************************************/
    public Properties asProperties(final Charset aCharset) throws MissingResourceException {
        try {
            return PropertiesOps.from(asReader(aCharset));
        } catch (final IOException e) {
            throw MissingResourceExceptionOps.with(e);
        } catch (final RuntimeException e) {
            throw MissingResourceExceptionOps.with(e);
        }
    }
}
