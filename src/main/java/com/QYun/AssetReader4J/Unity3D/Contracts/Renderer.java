package com.QYun.AssetReader4J.Unity3D.Contracts;

import com.QYun.AssetReader4J.Unity3D.ObjectReader;
import com.QYun.AssetReader4J.Unity3D.Objects.GameObject;
import com.QYun.AssetReader4J.Unity3D.Objects.Material.Material;
import com.QYun.AssetReader4J.Unity3D.Objects.PPtr;
import com.QYun.AssetReader4J.Unity3D.Objects.Transform;
import org.eclipse.collections.api.factory.Lists;
import org.eclipse.collections.api.list.MutableList;

public abstract class Renderer extends Component {
    public MutableList<PPtr<Material>> m_Materials;
    public StaticBatchInfo m_StaticBatchInfo;
    public Integer[] m_SubsetIndices;

    protected Renderer(ObjectReader reader) {
        super(reader);
        if (version[0] < 5) {
            var m_Enabled = reader.readBoolean();
            var m_CastShadows = reader.readBoolean();
            var m_ReceiveShadows = reader.readBoolean();
            var m_LightmapIndex = reader.readByte();
        } else {
            if (version[0] > 5 || (version[0] == 5 && version[1] >= 4)) { //5.4 and up
                var m_Enabled = reader.readBoolean();
                var m_CastShadows = reader.readByte();
                var m_ReceiveShadows = reader.readByte();
                if (version[0] > 2017 || (version[0] == 2017 && version[1] >= 2)) {//2017.2 and up
                    var m_DynamicOccludee = reader.readByte();
                }
                if (version[0] >= 2021) //2021.1 and up
                {
                    var m_StaticShadowCaster = reader.readByte();
                }
                var m_MotionVectors = reader.readByte();
                var m_LightProbeUsage = reader.readByte();
                var m_ReflectionProbeUsage = reader.readByte();
                if (version[0] > 2019 || (version[0] == 2019 && version[1] >= 3)) {//2019.3 and up
                    var m_RayTracingMode = reader.readByte();
                }
                if (version[0] >= 2020) {//2020.1 and up
                    var m_RayTraceProcedural = reader.readByte();
                }
                reader.AlignStream();
            } else {
                var m_Enabled = reader.readBoolean();
                reader.AlignStream();
                var m_CastShadows = reader.readByte();
                var m_ReceiveShadows = reader.readBoolean();
                reader.AlignStream();
            }

            if (version[0] >= 2018) { //2018 and up
                var m_RenderingLayerMask = reader.readInt();
            }

            if (version[0] > 2018 || (version[0] == 2018 && version[1] >= 3)) { //2018.3 and up
                var m_RendererPriority = reader.readInt();
            }

            var m_LightmapIndex = reader.readShort();
            var m_LightmapIndexDynamic = reader.readShort();
        }

        if (version[0] >= 3) { //3.0 and up
            var m_LightmapTilingOffset = reader.ReadVector4();
        }

        if (version[0] >= 5) { //5.0 and up
            var m_LightmapTilingOffsetDynamic = reader.ReadVector4();
        }

        var m_MaterialsSize = reader.readInt();
        m_Materials = Lists.mutable.withInitialCapacity(m_MaterialsSize);
        for (int i = 0; i < m_MaterialsSize; i++) {
            m_Materials.add(i, new PPtr<>(reader, Material.class));
        }

        if (version[0] < 3) { //3.0 down
            var m_LightmapTilingOffset = reader.ReadVector4();
        } else { //3.0 and up
            if (version[0] > 5 || (version[0] == 5 && version[1] >= 5)) { //5.5 and up
                m_StaticBatchInfo = new StaticBatchInfo(reader);
            } else {
                m_SubsetIndices = reader.readInts(reader.readInt());
            }

            var m_StaticBatchRoot = new PPtr<>(reader, Transform.class);
        }

        if (version[0] > 5 || (version[0] == 5 && version[1] >= 4)) { //5.4 and up
            var m_ProbeAnchor = new PPtr<>(reader, Transform.class);
            var m_LightProbeVolumeOverride = new PPtr<>(reader, GameObject.class);
        } else if (version[0] > 3 || (version[0] == 3 && version[1] >= 5)) { //3.5 - 5.3
            var m_UseLightProbes = reader.readBoolean();
            reader.AlignStream();

            if (version[0] >= 5) { //5.0 and up
                var m_ReflectionProbeUsage = reader.readInt();
            }

            var m_LightProbeAnchor = new PPtr<>(reader, Transform.class); //5.0 and up m_ProbeAnchor
        }

        if (version[0] > 4 || (version[0] == 4 && version[1] >= 3)) { //4.3 and up
            if (version[0] == 4 && version[1] == 3) { //4.3
                var m_SortingLayer = reader.readShort();
            } else {
                var m_SortingLayerID = reader.readInt();
            }

            //SInt16 m_SortingLayer 5.6 and up
            var m_SortingOrder = reader.readShort();
            reader.AlignStream();
        }
    }
}
