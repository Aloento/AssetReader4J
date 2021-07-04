package com.QYun.AssetReader4J.Unity3D.Objects.AnimatorController;

import com.QYun.AssetReader4J.Unity3D.ObjectReader;

public class TransitionConstant {
    public ConditionConstant[] m_ConditionConstantArray;
    public int m_DestinationState;
    public int m_FullPathID;
    public int m_ID;
    public int m_UserID;
    public float m_TransitionDuration;
    public float m_TransitionOffset;
    public float m_ExitTime;
    public boolean m_HasExitTime;
    public boolean m_HasFixedDuration;
    public int m_InterruptionSource;
    public boolean m_OrderedInterruption;
    public boolean m_Atomic;
    public boolean m_CanTransitionToSelf;

    public TransitionConstant(ObjectReader reader) {
        var version = reader.version();

        int numConditions = reader.readInt();
        m_ConditionConstantArray = new ConditionConstant[numConditions];
        for (int i = 0; i < numConditions; i++) {
            m_ConditionConstantArray[i] = new ConditionConstant(reader);
        }

        m_DestinationState = reader.readInt();
        if (version[0] >= 5) { //5.0 and up
            m_FullPathID = reader.readInt();
        }

        m_ID = reader.readInt();
        m_UserID = reader.readInt();
        m_TransitionDuration = reader.readFloat();
        m_TransitionOffset = reader.readFloat();
        if (version[0] >= 5) { //5.0 and up
            m_ExitTime = reader.readFloat();
            m_HasExitTime = reader.readBoolean();
            m_HasFixedDuration = reader.readBoolean();
            reader.alignStream();
            m_InterruptionSource = reader.readInt();
            m_OrderedInterruption = reader.readBoolean();
        } else {
            m_Atomic = reader.readBoolean();
        }

        if (version[0] > 4 || (version[0] == 4 && version[1] >= 5)) { //4.5 and up
            m_CanTransitionToSelf = reader.readBoolean();
        }

        reader.alignStream();
    }
}
