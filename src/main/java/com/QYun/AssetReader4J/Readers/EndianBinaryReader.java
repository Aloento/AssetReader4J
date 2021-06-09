package com.QYun.AssetReader4J.Readers;

import java.io.File;

public class EndianBinaryReader extends BinaryReader {
    public EndianType endian;

    public EndianBinaryReader(File file, EndianType endian) {
        super(file);
        this.endian = endian;
    }

    public enum EndianType {
        LittleEndian,
        BigEndian
    }
}
