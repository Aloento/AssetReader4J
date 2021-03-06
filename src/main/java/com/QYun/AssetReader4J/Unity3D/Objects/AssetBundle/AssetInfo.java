package com.QYun.AssetReader4J.Unity3D.Objects.AssetBundle;

import com.QYun.AssetReader4J.Unity3D.ObjectReader;
import com.QYun.AssetReader4J.Unity3D.Objects.PPtr;
import com.QYun.AssetReader4J.Unity3D.UObject;

public class AssetInfo {
    public int preloadIndex;
    public int preloadSize;
    public PPtr<UObject> asset;

    public AssetInfo(ObjectReader reader) {
        preloadIndex = reader.readInt();
        preloadSize = reader.readInt();
        asset = new PPtr<>(reader, UObject.class);
    }
}
