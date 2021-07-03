package com.QYun.AssetReader4J.Unity3D.Objects.AnimatorController;

import com.QYun.AssetReader4J.Unity3D.ObjectReader;

public class Blend1dDataConstant {
    public Float[] m_ChildThresholdArray;

    public Blend1dDataConstant(ObjectReader reader) {
        m_ChildThresholdArray = reader.readFloats(reader.readInt());
    }
}
