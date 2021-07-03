package com.QYun.AssetReader4J.Unity3D.Objects.Sprite;

import com.QYun.AssetReader4J.Unity3D.ObjectReader;
import com.QYun.AssetReader4J.Unity3D.Objects.PPtr;
import com.QYun.AssetReader4J.Unity3D.Objects.Texture2D.Texture2D;

public class SecondarySpriteTexture {
    public PPtr<Texture2D> texture;
    public String name;

    public SecondarySpriteTexture(ObjectReader reader) {
        texture = new PPtr<>(reader);
        name = reader.ReadStringToNull();
    }
}
