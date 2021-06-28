package com.QYun.AssetReader4J.Unity3D.Contracts;

import com.QYun.AssetReader4J.Unity3D.UObjectReader;

public abstract class Behaviour extends Component {
    public byte m_Enabled;

    protected Behaviour(UObjectReader reader) {
        super(reader);
        m_Enabled = reader.readByte();
        reader.alignStream();
    }
}
