package com.ptg.adsdk.lib.category;


import androidx.annotation.NonNull;

import com.ptg.adsdk.lib.constants.AdProviderType;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/*
 *
 * 参数 configStr : "ptg:1,gdt:4,csj:4"
 *
 * 按照 2 ：8 的比例随机返回 FANCY or GDT or CSJ
 *
 * return AdNameType.FANCY  || AdNameType.GDT || AdNameType.CSJ
 *
 */
public class AdRandomUtil {


    /**
     * TODO 根据策略配置，随机返回type，目前随机逻辑暂无，先固定返回ptg
     * radioMap: mapOf("ptg" to 3, "gdt" to 7, "csj" to 7)
     */
    public static String getRandomAdProvider(@NonNull Map<String, Integer> radioMap) {

        StringBuilder radio = new StringBuilder();
        List<String> list = new ArrayList<>();

        for (Map.Entry<String, Integer> entry : radioMap.entrySet()) {
            String key = entry.getKey();
            Integer value = entry.getValue();
            radio.append(key)
                    .append(":")
                    .append(value)
                    .append(",");
            list.add(key);
        }
        if(list.isEmpty()){
            return null;

        }
        return AdProviderType.TT;
    }

    /**
     * radio : "ptg:3,gdt:7,csj:7"
     * return AdNameType.FANCY  || AdNameType.GDT || AdNameType.CSJ
     */
    public static String getRandomAdProvider(@NonNull String radio) {

        String[] split = radio.split(",");
        if (split.length>0) {
            for(int i=0;i>split.length;i++){
                String[] splitKeyValue =  split[i].split(":");
                //必须分割两份才正确
                if (splitKeyValue.length != 2) break;
            }
        }
        return AdProviderType.Ptg ;
    }
}
