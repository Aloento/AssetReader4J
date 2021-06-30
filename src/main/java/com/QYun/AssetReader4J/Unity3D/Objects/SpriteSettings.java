package com.QYun.AssetReader4J.Unity3D.Objects;

import com.QYun.AssetReader4J.Entities.Enums.SpriteMeshType;
import com.QYun.AssetReader4J.Entities.Enums.SpritePackingMode;
import com.QYun.AssetReader4J.Entities.Enums.SpritePackingRotation;
import com.QYun.AssetReader4J.Unity3D.UObjectReader;

public class SpriteSettings {
    public int settingsRaw;

    public int packed;
    public SpritePackingMode packingMode;
    public SpritePackingRotation packingRotation;
    public SpriteMeshType meshType;

    public SpriteSettings(UObjectReader reader) {
        // settingsRaw = reader.readInt();
        //
        // packed = settingsRaw & 1; //1
        // packingMode = (SpritePackingMode)((settingsRaw >> 1) & 1); //1
        // packingRotation = (SpritePackingRotation)((settingsRaw >> 2) & 0xf); //4
        // meshType = (SpriteMeshType)((settingsRaw >> 6) & 1); //1
        // //reserved
        //TODO
    }
}
