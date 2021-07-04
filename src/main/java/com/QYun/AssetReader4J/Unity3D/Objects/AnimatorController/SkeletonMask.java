package com.QYun.AssetReader4J.Unity3D.Objects.AnimatorController;

import com.QYun.AssetReader4J.Unity3D.ObjectReader;

public class SkeletonMask {
    public SkeletonMaskElement[] m_Data;

    public SkeletonMask(ObjectReader reader) {
        int numElements = reader.readInt();
        m_Data = new SkeletonMaskElement[numElements];
        for (int i = 0; i < numElements; i++) {
            m_Data[i] = new SkeletonMaskElement(reader);
        }
    }
}
