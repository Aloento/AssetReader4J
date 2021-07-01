package com.QYun.AssetReader4J.Unity3D.Contracts;

import com.QYun.AssetReader4J.Unity3D.UObjectReader;

public class StaticBatchInfo {
    public short firstSubMesh;
    public short subMeshCount;

    public StaticBatchInfo(UObjectReader reader) {
        firstSubMesh = reader.readShort();
        subMeshCount = reader.readShort();
    }
}
