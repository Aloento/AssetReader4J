package com.QYun.AssetReader4J.Unity3D.Objects.Shader;

import com.QYun.AssetReader4J.Unity3D.ObjectReader;

public class StructParameter {
    public MatrixParameter[] m_MatrixParams;
    public VectorParameter[] m_VectorParams;

    public StructParameter(ObjectReader reader) {
        var m_NameIndex = reader.readInt();
        var m_Index = reader.readInt();
        var m_ArraySize = reader.readInt();
        var m_StructSize = reader.readInt();

        int numVectorParams = reader.readInt();
        m_VectorParams = new VectorParameter[numVectorParams];
        for (int i = 0; i < numVectorParams; i++) {
            m_VectorParams[i] = new VectorParameter(reader);
        }

        int numMatrixParams = reader.readInt();
        m_MatrixParams = new MatrixParameter[numMatrixParams];
        for (int i = 0; i < numMatrixParams; i++) {
            m_MatrixParams[i] = new MatrixParameter(reader);
        }
    }
}
