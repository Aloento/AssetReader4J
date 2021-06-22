package com.QYun.AssetReader4J.Entities;

import com.QYun.Stream.UnityStream;

import java.io.File;
import java.io.InputStream;

public class Struct {
    public static class StreamFile {
        public File file;
        public String fileName;
        public UnityStream stream;
    }

    public static class Header {
        public String signature;
        public int version;
        public String unityVersion;
        public String unityRevision;
        public long size;
        public int compressedBlocksInfoSize;
        public int uncompressedBlocksInfoSize;
        public int flags;
    }

    public static class StorageBlock {
        public int compressedSize;
        public int uncompressedSize;
        public short flags;
    }

    public static class Node {
        public long offset;
        public long size;
        public int flags;
        public String path;
    }
}
