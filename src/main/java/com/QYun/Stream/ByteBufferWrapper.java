package com.QYun.Stream;

import java.nio.ByteBuffer;

public abstract class ByteBufferWrapper {
    public ByteBuffer byteBuffer;

    public ByteBufferWrapper(int capacity) {
        byteBuffer = ByteBuffer.allocate(capacity);
    }

    public ByteBufferWrapper(byte[] array) {
        byteBuffer = ByteBuffer.wrap(array);
    }

    public ByteBufferWrapper(byte[] array, int offset, int length) {
        byteBuffer = ByteBuffer.wrap(array, offset, length);
    }
}
