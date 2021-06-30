package com.QYun.AssetReader4J.Unity3D.Objects;

import com.QYun.AssetReader4J.Entities.Enums;
import com.QYun.AssetReader4J.Entities.Enums.GfxPrimitiveType;
import com.QYun.AssetReader4J.Unity3D.UObjectReader;

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

        if (version[0] < 4) {
            triangleCount = reader.readInt();
        }

        if (version[0] > 2017 || (version[0] == 2017 && version[1] >= 3)) {
            baseVertex = reader.readInt();
        }

        if (version[0] >= 3) {
            firstVertex = reader.readInt();
            vertexCount = reader.readInt();
            localAABB = new AABB(reader);
        }
    }
}
