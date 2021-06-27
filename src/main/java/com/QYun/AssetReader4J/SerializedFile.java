package com.QYun.AssetReader4J;

import com.QYun.AssetReader4J.Entities.Enums;
import com.QYun.AssetReader4J.Entities.Enums.BuildTarget;
import com.QYun.AssetReader4J.Entities.Enums.SerializedFileFormatVersion;
import com.QYun.AssetReader4J.Entities.Struct;
import com.QYun.AssetReader4J.Entities.Struct.*;
import com.QYun.util.Stream.UnityStream;

import java.io.File;
import java.nio.ByteOrder;
import java.util.ArrayList;
import java.util.Hashtable;
import java.util.UUID;

public class SerializedFile {
    private final byte m_FileEndianess;
    public AssetsManager assetsManager;
    public UnityStream reader;
    public File file;
    public SerializedFileHeader header;
    public BuildTarget m_TargetPlatform = BuildTarget.UnknownPlatform;
    public String unityVersion = "2.5.0f5";
    public ArrayList<SerializedType> m_Types;
    public int bigIDEnabled = 0;
    public ArrayList<ObjectInfo> m_Objects;
    public ArrayList<Object> Objects;
    public Hashtable<long, Object> ObjectsDic;
    public ArrayList<FileIdentifier> m_Externals;
    public ArrayList<SerializedType> m_RefTypes;
    public String userInformation;
    public String originalPath;
    private ArrayList<LocalSerializedObjectIdentifier> m_ScriptTypes;

    private boolean m_EnableTypeTree = true;

