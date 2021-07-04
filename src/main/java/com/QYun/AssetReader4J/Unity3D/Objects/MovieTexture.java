package com.QYun.AssetReader4J.Unity3D.Objects;

import com.QYun.AssetReader4J.Unity3D.Contracts.Texture;
import com.QYun.AssetReader4J.Unity3D.ObjectReader;

public class MovieTexture extends Texture {
    public Byte[] m_MovieData;
    public PPtr<AudioClip> m_AudioClip;

    public MovieTexture(ObjectReader reader) {
        super(reader);
        var m_Loop = reader.ReadBoolean();
        reader.AlignStream();
        m_AudioClip = new PPtr<>(reader, AudioClip.class);
        m_MovieData = reader.ReadUInt8Array();
    }
}
