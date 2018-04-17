package com.maciejnowicki.bloxdownloader.util;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.BufferedInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;

public class URLResourceProvider {

    private static final Logger log = LoggerFactory.getLogger(URLResourceProvider.class);

    public byte[] getByteArray(String url) {
        try {
            InputStream resourceInputStream = new URL(url).openStream();
            return getBytes(resourceInputStream);
        } catch (IOException e) {
            log.warn("Unable to get resource located at {}", url);
            return null;
        }
    }

    private byte[] getBytes(InputStream resourceInputStream) throws IOException {
        byte[] buffer = new byte[1024];
        try (InputStream inputStream = new BufferedInputStream(resourceInputStream);
                ByteArrayOutputStream outputStream = new ByteArrayOutputStream()) {
            int n;
            while ((n = inputStream.read(buffer)) != -1)
                outputStream.write(buffer, 0, n);

            return outputStream.toByteArray();
        }
    }
}
