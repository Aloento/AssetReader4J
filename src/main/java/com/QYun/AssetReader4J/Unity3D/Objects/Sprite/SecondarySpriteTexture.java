package com.QYun.AssetReader4J.Unity3D.Objects.Sprite;

import com.QYun.AssetReader4J.Unity3D.Objects.PPtr;
import com.QYun.AssetReader4J.Unity3D.Objects.Texture2D.Texture2D;
import com.QYun.AssetReader4J.Unity3D.UObjectReader;

public class SecondarySpriteTexture {

    public PPtr<Texture2D> texture;
    public String name;

    public SecondarySpriteTexture(UObjectReader reader) {
        texture = new PPtr<>(reader);
        name = reader.readStringToNull();
    }

}
