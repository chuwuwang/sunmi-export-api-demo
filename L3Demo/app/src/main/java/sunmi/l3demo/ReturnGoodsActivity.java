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
import android.widget.Toast;

import static sunmi.l3demo.R.id.wechat_code_rb;

/**
 * @author  by xurong on 2017/5/15.
 */

public class ReturnGoodsActivity extends Activity implements View.OnClickListener {
    private EditText oriReferenceNoEdt, oriDateEdt, moneyEdt;
    private RadioButton bankCardRb, alipayScanRb, wechatScanRb, userOptionalRb, alipayCodeRb, wechatCodeRb;
    private int paymentType;
    private Button okBtn;

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
        alipayScanRb = (RadioButton) findViewById(R.id.alipay_scan_rb);
        wechatScanRb = (RadioButton) findViewById(R.id.wechat_scan_rb);
        userOptionalRb = (RadioButton) findViewById(R.id.optional_rb);
        alipayCodeRb = (RadioButton) findViewById(R.id.alipay_code_rb);
        wechatCodeRb = (RadioButton) findViewById(wechat_code_rb);
        okBtn = (Button) findViewById(R.id.ok_btn);
        okBtn.setOnClickListener(this);
        okBtn.setEnabled(false);
        moneyEdt.addTextChangedListener(mTextWatcher);
        oriDateEdt.addTextChangedListener(mTextWatcher);
        oriReferenceNoEdt.addTextChangedListener(mTextWatcher);
    }

    private TextWatcher mTextWatcher = new TextWatcher() {
        @Override
        public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

        }

        @Override
        public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {

        }

        @Override
        public void afterTextChanged(Editable editable) {
            checkEdit();
        }
    };

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.ok_btn:
                setPaymentType();
                Intent intent = new Intent("sunmi.payment.L3");
                intent.putExtra("transId", "L3 demo transId");
                intent.putExtra("transType", 2);
                intent.putExtra("paymentType", paymentType);
                intent.putExtra("amount", Long.parseLong(moneyEdt.getText().toString()));
                intent.putExtra("appId", getPackageName());
                intent.putExtra("oriReferenceNo", oriReferenceNoEdt.getText());//oriReferenceNo不能为"",否则交易失败
                intent.putExtra("oriTransDate", oriDateEdt.getText());
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
        } else if (alipayScanRb.isChecked()) {
            paymentType = 1;
        } else if (alipayCodeRb.isChecked()) {
            paymentType = 2;
        } else if (wechatScanRb.isChecked()) {
            paymentType = 3;
        } else if (wechatCodeRb.isChecked()) {
            paymentType = 4;
        } else if (userOptionalRb.isChecked()) {
            paymentType = -1;
        }
    }
}
