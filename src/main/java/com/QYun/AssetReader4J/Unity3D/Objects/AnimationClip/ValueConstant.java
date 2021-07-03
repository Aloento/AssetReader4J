package com.QYun.AssetReader4J.Unity3D.Objects.AnimationClip;

import com.QYun.AssetReader4J.Unity3D.ObjectReader;

public class ValueConstant {
    public int m_ID;
    public int m_TypeID;
    public int m_Type;
    public int m_Index;

    public ValueConstant(ObjectReader reader) {
        var version = reader.version();
        m_ID = reader.readInt();
        if (version[0] < 5 || (version[0] == 5 && version[1] < 5)) { //5.5 down
            m_TypeID = reader.readInt();
        }
        m_Type = reader.readInt();
        m_Index = reader.readInt();
    }
}
