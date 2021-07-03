package com.QYun.AssetReader4J.Unity3D.Objects.Mesh;

import com.QYun.AssetReader4J.Unity3D.ObjectReader;

public class ChannelInfo {
    public byte stream;
    public byte offset;
    public byte format;
    public byte dimension;

    public ChannelInfo() {
    }

    public ChannelInfo(ObjectReader reader) {
        stream = reader.readByte();
        offset = reader.readByte();
        format = reader.readByte();
        dimension = (byte) (reader.readByte() & 0xF);
    }
}
