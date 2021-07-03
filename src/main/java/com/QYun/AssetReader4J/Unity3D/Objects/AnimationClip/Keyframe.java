package com.QYun.AssetReader4J.Unity3D.Objects.AnimationClip;

import com.QYun.AssetReader4J.Unity3D.ObjectReader;

import java.util.function.Supplier;

public class Keyframe<T> {
    public float time;
    public T value;
    public T inSlope;
    public T outSlope;
    public int weightedMode;
    public T inWeight;
    public T outWeight;

    public Keyframe(ObjectReader reader, Supplier<T> supplier) {
        time = reader.readFloat();
        value = supplier.get();
        inSlope = supplier.get();
        outSlope = supplier.get();
        if (reader.version()[0] >= 2018) { //2018 and up
            weightedMode = reader.readInt();
            inWeight = supplier.get();
            outWeight = supplier.get();
        }
    }
}
