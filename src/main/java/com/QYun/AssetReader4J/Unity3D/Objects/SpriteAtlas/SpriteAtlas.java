package com.QYun.AssetReader4J.Unity3D.Objects.SpriteAtlas;

import com.QYun.AssetReader4J.Unity3D.Contracts.NamedObject;
import com.QYun.AssetReader4J.Unity3D.ObjectReader;
import com.QYun.AssetReader4J.Unity3D.Objects.PPtr;
import com.QYun.AssetReader4J.Unity3D.Objects.Sprite.Sprite;
import org.eclipse.collections.api.map.MutableMap;

import java.util.UUID;

public class SpriteAtlas extends NamedObject {
    public PPtr<Sprite>[] m_PackedSprites;
    public MutableMap<MutableMap<UUID, Long>, SpriteAtlasData> m_RenderDataMap;

    public SpriteAtlas(ObjectReader reader) {
        super(reader);

    }
}
