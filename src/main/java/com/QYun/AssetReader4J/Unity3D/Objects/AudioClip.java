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
            m_Format = reader.readInt();
            m_Type = Enums.audioType(reader.readInt());
            m_3D = reader.readBoolean();
            m_UseHardware = reader.readBoolean();
            reader.alignStream();

            if (version[0] >= 4 || (version[0] == 3 && version[1] >= 2)) //3.2.0 to 5
            {
                int m_Stream = reader.readInt();
                m_Size = reader.readInt();
                var tsize = m_Size % 4 != 0 ? m_Size + 4 - m_Size % 4 : m_Size;
                if (reader.byteSize + reader.byteStart - reader.getPos() != tsize) {
                    m_Offset = reader.readInt();
                    m_Source = assetsFile.file.getPath() + ".resS";
                }
            } else {
                m_Size = reader.readInt();
            }
        } else {
            m_LoadType = reader.readInt();
            m_Channels = reader.readInt();
            m_Frequency = reader.readInt();
            m_BitsPerSample = reader.readInt();
            m_Length = reader.readFloat();
            m_IsTrackerFormat = reader.readBoolean();
            reader.alignStream();
            m_SubsoundIndex = reader.readInt();
            m_PreloadAudioData = reader.readBoolean();
            m_LoadInBackground = reader.readBoolean();
            m_Legacy3D = reader.readBoolean();
            reader.alignStream();

            //StreamedResource m_Resource
            m_Source = reader.readAlignedString();
            m_Offset = reader.readLong();
            m_Size = reader.readLong();
            m_CompressionFormat = Enums.audioCompressionFormat(reader.readInt());
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
