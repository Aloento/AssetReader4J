package com.QYun.AssetReader4J.Unity3D.Objects.AnimationClip;

import com.QYun.AssetReader4J.Unity3D.ObjectReader;

public class PackedIntVector {
    public int m_NumItems;
    public Byte[] m_Data;
    public byte m_BitSize;

    public PackedIntVector(ObjectReader reader) {
        m_NumItems = reader.readInt();

        int numData = reader.readInt();
        m_Data = reader.readBytes(numData);
        reader.AlignStream();

        m_BitSize = reader.readByte();
        reader.AlignStream();
    }

    public int[] unpackInts() {
        var data = new int[m_NumItems];
        int indexPos = 0;
        int bitPos = 0;
        for (int i = 0; i < m_NumItems; i++) {
            int bits = 0;
            data[i] = 0;
            while (bits < m_BitSize) {
                data[i] |= (m_Data[indexPos] >> bitPos) << bits;
                int num = Math.min(m_BitSize - bits, 8 - bitPos);
                bitPos += num;
                bits += num;
                if (bitPos == 8) {
                    indexPos++;
                    bitPos = 0;
                }
            }
            data[i] &= (1 << m_BitSize) - 1;
        }
        return data;
    }
}
