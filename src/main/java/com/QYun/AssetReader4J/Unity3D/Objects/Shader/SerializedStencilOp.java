package com.QYun.AssetReader4J.Unity3D.Objects.Shader;

import com.QYun.AssetReader4J.Unity3D.ObjectReader;

public class SerializedStencilOp {
    public SerializedShaderFloatValue pass;
    public SerializedShaderFloatValue fail;
    public SerializedShaderFloatValue zFail;
    public SerializedShaderFloatValue comp;

    public SerializedStencilOp(ObjectReader reader) {
        pass = new SerializedShaderFloatValue(reader);
        fail = new SerializedShaderFloatValue(reader);
        zFail = new SerializedShaderFloatValue(reader);
        comp = new SerializedShaderFloatValue(reader);
    }
}
