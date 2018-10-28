package com.example.brunoorocha.petshop;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;

public class UrlUtils {
    public static String toString(InputStream is) throws IOException {

        byte[] bytes = new byte[1024];
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        int read;
        while ((read = is.read(bytes)) > 0) {
            baos.write(bytes, 0, read);
        }
        return new String(baos.toByteArray());
    }
}
