package com.chenlei.autotest.utils;

import java.io.File;
import java.util.ArrayList;
import java.util.List;


/**
 * Created by chenlei on 2017/7/12.
 */
public class CaseCount {
    private static List list;
    public static void main(String[] args) throws Exception {
        list = new ArrayList<String>();
        String property = System.getProperty("user.dir");
        File file = new File(property + "/proxymock/");
        showAllFiles(file);
        System.out.println(list.size());
    }

    final static void showAllFiles(File dir) throws Exception {
        File[] fs = dir.listFiles();
        for (int i = 0; i < fs.length; i++) {
            if (fs[i].isDirectory()) {
                try {
                    showAllFiles(fs[i]);
                } catch (Exception e) {
                }
            } else if (fs[i].getName().contains(".json")) {
                System.out.println(fs[i].getAbsolutePath());
                list.add(fs[i].getAbsolutePath());
            }
        }
    }
}
