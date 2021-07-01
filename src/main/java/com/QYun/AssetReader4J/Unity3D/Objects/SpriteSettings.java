package com.QYun.AssetReader4J.Unity3D.Objects;

import com.QYun.AssetReader4J.Entities.Enums;
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
        settingsRaw = reader.readInt();

        packed = settingsRaw & 1; //1
        packingMode = Enums.spritePackingMode((settingsRaw >> 1) & 1); //1
        packingRotation = Enums.spritePackingRotation((settingsRaw >> 2) & 0xf); //4
        meshType = Enums.spriteMeshType((settingsRaw >> 6) & 1); //1
        //reserved
    }
}
