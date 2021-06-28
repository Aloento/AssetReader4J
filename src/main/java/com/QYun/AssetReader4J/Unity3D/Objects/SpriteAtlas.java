package com.QYun.AssetReader4J.Unity3D.Objects;

import com.QYun.AssetReader4J.Unity3D.Contracts.NamedObject;
import com.QYun.AssetReader4J.Unity3D.PPtr;
import com.QYun.AssetReader4J.Unity3D.SpriteAtlasData;
import com.QYun.AssetReader4J.Unity3D.UObjectReader;

import java.util.HashMap;
import java.util.Hashtable;
import java.util.UUID;

public class SpriteAtlas extends NamedObject {
    public PPtr<Sprite>[] m_PackedSprites;
    public Hashtable<HashMap<UUID, long>, SpriteAtlasData> m_RenderDataMap;

    public SpriteAtlas(UObjectReader reader) {
        super(reader);

    }
}
