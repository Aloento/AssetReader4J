package com.QYun.AssetReader4J.Unity3D.Objects;

import com.QYun.AssetReader4J.Unity3D.Contracts.NamedObject;
import com.QYun.AssetReader4J.Unity3D.UObjectReader;

public class MonoScript extends NamedObject {
    public String m_ClassName;
    public String m_Namespace;
    public String m_AssemblyName;

    public MonoScript(UObjectReader reader) {
        super(reader);
        if (version[0] > 3 || (version[0] == 3 && version[1] >= 4)) {//3.4 and up
            var m_ExecutionOrder = reader.readInt();
        }
        if (version[0] < 5) {//5.0 down
            var m_PropertiesHash = reader.readInt();
        } else {
            var m_PropertiesHash = reader.readBytes(16);
        }
        if (version[0] < 3) {//3.0 down
            var m_PathName = reader.readAlignedString();
        }
        m_ClassName = reader.readAlignedString();
        if (version[0] >= 3) { //3.0 and up
            m_Namespace = reader.readAlignedString();
        }
        m_AssemblyName = reader.readAlignedString();
        if (version[0] < 2018 || (version[0] == 2018 && version[1] < 2)) {//2018.2 down
            var m_IsEditorScript = reader.readBoolean();
        }
    }
}
