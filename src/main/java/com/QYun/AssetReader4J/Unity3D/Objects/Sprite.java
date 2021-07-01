package com.QYun.AssetReader4J.Unity3D.Objects;

import com.QYun.AssetReader4J.Unity3D.Contracts.NamedObject;
import com.QYun.AssetReader4J.Unity3D.Objects.Mesh.BoneWeights4;
import com.QYun.AssetReader4J.Unity3D.Objects.Mesh.SubMesh;
import com.QYun.AssetReader4J.Unity3D.Objects.Mesh.VertexData;
import com.QYun.AssetReader4J.Unity3D.PPtr;
import com.QYun.AssetReader4J.Unity3D.UObjectReader;
import com.QYun.util.Stream.UnityStream;
import org.eclipse.collections.api.factory.Maps;
import org.eclipse.collections.api.map.MutableMap;

import javax.vecmath.Matrix4f;
import javax.vecmath.Vector2f;
import javax.vecmath.Vector4f;
import java.util.UUID;

public class Sprite extends NamedObject {
    public Rectf m_Rect;
    public Vector2f m_Offset;
    public Vector4f m_Border;
    public float m_PixelsToUnits;
    public Vector2f m_Pivboolean;
    public int m_Extrude;
    public boolean m_IsPolygon;
    public MutableMap<UUID, Long> m_RenderDataKey;
    public String[] m_AtlasTags;
    public PPtr<SpriteAtlas> m_SpriteAtlas;
    public SpriteRenderData m_RD;
    public Vector2f[][] m_PhysicsShape;

    public Sprite(UObjectReader reader) {
        super(reader);

        m_Rect = new Rectf(reader);
        m_Offset = reader.readVector2();
        if (version[0] > 4 || (version[0] == 4 && version[1] >= 5)) {
            m_Border = reader.readVector4();
        }

        m_PixelsToUnits = reader.readFloat();
        if (version[0] > 5
                || (version[0] == 5 && version[1] > 4)
                || (version[0] == 5 && version[1] == 4 && version[2] >= 2)
                || (version[0] == 5 && version[1] == 4 && version[2] == 1 && buildType.isPatch() && version[3] >= 3)) {
            m_Pivboolean = reader.readVector2();
        }

        m_Extrude = reader.readInt();
        if (version[0] > 5 || (version[0] == 5 && version[1] >= 3)) {
            m_IsPolygon = reader.readBoolean();
            reader.alignStream();
        }

        if (version[0] >= 2017) {
            var first = UUID.nameUUIDFromBytes(reader.readBytes(16));
            var second = reader.readLong();
            m_RenderDataKey = Maps.mutable.empty();
            m_AtlasTags = reader.readStrings(reader.readInt());
            m_SpriteAtlas = new PPtr<>(reader);
        }

        m_RD = new SpriteRenderData(reader);

        if (version[0] > 2017) {
            var m_PhysicsShapeSize = reader.readInt();
            m_PhysicsShape = new Vector2f[m_PhysicsShapeSize][];
            for (int i = 0; i < m_PhysicsShapeSize; i++) {
                m_PhysicsShape[i] = reader.readVector2s(reader.readInt());
            }
        }
    }
}

class SpriteRenderData {
    public PPtr<Texture2D> texture;
    public PPtr<Texture2D> alphaTexture;
    public SecondarySpriteTexture[] secondaryTextures;
    public SubMesh[] m_SubMeshes;
    public byte[] m_IndexBuffer;
    public VertexData m_VertexData;
    public SpriteVertex[] vertices;
    public short[] indices;
    public Matrix4f[] m_Bindpose;
    public BoneWeights4[] m_SourceSkin;
    public Rectf textureRect;
    public Vector2f textureRectOffset;
    public Vector2f atlasRectOffset;
    public SpriteSettings settingsRaw;
    public Vector4f uvTransform;
    public float downscaleMultiplier;

    public SpriteRenderData(UObjectReader reader) {
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
            reader.alignStream();

            m_VertexData = new VertexData(reader);
        } else {
            var verticesSize = reader.readInt();
            vertices = new SpriteVertex[verticesSize];
            for (int i = 0; i < verticesSize; i++) {
                vertices[i] = new SpriteVertex(reader);
            }

            indices = reader.readShorts(reader.readInt());
            reader.alignStream();
        }

        if (version[0] >= 2018) { //2018 and up
            m_Bindpose = reader.readMatrixs(reader.readInt());

            if (version[0] == 2018 && version[1] < 2) { //2018.2 down
                var m_SourceSkinSize = reader.readInt();
                for (int i = 0; i < m_SourceSkinSize; i++) {
                    m_SourceSkin[i] = new BoneWeights4(reader);
                }
            }
        }

        textureRect = new Rectf(reader);
        textureRectOffset = reader.readVector2();
        if (version[0] > 5 || (version[0] == 5 && version[1] >= 6)) { //5.6 and up
            atlasRectOffset = reader.readVector2();
        }

        settingsRaw = new SpriteSettings(reader);
        if (version[0] > 4 || (version[0] == 4 && version[1] >= 5)) { //4.5 and up
            uvTransform = reader.readVector4();
        }

        if (version[0] >= 2017) { //2017 and up
            downscaleMultiplier = reader.readFloat();
        }
    }
}

class Rectf {
    public float x;
    public float y;
    public float width;
    public float height;

    public Rectf(UnityStream reader) {
        x = reader.readFloat();
        y = reader.readFloat();
        width = reader.readFloat();
        height = reader.readFloat();
    }
}
