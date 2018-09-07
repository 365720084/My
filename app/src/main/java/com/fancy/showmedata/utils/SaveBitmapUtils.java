package com.fancy.showmedata.utils;

import android.graphics.Bitmap;

import com.fancy.showmedata.application.Constants;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;

/**
 * Created by sun on 15/10/29.
 */
public class SaveBitmapUtils {

    public static void saveMyBitmap(String bitName, Bitmap mBitmap) {
        saveMyBitmap(Constants.PATH_IMAGE_TEMP, bitName, mBitmap, Bitmap.CompressFormat.JPEG);
    }

    public static void saveMyBitmap(String bitName, Bitmap mBitmap, Bitmap.CompressFormat type) {
        saveMyBitmap(Constants.PATH_IMAGE_TEMP, bitName, mBitmap, type);
    }

    public static String saveMyBitmap(String dir, String bitName, Bitmap mBitmap) {
        return saveMyBitmap(dir, bitName, mBitmap, Bitmap.CompressFormat.JPEG);
    }

    public static String saveMyBitmap(String dir, String bitName, Bitmap mBitmap, Bitmap.CompressFormat type) {
        if (type == null)
            type = Bitmap.CompressFormat.JPEG;
        String name = bitName;
        if (!name.contains(".")) {
            name += (type == Bitmap.CompressFormat.JPEG ? ".jpg" : ".png");
        }
        File fileDir = new File(dir);
        if (!fileDir.exists())
            fileDir.mkdirs();
        File f = new File(fileDir, name);
        FileOutputStream fOut = null;
        try {
            f.createNewFile();
            fOut = new FileOutputStream(f);
            mBitmap.compress(type, 70, fOut);
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (fOut != null) {
                try {
                    fOut.flush();
                    fOut.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
        return f.getAbsolutePath();
    }
}
