package com.QYun.AssetReader4J.Unity3D.Objects.Avatar;

import com.QYun.AssetReader4J.Unity3D.ObjectReader;
import com.QYun.AssetReader4J.Unity3D.Objects.AnimationClip.xform;

public class AvatarConstant {
    public Skeleton m_AvatarSkeleton;
    public SkeletonPose m_AvatarSkeletonPose;
    public SkeletonPose m_DefaultPose;
    public Integer[] m_SkeletonNameIDArray;
    public Human m_Human;
    public Integer[] m_HumanSkeletonIndexArray;
    public Integer[] m_HumanSkeletonReverseIndexArray;
    public int m_RootMotionBoneIndex;
    public xform m_RootMotionBoneX;
    public Skeleton m_RootMotionSkeleton;
    public SkeletonPose m_RootMotionSkeletonPose;
    public Integer[] m_RootMotionSkeletonIndexArray;

    public AvatarConstant(ObjectReader reader) {
        var version = reader.version();
        m_AvatarSkeleton = new Skeleton(reader);
        m_AvatarSkeletonPose = new SkeletonPose(reader);

        if (version[0] > 4 || (version[0] == 4 && version[1] >= 3)) { //4.3 and up
            m_DefaultPose = new SkeletonPose(reader);

            m_SkeletonNameIDArray = reader.readIntArray();
        }

        m_Human = new Human(reader);

        m_HumanSkeletonIndexArray = reader.readIntArray();

        if (version[0] > 4 || (version[0] == 4 && version[1] >= 3)) { //4.3 and up
            m_HumanSkeletonReverseIndexArray = reader.readIntArray();
        }

        m_RootMotionBoneIndex = reader.readInt();
        m_RootMotionBoneX = new xform(reader);

        if (version[0] > 4 || (version[0] == 4 && version[1] >= 3)) { //4.3 and up
            m_RootMotionSkeleton = new Skeleton(reader);
            m_RootMotionSkeletonPose = new SkeletonPose(reader);

            m_RootMotionSkeletonIndexArray = reader.readIntArray();
        }
    }
}
