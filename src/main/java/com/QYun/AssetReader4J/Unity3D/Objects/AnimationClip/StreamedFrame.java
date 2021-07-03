package com.QYun.AssetReader4J.Unity3D.Objects.AnimationClip;

import com.QYun.util.Stream.UnityStream;

public class StreamedFrame {
    public float time;
    public StreamedCurveKey[] keyList;

    public StreamedFrame(UnityStream reader) {
        time = reader.readFloat();

        int numKeys = reader.readInt();
        keyList = new StreamedCurveKey[numKeys];
        for (int i = 0; i < numKeys; i++) {
            keyList[i] = new StreamedCurveKey(reader);
        }
    }
}
