package com.QYun.AssetReader4J.Entities;

import com.QYun.AssetReader4J.Entities.Enums.SerializedFileFormatVersion;
import com.QYun.util.Stream.UnityStream;

import java.io.File;
import java.util.ArrayList;
import java.util.UUID;

public class Struct {
    public static class StreamFile {
        public File file;
        public String fileName;
        public UnityStream stream;
    }

    public static class Header {
        public String signature;
        public int version;
        public String unityVersion;
        public String unityRevision;
        public long size;
        public int compressedBlocksInfoSize;
        public int uncompressedBlocksInfoSize;
        public int flags;
    }

    public static class StorageBlock {
        public int compressedSize;
        public int uncompressedSize;
        public short flags;
    }

    public static class Node {
        public long offset;
        public long size;
        public int flags;
        public String path;
    }

    public static class SerializedFileHeader {
        public int m_MetadataSize;
        public long m_FileSize;
        public SerializedFileFormatVersion m_Version;
        public long m_DataOffset;
        public byte m_Endianess;
        public byte[] m_Reserved;
    }

    public static class SerializedType {
        public int classID;
        public boolean m_IsStrippedType;
        public short m_ScriptTypeIndex = -1;
        public TypeTree m_Type;
        public byte[] m_ScriptID; //Hash128
        public byte[] m_OldTypeHash; //Hash128
        public int[] m_TypeDependencies;
        public String m_KlassName;
        public String m_NameSpace;
        public String m_AsmName;
    }

    public static class TypeTree {
        public ArrayList<TypeTreeNode> m_Nodes;
        public byte[] m_StringBuffer;
    }

    public static class TypeTreeNode {
        public String m_Type;
        public String m_Name;
        public int m_ByteSize;
        public int m_Index;
        public int m_TypeFlags; //m_IsArray
        public int m_Version;
        public int m_MetaFlag;
        public int m_Level;
        public int m_TypeStrOffset;
        public int m_NameStrOffset;
        public long m_RefTypeHash;

        public TypeTreeNode() {
        }

        public TypeTreeNode(String type, String name, int level, boolean align) {
            m_Type = type;
            m_Name = name;
            m_Level = level;
            m_MetaFlag = align ? 0x4000 : 0;
        }
    }

    public static class ObjectInfo {
        public long byteStart;
        public int byteSize;
        public int typeID;
        public int classID;
        public short isDestroyed;
        public byte stripped;
        public long m_PathID;
        public SerializedType serializedType;
    }

    public static class LocalSerializedObjectIdentifier {
        public int localSerializedFileIndex;
        public long localIdentifierInFile;
    }

    public static class FileIdentifier {
        public UUID guid;
        public int type; //enum { kNonAssetType = 0, kDeprecatedCachedAssetType = 1, kSerializedAssetType = 2, kMetaAssetType = 3 };
        public String pathName;
        //custom
        public String fileName;
    }

}
