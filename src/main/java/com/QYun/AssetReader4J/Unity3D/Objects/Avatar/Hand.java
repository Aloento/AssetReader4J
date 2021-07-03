package com.QYun.AssetReader4J.Unity3D.Objects.Avatar;

import com.QYun.AssetReader4J.Unity3D.ObjectReader;

public class Hand {
    public Integer[] m_HandBoneIndex;

    public Hand(ObjectReader reader) {
        m_HandBoneIndex = reader.ReadInt32Array();
    }
}
