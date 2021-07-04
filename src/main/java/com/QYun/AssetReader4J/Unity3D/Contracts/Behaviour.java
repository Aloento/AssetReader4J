package com.QYun.AssetReader4J.Unity3D.Contracts;

import com.QYun.AssetReader4J.Unity3D.ObjectReader;

public abstract class Behaviour extends Component {
    public byte m_Enabled;

    protected Behaviour(ObjectReader reader) {
        super(reader);
        m_Enabled = reader.readByte();
        reader.alignStream();
    }
}
