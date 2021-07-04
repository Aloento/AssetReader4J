package com.QYun.AssetReader4J.Unity3D.Objects.Mesh;

import com.QYun.AssetReader4J.Unity3D.ObjectReader;

public class MeshBlendShapeChannel {
    public String name;
    public int nameHash;
    public int frameIndex;
    public int frameCount;

    public MeshBlendShapeChannel(ObjectReader reader) {
        name = reader.readAlignedString();
        nameHash = reader.readInt();
        frameIndex = reader.readInt();
        frameCount = reader.readInt();
    }
}
