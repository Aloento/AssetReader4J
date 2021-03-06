package com.QYun.AssetReader4J.Unity3D.Objects;

import com.QYun.AssetReader4J.Unity3D.Contracts.Component;
import com.QYun.AssetReader4J.Unity3D.Contracts.EditorExtension;
import com.QYun.AssetReader4J.Unity3D.ObjectReader;
import org.eclipse.collections.api.factory.Lists;
import org.eclipse.collections.api.list.MutableList;

public class GameObject extends EditorExtension {
    public MutableList<PPtr<Component>> m_Components;
    public String m_Name;

    public Transform m_Transform;
    public MeshRenderer m_MeshRenderer;
    public MeshFilter m_MeshFilter;
    public SkinnedMeshRenderer m_SkinnedMeshRenderer;
    public Animator m_Animator;
    public Animation m_Animation;

    public GameObject(ObjectReader reader) {
        super(reader);
        int m_Component_size = reader.readInt();
        m_Components = Lists.mutable.withInitialCapacity(m_Component_size);

        for (int i = 0; i < m_Component_size; i++) {
            int first;
            if ((version[0] == 5 && version[1] < 5) || version[0] < 5)
                first = reader.readInt();
            m_Components.add(i, new PPtr<>(reader, Component.class));
        }

        var m_Layer = reader.readInt();
        m_Name = reader.readAlignedString();
    }
}
