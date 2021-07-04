package com.QYun.AssetReader4J.Unity3D.Objects.Shader;

import com.QYun.AssetReader4J.Unity3D.ObjectReader;

public class SerializedShader {
    public SerializedProperties m_PropInfo;
    public SerializedSubShader[] m_SubShaders;
    public String m_Name;
    public String m_CustomEditorName;
    public String m_FallbackName;
    public SerializedShaderDependency[] m_Dependencies;
    public SerializedCustomEditorForRenderPipeline[] m_CustomEditorForRenderPipelines;
    public boolean m_DisableNoSubshadersMessage;

    public SerializedShader(ObjectReader reader) {
        var version = reader.version();

        m_PropInfo = new SerializedProperties(reader);

        int numSubShaders = reader.readInt();
        m_SubShaders = new SerializedSubShader[numSubShaders];
        for (int i = 0; i < numSubShaders; i++) {
            m_SubShaders[i] = new SerializedSubShader(reader);
        }

        m_Name = reader.readAlignedString();
        m_CustomEditorName = reader.readAlignedString();
        m_FallbackName = reader.readAlignedString();

        int numDependencies = reader.readInt();
        m_Dependencies = new SerializedShaderDependency[numDependencies];
        for (int i = 0; i < numDependencies; i++) {
            m_Dependencies[i] = new SerializedShaderDependency(reader);
        }

        if (version[0] >= 2021) { //2021.1 and up
            int m_CustomEditorForRenderPipelinesSize = reader.readInt();
            m_CustomEditorForRenderPipelines = new SerializedCustomEditorForRenderPipeline[m_CustomEditorForRenderPipelinesSize];
            for (int i = 0; i < m_CustomEditorForRenderPipelinesSize; i++) {
                m_CustomEditorForRenderPipelines[i] = new SerializedCustomEditorForRenderPipeline(reader);
            }
        }

        m_DisableNoSubshadersMessage = reader.readBoolean();
        reader.alignStream();
    }
}
