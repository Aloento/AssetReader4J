package com.QYun.AssetReader4J.Unity3D.Objects.AnimationClip;

import com.QYun.AssetReader4J.Unity3D.ObjectReader;
import com.QYun.AssetReader4J.Unity3D.Objects.PPtr;
import com.QYun.AssetReader4J.Unity3D.UObject;

public class AnimationEvent {
    public float time;
    public String functionName;
    public String data;
    public PPtr<UObject> objectReferenceParameter;
    public float floatParameter;
    public int intParameter;
    public int messageOptions;

    public AnimationEvent(ObjectReader reader) {
        var version = reader.version();

        time = reader.readFloat();
        functionName = reader.readAlignedString();
        data = reader.readAlignedString();
        objectReferenceParameter = new PPtr<>(reader, UObject.class);
        floatParameter = reader.readFloat();
        if (version[0] >= 3) { //3 and up
            intParameter = reader.readInt();
        }
        messageOptions = reader.readInt();
    }
}
