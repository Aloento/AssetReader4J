package com.QYun.AssetReader4J.Entities;

public class Enums {
    public static SerializedFileFormatVersion serializedFileFormatVersion(int index) {
        for (var type : SerializedFileFormatVersion.values()) {
            if (type.ordinal() == index)
                return type;
        }
        return SerializedFileFormatVersion.Null;
    }

    public static BuildTarget buildTarget(int index) {
        for (var type : BuildTarget.values()) {
            if (type.index() == index)
                return type;
        }
        return BuildTarget.UnknownPlatform;
    }

    public static ClassIDType classIDType(int index) {
        for (var type : ClassIDType.values()) {
            if (type.index() == index)
                return type;
        }
        return ClassIDType.UnknownType;
    }

    public static TextureFormat textureFormat(int index) {
        for (var type : TextureFormat.values()) {
            if (type.index() == index)
                return type;
        }
        return TextureFormat.Null;
    }

    public static GfxPrimitiveType gfxPrimitiveType(int index) {
        for (var type : GfxPrimitiveType.values()) {
            if (type.ordinal() == index)
                return type;
        }
        return GfxPrimitiveType.Null;
    }

    public static SpritePackingMode spritePackingMode(int index) {
        for (var type : SpritePackingMode.values()) {
            if (type.ordinal() == index)
                return type;
        }
        return SpritePackingMode.Null;
    }

    public static SpritePackingRotation spritePackingRotation(int index) {
        for (var type : SpritePackingRotation.values()) {
            if (type.ordinal() == index)
                return type;
        }
        return SpritePackingRotation.Null;
    }

    public static SpriteMeshType spriteMeshType(int index) {
        for (var type : SpriteMeshType.values()) {
            if (type.ordinal() == index)
                return type;
        }
        return SpriteMeshType.Null;
    }

    public static AnimationType animationType(int index) {
        for (var type : AnimationType.values()) {
            if (type.ordinal() == index)
                return type;
        }
        return AnimationType.Null;
    }

    public static PassType passType(int index) {
        for (var type : PassType.values()) {
            if (type.ordinal() == index)
                return type;
        }
        return PassType.Null;
    }

    public static SerializedPropertyType serializedPropertyType(int index) {
        for (var type : SerializedPropertyType.values()) {
            if (type.ordinal() == index)
                return type;
        }
        return SerializedPropertyType.Null;
    }

    public static FogMode fogMode(int index) {
        for (var type : FogMode.values()) {
            if (type.ordinal() == index)
                return type;
        }
        return FogMode.Null;
    }

    public static ShaderGpuProgramType shaderGpuProgramType(int index) {
        for (var type : ShaderGpuProgramType.values()) {
            if (type.ordinal() == index)
                return type;
        }
        return ShaderGpuProgramType.Null;
    }

    public static TextureDimension textureDimension(int index) {
        for (var type : TextureDimension.values()) {
            if (type.index() == index)
                return type;
        }
        return TextureDimension.Null;
    }

    public static ShaderCompilerPlatform shaderCompilerPlatform(int index) {
        for (var type : ShaderCompilerPlatform.values()) {
            if (type.ordinal() == index)
                return type;
        }
        return ShaderCompilerPlatform.Null;
    }

    public static AudioType audioType(int index) {
        for (var type : AudioType.values()) {
            if (type.index() == index)
                return type;
        }
        return AudioType.UNKNOWN;
    }

    public static AudioCompressionFormat audioCompressionFormat(int index) {
        for (var type : AudioCompressionFormat.values()) {
            if (type.ordinal() == index)
                return type;
        }
        return AudioCompressionFormat.Null;
    }

    public enum FileType {
        AssetsFile,
        BundleFile,
        WebFile,
        ResourceFile,
    }

