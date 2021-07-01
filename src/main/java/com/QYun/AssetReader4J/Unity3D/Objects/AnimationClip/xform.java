package com.QYun.AssetReader4J.Unity3D.Objects.AnimationClip;

import com.QYun.AssetReader4J.Unity3D.UObjectReader;

import javax.vecmath.Quat4f;
import javax.vecmath.Vector3f;

public class xform {
    public Vector3f t;
    public Quat4f q;
    public Vector3f s;

    public xform(UObjectReader reader) {
        var version = reader.version();

        if (version[0] > 5 || (version[0] == 5 && version[1] >= 4))
            t = reader.readVector3();
        else {
            var tmp = reader.readVector4();
            t = new Vector3f(tmp.x, tmp.y, tmp.z);//5.4 and up
        }

        q = reader.readQuaternion();

        if (version[0] > 5 || (version[0] == 5 && version[1] >= 4))
            s = reader.readVector3();
        else {
            var tmp = reader.readVector4();
            s = new Vector3f(tmp.x, tmp.y, tmp.z);//5.4 and up
        }
    }
}
