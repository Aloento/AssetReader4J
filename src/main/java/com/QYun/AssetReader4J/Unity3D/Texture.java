package com.QYun.AssetReader4J.Unity3D;

public abstract class Texture extends NamedObject {
    protected Texture(UObjectReader reader) {
        super(reader);
        if (version[0] > 2017 || (version[0] == 2017 && version[1] >= 3)) {
            var m_ForcedFallbackFormat = reader.readInt();
            var m_DownscaleFallback = reader.readBoolean();
            if (version[0] > 2020 || (version[0] == 2020 && version[1] >= 2)) {
                var m_IsAlphaChannelOptional = reader.readBoolean();
            }
            reader.alignStream();
        }
    }
}
