package com.QYun.AssetReader4J;

public class WebFile {
    public static Byte[] gzipMagic = {0x1f, (byte) 0x8b};
    public static Byte[] brotliMagic = {0x62, 0x72, 0x6F, 0x74, 0x6C, 0x69};
}
