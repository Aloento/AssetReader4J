package com.QYun.AssetReader4J.Unity3D;

import com.QYun.AssetReader4J.Entities.Enums;
import com.QYun.AssetReader4J.Entities.Enums.GfxPrimitiveType;

public class SubMesh {
    public int firstByte;
    public int indexCount;
    public GfxPrimitiveType topology;
    public int triangleCount;
    public int baseVertex;
    public int firstVertex;
    public int vertexCount;
    public AABB localAABB;

    public SubMesh(UObjectReader reader) {
        var version = reader.version();

        firstByte = reader.readInt();
        indexCount = reader.readInt();
        topology = Enums.gfxPrimitiveType(reader.readInt());

    }
}
