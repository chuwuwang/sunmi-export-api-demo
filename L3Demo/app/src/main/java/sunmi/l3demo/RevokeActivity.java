package sunmi.l3demo;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.Toast;

/**
 * @author by xurong on 2017/5/15.
 */

public class RevokeActivity extends Activity implements View.OnClickListener {

    private RadioButton bank_card_rb;
    private RadioButton aliPay_scan_rb;
    private RadioButton wx_scan_rb;
    private EditText input_ori_voucher_no;

    private EditText userInfoEdit;
    private EditText userCodeInfoEdit;
    private EditText merchantInfoEdit;
    private EditText merchantCodeInfoEdit;

    private CheckBox isPrintCb;
    private CheckBox isManagePwdCb;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_revoke);
        Constants.signBtnEnable = true;
        initView();
    }

    private void initView() {
        bank_card_rb = (RadioButton) findViewById(R.id.bank_card_rb);
        aliPay_scan_rb = (RadioButton) findViewById(R.id.aliPay_scan_rb);
        wx_scan_rb = (RadioButton) findViewById(R.id.wx_scan_rb);
        input_ori_voucher_no = (EditText) findViewById(R.id.input_ori_voucher_no);
        Button ok_btn;
        ok_btn = (Button) findViewById(R.id.ok_btn);
        ok_btn.setOnClickListener(this);

        userInfoEdit = (EditText) findViewById(R.id.et_user_info);
        userCodeInfoEdit = (EditText) findViewById(R.id.et_user_code_info);
        merchantInfoEdit = (EditText) findViewById(R.id.et_merchant_info);
        merchantCodeInfoEdit = (EditText) findViewById(R.id.et_merchant_code_info);

        isPrintCb = (CheckBox) findViewById(R.id.cb_code_print);
        isManagePwdCb = (CheckBox) findViewById(R.id.cb_manage_pwd);
    }

    private int getType() {
        int paymentType = 0;
        if (bank_card_rb.isChecked()) {
            paymentType = 0;
        } else if (aliPay_scan_rb.isChecked()) {
            paymentType = 1;
        } else if (wx_scan_rb.isChecked()) {
            paymentType = 2;
        }
        return paymentType;
    }

    @Override
    public void onClick(View v) {
        // 支付类型
        int paymentType = getType();
        // 原交易凭证号
        // 如果有值则会直接跳转到刷卡界面，为""则跳转到选择交易列表界面
        String voucherNo = input_ori_voucher_no.getText().toString();

        Intent intent = new Intent("sunmi.payment.L3");
        intent.putExtra("transId", System.currentTimeMillis() + "");
        intent.putExtra("transType", 1);
        intent.putExtra("paymentType", paymentType);
        intent.putExtra("oriVoucherNo", voucherNo);
        intent.putExtra("appId", getPackageName());

        String printInfo = userInfoEdit.getText().toString();
        String printInfo2 = userCodeInfoEdit.getText().toString();
        String printMerchantInfo = merchantInfoEdit.getText().toString();
        String printMerchantInfo2 = merchantCodeInfoEdit.getText().toString();
        if (printInfo.length() > 100) {
            Toast.makeText(this, "用户联追加打印请在100字以内", Toast.LENGTH_SHORT).show();
        }
        if (printMerchantInfo.length() > 100) {
            Toast.makeText(this, "商户联追加打印请在100字以内", Toast.LENGTH_SHORT).show();
        }
        intent.putExtra("printInfo", printInfo);
        intent.putExtra("printInfo2", printInfo2);
        intent.putExtra("printMerchantInfo", printMerchantInfo);
        intent.putExtra("printMerchantInfo2", printMerchantInfo2);

        intent.putExtra("isPrintTicket", isPrintCb.isChecked());
        if (isManagePwdCb.isChecked()) {
            intent.putExtra("isManagePwd", 1);
        } else {
            intent.putExtra("isManagePwd", 0);
        }

        if (Util.isIntentExisting(intent, this)) {
            startActivity(intent);
        } else {
            Toast.makeText(this, "此机器上没有安装L3应用", Toast.LENGTH_SHORT).show();
        }
    }


}
