package com.QYun.AssetReader4J.Unity3D.Objects.AnimationClip;

import com.QYun.AssetReader4J.Unity3D.ObjectReader;

public class StreamedClip {
    public Integer[] data;
    public int curveCount;

    public StreamedClip(ObjectReader reader) {
        data = reader.readInts(reader.readInt());
        curveCount = reader.readInt();
    }
}
