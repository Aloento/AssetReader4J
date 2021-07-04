package com.QYun.AssetReader4J.Unity3D.Contracts;

import com.QYun.AssetReader4J.Unity3D.ObjectReader;

public class NamedObject extends EditorExtension {
    public String m_Name;

    protected NamedObject(ObjectReader reader) {
        super(reader);
        m_Name = reader.readAlignedString();
    }
}
