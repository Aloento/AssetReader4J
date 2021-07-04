package com.QYun.AssetReader4J.Unity3D.Objects.Avatar;

import com.QYun.AssetReader4J.Unity3D.ObjectReader;
import com.QYun.AssetReader4J.Unity3D.Objects.AnimationClip.xform;

public class Collider {
    public xform m_X;
    public int m_Type;
    public int m_XMotionType;
    public int m_YMotionType;
    public int m_ZMotionType;
    public float m_MinLimitX;
    public float m_MaxLimitX;
    public float m_MaxLimitY;
    public float m_MaxLimitZ;

    public Collider(ObjectReader reader) {
        m_X = new xform(reader);
        m_Type = reader.readInt();
        m_XMotionType = reader.readInt();
        m_YMotionType = reader.readInt();
        m_ZMotionType = reader.readInt();
        m_MinLimitX = reader.readFloat();
        m_MaxLimitX = reader.readFloat();
        m_MaxLimitY = reader.readFloat();
        m_MaxLimitZ = reader.readFloat();
    }
}
