package com.QYun.AssetReader4J.Helpers;

import com.QYun.AssetReader4J.Entities.Struct.TypeTree;
import com.QYun.AssetReader4J.Entities.Struct.TypeTreeNode;
import com.QYun.AssetReader4J.Extensions.UObjectReader;

import java.io.IOException;
import java.util.ArrayList;
import java.util.LinkedHashMap;

public class TypeTreeHelper {
    public static String readTypeString(TypeTree m_Type, UObjectReader reader) {
        reader.reset();
        var sb = new StringBuilder();
        var m_Nodes = m_Type.m_Nodes;

        for (int i = 0; i < m_Nodes.size(); i++)
            readStringValue(sb, m_Nodes, reader, i);

        var read = reader.getPos() - reader.byteStart;
        if (read != reader.byteSize)
            new IOException("Error while read type, read " + read + " bytes but expected " + reader.byteSize + "bytes").printStackTrace();

        return sb.toString();
    }

    private static void readStringValue(StringBuilder sb, ArrayList<TypeTreeNode> m_Nodes, UObjectReader reader, int i) {
        var m_Node = m_Nodes.get(i);
        var level = m_Node.m_Level;
        var varTypeStr = m_Node.m_Type;
        var varNameStr = m_Node.m_Name;
        Object value = null;
        var append = true;
        var align = (m_Node.m_MetaFlag & 0x4000) != 0;

        switch (varTypeStr) {
            case "SInt8" -> value = reader.readByte();
            case "UInt8", "char" -> value = reader.readUnsignedByte();
            case "short", "SInt16" -> value = reader.readShort();
            case "UInt16", "unsigned short" -> value = reader.readUnsignedShort();
            case "int", "SInt32", "UInt32", "unsigned int", "Type*" -> value = reader.readInt();
            case "long long", "SInt64", "UInt64", "unsigned long long", "FileSize" -> value = reader.readLong();
            case "float" -> value = reader.readFloat();
            case "double" -> value = reader.readDouble();
            case "bool" -> value = reader.readBoolean();
            case "string" -> {
                append = false;
                var str = reader.readAlignedString();
                sb.append(String.format("%s%s %s = \"%s\"\r\n", "\t".repeat(level), varTypeStr, varNameStr, str));
                i += 3;
            }
            case "map" -> {
                if ((m_Nodes.get(i + 1).m_MetaFlag & 0x4000) != 0)
                    align = true;

                append = false;
                sb.append(String.format("%s%s %s\r\n", "\t".repeat(level), varTypeStr, varNameStr));
                sb.append(String.format("%s%s %s\r\n", "\t".repeat(level + 1), "Array", "Array"));

            }
        }
    }

    public static LinkedHashMap<String, Object> readType(TypeTree m_Types, UObjectReader reader) {
        reader.reset();
        var obj = new LinkedHashMap<String, Object>();
        var m_Nodes = m_Types.m_Nodes;
        for (int i = 1; i < m_Nodes.size(); i++) {
            var m_Node = m_Nodes.get(i);
            var varNameStr = m_Node.m_Name;
            obj.put(varNameStr, readValue(m_Nodes, reader, i));
        }
        var read = reader.getPos() - reader.byteStart;
        if (read != reader.byteSize) {
            new IOException("Error while read type, read " + read + " bytes but expected " + reader.byteSize + "bytes").printStackTrace();
        }
        return obj;
    }

    private static Object readValue(ArrayList<TypeTreeNode> m_Nodes, UObjectReader reader, int i) {
        var m_Node = m_Nodes.get(i);
        var varTypeStr = m_Node.m_Type;
        Object value;
        var align = (m_Node.)

    }


}
