package com.QYun.AssetReader4J.Unity3D.Objects.SpriteAtlas;

import com.QYun.AssetReader4J.Unity3D.Contracts.NamedObject;
import com.QYun.AssetReader4J.Unity3D.ObjectReader;
import com.QYun.AssetReader4J.Unity3D.Objects.PPtr;
import com.QYun.AssetReader4J.Unity3D.Objects.Sprite.Sprite;
import org.apache.commons.lang.ArrayUtils;
import org.eclipse.collections.api.factory.Lists;
import org.eclipse.collections.api.factory.Maps;
import org.eclipse.collections.api.list.MutableList;
import org.eclipse.collections.api.map.MutableMap;

import java.util.UUID;

public class SpriteAtlas extends NamedObject {
    public MutableList<PPtr<Sprite>> m_PackedSprites;
    public MutableMap<MutableMap<UUID, Long>, SpriteAtlasData> m_RenderDataMap;

    public SpriteAtlas(ObjectReader reader) {
        super(reader);
        var m_PackedSpritesSize = reader.ReadInt32();
        m_PackedSprites = Lists.mutable.withInitialCapacity(m_PackedSpritesSize);
        for (int i = 0; i < m_PackedSpritesSize; i++) {
            m_PackedSprites.add(i, new PPtr<>(reader));
        }

        var m_PackedSpriteNamesToIndex = reader.ReadStringArray();

        var m_RenderDataMapSize = reader.ReadInt32();
        m_RenderDataMap = Maps.mutable.withInitialCapacity(m_RenderDataMapSize);
        for (int i = 0; i < m_RenderDataMapSize; i++) {
            var first = UUID.nameUUIDFromBytes(ArrayUtils.toPrimitive(reader.ReadBytes(16)));
            var second = reader.ReadInt64();
            var value = new SpriteAtlasData(reader);

            MutableMap<UUID, Long> tmp = Maps.mutable.empty();
            tmp.put(first, second);
            m_RenderDataMap.put(tmp, value);
        }
        //string m_Tag
        //bool m_IsVariant
    }
}
