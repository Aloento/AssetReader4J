package com.QYun.AssetReader4J.Unity3D.Contracts;

import com.QYun.AssetReader4J.Unity3D.Objects.GameObject;
import com.QYun.AssetReader4J.Unity3D.PPtr;
import com.QYun.AssetReader4J.Unity3D.UObjectReader;

public abstract class Component extends EditorExtension {
    public PPtr<GameObject> m_GameObject;

    protected Component(UObjectReader reader) {
        super(reader);
        m_GameObject = new PPtr<GameObject>(reader);
    }
}
