package com.QYun.AssetReader4J.Unity3D.Objects;

import com.QYun.AssetReader4J.Unity3D.Contracts.NamedObject;
import com.QYun.AssetReader4J.Unity3D.Contracts.Texture;
import com.QYun.AssetReader4J.Unity3D.ObjectReader;
import com.QYun.AssetReader4J.Unity3D.Objects.Material.Material;

public class Font extends NamedObject {
    public Byte[] m_FontData;

    public Font(ObjectReader reader) {
        super(reader);
        if ((version[0] == 5 && version[1] >= 5) || version[0] > 5) {//5.5 and up
            var m_LineSpacing = reader.readFloat();
            var m_DefaultMaterial = new PPtr<>(reader, Material.class);
            var m_FontSize = reader.readFloat();
            var m_Texture = new PPtr<>(reader, Texture.class);
            int m_AsciiStartOffset = reader.readInt();
            var m_Tracking = reader.readFloat();
            var m_CharacterSpacing = reader.readInt();
            var m_CharacterPadding = reader.readInt();
            var m_ConvertCase = reader.readInt();
            int m_CharacterRects_size = reader.readInt();
            for (int i = 0; i < m_CharacterRects_size; i++) {
                reader.setPos(reader.getPos() + 44); //CharacterInfo data 41
            }
            int m_KerningValues_size = reader.readInt();
            for (int i = 0; i < m_KerningValues_size; i++) {
                reader.setPos(reader.getPos() + 8);
            }
            var m_PixelScale = reader.readFloat();
            int m_FontData_size = reader.readInt();
            if (m_FontData_size > 0) {
                m_FontData = reader.readBytes(m_FontData_size);
            }
        } else {
            int m_AsciiStartOffset = reader.readInt();

            if (version[0] <= 3) {
                int m_FontCountX = reader.readInt();
                int m_FontCountY = reader.readInt();
            }

            float m_Kerning = reader.readFloat();
            float m_LineSpacing = reader.readFloat();

            if (version[0] <= 3) {
                int m_PerCharacterKerning_size = reader.readInt();
                for (int i = 0; i < m_PerCharacterKerning_size; i++) {
                    int first = reader.readInt();
                    float second = reader.readFloat();
                }
            } else {
                int m_CharacterSpacing = reader.readInt();
                int m_CharacterPadding = reader.readInt();
            }

            int m_ConvertCase = reader.readInt();
            var m_DefaultMaterial = new PPtr<>(reader, Material.class);

            int m_CharacterRects_size = reader.readInt();
            for (int i = 0; i < m_CharacterRects_size; i++) {
                int index = reader.readInt();
                //Rectf uv
                float uvx = reader.readFloat();
                float uvy = reader.readFloat();
                float uvwidth = reader.readFloat();
                float uvheight = reader.readFloat();
                //Rectf vert
                float vertx = reader.readFloat();
                float verty = reader.readFloat();
                float vertwidth = reader.readFloat();
                float vertheight = reader.readFloat();
                float width = reader.readFloat();

                if (version[0] >= 4) {
                    var flipped = reader.readBoolean();
                    reader.alignStream();
                }
            }

            var m_Texture = new PPtr<>(reader, Texture.class);

            int m_KerningValues_size = reader.readInt();
            for (int i = 0; i < m_KerningValues_size; i++) {
                int pairfirst = reader.readShort();
                int pairsecond = reader.readShort();
                float second = reader.readFloat();
            }

            if (version[0] <= 3) {
                var m_GridFont = reader.readBoolean();
                reader.alignStream();
            } else {
                float m_PixelScale = reader.readFloat();
            }

            int m_FontData_size = reader.readInt();
            if (m_FontData_size > 0) {
                m_FontData = reader.readBytes(m_FontData_size);
            }
        }
    }
}
