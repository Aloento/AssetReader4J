package com.QYun.AssetReader4J.Unity3D.Objects.AnimationClip;

import com.QYun.AssetReader4J.Unity3D.UObjectReader;

public class ValueArrayConstant {
    public ValueConstant[] m_ValueArray;

    public ValueArrayConstant(UObjectReader reader) {
        int numVals = reader.readInt();
        m_ValueArray = new ValueConstant[numVals];
        for (int i = 0; i < numVals; i++) {
            m_ValueArray[i] = new ValueConstant(reader);
        }
    }
}
