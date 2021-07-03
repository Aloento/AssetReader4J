package com.QYun.AssetReader4J.Unity3D.Objects.AnimationClip;

import com.QYun.AssetReader4J.Unity3D.UObjectReader;

public class ConstantClip {
    public float[] data;

    public ConstantClip(UObjectReader reader) {
        data = reader.readFloats(reader.readInt());
    }
}
