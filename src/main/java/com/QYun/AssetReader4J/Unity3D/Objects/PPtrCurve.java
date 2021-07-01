package com.QYun.AssetReader4J.Unity3D.Objects;

import com.QYun.AssetReader4J.Unity3D.PPtr;
import com.QYun.AssetReader4J.Unity3D.UObjectReader;

public class PPtrCurve {
    public PPtrKeyframe[] curve;
    public String attribute;
    public String path;
    public int classID;
    public PPtr<MonoScript> script;


    public PPtrCurve(UObjectReader reader) {
        int numCurves = reader.readInt();
        curve = new PPtrKeyframe[numCurves];
        for (int i = 0; i < numCurves; i++) {
            curve[i] = new PPtrKeyframe(reader);
        }

        attribute = reader.readAlignedString();
        path = reader.readAlignedString();
        classID = reader.readInt();
        script = new PPtr<MonoScript>(reader);
    }
}
