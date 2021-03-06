package com.QYun.AssetReader4J.Unity3D.Objects.Sprite;

import com.QYun.AssetReader4J.Unity3D.Contracts.NamedObject;
import com.QYun.AssetReader4J.Unity3D.ObjectReader;
import com.QYun.AssetReader4J.Unity3D.Objects.PPtr;
import com.QYun.AssetReader4J.Unity3D.Objects.SpriteAtlas.SpriteAtlas;
import org.apache.commons.lang.ArrayUtils;
import org.eclipse.collections.api.factory.Maps;
import org.eclipse.collections.api.map.MutableMap;

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

    public Sprite(ObjectReader reader) {
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
            var first = UUID.nameUUIDFromBytes(ArrayUtils.toPrimitive(reader.readBytes(16)));
            var second = reader.readLong();
            m_RenderDataKey = Maps.mutable.empty();
            m_AtlasTags = reader.readStringArray(reader.readInt());
            m_SpriteAtlas = new PPtr<>(reader, SpriteAtlas.class);
        }

        m_RD = new SpriteRenderData(reader);

        if (version[0] > 2017) {
            var m_PhysicsShapeSize = reader.readInt();
            m_PhysicsShape = new Vector2f[m_PhysicsShapeSize][];
            for (int i = 0; i < m_PhysicsShapeSize; i++) {
                m_PhysicsShape[i] = reader.readVector2Array();
            }
        }
    }
}
