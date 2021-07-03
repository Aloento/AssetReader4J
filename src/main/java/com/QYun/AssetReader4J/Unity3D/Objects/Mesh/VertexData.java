package com.QYun.AssetReader4J.Unity3D.Objects.Mesh;

import com.QYun.AssetReader4J.Unity3D.ObjectReader;

public class VertexData {
    public int m_CurrentChannels;
    public int m_VertexCount;
    public ChannelInfo[] m_Channels;
    public StreamInfo[] m_Streams;
    public Byte[] m_DataSize;

    public VertexData(ObjectReader reader) {
        var version = reader.version();

        if (version[0] < 2018) { //2018 down
            m_CurrentChannels = reader.readInt();
        }
        m_VertexCount = reader.readInt();

        if (version[0] >= 4) { //4.0 and up
            var m_ChannelsSize = reader.readInt();
            m_Channels = new ChannelInfo[m_ChannelsSize];
            for (int i = 0; i < m_ChannelsSize; i++) {
                m_Channels[i] = new ChannelInfo(reader);
            }
        }

        if (version[0] < 5) { //5.0 down
            if (version[0] < 4) {
                m_Streams = new StreamInfo[4];
            } else {
                m_Streams = new StreamInfo[reader.readInt()];
            }

            for (int i = 0; i < m_Streams.length; i++) {
                m_Streams[i] = new StreamInfo(reader);
            }

            if (version[0] < 4) { //4.0 down
                getChannels(version);
            }
        } else { //5.0 and up
            getStreams(version);
        }

        m_DataSize = reader.readBytes(reader.readInt());
        reader.AlignStream();
    }

    private void getStreams(int[] version) {
        //TODO
    }

    private void getChannels(int[] version) {
        //TODO
    }
}
