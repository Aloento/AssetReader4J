package com.QYun.AssetReader4J;

import com.QYun.AssetReader4J.Entities.Enums.SerializedFileFormatVersion;
import com.QYun.AssetReader4J.Helpers.DirectoryHelper;
import com.QYun.AssetReader4J.Helpers.ImportHelper;
import com.QYun.AssetReader4J.Unity3D.Objects.*;
import com.QYun.AssetReader4J.Unity3D.Objects.AnimationClip.AnimationClip;
import com.QYun.AssetReader4J.Unity3D.UObject;
import com.QYun.AssetReader4J.Unity3D.UObjectReader;
import com.QYun.util.Stream.UnityStream;
import org.eclipse.collections.api.factory.Lists;
import org.eclipse.collections.api.factory.Maps;
import org.eclipse.collections.api.factory.Sets;
import org.eclipse.collections.api.list.MutableList;
import org.eclipse.collections.api.map.MutableMap;
import org.eclipse.collections.api.set.MutableSet;

import java.io.File;
import java.io.IOException;

public class AssetsManager {
    private final MutableSet<File> assetsFileListHash = Sets.mutable.empty();
    private final MutableSet<String> importFilesHash = Sets.mutable.empty();
    private final MutableList<File> importFiles = Lists.mutable.empty();
    public MutableList<SerializedFile> assetsFileList = Lists.mutable.empty();
    public MutableMap<String, Integer> assetsFileIndexCache = Maps.mutable.empty();
    public MutableMap<String, UnityStream> resourceFileReaders = Maps.mutable.empty();

    public void loadFiles(MutableList<File> files) throws IOException {
        ImportHelper.mergeSplitAssets(files.get(0));
        files.removeIf(File -> File.getName().contains(".split"));
        load(files);
    }

    private void load(MutableList<File> files) throws IOException {
        for (var file : files) {
            importFiles.add(file);
            importFilesHash.add(file.getName());
        }

        for (File importFile : importFiles) {
            loadFile(importFile);
        }

        importFiles.clear();
        importFilesHash.clear();
        assetsFileListHash.clear();

        readAssets();
        processAssets();
    }

    private void readAssets() {
        for (var assetsFile : assetsFileList) {
            for (var objectInfo : assetsFile.m_Objects) {
                var objectReader = new UObjectReader(assetsFile.reader, assetsFile, objectInfo);
                UObject obj;
                switch (objectReader.type) {
                    case Animation -> obj = new Animation(objectReader);
                    case AnimationClip -> obj = new AnimationClip(objectReader);
                    case Animator -> obj = new Animator(objectReader);
                    case AnimatorController -> obj = new AnimatorController(objectReader);
                    case AnimatorOverrideController -> obj = new AnimatorOverrideController(objectReader);
                    case AssetBundle -> obj = new AssetBundle(objectReader);
                    case AudioClip -> obj = new AudioClip(objectReader);
                    case Avatar -> obj = new Avatar(objectReader);
                    case Font -> obj = new Font(objectReader);
                    case GameObject -> obj = new GameObject(objectReader);
                    case Material -> obj = new Material(objectReader);
                    case Mesh -> obj = new Mesh(objectReader);
                    case MeshFilter -> obj = new MeshFilter(objectReader);
                    case MeshRenderer -> obj = new MeshRenderer(objectReader);
                    case MonoBehaviour -> obj = new MonoBehaviour(objectReader);
                    case MonoScript -> obj = new MonoScript(objectReader);
                    case MovieTexture -> obj = new MovieTexture(objectReader);
                    case PlayerSettings -> obj = new PlayerSettings(objectReader);
                    case RectTransform -> obj = new RectTransform(objectReader);
                    case ResourceManager -> obj = new ResourceManager(objectReader);
                    case Shader -> obj = new Shader(objectReader);
                    case SkinnedMeshRenderer -> obj = new SkinnedMeshRenderer(objectReader);
                    case Sprite -> obj = new Sprite(objectReader);
                    case SpriteAtlas -> obj = new SpriteAtlas(objectReader);
                    case TextAsset -> obj = new TextAsset(objectReader);
                    case Texture2D -> obj = new Texture2D(objectReader);
                    case Transform -> obj = new Transform(objectReader);
                    case VideoClip -> obj = new VideoClip(objectReader);
                    default -> obj = new UObject(objectReader);
                }
                assetsFile.addObject(obj);
            }
        }
    }

