package com.QYun.AssetReader4J.Helpers;

import org.eclipse.collections.api.list.MutableList;

import java.io.File;
import java.util.Comparator;
import java.util.Objects;

public class DirectoryHelper {
    public static void findFiles(File baseDir, String target, MutableList<File> list, boolean subDirectories) {
        if (!baseDir.exists() || !baseDir.isDirectory()) {
            System.err.println(baseDir.getAbsolutePath() + " Not exists or Not directory");
            return;
        }

        for (File file : Objects.requireNonNull(baseDir.listFiles())) {
            if (file.isDirectory() && subDirectories) {
                findFiles(file, target, list, true);
            } else {
                if (wildcardMatch(target, file.getName()))
                    list.add(file);
            }
        }

        list.sort(Comparator.comparing(File::getName));
    }

    public static boolean wildcardMatch(String pattern, String str) {
        int patternLength = pattern.length();
        int strLength = str.length();
        int strIndex = 0;
        char ch;
        for (int patternIndex = 0; patternIndex < patternLength; patternIndex++) {
            ch = pattern.charAt(patternIndex);
            if (ch == '*') {
                while (strIndex < strLength) {
                    if (wildcardMatch(pattern.substring(patternIndex + 1),
                            str.substring(strIndex))) {
                        return true;
                    }
                    strIndex++;
                }
            } else if (ch == '?') {
                strIndex++;
                if (strIndex > strLength)
                    return false;
            } else {
                if ((strIndex >= strLength) || (ch != str.charAt(strIndex)))
                    return false;
                strIndex++;
            }
        }
        return (strIndex == strLength);
    }
}
