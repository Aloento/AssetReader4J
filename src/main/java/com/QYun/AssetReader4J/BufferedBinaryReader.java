package com.QYun.AssetReader4J;

import org.apache.commons.io.EndianUtils;

import java.io.*;
import java.util.Objects;

public class BufferedBinaryReader extends BufferedInputStream implements DataInput {
    private boolean isLittleEndian;

    public BufferedBinaryReader(InputStream in, boolean isLittleEndian) {
        super(in);
        this.isLittleEndian = isLittleEndian;
    }

    public int getPos() {
        return pos;
    }

    public void setPos(int pos) {
        this.pos = pos;
    }

    @Override
    public void readFully(byte[] b) throws IOException {
        readFully(b, 0, b.length);
    }

    @Override
    public void readFully(byte[] b, int off, int len) throws IOException {
        Objects.checkFromIndexSize(off, len, b.length);
        int n = 0;
        while (n < len) {
            int count = read(b, off + n, len - n);
            if (count < 0)
                throw new EOFException();
            n += count;
        }
    }

    @Override
    public int skipBytes(int n) throws IOException {
        return (int) skip(n);
    }

    @Override
    public boolean readBoolean() throws IOException {
        return 0 != readByte();
    }

    @Override
    public byte readByte() throws IOException {
        return (byte) read();
    }

    @Override
    public int readUnsignedByte() throws IOException {
        return read();
    }

    @Override
    public short readShort() throws IOException {
        if (isLittleEndian) {
            return EndianUtils.readSwappedShort(this);
        }
        int ch1 = readByte();
        int ch2 = readByte();
        return (short)((ch1 << 8) + ch2);
    }

    @Override
    public int readUnsignedShort() throws IOException {
        if (isLittleEndian) {
            return EndianUtils.readSwappedUnsignedShort(this);
        }
        int ch1 = readByte();
        int ch2 = readByte();
        return (ch1 << 8) + ch2;
    }

    @Override
    public char readChar() throws IOException {
        return (char) readShort();
    }

    @Override
    public int readInt() throws IOException {
        if (isLittleEndian) {
            return EndianUtils.readSwappedInteger(this);
        }
        int ch1 = readByte();
        int ch2 = readByte();
        int ch3 = readByte();
        int ch4 = readByte();
        return ((ch1 << 24) + (ch2 << 16) + (ch3 << 8) + ch4);
    }

    @Override
    public long readLong() throws IOException {
        if (isLittleEndian) {
            return EndianUtils.readSwappedLong(this);
        }
        byte[] readBuffer = new byte[8];
        readFully(readBuffer, 0, 8);
        return (((long)readBuffer[0] << 56) +
                ((long)(readBuffer[1] & 255) << 48) +
                ((long)(readBuffer[2] & 255) << 40) +
                ((long)(readBuffer[3] & 255) << 32) +
                ((long)(readBuffer[4] & 255) << 24) +
                ((readBuffer[5] & 255) << 16) +
                ((readBuffer[6] & 255) <<  8) +
                (readBuffer[7] & 255));
    }

    @Override
    public float readFloat() throws IOException {
        if (isLittleEndian) {
            return EndianUtils.readSwappedFloat(this);
        }
        return Float.intBitsToFloat(readInt());
    }

    @Override
    public double readDouble() throws IOException {
        if (isLittleEndian) {
            return EndianUtils.readSwappedDouble(this);
        }
        return Double.longBitsToDouble(readLong());
    }

    @Override
    public String readLine() throws IOException {
        return null;
    }

    @Override
    public String readUTF() throws IOException {
        return "null";
    }
}
