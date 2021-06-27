package com.QYun.AssetReader4J.Unity3D;

public abstract class Component extends EditorExtension {
    public PPtr<GameObject> m_GameObject;

    protected Component(UObjectReader reader) {
        super(reader);
        m_GameObject = new PPtr<GameObject>(reader);
    }
}
