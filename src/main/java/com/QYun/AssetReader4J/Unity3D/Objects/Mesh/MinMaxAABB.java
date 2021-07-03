package com.QYun.AssetReader4J.Unity3D.Objects.Mesh;

import com.QYun.AssetReader4J.Unity3D.ObjectReader;

import javax.vecmath.Vector3f;

public class MinMaxAABB {
    public Vector3f m_Min;
    public Vector3f m_Max;

    public MinMaxAABB(ObjectReader reader) {
        m_Min = reader.ReadVector3();
        m_Max = reader.ReadVector3();
    }
}
