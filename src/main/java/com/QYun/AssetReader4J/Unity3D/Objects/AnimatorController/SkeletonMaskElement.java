package com.QYun.AssetReader4J.Unity3D.Objects.AnimatorController;

import com.QYun.AssetReader4J.Unity3D.ObjectReader;

public class SkeletonMaskElement {
    public int m_PathHash;
    public float m_Weight;

    public SkeletonMaskElement(ObjectReader reader) {
        m_PathHash = reader.ReadUInt32();
        m_Weight = reader.ReadSingle();
    }
}
