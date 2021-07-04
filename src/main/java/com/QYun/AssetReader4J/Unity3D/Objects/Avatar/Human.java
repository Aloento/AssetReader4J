package com.QYun.AssetReader4J.Unity3D.Objects.Avatar;

import com.QYun.AssetReader4J.Unity3D.ObjectReader;
import com.QYun.AssetReader4J.Unity3D.Objects.AnimationClip.xform;

public class Human {
    public xform m_RootX;
    public Skeleton m_Skeleton;
    public SkeletonPose m_SkeletonPose;
    public Hand m_LeftHand;
    public Hand m_RightHand;
    public Handle[] m_Handles;
    public Collider[] m_ColliderArray;
    public Integer[] m_HumanBoneIndex;
    public Float[] m_HumanBoneMass;
    public Integer[] m_ColliderIndex;
    public float m_Scale;
    public float m_ArmTwist;
    public float m_ForeArmTwist;
    public float m_UpperLegTwist;
    public float m_LegTwist;
    public float m_ArmStretch;
    public float m_LegStretch;
    public float m_FeetSpacing;
    public boolean m_HasLeftHand;
    public boolean m_HasRightHand;
    public boolean m_HasTDoF;

    public Human(ObjectReader reader) {
        var version = reader.version();
        m_RootX = new xform(reader);
        m_Skeleton = new Skeleton(reader);
        m_SkeletonPose = new SkeletonPose(reader);
        m_LeftHand = new Hand(reader);
        m_RightHand = new Hand(reader);

        if (version[0] < 2018 || (version[0] == 2018 && version[1] < 2)) { //2018.2 down
            int numHandles = reader.readInt();
            m_Handles = new Handle[numHandles];
            for (int i = 0; i < numHandles; i++) {
                m_Handles[i] = new Handle(reader);
            }

            int numColliders = reader.readInt();
            m_ColliderArray = new Collider[numColliders];
            for (int i = 0; i < numColliders; i++) {
                m_ColliderArray[i] = new Collider(reader);
            }
        }

        m_HumanBoneIndex = reader.readIntArray();

        m_HumanBoneMass = reader.readFloatArray();

        if (version[0] < 2018 || (version[0] == 2018 && version[1] < 2)) { //2018.2 down
            m_ColliderIndex = reader.readIntArray();
        }

        m_Scale = reader.readFloat();
        m_ArmTwist = reader.readFloat();
        m_ForeArmTwist = reader.readFloat();
        m_UpperLegTwist = reader.readFloat();
        m_LegTwist = reader.readFloat();
        m_ArmStretch = reader.readFloat();
        m_LegStretch = reader.readFloat();
        m_FeetSpacing = reader.readFloat();
        m_HasLeftHand = reader.readBoolean();
        m_HasRightHand = reader.readBoolean();
        if (version[0] > 5 || (version[0] == 5 && version[1] >= 2)) { //5.2 and up
            m_HasTDoF = reader.readBoolean();
        }
        reader.alignStream();
    }
}
