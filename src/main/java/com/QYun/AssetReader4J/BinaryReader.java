package com.QYun.AssetReader4J;

import javax.vecmath.Quat4f;
import javax.vecmath.Vector2f;
import javax.vecmath.Vector3f;
import javax.vecmath.Vector4f;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.nio.charset.StandardCharsets;

public class BinaryReader extends EndianInputStream {
    public BinaryReader(File file, boolean isLittleEndian) throws FileNotFoundException {
        super(file, isLittleEndian);
    }

    public void alignStream() {
        alignStream(4);
    }

    public void alignStream(int alignment) {
        var mod = pos % alignment;
        if (mod != 0)
            pos += alignment - mod;
    }

    public String readAlignedString() throws IOException {
        int length = readInt();
        if (length > 0 && length <= fileLen - pos) {
            var str = new String(readBytes(length), StandardCharsets.UTF_8);
            alignStream();
            return str;
        }
        return "";
    }

    public String readStringToNull() throws IOException {
        return readStringToNull(32767);
    }

    public String readStringToNull(int maxLength) throws IOException {
        var bytes = new ByteArrayOutputStream();
        int i = 0;
        while (pos != fileLen && i < maxLength) {
            byte b = readByte();
            if (b == 0)
                break;
            bytes.write(b);
            i++;
        }
        return bytes.toString(StandardCharsets.UTF_8);
    }

    public Quat4f readQuaternion() throws IOException {
        return new Quat4f(readFloat(), readFloat(), readFloat(), readFloat());
    }

    public Vector2f readVector2() throws IOException {
        return new Vector2f(readFloat(), readFloat());
    }

    public Vector3f readVector3() throws IOException {
        return new Vector3f(readFloat(), readFloat(), readFloat());
    }

    public Vector4f readVector4() throws IOException {
        return new Vector4f(readFloat(), readFloat(), readFloat(), readFloat());
    }

}
