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
 * @author xurong on 2017/5/15.
 */

public class ConsumeActivity extends Activity implements View.OnClickListener {

    private EditText amountEdit;

    private RadioButton bankCardRb, aliPayScanRb, weChatScanRb, userOptionalRb, aliPayCodeRb, weChatCodeRb;

    private int paymentType;

    private EditText userInfoEdit;
    private EditText userCodeInfoEdit;
    private EditText merchantInfoEdit;
    private EditText merchantCodeInfoEdit;

    private CheckBox isPrintCb;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Constants.signBtnEnable = true;
        setContentView(R.layout.activity_consume);
        initView();
    }

    /**
     * 初始化界面
     */
    private void initView() {
        final Button okBtn;
        amountEdit = (EditText) findViewById(R.id.input_money_edt);
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
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.ok_btn:
                setPaymentType();
                Intent intent = new Intent("sunmi.payment.L3");
                intent.putExtra("transType", 0);
                intent.putExtra("transId", "fuck you");
                intent.putExtra("appId", getPackageName());
                intent.putExtra("paymentType", paymentType);
                try {
                    String amount = amountEdit.getText().toString();
                    long aLong = Long.parseLong(amount);
                    intent.putExtra("amount", aLong);
                } catch (Exception e) {
                    Toast.makeText(this, "消费金额填写错误", Toast.LENGTH_SHORT).show();
                    return;
                }

                String printInfo = userInfoEdit.getText().toString();
                String printInfo2 = userCodeInfoEdit.getText().toString();
                String printMerchantInfo = merchantInfoEdit.getText().toString();
                String printMerchantInfo2 = merchantCodeInfoEdit.getText().toString();
                intent.putExtra("printInfo", printInfo);
                intent.putExtra("printInfo2", printInfo2);
                intent.putExtra("printMerchantInfo", printMerchantInfo);
                intent.putExtra("printMerchantInfo2", printMerchantInfo2);

                intent.putExtra("isPrintTicket", isPrintCb.isChecked());

                if (Util.isIntentExisting(intent, this)) {
                    startActivity(intent);
                } else {
                    Toast.makeText(this, "此机器上没有安装L3应用", Toast.LENGTH_SHORT).show();
                }
        }

    }

    private void setPaymentType() {
        if (bankCardRb.isChecked()) {
            paymentType = 0;
        } else if (aliPayScanRb.isChecked()) {
            paymentType = 1;
        } else if (weChatScanRb.isChecked()) {
            paymentType = 3;
        } else if (aliPayCodeRb.isChecked()) {
            paymentType = 2;
        } else if (weChatCodeRb.isChecked()) {
            paymentType = 4;
        } else if (userOptionalRb.isChecked()) {
            paymentType = -1;
        }
    }

}
