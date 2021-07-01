package com.QYun.AssetReader4J.Unity3D.Objects;

import com.QYun.AssetReader4J.Unity3D.UObjectReader;

public class PackedQuatVector {
    public int m_NumItems;
    public byte[] m_Data;

    public PackedQuatVector(UObjectReader reader) {
        m_NumItems = reader.readInt();

        int numData = reader.readInt();
        m_Data = reader.readBytes(numData);

        reader.alignStream();
    }
}
