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
        m_OriginalPath = reader.ReadAlignedString();
        var m_ProxyWidth = reader.ReadUInt32();
        var m_ProxyHeight = reader.ReadUInt32();
        var Width = reader.ReadUInt32();
        var Height = reader.ReadUInt32();
        if (version[0] > 2017 || (version[0] == 2017 && version[1] >= 2)) { //2017.2 and up
            var m_PixelAspecRatioNum = reader.ReadUInt32();
            var m_PixelAspecRatioDen = reader.ReadUInt32();
        }
        var m_FrameRate = reader.ReadDouble();
        var m_FrameCount = reader.ReadUInt64();
        var m_Format = reader.ReadInt32();
        var m_AudioChannelCount = reader.ReadUInt16Array();
        reader.AlignStream();
        var m_AudioSampleRate = reader.ReadUInt32Array();
        var m_AudioLanguage = reader.ReadStringArray();
        if (version[0] >= 2020) { //2020.1 and up
            var m_VideoShadersSize = reader.ReadInt32();
            MutableList<PPtr<Shader>> m_VideoShaders = Lists.mutable.withInitialCapacity(m_VideoShadersSize);
            for (int i = 0; i < m_VideoShadersSize; i++) {
                m_VideoShaders.add(i, new PPtr<>(reader));
            }
        }
        m_ExternalResources = new StreamedResource(reader);
        var m_HasSplitAlpha = reader.ReadBoolean();
        if (version[0] >= 2020) { //2020.1 and up
            var m_sRGB = reader.ReadBoolean();
        }

        ResourceReader resourceReader;
        if (m_ExternalResources.m_Source.isBlank()) {
            resourceReader = new ResourceReader(m_ExternalResources.m_Source, assetsFile, m_ExternalResources.m_Offset, m_ExternalResources.m_Size);
        } else {
            resourceReader = new ResourceReader(reader, reader.getPos(), m_ExternalResources.m_Size);
        }
        m_VideoData = resourceReader;
    }
}
