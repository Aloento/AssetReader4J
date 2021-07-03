package com.QYun.AssetReader4J.Unity3D.Objects.AnimationClip;

import com.QYun.AssetReader4J.Unity3D.ObjectReader;

public class ConstantClip {
    public float[] data;

    public ConstantClip(ObjectReader reader) {
        data = reader.readFloats(reader.readInt());
    }
}