    public SerializedFile(AssetsManager assetsManager, File file, UnityStream reader) {
        this.assetsManager = assetsManager;
        this.reader = reader;
        this.file = file;

        header = new SerializedFileHeader();
        header.m_MetadataSize = reader.readInt();
        header.m_FileSize = reader.readInt();
        header.m_Version = Enums.serializedFileFormatVersion(reader.readInt());
        header.m_DataOffset = reader.readInt();

        if (header.m_Version.ordinal() >= SerializedFileFormatVersion.kUnknown_9.ordinal()) {
            header.m_Endianess = reader.readByte();
            header.m_Reserved = reader.readBytes(3);
            m_FileEndianess = header.m_Endianess;
        } else {
            reader.setPos(Math.toIntExact(header.m_FileSize - header.m_MetadataSize));
            m_FileEndianess = reader.readByte();
        }

        if (header.m_Version.ordinal() >= SerializedFileFormatVersion.kLargeFilesSupport.ordinal()) {
            header.m_MetadataSize = reader.readInt();
            header.m_FileSize = reader.readLong();
            header.m_DataOffset = reader.readLong();
            reader.readLong();
        }

        if (m_FileEndianess == 0)
            reader.setByteOrder(ByteOrder.LITTLE_ENDIAN);

        if (header.m_Version.ordinal() >= SerializedFileFormatVersion.kUnknown_7.ordinal()) {
            unityVersion = reader.readStringToNull();
            setVersion(unityVersion);
        }

        if (header.m_Version.ordinal() >= SerializedFileFormatVersion.kUnknown_8.ordinal())
            m_TargetPlatform = Enums.buildTarget(reader.readInt());

        if (header.m_Version.ordinal() >= SerializedFileFormatVersion.kHasTypeTreeHashes.ordinal()) {
            m_EnableTypeTree = reader.readBoolean();
        }

        int typeCount = reader.readInt();
        m_Types = new ArrayList<>(typeCount);
        for (int i = 0; i < typeCount; i++) {
            m_Types.add(readSerializedType(false));
        }

        if (header.m_Version.ordinal() >= SerializedFileFormatVersion.kUnknown_7.ordinal() && header.m_Version.ordinal() < SerializedFileFormatVersion.kUnknown_14.ordinal()) {
            bigIDEnabled = reader.readInt();
        }

        int objectCount = reader.readInt();
        m_Objects = new ArrayList<>(objectCount);
        Objects = new ArrayList<>(objectCount);
        ObjectsDic = new Hashtable<>(objectCount);
        for (int i = 0; i < objectCount; i++) {
            var objectInfo = new ObjectInfo();
            if (bigIDEnabled != 0) {
                objectInfo.m_PathID = reader.readLong();
            } else if (header.m_Version.ordinal() < SerializedFileFormatVersion.kUnknown_14.ordinal()) {
                objectInfo.m_PathID = reader.readInt();
            } else {
                reader.alignStream();
                objectInfo.m_PathID = reader.readLong();
            }

            if (header.m_Version.ordinal() > SerializedFileFormatVersion.kLargeFilesSupport.ordinal()) {
                objectInfo.byteStart = reader.readLong();
            } else {
                objectInfo.byteStart = reader.readInt();
            }

            objectInfo.byteStart += header.m_DataOffset;
            objectInfo.byteSize = reader.readInt();
            objectInfo.typeID = reader.readInt();
            if (header.m_Version.ordinal() < SerializedFileFormatVersion.kRefactoredClassId.ordinal()) {
                objectInfo.classID = reader.readShort();
                for (var type : m_Types) {
                    if (type.classID == objectInfo.typeID) {
                        objectInfo.serializedType = type;
                        break;
                    }
                }
            } else {
                var type = m_Types.get(objectInfo.typeID);
                objectInfo.serializedType = type;
                objectInfo.classID = type.classID;
            }

            if (header.m_Version.ordinal() < SerializedFileFormatVersion.kHasScriptTypeIndex.ordinal()) {
                objectInfo.isDestroyed = reader.readShort();
            }

            if (header.m_Version.ordinal() >= SerializedFileFormatVersion.kHasScriptTypeIndex.ordinal() && header.m_Version.ordinal() < SerializedFileFormatVersion.kRefactorTypeData.ordinal()) {
                short m_ScriptTypeIndex = reader.readShort();
                if (objectInfo.serializedType != null) {
                    objectInfo.serializedType.m_ScriptTypeIndex = m_ScriptTypeIndex;
                }
            }


            if (header.m_Version.ordinal() == SerializedFileFormatVersion.kSupportsStrippedObject.ordinal() || header.m_Version.ordinal() == SerializedFileFormatVersion.kRefactoredClassId.ordinal()) {
                objectInfo.stripped = reader.readByte();
            }
            m_Objects.add(objectInfo);
        }

        if (header.m_Version.ordinal() >= SerializedFileFormatVersion.kHasScriptTypeIndex.ordinal()) {
            int scriptCount = reader.readInt();
            m_ScriptTypes = new ArrayList<>(scriptCount);
            for (int i = 0; i < scriptCount; i++) {
                LocalSerializedObjectIdentifier m_ScriptType = new LocalSerializedObjectIdentifier();
                m_ScriptType.localSerializedFileIndex = reader.readInt();
                if (header.m_Version.ordinal() < SerializedFileFormatVersion.kUnknown_14.ordinal()) {
                    m_ScriptType.localIdentifierInFile = reader.readInt();
                } else {
                    reader.alignStream();
                    m_ScriptType.localIdentifierInFile = reader.readLong();
                }
                m_ScriptTypes.add(m_ScriptType);
            }
        }

        int externalsCount = reader.readInt();
        m_Externals = new ArrayList<>(externalsCount);
        for (int i = 0; i < externalsCount; i++) {
            FileIdentifier m_External = new FileIdentifier();
            if (header.m_Version.ordinal() >= SerializedFileFormatVersion.kUnknown_6.ordinal()) {
                String tempEmpty = reader.readStringToNull();
            }
            if (header.m_Version.ordinal() >= SerializedFileFormatVersion.kUnknown_5.ordinal()) {
                m_External.guid = UUID.nameUUIDFromBytes(reader.readBytes(16));
                m_External.type = reader.readInt();
            }
            m_External.pathName = reader.readStringToNull();
            m_External.fileName = new File(m_External.pathName).getName();
            m_Externals.add(m_External);
        }

        if (header.m_Version.ordinal() >= SerializedFileFormatVersion.kSupportsRefObject.ordinal()) {
            int refTypesCount = reader.readInt();
            m_RefTypes = new ArrayList<>(refTypesCount);
            for (int i = 0; i < refTypesCount; i++) {
                m_RefTypes.add(readSerializedType(true));
            }
        }

        if (header.m_Version.ordinal() >= SerializedFileFormatVersion.kUnknown_5.ordinal()) {
            userInformation = reader.readStringToNull();
        }

    }

    public static boolean isSerializedFile(UnityStream reader) {
        if (reader.trueLen < 20)
            return false;

        int m_MetadataSize = reader.readInt();
        long m_FileSize = reader.readInt();
        int m_Version = reader.readInt();
        long m_DataOffset = reader.readInt();
        byte m_Endianess = reader.readByte();
        byte[] m_Reserved = reader.readBytes(3);

        if (m_Version >= 22) {
            if (reader.trueLen < 48)
                return false;

            m_MetadataSize = reader.readInt();
            m_FileSize = reader.readLong();
            m_DataOffset = reader.readLong();
        }
        if (m_FileSize != reader.trueLen || m_DataOffset > reader.trueLen) {
            reader.rewind();
            return false;
        }

        reader.rewind();
        return true;
    }

