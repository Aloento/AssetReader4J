package com.QYun.AssetReader4J;

import com.QYun.Stream.UnityStream;

public class SerializedFile {
    public static boolean isSerializedFile(UnityStream reader) {
        if (reader.length < 20)
            return false;

        int m_MetadataSize = reader.readInt();
        long m_FileSize = reader.readInt();
        int m_Version = reader.readInt();
        long m_DataOffset = reader.readInt();
        byte m_Endianess = reader.readByte();
        byte[] m_Reserved = reader.readBytes(3);

        if (m_Version >= 22) {
            if (reader.length < 48)
                return false;

            m_MetadataSize = reader.readInt();
            m_FileSize = reader.readLong();
            m_DataOffset = reader.readLong();
        }
        if (m_FileSize != reader.length || m_DataOffset > reader.length) {
            reader.rewind();
            return false;
        }

        reader.rewind();
        return true;
    }
}
