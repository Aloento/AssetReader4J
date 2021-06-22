package com.QYun.Stream;

import java.io.Closeable;
import java.io.DataInput;
import java.io.DataOutput;
import java.io.IOException;
import java.nio.ByteOrder;

public class ByteStream extends ByteBufferWrapper implements DataInput, DataOutput, Closeable {
    public ByteStream(int capacity) {
        super(capacity);
    }

    public ByteStream(int capacity, ByteOrder byteOrder) {
        super(capacity, byteOrder);
    }

    public ByteStream(byte[] array) {
        super(array);
    }

    public ByteStream(byte[] array, ByteOrder byteOrder) {
        super(array, byteOrder);
    }

    public ByteStream(byte[] array, int offset, int length) {
        super(array, offset, length);
    }

    public ByteStream(byte[] array, int offset, int length, ByteOrder byteOrder) {
        super(array, offset, length, byteOrder);
    }

    @Override
    public void close() throws IOException {
        byteBuffer.clear();
        byteBuffer = null;
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

    @Override
    public void write(int b) throws IOException {

    }

    @Override
    public void write(byte[] b) throws IOException {

    }

    @Override
    public void write(byte[] b, int off, int len) throws IOException {

    }

    @Override
    public void writeBoolean(boolean v) throws IOException {

    }

    @Override
    public void writeByte(int v) throws IOException {

    }

    @Override
    public void writeShort(int v) throws IOException {

    }

    @Override
    public void writeChar(int v) throws IOException {

    }

    @Override
    public void writeInt(int v) throws IOException {

    }

    @Override
    public void writeLong(long v) throws IOException {

    }

    @Override
    public void writeFloat(float v) throws IOException {

    }

    @Override
    public void writeDouble(double v) throws IOException {

    }

    @Override
    public void writeBytes(String s) throws IOException {

    }

    @Override
    public void writeChars(String s) throws IOException {

    }

    @Override
    public void writeUTF(String s) throws IOException {

    }
}
