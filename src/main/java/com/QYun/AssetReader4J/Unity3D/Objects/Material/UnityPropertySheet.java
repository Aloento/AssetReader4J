package com.QYun.AssetReader4J.Unity3D.Objects.Material;

import com.QYun.AssetReader4J.Unity3D.ObjectReader;
import org.eclipse.collections.api.factory.Lists;
import org.eclipse.collections.api.factory.Maps;
import org.eclipse.collections.api.list.MutableList;
import org.eclipse.collections.api.map.MutableMap;

import javax.vecmath.Color4f;

public class UnityPropertySheet {
    public MutableList<MutableMap<String, UnityTexEnv>> m_TexEnvs;
    public MutableList<MutableMap<String, Integer>> m_Ints;
    public MutableList<MutableMap<String, Float>> m_Floats;
    public MutableList<MutableMap<String, Color4f>> m_Colors;

    public UnityPropertySheet(ObjectReader reader) {
        var version = reader.version();

        int m_TexEnvsSize = reader.ReadInt32();
        m_TexEnvs = Lists.mutable.withInitialCapacity(m_TexEnvsSize);
        for (int i = 0; i < m_TexEnvsSize; i++) {
            MutableMap<String, UnityTexEnv> tmp = Maps.mutable.empty();
            tmp.put(reader.ReadAlignedString(), new UnityTexEnv(reader));
            m_TexEnvs.add(i, tmp);
        }

        if (version[0] >= 2021) { //2021.1 and up
            int m_IntsSize = reader.ReadInt32();
            m_Ints = Lists.mutable.withInitialCapacity(m_IntsSize);
            for (int i = 0; i < m_IntsSize; i++) {
                MutableMap<String, Integer> tmp = Maps.mutable.empty();
                tmp.put(reader.ReadAlignedString(), reader.ReadInt32());
                m_Ints.add(i, tmp);
            }
        }

        int m_FloatsSize = reader.ReadInt32();
        m_Floats = Lists.mutable.withInitialCapacity(m_FloatsSize);
        for (int i = 0; i < m_FloatsSize; i++) {
            MutableMap<String, Float> tmp = Maps.mutable.empty();
            tmp.put(reader.ReadAlignedString(), reader.ReadSingle());
            m_Floats.add(i, tmp);
        }

        int m_ColorsSize = reader.ReadInt32();
        m_Colors = Lists.mutable.withInitialCapacity(m_ColorsSize);
        for (int i = 0; i < m_ColorsSize; i++) {
            MutableMap<String, Color4f> tmp = Maps.mutable.empty();
            tmp.put(reader.ReadAlignedString(), reader.ReadColor4());
            m_Colors.add(i, tmp);
        }
    }
}
