package com.QYun.AssetReader4J.Unity3D.Objects.AnimationClip;

import com.QYun.AssetReader4J.Unity3D.ObjectReader;

public class PackedQuatVector {
    public int m_NumItems;
    public Byte[] m_Data;

    public PackedQuatVector(ObjectReader reader) {
        m_NumItems = reader.readInt();

        int numData = reader.readInt();
        m_Data = reader.readBytes(numData);

        reader.alignStream();
    }
}
