package com.QYun.AssetReader4J.Unity3D.Objects.AnimationClip;

import com.QYun.AssetReader4J.Unity3D.ObjectReader;
import com.QYun.AssetReader4J.Unity3D.Objects.MonoScript;
import com.QYun.AssetReader4J.Unity3D.Objects.PPtr;

public class PPtrCurve {
    public PPtrKeyframe[] curve;
    public String attribute;
    public String path;
    public int classID;
    public PPtr<MonoScript> script;

    public PPtrCurve(ObjectReader reader) {
        int numCurves = reader.readInt();
        curve = new PPtrKeyframe[numCurves];
        for (int i = 0; i < numCurves; i++) {
            curve[i] = new PPtrKeyframe(reader);
        }

        attribute = reader.ReadAlignedString();
        path = reader.ReadAlignedString();
        classID = reader.readInt();
        script = new PPtr<>(reader, MonoScript.class);
    }
}
