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
        m_Name = reader.ReadAlignedString();
        m_Description = reader.ReadAlignedString();
        m_Attributes = reader.ReadStringArray();
        m_Type = Enums.serializedPropertyType(reader.ReadInt32());
        m_Flags = reader.ReadUInt32();
        m_DefValue = reader.ReadSingleArray(4);
        m_DefTexture = new SerializedTextureProperty(reader);
    }
}
