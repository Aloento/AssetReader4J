package com.QYun.AssetReader4J.Unity3D.Objects.AnimationClip;

import com.QYun.AssetReader4J.Entities.Enums;
import com.QYun.AssetReader4J.Entities.Enums.ClassIDType;
import com.QYun.AssetReader4J.Unity3D.ObjectReader;
import com.QYun.AssetReader4J.Unity3D.Objects.PPtr;
import com.QYun.AssetReader4J.Unity3D.UObject;

public class GenericBinding {
    public int path;
    public int attribute;
    public PPtr<UObject> script;
    public ClassIDType typeID;
    public byte customType;
    public byte isPPtrCurve;

    public GenericBinding(ObjectReader reader) {
        var version = reader.version();
        path = reader.readInt();
        attribute = reader.readInt();
        script = new PPtr<>(reader);
        if (version[0] > 5 || (version[0] == 5 && version[1] >= 6)) { //5.6 and up
            typeID = Enums.classIDType(reader.readInt());
        } else {
            typeID = Enums.classIDType(reader.readShort());
        }
        customType = reader.readByte();
        isPPtrCurve = reader.readByte();
        reader.AlignStream();
    }
}
