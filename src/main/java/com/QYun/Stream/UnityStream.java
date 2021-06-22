package com.QYun.Stream;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.nio.ByteOrder;

public class UnityStream extends ByteStream {
    public final long length;

    public UnityStream(byte[] array) {
        super(array);
        length = array.length;
    }

    public UnityStream(byte[] array, int offset, int length) {
        super(array, offset, length);
        this.length = length;
    }

    public UnityStream(InputStream inputStream) throws IOException {
        super(inputStream);
        length = inputStream.available();
    }

    public UnityStream(ByteArrayOutputStream outputStream) {
        super(outputStream);
        length = outputStream.size();
    }

    public UnityStream(File file) throws IOException {
        super(file);
        length = file.length();
    }

    @Override
    public UnityStream setToReadOnly() {
        return (UnityStream) super.setToReadOnly();
    }

    @Override
    public UnityStream setByteOrder(ByteOrder byteOrder) {
        return (UnityStream) super.setByteOrder(byteOrder);
    }
}
