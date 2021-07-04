package com.QYun.AssetReader4J.Unity3D.Objects.SpriteAtlas;

import com.QYun.AssetReader4J.Unity3D.ObjectReader;
import com.QYun.AssetReader4J.Unity3D.Objects.PPtr;
import com.QYun.AssetReader4J.Unity3D.Objects.Texture2D.Texture2D;

public class SpriteAtlasData {
    public PPtr<Texture2D> texture;
    public PPtr<Texture2D> alphaTexture;

    public SpriteAtlasData(ObjectReader reader) {
    }
}
