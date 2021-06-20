package com.QYun.AssetReader4J;

import org.apache.commons.lang3.ArrayUtils;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;

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
        ArrayList<byte> bytes = new ArrayList<byte>();
        int i = 0;
        while (pos != fileLen && i < maxLength) {
            byte b = readByte();
            if (b == 0)
                break;
            bytes.add(b);
            i++;
        }
        return new String(ArrayUtils.toPrimitive((Byte[]) bytes.toArray()), StandardCharsets.UTF_8);
    }


}
