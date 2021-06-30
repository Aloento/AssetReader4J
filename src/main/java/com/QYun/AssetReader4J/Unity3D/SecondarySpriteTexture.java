package com.QYun.AssetReader4J.Unity3D;

import com.QYun.AssetReader4J.Unity3D.Objects.Texture2D;

public class SecondarySpriteTexture {

    public PPtr<Texture2D> texture;
    public String name;

    public SecondarySpriteTexture(UObjectReader reader) {
        texture = new PPtr<>(reader);
        name = reader.readStringToNull();
    }

}