    public enum SerializedFileFormatVersion {
        Null,
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
        StandaloneOSX(2),
        StandaloneOSXPPC(3),
        StandaloneOSXIntel(4),
        StandaloneWindows(5),
        WebPlayer(6),
        WebPlayerStreamed(7),
        Wii(8),
        iOS(9),
        PS3(10),
        XBOX360(11),
        Android(13),
        StandaloneGLESEmu(14),
        NaCl(16),
        StandaloneLinux(17),
        FlashPlayer(18),
        StandaloneWindows64(19),
        WebGL(20),
        WSAPlayer(21),
        StandaloneLinux64(24),
        StandaloneLinuxUniversal(25),
        WP8Player(26),
        StandaloneOSXIntel64(27),
        BlackBerry(28),
        Tizen(29),
        PSP2(30),
        PS4(31),
        PSM(32),
        XboxOne(33),
        SamsungTV(34),
        N3DS(35),
        WiiU(36),
        tvOS(37),
        Switch(38),
        Lumin(39),
        Stadia(40),
        CloudRendering(41),
        GameCoreXboxSeries(42),
        GameCoreXboxOne(43),
        PS5(44),
        UnknownPlatform(9999),
        ;

        private final int index;

        BuildTarget(int i) {
            this.index = i;
        }

        public int index() {
            return index;
        }
    }

