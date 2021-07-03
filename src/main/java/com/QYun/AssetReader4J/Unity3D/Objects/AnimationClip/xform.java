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
        //5.4 and up
        t = version[0] > 5 || (version[0] == 5 && version[1] >= 4) ? reader.readVector3() : reader.read4ToVector3();
        q = reader.readQuaternion();
        //5.4 and up
        s = version[0] > 5 || (version[0] == 5 && version[1] >= 4) ? reader.readVector3() : reader.read4ToVector3();
    }
}
