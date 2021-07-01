package com.QYun.AssetReader4J.Unity3D.Objects;

import com.QYun.AssetReader4J.Unity3D.UObjectReader;

import javax.vecmath.Quat4f;

public class QuaternionCurve {
    public AnimationCurve<Quat4f> curve;
    public String path;

    public QuaternionCurve(UObjectReader reader) {
        curve = new AnimationCurve<>(reader, reader::readQuaternion);
        path = reader.readAlignedString();
    }
}
