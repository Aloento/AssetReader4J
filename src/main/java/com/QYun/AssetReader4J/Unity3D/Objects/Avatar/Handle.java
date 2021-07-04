package com.QYun.AssetReader4J.Unity3D.Objects.Avatar;

import com.QYun.AssetReader4J.Unity3D.ObjectReader;
import com.QYun.AssetReader4J.Unity3D.Objects.AnimationClip.xform;

public class Handle {
    public xform m_X;
    public int m_ParentHumanIndex;
    public int m_ID;

    public Handle(ObjectReader reader) {
        m_X = new xform(reader);
        m_ParentHumanIndex = reader.readInt();
        m_ID = reader.readInt();
    }
}
