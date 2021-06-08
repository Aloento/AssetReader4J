package com.QYun.AssetReader4J;

import com.QYun.AssetReader4J.Helpers.ImportHelper;

import java.io.File;
import java.util.ArrayList;

public class AssetsManager {

    public void loadFiles(ArrayList<File> files) {
        ImportHelper.mergeSplitAssets(files.get(0));
        files.removeIf(File -> File.getName().contains(".split"));
        load(files);
    }

    private void load(ArrayList<File> files) {

    }

}
