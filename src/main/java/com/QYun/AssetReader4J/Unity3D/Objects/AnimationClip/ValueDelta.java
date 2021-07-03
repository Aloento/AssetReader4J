package com.QYun.AssetReader4J.Unity3D.Objects.AnimationClip;

import com.QYun.AssetReader4J.Unity3D.ObjectReader;

public class ValueDelta {
    public float m_Start;
    public float m_Stop;

    public ValueDelta(ObjectReader reader) {
        m_Start = reader.readFloat();
        m_Stop = reader.readFloat();
    }
}
