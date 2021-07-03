package com.QYun.AssetReader4J.Unity3D.Objects.AnimationClip;

import com.QYun.AssetReader4J.Unity3D.UObjectReader;

import javax.vecmath.Vector3f;
import javax.vecmath.Vector4f;

public class HumanPose {
    public xform m_RootX;
    public Vector3f m_LookAtPosition;
    public Vector4f m_LookAtWeight;
    public HumanGoal[] m_GoalArray;
    public HandPose m_LeftHandPose;
    public HandPose m_RightHandPose;
    public float[] m_DoFArray;
    public Vector3f[] m_TDoFArray;

    public HumanPose(UObjectReader reader) {
        var version = reader.version();
        m_RootX = new xform(reader);
        if (version[0] > 5 || (version[0] == 5 && version[1] >= 4))
            m_LookAtPosition = reader.readVector3();
        else {
            var tmp = reader.readVector4();
            m_LookAtPosition = new Vector3f(tmp.x, tmp.y, tmp.z);
        }
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
                if (version[0] > 5 || (version[0] == 5 && version[1] >= 4))
                    m_TDoFArray[i] = reader.readVector3();
                else {
                    var tmp = reader.readVector4();
                    m_TDoFArray[i] = new Vector3f(tmp.x, tmp.y, tmp.z);
                }
            }
        }
    }
}
