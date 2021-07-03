package com.QYun.AssetReader4J.Unity3D.Objects.AnimatorController;

import com.QYun.AssetReader4J.Unity3D.ObjectReader;

public class HumanPoseMask {
    public int word0;
    public int word1;
    public int word2;

    public HumanPoseMask(ObjectReader reader) {
        var version = reader.version();

        word0 = reader.readInt();
        word1 = reader.readInt();
        if (version[0] > 5 || (version[0] == 5 && version[1] >= 2)) { //5.2 and up
            word2 = reader.readInt();
        }
    }
}
