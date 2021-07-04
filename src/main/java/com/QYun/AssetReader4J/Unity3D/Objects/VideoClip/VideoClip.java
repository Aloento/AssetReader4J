package com.QYun.AssetReader4J.Unity3D.Objects.VideoClip;

import com.QYun.AssetReader4J.ResourceReader;
import com.QYun.AssetReader4J.Unity3D.Contracts.NamedObject;
import com.QYun.AssetReader4J.Unity3D.ObjectReader;
import com.QYun.AssetReader4J.Unity3D.Objects.PPtr;
import com.QYun.AssetReader4J.Unity3D.Objects.Shader.Shader;
import org.eclipse.collections.api.factory.Lists;
import org.eclipse.collections.api.list.MutableList;

public class VideoClip extends NamedObject {
    public ResourceReader m_VideoData;
    public String m_OriginalPath;
    public StreamedResource m_ExternalResources;

    public VideoClip(ObjectReader reader) {
        super(reader);
        m_OriginalPath = reader.readAlignedString();
        var m_ProxyWidth = reader.readInt();
        var m_ProxyHeight = reader.readInt();
        var Width = reader.readInt();
        var Height = reader.readInt();
        if (version[0] > 2017 || (version[0] == 2017 && version[1] >= 2)) { //2017.2 and up
            var m_PixelAspecRatioNum = reader.readInt();
            var m_PixelAspecRatioDen = reader.readInt();
        }
        var m_FrameRate = reader.readDouble();
        var m_FrameCount = reader.readLong();
        var m_Format = reader.readInt();
        var m_AudioChannelCount = reader.readShortArray();
        reader.alignStream();
        var m_AudioSampleRate = reader.readIntArray();
        var m_AudioLanguage = reader.readStringArray();
        if (version[0] >= 2020) { //2020.1 and up
            var m_VideoShadersSize = reader.readInt();
            MutableList<PPtr<Shader>> m_VideoShaders = Lists.mutable.withInitialCapacity(m_VideoShadersSize);
            for (int i = 0; i < m_VideoShadersSize; i++) {
                m_VideoShaders.add(i, new PPtr<>(reader, Shader.class));
            }
        }
        m_ExternalResources = new StreamedResource(reader);
        var m_HasSplitAlpha = reader.readBoolean();
        if (version[0] >= 2020) { //2020.1 and up
            var m_sRGB = reader.readBoolean();
        }

        ResourceReader resourceReader;
        if (!m_ExternalResources.m_Source.isBlank()) {
            resourceReader = new ResourceReader(m_ExternalResources.m_Source, assetsFile, m_ExternalResources.m_Offset, m_ExternalResources.m_Size);
        } else {
            resourceReader = new ResourceReader(reader, reader.getPos(), m_ExternalResources.m_Size);
        }
        m_VideoData = resourceReader;
    }
}
