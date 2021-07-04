package com.QYun.AssetReader4J;

import org.eclipse.collections.api.factory.Lists;
import org.eclipse.collections.api.list.MutableList;

import java.io.File;

public class App {
    private static final MutableList<File> files = Lists.mutable.empty();
    public static AssetsManager manager = new AssetsManager();

    public static void main(String[] args) {
        System.out.println("Hello QYun.AssetReader4J!");
        files.add(new File("src/main/resources/char_1012_skadi2.ab"));
        try {
            manager.loadFiles(files);

            for (var serializedFile : manager.assetsFileList) {
                var objects = serializedFile.ObjectsDic.elements();
                while (objects.hasMoreElements()) {
                    var obj = objects.nextElement();
                    System.out.format("%-20s%-10s%s\n", obj.type, obj.byteSize, obj.m_PathID);
                }
            }
        } catch (Throwable e) {
            e.printStackTrace();
        }
        System.out.println("Exited");
    }
}
