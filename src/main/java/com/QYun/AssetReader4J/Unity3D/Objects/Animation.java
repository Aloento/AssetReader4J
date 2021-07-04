package com.QYun.AssetReader4J.Unity3D.Objects;

import com.QYun.AssetReader4J.Unity3D.Contracts.Behaviour;
import com.QYun.AssetReader4J.Unity3D.ObjectReader;
import com.QYun.AssetReader4J.Unity3D.Objects.AnimationClip.AnimationClip;
import org.eclipse.collections.api.factory.Lists;
import org.eclipse.collections.api.list.MutableList;

public class Animation extends Behaviour {
    public MutableList<PPtr<AnimationClip>> m_Animations;

    public Animation(ObjectReader reader) {
        super(reader);

        var m_Animation = new PPtr<>(reader, AnimationClip.class);
        int numAnimations = reader.readInt();
        m_Animations = Lists.mutable.withInitialCapacity(numAnimations);
        for (int i = 0; i < numAnimations; i++) {
            m_Animations.add(i, new PPtr<>(reader, AnimationClip.class));
        }
    }
}
