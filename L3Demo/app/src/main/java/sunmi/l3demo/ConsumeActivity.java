package sunmi.l3demo;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;

import static sunmi.l3demo.R.id.wechat_code_rb;

/**
 * Created by xurong on 2017/5/15.
 */

public class ConsumeActivity extends Activity implements View.OnClickListener {
    private EditText amountEdit;
    private RadioButton bankCardRb, alipayScanRb, wechatScanRb, userOptionalRb, alipayCodeRb, wechatCodeRb;
    private int paymentType;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
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
        alipayScanRb = (RadioButton) findViewById(R.id.alipay_scan_rb);
        wechatScanRb = (RadioButton) findViewById(R.id.wechat_scan_rb);
        userOptionalRb = (RadioButton) findViewById(R.id.optional_rb);
        alipayCodeRb = (RadioButton) findViewById(R.id.alipay_code_rb);
        wechatCodeRb = (RadioButton) findViewById(wechat_code_rb);
        okBtn = (Button) findViewById(R.id.ok_btn);
        okBtn.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.ok_btn:
                setPaymentType();
                Intent intent = new Intent("sunmi.payment.L3");
                intent.putExtra("transId", "L3 demo transId");
                intent.putExtra("transType", 0);
                intent.putExtra("paymentType", paymentType);
                if (TextUtils.isEmpty(amountEdit.getText().toString())) {
                    intent.putExtra("amount", Long.parseLong(amountEdit.getText().toString()));
                }
                intent.putExtra("appId", getPackageName());
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
        } else if (alipayScanRb.isChecked()) {
            paymentType = 1;
        } else if (wechatScanRb.isChecked()) {
            paymentType = 3;
        } else if (alipayCodeRb.isChecked()) {
            paymentType = 2;
        } else if (wechatCodeRb.isChecked()) {
            paymentType = 4;
        } else if (userOptionalRb.isChecked()) {
            paymentType = -1;
        }
    }
}
