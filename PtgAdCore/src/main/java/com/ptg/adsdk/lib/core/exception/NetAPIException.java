package com.ptg.adsdk.lib.core.exception;

import java.net.HttpURLConnection;

public class NetAPIException extends Exception {

    private HttpURLConnection conn;

    public NetAPIException(String msg, HttpURLConnection conn) {
        super(msg);
        this.conn = conn;
    }

    public NetAPIException(String msg) {
        super(msg);
    }

    public String getMessage() {
        StringBuilder stringbuilder = new StringBuilder(String.valueOf(super.getMessage()));
        try {
            if (conn != null) {
                stringbuilder.append(' ');
                stringbuilder.append('[');
                stringbuilder.append(conn.toString());
                stringbuilder.append(']');
            }
        } catch (Exception e) {
        }
        return stringbuilder.toString();
    }

    public String toString() {
        return getMessage();
    }
}
