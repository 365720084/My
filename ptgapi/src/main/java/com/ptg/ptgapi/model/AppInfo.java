package com.ptg.ptgapi.model;

public class AppInfo {
    private String icon_url;
    private String name;
    private String package_name;

    public String getIcon_url() {
        return icon_url;
    }

    public void setIcon_url(String icon_url) {
        this.icon_url = icon_url;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPackage_name() {
        return package_name;
    }

    public void setPackage_name(String package_name) {
        this.package_name = package_name;
    }

    @Override
    public String toString() {
        return "AppInfo{" +
                "icon_url='" + icon_url + '\'' +
                ", name='" + name + '\'' +
                ", package_name='" + package_name + '\'' +
                '}';
    }
}
