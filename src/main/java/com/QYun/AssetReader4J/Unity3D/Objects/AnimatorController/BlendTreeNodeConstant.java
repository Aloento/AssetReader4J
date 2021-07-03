package com.QYun.AssetReader4J.Unity3D.Objects.AnimatorController;

import com.QYun.AssetReader4J.Unity3D.ObjectReader;

public class BlendTreeNodeConstant {
    public int m_BlendType;
    public int m_BlendEventID;
    public int m_BlendEventYID;
    public int[] m_ChildIndices;
    public float[] m_ChildThresholdArray;
    public Blend1dDataConstant m_Blend1dData;
    public Blend2dDataConstant m_Blend2dData;
    public BlendDirectDataConstant m_BlendDirectData;
    public int m_ClipID;
    public int m_ClipIndex;
    public float m_Duration;
    public float m_CycleOffset;
    public boolean m_Mirror;

    public BlendTreeNodeConstant(ObjectReader reader) {
        var version = reader.version();

        if (version[0] > 4 || (version[0] == 4 && version[1] >= 1)) { //4.1 and up
            m_BlendType = reader.readInt();
        }
        m_BlendEventID = reader.readInt();
        if (version[0] > 4 || (version[0] == 4 && version[1] >= 1)) { //4.1 and up
            m_BlendEventYID = reader.readInt();
        }
        m_ChildIndices = reader.readInts(reader.readInt());
        if (version[0] < 4 || (version[0] == 4 && version[1] < 1)) { //4.1 down
            m_ChildThresholdArray = reader.readFloats(reader.readInt());
        }

        if (version[0] > 4 || (version[0] == 4 && version[1] >= 1)) { //4.1 and up
            m_Blend1dData = new Blend1dDataConstant(reader);
            m_Blend2dData = new Blend2dDataConstant(reader);
        }

        if (version[0] >= 5) { //5.0 and up
            m_BlendDirectData = new BlendDirectDataConstant(reader);
        }

        m_ClipID = reader.readInt();
        if (version[0] == 4 && version[1] >= 5) { //4.5 - 5.0
            m_ClipIndex = reader.readInt();
        }

        m_Duration = reader.readFloat();

        if (version[0] > 4
                || (version[0] == 4 && version[1] > 1)
                || (version[0] == 4 && version[1] == 1 && version[2] >= 3)) { //4.1.3 and up
            m_CycleOffset = reader.readFloat();
            m_Mirror = reader.readBoolean();
            reader.AlignStream();
        }
    }
}
