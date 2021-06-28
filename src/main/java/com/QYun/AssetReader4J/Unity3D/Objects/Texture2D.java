package com.QYun.AssetReader4J.Unity3D.Objects;

import com.QYun.AssetReader4J.Entities.Enums.TextureFormat;
import com.QYun.AssetReader4J.ResourceReader;
import com.QYun.AssetReader4J.Unity3D.Contracts.Texture;
import com.QYun.AssetReader4J.Unity3D.GLTextureSettings;
import com.QYun.AssetReader4J.Unity3D.UObjectReader;

public class Texture2D extends Texture {

    public int m_Width;
    public int m_Height;
    public TextureFormat m_TextureFormat;
    public Boolean m_MipMap;
    public int m_MipCount;
    public GLTextureSettings m_TextureSettings;
    public ResourceReader image_data;
    public StreamingInfo m_StreamData;

    public Texture2D(UObjectReader reader) {
        super(reader);
        // TODO
    }

}
