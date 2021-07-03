package com.QYun.AssetReader4J.Unity3D.Objects.AnimationClip;

import com.QYun.AssetReader4J.Unity3D.ObjectReader;

import javax.vecmath.Quat4f;

public class QuaternionCurve {
    public AnimationCurve<Quat4f> curve;
    public String path;

    public QuaternionCurve(ObjectReader reader) {
        curve = new AnimationCurve<>(reader, reader::ReadQuaternion);
        path = reader.ReadAlignedString();
    }
}
