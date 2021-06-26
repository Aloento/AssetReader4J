package com.QYun.AssetReader4J;

import com.QYun.AssetReader4J.Entities.Enums;
import com.QYun.AssetReader4J.Entities.Enums.BuildTarget;
import com.QYun.AssetReader4J.Entities.Enums.SerializedFileFormatVersion;
import com.QYun.AssetReader4J.Entities.Struct.ObjectInfo;
import com.QYun.AssetReader4J.Entities.Struct.SerializedFileHeader;
import com.QYun.AssetReader4J.Entities.Struct.SerializedType;
import com.QYun.AssetReader4J.Entities.Struct.TypeTree;
import com.QYun.util.Stream.UnityStream;

import java.io.File;
import java.nio.ByteOrder;
import java.util.ArrayList;
import java.util.Hashtable;

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
        ObjectsDic = new Hashtable<long, Object>(objectCount);
        for (int i = 0; i < objectCount; i++) {
            ObjectInfo objectInfo = new ObjectInfo();
            if (bigIDEnabled != 0) {
                objectInfo.m_PathID = reader.readInt();
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
            }

            if (header.m_Version.ordinal() < SerializedFileFormatVersion.kHasScriptTypeIndex.ordinal()) {
                objectInfo.isDestroyed = reader.readShort();
            }

            if (header.m_Version.ordinal() >= SerializedFileFormatVersion.kHasScriptTypeIndex.ordinal() && header.m_Version.ordinal() < SerializedFileFormatVersion.kRefactorTypeData.ordinal()) {

            }

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

            } else {

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

    public void setVersion(String version) {

    }
}