    private SerializedType readSerializedType(boolean isRefType) {
        var type = new SerializedType();
        type.classID = reader.readInt();

        if (header.m_Version.ordinal() >= SerializedFileFormatVersion.kRefactoredClassId.ordinal())
            type.m_IsStrippedType = reader.readBoolean();

        if (header.m_Version.ordinal() >= SerializedFileFormatVersion.kRefactorTypeData.ordinal())
            type.m_ScriptTypeIndex = reader.readShort();

        if (header.m_Version.ordinal() >= SerializedFileFormatVersion.kHasTypeTreeHashes.ordinal()) {
            if (isRefType && type.m_ScriptTypeIndex >= 0) {
                type.m_ScriptID = reader.readBytes(16);
            } else if ((header.m_Version.ordinal() < SerializedFileFormatVersion.kRefactoredClassId.ordinal() && type.classID < 0)
                    || (header.m_Version.ordinal() >= SerializedFileFormatVersion.kRefactoredClassId.ordinal() && type.classID == 114)) {
                type.m_ScriptID = reader.readBytes(16);
            }
            type.m_OldTypeHash = reader.readBytes(16);
        }

        if (m_EnableTypeTree) {
            type.m_Type = new TypeTree();
            type.m_Type.m_Nodes = new ArrayList<>();
            if (header.m_Version.ordinal() >= SerializedFileFormatVersion.kUnknown_12.ordinal()
                    || header.m_Version.ordinal() == SerializedFileFormatVersion.kUnknown_10.ordinal()) {
                typeTreeBlobRead(type.m_Type);
            } else {
                readTypeTree(type.m_Type);
            }

            if (header.m_Version.ordinal() >= SerializedFileFormatVersion.kStoresTypeDependencies.ordinal()) {
                if (isRefType) {
                    type.m_KlassName = reader.readStringToNull();
                    type.m_NameSpace = reader.readStringToNull();
                    type.m_AsmName = reader.readStringToNull();
                } else {
                    type.m_TypeDependencies = reader.readInts(reader.readInt());
                }
            }
        }

        return type;
    }

    private void readTypeTree(TypeTree m_Type) {
        readTypeTree(m_Type, 0);
    }

    private void readTypeTree(TypeTree m_Type, int level) {
        var typeTreeNode = new TypeTreeNode();
        m_Type.m_Nodes.add(typeTreeNode);
        typeTreeNode.m_Level = level;
        typeTreeNode.m_Type = reader.readStringToNull();
        typeTreeNode.m_Name = reader.readStringToNull();
        typeTreeNode.m_ByteSize = reader.readInt();
        if (header.m_Version.ordinal() == SerializedFileFormatVersion.kUnknown_2.ordinal()) {
            var variableCount = reader.readInt();
        }
        if (header.m_Version.ordinal() != SerializedFileFormatVersion.kUnknown_3.ordinal()) {
            typeTreeNode.m_Index = reader.readInt();
        }
        typeTreeNode.m_TypeFlags = reader.readInt();
        typeTreeNode.m_Version = reader.readInt();
        if (header.m_Version.ordinal() != SerializedFileFormatVersion.kUnknown_3.ordinal()) {
            typeTreeNode.m_MetaFlag = reader.readInt();
        }

        int childrenCount = reader.readInt();
        for (int i = 0; i < childrenCount; i++) {
            readTypeTree(m_Type, level + 1);
        }
    }

    private void typeTreeBlobRead(TypeTree m_Type) {
        int numberOfNodes = reader.readInt();
        int stringBufferSize = reader.readInt();

        for (int i = 0; i < numberOfNodes; i++) {
            var typeTreeNode = new TypeTreeNode();
            m_Type.m_Nodes.add(typeTreeNode);
            typeTreeNode.m_Version = reader.readShort();
            typeTreeNode.m_Level = reader.readByte();
            typeTreeNode.m_TypeFlags = reader.readByte();
            typeTreeNode.m_TypeStrOffset = reader.readInt();
            typeTreeNode.m_NameStrOffset = reader.readInt();
            typeTreeNode.m_ByteSize = reader.readInt();
            typeTreeNode.m_Index = reader.readInt();
            typeTreeNode.m_MetaFlag = reader.readInt();
            if (header.m_Version.ordinal() >= SerializedFileFormatVersion.kTypeTreeNodeWithTypeFlags.ordinal())
                typeTreeNode.m_RefTypeHash = reader.readShort();
        }
        m_Type.m_StringBuffer = reader.readBytes(stringBufferSize);

        var stringBufferReader = new UnityStream(m_Type.m_StringBuffer);
        for (int i = 0; i < numberOfNodes; i++) {
            var m_Node = m_Type.m_Nodes.get(i);
            m_Node.m_Type = readString(stringBufferReader, m_Node.m_TypeStrOffset);
            m_Node.m_Name = readString(stringBufferReader, m_Node.m_NameStrOffset);
        }
    }

    private String readString(UnityStream reader, int value) {
        var isOffset = (value & 0x80000000) == 0;
        if (isOffset) {
            reader.setPos(value);
            return reader.readStringToNull();
        }

        int offset = value & 0x7FFFFFFF;
        String str = Struct.stringBuffer.get(offset);
        if (str != null)
            return str;

        return String.valueOf(offset);
    }

    public void addObject(Object obj) {
        Objects.add(obj);
        //ObjectsDic.Add(obj.m_PathID, obj);
    }

    public void setVersion(String version) {
        unityVersion = version;
    }
}
