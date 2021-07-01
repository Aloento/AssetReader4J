package com.QYun.AssetReader4J.Unity3D.Objects.Texture2D;

import com.QYun.AssetReader4J.Unity3D.UObjectReader;

public class GLTextureSettings {
    public int m_FilterMode;
    public int m_Aniso;
    public float m_MipBias;
    public int m_WrapMode;

    public GLTextureSettings(UObjectReader reader) {
        var version = reader.version();
        m_FilterMode = reader.readInt();
        m_Aniso = reader.readInt();
        m_MipBias = reader.readShort();
        m_WrapMode = reader.readInt();

        if (version[0] >= 2017) {
            int m_WrapV = reader.readInt();
            int m_WrapW = reader.readInt();
        }
    }
}
