package com.QYun.util.Stream;

import javax.vecmath.*;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.nio.ByteOrder;
import java.nio.charset.StandardCharsets;

public class UnityStream extends ByteStream {
    public UnityStream(int capacity) {
        super(capacity);
    }

    public UnityStream(byte[] array) {
        super(array);
    }

    public UnityStream(byte[] array, int offset, int length) {
        super(array, offset, length);
    }

    public UnityStream(InputStream inputStream) throws IOException {
        super(inputStream);
    }

    public UnityStream(ByteArrayOutputStream outputStream) {
        super(outputStream);
    }

    public UnityStream(File file) throws IOException {
        super(file);
    }

    public void alignStream() {
        alignStream(4);
    }

    public void alignStream(int alignment) {
        var mod = getPos() % alignment;
        if (mod != 0)
            setPos(getPos() + alignment - mod);
    }

    public String readAlignedString() {
        int length = readInt();
        if (length > 0 && length <= length - getPos()) {
            var str = new String(readBytes(length), StandardCharsets.UTF_8);
            alignStream();
            return str;
        }
        return "";
    }

    public String readStringToNull() {
        return readStringToNull(32767);
    }

    public String readStringToNull(int maxLength) {
        var bytes = new ByteArrayOutputStream();
        int i = 0;
        while (getPos() != capacity() && i < maxLength) {
            byte b = readByte();
            if (b == 0)
                break;
            bytes.write(b);
            i++;
        }
        return bytes.toString(StandardCharsets.UTF_8);
    }

    public Quat4f readQuaternion() {
        return new Quat4f(readFloat(), readFloat(), readFloat(), readFloat());
    }

    public Vector2f readVector2() {
        return new Vector2f(readFloat(), readFloat());
    }

    public Vector3f readVector3() {
        return new Vector3f(readFloat(), readFloat(), readFloat());
    }

    public Vector4f readVector4() {
        return new Vector4f(readFloat(), readFloat(), readFloat(), readFloat());
    }

    public Color4f readColor4() {
        return new Color4f(readFloat(), readFloat(), readFloat(), readFloat());
    }

    public Matrix4f readMatrix() {
        return new Matrix4f(readFloats(16));
    }

    @Override
    public UnityStream setToReadOnly() {
        super.setToReadOnly();
        return this;
    }

    @Override
    public UnityStream setByteOrder(ByteOrder byteOrder) {
        super.setByteOrder(byteOrder);
        return this;
    }
}
