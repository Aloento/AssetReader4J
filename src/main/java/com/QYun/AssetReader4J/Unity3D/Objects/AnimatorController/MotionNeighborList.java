package com.QYun.AssetReader4J.Unity3D.Objects.AnimatorController;

import com.QYun.AssetReader4J.Unity3D.ObjectReader;

public class MotionNeighborList {
    public int[] m_NeighborArray;

    public MotionNeighborList(ObjectReader reader) {
        m_NeighborArray = reader.readInts(reader.readInt());
    }
}
