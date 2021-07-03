package com.QYun.AssetReader4J.Unity3D.Objects.AnimatorController;

import com.QYun.AssetReader4J.Unity3D.ObjectReader;

import javax.vecmath.Vector2f;

public class Blend2dDataConstant {
    public Vector2f[] m_ChildPositionArray;
    public Float[] m_ChildMagnitudeArray;
    public Vector2f[] m_ChildPairVectorArray;
    public Float[] m_ChildPairAvgMagInvArray;
    public MotionNeighborList[] m_ChildNeighborListArray;

    public Blend2dDataConstant(ObjectReader reader) {
        m_ChildPositionArray = reader.readVector2s(reader.readInt());
        m_ChildMagnitudeArray = reader.readFloats(reader.readInt());
        m_ChildPairVectorArray = reader.readVector2s(reader.readInt());
        m_ChildPairAvgMagInvArray = reader.readFloats(reader.readInt());

        int numNeighbours = reader.readInt();
        m_ChildNeighborListArray = new MotionNeighborList[numNeighbours];
        for (int i = 0; i < numNeighbours; i++) {
            m_ChildNeighborListArray[i] = new MotionNeighborList(reader);
        }
    }
}
