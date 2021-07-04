package com.QYun.AssetReader4J.Unity3D.Objects;

import com.QYun.AssetReader4J.Unity3D.Contracts.Component;
import com.QYun.AssetReader4J.Unity3D.ObjectReader;
import org.eclipse.collections.api.factory.Lists;
import org.eclipse.collections.api.list.MutableList;

import javax.vecmath.Quat4f;
import javax.vecmath.Vector3f;

public class Transform extends Component {
    public Quat4f m_LocalRotation;
    public Vector3f m_LocalPosition;
    public Vector3f m_LocalScale;
    public MutableList<PPtr<Transform>> m_Children;
    public PPtr<Transform> m_Father;

    public Transform(ObjectReader reader) {
        super(reader);
        m_LocalRotation = reader.ReadQuaternion();
        m_LocalPosition = reader.ReadVector3();
        m_LocalScale = reader.ReadVector3();

        int m_ChildrenCount = reader.ReadInt32();
        m_Children = Lists.mutable.withInitialCapacity(m_ChildrenCount);
        for (int i = 0; i < m_ChildrenCount; i++) {
            m_Children.add(i, new PPtr<>(reader, Transform.class));
        }
        m_Father = new PPtr<>(reader, Transform.class);
    }
}
