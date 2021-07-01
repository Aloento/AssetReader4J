package com.QYun.AssetReader4J.Unity3D.Objects.Mesh;

import com.QYun.AssetReader4J.Unity3D.UObjectReader;

public class BoneWeights4 {
    public float[] weight;
    public int[] boneIndex;

    public BoneWeights4() {
        weight = new float[4];
        boneIndex = new int[4];
    }

    public BoneWeights4(UObjectReader reader) {
        weight = reader.readFloats(4);
        boneIndex = reader.readInts(4);
    }
}
