package com.QYun.AssetReader4J.Unity3D.Objects;

import com.QYun.AssetReader4J.Unity3D.UObjectReader;

import javax.vecmath.Vector3f;

public class Vector3Curve {
    public AnimationCurve<Vector3f> curve;
    public String path;

    public Vector3Curve(UObjectReader reader) {
        curve = new AnimationCurve<>(reader, reader::readVector3);
        path = reader.readAlignedString();
    }
}
