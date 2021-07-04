package com.QYun.AssetReader4J.Unity3D.Objects.Shader;

import com.QYun.AssetReader4J.Unity3D.ObjectReader;

public class VectorParameter {
    public int m_NameIndex;
    public int m_Index;
    public int m_ArraySize;
    public byte m_Type;
    public byte m_Dim;

    public VectorParameter(ObjectReader reader) {
        m_NameIndex = reader.readInt();
        m_Index = reader.readInt();
        m_ArraySize = reader.readInt();
        m_Type = reader.readByte(); //m_Type = reader.ReadSByte();
        m_Dim = reader.readByte();  //m_Dim = reader.ReadSByte();
        reader.alignStream();
    }
}
