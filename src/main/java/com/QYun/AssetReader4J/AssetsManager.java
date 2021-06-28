package com.QYun.AssetReader4J;

import com.QYun.AssetReader4J.Entities.Enums.SerializedFileFormatVersion;
import com.QYun.AssetReader4J.Helpers.DirectoryHelper;
import com.QYun.AssetReader4J.Helpers.ImportHelper;
import com.QYun.AssetReader4J.Unity3D.GameObject;
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
        int progressCount = assetsFileList.stream().mapToInt(SerializedFile -> SerializedFile.m_Objects.size()).sum();
        int i = 0;

        for (var assetsFile : assetsFileList) {
            for (var objectInfo : assetsFile.m_Objects) {

            }
        }
    }

    private void processAssets() {

        for (var assetsFile : assetsFileList) {
            for (var obj : assetsFile.Objects) {
                if (obj instanceof GameObject m_GameObject) {
                    for (var pptr : m_GameObject.m_Components) {
                        if (pptr.TryGet()) {
                            switch (m_Component) {
                                case
                            }
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
                        var findFiles = new MutableList<File>();
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
