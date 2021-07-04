package com.QYun.AssetReader4J.Unity3D.Objects.Avatar;

import com.QYun.AssetReader4J.Unity3D.ObjectReader;

public class Skeleton {
    public Node[] m_Node;
    public Integer[] m_ID;
    public Axes[] m_AxesArray;

    public Skeleton(ObjectReader reader) {
        int numNodes = reader.readInt();
        m_Node = new Node[numNodes];
        for (int i = 0; i < numNodes; i++) {
            m_Node[i] = new Node(reader);
        }

        m_ID = reader.readIntArray();

        int numAxes = reader.readInt();
        m_AxesArray = new Axes[numAxes];
        for (int i = 0; i < numAxes; i++) {
            m_AxesArray[i] = new Axes(reader);
        }
    }
}
