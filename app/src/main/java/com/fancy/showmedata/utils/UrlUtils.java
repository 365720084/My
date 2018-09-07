package com.fancy.showmedata.utils;

import android.text.TextUtils;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;

/**
 * @author : gavin
 * @date 2018/8/30.
 */

public class UrlUtils {

    /**
     * 向url链接追加参数(单个)
     * @param url
     * @param name String
     * @param value String
     * @return
     */
    public static String appendParam(String url, String name, String value){
        if(TextUtils.isEmpty(url)){
            return "";
        }else if(TextUtils.isEmpty(name)){
            return url.trim();
        }else{
            Map<String, String> params = new HashMap<String, String>();
            params.put(name, value);
            return appendParams(url, params);
        }
    }

    /**
     * 向url链接追加参数
     * @param url
     * @param params Map<String, String>
     * @return
     */
    public static String appendParams(String url, Map<String, String> params){
        if(TextUtils.isEmpty(url)){
            return "";
        }else if(null == params || params.size() == 0){
            return url.trim();
        }else{
            StringBuffer sb = new StringBuffer("");
            Set<String> keys = params.keySet();
            for (String key : keys) {
                sb.append(key).append("=").append(params.get(key)).append("&");
            }
            sb.deleteCharAt(sb.length() - 1);

            url = url.trim();
            int length = url.length();
            int index = url.indexOf("?");
            if(index > -1){
                //url说明有问号
                if((length - 1) == index){
                    //url最后一个符号为？，如：http://wwww.baidu.com?
                    url += sb.toString();
                }else{
                    //情况为：http://wwww.baidu.com?aa=11
                    url += "&" + sb.toString();
                }
            }else{
                //url后面没有问号，如：http://wwww.baidu.com
                url += "?" + sb.toString();
            }
            return url;
        }
    }

    /**
     * 移除url链接的多个参数
     * @param url String
     * @param paramNames String[]
     * @return
     */
    public static String removeParams(String url, String... paramNames){
        if(TextUtils.isEmpty(url)){
            return "";
        }else if(null == paramNames || paramNames.length == 0){
            return url.trim();
        }else{
            url = url.trim();
            int length = url.length();
            int index = url.indexOf("?");
            if(index > -1){
                //url说明有问号
                if((length - 1) == index){
                    //url最后一个符号为？，如：http://wwww.baidu.com?
                    return url;
                }else{
                    //情况为：http://wwww.baidu.com?aa=11或http://wwww.baidu.com?aa=或http://wwww.baidu.com?aa
                    String baseUrl = url.substring(0, index);
                    String paramsString = url.substring(index + 1);
                    String[] params = paramsString.split("&");
                    if(null != params && params.length!=0){
                        Map<String, String> paramsMap = new HashMap<String, String>();
                        for (String param : params) {
                            if(!TextUtils.isEmpty(param)){
                                String[] oneParam = param.split("=");
                                String paramName = oneParam[0];
                                int count = 0;
                                for(int i=0; i<paramNames.length; i++){
                                    if(paramNames[i].equals(paramName)){
                                        break;
                                    }
                                    count ++;
                                }
                                if(count == paramNames.length){
                                    paramsMap.put(paramName, (oneParam.length > 1)?oneParam[1]:"");
                                }
                            }
                        }
                        if(null != paramsMap && paramsMap.size() != 0){
                            StringBuffer paramBuffer = new StringBuffer(baseUrl);
                            paramBuffer.append("?");
                            Set<String> set = paramsMap.keySet();
                            for (String paramName : set) {
                                paramBuffer.append(paramName).append("=").append(paramsMap.get(paramName)).append("&");
                            }
                            paramBuffer.deleteCharAt(paramBuffer.length() - 1);
                            return paramBuffer.toString();
                        }
                        return baseUrl;
                    }
                }
            }
            return url;
        }
    }
}
