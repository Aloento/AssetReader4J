package com.QYun.AssetReader4J.Unity3D.Objects.Shader;

import com.QYun.AssetReader4J.Entities.Enums;
import com.QYun.AssetReader4J.Entities.Enums.PassType;
import com.QYun.AssetReader4J.Unity3D.ObjectReader;
import org.eclipse.collections.api.factory.Lists;
import org.eclipse.collections.api.factory.Maps;
import org.eclipse.collections.api.list.MutableList;
import org.eclipse.collections.api.map.MutableMap;

public class SerializedPass {
    public Hash128[] m_EditorDataHash;
    public Byte[] m_Platforms;
    public Short[] m_LocalKeywordMask;
    public Short[] m_GlobalKeywordMask;
    public MutableList<MutableMap<String, Integer>> m_NameIndices;
    public PassType m_Type;
    public SerializedShaderState m_State;
    public int m_ProgramMask;
    public SerializedProgram progVertex;
    public SerializedProgram progFragment;
    public SerializedProgram progGeometry;
    public SerializedProgram progHull;
    public SerializedProgram progDomain;
    public SerializedProgram progRayTracing;
    public boolean m_HasInstancingVariant;
    public String m_UseName;
    public String m_Name;
    public String m_TextureName;
    public SerializedTagMap m_Tags;

    public SerializedPass(ObjectReader reader) {
        var version = reader.version();

        if (version[0] > 2020 || (version[0] == 2020 && version[1] >= 2)) { //2020.2 and up
            int numEditorDataHash = reader.readInt();
            m_EditorDataHash = new Hash128[numEditorDataHash];
            for (int i = 0; i < numEditorDataHash; i++) {
                m_EditorDataHash[i] = new Hash128(reader);
            }
            reader.alignStream();
            m_Platforms = reader.readByteArray();
            reader.alignStream();
            m_LocalKeywordMask = reader.readShortArray();
            reader.alignStream();
            m_GlobalKeywordMask = reader.readShortArray();
            reader.alignStream();
        }

        int numIndices = reader.readInt();
        m_NameIndices = Lists.mutable.withInitialCapacity(numIndices);
        for (int i = 0; i < numIndices; i++) {
            MutableMap<String, Integer> tmp = Maps.mutable.empty();
            tmp.put(reader.readAlignedString(), reader.readInt());
            m_NameIndices.add(i, tmp);
        }

        m_Type = Enums.passType(reader.readInt());
        m_State = new SerializedShaderState(reader);
        m_ProgramMask = reader.readInt();
        progVertex = new SerializedProgram(reader);
        progFragment = new SerializedProgram(reader);
        progGeometry = new SerializedProgram(reader);
        progHull = new SerializedProgram(reader);
        progDomain = new SerializedProgram(reader);
        if (version[0] > 2019 || (version[0] == 2019 && version[1] >= 3)) { //2019.3 and up
            progRayTracing = new SerializedProgram(reader);
        }
        m_HasInstancingVariant = reader.readBoolean();
        if (version[0] >= 2018) { //2018 and up
            var m_HasProceduralInstancingVariant = reader.readBoolean();
        }
        reader.alignStream();
        m_UseName = reader.readAlignedString();
        m_Name = reader.readAlignedString();
        m_TextureName = reader.readAlignedString();
        m_Tags = new SerializedTagMap(reader);
    }
}
