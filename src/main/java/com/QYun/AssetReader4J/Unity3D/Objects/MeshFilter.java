package com.QYun.AssetReader4J.Unity3D.Objects;

import com.QYun.AssetReader4J.Unity3D.Contracts.Component;
import com.QYun.AssetReader4J.Unity3D.ObjectReader;
import com.QYun.AssetReader4J.Unity3D.Objects.Mesh.Mesh;

public class MeshFilter extends Component {
    public PPtr<Mesh> m_Mesh;

    public MeshFilter(ObjectReader reader) {
        super(reader);
        m_Mesh = new PPtr<>(reader);
    }
}