    public enum ClassIDType {
        UnknownType(-1),
        Object(0),
        GameObject(1),
        Component(2),
        LevelGameManager(3),
        Transform(4),
        TimeManager(5),
        GlobalGameManager(6),
        Behaviour(8),
        GameManager(9),
        AudioManager(11),
        ParticleAnimator(12),
        InputManager(13),
        EllipsoidParticleEmitter(15),
        Pipeline(17),
        EditorExtension(18),
        Physics2DSettings(19),
        Camera(20),
        Material(21),
        MeshRenderer(23),
        Renderer(25),
        ParticleRenderer(26),
        Texture(27),
        Texture2D(28),
        OcclusionCullingSettings(29),
        GraphicsSettings(30),
        MeshFilter(33),
        OcclusionPortal(41),
        Mesh(43),
        Skybox(45),
        QualitySettings(47),
        Shader(48),
        TextAsset(49),
        Rigidbody2D(50),
        Physics2DManager(51),
        Collider2D(53),
        Rigidbody(54),
        PhysicsManager(55),
        Collider(56),
        Joint(57),
        CircleCollider2D(58),
        HingeJoint(59),
        PolygonCollider2D(60),
        BoxCollider2D(61),
        PhysicsMaterial2D(62),
        MeshCollider(64),
        BoxCollider(65),
        CompositeCollider2D(66),
        EdgeCollider2D(68),
        CapsuleCollider2D(70),
        ComputeShader(72),
        AnimationClip(74),
        ConstantForce(75),
        WorldParticleCollider(76),
        TagManager(78),
        AudioListener(81),
        AudioSource(82),
        AudioClip(83),
        RenderTexture(84),
        CustomRenderTexture(86),
        MeshParticleEmitter(87),
        ParticleEmitter(88),
        Cubemap(89),
        Avatar(90),
        AnimatorController(91),
        GUILayer(92),
        RuntimeAnimatorController(93),
        ScriptMapper(94),
        Animator(95),
        TrailRenderer(96),
        DelayedCallManager(98),
        TextMesh(102),
        RenderSettings(104),
        Light(108),
        CGProgram(109),
        BaseAnimationTrack(110),
        Animation(111),
        MonoBehaviour(114),
        MonoScript(115),
        MonoManager(116),
        Texture3D(117),
        NewAnimationTrack(118),
        Projector(119),
        LineRenderer(120),
        Flare(121),
        Halo(122),
        LensFlare(123),
        FlareLayer(124),
        HaloLayer(125),
        NavMeshAreas(126),
        NavMeshProjectSettings(126),
        HaloManager(127),
        Font(128),
        PlayerSettings(129),
        NamedObject(130),
        GUITexture(131),
        GUIText(132),
        GUIElement(133),
        PhysicMaterial(134),
        SphereCollider(135),
        CapsuleCollider(136),
        SkinnedMeshRenderer(137),
        FixedJoint(138),
        RaycastCollider(140),
        BuildSettings(141),
        AssetBundle(142),
        CharacterController(143),
        CharacterJoint(144),
        SpringJoint(145),
        WheelCollider(146),
        ResourceManager(147),
        NetworkView(148),
        NetworkManager(149),
        PreloadData(150),
        MovieTexture(152),
        ConfigurableJoint(153),
        TerrainCollider(154),
        MasterServerInterface(155),
        TerrainData(156),
        LightmapSettings(157),
        WebCamTexture(158),
        EditorSettings(159),
        InteractiveCloth(160),
        ClothRenderer(161),
        EditorUserSettings(162),
        SkinnedCloth(163),
        AudioReverbFilter(164),
        AudioHighPassFilter(165),
        AudioChorusFilter(166),
        AudioReverbZone(167),
        AudioEchoFilter(168),
        AudioLowPassFilter(169),
        AudioDistortionFilter(170),
        SparseTexture(171),
        AudioBehaviour(180),
        AudioFilter(181),
        WindZone(182),
        Cloth(183),
        SubstanceArchive(184),
        ProceduralMaterial(185),
        ProceduralTexture(186),
        Texture2DArray(187),
        CubemapArray(188),
        OffMeshLink(191),
        OcclusionArea(192),
        Tree(193),
        NavMeshObsolete(194),
        NavMeshAgent(195),
        NavMeshSettings(196),
        LightProbesLegacy(197),
        ParticleSystem(198),
        ParticleSystemRenderer(199),
        ShaderVariantCollection(200),
        LODGroup(205),
        BlendTree(206),
        Motion(207),
        NavMeshObstacle(208),
        SortingGroup(210),
        SpriteRenderer(212),
        Sprite(213),
        CachedSpriteAtlas(214),
        ReflectionProbe(215),
        ReflectionProbes(216),
        Terrain(218),
        LightProbeGroup(220),
        AnimatorOverrideController(221),
        CanvasRenderer(222),
        Canvas(223),
        RectTransform(224),
        CanvasGroup(225),
        BillboardAsset(226),
        BillboardRenderer(227),
        SpeedTreeWindAsset(228),
        AnchoredJoint2D(229),
        Joint2D(230),
        SpringJoint2D(231),
        DistanceJoint2D(232),
        HingeJoint2D(233),
        SliderJoint2D(234),
        WheelJoint2D(235),
        ClusterInputManager(236),
        BaseVideoTexture(237),
        NavMeshData(238),
        AudioMixer(240),
        AudioMixerController(241),
        AudioMixerGroupController(243),
        AudioMixerEffectController(244),
        AudioMixerSnapshotController(245),
        PhysicsUpdateBehaviour2D(246),
        ConstantForce2D(247),
        Effector2D(248),
        AreaEffector2D(249),
        PointEffector2D(250),
        PlatformEffector2D(251),
        SurfaceEffector2D(252),
        BuoyancyEffector2D(253),
        RelativeJoint2D(254),
        FixedJoint2D(255),
        FrictionJoint2D(256),
        TargetJoint2D(257),
        LightProbes(258),
        LightProbeProxyVolume(259),
        SampleClip(271),
        AudioMixerSnapshot(272),
        AudioMixerGroup(273),
        NScreenBridge(280),
        AssetBundleManifest(290),
        UnityAdsManager(292),
        RuntimeInitializeOnLoadManager(300),
        CloudWebServicesManager(301),
        UnityAnalyticsManager(303),
        CrashReportManager(304),
        PerformanceReportingManager(305),
        UnityConnectSettings(310),
        AvatarMask(319),
        PlayableDirector(320),
        VideoPlayer(328),
        VideoClip(329),
        ParticleSystemForceField(330),
        SpriteMask(331),
        WorldAnchor(362),
        OcclusionCullingData(363),
        //kLargestRuntimeClassID ( 364
        SmallestEditorClassID(1000),
        PrefabInstance(1001),
        EditorExtensionImpl(1002),
        AssetImporter(1003),
        AssetDatabaseV1(1004),
        Mesh3DSImporter(1005),
        TextureImporter(1006),
        ShaderImporter(1007),
        ComputeShaderImporter(1008),
        AudioImporter(1020),
        HierarchyState(1026),
        GUIDSerializer(1027),
        AssetMetaData(1028),
        DefaultAsset(1029),
        DefaultImporter(1030),
        TextScriptImporter(1031),
        SceneAsset(1032),
        NativeFormatImporter(1034),
        MonoImporter(1035),
        AssetServerCache(1037),
        LibraryAssetImporter(1038),
        ModelImporter(1040),
        FBXImporter(1041),
        TrueTypeFontImporter(1042),
        MovieImporter(1044),
        EditorBuildSettings(1045),
        DDSImporter(1046),
        InspectorExpandedState(1048),
        AnnotationManager(1049),
        PluginImporter(1050),
        EditorUserBuildSettings(1051),
        PVRImporter(1052),
        ASTCImporter(1053),
        KTXImporter(1054),
        IHVImageFormatImporter(1055),
        AnimatorStateTransition(1101),
        AnimatorState(1102),
        HumanTemplate(1105),
        AnimatorStateMachine(1107),
        PreviewAnimationClip(1108),
        AnimatorTransition(1109),
        SpeedTreeImporter(1110),
        AnimatorTransitionBase(1111),
        SubstanceImporter(1112),
        LightmapParameters(1113),
        LightingDataAsset(1120),
        GISRaster(1121),
        GISRasterImporter(1122),
        CadImporter(1123),
        SketchUpImporter(1124),
        BuildReport(1125),
        PackedAssets(1126),
        VideoClipImporter(1127),
        ActivationLogComponent(2000),
        //kLargestEditorClassID ( 2001
        //kClassIdOutOfHierarchy ( 100000
        //int ( 100000),
        //bool ( 100001),
        //float ( 100002),
        MonoObject(100003),
        Collision(100004),
        Vector3f(100005),
        RootMotionData(100006),
        Collision2D(100007),
        AudioMixerLiveUpdateFloat(100008),
        AudioMixerLiveUpdateBool(100009),
        Polygon2D(100010),
        //void ( 100011),
        TilemapCollider2D(19719996),
        AssetImporterLog(41386430),
        VFXRenderer(73398921),
        SerializableManagedRefTestClass(76251197),
        Grid(156049354),
        ScenesUsingAssets(156483287),
        ArticulationBody(171741748),
        Preset(181963792),
        EmptyObject(277625683),
        IConstraint(285090594),
        TestObjectWithSpecialLayoutOne(293259124),
        AssemblyDefinitionReferenceImporter(294290339),
        SiblingDerived(334799969),
        TestObjectWithSerializedMapStringNonAlignedStruct(342846651),
        SubDerived(367388927),
        AssetImportInProgressProxy(369655926),
        PluginBuildInfo(382020655),
        EditorProjectAccess(426301858),
        PrefabImporter(468431735),
        TestObjectWithSerializedArray(478637458),
        TestObjectWithSerializedAnimationCurve(478637459),
        TilemapRenderer(483693784),
        ScriptableCamera(488575907),
        SpriteAtlasAsset(612988286),
        SpriteAtlasDatabase(638013454),
        AudioBuildInfo(641289076),
        CachedSpriteAtlasRuntimeData(644342135),
        RendererFake(646504946),
        AssemblyDefinitionReferenceAsset(662584278),
        BuiltAssetBundleInfoSet(668709126),
        SpriteAtlas(687078895),
        RayTracingShaderImporter(747330370),
        RayTracingShader(825902497),
        LightingSettings(850595691),
        PlatformModuleSetup(877146078),
        VersionControlSettings(890905787),
        AimConstraint(895512359),
        VFXManager(937362698),
        VisualEffectSubgraph(994735392),
        VisualEffectSubgraphOperator(994735403),
        VisualEffectSubgraphBlock(994735404),
        LocalizationImporter(1027052791),
        Derived(1091556383),
        PropertyModificationsTargetTestObject(1111377672),
        ReferencesArtifactGenerator(1114811875),
        AssemblyDefinitionAsset(1152215463),
        SceneVisibilityState(1154873562),
        LookAtConstraint(1183024399),
        SpriteAtlasImporter(1210832254),
        MultiArtifactTestImporter(1223240404),
        GameObjectRecorder(1268269756),
        LightingDataAssetParent(1325145578),
        PresetManager(1386491679),
        TestObjectWithSpecialLayoutTwo(1392443030),
        StreamingManager(1403656975),
        LowerResBlitTexture(1480428607),
        StreamingController(1542919678),
        RenderPassAttachment(1571458007),
        TestObjectVectorPairStringBool(1628831178),
        GridLayout(1742807556),
        AssemblyDefinitionImporter(1766753193),
        ParentConstraint(1773428102),
        FakeComponent(1803986026),
        PositionConstraint(1818360608),
        RotationConstraint(1818360609),
        ScaleConstraint(1818360610),
        Tilemap(1839735485),
        PackageManifest(1896753125),
        PackageManifestImporter(1896753126),
        TerrainLayer(1953259897),
        SpriteShapeRenderer(1971053207),
        NativeObjectType(1977754360),
        TestObjectWithSerializedMapStringBool(1981279845),
        SerializableManagedHost(1995898324),
        VisualEffectAsset(2058629509),
        VisualEffectImporter(2058629510),
        VisualEffectResource(2058629511),
        VisualEffectObject(2059678085),
        VisualEffect(2083052967),
        LocalizationAsset(2083778819),
        ScriptedImporter(2089858483),
        ;

