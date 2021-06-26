package com.QYun.AssetReader4J.Entities;

import com.QYun.AssetReader4J.Entities.Enums.SerializedFileFormatVersion;
import com.QYun.util.Stream.UnityStream;

import java.io.File;
import java.util.ArrayList;
import java.util.Hashtable;
import java.util.UUID;

public class Struct {
    public final static Hashtable<Integer, String> StringBuffer = new Hashtable<>() {{
        put(0, "AABB");
        put(5, "AnimationClip");
        put(19, "AnimationCurve");
        put(34, "AnimationState");
        put(49, "Array");
        put(55, "Base");
        put(60, "BitField");
        put(69, "bitset");
        put(76, "bool");
        put(81, "char");
        put(86, "ColorRGBA");
        put(96, "Component");
        put(106, "data");
        put(111, "deque");
        put(117, "double");
        put(124, "dynamic_array");
        put(138, "FastPropertyName");
        put(155, "first");
        put(161, "float");
        put(167, "Font");
        put(172, "GameObject");
        put(183, "Generic Mono");
        put(196, "GradientNEW");
        put(208, "GUID");
        put(213, "GUIStyle");
        put(222, "int");
        put(226, "list");
        put(231, "long long");
        put(241, "map");
        put(245, "Matrix4x4f");
        put(256, "MdFour");
        put(263, "MonoBehaviour");
        put(277, "MonoScript");
        put(288, "m_ByteSize");
        put(299, "m_Curve");
        put(307, "m_EditorClassIdentifier");
        put(331, "m_EditorHideFlags");
        put(349, "m_Enabled");
        put(359, "m_ExtensionPtr");
        put(374, "m_GameObject");
        put(387, "m_Index");
        put(395, "m_IsArray");
        put(405, "m_IsStatic");
        put(416, "m_MetaFlag");
        put(427, "m_Name");
        put(434, "m_ObjectHideFlags");
        put(452, "m_PrefabInternal");
        put(469, "m_PrefabParentObject");
        put(490, "m_Script");
        put(499, "m_StaticEditorFlags");
        put(519, "m_Type");
        put(526, "m_Version");
        put(536, "Object");
        put(543, "pair");
        put(548, "PPtr<Component>");
        put(564, "PPtr<GameObject>");
        put(581, "PPtr<Material>");
        put(596, "PPtr<MonoBehaviour>");
        put(616, "PPtr<MonoScript>");
        put(633, "PPtr<Object>");
        put(646, "PPtr<Prefab>");
        put(659, "PPtr<Sprite>");
        put(672, "PPtr<TextAsset>");
        put(688, "PPtr<Texture>");
        put(702, "PPtr<Texture2D>");
        put(718, "PPtr<Transform>");
        put(734, "Prefab");
        put(741, "Quaternionf");
        put(753, "Rectf");
        put(759, "RectInt");
        put(767, "RectOffset");
        put(778, "second");
        put(785, "set");
        put(789, "short");
        put(795, "size");
        put(800, "SInt16");
        put(807, "SInt32");
        put(814, "SInt64");
        put(821, "SInt8");
        put(827, "staticvector");
        put(840, "string");
        put(847, "TextAsset");
        put(857, "TextMesh");
        put(866, "Texture");
        put(874, "Texture2D");
        put(884, "Transform");
        put(894, "TypelessData");
        put(907, "UInt16");
        put(914, "UInt32");
        put(921, "UInt64");
        put(928, "UInt8");
        put(934, "unsigned int");
        put(947, "unsigned long long");
        put(966, "unsigned short");
        put(981, "vector");
        put(988, "Vector2f");
        put(997, "Vector3f");
        put(1006, "Vector4f");
        put(1015, "m_ScriptingClassIdentifier");
        put(1042, "Gradient");
        put(1051, "Type*");
        put(1057, "int2_storage");
        put(1070, "int3_storage");
        put(1083, "BoundsInt");
        put(1093, "m_CorrespondingSourceObject");
        put(1121, "m_PrefabInstance");
        put(1138, "m_PrefabAsset");
        put(1152, "FileSize");
        put(1161, "Hash128");
    }};

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
