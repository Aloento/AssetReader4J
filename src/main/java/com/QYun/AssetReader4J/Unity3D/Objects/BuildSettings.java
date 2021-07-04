package com.QYun.AssetReader4J.Unity3D.Objects;

import com.QYun.AssetReader4J.Unity3D.ObjectReader;
import com.QYun.AssetReader4J.Unity3D.UObject;

public class BuildSettings extends UObject {
    public String m_Version;

    public BuildSettings(ObjectReader reader) {
        super(reader);
        var levels = reader.readStringArray();

        var hasRenderTexture = reader.readBoolean();
        var hasPROVersion = reader.readBoolean();
        var hasPublishingRights = reader.readBoolean();
        var hasShadows = reader.readBoolean();

        m_Version = reader.readAlignedString();
    }
}
