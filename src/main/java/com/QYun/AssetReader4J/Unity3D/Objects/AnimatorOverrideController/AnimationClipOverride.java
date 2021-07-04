package com.QYun.AssetReader4J.Unity3D.Objects.AnimatorOverrideController;

import com.QYun.AssetReader4J.Unity3D.ObjectReader;
import com.QYun.AssetReader4J.Unity3D.Objects.AnimationClip.AnimationClip;
import com.QYun.AssetReader4J.Unity3D.Objects.PPtr;

public class AnimationClipOverride {
    public PPtr<AnimationClip> m_OriginalClip;
    public PPtr<AnimationClip> m_OverrideClip;

    public AnimationClipOverride(ObjectReader reader) {
        m_OriginalClip = new PPtr<>(reader, AnimationClip.class);
        m_OverrideClip = new PPtr<>(reader, AnimationClip.class);
    }
}
