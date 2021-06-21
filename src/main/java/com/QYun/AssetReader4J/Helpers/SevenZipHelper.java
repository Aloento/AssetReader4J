package com.QYun.AssetReader4J.Helpers;

import com.QYun.AssetReader4J.SevenZip.LZMA.Decoder;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.EOFException;
import java.io.IOException;

public class SevenZipHelper {
    public static void streamDecompress(ByteArrayInputStream compressedStream,
                                        ByteArrayOutputStream decompressedStream,
                                        int decompressedSize) throws IOException {
        Decoder decoder = new Decoder();
        byte[] properties = new byte[5];
        if (compressedStream.read(properties, 0, 5) != 5)
            throw new EOFException();
        decoder.SetDecoderProperties(properties);
        decoder.Code(compressedStream, decompressedStream, decompressedSize);
    }
}
