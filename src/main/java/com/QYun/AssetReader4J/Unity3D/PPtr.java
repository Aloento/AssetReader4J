package com.QYun.AssetReader4J.Unity3D;

import com.QYun.AssetReader4J.Entities.Enums;
import com.QYun.AssetReader4J.SerializedFile;

public class PPtr<T extends UObject> {
    private final SerializedFile assetsFile;
    private final int index = -2; //-2 - Prepare, -1 - Missing
    public int m_FileID;
    public long m_PathID;

    public PPtr(UObjectReader reader) {
        m_FileID = reader.readInt();
        m_PathID = reader.m_Version.ordinal() < Enums.SerializedFileFormatVersion.kUnknown_14.ordinal() ? reader.readInt() : reader.readLong();
        assetsFile = reader.assetsFile;
    }

    private Boolean TryGetAssetsFile(SerializedFile result) {
        result = null;
        if (m_FileID == 0) {
            result = assetsFile;
            return true;
        }

        if (m_FileID > 0 && m_FileID - 1 < assetsFile.m_Externals.size()) {
            var assetsManager = assetsFile.assetsManager;
            var assetsFileList = assetsManager.assetsFileList;
            var assetsFileIndexCache = assetsManager.assetsFileIndexCache;

            if (index == -2) {
                var m_External = assetsFile.m_Externals.get(m_FileID - 1);
                var name = m_External.fileName;
                if (!assetsFileIndexCache.get(name)) {

                }
            }

        }

        return false;
    }

}