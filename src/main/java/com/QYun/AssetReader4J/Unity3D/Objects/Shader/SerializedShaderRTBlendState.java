package com.QYun.AssetReader4J.Unity3D.Objects.Shader;

import com.QYun.AssetReader4J.Unity3D.ObjectReader;

public class SerializedShaderRTBlendState {
    public SerializedShaderFloatValue srcBlend;
    public SerializedShaderFloatValue destBlend;
    public SerializedShaderFloatValue srcBlendAlpha;
    public SerializedShaderFloatValue destBlendAlpha;
    public SerializedShaderFloatValue blendOp;
    public SerializedShaderFloatValue blendOpAlpha;
    public SerializedShaderFloatValue colMask;

    public SerializedShaderRTBlendState(ObjectReader reader)
    {
        srcBlend = new SerializedShaderFloatValue(reader);
        destBlend = new SerializedShaderFloatValue(reader);
        srcBlendAlpha = new SerializedShaderFloatValue(reader);
        destBlendAlpha = new SerializedShaderFloatValue(reader);
        blendOp = new SerializedShaderFloatValue(reader);
        blendOpAlpha = new SerializedShaderFloatValue(reader);
        colMask = new SerializedShaderFloatValue(reader);
    }
}
