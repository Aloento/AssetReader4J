package com.QYun.AssetReader4J.Unity3D.Objects.Shader;

import com.QYun.AssetReader4J.Unity3D.ObjectReader;
import org.eclipse.collections.api.factory.Lists;
import org.eclipse.collections.api.factory.Maps;
import org.eclipse.collections.api.list.MutableList;
import org.eclipse.collections.api.map.MutableMap;

public class SerializedTagMap {
    public MutableList<MutableMap<String, String>> tags;

    public SerializedTagMap(ObjectReader reader) {
        int numTags = reader.ReadInt32();
        tags = Lists.mutable.withInitialCapacity(numTags);
        for (int i = 0; i < numTags; i++) {
            MutableMap<String, String> tmp = Maps.mutable.empty();
            tmp.put(reader.ReadAlignedString(), reader.ReadAlignedString());
            tags.add(i, tmp);
        }
    }
}
