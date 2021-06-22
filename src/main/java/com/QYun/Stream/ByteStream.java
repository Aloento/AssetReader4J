package com.QYun.Stream;

import java.io.*;
import java.nio.ByteOrder;

public class ByteStream extends ByteBufferWrapper implements DataInput, DataOutput, Closeable {
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
    public void close() {
        byteBuffer.clear();
        byteBuffer = null;
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
    }

    @Override
    public void write(byte[] b) {
        byteBuffer.put(b);
    }

    @Override
    public void write(byte[] b, int off, int len) {
        byteBuffer.put(b, off, len);
    }

    @Override
    public void writeBoolean(boolean v) {
        byteBuffer.put((byte) (v ? 1 : 0));
    }

    @Override
    public void writeByte(int v) {
        byteBuffer.put((byte) v);
    }

    @Override
    public void writeShort(int v) {
        byteBuffer.putShort((short) v);
    }

    @Override
    public void writeChar(int v) {
        byteBuffer.putChar((char) v);
    }

    @Override
    public void writeInt(int v) {
        byteBuffer.putInt(v);
    }

    @Override
    public void writeLong(long v) {
        byteBuffer.putLong(v);
    }

    @Override
    public void writeFloat(float v) {
        byteBuffer.putFloat(v);
    }

    @Override
    public void writeDouble(double v) {
        byteBuffer.putDouble(v);
    }

    @Override
    public void writeBytes(String s) {
        for (int i = 0; i < s.length(); i++) {
            byteBuffer.put((byte) s.charAt(i));
        }
    }

    @Override
    public void writeChars(String s) {
        for (int i = 0; i < s.length(); i++) {
            byteBuffer.putChar(s.charAt(i));
        }
    }

    @Override
    public void writeUTF(String s) {
        throw new UnsupportedOperationException();
    }

    @Override
    public ByteStream setToReadOnly() {
        return (ByteStream) super.setToReadOnly();
    }

    @Override
    public ByteStream setByteOrder(ByteOrder byteOrder) {
        return (ByteStream) super.setByteOrder(byteOrder);
    }
}
