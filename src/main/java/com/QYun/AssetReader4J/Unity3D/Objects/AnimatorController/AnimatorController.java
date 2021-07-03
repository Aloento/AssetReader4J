package com.QYun.AssetReader4J.Unity3D.Objects.AnimatorController;

import com.QYun.AssetReader4J.Unity3D.Contracts.RuntimeAnimatorController;
import com.QYun.AssetReader4J.Unity3D.ObjectReader;
import com.QYun.AssetReader4J.Unity3D.Objects.AnimationClip.AnimationClip;
import com.QYun.AssetReader4J.Unity3D.Objects.PPtr;
import org.eclipse.collections.api.factory.Lists;
import org.eclipse.collections.api.factory.Maps;
import org.eclipse.collections.api.list.MutableList;
import org.eclipse.collections.api.map.MutableMap;

public class AnimatorController extends RuntimeAnimatorController {
    public MutableList<PPtr<AnimationClip>> m_AnimationClips;

    public AnimatorController(ObjectReader reader) {
        super(reader);
        var m_ControllerSize = reader.readInt();
        var m_Controller = new ControllerConstant(reader);

        int tosSize = reader.readInt();
        MutableList<MutableMap<Integer, String>> m_TOS = Lists.mutable.withInitialCapacity(tosSize);
        for (int i = 0; i < tosSize; i++) {
            MutableMap<Integer, String> tmp = Maps.mutable.empty();
            tmp.put(reader.readInt(), reader.ReadAlignedString());
            m_TOS.add(i, tmp);
        }

        int numClips = reader.readInt();
        m_AnimationClips = Lists.mutable.withInitialCapacity(numClips);
        for (int i = 0; i < numClips; i++) {
            m_AnimationClips.add(i, new PPtr<>(reader));
        }
    }
}
