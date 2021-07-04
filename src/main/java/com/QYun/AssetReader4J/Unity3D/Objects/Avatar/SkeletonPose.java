package com.QYun.AssetReader4J.Unity3D.Objects.Avatar;

import com.QYun.AssetReader4J.Unity3D.ObjectReader;
import com.QYun.AssetReader4J.Unity3D.Objects.AnimationClip.xform;

public class SkeletonPose {
    public xform[] m_X;

    public SkeletonPose(ObjectReader reader) {
        int numXforms = reader.readInt();
        m_X = new xform[numXforms];
        for (int i = 0; i < numXforms; i++) {
            m_X[i] = new xform(reader);
        }
    }
}
