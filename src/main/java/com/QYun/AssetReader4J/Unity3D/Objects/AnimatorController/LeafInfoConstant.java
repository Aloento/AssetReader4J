package com.QYun.AssetReader4J.Unity3D.Objects.AnimatorController;

import com.QYun.AssetReader4J.Unity3D.ObjectReader;

public class LeafInfoConstant {
    public int[] m_IDArray;
    public int m_IndexOffset;

    public LeafInfoConstant(ObjectReader reader) {
        m_IDArray = reader.readInts(reader.readInt());
        m_IndexOffset = reader.readInt();
    }
}
