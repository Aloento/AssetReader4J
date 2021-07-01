package com.QYun.AssetReader4J.Unity3D.Objects;

import com.QYun.AssetReader4J.Unity3D.UObjectReader;

public class ValueDelta {
    public float m_Start;
    public float m_Stop;

    public ValueDelta(UObjectReader reader) {
        m_Start = reader.readFloat();
        m_Stop = reader.readFloat();
    }
}
