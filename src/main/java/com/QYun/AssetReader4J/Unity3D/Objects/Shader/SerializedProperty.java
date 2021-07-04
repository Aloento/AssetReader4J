package com.QYun.AssetReader4J.Unity3D.Objects.Shader;

import com.QYun.AssetReader4J.Entities.Enums;
import com.QYun.AssetReader4J.Entities.Enums.SerializedPropertyType;
import com.QYun.AssetReader4J.Unity3D.ObjectReader;

public class SerializedProperty {
    public String m_Name;
    public String m_Description;
    public String[] m_Attributes;
    public SerializedPropertyType m_Type;
    public int m_Flags;
    public Float[] m_DefValue;
    public SerializedTextureProperty m_DefTexture;

    public SerializedProperty(ObjectReader reader) {
        m_Name = reader.readAlignedString();
        m_Description = reader.readAlignedString();
        m_Attributes = reader.readStringArray();
        m_Type = Enums.serializedPropertyType(reader.readInt());
        m_Flags = reader.readInt();
        m_DefValue = reader.readFloatArray(4);
        m_DefTexture = new SerializedTextureProperty(reader);
    }
}
