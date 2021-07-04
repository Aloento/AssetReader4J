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
            var name = reader.readAlignedString();
        }
        firstVertex = reader.readInt();
        vertexCount = reader.readInt();
        if (version[0] == 4 && version[1] < 3) { //4.3 down
            var aabbMinDelta = reader.readVector3();
            var aabbMaxDelta = reader.readVector3();
        }
        hasNormals = reader.readBoolean();
        hasTangents = reader.readBoolean();
        if (version[0] > 4 || (version[0] == 4 && version[1] >= 3)) { //4.3 and up
            reader.alignStream();
        }
    }
}
