package com.QYun.AssetReader4J.Unity3D.Objects.Sprite;

import com.QYun.util.Stream.UnityStream;

public class Rectf {
    public float x;
    public float y;
    public float width;
    public float height;

    public Rectf(UnityStream reader) {
        x = reader.readFloat();
        y = reader.readFloat();
        width = reader.readFloat();
        height = reader.readFloat();
    }
}
