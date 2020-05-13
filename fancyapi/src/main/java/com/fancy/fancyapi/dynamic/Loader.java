package com.fancy.fancyapi.dynamic;

import android.content.Context;
import android.util.Log;

import com.fancy.adsdk.lib.model.Core;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;

import dalvik.system.DexClassLoader;

class Loader {

    static Core load(Context context) {
        Log.d("FancyAd", "Dynamic Loader");
        Core core = null;

        File remoteFile = context.getFileStreamPath("inner_text_core.remote");

        if (remoteFile != null && remoteFile.exists()) {
            Log.d("FancyAd", "loadFromRemote");
            core = loadFromFile(context, remoteFile);
        }
        if (core == null) {
            Log.d("FancyAd", "loadFromAssets");
            core = loadFromAssets(context);
        }
        return core;
    }

    private static Core loadFromAssets(Context context) {
        File file = context.getFileStreamPath("inner_text_core.local");
        if (file != null && file.exists()) {
            file.delete();
        }
        try {
            InputStream ins = context.getAssets().open("inner_text_core");
            FileOutputStream fos = new FileOutputStream(file);
            IOUtils.copy(ins, fos);
        } catch (IOException e) {
            e.printStackTrace();
        }
        if (file != null && file.exists()) {
            return loadFromFile(context, file);
        }
        return null;
    }

    private static Core loadFromFile(Context context, File file) {
        Core core = null;
        try {
            DexClassLoader loader = new DexClassLoader(file.getPath(),
                    context.getFilesDir().getAbsolutePath(), null,
                    Core.class.getClassLoader());
            Class _InnerTextSdk = loader.loadClass("InnerTextCore");
            core = (Core) _InnerTextSdk.newInstance();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return core;
    }
}
