package com.QYun.AssetReader4J.Unity3D.Objects.Shader;

import com.QYun.AssetReader4J.Unity3D.ObjectReader;

public class SerializedShaderVectorValue {
    public SerializedShaderFloatValue x;
    public SerializedShaderFloatValue y;
    public SerializedShaderFloatValue z;
    public SerializedShaderFloatValue w;
    public String name;

    public SerializedShaderVectorValue(ObjectReader reader) {
        x = new SerializedShaderFloatValue(reader);
        y = new SerializedShaderFloatValue(reader);
        z = new SerializedShaderFloatValue(reader);
        w = new SerializedShaderFloatValue(reader);
        name = reader.readAlignedString();
    }
}
