package com.QYun.AssetReader4J.Unity3D.Objects.Mesh;

import com.QYun.AssetReader4J.Unity3D.ObjectReader;

public class BlendShapeData {
    public BlendShapeVertex[] vertices;
    public MeshBlendShape[] shapes;
    public MeshBlendShapeChannel[] channels;
    public Float[] fullWeights;

    public BlendShapeData(ObjectReader reader) {
        var version = reader.version();

        if (version[0] > 4 || (version[0] == 4 && version[1] >= 3)) { //4.3 and up
            int numVerts = reader.readInt();
            vertices = new BlendShapeVertex[numVerts];
            for (int i = 0; i < numVerts; i++) {
                vertices[i] = new BlendShapeVertex(reader);
            }

            int numShapes = reader.readInt();
            shapes = new MeshBlendShape[numShapes];
            for (int i = 0; i < numShapes; i++) {
                shapes[i] = new MeshBlendShape(reader);
            }

            int numChannels = reader.readInt();
            channels = new MeshBlendShapeChannel[numChannels];
            for (int i = 0; i < numChannels; i++) {
                channels[i] = new MeshBlendShapeChannel(reader);
            }

            fullWeights = reader.readFloatArray();
        } else {
            var m_ShapesSize = reader.readInt();
            var m_Shapes = new MeshBlendShape[m_ShapesSize];
            for (int i = 0; i < m_ShapesSize; i++) {
                m_Shapes[i] = new MeshBlendShape(reader);
            }
            reader.alignStream();
            var m_ShapeVerticesSize = reader.readInt();
            var m_ShapeVertices = new BlendShapeVertex[m_ShapeVerticesSize]; //MeshBlendShapeVertex
            for (int i = 0; i < m_ShapeVerticesSize; i++) {
                m_ShapeVertices[i] = new BlendShapeVertex(reader);
            }
        }
    }
}
