package com.QYun.AssetReader4J.Unity3D.Objects.Mesh;

import com.QYun.AssetReader4J.Unity3D.Contracts.NamedObject;
import com.QYun.AssetReader4J.Unity3D.ObjectReader;
import com.QYun.AssetReader4J.Unity3D.Objects.Texture2D.StreamingInfo;
import org.eclipse.collections.api.factory.Lists;
import org.eclipse.collections.api.list.MutableList;

import javax.vecmath.Matrix4f;

public class Mesh extends NamedObject {
    public SubMesh[] m_SubMeshes;
    public BlendShapeData m_Shapes;
    public Matrix4f[] m_BindPose;
    public Integer[] m_BoneNameHashes;
    public int m_VertexCount;
    public Float[] m_Vertices;
    public BoneWeights4[] m_Skin;
    public Float[] m_Normals;
    public float[] m_Colors;
    public Float[] m_UV0;
    public Float[] m_UV1;
    public float[] m_UV2;
    public float[] m_UV3;
    public float[] m_UV4;
    public float[] m_UV5;
    public float[] m_UV6;
    public float[] m_UV7;
    public Float[] m_Tangents;
    public MutableList<Integer> m_Indices = Lists.mutable.empty();
    private boolean m_Use16BitIndices = true;
    private Integer[] m_IndexBuffer;
    private VertexData m_VertexData;
    private CompressedMesh m_CompressedMesh;
    private StreamingInfo m_StreamData;

