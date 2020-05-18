package com.ptg.ptgapi.model;

import androidx.annotation.NonNull;

public class AdProviderEntity {

    // key 标示，唯一
    @NonNull
    String providerType;

    //Provider 类的路径
    @NonNull
    String classPath;

    //Provider 的描述
    @NonNull
    String desc ;

    @NonNull
    public String getProviderType() {
        return providerType;
    }

    public void setProviderType(@NonNull String providerType) {
        this.providerType = providerType;
    }

    @NonNull
    public String getClassPath() {
        return classPath;
    }

    public void setClassPath(@NonNull String classPath) {
        this.classPath = classPath;
        this.desc=classPath;
    }

    @NonNull
    public String getDesc() {
        return desc;
    }

    public void setDesc(@NonNull String desc) {
        this.desc = desc;
    }

    @Override
    public String toString() {
        return "AdProviderEntity{" +
                "providerType='" + providerType + '\'' +
                ", classPath='" + classPath + '\'' +
                ", desc='" + desc + '\'' +
                '}';
    }
}
