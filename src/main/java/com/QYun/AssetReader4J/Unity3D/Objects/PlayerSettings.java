package com.QYun.AssetReader4J.Unity3D.Objects;

import com.QYun.AssetReader4J.Unity3D.ObjectReader;
import com.QYun.AssetReader4J.Unity3D.UObject;

public class PlayerSettings extends UObject {
    public String companyName;
    public String productName;

    public PlayerSettings(ObjectReader reader) {
        super(reader);
        if (version[0] > 5 || (version[0] == 5 && version[1] >= 4)) { //5.4.0 nad up
            var productGUID = reader.readBytes(16);
        }

        var AndroidProfiler = reader.readBoolean();
        //bool AndroidFilterTouchesWhenObscured 2017.2 and up
        //bool AndroidEnableSustainedPerformanceMode 2018 and up
        reader.alignStream();
        int defaultScreenOrientation = reader.readInt();
        int targetDevice = reader.readInt();
        if (version[0] < 5 || (version[0] == 5 && version[1] < 3)) { //5.3 down
            if (version[0] < 5) { //5.0 down
                int targetPlatform = reader.readInt(); //4.0 and up targetGlesGraphics
                if (version[0] > 4 || (version[0] == 4 && version[1] >= 6)) { //4.6 and up
                    var targetIOSGraphics = reader.readInt();
                }
            }
            int targetResolution = reader.readInt();
        } else {
            var useOnDemandResources = reader.readBoolean();
            reader.alignStream();
        }
        if (version[0] > 3 || (version[0] == 3 && version[1] >= 5)) { //3.5 and up
            var accelerometerFrequency = reader.readInt();
        }
        companyName = reader.readAlignedString();
        productName = reader.readAlignedString();
    }
}
