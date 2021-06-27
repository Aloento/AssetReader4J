package com.QYun.AssetReader4J.Unity3D;

import java.util.ArrayList;

public class GameObject extends EditorExtension {
    public ArrayList<PPtr<Component>> m_Components;
    public String m_Name;

    public Transform m_Transform;
    public MeshRenderer m_MeshRenderer;
    public MeshFilter m_MeshFilter;
    public SkinnedMeshRenderer m_SkinnedMeshRenderer;
    public Animator m_Animator;
    public Animation m_Animation;

    public GameObject(UObjectReader reader) {
        super(reader);
        int m_Component_size = reader.readInt();
        m_Components = new ArrayList<>(m_Component_size);

        for (int i = 0; i < m_Component_size; i++) {
            int first;
            if ((version[0] == 5 && version[1] < 5) || version[0] < 5)
                first = reader.readInt();
            m_Components.set(i, new PPtr<>(reader));
        }

        var m_Layer = reader.readInt();
        m_Name = reader.readAlignedString();
    }
}
