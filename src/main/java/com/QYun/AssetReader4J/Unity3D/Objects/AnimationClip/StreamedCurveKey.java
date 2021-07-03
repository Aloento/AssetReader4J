package com.QYun.AssetReader4J.Unity3D.Objects.AnimationClip;

import com.QYun.util.Stream.UnityStream;

public class StreamedCurveKey {
    public int index;
    public Float[] coeff;

    public float value;
    public float outSlope;
    public float inSlope;

    public StreamedCurveKey(UnityStream reader) {
        index = reader.readInt();
        coeff = reader.readFloats(4);

        outSlope = coeff[2];
        value = coeff[3];
    }
}
