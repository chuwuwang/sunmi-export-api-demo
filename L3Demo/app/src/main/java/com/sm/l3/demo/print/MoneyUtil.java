package com.sm.l3.demo.print;

import java.math.BigDecimal;
import java.text.DecimalFormat;

public class MoneyUtil {

    /**
     * 将String类型的钱(单位：元)转换成Long类型的钱（单位：分）
     */
    public static long stringMoney2LongCent(String amount) {
        BigDecimal bd = new BigDecimal(amount);
        BigDecimal bigDecimal = new BigDecimal("100");
        return bd.multiply(bigDecimal).longValue();
    }

    /**
     * 将Long类型的钱（单位：分）转化成String类型的钱（单位：元）
     */
    public static String longCent2DoubleMoneyStr(long amount) {
        BigDecimal bd = new BigDecimal(amount);
        BigDecimal bigDecimal = new BigDecimal("100");
        double doubleValue = bd.divide(bigDecimal).doubleValue();
        DecimalFormat df = new DecimalFormat("#0.00");
        return df.format(doubleValue);
    }

}
