package com.QYun.AssetReader4J.Unity3D;

public class MeshFilter extends Component {
    public PPtr<Mesh> m_Mesh;

    public MeshFilter(UObjectReader reader) {
        super(reader);
        m_Mesh = new PPtr<>(reader);
    }
}
