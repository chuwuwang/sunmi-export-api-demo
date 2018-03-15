package sunmi.l3demo;

/**
 * L3常量类
 *
 * @author Created by Lee64 on 2018/3/2.
 */

public class L3Constant {

    /**
     * 支付方式
     */
    public static class PayType {
        public static final int BARCODE_TYPE_SELECT = -1;       // 由收银员选择支付方式
        public static final int BARCODE_TYPE_BANK = 0;          // 银行卡
        public static final int BARCODE_TYPE_AliPay_SCAN = 1;   // 支付宝扫码（B扫C）
        public static final int BARCODE_TYPE_AliPay_QR = 2;     // 支付宝二维码（C扫B）
        public static final int BARCODE_TYPE_WeChat_SCAN = 3;   // 微信支付扫码（B扫C）
        public static final int BARCODE_TYPE_WeChat_QR = 4;     // 微信支付二维码（C扫B）
        public static final int BARCODE_TYPE_UNION_SCAN = 5;    // 银联扫码(B扫C)
        public static final int BARCODE_TYPE_UNION_QR = 6;      // 银联二维码（C扫B）
        public static final int BARCODE_TYPE_ALL_SCAN_QR = 7;   // 扫码支付（不区分支付宝、微信、银联二维码
    }


}
