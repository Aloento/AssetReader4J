package com.QYun.AssetReader4J.Unity3D.Objects.AnimationClip;

import com.QYun.AssetReader4J.Entities.Enums;
import com.QYun.AssetReader4J.Entities.Enums.ClassIDType;
import com.QYun.AssetReader4J.Unity3D.Objects.MonoScript;
import com.QYun.AssetReader4J.Unity3D.Objects.PPtr;
import com.QYun.AssetReader4J.Unity3D.UObjectReader;

public class FloatCurve {
    public AnimationCurve<Float> curve;
    public String attribute;
    public String path;
    public ClassIDType classID;
    public PPtr<MonoScript> script;

    public FloatCurve(UObjectReader reader) {
        curve = new AnimationCurve<>(reader, reader::readFloat);
        attribute = reader.readAlignedString();
        path = reader.readAlignedString();
        classID = Enums.classIDType(reader.readInt());
        script = new PPtr<>(reader);
    }
}
