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
        m_Type = reader.ReadUInt32();
        m_XMotionType = reader.ReadUInt32();
        m_YMotionType = reader.ReadUInt32();
        m_ZMotionType = reader.ReadUInt32();
        m_MinLimitX = reader.ReadSingle();
        m_MaxLimitX = reader.ReadSingle();
        m_MaxLimitY = reader.ReadSingle();
        m_MaxLimitZ = reader.ReadSingle();
    }
}
