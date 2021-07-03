package com.QYun.AssetReader4J.Unity3D.Objects.Mesh;

import com.QYun.AssetReader4J.Unity3D.ObjectReader;

public class MeshBlendShape {
    public int firstVertex;
    public int vertexCount;
    public boolean hasNormals;
    public boolean hasTangents;

    public MeshBlendShape(ObjectReader reader) {
        var version = reader.version();

        if (version[0] == 4 && version[1] < 3) { //4.3 down
            var name = reader.ReadAlignedString();
        }
        firstVertex = reader.ReadUInt32();
        vertexCount = reader.ReadUInt32();
        if (version[0] == 4 && version[1] < 3) { //4.3 down
            var aabbMinDelta = reader.ReadVector3();
            var aabbMaxDelta = reader.ReadVector3();
        }
        hasNormals = reader.ReadBoolean();
        hasTangents = reader.ReadBoolean();
        if (version[0] > 4 || (version[0] == 4 && version[1] >= 3)) { //4.3 and up
            reader.AlignStream();
        }
    }
}
