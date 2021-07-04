package com.QYun.AssetReader4J.Unity3D.Objects.Shader;

import com.QYun.AssetReader4J.Entities.Enums;
import com.QYun.AssetReader4J.Entities.Enums.ShaderCompilerPlatform;
import com.QYun.AssetReader4J.Unity3D.Contracts.NamedObject;
import com.QYun.AssetReader4J.Unity3D.Contracts.Texture;
import com.QYun.AssetReader4J.Unity3D.ObjectReader;
import com.QYun.AssetReader4J.Unity3D.Objects.PPtr;

import java.util.Arrays;

public class Shader extends NamedObject {
    public Byte[] m_Script;
    //5.3 - 5.4
    public int decompressedSize;
    public Byte[] m_SubProgramBlob;
    //5.5 and up
    public SerializedShader m_ParsedForm;
    public ShaderCompilerPlatform[] platforms;
    public Integer[] offsets;
    public Integer[] compressedLengths;
    public Integer[] decompressedLengths;
    public Byte[] compressedBlob;

    public Shader(ObjectReader reader) {
        super(reader);
        if (version[0] == 5 && version[1] >= 5 || version[0] > 5) { //5.5 and up
            m_ParsedForm = new SerializedShader(reader);
            platforms = Arrays.stream(reader.ReadUInt32Array()).map(Enums::shaderCompilerPlatform).toArray(ShaderCompilerPlatform[]::new);
            if (version[0] > 2019 || (version[0] == 2019 && version[1] >= 3)) { //2019.3 and up
                offsets = Arrays.stream(reader.ReadUInt32ArrayArray()).map(integers -> integers[0]).toArray(Integer[]::new);
                compressedLengths = Arrays.stream(reader.ReadUInt32ArrayArray()).map(integers -> integers[0]).toArray(Integer[]::new);
                decompressedLengths = Arrays.stream(reader.ReadUInt32ArrayArray()).map(integers -> integers[0]).toArray(Integer[]::new);
            } else {
                offsets = reader.ReadUInt32Array();
                compressedLengths = reader.ReadUInt32Array();
                decompressedLengths = reader.ReadUInt32Array();
            }
            compressedBlob = reader.ReadUInt8Array();
            reader.AlignStream();

            var m_DependenciesCount = reader.ReadInt32();
            for (int i = 0; i < m_DependenciesCount; i++) {
                new PPtr<>(reader, Shader.class);
            }

            if (version[0] >= 2018) {
                var m_NonModifiableTexturesCount = reader.ReadInt32();
                for (int i = 0; i < m_NonModifiableTexturesCount; i++) {
                    var first = reader.ReadAlignedString();
                    new PPtr<>(reader, Texture.class);
                }
            }

            var m_ShaderIsBaked = reader.ReadBoolean();
            reader.AlignStream();
        } else {
            m_Script = reader.ReadUInt8Array();
            reader.AlignStream();
            var m_PathName = reader.ReadAlignedString();
            if (version[0] == 5 && version[1] >= 3) { //5.3 - 5.4
                decompressedSize = reader.ReadUInt32();
                m_SubProgramBlob = reader.ReadUInt8Array();
            }
        }
    }
}
