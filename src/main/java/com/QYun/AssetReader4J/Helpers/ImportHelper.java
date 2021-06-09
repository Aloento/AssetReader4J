package com.QYun.AssetReader4J.Helpers;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;

public class ImportHelper {
    public static void mergeSplitAssets(File file) {
        mergeSplitAssets(file, false);
    }

    public static void mergeSplitAssets(File file, boolean subDirectories) {
        var splitFiles = new ArrayList<File>();
        DirectoryHelper.findFiles(file.getAbsoluteFile().getParentFile(), "*.split0", splitFiles, subDirectories);

        for (var splitFile : splitFiles) {
            var destFile = splitFile.getName().replaceFirst("[.][^.]+$", "");
            var destPath = splitFile.getParentFile().getPath();
            var destFull = destPath + destFile;

            var fullFile = new File(destFull);
            if (fullFile.exists())
                return;

            var splitParts = new ArrayList<File>();
            DirectoryHelper.findFiles(
                    splitFile.getParentFile(),
                    destFile + ".split*",
                    splitParts, false);

            try {
                if (!fullFile.createNewFile()) {
                    System.err.println("Cannot Create Temp file: " + fullFile.getAbsolutePath());
                    return;
                }
                var destStream = new FileOutputStream(fullFile, true);

                for (int i = 0; i < splitParts.size(); i++) {
                    var slitPart = destFull + ".split" + i;
                    var sourceStream = new FileInputStream(slitPart);
                    destStream.write(sourceStream.read());
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    public enum FileType {
        AssetsFile,
        BundleFile,
        WebFile,
        ResourceFile
    }

    public static FileType CheckFileType(EndianBinaryReader reader) {

    }
}
