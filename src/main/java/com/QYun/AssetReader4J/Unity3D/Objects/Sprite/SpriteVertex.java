package com.QYun.AssetReader4J.Unity3D.Objects.Sprite;

import com.QYun.AssetReader4J.Unity3D.ObjectReader;

import javax.vecmath.Vector2f;
import javax.vecmath.Vector3f;

public class SpriteVertex {
    public Vector3f pos;
    public Vector2f uv;

    public SpriteVertex(ObjectReader reader) {
        var version = reader.version();

        pos = reader.ReadVector3();
        if (version[0] < 4 || (version[0] == 4 && version[1] <= 3)) { //4.3 and down
            uv = reader.ReadVector2();
        }
    }
}
