package com.QYun.AssetReader4J.Unity3D.Objects.Avatar;

import com.QYun.AssetReader4J.Unity3D.ObjectReader;

public class Limit {
    public Object m_Min;
    public Object m_Max;

    public Limit(ObjectReader reader) {
        var version = reader.version();
        if (version[0] > 5 || (version[0] == 5 && version[1] >= 4)) { //5.4 and up
            m_Min = reader.readVector3();
            m_Max = reader.readVector3();
        } else {
            m_Min = reader.readVector4();
            m_Max = reader.readVector4();
        }
    }
}