    public Mesh(ObjectReader reader) {
        super(reader);
        if (version[0] < 3 || (version[0] == 3 && version[1] < 5)) { //3.5 down
            m_Use16BitIndices = reader.readInt() > 0;
        }

        if (version[0] == 2 && version[1] <= 5) { //2.5 and down
            int m_IndexBuffer_size = reader.readInt();

            if (m_Use16BitIndices) {
                m_IndexBuffer = new Integer[m_IndexBuffer_size / 2];
                for (int i = 0; i < m_IndexBuffer_size / 2; i++) {
                    m_IndexBuffer[i] = Integer.valueOf(reader.readShort());
                }
                reader.alignStream();
            } else {
                m_IndexBuffer = reader.readIntArray(m_IndexBuffer_size / 4);
            }
        }

        int m_SubMeshesSize = reader.readInt();
        m_SubMeshes = new SubMesh[m_SubMeshesSize];
        for (int i = 0; i < m_SubMeshesSize; i++) {
            m_SubMeshes[i] = new SubMesh(reader);
        }

        if (version[0] > 4 || (version[0] == 4 && version[1] >= 1)) { //4.1 and up
            m_Shapes = new BlendShapeData(reader);
        }

        if (version[0] > 4 || (version[0] == 4 && version[1] >= 3)) { //4.3 and up
            m_BindPose = reader.readMatrixArray();
            m_BoneNameHashes = reader.readIntArray();
            var m_RootBoneNameHash = reader.readInt();
        }

        if (version[0] > 2 || (version[0] == 2 && version[1] >= 6)) { //2.6.0 and up
            if (version[0] >= 2019) { //2019 and up
                var m_BonesAABBSize = reader.readInt();
                var m_BonesAABB = new MinMaxAABB[m_BonesAABBSize];
                for (int i = 0; i < m_BonesAABBSize; i++) {
                    m_BonesAABB[i] = new MinMaxAABB(reader);
                }

                var m_VariableBoneCountWeights = reader.readIntArray();
            }

            var m_MeshCompression = reader.readByte();
            if (version[0] >= 4) {
                if (version[0] < 5) {
                    var m_StreamCompression = reader.readByte();
                }
                var m_IsReadable = reader.readBoolean();
                var m_KeepVertices = reader.readBoolean();
                var m_KeepIndices = reader.readBoolean();
            }
            reader.alignStream();

            //Unity fixed it in 2017.3.1p1 and later versions
            if ((version[0] > 2017 || (version[0] == 2017 && version[1] >= 4)) || //2017.4
                    ((version[0] == 2017 && version[1] == 3 && version[2] == 1) && buildType.isPatch()) || //fixed after 2017.3.1px
                    ((version[0] == 2017 && version[1] == 3) && m_MeshCompression == 0))//2017.3.xfx with no compression
            {
                var m_IndexFormat = reader.readInt();
                m_Use16BitIndices = m_IndexFormat == 0;
            }

            int m_IndexBuffer_size = reader.readInt();
            if (m_Use16BitIndices) {
                m_IndexBuffer = new Integer[m_IndexBuffer_size / 2];
                for (int i = 0; i < m_IndexBuffer_size / 2; i++) {
                    m_IndexBuffer[i] = Integer.valueOf(reader.readShort());
                }
                reader.alignStream();
            } else {
                m_IndexBuffer = reader.readIntArray(m_IndexBuffer_size / 4);
            }
        }

        if (version[0] < 3 || (version[0] == 3 && version[1] < 5)) { //3.4.2 and earlier
            m_VertexCount = reader.readInt();
            m_Vertices = reader.readFloatArray(m_VertexCount * 3); //Vector3

            m_Skin = new BoneWeights4[reader.readInt()];
            for (int s = 0; s < m_Skin.length; s++) {
                m_Skin[s] = new BoneWeights4(reader);
            }

            m_BindPose = reader.readMatrixArray();

            m_UV0 = reader.readFloatArray(reader.readInt() * 2); //Vector2

            m_UV1 = reader.readFloatArray(reader.readInt() * 2); //Vector2

            if (version[0] == 2 && version[1] <= 5) { //2.5 and down
                int m_TangentSpace_size = reader.readInt();
                m_Normals = new Float[m_TangentSpace_size * 3];
                m_Tangents = new Float[m_TangentSpace_size * 4];
                for (int v = 0; v < m_TangentSpace_size; v++) {
                    m_Normals[v * 3] = reader.readFloat();
                    m_Normals[v * 3 + 1] = reader.readFloat();
                    m_Normals[v * 3 + 2] = reader.readFloat();
                    m_Tangents[v * 3] = reader.readFloat();
                    m_Tangents[v * 3 + 1] = reader.readFloat();
                    m_Tangents[v * 3 + 2] = reader.readFloat();
                    m_Tangents[v * 3 + 3] = reader.readFloat(); //handedness
                }
            } else { //2.6.0 and later
                m_Tangents = reader.readFloatArray(reader.readInt() * 4); //Vector4

                m_Normals = reader.readFloatArray(reader.readInt() * 3); //Vector3
            }
        } else {
            if (version[0] < 2018 || (version[0] == 2018 && version[1] < 2)) { //2018.2 down
                m_Skin = new BoneWeights4[reader.readInt()];
                for (int s = 0; s < m_Skin.length; s++) {
                    m_Skin[s] = new BoneWeights4(reader);
                }
            }

            if (version[0] == 3 || (version[0] == 4 && version[1] <= 2)) { //4.2 and down
                m_BindPose = reader.readMatrixArray();
            }

            m_VertexData = new VertexData(reader);
        }

        if (version[0] > 2 || (version[0] == 2 && version[1] >= 6)) { //2.6.0 and later
            m_CompressedMesh = new CompressedMesh(reader);
        }

        reader.setPos(reader.getPos() + 24); //AABB m_LocalAABB

        if (version[0] < 3 || (version[0] == 3 && version[1] <= 4)) { //3.4.2 and earlier
            int m_Colors_size = reader.readInt();
            m_Colors = new float[m_Colors_size * 4];
            for (int v = 0; v < m_Colors_size * 4; v++) {
                m_Colors[v] = (float) reader.readByte() / 0xFF;
            }

            int m_CollisionTriangles_size = reader.readInt();
            reader.setPos(reader.getPos() + m_CollisionTriangles_size * 4); //UInt32 indices
            int m_CollisionVertexCount = reader.readInt();
        }

        int m_MeshUsageFlags = reader.readInt();

        if (version[0] >= 5) { //5.0 and up
            var m_BakedConvexCollisionMesh = reader.readByteArray();
            reader.alignStream();
            var m_BakedTriangleCollisionMesh = reader.readByteArray();
            reader.alignStream();
        }

        if (version[0] > 2018 || (version[0] == 2018 && version[1] >= 2)) { //2018.2 and up
            var m_MeshMetrics = new float[2];
            m_MeshMetrics[0] = reader.readFloat();
            m_MeshMetrics[1] = reader.readFloat();
        }

        if (version[0] > 2018 || (version[0] == 2018 && version[1] >= 3)) { //2018.3 and up
            reader.alignStream();
            m_StreamData = new StreamingInfo(reader);
        }

        ProcessData();
    }

    private void ProcessData() {
    }
}
