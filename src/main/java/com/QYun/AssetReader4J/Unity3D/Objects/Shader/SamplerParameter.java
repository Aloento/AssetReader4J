package com.QYun.AssetReader4J.Unity3D.Objects.Shader;

import com.QYun.AssetReader4J.Unity3D.ObjectReader;

public class SamplerParameter {
    public int sampler;
    public int bindPoint;

    public SamplerParameter(ObjectReader reader) {
        sampler = reader.readInt();
        bindPoint = reader.readInt();
    }
}
