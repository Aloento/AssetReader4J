package com.QYun.AssetReader4J.Unity3D.Objects.Avatar;

import com.QYun.AssetReader4J.Unity3D.Contracts.NamedObject;
import com.QYun.AssetReader4J.Unity3D.ObjectReader;
import org.eclipse.collections.api.factory.Lists;
import org.eclipse.collections.api.factory.Maps;
import org.eclipse.collections.api.list.MutableList;
import org.eclipse.collections.api.map.MutableMap;

public class Avatar extends NamedObject {
    public int m_AvatarSize;
    public AvatarConstant m_Avatar;
    public MutableList<MutableMap<Integer, String>> m_TOS;

    public Avatar(ObjectReader reader) {
        super(reader);
        m_AvatarSize = reader.readInt();
        m_Avatar = new AvatarConstant(reader);

        int numTOS = reader.readInt();
        m_TOS = Lists.mutable.withInitialCapacity(numTOS);
        for (int i = 0; i < numTOS; i++) {
            MutableMap<Integer, String> tmp = Maps.mutable.empty();
            tmp.put(reader.readInt(), reader.readAlignedString());
            m_TOS.add(i, tmp);
        }

        //HumanDescription m_HumanDescription 2019 and up
    }
}
