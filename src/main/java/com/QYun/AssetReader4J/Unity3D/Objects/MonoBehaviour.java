package com.QYun.AssetReader4J.Unity3D.Objects;

import com.QYun.AssetReader4J.Unity3D.Contracts.Behaviour;
import com.QYun.AssetReader4J.Unity3D.ObjectReader;

public class MonoBehaviour extends Behaviour {
    public PPtr<MonoScript> m_Script;
    public String m_Name;

    public MonoBehaviour(ObjectReader reader) {
        super(reader);
        m_Script = new PPtr<>(reader, MonoScript.class);
        m_Name = reader.readAlignedString();
    }
}
