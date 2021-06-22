package com.QYun.Stream;

import java.nio.ByteBuffer;
import java.nio.ByteOrder;

public abstract class ByteBufferWrapper {
    ByteBuffer byteBuffer;
    ByteOrder byteOrder;

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

    void setByteOrder(ByteOrder byteOrder) {
        byteBuffer.order(byteOrder);
        this.byteOrder = byteOrder;
    }
}
