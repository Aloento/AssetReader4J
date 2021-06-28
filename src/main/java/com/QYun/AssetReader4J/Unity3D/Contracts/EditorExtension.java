package com.QYun.AssetReader4J.Unity3D.Contracts;

import com.QYun.AssetReader4J.Entities.Enums.BuildTarget;
import com.QYun.AssetReader4J.Unity3D.PPtr;
import com.QYun.AssetReader4J.Unity3D.UObject;
import com.QYun.AssetReader4J.Unity3D.UObjectReader;

public abstract class EditorExtension extends UObject {
    protected EditorExtension(UObjectReader reader) {
        super(reader);
        if (platform == BuildTarget.NoTarget) {
            var m_PrefabParentObject = new PPtr<EditorExtension>(reader);
            var m_PrefabInternal = new PPtr<UObject>(reader);
        }
    }
}
