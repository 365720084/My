/**
 * @Title: AppUtils.java
 * @Package com.adeaz.android.lib.security
 * Copyright: Copyright (c) 2015
 * Company:上海渭菲网络科技有限公司
 * @author 创建人-jerry
 * @date 2016-7-15 上午10:28:38
 * @version V1.0
 */
package com.fancy.showmedata.utils;

/**
 * @ClassName: AppUtils
 * @author 创建人-jerry
 * @date 2016-7-15 上午10:28:38
 *
 */
public class AppNativeUtils {

    private static AppNativeUtils instance;

    static {
        System.loadLibrary("apputils");
    }

    private AppNativeUtils(){}

    public static AppNativeUtils getInstance() {
        if (instance == null) {
            synchronized (AppNativeUtils.class){
                instance = new AppNativeUtils();
            }
        }
        return instance;
    }

    //是否真机或者模拟器
    public native boolean isPrototypeDevice();

    //获取设备分区信息
    //真机下都有mmcblk0分区，但是模拟器没有分区信息。
    public native String getDiskStatus();

    //获取网卡相关信息
    //string[0]设备名称，string[1]:mac地址
    //真机可以获取wlan0的ip和mac地址，模拟器只能获取eth0的ip和mac地址;
    public native String[] getLocalMacAddress();

    //获取系统属性信息
    //string[0]:序列号1；string[1]:序列号2；string[2]:硬件信息；
    //模拟器没有ro.boot.serialno和ro.serialno属性，真机中为机器序列号
    //模拟器 ro.hardware属性为goldfish，真机为各自的型号
    public native String[] getSystemProperties();

    //获取cpu相关的信息
    //模拟器中cpuinfo的硬件为Goldfish。
    public native String getCPUInfo();

    //获取设备驱动相关的信息
    //模拟器中包含goldfish的驱动
    public native String getDriverStatusInfo();

    //获取其他的设备信息,返回类型1，0或者0，0之类的数据
    //模拟器专有的文件，真机中没有。[0,0]为正常
    public native String getOtherDeviceInfo();

}
