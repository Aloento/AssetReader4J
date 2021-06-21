package com.QYun.AssetReader4J;

import com.QYun.AssetReader4J.Helpers.ImportHelper;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashSet;

public class AssetsManager {
    private HashSet<File> assetsFileListHash = new HashSet<>();

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
        BinaryReader reader = new BinaryReader(file, false);
        switch (ImportHelper.checkFileType(reader)) {
            case AssetsFile -> LoadAssetsFile(file, reader);
            case BundleFile -> LoadBundleFile(file, reader);
            case WebFile -> LoadWebFile(file, reader);
        }
    }

    private void LoadAssetsFile(File file, BinaryReader reader) {
        if (!assetsFileListHash.contains(file)) {

        }
    }

    private void LoadBundleFile(File file, BinaryReader reader) {

    }

    private void LoadWebFile(File file, BinaryReader reader) {
    }

}
