package com.QYun.AssetReader4J.Unity3D.Objects.AnimatorController;

import com.QYun.AssetReader4J.Unity3D.ObjectReader;
import com.QYun.AssetReader4J.Unity3D.Objects.AnimationClip.ValueArrayConstant;

public class ControllerConstant {
    public LayerConstant[] m_LayerArray;
    public StateMachineConstant[] m_StateMachineArray;
    public ValueArrayConstant m_Values;
    public ValueArray m_DefaultValues;

    public ControllerConstant(ObjectReader reader) {
        int numLayers = reader.readInt();
        m_LayerArray = new LayerConstant[numLayers];
        for (int i = 0; i < numLayers; i++) {
            m_LayerArray[i] = new LayerConstant(reader);
        }

        int numStates = reader.readInt();
        m_StateMachineArray = new StateMachineConstant[numStates];
        for (int i = 0; i < numStates; i++) {
            m_StateMachineArray[i] = new StateMachineConstant(reader);
        }

        m_Values = new ValueArrayConstant(reader);
        m_DefaultValues = new ValueArray(reader);
    }
}
