package com.QYun.Stream;

import java.io.*;
import java.nio.ByteBuffer;
import java.nio.ByteOrder;

public abstract class ByteBufferWrapper {
    ByteBuffer byteBuffer;

    public ByteBufferWrapper(int capacity) {
        this(capacity, ByteOrder.BIG_ENDIAN);
    }

    public ByteBufferWrapper(int capacity, ByteOrder byteOrder) {
        byteBuffer = ByteBuffer.allocate(capacity);
        setByteOrder(byteOrder);
    }

    public ByteBufferWrapper(byte[] array) {
        this(array, ByteOrder.BIG_ENDIAN);
    }

    public ByteBufferWrapper(byte[] array, ByteOrder byteOrder) {
        byteBuffer = ByteBuffer.wrap(array);
        setByteOrder(byteOrder);
    }

    public ByteBufferWrapper(byte[] array, int offset, int length) {
        this(array, offset, length, ByteOrder.BIG_ENDIAN);
    }

    public ByteBufferWrapper(byte[] array, int offset, int length, ByteOrder byteOrder) {
        byteBuffer = ByteBuffer.wrap(array, offset, length);
        setByteOrder(byteOrder);
    }

    public ByteBufferWrapper(InputStream inputStream) throws IOException {
        this(inputStream.readAllBytes());
    }

    public ByteBufferWrapper(ByteArrayOutputStream outputStream) {
        this(outputStream.toByteArray());
    }

    public ByteBufferWrapper(File file) throws IOException {
        this(new FileInputStream(file));
    }

    public void setToReadOnly() {
        byteBuffer = byteBuffer.asReadOnlyBuffer();
    }

    public void setByteOrder(ByteOrder byteOrder) {
        byteBuffer.order(byteOrder);
    }

    public ByteArrayInputStream toInputStream() {
        return new ByteArrayInputStream(byteBuffer.array());
    }

    public ByteArrayOutputStream toOutputStream() {
        var tmp = new ByteArrayOutputStream(byteBuffer.capacity());
        tmp.writeBytes(byteBuffer.array());
        return tmp;
    }
}
