package com.QYun.AssetReader4J.Unity3D.Objects.Shader;

import com.QYun.AssetReader4J.Unity3D.ObjectReader;

public class SerializedProperties {
    public SerializedProperty[] m_Props;

    public SerializedProperties(ObjectReader reader) {
        int numProps = reader.readInt();
        m_Props = new SerializedProperty[numProps];
        for (int i = 0; i < numProps; i++) {
            m_Props[i] = new SerializedProperty(reader);
        }
    }
}
