package com.QYun.AssetReader4J;

import java.io.File;
import java.util.ArrayList;

public class App {
    private static final ArrayList<File> files = new ArrayList<>();
    public static AssetsManager manager = new AssetsManager();

    public static void main(String[] args) {
        System.out.println("Hello QYun.AssetReader4J!");
        files.add(new File("src/main/resources/char_1012_skadi2.ab"));
        Thread.startVirtualThread(() -> manager.loadFiles(files));
    }
}
