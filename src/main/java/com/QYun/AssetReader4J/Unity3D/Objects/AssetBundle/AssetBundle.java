package com.QYun.AssetReader4J.Unity3D.Objects.AssetBundle;

import com.QYun.AssetReader4J.Unity3D.Contracts.NamedObject;
import com.QYun.AssetReader4J.Unity3D.ObjectReader;
import com.QYun.AssetReader4J.Unity3D.Objects.PPtr;
import com.QYun.AssetReader4J.Unity3D.UObject;
import org.eclipse.collections.api.factory.Lists;
import org.eclipse.collections.api.factory.Maps;
import org.eclipse.collections.api.list.MutableList;
import org.eclipse.collections.api.map.MutableMap;

public class AssetBundle extends NamedObject {
    public MutableList<PPtr<UObject>> m_PreloadTable;
    public MutableList<MutableMap<String, AssetInfo>> m_Container;

    public AssetBundle(ObjectReader reader) {
        super(reader);
        var m_PreloadTableSize = reader.readInt();
        m_PreloadTable = Lists.mutable.withInitialCapacity(m_PreloadTableSize);
        for (int i = 0; i < m_PreloadTableSize; i++) {
            m_PreloadTable.add(i, new PPtr<>(reader, UObject.class));
        }

        var m_ContainerSize = reader.readInt();
        m_Container = Lists.mutable.withInitialCapacity(m_ContainerSize);
        for (int i = 0; i < m_ContainerSize; i++) {
            MutableMap<String, AssetInfo> tmp = Maps.mutable.empty();
            tmp.put(reader.readAlignedString(), new AssetInfo(reader));
            m_Container.add(i, tmp);
        }
    }
}
