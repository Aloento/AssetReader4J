package com.QYun.AssetReader4J.Unity3D.Objects;

import com.QYun.AssetReader4J.Unity3D.UObjectReader;

import javax.vecmath.Vector2f;
import javax.vecmath.Vector3f;

public class SpriteVertex {
    public Vector3f pos;
    public Vector2f uv;

    public SpriteVertex(UObjectReader reader) {
        var version = reader.version();

        pos = reader.readVector3();
        if (version[0] < 4 || (version[0] == 4 && version[1] <= 3)) { //4.3 and down
            uv = reader.readVector2();
        }
    }
}
