package com.QYun.AssetReader4J.Unity3D.Objects;

import javax.sound.sampled.Clip;
import javax.vecmath.Vector3f;

public class ClipMuscleConstant {
    public HumanPose m_DeltaPose;
    public xform m_StartX;
    public xform m_StopX;
    public xform m_LeftFootStartX;
    public xform m_RightFootStartX;
    public xform m_MotionStartX;
    public xform m_MotionStopX;
    public Vector3f m_AverageSpeed;
    public Clip m_Clip;
    public float m_StartTime;
    public float m_StopTime;
    public float m_OrientationOffsetY;
    public float m_Level;
    public float m_CycleOffset;
    public float m_AverageAngularSpeed;
    public int[] m_IndexArray;
    public ValueDelta[] m_ValueArrayDelta;
    public float[] m_ValueArrayReferencePose;
    public boolean m_Mirror;
    public boolean m_LoopTime;
    public boolean m_LoopBlend;
    public boolean m_LoopBlendOrientation;
    public boolean m_LoopBlendPositionY;
    public boolean m_LoopBlendPositionXZ;
    public boolean m_StartAtOrigin;
    public boolean m_KeepOriginalOrientation;
    public boolean m_KeepOriginalPositionY;
    public boolean m_KeepOriginalPositionXZ;
    public boolean m_HeightFromFeet;
}
