package com.QYun.AssetReader4J.Unity3D.Objects;

import com.QYun.AssetReader4J.Entities.Enums;
import com.QYun.AssetReader4J.Entities.Enums.AnimationType;
import com.QYun.AssetReader4J.Unity3D.Contracts.NamedObject;
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
        if (version[0] >= 5) { //5.0 and up
            m_Legacy = reader.readBoolean();
        } else if (version[0] >= 4) { //4.0 and up
            m_AnimationType = Enums.animationType(reader.readInt());
            if (m_AnimationType == AnimationType.kLegacy)
                m_Legacy = true;
        } else {
            m_Legacy = true;
        }
        m_Compressed = reader.readBoolean();
        if (version[0] > 4 || (version[0] == 4 && version[1] >= 3)) //4.3 and up
        {
            m_UseHighQualityCurve = reader.readBoolean();
        }
        reader.alignStream();
        int numRCurves = reader.readInt();
        m_RotationCurves = new QuaternionCurve[numRCurves];
        for (int i = 0; i < numRCurves; i++) {
            m_RotationCurves[i] = new QuaternionCurve(reader);
        }

        int numCRCurves = reader.readInt();
        m_CompressedRotationCurves = new CompressedAnimationCurve[numCRCurves];
        for (int i = 0; i < numCRCurves; i++) {
            m_CompressedRotationCurves[i] = new CompressedAnimationCurve(reader);
        }

        if (version[0] > 5 || (version[0] == 5 && version[1] >= 3)) { //5.3 and up
            int numEulerCurves = reader.readInt();
            m_EulerCurves = new Vector3Curve[numEulerCurves];
            for (int i = 0; i < numEulerCurves; i++) {
                m_EulerCurves[i] = new Vector3Curve(reader);
            }
        }

        int numPCurves = reader.readInt();
        m_PositionCurves = new Vector3Curve[numPCurves];
        for (int i = 0; i < numPCurves; i++) {
            m_PositionCurves[i] = new Vector3Curve(reader);
        }

        int numSCurves = reader.readInt();
        m_ScaleCurves = new Vector3Curve[numSCurves];
        for (int i = 0; i < numSCurves; i++) {
            m_ScaleCurves[i] = new Vector3Curve(reader);
        }

        int numFCurves = reader.readInt();
        m_FloatCurves = new FloatCurve[numFCurves];
        for (int i = 0; i < numFCurves; i++) {
            m_FloatCurves[i] = new FloatCurve(reader);
        }

        if (version[0] > 4 || (version[0] == 4 && version[1] >= 3)) { //4.3 and up
            int numPtrCurves = reader.readInt();
            m_PPtrCurves = new PPtrCurve[numPtrCurves];
            for (int i = 0; i < numPtrCurves; i++) {
                m_PPtrCurves[i] = new PPtrCurve(reader);
            }
        }

        m_SampleRate = reader.readFloat();
        m_WrapMode = reader.readInt();
        if (version[0] > 3 || (version[0] == 3 && version[1] >= 4)) { //3.4 and up
            m_Bounds = new AABB(reader);
        }
        if (version[0] >= 4) { //4.0 and up
            m_MuscleClipSize = reader.readInt();
            m_MuscleClip = new ClipMuscleConstant(reader);
        }
        if (version[0] > 4 || (version[0] == 4 && version[1] >= 3)) { //4.3 and up
            m_ClipBindingConstant = new AnimationClipBindingConstant(reader);
        }
        if (version[0] > 2018 || (version[0] == 2018 && version[1] >= 3)) { //2018.3 and up
            var m_HasGenericRootTransform = reader.readBoolean();
            var m_HasMotionFloatCurves = reader.readBoolean();
            reader.alignStream();
        }
        int numEvents = reader.readInt();
        m_Events = new AnimationEvent[numEvents];
        for (int i = 0; i < numEvents; i++) {
            m_Events[i] = new AnimationEvent(reader);
        }
        if (version[0] >= 2017) { //2017 and up
            reader.alignStream();
        }
    }
}
