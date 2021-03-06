package com.QYun.AssetReader4J.Unity3D.Objects.Shader;

import com.QYun.AssetReader4J.Unity3D.ObjectReader;

public class ParserBindChannels {
    public ShaderBindChannel[] m_Channels;
    public int m_SourceMap;

    public ParserBindChannels(ObjectReader reader) {
        int numChannels = reader.readInt();
        m_Channels = new ShaderBindChannel[numChannels];
        for (int i = 0; i < numChannels; i++) {
            m_Channels[i] = new ShaderBindChannel(reader);
        }
        reader.alignStream();

        m_SourceMap = reader.readInt();
    }
}
