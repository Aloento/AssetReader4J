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
        m_PreQ = reader.readVector4();
        m_PostQ = reader.readVector4();
        if (version[0] > 5 || (version[0] == 5 && version[1] >= 4)) { //5.4 and up
            m_Sgn = reader.readVector3();
        } else {
            m_Sgn = reader.readVector4();
        }
        m_Limit = new Limit(reader);
        m_Length = reader.readFloat();
        m_Type = reader.readInt();
    }
}
