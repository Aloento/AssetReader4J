package com.QYun.AssetReader4J.Unity3D.Objects.AnimationClip;

import com.QYun.AssetReader4J.Unity3D.ObjectReader;

public class PackedFloatVector {
    public int m_NumItems;
    public float m_Range;
    public float m_Start;
    public Byte[] m_Data;
    public byte m_BitSize;

    public PackedFloatVector(ObjectReader reader) {
        m_NumItems = reader.readInt();
        m_Range = reader.readFloat();
        m_Start = reader.readFloat();

        int numData = reader.readInt();
        m_Data = reader.readBytes(numData);
        reader.AlignStream();

        m_BitSize = reader.readByte();
        reader.AlignStream();
    }

    // public float[] unpackFloats(int itemCountInChunk, int chunkStride, int start = 0, int numChunks = -1){
    //
    // }
}
