package com.QYun.AssetReader4J.Unity3D.Objects.Mesh;

import com.QYun.AssetReader4J.Unity3D.ObjectReader;

public class MeshBlendShapeChannel {
    public String name;
    public int nameHash;
    public int frameIndex;
    public int frameCount;

    public MeshBlendShapeChannel(ObjectReader reader) {
        name = reader.ReadAlignedString();
        nameHash = reader.ReadUInt32();
        frameIndex = reader.ReadInt32();
        frameCount = reader.ReadInt32();
    }
}
