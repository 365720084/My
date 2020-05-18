package com.ptg.adsdk.lib.interf;

import androidx.annotation.MainThread;

public interface Error {
    @MainThread
    void onError(int var1, String var2);
}
