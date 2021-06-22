package com.QYun.AssetReader4J.Helpers;

import com.QYun.Stream.UnityStream;

import java.io.IOException;

public class StreamCopyHelper {
    private static final int bufferSize = 81920;

    public static void copyTo(UnityStream src, UnityStream dest, long size) throws IOException {
        for (var left = size; left > 0; left -= bufferSize) {
            int toRead = bufferSize < left ? bufferSize : (int) left;
            dest.write(src.readBytes(toRead));
        }
    }
}
