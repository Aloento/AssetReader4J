package com.QYun.AssetReader4J.Unity3D.Objects;

import com.QYun.AssetReader4J.Unity3D.UObjectReader;
import org.eclipse.collections.api.factory.Lists;
import org.eclipse.collections.api.list.MutableList;

import java.util.function.Supplier;

public class AnimationCurve<T> {
    public MutableList<Keyframe<T>> m_Curve;
    public int m_PreInfinity;
    public int m_PostInfinity;
    public int m_RotationOrder;

    public AnimationCurve(UObjectReader reader, Supplier<T> supplier) {
        var version = reader.version();
        int numCurves = reader.readInt();
        m_Curve = Lists.mutable.withInitialCapacity(numCurves);
        for (int i = 0; i < numCurves; i++) {
            m_Curve.add(i, new Keyframe<>(reader, supplier));
        }

        m_PreInfinity = reader.readInt();
        m_PostInfinity = reader.readInt();
        if (version[0] > 5 || (version[0] == 5 && version[1] >= 3)) { //5.3 and up
            m_RotationOrder = reader.readInt();
        }
    }
}
