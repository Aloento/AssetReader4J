package com.QYun.AssetReader4J.Unity3D.Objects.AnimatorController;

import com.QYun.AssetReader4J.Unity3D.ObjectReader;

import javax.vecmath.Vector3f;
import javax.vecmath.Vector4f;

public class ValueArray {
    public Boolean[] m_BoolValues;
    public Integer[] m_IntValues;
    public Float[] m_FloatValues;
    public Vector4f[] m_VectorValues;
    public Vector3f[] m_PositionValues;
    public Vector4f[] m_QuaternionValues;
    public Vector3f[] m_ScaleValues;

    public ValueArray(ObjectReader reader) {
        var version = reader.version();

        if (version[0] < 5 || (version[0] == 5 && version[1] < 5)) { //5.5 down
            m_BoolValues = reader.readBooleanArray();
            reader.alignStream();
            m_IntValues = reader.readIntArray();
            m_FloatValues = reader.readFloatArray();
        }

        if (version[0] < 4 || (version[0] == 4 && version[1] < 3)) { //4.3 down
            m_VectorValues = reader.readVector4Array();
        } else {
            int numPosValues = reader.readInt();
            m_PositionValues = new Vector3f[numPosValues];
            for (int i = 0; i < numPosValues; i++) { //5.4 and up
                m_PositionValues[i] = version[0] > 5 || (version[0] == 5 && version[1] >= 4) ? reader.readVector3() : reader.read4ToVector3();
            }

            m_QuaternionValues = reader.readVector4Array();

            int numScaleValues = reader.readInt();
            m_ScaleValues = new Vector3f[numScaleValues];
            for (int i = 0; i < numScaleValues; i++) { //5.4 and up
                m_ScaleValues[i] = version[0] > 5 || (version[0] == 5 && version[1] >= 4) ? reader.readVector3() : reader.read4ToVector3();
            }

            if (version[0] > 5 || (version[0] == 5 && version[1] >= 5)) { //5.5 and up
                m_FloatValues = reader.readFloatArray();
                m_IntValues = reader.readIntArray();
                m_BoolValues = reader.readBooleanArray();
                reader.alignStream();
            }
        }
    }
}
