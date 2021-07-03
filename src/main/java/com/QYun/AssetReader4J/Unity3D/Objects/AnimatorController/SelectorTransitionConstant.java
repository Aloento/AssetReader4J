package com.QYun.AssetReader4J.Unity3D.Objects.AnimatorController;

import com.QYun.AssetReader4J.Unity3D.ObjectReader;

public class SelectorTransitionConstant {
    public int m_Destination;
    public ConditionConstant[] m_ConditionConstantArray;

    public SelectorTransitionConstant(ObjectReader reader) {
        m_Destination = reader.ReadUInt32();

        int numConditions = reader.ReadInt32();
        m_ConditionConstantArray = new ConditionConstant[numConditions];
        for (int i = 0; i < numConditions; i++) {
            m_ConditionConstantArray[i] = new ConditionConstant(reader);
        }
    }
}