        private final int index;

        ClassIDType(int i) {
            this.index = i;
        }

        public int index() {
            return index;
        }
    }

    public enum TextureFormat {
        Null(0),
        Alpha8(1),
        ARGB4444(2),
        RGB24(3),
        RGBA32(4),
        ARGB32(5),
        RGB565(7),
        R16(9),
        DXT1(10),
        DXT5(12),
        RGBA4444(13),
        BGRA32(14),
        RHalf(15),
        RGHalf(16),
        RGBAHalf(17),
        RFloat(18),
        RGFloat(19),
        RGBAFloat(20),
        YUY2(21),
        RGB9e5Float(22),
        BC4(26),
        BC5(27),
        BC6H(24),
        BC7(25),
        DXT1Crunched(28),
        DXT5Crunched(29),
        PVRTC_RGB2(30),
        PVRTC_RGBA2(31),
        PVRTC_RGB4(32),
        PVRTC_RGBA4(33),
        ETC_RGB4(34),
        ATC_RGB4(35),
        ATC_RGBA8(36),
        EAC_R(41),
        EAC_R_SIGNED(42),
        EAC_RG(43),
        EAC_RG_SIGNED(44),
        ETC2_RGB(45),
        ETC2_RGBA1(46),
        ETC2_RGBA8(47),
        ASTC_RGB_4x4(48),
        ASTC_RGB_5x5(49),
        ASTC_RGB_6x6(50),
        ASTC_RGB_8x8(51),
        ASTC_RGB_10x10(52),
        ASTC_RGB_12x12(53),
        ASTC_RGBA_4x4(54),
        ASTC_RGBA_5x5(55),
        ASTC_RGBA_6x6(56),
        ASTC_RGBA_8x8(57),
        ASTC_RGBA_10x10(58),
        ASTC_RGBA_12x12(59),
        ETC_RGB4_3DS(60),
        ETC_RGBA8_3DS(61),
        RG16(62),
        R8(63),
        ETC_RGB4Crunched(64),
        ETC2_RGBA8Crunched(65),
        ASTC_HDR_4x4(66),
        ASTC_HDR_5x5(67),
        ASTC_HDR_6x6(68),
        ASTC_HDR_8x8(69),
        ASTC_HDR_10x10(70),
        ASTC_HDR_12x12(71),
        RG32(72),
        RGB48(73),
        RGBA64(74),
        ;

