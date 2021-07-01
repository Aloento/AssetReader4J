package com.QYun.AssetReader4J.Unity3D.Objects;

import com.QYun.AssetReader4J.Unity3D.Contracts.Component;
import com.QYun.AssetReader4J.Unity3D.Objects.Mesh.Mesh;
import com.QYun.AssetReader4J.Unity3D.PPtr;
import com.QYun.AssetReader4J.Unity3D.UObjectReader;

public class MeshFilter extends Component {
    public PPtr<Mesh> m_Mesh;

    public MeshFilter(UObjectReader reader) {
        super(reader);
        m_Mesh = new PPtr<>(reader);
    }
}
