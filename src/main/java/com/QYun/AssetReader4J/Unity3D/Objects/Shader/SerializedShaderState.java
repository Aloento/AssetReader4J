package com.QYun.AssetReader4J.Unity3D.Objects.Shader;

import com.QYun.AssetReader4J.Entities.Enums;
import com.QYun.AssetReader4J.Entities.Enums.FogMode;
import com.QYun.AssetReader4J.Unity3D.ObjectReader;

public class SerializedShaderState {
    public String m_Name;
    public SerializedShaderRTBlendState[] rtBlend;
    public boolean rtSeparateBlend;
    public SerializedShaderFloatValue zClip;
    public SerializedShaderFloatValue zTest;
    public SerializedShaderFloatValue zWrite;
    public SerializedShaderFloatValue culling;
    public SerializedShaderFloatValue conservative;
    public SerializedShaderFloatValue offsetFactor;
    public SerializedShaderFloatValue offsetUnits;
    public SerializedShaderFloatValue alphaToMask;
    public SerializedStencilOp stencilOp;
    public SerializedStencilOp stencilOpFront;
    public SerializedStencilOp stencilOpBack;
    public SerializedShaderFloatValue stencilReadMask;
    public SerializedShaderFloatValue stencilWriteMask;
    public SerializedShaderFloatValue stencilRef;
    public SerializedShaderFloatValue fogStart;
    public SerializedShaderFloatValue fogEnd;
    public SerializedShaderFloatValue fogDensity;
    public SerializedShaderVectorValue fogColor;
    public FogMode fogMode;
    public int gpuProgramID;
    public SerializedTagMap m_Tags;
    public int m_LOD;
    public boolean lighting;

    public SerializedShaderState(ObjectReader reader) {
        var version = reader.version();

        m_Name = reader.readAlignedString();
        rtBlend = new SerializedShaderRTBlendState[8];
        for (int i = 0; i < 8; i++) {
            rtBlend[i] = new SerializedShaderRTBlendState(reader);
        }
        rtSeparateBlend = reader.readBoolean();
        reader.alignStream();
        if (version[0] > 2017 || (version[0] == 2017 && version[1] >= 2)) { //2017.2 and up
            zClip = new SerializedShaderFloatValue(reader);
        }
        zTest = new SerializedShaderFloatValue(reader);
        zWrite = new SerializedShaderFloatValue(reader);
        culling = new SerializedShaderFloatValue(reader);
        if (version[0] >= 2020) { //2020.1 and up
            conservative = new SerializedShaderFloatValue(reader);
        }
        offsetFactor = new SerializedShaderFloatValue(reader);
        offsetUnits = new SerializedShaderFloatValue(reader);
        alphaToMask = new SerializedShaderFloatValue(reader);
        stencilOp = new SerializedStencilOp(reader);
        stencilOpFront = new SerializedStencilOp(reader);
        stencilOpBack = new SerializedStencilOp(reader);
        stencilReadMask = new SerializedShaderFloatValue(reader);
        stencilWriteMask = new SerializedShaderFloatValue(reader);
        stencilRef = new SerializedShaderFloatValue(reader);
        fogStart = new SerializedShaderFloatValue(reader);
        fogEnd = new SerializedShaderFloatValue(reader);
        fogDensity = new SerializedShaderFloatValue(reader);
        fogColor = new SerializedShaderVectorValue(reader);
        fogMode = Enums.fogMode(reader.readInt());
        gpuProgramID = reader.readInt();
        m_Tags = new SerializedTagMap(reader);
        m_LOD = reader.readInt();
        lighting = reader.readBoolean();
        reader.alignStream();
    }
}
