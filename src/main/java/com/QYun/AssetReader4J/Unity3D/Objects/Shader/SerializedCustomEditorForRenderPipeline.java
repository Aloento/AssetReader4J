package com.QYun.AssetReader4J.Unity3D.Objects.Shader;

import com.QYun.AssetReader4J.Unity3D.ObjectReader;

public class SerializedCustomEditorForRenderPipeline {
    public String customEditorName;
    public String renderPipelineType;

    public SerializedCustomEditorForRenderPipeline(ObjectReader reader) {
        customEditorName = reader.ReadAlignedString();
        renderPipelineType = reader.ReadAlignedString();
    }
}
