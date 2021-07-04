package com.QYun.AssetReader4J.Unity3D.Objects.AnimationClip;

import com.QYun.AssetReader4J.Entities.Enums;
import com.QYun.AssetReader4J.Entities.Enums.ClassIDType;
import com.QYun.AssetReader4J.Unity3D.ObjectReader;
import com.QYun.AssetReader4J.Unity3D.Objects.MonoScript;
import com.QYun.AssetReader4J.Unity3D.Objects.PPtr;

public class FloatCurve {
    public AnimationCurve<Float> curve;
    public String attribute;
    public String path;
    public ClassIDType classID;
    public PPtr<MonoScript> script;

    public FloatCurve(ObjectReader reader) {
        curve = new AnimationCurve<>(reader, reader::readFloat);
        attribute = reader.ReadAlignedString();
        path = reader.ReadAlignedString();
        classID = Enums.classIDType(reader.readInt());
        script = new PPtr<>(reader, MonoScript.class);
    }
}
