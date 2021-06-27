package com.QYun.AssetReader4J.Unity3D;

public class NamedObject extends EditorExtension {
    public String m_Name;

    protected NamedObject(UObjectReader reader) {
        super(reader);
        m_Name = reader.readAlignedString();
    }
}
