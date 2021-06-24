package com.QYun.util.Stream;

import java.io.*;
import java.nio.ByteOrder;

public class ByteStream extends ByteBufferWrapper implements DataInput, DataOutput {
    public int trueLen;

    public ByteStream(int capacity) {
        super(capacity);
    }

    public ByteStream(byte[] array) {
        super(array);
    }

    public ByteStream(byte[] array, int offset, int length) {
        super(array, offset, length);
    }

    public ByteStream(InputStream inputStream) throws IOException {
        super(inputStream);
    }

    public ByteStream(ByteArrayOutputStream outputStream) {
        super(outputStream);
    }

    public ByteStream(File file) throws IOException {
        super(file);
    }

    @Override
    public void readFully(byte[] b) {
        readFully(b, 0, b.length);
    }

    @Override
    public void readFully(byte[] b, int off, int len) {
        byteBuffer.get(b, off, len);
    }

    @Override
    public int skipBytes(int n) {
        if (n <= 0)
            return 0;

        int avail = byteBuffer.capacity() - byteBuffer.position();
        if (avail <= 0)
            return 0;

        int skipped = Math.min(avail, n);
        byteBuffer.position(byteBuffer.position() + skipped);
        return skipped;
    }

    @Override
    public boolean readBoolean() {
        return 0 != byteBuffer.get();
    }

    @Override
    public byte readByte() {
        return byteBuffer.get();
    }

    public byte[] readBytes(int n) {
        var array = new byte[n];
        for (int i = 0; i < n; i++) {
            array[i] = readByte();
        }
        return array;
    }

    @Override
    public int readUnsignedByte() {
        return byteBuffer.get();
    }

    @Override
    public short readShort() {
        return byteBuffer.getShort();
    }

    @Override
    public int readUnsignedShort() {
        return byteBuffer.getShort();
    }

    @Override
    public char readChar() {
        return byteBuffer.getChar();
    }

    @Override
    public int readInt() {
        return byteBuffer.getInt();
    }

    @Override
    public long readLong() {
        return byteBuffer.getLong();
    }

    @Override
    public float readFloat() {
        return byteBuffer.getFloat();
    }

    public float[] readFloats(int n) {
        float[] array = new float[n];
        for (int i = 0; i < n; i++) {
            array[i] = readFloat();
        }
        return array;
    }

    @Override
    public double readDouble() {
        return byteBuffer.getDouble();
    }

    @Override
    public String readLine() {
        throw new UnsupportedOperationException();
    }

    @Override
    public String readUTF() {
        throw new UnsupportedOperationException();
    }

    @Override
    public void write(int b) {
        byteBuffer.put((byte) b);
        trueLen++;
    }

    @Override
    public void write(byte[] b) {
        byteBuffer.put(b);
        trueLen += b.length;
    }

    @Override
    public void write(byte[] b, int off, int len) {
        byteBuffer.put(b, off, len);
        trueLen += len;
    }

    @Override
    public void writeBoolean(boolean v) {
        byteBuffer.put((byte) (v ? 1 : 0));
        trueLen++;
    }

    @Override
    public void writeByte(int v) {
        byteBuffer.put((byte) v);
        trueLen++;
    }

    @Override
    public void writeShort(int v) {
        byteBuffer.putShort((short) v);
        trueLen += 2;
    }

    @Override
    public void writeChar(int v) {
        byteBuffer.putChar((char) v);
        trueLen += 2;
    }

    @Override
    public void writeInt(int v) {
        byteBuffer.putInt(v);
        trueLen += 4;
    }

    @Override
    public void writeLong(long v) {
        byteBuffer.putLong(v);
        trueLen += 8;
    }

    @Override
    public void writeFloat(float v) {
        byteBuffer.putFloat(v);
        trueLen += 4;
    }

    @Override
    public void writeDouble(double v) {
        byteBuffer.putDouble(v);
        trueLen += 8;
    }

    @Override
    public void writeBytes(String s) {
        for (int i = 0; i < s.length(); i++)
            byteBuffer.put((byte) s.charAt(i));
        trueLen += s.length();
    }

    @Override
    public void writeChars(String s) {
        for (int i = 0; i < s.length(); i++)
            byteBuffer.putChar(s.charAt(i));
        trueLen += s.length();
    }

    @Override
    public void writeUTF(String s) {
        throw new UnsupportedOperationException();
    }

    @Override
    public ByteStream setToReadOnly() {
        super.setToReadOnly();
        return this;
    }

    @Override
    public ByteStream setByteOrder(ByteOrder byteOrder) {
        super.setByteOrder(byteOrder);
        return this;
    }

    public void copyTo(ByteStream dest, long size) {
        dest.write(readBytes(Math.min(trueLen, Math.toIntExact(size))));
    }
}