    private void processAssets() {
        for (var assetsFile : assetsFileList) {
            for (var obj : assetsFile.Objects) {
                if (obj instanceof GameObject m_GameObject) {
                    for (var pptr : m_GameObject.m_Components) {
                        var m_Component = pptr.tryGet();
                        if (m_Component != null) {
                            if (m_Component instanceof Transform m_Transform)
                                m_GameObject.m_Transform = m_Transform;
                            else if (m_Component instanceof MeshRenderer m_MeshRenderer)
                                m_GameObject.m_MeshRenderer = m_MeshRenderer;
                            else if (m_Component instanceof MeshFilter m_MeshFilter)
                                m_GameObject.m_MeshFilter = m_MeshFilter;
                            else if (m_Component instanceof SkinnedMeshRenderer m_SkinnedMeshRenderer)
                                m_GameObject.m_SkinnedMeshRenderer = m_SkinnedMeshRenderer;
                            else if (m_Component instanceof Animator m_Animator)
                                m_GameObject.m_Animator = m_Animator;
                            else if (m_Component instanceof Animation m_Animation)
                                m_GameObject.m_Animation = m_Animation;
                        }
                    }
                } else if (obj instanceof SpriteAtlas m_SpriteAtlas) {
                    for (var m_PackedSprite : m_SpriteAtlas.m_PackedSprites) {
                        var m_Sprite = m_PackedSprite.tryGet();
                        if (m_Sprite != null) {
                            if (m_Sprite.m_SpriteAtlas.isNull())
                                m_Sprite.m_SpriteAtlas.set(m_SpriteAtlas);
                        }
                    }
                }
            }
        }
    }

    private void loadFile(File file) throws IOException {
        UnityStream reader = new UnityStream(file);
        switch (ImportHelper.checkFileType(reader)) {
            case AssetsFile -> loadAssetsFile(file, reader);
            case BundleFile -> loadBundleFile(file, reader);
            case WebFile -> loadWebFile(file, reader);
        }
    }

    private void loadAssetsFile(File file, UnityStream reader) {
        if (!assetsFileListHash.contains(file)) {
            var assetsFile = new SerializedFile(this, file, reader);
            assetsFileList.add(assetsFile);
            assetsFileListHash.add(file);

            for (var sharedFile : assetsFile.m_Externals) {
                var sharedFilePath = new File(file.getPath() + File.separator + sharedFile.fileName);
                var sharedFileName = sharedFile.fileName;

                if (!importFilesHash.contains(sharedFileName)) {
                    if (!sharedFilePath.exists()) {
                        MutableList<File> findFiles = Lists.mutable.empty();
                        DirectoryHelper.findFiles(file.getParentFile(), sharedFileName, findFiles, true);
                        if (findFiles.size() > 0)
                            sharedFilePath = findFiles.get(0);
                    }

                    if (sharedFilePath.exists()) {
                        importFiles.add(sharedFilePath);
                        importFilesHash.add(sharedFileName);
                    }
                }
            }
        }
    }

    private void loadAssetsFromMemory(File file, UnityStream reader, File originalFile) {
        loadAssetsFromMemory(file, reader, originalFile, null);
    }

    private void loadAssetsFromMemory(File file, UnityStream reader, File originalFile, String unityVersion) {
        if (!assetsFileListHash.contains(file)) {
            var assetsFile = new SerializedFile(this, file, reader);
            assetsFile.originalPath = String.valueOf(originalFile);
            if (assetsFile.header.m_Version.ordinal() < SerializedFileFormatVersion.kUnknown_7.ordinal()) {
                assetsFile.setVersion(unityVersion);
            }
            assetsFileList.add(assetsFile);
            assetsFileListHash.add(file);
        }
    }

    private void loadBundleFile(File file, UnityStream reader) {
        loadBundleFile(file, reader, null);
    }

    private void loadBundleFile(File file, UnityStream reader, File parentFile) {
        try {
            BundleFile bundleFile = new BundleFile(reader, file);
            for (var streamFile : bundleFile.fileList) {
                UnityStream subReader = streamFile.stream;
                if (SerializedFile.isSerializedFile(subReader)) {
                    loadAssetsFromMemory(new File(file.getParent() + File.separator + streamFile.fileName),
                            subReader, parentFile == null ? file : parentFile, bundleFile.m_Header.unityRevision);
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void loadWebFile(File file, UnityStream reader) {
        throw new UnsupportedOperationException();
    }

}
