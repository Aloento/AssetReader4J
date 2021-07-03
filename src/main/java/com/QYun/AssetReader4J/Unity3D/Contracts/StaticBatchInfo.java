package com.QYun.AssetReader4J.Unity3D.Contracts;

import com.QYun.AssetReader4J.Unity3D.ObjectReader;

public class StaticBatchInfo {
    public short firstSubMesh;
    public short subMeshCount;

    public StaticBatchInfo(ObjectReader reader) {
        firstSubMesh = reader.readShort();
        subMeshCount = reader.readShort();
    }
}
