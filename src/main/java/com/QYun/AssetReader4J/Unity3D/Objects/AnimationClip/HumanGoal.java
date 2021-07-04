package com.QYun.AssetReader4J.Unity3D.Objects.AnimationClip;

import com.QYun.AssetReader4J.Unity3D.ObjectReader;

import javax.vecmath.Vector3f;

public class HumanGoal {
    public xform m_X;
    public float m_WeightT;
    public float m_WeightR;
    public Vector3f m_HintT;
    public float m_HintWeightT;

    public HumanGoal(ObjectReader reader) {
        var version = reader.version();
        m_X = new xform(reader);
        m_WeightT = reader.readFloat();
        m_WeightR = reader.readFloat();
        if (version[0] >= 5) //5.0 and up
        {
            //5.4 and up
            m_HintT = version[0] > 5 || (version[0] == 5 && version[1] >= 4) ? reader.readVector3() : reader.read4ToVector3();
            m_HintWeightT = reader.readFloat();
        }
    }
}
