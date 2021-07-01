package com.QYun.AssetReader4J.Unity3D.Objects.AnimationClip;

import com.QYun.AssetReader4J.Unity3D.PPtr;
import com.QYun.AssetReader4J.Unity3D.UObject;
import com.QYun.AssetReader4J.Unity3D.UObjectReader;

public class PPtrKeyframe {
    public float time;
    public PPtr<UObject> value;


    public PPtrKeyframe(UObjectReader reader) {
        time = reader.readFloat();
        value = new PPtr<UObject>(reader);
    }
}
