package com.QYun.AssetReader4J.Unity3D.Objects;

import com.QYun.AssetReader4J.Unity3D.PPtr;
import com.QYun.AssetReader4J.Unity3D.UObjectReader;

public class SecondarySpriteTexture {

    public PPtr<Texture2D> texture;
    public String name;

    public SecondarySpriteTexture(UObjectReader reader) {
        texture = new PPtr<>(reader);
        name = reader.readStringToNull();
    }

}
