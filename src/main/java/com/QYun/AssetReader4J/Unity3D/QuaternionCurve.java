package com.QYun.AssetReader4J.Unity3D;

public class QuaternionCurve {
    public AnimationCurve<Quaternion> curve;
    public String path;

    public QuaternionCurve(UObjectReader reader) {
        curve = new AnimationCurve<Quaternion>(reader, reader.readQuaternion());
        path = reader.readAlignedString();
    }
}
