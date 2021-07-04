package com.QYun.AssetReader4J.Unity3D.Objects.AnimationClip;

import com.QYun.AssetReader4J.Unity3D.ObjectReader;

import javax.vecmath.Vector3f;
import javax.vecmath.Vector4f;

public class HumanPose {
    public xform m_RootX;
    public Vector3f m_LookAtPosition;
    public Vector4f m_LookAtWeight;
    public HumanGoal[] m_GoalArray;
    public HandPose m_LeftHandPose;
    public HandPose m_RightHandPose;
    public Float[] m_DoFArray;
    public Vector3f[] m_TDoFArray;

    public HumanPose(ObjectReader reader) {
        var version = reader.version();
        m_RootX = new xform(reader);
        m_LookAtPosition = version[0] > 5 || (version[0] == 5 && version[1] >= 4) ? reader.readVector3() : reader.read4ToVector3();
        m_LookAtWeight = reader.readVector4();

        int numGoals = reader.readInt();
        m_GoalArray = new HumanGoal[numGoals];
        for (int i = 0; i < numGoals; i++) {
            m_GoalArray[i] = new HumanGoal(reader);
        }

        m_LeftHandPose = new HandPose(reader);
        m_RightHandPose = new HandPose(reader);

        m_DoFArray = reader.readFloats(reader.readInt());

        if (version[0] > 5 || (version[0] == 5 && version[1] >= 2)) { //5.2 and up
            int numTDof = reader.readInt();
            m_TDoFArray = new Vector3f[numTDof];
            for (int i = 0; i < numTDof; i++) {
                m_TDoFArray[i] = version[0] > 5 || (version[0] == 5 && version[1] >= 4) ? reader.readVector3() : reader.read4ToVector3();
            }
        }
    }
}
