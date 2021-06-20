package com.QYun.AssetReader4J.Helpers;

import com.QYun.AssetReader4J.BinaryReader;
import com.QYun.AssetReader4J.Entities.Enums.FileType;

import java.io.*;
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

    public static FileType checkFileType(File file, BinaryReader reader) {
        try {
            reader = new BinaryReader(file, false);
            return checkFileType(reader);
        } catch (IOException e) {
            e.printStackTrace();
            return FileType.Null;
        }
    }

    private static FileType checkFileType(BinaryReader reader) throws IOException {
        var signature = reader.readStringToNull(20);
        reader.setPos(0);
        switch (signature) {
            case "UnityWeb":
            case "UnityRaw":
            case "UnityArchive":
            case "UnityFS":
                return FileType.BundleFile;
            case "UnityWebData1.0":
                return FileType.WebFile;
            default: {
                var magic = reader.readBytes(2);
                reader.setPos(0);

            }
        }
    }
}
