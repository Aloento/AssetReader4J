package com.QYun.AssetReader4J.Unity3D;

import javax.vecmath.Vector3f;

public class AABB {
    public Vector3f m_Center;
    public Vector3f m_Extent;

    public AABB(UObjectReader reader) {
        m_Center = reader.readVector3();
        m_Extent = reader.readVector3();
    }
}
