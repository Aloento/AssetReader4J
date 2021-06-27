package com.QYun.AssetReader4J.Unity3D;

import com.QYun.AssetReader4J.Entities.Enums.BuildTarget;

public abstract class EditorExtension extends UObject {
    protected EditorExtension(UObjectReader reader) {
        super(reader);
        if (platform == BuildTarget.NoTarget) {
            var m_PrefabParentObject = new PPtr<EditorExtension>(reader);
            var m_PrefabInternal = new PPtr<UObject>(reader);
        }
    }
}