        private final int index;

        TextureFormat(int i) {
            this.index = i;
        }

        public int index() {
            return index;
        }
    }

    public enum GfxPrimitiveType {
        kPrimitiveTriangles,
        kPrimitiveTriangleStrip,
        kPrimitiveQuads,
        kPrimitiveLines,
        kPrimitiveLineStrip,
        kPrimitivePoints,
        Null,
    }

    public enum SpritePackingMode {
        kSPMTight,
        kSPMRectangle,
        Null,
    }

    public enum SpritePackingRotation {
        kSPRNone,
        kSPRFlipHorizontal,
        kSPRFlipVertical,
        kSPRRotate180,
        kSPRRotate90,
        Null,
    }

    public enum SpriteMeshType {
        kSpriteMeshTypeFullRect,
        kSpriteMeshTypeTight,
        Null,
    }

    public enum AnimationType {
        kLegacy,
        kGeneric,
        kHumanoid,
        Null,
    }

    public enum AudioType {
        UNKNOWN(0),
        ACC(1),
        AIFF(2),
        IT(10),
        MOD(12),
        MPEG(13),
        OGGVORBIS(14),
        S3M(17),
        WAV(20),
        XM(21),
        XMA(22),
        VAG(23),
        AUDIOQUEUE(24),
        ;

