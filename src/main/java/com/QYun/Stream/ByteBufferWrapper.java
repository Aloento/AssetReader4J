package com.QYun.Stream;

import java.nio.ByteBuffer;

public class ByteBufferWrapper {
    private ByteBufferWrapper() {
    }

    public static ByteBuffer allocate(int capacity) {
        return ByteBuffer.allocate(capacity);
    }

    public static ByteBuffer wrap(byte[] array) {
        return ByteBuffer.wrap(array);
    }

    public static ByteBuffer wrap(byte[] array, int offset, int length) {
        return ByteBuffer.wrap(array, offset, length);
    }
}
