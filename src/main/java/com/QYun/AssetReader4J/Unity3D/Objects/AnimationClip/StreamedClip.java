package com.QYun.AssetReader4J.Unity3D.Objects.AnimationClip;

import com.QYun.AssetReader4J.Unity3D.UObjectReader;

public class StreamedClip {
    public int[] data;
    public int curveCount;

    public StreamedClip(UObjectReader reader) {
        data = reader.readInts(reader.readInt());
        curveCount = reader.readInt();
    }
}
