package com.QYun.AssetReader4J;

import com.QYun.AssetReader4J.Helpers.DirectoryHelper;
import com.QYun.util.Stream.UnityStream;
import org.eclipse.collections.api.factory.Lists;
import org.eclipse.collections.api.list.MutableList;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;

public class ResourceReader {
    private Boolean needSearch;
    private String path;
    private SerializedFile assetsFile;
    private final long offset;
    private final int size;
    private UnityStream reader;

    public ResourceReader(String path, SerializedFile assetsFile, long offset, int size) {
        needSearch = true;
        this.path = path;
        this.assetsFile = assetsFile;
        this.offset = offset;
        this.size = size;
    }

    public ResourceReader(UnityStream reader, long offset, int size) {
        this.reader = reader;
        this.offset = offset;
        this.size = size;
    }

    public byte[] getData() throws IOException {
        if (needSearch) {
            var resourceFileName = new File(path).getName();
            var reader = assetsFile.assetsManager.resourceFileReaders.get(resourceFileName);

            if (reader != null) {
                needSearch = false;
                reader.setPos(Math.toIntExact(offset));
                return reader.readBytes(size);
            }

            var assetsFileDirectory = assetsFile.file.getParentFile();
            var resourceFilePath = new File(assetsFileDirectory.getPath() + File.separator + resourceFileName);

            if (!resourceFilePath.exists()) {
                MutableList<File> findFiles = Lists.mutable.empty();
                DirectoryHelper.findFiles(assetsFileDirectory, resourceFileName, findFiles, true);
                if (!findFiles.isEmpty())
                    resourceFilePath = findFiles.get(0);
            }

            if (resourceFilePath.exists()) {
                reader = new UnityStream(resourceFilePath);
                needSearch = false;
                assetsFile.assetsManager.resourceFileReaders.put(resourceFileName, reader);
                reader.setPos(Math.toIntExact(offset));
                return reader.readBytes(size);
            }

            throw new FileNotFoundException("Can't find the resource file " + resourceFileName);
        }

        reader.setPos(Math.toIntExact(offset));
        return reader.readBytes(size);
    }

}
