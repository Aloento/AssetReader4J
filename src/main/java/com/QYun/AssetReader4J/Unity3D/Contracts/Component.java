package com.QYun.AssetReader4J.Unity3D.Contracts;

import com.QYun.AssetReader4J.Unity3D.ObjectReader;
import com.QYun.AssetReader4J.Unity3D.Objects.GameObject;
import com.QYun.AssetReader4J.Unity3D.Objects.PPtr;

public abstract class Component extends EditorExtension {
    public PPtr<GameObject> m_GameObject;

    protected Component(ObjectReader reader) {
        super(reader);
        m_GameObject = new PPtr<GameObject>(reader);
    }
}
