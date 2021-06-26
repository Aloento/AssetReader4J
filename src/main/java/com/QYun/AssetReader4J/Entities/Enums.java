package com.QYun.AssetReader4J.Entities;

public class Enums {
    public static SerializedFileFormatVersion serializedFileFormatVersion(int index) {
        for (var type : SerializedFileFormatVersion.values()) {
            if (type.ordinal() == index)
                return type;
        }
        return SerializedFileFormatVersion.Zero;
    }

    public static BuildTarget buildTarget(int index) {
        for (var type : BuildTarget.values()) {
            if (type.ordinal() == index)
                return type;
        }
        return BuildTarget.UnknownPlatform;
    }

    public enum FileType {
        AssetsFile,
        BundleFile,
        WebFile,
        ResourceFile,
    }

    public enum SerializedFileFormatVersion {
        Zero,
        kUnsupported,
        kUnknown_2,
        kUnknown_3,
        kUnknown_5,
        kUnknown_6,
        kUnknown_7,
        kUnknown_8,
        kUnknown_9,
        kUnknown_10,
        kHasScriptTypeIndex,
        kUnknown_12,
        kHasTypeTreeHashes,
        kUnknown_14,
        kSupportsStrippedObject,
        kRefactoredClassId,
        kRefactorTypeData,
        kRefactorShareableTypeTreeData,
        kTypeTreeNodeWithTypeFlags,
        kSupportsRefObject,
        kStoresTypeDependencies,
        kLargeFilesSupport,
    }

    public enum BuildTarget {
        NoTarget(-2),
        DashboardWidget(1),
        StandaloneOSX,
        StandaloneOSXPPC,
        StandaloneOSXIntel,
        StandaloneWindows,
        WebPlayer,
        WebPlayerStreamed,
        Wii,
        iOS,
        PS3,
        XBOX360,
        Android(13),
        StandaloneGLESEmu,
        NaCl(16),
        StandaloneLinux,
        FlashPlayer,
        StandaloneWindows64,
        WebGL,
        WSAPlayer,
        StandaloneLinux64(24),
        StandaloneLinuxUniversal,
        WP8Player,
        StandaloneOSXIntel64,
        BlackBerry,
        Tizen,
        PSP2,
        PS4,
        PSM,
        XboxOne,
        SamsungTV,
        N3DS,
        WiiU,
        tvOS,
        Switch,
        Lumin,
        Stadia,
        CloudRendering,
        GameCoreXboxSeries,
        GameCoreXboxOne,
        PS5,
        UnknownPlatform(9999),
        ;

        BuildTarget() {
        }

        BuildTarget(int i) {
        }
    }
}
