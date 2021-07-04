package com.QYun.AssetReader4J.Unity3D.Objects.Shader;

import com.QYun.AssetReader4J.Unity3D.ObjectReader;

public class SerializedShaderFloatValue {
    public float val;
    public String name;

    public SerializedShaderFloatValue(ObjectReader reader) {
        val = reader.ReadSingle();
        name = reader.ReadAlignedString();
    }
}
