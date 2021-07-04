package com.QYun.AssetReader4J.Unity3D.Objects.Shader;

import com.QYun.AssetReader4J.Unity3D.ObjectReader;

public class Hash128 {
    public Byte[] bytes;

    public Hash128(ObjectReader reader)
    {
        bytes = reader.readBytes(16);
    }
}
