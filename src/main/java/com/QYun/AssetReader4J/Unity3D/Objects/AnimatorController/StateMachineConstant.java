package com.QYun.AssetReader4J.Unity3D.Objects.AnimatorController;

import com.QYun.AssetReader4J.Unity3D.ObjectReader;

public class StateMachineConstant {
    public StateConstant[] m_StateConstantArray;
    public TransitionConstant[] m_AnyStateTransitionConstantArray;
    public SelectorStateConstant[] m_SelectorStateConstantArray;
    public int m_DefaultState;
    public int m_MotionSetCount;

    public StateMachineConstant(ObjectReader reader) {
        var version = reader.version();

        int numStates = reader.readInt();
        m_StateConstantArray = new StateConstant[numStates];
        for (int i = 0; i < numStates; i++) {
            m_StateConstantArray[i] = new StateConstant(reader);
        }

        int numAnyStates = reader.readInt();
        m_AnyStateTransitionConstantArray = new TransitionConstant[numAnyStates];
        for (int i = 0; i < numAnyStates; i++) {
            m_AnyStateTransitionConstantArray[i] = new TransitionConstant(reader);
        }

        if (version[0] >= 5) { //5.0 and up
            int numSelectors = reader.readInt();
            m_SelectorStateConstantArray = new SelectorStateConstant[numSelectors];
            for (int i = 0; i < numSelectors; i++) {
                m_SelectorStateConstantArray[i] = new SelectorStateConstant(reader);
            }
        }

        m_DefaultState = reader.readInt();
        m_MotionSetCount = reader.readInt();
    }
}
