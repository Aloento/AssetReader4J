package com.QYun.AssetReader4J.Unity3D.Objects.Sprite;

import com.QYun.AssetReader4J.Unity3D.ObjectReader;
import com.QYun.AssetReader4J.Unity3D.Objects.Mesh.BoneWeights4;
import com.QYun.AssetReader4J.Unity3D.Objects.Mesh.SubMesh;
import com.QYun.AssetReader4J.Unity3D.Objects.Mesh.VertexData;
import com.QYun.AssetReader4J.Unity3D.Objects.PPtr;
import com.QYun.AssetReader4J.Unity3D.Objects.Texture2D.Texture2D;

import javax.vecmath.Matrix4f;
import javax.vecmath.Vector2f;
import javax.vecmath.Vector4f;

public class SpriteRenderData {
    public PPtr<Texture2D> texture;
    public PPtr<Texture2D> alphaTexture;
    public SecondarySpriteTexture[] secondaryTextures;
    public SubMesh[] m_SubMeshes;
    public Byte[] m_IndexBuffer;
    public VertexData m_VertexData;
    public SpriteVertex[] vertices;
    public Short[] indices;
    public Matrix4f[] m_Bindpose;
    public BoneWeights4[] m_SourceSkin;
    public Rectf textureRect;
    public Vector2f textureRectOffset;
    public Vector2f atlasRectOffset;
    public SpriteSettings settingsRaw;
    public Vector4f uvTransform;
    public float downscaleMultiplier;

    public SpriteRenderData(ObjectReader reader) {
        var version = reader.version();

        texture = new PPtr<>(reader);
        if (version[0] > 5 || (version[0] == 5 && version[1] >= 2)) { //5.2 and up
            alphaTexture = new PPtr<>(reader);
        }

        if (version[0] >= 2019) { //2019 and up
            var secondaryTexturesSize = reader.readInt();
            secondaryTextures = new SecondarySpriteTexture[secondaryTexturesSize];
            for (int i = 0; i < secondaryTexturesSize; i++) {
                secondaryTextures[i] = new SecondarySpriteTexture(reader);
            }
        }

        if (version[0] > 5 || (version[0] == 5 && version[1] >= 6)) { //5.6 and up
            var m_SubMeshesSize = reader.readInt();
            m_SubMeshes = new SubMesh[m_SubMeshesSize];
            for (int i = 0; i < m_SubMeshesSize; i++) {
                m_SubMeshes[i] = new SubMesh(reader);
            }

            m_IndexBuffer = reader.readBytes(reader.readInt());
            reader.AlignStream();

            m_VertexData = new VertexData(reader);
        } else {
            var verticesSize = reader.readInt();
            vertices = new SpriteVertex[verticesSize];
            for (int i = 0; i < verticesSize; i++) {
                vertices[i] = new SpriteVertex(reader);
            }

            indices = reader.readShorts(reader.readInt());
            reader.AlignStream();
        }

        if (version[0] >= 2018) { //2018 and up
            m_Bindpose = reader.readMatrices(reader.readInt());

            if (version[0] == 2018 && version[1] < 2) { //2018.2 down
                var m_SourceSkinSize = reader.readInt();
                for (int i = 0; i < m_SourceSkinSize; i++) {
                    m_SourceSkin[i] = new BoneWeights4(reader);
                }
            }
        }

        textureRect = new Rectf(reader);
        textureRectOffset = reader.ReadVector2();
        if (version[0] > 5 || (version[0] == 5 && version[1] >= 6)) { //5.6 and up
            atlasRectOffset = reader.ReadVector2();
        }

        settingsRaw = new SpriteSettings(reader);
        if (version[0] > 4 || (version[0] == 4 && version[1] >= 5)) { //4.5 and up
            uvTransform = reader.ReadVector4();
        }

        if (version[0] >= 2017) { //2017 and up
            downscaleMultiplier = reader.readFloat();
        }
    }
}
