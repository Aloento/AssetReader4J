package com.QYun.AssetReader4J.Unity3D.Objects.AnimationClip;

import com.QYun.AssetReader4J.Entities.Enums.ClassIDType;
import com.QYun.AssetReader4J.Unity3D.ObjectReader;
import com.QYun.AssetReader4J.Unity3D.Objects.PPtr;
import com.QYun.AssetReader4J.Unity3D.UObject;
import org.eclipse.collections.api.factory.Lists;
import org.eclipse.collections.api.list.MutableList;

public class AnimationClipBindingConstant {
    public GenericBinding[] genericBindings;
    public MutableList<PPtr<UObject>> pptrCurveMapping;

    public AnimationClipBindingConstant(ObjectReader reader) {
        int numBindings = reader.readInt();
        genericBindings = new GenericBinding[numBindings];
        for (int i = 0; i < numBindings; i++) {
            genericBindings[i] = new GenericBinding(reader);
        }

        int numMappings = reader.readInt();
        pptrCurveMapping = Lists.mutable.withInitialCapacity(numMappings);
        for (int i = 0; i < numMappings; i++) {
            pptrCurveMapping.add(i, new PPtr<>(reader, UObject.class));
        }
    }

    public GenericBinding findBinding(int index) {
        int curves = 0;
        for (var b : genericBindings) {
            if (b.typeID == ClassIDType.Transform) {
                switch (b.attribute) {
                    case 1: //kBindTransformPosition
                    case 3: //kBindTransformScale
                    case 4: //kBindTransformEuler
                        curves += 3;
                        break;
                    case 2: //kBindTransformRotation
                        curves += 4;
                        break;
                    default:
                        curves += 1;
                        break;
                }
            } else {
                curves += 1;
            }
            if (curves > index) {
                return b;
            }
        }

        return null;
    }
}
