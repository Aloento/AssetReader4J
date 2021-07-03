package com.QYun.AssetReader4J.Unity3D.Objects.AnimatorController;

import com.QYun.AssetReader4J.Unity3D.ObjectReader;
import com.QYun.AssetReader4J.Unity3D.Objects.AnimationClip.ValueArrayConstant;

public class BlendTreeConstant {
    public BlendTreeNodeConstant[] m_NodeArray;
    public ValueArrayConstant m_BlendEventArrayConstant;

    public BlendTreeConstant(ObjectReader reader) {
        var version = reader.version();

        int numNodes = reader.readInt();
        m_NodeArray = new BlendTreeNodeConstant[numNodes];
        for (int i = 0; i < numNodes; i++) {
            m_NodeArray[i] = new BlendTreeNodeConstant(reader);
        }

        if (version[0] < 4 || (version[0] == 4 && version[1] < 5)) { //4.5 down
            m_BlendEventArrayConstant = new ValueArrayConstant(reader);
        }
    }
}
