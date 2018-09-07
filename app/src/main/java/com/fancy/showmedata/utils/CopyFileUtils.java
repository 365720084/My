package com.fancy.showmedata.utils;

import com.fancy.showmedata.utils.log.LogUtils;

import java.io.File;
import java.io.FileOutputStream;

/**
 * 类描述：
 * Created by 殴打小熊猫 on 2017/3/29.
 */

public class CopyFileUtils {

    public static void copyFile(File fromFile, File toFile, Boolean rewrite) {
        if (!fromFile.exists()) {
            return;
        }

        if (!fromFile.isFile()) {
            return;
        }
        if (!fromFile.canRead()) {
            return;
        }
        if (!toFile.getParentFile().exists()) {
            toFile.getParentFile().mkdirs();
        }

        if (toFile.exists() && rewrite) {
            toFile.delete();
        }

        try {

            java.io.FileInputStream fosfrom = new java.io.FileInputStream(fromFile);
            java.io.FileOutputStream fosto = new FileOutputStream(toFile);
            byte bt[] = new byte[1024];

            int c;
            while ((c = fosfrom.read(bt)) > 0) {
                fosto.write(bt, 0, c);
            }
            fosfrom.close();
            fosto.close();
        } catch (Exception ex) {

            LogUtils.e("readfile", ex.getMessage());
        }
    }
}
