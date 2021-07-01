package com.QYun.AssetReader4J.Unity3D.Objects;

import com.QYun.AssetReader4J.Entities.Enums.AnimationType;
import com.QYun.AssetReader4J.Unity3D.Contracts.NamedObject;
import com.QYun.AssetReader4J.Unity3D.QuaternionCurve;
import com.QYun.AssetReader4J.Unity3D.UObjectReader;

public class AnimationClip extends NamedObject {
    public AnimationType m_AnimationType;
    public boolean m_Legacy;
    public boolean m_Compressed;
    public boolean m_UseHighQualityCurve;
    public QuaternionCurve[] m_RotationCurves;
    public CompressedAnimationCurve[] m_CompressedRotationCurves;
    public Vector3Curve[] m_EulerCurves;
    public Vector3Curve[] m_PositionCurves;
    public Vector3Curve[] m_ScaleCurves;
    public FloatCurve[] m_FloatCurves;
    public PPtrCurve[] m_PPtrCurves;
    public float m_SampleRate;
    public int m_WrapMode;
    public AABB m_Bounds;
    public int m_MuscleClipSize;
    public ClipMuscleConstant m_MuscleClip;
    public AnimationClipBindingConstant m_ClipBindingConstant;
    public AnimationEvent[] m_Events;

    public AnimationClip(UObjectReader reader) {
        super(reader);
        // TODO
    }
}
