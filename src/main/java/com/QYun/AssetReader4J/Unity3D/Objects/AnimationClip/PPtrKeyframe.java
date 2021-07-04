package com.QYun.AssetReader4J.Unity3D.Objects.AnimationClip;

import com.QYun.AssetReader4J.Unity3D.ObjectReader;
import com.QYun.AssetReader4J.Unity3D.Objects.PPtr;
import com.QYun.AssetReader4J.Unity3D.UObject;

public class PPtrKeyframe {
    public float time;
    public PPtr<UObject> value;

    public PPtrKeyframe(ObjectReader reader) {
        time = reader.readFloat();
        value = new PPtr<>(reader, UObject.class);
    }
}
