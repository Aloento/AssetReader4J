package com.QYun.AssetReader4J.Unity3D.Objects.AnimatorController;

import com.QYun.AssetReader4J.Unity3D.ObjectReader;

public class ConditionConstant {
    public int m_ConditionMode;
    public int m_EventID;
    public float m_EventThreshold;
    public float m_ExitTime;

    public ConditionConstant(ObjectReader reader) {
        m_ConditionMode = reader.readInt();
        m_EventID = reader.readInt();
        m_EventThreshold = reader.readFloat();
        m_ExitTime = reader.readFloat();
    }
}
