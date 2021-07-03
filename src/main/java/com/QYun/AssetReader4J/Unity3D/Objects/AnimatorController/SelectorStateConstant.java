package com.QYun.AssetReader4J.Unity3D.Objects.AnimatorController;

import com.QYun.AssetReader4J.Unity3D.ObjectReader;

public class SelectorStateConstant {
    public SelectorTransitionConstant[] m_TransitionConstantArray;
    public int m_FullPathID;
    public boolean m_isEntry;

    public SelectorStateConstant(ObjectReader reader) {
        int numTransitions = reader.ReadInt32();
        m_TransitionConstantArray = new SelectorTransitionConstant[numTransitions];
        for (int i = 0; i < numTransitions; i++) {
            m_TransitionConstantArray[i] = new SelectorTransitionConstant(reader);
        }

        m_FullPathID = reader.ReadUInt32();
        m_isEntry = reader.ReadBoolean();
        reader.AlignStream();
    }
}
