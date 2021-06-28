package com.QYun.AssetReader4J.Unity3D;

import com.QYun.AssetReader4J.Entities.Enums.BuildTarget;
import com.QYun.AssetReader4J.Entities.Enums.ClassIDType;
import com.QYun.AssetReader4J.Entities.Struct.BuildType;
import com.QYun.AssetReader4J.Entities.Struct.SerializedType;
import com.QYun.AssetReader4J.Entities.Struct.TypeTree;
import com.QYun.AssetReader4J.Helpers.TypeTreeHelper;
import com.QYun.AssetReader4J.SerializedFile;
import org.eclipse.collections.api.map.MutableOrderedMap;

public class UObject {
    public SerializedFile assetsFile;
    public UObjectReader reader;
    public long m_PathID;
    public int[] version;
    public BuildTarget platform;
    public ClassIDType type;
    public SerializedType serializedType;
    public int byteSize;
    protected BuildType buildType;

    public UObject(UObjectReader reader) {
        this.reader = reader;
        reader.reset();
        assetsFile = reader.assetsFile;
        type = reader.type;
        m_PathID = reader.m_PathID;
        version = reader.version();
        buildType = reader.buildType();
        platform = reader.platform;
        serializedType = reader.serializedType;
        byteSize = reader.byteSize;

        if (platform == BuildTarget.NoTarget) {
            var m_ObjectHideFlags = reader.readInt();
        }
    }

    public String dump() {
        if (serializedType.m_Type != null) {
            return TypeTreeHelper.readTypeString(serializedType.m_Type, reader);
        }
        return null;
    }

    public String dump(TypeTree m_Type) {
        if (m_Type != null) {
            return TypeTreeHelper.readTypeString(m_Type, reader);
        }
        return null;
    }

    public MutableOrderedMap<String, Object> toType() {
        if (serializedType.m_Type != null) {
            return TypeTreeHelper.readType(serializedType.m_Type, reader);
        }
        return null;
    }

    public MutableOrderedMap<String, Object> toType(TypeTree m_Type) {
        if (m_Type != null) {
            return TypeTreeHelper.readType(m_Type, reader);
        }
        return null;
    }

    public byte[] getRawData() {
        reader.reset();
        return reader.readBytes(byteSize);
    }
}
