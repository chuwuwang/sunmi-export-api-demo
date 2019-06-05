package com.sm.l3.demo.print;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by Administrator on 2016/6/20.
 */
public class CurrencyInfo {
    private static final Map currencyinfo = new HashMap<String, String>();

    public static String getName(String code) {
        if(currencyinfo.isEmpty()) {
            initMap();
        }

        if(currencyinfo.containsKey(code)) {
            return (String) currencyinfo.get(code);
        } else {
            return code;
        }
    }

    private static void initMap() {
        currencyinfo.put("156", "RMB");
        currencyinfo.put("840", "USD");
        currencyinfo.put("826", "GBP");
        currencyinfo.put("392", "JPY");
        currencyinfo.put("344", "HKD");
        currencyinfo.put("250", "FRF");
        currencyinfo.put("278", "DEM");
        currencyinfo.put("764", "THB");
    }
}
