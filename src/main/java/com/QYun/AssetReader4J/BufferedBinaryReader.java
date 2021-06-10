package com.QYun.AssetReader4J;

import java.io.BufferedInputStream;
import java.io.DataInput;
import java.io.IOException;
import java.io.InputStream;

public class BufferedBinaryReader extends BufferedInputStream implements DataInput {
    private boolean isLittleEndian = false;

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

    }

    @Override
    public void readFully(byte[] b, int off, int len) throws IOException {

    }

    @Override
    public int skipBytes(int n) throws IOException {
        return 0;
    }

    @Override
    public boolean readBoolean() throws IOException {
        return false;
    }

    @Override
    public byte readByte() throws IOException {
        return 0;
    }

    @Override
    public int readUnsignedByte() throws IOException {
        return 0;
    }

    @Override
    public short readShort() throws IOException {
        return 0;
    }

    @Override
    public int readUnsignedShort() throws IOException {
        return 0;
    }

    @Override
    public char readChar() throws IOException {
        return 0;
    }

    @Override
    public int readInt() throws IOException {
        return 0;
    }

    @Override
    public long readLong() throws IOException {
        return 0;
    }

    @Override
    public float readFloat() throws IOException {
        return 0;
    }

    @Override
    public double readDouble() throws IOException {
        return 0;
    }

    @Override
    public String readLine() throws IOException {
        return null;
    }

    @Override
    public String readUTF() throws IOException {
        return null;
    }
}
