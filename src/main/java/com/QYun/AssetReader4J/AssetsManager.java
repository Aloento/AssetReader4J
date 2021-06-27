package com.QYun.AssetReader4J;

import com.QYun.AssetReader4J.Entities.Enums.SerializedFileFormatVersion;
import com.QYun.AssetReader4J.Helpers.DirectoryHelper;
import com.QYun.AssetReader4J.Helpers.ImportHelper;
import com.QYun.util.Stream.UnityStream;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashSet;

public class AssetsManager {
    private final HashSet<File> assetsFileListHash = new HashSet<>();
    private final HashSet<String> importFilesHash = new HashSet<>();
    private final ArrayList<File> importFiles = new ArrayList<>();
    public ArrayList<SerializedFile> assetsFileList = new ArrayList<>();

    public void loadFiles(ArrayList<File> files) throws IOException {
        ImportHelper.mergeSplitAssets(files.get(0));
        files.removeIf(File -> File.getName().contains(".split"));
        load(files);
    }

    private void load(ArrayList<File> files) throws IOException {
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

    }

    private void processAssets() {

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
                        var findFiles = new ArrayList<File>();
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
