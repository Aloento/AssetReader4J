package com.QYun.AssetReader4J.Unity3D.Objects.AnimationClip;

import com.QYun.AssetReader4J.Unity3D.UObjectReader;

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

    public ClipMuscleConstant(UObjectReader reader) {
        var version = reader.version();
        m_DeltaPose = new HumanPose(reader);
        m_StartX = new xform(reader);
        if (version[0] > 5 || (version[0] == 5 && version[1] >= 5)) { //5.5 and up
            m_StopX = new xform(reader);
        }
        m_LeftFootStartX = new xform(reader);
        m_RightFootStartX = new xform(reader);
        if (version[0] < 5) { //5.0 down
            m_MotionStartX = new xform(reader);
            m_MotionStopX = new xform(reader);
        }
        if (version[0] > 5 || (version[0] == 5 && version[1] >= 4))
            m_AverageSpeed = reader.readVector3();
        else {
            var tmp = reader.readVector4();
            m_AverageSpeed = new Vector3f(tmp.x, tmp.y, tmp.z); //5.4 and up
        }
        m_Clip = new Clip(reader);
        m_StartTime = reader.readFloat();
        m_StopTime = reader.readFloat();
        m_OrientationOffsetY = reader.readFloat();
        m_Level = reader.readFloat();
        m_CycleOffset = reader.readFloat();
        m_AverageAngularSpeed = reader.readFloat();

        m_IndexArray = reader.readInts(reader.readInt());
        if (version[0] < 4 || (version[0] == 4 && version[1] < 3)) {  //4.3 down
            var m_AdditionalCurveIndexArray = reader.readInts(reader.readInt());
        }
        int numDeltas = reader.readInt();
        m_ValueArrayDelta = new ValueDelta[numDeltas];
        for (int i = 0; i < numDeltas; i++) {
            m_ValueArrayDelta[i] = new ValueDelta(reader);
        }
        if (version[0] > 5 || (version[0] == 5 && version[1] >= 3)) { //5.3 and up
            m_ValueArrayReferencePose = reader.readFloats(reader.readInt());
        }

        m_Mirror = reader.readBoolean();
        if (version[0] > 4 || (version[0] == 4 && version[1] >= 3)) { //4.3 and up
            m_LoopTime = reader.readBoolean();
        }
        m_LoopBlend = reader.readBoolean();
        m_LoopBlendOrientation = reader.readBoolean();
        m_LoopBlendPositionY = reader.readBoolean();
        m_LoopBlendPositionXZ = reader.readBoolean();
        if (version[0] > 5 || (version[0] == 5 && version[1] >= 5)) { //5.5 and up
            m_StartAtOrigin = reader.readBoolean();
        }
        m_KeepOriginalOrientation = reader.readBoolean();
        m_KeepOriginalPositionY = reader.readBoolean();
        m_KeepOriginalPositionXZ = reader.readBoolean();
        m_HeightFromFeet = reader.readBoolean();
        reader.alignStream();
    }
}
