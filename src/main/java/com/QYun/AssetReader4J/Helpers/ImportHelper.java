package com.QYun.AssetReader4J.Helpers;

import com.QYun.AssetReader4J.Entities.Enums.FileType;
import com.QYun.AssetReader4J.SerializedFile;
import com.QYun.AssetReader4J.WebFile;
import com.QYun.util.Stream.UnityStream;
import org.eclipse.collections.api.factory.Lists;
import org.eclipse.collections.api.list.MutableList;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Arrays;

public class ImportHelper {
    public static void mergeSplitAssets(File file) {
        mergeSplitAssets(file, false);
    }

    public static void mergeSplitAssets(File file, boolean subDirectories) {
        MutableList<File> splitFiles = Lists.mutable.empty();
        DirectoryHelper.findFiles(file.getAbsoluteFile().getParentFile(), "*.split0", splitFiles, subDirectories);

        for (var splitFile : splitFiles) {
            var destFile = splitFile.getName().replaceFirst("[.][^.]+$", "");
            var destPath = splitFile.getParentFile().getPath();
            var destFull = destPath + destFile;

            var fullFile = new File(destFull);
            if (fullFile.exists())
                return;

            MutableList<File> splitParts = Lists.mutable.empty();
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

    public static FileType checkFileType(UnityStream reader) {
        var signature = reader.ReadStringToNull(20);
        reader.rewind();
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
                reader.rewind();
                if (Arrays.equals(WebFile.gzipMagic, magic))
                    return FileType.WebFile;

                reader.setPos(0x20);
                magic = reader.readBytes(6);
                reader.rewind();
                if (Arrays.equals(WebFile.brotliMagic, magic))
                    return FileType.WebFile;

                if (SerializedFile.isSerializedFile(reader))
                    return FileType.AssetsFile;

                return FileType.ResourceFile;
            }
        }
    }
}
