package sunmi.l3demo;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.Toast;

/**
 * @author by xurong on 2017/5/15.
 */

public class ReturnGoodsActivity extends Activity implements View.OnClickListener {

    private EditText oriReferenceNoEdt, oriDateEdt, moneyEdt;
    private RadioButton bankCardRb, aliPayScanRb, weChatScanRb, userOptionalRb, aliPayCodeRb, weChatCodeRb;
    private Button okBtn;

    private int paymentType;

    private EditText userInfoEdit;
    private EditText userCodeInfoEdit;
    private EditText merchantInfoEdit;
    private EditText merchantCodeInfoEdit;

    private CheckBox isPrintCb;
    private CheckBox isManagePwdCb;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_refund);
        initView();
    }

    /**
     * 初始化界面
     */
    private void initView() {
        moneyEdt = (EditText) findViewById(R.id.input_money_edt);
        oriDateEdt = (EditText) findViewById(R.id.ori_date_edt);
        oriReferenceNoEdt = (EditText) findViewById(R.id.ori_reference_no_edt);
        bankCardRb = (RadioButton) findViewById(R.id.bank_card_rb);
        aliPayScanRb = (RadioButton) findViewById(R.id.aliPay_scan_rb);
        weChatScanRb = (RadioButton) findViewById(R.id.weChat_scan_rb);
        userOptionalRb = (RadioButton) findViewById(R.id.optional_rb);
        aliPayCodeRb = (RadioButton) findViewById(R.id.aliPay_code_rb);
        weChatCodeRb = (RadioButton) findViewById(R.id.weChat_code_rb);
        okBtn = (Button) findViewById(R.id.ok_btn);
        okBtn.setOnClickListener(this);
        aliPayCodeRb.setVisibility(View.GONE);
        weChatCodeRb.setVisibility(View.GONE);

        userInfoEdit = (EditText) findViewById(R.id.et_user_info);
        userCodeInfoEdit = (EditText) findViewById(R.id.et_user_code_info);
        merchantInfoEdit = (EditText) findViewById(R.id.et_merchant_info);
        merchantCodeInfoEdit = (EditText) findViewById(R.id.et_merchant_code_info);

        isPrintCb = (CheckBox) findViewById(R.id.cb_code_print);
        isManagePwdCb = (CheckBox) findViewById(R.id.cb_manage_pwd);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.ok_btn:
                setPaymentType();
                Intent intent = new Intent("sunmi.payment.L3");
                intent.putExtra("transType", 2);
                intent.putExtra("transId", System.currentTimeMillis() + "");
                intent.putExtra("appId", getPackageName());
                intent.putExtra("paymentType", paymentType);
                try {
                    String amount = moneyEdt.getText().toString();
                    long aLong = Long.parseLong(amount);
                    intent.putExtra("amount", aLong);
                } catch (Exception e) {
                    e.printStackTrace();
                }

                String referenceNo = oriReferenceNoEdt.getText().toString();
                if (paymentType == 1 || paymentType == 2 || paymentType == 3 || paymentType == 4) {
                    intent.putExtra("oriQROrderNo", referenceNo);
                } else {
                    intent.putExtra("oriReferenceNo", referenceNo);
                }

                String date = oriDateEdt.getText().toString();
                intent.putExtra("oriTransDate", date);

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
                intent.putExtra("isManagePwd", isManagePwdCb.isChecked());

                if (Util.isIntentExisting(intent, this)) {
                    startActivity(intent);
                } else {
                    Toast.makeText(this, "此机器上没有安装L3应用", Toast.LENGTH_SHORT).show();
                }
        }
    }

    private void checkEdit() {
        if (!TextUtils.isEmpty(oriReferenceNoEdt.getText()) && !TextUtils.isEmpty(moneyEdt.getText())) {
            okBtn.setEnabled(true);
        }
    }

    private void setPaymentType() {
        if (bankCardRb.isChecked()) {
            paymentType = 0;
        } else if (aliPayScanRb.isChecked()) {
            paymentType = 1;
        } else if (aliPayCodeRb.isChecked()) {
            paymentType = 2;
        } else if (weChatScanRb.isChecked()) {
            paymentType = 3;
        } else if (weChatCodeRb.isChecked()) {
            paymentType = 4;
        } else if (userOptionalRb.isChecked()) {
            paymentType = -1;
        }
    }


}
