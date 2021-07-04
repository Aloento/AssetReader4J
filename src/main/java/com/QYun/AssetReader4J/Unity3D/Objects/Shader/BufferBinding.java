package com.QYun.AssetReader4J.Unity3D.Objects.Shader;

import com.QYun.AssetReader4J.Unity3D.ObjectReader;

public class BufferBinding {
    public int m_NameIndex;
    public int m_Index;
    public int m_ArraySize;

    public BufferBinding(ObjectReader reader) {
        var version = reader.version();

        m_NameIndex = reader.readInt();
        m_Index = reader.readInt();
        if (version[0] >= 2020) { //2020.1 and up
            m_ArraySize = reader.readInt();
        }
    }
}
