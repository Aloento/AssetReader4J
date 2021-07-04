package com.QYun.AssetReader4J.Unity3D.Objects;

import com.QYun.AssetReader4J.Unity3D.ObjectReader;
import com.QYun.AssetReader4J.Unity3D.UObject;
import org.eclipse.collections.api.factory.Lists;
import org.eclipse.collections.api.factory.Maps;
import org.eclipse.collections.api.list.MutableList;
import org.eclipse.collections.api.map.MutableMap;

public class ResourceManager extends UObject {
    public MutableList<MutableMap<String, PPtr<UObject>>> m_Container;

    public ResourceManager(ObjectReader reader) {
        super(reader);
        var m_ContainerSize = reader.ReadInt32();
        m_Container = Lists.mutable.withInitialCapacity(m_ContainerSize);
        for (int i = 0; i < m_ContainerSize; i++) {
            MutableMap<String, PPtr<UObject>> tmp = Maps.mutable.empty();
            tmp.put(reader.ReadAlignedString(), new PPtr<>(reader));
            m_Container.add(i, tmp);
        }
    }
}
