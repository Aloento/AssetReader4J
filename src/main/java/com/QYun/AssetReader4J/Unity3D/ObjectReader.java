package com.QYun.AssetReader4J.Unity3D;

import com.QYun.AssetReader4J.Entities.Enums;
import com.QYun.AssetReader4J.Entities.Enums.BuildTarget;
import com.QYun.AssetReader4J.Entities.Enums.ClassIDType;
import com.QYun.AssetReader4J.Entities.Enums.SerializedFileFormatVersion;
import com.QYun.AssetReader4J.Entities.Struct.BuildType;
import com.QYun.AssetReader4J.Entities.Struct.ObjectInfo;
import com.QYun.AssetReader4J.Entities.Struct.SerializedType;
import com.QYun.AssetReader4J.SerializedFile;
import com.QYun.util.Stream.UnityStream;

public class ObjectReader extends UnityStream {
    public SerializedFile assetsFile;
    public long m_PathID;
    public long byteStart;
    public int byteSize;
    public ClassIDType type;
    public SerializedType serializedType;
    public BuildTarget platform;
    public SerializedFileFormatVersion m_Version;

    public ObjectReader(UnityStream reader, SerializedFile assetsFile, ObjectInfo objectInfo) {
        super(reader);
        this.assetsFile = assetsFile;
        m_PathID = objectInfo.m_PathID;
        byteStart = objectInfo.byteStart;
        byteSize = objectInfo.byteSize;

        type = Enums.classIDType(objectInfo.classID);
        serializedType = objectInfo.serializedType;
        platform = assetsFile.m_TargetPlatform;
        m_Version = assetsFile.header.m_Version;
    }

    public int[] version() {
        return assetsFile.version;
    }

    public BuildType buildType() {
        return assetsFile.buildType;
    }

    public void reset() {
        setPos(Math.toIntExact(byteStart));
    }

}
