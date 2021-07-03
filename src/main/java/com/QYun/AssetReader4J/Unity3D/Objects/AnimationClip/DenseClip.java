package com.QYun.AssetReader4J.Unity3D.Objects.AnimationClip;

import com.QYun.AssetReader4J.Unity3D.ObjectReader;

public class DenseClip {
    public int m_FrameCount;
    public int m_CurveCount;
    public float m_SampleRate;
    public float m_BeginTime;
    public float[] m_SampleArray;

    public DenseClip(ObjectReader reader) {
        m_FrameCount = reader.readInt();
        m_CurveCount = reader.readInt();
        m_SampleRate = reader.readFloat();
        m_BeginTime = reader.readFloat();
        m_SampleArray = reader.readFloats(reader.readInt());
    }
}
