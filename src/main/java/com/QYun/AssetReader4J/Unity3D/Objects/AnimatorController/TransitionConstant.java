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

        int numConditions = reader.ReadInt32();
        m_ConditionConstantArray = new ConditionConstant[numConditions];
        for (int i = 0; i < numConditions; i++) {
            m_ConditionConstantArray[i] = new ConditionConstant(reader);
        }

        m_DestinationState = reader.ReadUInt32();
        if (version[0] >= 5) { //5.0 and up
            m_FullPathID = reader.ReadUInt32();
        }

        m_ID = reader.ReadUInt32();
        m_UserID = reader.ReadUInt32();
        m_TransitionDuration = reader.ReadSingle();
        m_TransitionOffset = reader.ReadSingle();
        if (version[0] >= 5) { //5.0 and up
            m_ExitTime = reader.ReadSingle();
            m_HasExitTime = reader.ReadBoolean();
            m_HasFixedDuration = reader.ReadBoolean();
            reader.AlignStream();
            m_InterruptionSource = reader.ReadInt32();
            m_OrderedInterruption = reader.ReadBoolean();
        } else {
            m_Atomic = reader.ReadBoolean();
        }

        if (version[0] > 4 || (version[0] == 4 && version[1] >= 5)) { //4.5 and up
            m_CanTransitionToSelf = reader.ReadBoolean();
        }

        reader.AlignStream();
    }
}
