package com.QYun.AssetReader4J.Unity3D.Objects.Shader;

import com.QYun.AssetReader4J.Entities.Enums;
import com.QYun.AssetReader4J.Entities.Enums.TextureDimension;
import com.QYun.AssetReader4J.Unity3D.ObjectReader;

public class SerializedTextureProperty {
    public String m_DefaultName;
    public TextureDimension m_TexDim;

    public SerializedTextureProperty(ObjectReader reader) {
        m_DefaultName = reader.readAlignedString();
        m_TexDim = Enums.textureDimension(reader.readInt());
    }
}
