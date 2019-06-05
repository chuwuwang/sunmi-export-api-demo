package com.sm.l3.demo.util;

import java.math.BigInteger;
import java.util.Arrays;
import java.util.List;
import java.util.Locale;

public class ByteUtil {

    public static String bytes2HexStrToLower(byte[] bytes) {
        StringBuffer sb = new StringBuffer();
        for (int i = 0; i < bytes.length; i++) {
            int temp = bytes[i] & 0xFF;
            temp = temp + 0x100;
            String str = Integer.toString(temp, 16).substring(1);
            sb.append(str);
        }
        return sb.toString();
    }

    public static String bytes2HexStr(byte[] bytes) {
        StringBuilder sb = new StringBuilder();
        String temp;
        for (byte b : bytes) {
            // 将每个字节与0xFF进行与运算，然后转化为10进制，然后借助于Integer再转化为16进制
            temp = Integer.toHexString(0xFF & b);
            if (temp.length() == 1) {
                // 每个字节8为，转为16进制标志，2个16进制位
                temp = "0" + temp;
            }
            sb.append(temp);
        }
        return sb.toString().toUpperCase();
    }

    public static String bytes2HexStr_2(byte[] bytes) {
        BigInteger bigInteger = new BigInteger(1, bytes);
        return bigInteger.toString(16);
    }

    public static String byte2HexStr(byte b) {
        String temp = Integer.toHexString(0xFF & b);
        if (temp.length() == 1) {
            // 每个字节8为，转为16进制标志，2个16进制位
            temp = "0" + temp;
        }
        return temp;
    }

    public static byte[] hexStr2Bytes(String hexStr) {
        hexStr = hexStr.toLowerCase();
        int length = hexStr.length();
        byte[] bytes = new byte[length >> 1];
        int index = 0;
        for (int i = 0; i < length; i++) {
            if (index > hexStr.length() - 1) return bytes;
            byte highDit = (byte) (Character.digit(hexStr.charAt(index), 16) & 0xFF);
            byte lowDit = (byte) (Character.digit(hexStr.charAt(index + 1), 16) & 0xFF);
            bytes[i] = (byte) (highDit << 4 | lowDit);
            index += 2;
        }
        return bytes;
    }

    public static byte hexStr2Byte(String hexStr) {
        return (byte) Integer.parseInt(hexStr, 16);
    }

    public static String hexStr2Str(String hexStr) {
        String vi = "0123456789ABC DEF".replaceAll(" ", "");
        char[] array = hexStr.toCharArray();
        byte[] bytes = new byte[hexStr.length() / 2];
        int temp;
        for (int i = 0; i < bytes.length; i++) {
            char c = array[2 * i];
            temp = vi.indexOf(c) * 16;
            c = array[2 * i + 1];
            temp += vi.indexOf(c);
            bytes[i] = (byte) (temp & 0xFF);
        }
        return new String(bytes);
    }

    public static String hexStr2AsciiStr(String hexStr) {
        String vi = "0123456789ABC DEF".replaceAll(" ", "");
        hexStr = hexStr.trim().replace(" ", "").toUpperCase(Locale.US);
        char[] array = hexStr.toCharArray();
        byte[] bytes = new byte[hexStr.length() / 2];
        int temp;
        for (int i = 0; i < bytes.length; i++) {
            char c = array[2 * i];
            temp = vi.indexOf(c) << 4;
            c = array[2 * i + 1];
            temp |= vi.indexOf(c);
            bytes[i] = (byte) (temp & 0xFF);
        }
        return new String(bytes);
    }

    /**
     * 将字节数组转换成int, 小端模式(低位在前)
     */
    public static int unsignedInt2IntLE(byte[] src, int offset) {
        int value = 0;
        for (int i = offset; i < offset + 4; i++) {
            value |= (src[i] & 0XFF) << (i - offset) * 8;
        }
        return value;
    }

    public static byte[] long2Bytes(long value) {
        long b0 = value & 0xFF;
        long b1 = (value >> 8) & 0xFF;
        long b2 = (value >> 16) & 0xFF;
        long b3 = (value >> 24) & 0xFF;
        byte[] src = new byte[4];
        src[0] = (byte) b0;
        src[1] = (byte) b1;
        src[2] = (byte) b2;
        src[3] = (byte) b3;
        return src;
    }

    /**
     * 将short转换成byte数组, 大端模式(高位在前)
     */
    public static byte[] short2BytesBE(short src) {
        byte[] result = new byte[2];
        for (int i = 0; i < 2; i++) {
            result[i] = (byte) (src >> (1 - i) * 8);
        }
        return result;
    }

    /**
     * 将字节数组列表合并成单个字节数组
     */
    public static byte[] concatByteArrays(byte[]... list) {
        if (list == null || list.length == 0) {
            return new byte[0];
        }
        List<byte[]> bytes = Arrays.asList(list);
        return concatByteArrays(bytes);
    }

    /**
     * 将字节数组列表合并成单个字节数组
     */
    public static byte[] concatByteArrays(List<byte[]> list) {
        if (list == null || list.size() == 0) return new byte[0];
        int totalLen = 0;
        for (byte[] b : list) {
            if (b == null || b.length == 0) continue;
            totalLen += b.length;
        }
        byte[] result = new byte[totalLen];
        int index = 0;
        for (byte[] b : list) {
            if (b == null || b.length == 0) continue;
            System.arraycopy(b, 0, result, index, b.length);
            index += b.length;
        }
        return result;
    }

    /**
     * 将无符号short转换成int，大端模式(高位在前)
     */
    public static int unsignedShort2IntBE(byte[] src, int offset) {
        return (src[offset] & 0XFF) << 8 | (src[offset + 1] & 0XFF);
    }

    public static String stringToAscii(String value) {
        StringBuffer sb = new StringBuffer();
        char[] chars = value.toCharArray();
        for (int i = 0; i < chars.length; i++) {
            int var = chars[i];
            if (i != chars.length - 1) {
                sb.append(var).append(",");
            } else {
                sb.append(var);
            }
        }
        return sb.toString();
    }

    public static String asciiToString(String value) {
        StringBuffer sb = new StringBuffer();
        String[] split = value.split(",");
        for (int i = 0; i < split.length; i++) {
            String var = split[i];
            char parseInt = (char) Integer.parseInt(var);
            sb.append(parseInt);
        }
        return sb.toString();
    }

    public static String hexStrToAsciiStrGBK(String hexStr) {
        String result = null;
        try {
            result = new String(hexStr2Bytes(hexStr), "GBK");
        } catch (Exception e) {
            e.printStackTrace();
        }
        return result;
    }

    /**
     * 将BCD字节数据转换为ASCII字节数组
     */
    public static byte[] bcd2Asc(byte[] a) {
        if (a == null || a.length == 0) {
            return null;
        }
        byte[] result = new byte[a.length * 2];
        for (int i = 0; i < a.length; i++) {
            byte h = (byte) (a[i] >> 4 & 0x0F);
            byte l = (byte) (a[i] & 0x0F);
            result[i * 2] = (byte) (h > 9 ? h + 'A' - 10 : h + '0');
            result[i * 2 + 1] = (byte) (l > 9 ? l + 'A' - 10 : l + '0');
        }
        return result;
    }


}
