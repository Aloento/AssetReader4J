package com.QYun.AssetReader4J.Helpers;

import com.QYun.AssetReader4J.SevenZip.LZMA.Decoder;
import com.QYun.util.Stream.UnityStream;

import java.io.ByteArrayOutputStream;
import java.io.IOException;

public class SevenZipHelper {
    public static void streamDecompress(UnityStream compressedStream,
                                        UnityStream decompressedStream,
                                        int decompressedSize) throws IOException {
        Decoder decoder = new Decoder();
        decoder.SetDecoderProperties(compressedStream.readBytes(5));
        var tmp = new ByteArrayOutputStream(decompressedSize);
        decoder.Code(compressedStream.toInputStreamWithPos(), tmp, decompressedSize);
        decompressedStream.write(tmp.toByteArray());
    }
}
