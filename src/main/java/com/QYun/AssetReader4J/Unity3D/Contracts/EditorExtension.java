package com.QYun.AssetReader4J.Unity3D.Contracts;

import com.QYun.AssetReader4J.Entities.Enums.BuildTarget;
import com.QYun.AssetReader4J.Unity3D.ObjectReader;
import com.QYun.AssetReader4J.Unity3D.Objects.PPtr;
import com.QYun.AssetReader4J.Unity3D.UObject;

public abstract class EditorExtension extends UObject {
    protected EditorExtension(ObjectReader reader) {
        super(reader);
        if (platform == BuildTarget.NoTarget) {
            var m_PrefabParentObject = new PPtr<>(reader, EditorExtension.class);
            var m_PrefabInternal = new PPtr<>(reader, UObject.class);
        }
    }
}
