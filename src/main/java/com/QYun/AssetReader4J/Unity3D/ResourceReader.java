package com.QYun.AssetReader4J.Unity3D;

import com.QYun.AssetReader4J.SerializedFile;
import com.QYun.util.Stream.UnityStream;

public class ResourceReader {

    private Boolean needSearch;
    private String path;
    private SerializedFile assetsFile;
    private final long offset;
    private final int size;
    private UnityStream reader;

    public ResourceReader(String path, SerializedFile assetsFile, long offset, int size) {
        needSearch = true;
        this.path = path;
        this.assetsFile = assetsFile;
        this.offset = offset;
        this.size = size;
    }

    public ResourceReader(UnityStream reader, long offset, int size) {
        this.reader = reader;
        this.offset = offset;
        this.size = size;
    }

    public byte[] GetData() {
        if (needSearch) {
            if (assetsFile.assetsManager.resourceFileReaders.t) {

            }
        }
    }

}
