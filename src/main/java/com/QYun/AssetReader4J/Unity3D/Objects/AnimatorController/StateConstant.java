package com.QYun.AssetReader4J.Unity3D.Objects.AnimatorController;

import com.QYun.AssetReader4J.Unity3D.ObjectReader;

public class StateConstant {
    public TransitionConstant[] m_TransitionConstantArray;
    public Integer[] m_BlendTreeConstantIndexArray;
    public LeafInfoConstant[] m_LeafInfoArray;
    public BlendTreeConstant[] m_BlendTreeConstantArray;
    public int m_NameID;
    public int m_PathID;
    public int m_FullPathID;
    public int m_TagID;
    public int m_SpeedParamID;
    public int m_MirrorParamID;
    public int m_CycleOffsetParamID;
    public float m_Speed;
    public float m_CycleOffset;
    public boolean m_IKOnFeet;
    public boolean m_WriteDefaultValues;
    public boolean m_Loop;
    public boolean m_Mirror;

    public StateConstant(ObjectReader reader) {
        var version = reader.version();

        int numTransistions = reader.readInt();
        m_TransitionConstantArray = new TransitionConstant[numTransistions];
        for (int i = 0; i < numTransistions; i++) {
            m_TransitionConstantArray[i] = new TransitionConstant(reader);
        }

        m_BlendTreeConstantIndexArray = reader.readIntArray();

        if (version[0] < 5 || (version[0] == 5 && version[1] < 2)) { //5.2 down
            int numInfos = reader.readInt();
            m_LeafInfoArray = new LeafInfoConstant[numInfos];
            for (int i = 0; i < numInfos; i++) {
                m_LeafInfoArray[i] = new LeafInfoConstant(reader);
            }
        }

        int numBlends = reader.readInt();
        m_BlendTreeConstantArray = new BlendTreeConstant[numBlends];
        for (int i = 0; i < numBlends; i++) {
            m_BlendTreeConstantArray[i] = new BlendTreeConstant(reader);
        }

        m_NameID = reader.readInt();
        if (version[0] > 4 || (version[0] == 4 && version[1] >= 3)) { //4.3 and up
            m_PathID = reader.readInt();
        }
        if (version[0] >= 5) { //5.0 and up
            m_FullPathID = reader.readInt();
        }

        m_TagID = reader.readInt();
        if (version[0] > 5 || (version[0] == 5 && version[1] >= 1)) { //5.1 and up
            m_SpeedParamID = reader.readInt();
            m_MirrorParamID = reader.readInt();
            m_CycleOffsetParamID = reader.readInt();
        }

        if (version[0] > 2017 || (version[0] == 2017 && version[1] >= 2)) { //2017.2 and up
            var m_TimeParamID = reader.readInt();
        }

        m_Speed = reader.readFloat();
        if (version[0] > 4 || (version[0] == 4 && version[1] >= 1)) { //4.1 and up
            m_CycleOffset = reader.readFloat();
        }
        m_IKOnFeet = reader.readBoolean();
        if (version[0] >= 5) { //5.0 and up
            m_WriteDefaultValues = reader.readBoolean();
        }

        m_Loop = reader.readBoolean();
        if (version[0] > 4 || (version[0] == 4 && version[1] >= 1)) { //4.1 and up
            m_Mirror = reader.readBoolean();
        }

        reader.alignStream();
    }
}
