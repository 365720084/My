package com.fancy.showmedata.utils;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Judge {

    //China
//        Pattern p = Pattern.compile("^(1[0-9])\\d{9}$");
    //印尼
    static Pattern p = Pattern.compile("^0?[8][0-9]{8,11}$");

    public static boolean isMobileNO(String mobiles) {

        Matcher m = p.matcher(mobiles);
        return m.matches();
    }
}
