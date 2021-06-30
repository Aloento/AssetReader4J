package com.QYun.AssetReader4J.Unity3D.Objects;

import com.QYun.AssetReader4J.Unity3D.UObjectReader;

public class StreamingInfo {
    public long offset;
    public int size;
    public String path;

    public StreamingInfo(UObjectReader reader) {
        var version = reader.version();

        if (version[0] >= 2020) { //2020.1 and up
            offset = reader.readLong();
        } else {
            offset = reader.readInt();
        }
        size = reader.readInt();
        path = reader.readAlignedString();
    }
}
