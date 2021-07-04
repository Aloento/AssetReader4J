package com.QYun.AssetReader4J.Unity3D.Objects.AnimatorOverrideController;

import com.QYun.AssetReader4J.Unity3D.Contracts.RuntimeAnimatorController;
import com.QYun.AssetReader4J.Unity3D.ObjectReader;
import com.QYun.AssetReader4J.Unity3D.Objects.PPtr;

public class AnimatorOverrideController extends RuntimeAnimatorController {
    public PPtr<RuntimeAnimatorController> m_Controller;
    public AnimationClipOverride[] m_Clips;

    public AnimatorOverrideController(ObjectReader reader) {
        super(reader);
        m_Controller = new PPtr<>(reader, RuntimeAnimatorController.class);

        int numOverrides = reader.ReadInt32();
        m_Clips = new AnimationClipOverride[numOverrides];
        for (int i = 0; i < numOverrides; i++) {
            m_Clips[i] = new AnimationClipOverride(reader);
        }
    }
}
