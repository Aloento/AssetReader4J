package com.QYun.AssetReader4J.Unity3D.Objects.Texture2D;

import com.QYun.AssetReader4J.Entities.Enums;
import com.QYun.AssetReader4J.Entities.Enums.TextureFormat;
import com.QYun.AssetReader4J.ResourceReader;
import com.QYun.AssetReader4J.Unity3D.Contracts.Texture;
import com.QYun.AssetReader4J.Unity3D.ObjectReader;

import java.util.Objects;

public class Texture2D extends Texture {
    public int m_Width;
    public int m_Height;
    public TextureFormat m_TextureFormat;
    public boolean m_MipMap;
    public int m_MipCount;
    public GLTextureSettings m_TextureSettings;
    public ResourceReader image_data;
    public StreamingInfo m_StreamData;

    public Texture2D(ObjectReader reader) {
        super(reader);

        m_Width = reader.readInt();
        m_Height = reader.readInt();
        var m_CompleteImageSize = reader.readInt();
        if (version[0] >= 2020) {
            var m_MipsStripped = reader.readInt();
        }
        m_TextureFormat = Enums.textureFormat(reader.readInt());
        if (version[0] < 5 || (version[0] == 5 && version[1] < 2)) { //5.2 down
            m_MipMap = reader.readBoolean();
        } else {
            m_MipCount = reader.readInt();
        }
        if (version[0] > 2 || (version[0] == 2 && version[1] >= 6)) { //2.6.0 and up
            var m_IsReadable = reader.readBoolean();
        }
        if (version[0] >= 2020) //2020.1 and up
        {
            var m_IsPreProcessed = reader.readBoolean();
        }
        if (version[0] > 2019 || (version[0] == 2019 && version[1] >= 3)) { //2019.3 and up
            var m_IgnoreMasterTextureLimit = reader.readBoolean();
        }
        if (version[0] >= 3) { //3.0.0 - 5.4
            if (version[0] < 5 || (version[0] == 5 && version[1] <= 4)) {
                var m_ReadAllowed = reader.readBoolean();
            }
        }
        if (version[0] > 2018 || (version[0] == 2018 && version[1] >= 2)) {//2018.2 and up
            var m_StreamingMipmaps = reader.readBoolean();
        }
        reader.AlignStream();
        if (version[0] > 2018 || (version[0] == 2018 && version[1] >= 2)) {//2018.2 and up
            var m_StreamingMipmapsPriority = reader.readInt();
        }
        var m_ImageCount = reader.readInt();
        var m_TextureDimension = reader.readInt();
        m_TextureSettings = new GLTextureSettings(reader);
        if (version[0] >= 3) {//3.0 and up
            var m_LightmapFormat = reader.readInt();
        }
        if (version[0] > 3 || (version[0] == 3 && version[1] >= 5)) {//3.5.0 and up
            var m_ColorSpace = reader.readInt();
        }
        if (version[0] > 2020 || (version[0] == 2020 && version[1] >= 2)) {//2020.2 and up
            var m_PlatformBlob = reader.readShorts(reader.readInt());
            reader.AlignStream();
        }
        var image_data_size = reader.readInt();
        if (image_data_size == 0 && ((version[0] == 5 && version[1] >= 3) || version[0] > 5)) {//5.3.0 and up
            m_StreamData = new StreamingInfo(reader);
        }

        ResourceReader resourceReader = null;
        if (m_StreamData != null) {
            if (m_StreamData.path != null) {
                if (!m_StreamData.path.isBlank() && !m_StreamData.path.isEmpty())
                    resourceReader = new ResourceReader(m_StreamData.path, assetsFile, m_StreamData.offset, m_StreamData.size);
            } else {
                resourceReader = new ResourceReader(reader, reader.getPos(), image_data_size);
            }
        }
        image_data = Objects.requireNonNull(resourceReader);
    }
}
