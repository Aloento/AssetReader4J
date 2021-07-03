package com.QYun.AssetReader4J.Unity3D.Objects.AnimationClip;

import com.QYun.AssetReader4J.Unity3D.ObjectReader;

public class HandPose {
    public xform m_GrabX;
    public Float[] m_DoFArray;
    public float m_Override;
    public float m_CloseOpen;
    public float m_InOut;
    public float m_Grab;

    public HandPose(ObjectReader reader) {
        m_GrabX = new xform(reader);
        m_DoFArray = reader.readFloats(reader.readInt());
        m_Override = reader.readFloat();
        m_CloseOpen = reader.readFloat();
        m_InOut = reader.readFloat();
        m_Grab = reader.readFloat();
    }
}
