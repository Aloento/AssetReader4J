package com.QYun.AssetReader4J.Unity3D.Objects.Avatar;

import com.QYun.AssetReader4J.Unity3D.ObjectReader;

import javax.vecmath.Vector4f;

public class Axes {
    public Vector4f m_PreQ;
    public Vector4f m_PostQ;
    public Object m_Sgn;
    public Limit m_Limit;
    public float m_Length;
    public int m_Type;

    public Axes(ObjectReader reader) {
        var version = reader.version();
        m_PreQ = reader.ReadVector4();
        m_PostQ = reader.ReadVector4();
        if (version[0] > 5 || (version[0] == 5 && version[1] >= 4)) { //5.4 and up
            m_Sgn = reader.ReadVector3();
        } else {
            m_Sgn = reader.ReadVector4();
        }
        m_Limit = new Limit(reader);
        m_Length = reader.ReadSingle();
        m_Type = reader.ReadUInt32();
    }
}
