package com.QYun.AssetReader4J.Helpers;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;

public class StreamCopyHelper {
    private static int bufferSize = 81920;

    public static void copyTo(InputStream src, InputStream dest, long size) throws IOException {
        var buffer = new byte[bufferSize];
        var tmp = new ByteArrayOutputStream(Math.toIntExact(size));

        for (var left = size; left > 0; left -= bufferSize) {
            int toRead = bufferSize < left ? bufferSize : (int) left;
            int read = src.read(buffer, 0, toRead);
            tmp.write(buffer, 0, read);
            if (read != toRead)
                break;
        }

        dest = new ByteArrayInputStream(tmp.toByteArray());
    }
}
