package com.QYun.AssetReader4J.Unity3D.Objects;

import com.QYun.AssetReader4J.Unity3D.ObjectReader;
import com.QYun.AssetReader4J.Unity3D.UObject;

public class BuildSettings extends UObject {
    public String m_Version;

    public BuildSettings(ObjectReader reader) {
        super(reader);
        var levels = reader.ReadStringArray();

        var hasRenderTexture = reader.ReadBoolean();
        var hasPROVersion = reader.ReadBoolean();
        var hasPublishingRights = reader.ReadBoolean();
        var hasShadows = reader.ReadBoolean();

        m_Version = reader.ReadAlignedString();
    }
}
