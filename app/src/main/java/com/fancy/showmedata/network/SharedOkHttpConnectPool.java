package com.fancy.showmedata.network;

import java.util.concurrent.TimeUnit;

import okhttp3.ConnectionPool;

/**
 * Created by lugan on 17/08/2017.
 */

public class SharedOkHttpConnectPool {

    private static ConnectionPool sConnectionPool;

    public static ConnectionPool getInst() {
        if (sConnectionPool == null) {
            synchronized (SharedOkHttpConnectPool.class) {
                if (sConnectionPool == null) {
                    sConnectionPool = new ConnectionPool(20, 10, TimeUnit.SECONDS);
                }
            }
        }
        return sConnectionPool;
    }

}
