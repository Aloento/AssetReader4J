package com.QYun.AssetReader4J.Unity3D.Objects.Mesh;

import com.QYun.AssetReader4J.Unity3D.ObjectReader;

public class BoneWeights4 {
    public Float[] weight;
    public Integer[] boneIndex;

    public BoneWeights4() {
        weight = new Float[4];
        boneIndex = new Integer[4];
    }

    public BoneWeights4(ObjectReader reader) {
        weight = reader.readFloats(4);
        boneIndex = reader.readInts(4);
    }
}
