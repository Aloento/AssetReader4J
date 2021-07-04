package com.QYun.AssetReader4J.Unity3D.Objects;

import com.QYun.AssetReader4J.Unity3D.Contracts.NamedObject;
import com.QYun.AssetReader4J.Unity3D.ObjectReader;

public class TextAsset extends NamedObject {
    public Byte[] m_Script;

    public TextAsset(ObjectReader reader) {
        super(reader);
        m_Script = reader.readByteArray();
    }
}
