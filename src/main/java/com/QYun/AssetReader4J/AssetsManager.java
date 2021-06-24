package com.QYun.AssetReader4J;

import com.QYun.AssetReader4J.Helpers.ImportHelper;
import com.QYun.util.Stream.UnityStream;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashSet;

public class AssetsManager {
    private final HashSet<File> assetsFileListHash = new HashSet<>();

    public void loadFiles(ArrayList<File> files) throws IOException {
        ImportHelper.mergeSplitAssets(files.get(0));
        files.removeIf(File -> File.getName().contains(".split"));
        load(files);
    }

    private void load(ArrayList<File> files) throws IOException {
        for (var file : files) {
            loadFile(file);
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
    }

    private void loadAssetsFromMemory(File file, UnityStream reader, File originalFile) {
        loadAssetsFromMemory(file, reader, originalFile, null);
    }

    private void loadAssetsFromMemory(File file, UnityStream reader, File originalFile, String unityVersion) {

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
    }

}
