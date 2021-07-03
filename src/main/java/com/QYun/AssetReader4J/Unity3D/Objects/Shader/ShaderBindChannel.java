package com.QYun.AssetReader4J.Unity3D.Objects.Shader;

import com.QYun.AssetReader4J.Unity3D.ObjectReader;

public class ShaderBindChannel {
    public byte source;
    public byte target;

    public ShaderBindChannel(ObjectReader reader)
    {
        source = reader.readByte(); //source = reader.ReadSByte();
        target = reader.readByte(); //target = reader.ReadSByte();
    }
}
