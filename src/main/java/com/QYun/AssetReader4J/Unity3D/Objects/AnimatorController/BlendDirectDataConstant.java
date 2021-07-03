package com.QYun.AssetReader4J.Unity3D.Objects.AnimatorController;

import com.QYun.AssetReader4J.Unity3D.ObjectReader;

public class BlendDirectDataConstant {
    public int[] m_ChildBlendEventIDArray;
    public boolean m_NormalizedBlendValues;

    public BlendDirectDataConstant(ObjectReader reader) {
        m_ChildBlendEventIDArray = reader.readInts(reader.readInt());
        m_NormalizedBlendValues = reader.readBoolean();
        reader.AlignStream();
    }
}
