package com.QYun.AssetReader4J.Unity3D.Objects;

import com.QYun.AssetReader4J.Entities.Enums;
import com.QYun.AssetReader4J.Entities.Enums.AudioCompressionFormat;
import com.QYun.AssetReader4J.Entities.Enums.AudioType;
import com.QYun.AssetReader4J.ResourceReader;
import com.QYun.AssetReader4J.Unity3D.Contracts.NamedObject;
import com.QYun.AssetReader4J.Unity3D.ObjectReader;

public class AudioClip extends NamedObject {
    public int m_Format;
    public AudioType m_Type;
    public boolean m_3D;
    public boolean m_UseHardware;

    //version 5
    public int m_LoadType;
    public int m_Channels;
    public int m_Frequency;
    public int m_BitsPerSample;
    public float m_Length;
    public boolean m_IsTrackerFormat;
    public int m_SubsoundIndex;
    public boolean m_PreloadAudioData;
    public boolean m_LoadInBackground;
    public boolean m_Legacy3D;
    public AudioCompressionFormat m_CompressionFormat;

    public String m_Source;
    public long m_Offset; //ulong
    public long m_Size; //ulong
    public ResourceReader m_AudioData;

    public AudioClip(ObjectReader reader) {
        super(reader);
        if (version[0] < 5) {
            m_Format = reader.ReadInt32();
            m_Type = Enums.audioType(reader.ReadInt32());
            m_3D = reader.ReadBoolean();
            m_UseHardware = reader.ReadBoolean();
            reader.AlignStream();

            if (version[0] >= 4 || (version[0] == 3 && version[1] >= 2)) //3.2.0 to 5
            {
                int m_Stream = reader.ReadInt32();
                m_Size = reader.ReadInt32();
                var tsize = m_Size % 4 != 0 ? m_Size + 4 - m_Size % 4 : m_Size;
                if (reader.byteSize + reader.byteStart - reader.getPos() != tsize) {
                    m_Offset = reader.ReadUInt32();
                    m_Source = assetsFile.file.getPath() + ".resS";
                }
            } else {
                m_Size = reader.ReadInt32();
            }
        } else {
            m_LoadType = reader.ReadInt32();
            m_Channels = reader.ReadInt32();
            m_Frequency = reader.ReadInt32();
            m_BitsPerSample = reader.ReadInt32();
            m_Length = reader.ReadSingle();
            m_IsTrackerFormat = reader.ReadBoolean();
            reader.AlignStream();
            m_SubsoundIndex = reader.ReadInt32();
            m_PreloadAudioData = reader.ReadBoolean();
            m_LoadInBackground = reader.ReadBoolean();
            m_Legacy3D = reader.ReadBoolean();
            reader.AlignStream();

            //StreamedResource m_Resource
            m_Source = reader.ReadAlignedString();
            m_Offset = reader.ReadInt64();
            m_Size = reader.ReadInt64();
            m_CompressionFormat = Enums.audioCompressionFormat(reader.ReadInt32());
        }

        ResourceReader resourceReader;
        if (!m_Source.isBlank()) {
            resourceReader = new ResourceReader(m_Source, assetsFile, m_Offset, m_Size);
        } else {
            resourceReader = new ResourceReader(reader, reader.getPos(), m_Size);
        }
        m_AudioData = resourceReader;
    }
}
