package com.QYun.AssetReader4J.Unity3D.Objects;

import com.QYun.AssetReader4J.Unity3D.Contracts.Component;
import com.QYun.AssetReader4J.Unity3D.ObjectReader;

import javax.vecmath.Quat4f;
import javax.vecmath.Vector3f;

public class Transform extends Component {
    public Quat4f m_LocalRotation;
    public Vector3f m_LocalPosition;
    public Vector3f m_LocalScale;
    public PPtr<Transform>[] m_Children;
    public PPtr<Transform> m_Father;

    public Transform(ObjectReader reader) {
        super(reader);
        // TODO
    }
}
