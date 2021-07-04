package com.QYun.AssetReader4J.Unity3D.Objects.Shader;

import com.QYun.AssetReader4J.Unity3D.ObjectReader;

public class SerializedShaderDependency {
    public String from;
    public String to;

    public SerializedShaderDependency(ObjectReader reader) {
        from = reader.readAlignedString();
        to = reader.readAlignedString();
    }
}
