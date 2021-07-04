package com.QYun.AssetReader4J.Unity3D.Objects.Avatar;

import com.QYun.AssetReader4J.Unity3D.ObjectReader;

public class Node {
    public int m_ParentId;
    public int m_AxesId;

    public Node(ObjectReader reader) {
        m_ParentId = reader.readInt();
        m_AxesId = reader.readInt();
    }
}
