package com.QYun.AssetReader4J.Unity3D;

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
        if (version[0] >= 2017) {
            m_WrapMode = reader.readInt();
            int m_WrapV = reader.readInt();
            int m_WrapW = reader.readInt();
        } else {
            m_WrapMode = reader.readInt();
        }

    }

}
