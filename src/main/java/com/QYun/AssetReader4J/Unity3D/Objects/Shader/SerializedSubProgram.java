package com.QYun.AssetReader4J.Unity3D.Objects.Shader;

import com.QYun.AssetReader4J.Entities.Enums;
import com.QYun.AssetReader4J.Entities.Enums.ShaderGpuProgramType;
import com.QYun.AssetReader4J.Unity3D.ObjectReader;

public class SerializedSubProgram {
    public int m_BlobIndex;
    public ParserBindChannels m_Channels;
    public Short[] m_KeywordIndices;
    public byte m_ShaderHardwareTier;
    public ShaderGpuProgramType m_GpuProgramType;
    public SerializedProgramParameters m_Parameters;
    public VectorParameter[] m_VectorParams;
    public MatrixParameter[] m_MatrixParams;
    public TextureParameter[] m_TextureParams;
    public BufferBinding[] m_BufferParams;
    public ConstantBuffer[] m_ConstantBuffers;
    public BufferBinding[] m_ConstantBufferBindings;
    public UAVParameter[] m_UAVParams;
    public SamplerParameter[] m_Samplers;

    public SerializedSubProgram(ObjectReader reader) {
        var version = reader.version();

        m_BlobIndex = reader.readInt();
        m_Channels = new ParserBindChannels(reader);

        if (version[0] >= 2019) { //2019 and up
            var m_GlobalKeywordIndices = reader.readShortArray();
            reader.alignStream();
            var m_LocalKeywordIndices = reader.readShortArray();
            reader.alignStream();
        } else {
            m_KeywordIndices = reader.readShortArray();
            if (version[0] >= 2017) { //2017 and up
                reader.alignStream();
            }
        }

        m_ShaderHardwareTier = reader.readByte();
        m_GpuProgramType = Enums.shaderGpuProgramType(reader.readByte()); //m_GpuProgramType = (ShaderGpuProgramType)reader.ReadSByte();
        reader.alignStream();

        if ((version[0] == 2020 && version[1] > 3) ||
                (version[0] == 2020 && version[1] == 3 && version[2] > 0) ||
                (version[0] == 2020 && version[1] == 3 && version[2] == 0 && version[3] >= 2) || //2020.3.0f2 to 2020.3.x
                (version[0] == 2021 && version[1] > 1) ||
                (version[0] == 2021 && version[1] == 1 && version[2] >= 4)) //2021.1.4f1 to 2021.1.x
        {
            m_Parameters = new SerializedProgramParameters(reader);
        } else {
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

            int numTextureParams = reader.readInt();
            m_TextureParams = new TextureParameter[numTextureParams];
            for (int i = 0; i < numTextureParams; i++) {
                m_TextureParams[i] = new TextureParameter(reader);
            }

            int numBufferParams = reader.readInt();
            m_BufferParams = new BufferBinding[numBufferParams];
            for (int i = 0; i < numBufferParams; i++) {
                m_BufferParams[i] = new BufferBinding(reader);
            }

            int numConstantBuffers = reader.readInt();
            m_ConstantBuffers = new ConstantBuffer[numConstantBuffers];
            for (int i = 0; i < numConstantBuffers; i++) {
                m_ConstantBuffers[i] = new ConstantBuffer(reader);
            }

            int numConstantBufferBindings = reader.readInt();
            m_ConstantBufferBindings = new BufferBinding[numConstantBufferBindings];
            for (int i = 0; i < numConstantBufferBindings; i++) {
                m_ConstantBufferBindings[i] = new BufferBinding(reader);
            }

            int numUAVParams = reader.readInt();
            m_UAVParams = new UAVParameter[numUAVParams];
            for (int i = 0; i < numUAVParams; i++) {
                m_UAVParams[i] = new UAVParameter(reader);
            }

            if (version[0] >= 2017) { //2017 and up
                int numSamplers = reader.readInt();
                m_Samplers = new SamplerParameter[numSamplers];
                for (int i = 0; i < numSamplers; i++) {
                    m_Samplers[i] = new SamplerParameter(reader);
                }
            }
        }

        if (version[0] > 2017 || (version[0] == 2017 && version[1] >= 2)) { //2017.2 and up
            if (version[0] >= 2021) { //2021.1 and up
                var m_ShaderRequirements = reader.readLong();
            } else {
                var m_ShaderRequirements = reader.readInt();
            }
        }
    }
}
