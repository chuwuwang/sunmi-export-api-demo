package com.sm.l3.demo.print;

import java.math.BigDecimal;
import java.text.DecimalFormat;

/**
 * 存放与金额转换相关的函数
 *
 * @author tomcat on 2017/4/7.
 */
public class MoneyUtils {

    /**
     * 将String类型的钱(单位：元)转换成Long类型的钱（单位：分）
     */
    public static long stringMoney2LongCent(String amount) {
        BigDecimal bd = new BigDecimal(amount);
        return bd.multiply(new BigDecimal("100")).longValue();
    }

    /**
     * 将Long类型的钱（单位：分）转化成String类型的钱（单位：元）
     */
    public static String longCent2DoubleMoneyStr(long amount) {
        BigDecimal bd = new BigDecimal(amount);
        double doubleValue = bd.divide(new BigDecimal("100")).doubleValue();
        DecimalFormat df = new DecimalFormat("#0.00");
        return df.format(doubleValue);
    }

    // sunhengzhi, 2017-09-14, 使用精准计算模式计算浮点数减法
    public static float subtract(String v1, String v2) {
        BigDecimal b1 = new BigDecimal(v1);
        BigDecimal b2 = new BigDecimal(v2);
        return b1.subtract(b2).floatValue();
    }
}
