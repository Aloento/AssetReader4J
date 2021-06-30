package com.QYun.AssetReader4J.Unity3D.Objects;

import com.QYun.AssetReader4J.Unity3D.UObjectReader;

public class StreamInfo {
    public int channelMask;
    public int offset;
    public int stride;
    public int align;
    public byte dividerOp;
    public short frequency;

    public StreamInfo() {
    }

    public StreamInfo(UObjectReader reader) {
        var version = reader.version();

        channelMask = reader.readInt();
        offset = reader.readInt();

        if (version[0] < 4) //4.0 down
        {
            stride = reader.readInt();
            align = reader.readInt();
        } else {
            stride = reader.readByte();
            dividerOp = reader.readByte();
            frequency = reader.readShort();
        }
    }
}