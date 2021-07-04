package com.QYun.AssetReader4J.Unity3D.Objects.VideoClip;

import com.QYun.util.Stream.UnityStream;

public class StreamedResource {
    public String m_Source;
    public long m_Offset; //ulong
    public long m_Size; //ulong

    public StreamedResource(UnityStream reader) {
        m_Source = reader.readAlignedString();
        m_Offset = reader.readLong();
        m_Size = reader.readLong();
    }
}
