package com.QYun.AssetReader4J;

import java.io.IOException;

public class SerializedFile {
    public static boolean isSerializedFile(BinaryStream reader) {
        try {
            if (reader.fileLen < 20)
                return false;

            int m_MetadataSize = reader.readInt();
            long m_FileSize = reader.readInt();
            int m_Version = reader.readInt();
            long m_DataOffset = reader.readInt();
            byte m_Endianess = reader.readByte();
            byte[] m_Reserved = reader.readBytes(3);

            if (m_Version >= 22) {
                if (reader.fileLen < 48)
                    return false;

                m_MetadataSize = reader.readInt();
                m_FileSize = reader.readLong();
                m_DataOffset = reader.readLong();
            }
            if (m_FileSize != reader.fileLen || m_DataOffset > reader.fileLen) {
                reader.reset();
                return false;
            }

            reader.reset();
            return true;
        } catch (IOException e) {
            e.printStackTrace();
            return false;
        }
    }
}
