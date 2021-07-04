package com.QYun.AssetReader4J.Unity3D.Objects.Shader;

import com.QYun.AssetReader4J.Unity3D.ObjectReader;

public class TextureParameter {
    public int m_NameIndex;
    public int m_Index;
    public int m_SamplerIndex;
    public byte m_Dim;

    public TextureParameter(ObjectReader reader) {
        var version = reader.version();

        m_NameIndex = reader.readInt();
        m_Index = reader.readInt();
        m_SamplerIndex = reader.readInt();
        if (version[0] > 2017 || (version[0] == 2017 && version[1] >= 3)) { //2017.3 and up
            var m_MultiSampled = reader.readBoolean();
        }
        m_Dim = reader.readByte(); //m_Dim = reader.ReadSByte();
        reader.alignStream();
    }
}
