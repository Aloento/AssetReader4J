package com.QYun.AssetReader4J.Unity3D.Objects.AnimationClip;

import com.QYun.AssetReader4J.Unity3D.UObjectReader;

public class PackedFloatVector {
    public int m_NumItems;
    public float m_Range;
    public float m_Start;
    public byte[] m_Data;
    public byte m_BitSize;

    public PackedFloatVector(UObjectReader reader) {
        m_NumItems = reader.readInt();
        m_Range = reader.readFloat();
        m_Start = reader.readFloat();

        int numData = reader.readInt();
        m_Data = reader.readBytes(numData);
        reader.alignStream();

        m_BitSize = reader.readByte();
        reader.alignStream();
    }

    // public float[] unpackFloats(int itemCountInChunk, int chunkStride, int start = 0, int numChunks = -1){
    //
    // }
}
