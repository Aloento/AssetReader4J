package com.QYun.AssetReader4J.Unity3D.Objects.Shader;

import com.QYun.AssetReader4J.Unity3D.ObjectReader;

public class UAVParameter {
    public int m_NameIndex;
    public int m_Index;
    public int m_OriginalIndex;

    public UAVParameter(ObjectReader reader) {
        m_NameIndex = reader.readInt();
        m_Index = reader.readInt();
        m_OriginalIndex = reader.readInt();
    }
}