        private final int index;

        AudioType(int i) {
            this.index = i;
        }

        public int index() {
            return index;
        }
    }

    public enum AudioCompressionFormat {
        PCM,
        Vorbis,
        ADPCM,
        MP3,
        VAG,
        HEVAG,
        XMA,
        AAC,
        GCADPCM,
        ATRAC9,
        Null,
    }

    public enum TextureDimension {
        kTexDimUnknown(-1),
        kTexDimNone(0),
        kTexDimAny(1),
        kTexDim2D(2),
        kTexDim3D(3),
        kTexDimCUBE(4),
        kTexDim2DArray(5),
        kTexDimCubeArray(6),
        kTexDimForce32Bit(2147483647),
        Null(Integer.MAX_VALUE),
        ;
        private final int index;

        TextureDimension(int i) {
            this.index = i;
        }

        public int index() {
            return index;
        }
    }

    public enum SerializedPropertyType {
        kColor,
        kVector,
        kFloat,
        kRange,
        kTexture,
        Null,
    }

    public enum FogMode {
        kFogUnknown,
        kFogDisabled,
        kFogLinear,
        kFogExp,
        kFogExp2,
        Null,
    }

    public enum ShaderGpuProgramType {
        kShaderGpuProgramUnknown,
        kShaderGpuProgramGLLegacy,
        kShaderGpuProgramGLES31AEP,
        kShaderGpuProgramGLES31,
        kShaderGpuProgramGLES3,
        kShaderGpuProgramGLES,
        kShaderGpuProgramGLCore32,
        kShaderGpuProgramGLCore41,
        kShaderGpuProgramGLCore43,
        kShaderGpuProgramDX9VertexSM20,
        kShaderGpuProgramDX9VertexSM30,
        kShaderGpuProgramDX9PixelSM20,
        kShaderGpuProgramDX9PixelSM30,
        kShaderGpuProgramDX10Level9Vertex,
        kShaderGpuProgramDX10Level9Pixel,
        kShaderGpuProgramDX11VertexSM40,
        kShaderGpuProgramDX11VertexSM50,
        kShaderGpuProgramDX11PixelSM40,
        kShaderGpuProgramDX11PixelSM50,
        kShaderGpuProgramDX11GeometrySM40,
        kShaderGpuProgramDX11GeometrySM50,
        kShaderGpuProgramDX11HullSM50,
        kShaderGpuProgramDX11DomainSM50,
        kShaderGpuProgramMetalVS,
        kShaderGpuProgramMetalFS,
        kShaderGpuProgramSPIRV,
        kShaderGpuProgramConsoleVS,
        kShaderGpuProgramConsoleFS,
        kShaderGpuProgramConsoleHS,
        kShaderGpuProgramConsoleDS,
        kShaderGpuProgramConsoleGS,
        kShaderGpuProgramRayTracing,
        Null,
    }

    public enum PassType {
        kPassTypeNormal,
        kPassTypeUse,
        kPassTypeGrab,
        Null,
    }

    public enum ShaderCompilerPlatform {
        kShaderCompPlatformNone,
        kShaderCompPlatformGL,
        kShaderCompPlatformD3D9,
        kShaderCompPlatformXbox360,
        kShaderCompPlatformPS3,
        kShaderCompPlatformD3D11,
        kShaderCompPlatformGLES20,
        kShaderCompPlatformNaCl,
        kShaderCompPlatformFlash,
        kShaderCompPlatformD3D11_9x,
        kShaderCompPlatformGLES3Plus,
        kShaderCompPlatformPSP2,
        kShaderCompPlatformPS4,
        kShaderCompPlatformXboxOne,
        kShaderCompPlatformPSM,
        kShaderCompPlatformMetal,
        kShaderCompPlatformOpenGLCore,
        kShaderCompPlatformN3DS,
        kShaderCompPlatformWiiU,
        kShaderCompPlatformVulkan,
        kShaderCompPlatformSwitch,
        kShaderCompPlatformXboxOneD3D12,
        kShaderCompPlatformGameCoreXboxOne,
        kShaderCompPlatformGameCoreScarlett,
        kShaderCompPlatformPS5,
        kShaderCompPlatformPS5NGGC,
        Null,
    }
}
