package com.QYun.AssetReader4J.Unity3D.Objects.AnimationClip;

import com.QYun.AssetReader4J.Unity3D.ObjectReader;

public class CompressedAnimationCurve {
    public String m_Path;
    public PackedIntVector m_Times;
    public PackedQuatVector m_Values;
    public PackedFloatVector m_Slopes;
    public int m_PreInfinity;
    public int m_PostInfinity;

    public CompressedAnimationCurve(ObjectReader reader) {
        m_Path = reader.ReadAlignedString();
        m_Times = new PackedIntVector(reader);
        m_Values = new PackedQuatVector(reader);
        m_Slopes = new PackedFloatVector(reader);
        m_PreInfinity = reader.readInt();
        m_PostInfinity = reader.readInt();
    }
}
