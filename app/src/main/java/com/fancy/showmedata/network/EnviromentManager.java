package com.fancy.showmedata.network;

import com.fancy.showmedata.BuildConfig;

/**
 * Created by bruce on 2018/4/10.
 */

public class EnviromentManager {

    public static final String TEXT_URL = BuildConfig.HOST;
    public static final String TEXT_URL_CODE = "http://test03-api.beritaqu.aimodou.net";
    public static final String PRUDUCTION_URL = "https://api.wsdml.com";
    public static final String DEVELOPER_URL = "http:///dev2.jw830.com";
    public static final String NEWS_URL = "http://news-api.zhuoxunnews.com";
    public static boolean UseEncode=true;
    private static final EnviromentManager ourInstance = new EnviromentManager();
    private String CurrentUrl;
    private Enviroment enviroment;

    public Enviroment getEnviroment() {
        return enviroment;
    }

    public void setEnviroment(Enviroment enviroment) {
        this.enviroment = enviroment;
    }

    public static EnviromentManager getInstance() {
        return ourInstance;
    }

    private EnviromentManager() {
        CurrentUrl = TEXT_URL;
        setEnviroment(Enviroment.TEXT);
    }

    public String getCurrentUrl() {
        return CurrentUrl;
    }

    public void setCurrentUrl(Enviroment type) {
        switch (type) {
            case DEVELOP:
                CurrentUrl = DEVELOPER_URL;
                setEnviroment(Enviroment.DEVELOP);
                break;
            case TEXT:
                CurrentUrl = TEXT_URL;
                setEnviroment(Enviroment.TEXT);
                break;
            case PRODUCT:
                CurrentUrl = PRUDUCTION_URL;
                setEnviroment(Enviroment.PRODUCT);
                break;
        }


    }


}
