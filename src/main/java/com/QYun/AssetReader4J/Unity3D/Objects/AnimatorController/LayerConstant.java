package com.QYun.AssetReader4J.Unity3D.Objects.AnimatorController;

import com.QYun.AssetReader4J.Unity3D.ObjectReader;

public class LayerConstant {
    public int m_StateMachineIndex;
    public int m_StateMachineMotionSetIndex;
    public HumanPoseMask m_BodyMask;
    public SkeletonMask m_SkeletonMask;
    public int m_Binding;
    public int m_LayerBlendingMode;
    public float m_DefaultWeight;
    public boolean m_IKPass;
    public boolean m_SyncedLayerAffectsTiming;

    public LayerConstant(ObjectReader reader) {
        var version = reader.version();

        m_StateMachineIndex = reader.readInt();
        m_StateMachineMotionSetIndex = reader.readInt();
        m_BodyMask = new HumanPoseMask(reader);
        m_SkeletonMask = new SkeletonMask(reader);
        m_Binding = reader.readInt();
        m_LayerBlendingMode = reader.readInt();
        if (version[0] > 4 || (version[0] == 4 && version[1] >= 2)) { //4.2 and up
            m_DefaultWeight = reader.readFloat();
        }
        m_IKPass = reader.readBoolean();
        if (version[0] > 4 || (version[0] == 4 && version[1] >= 2)) { //4.2 and up
            m_SyncedLayerAffectsTiming = reader.readBoolean();
        }
        reader.alignStream();
    }
}
