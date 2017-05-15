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

/**
 * Created by xurong on 2017/5/15.
 */

public class ConsumeActivity extends Activity implements View.OnClickListener,RadioGroup.OnCheckedChangeListener{
    private EditText amountEdit;
    private RadioButton bankCardRb, alipayScanRb, wechatScanRb,userOptionalRb,alipayCodeRb,wechatCodeRb;
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
    private void initView(){
        final Button okBtn;
        RadioGroup modePaymentRg;
        amountEdit = (EditText) findViewById(R.id.input_money_edt);
        bankCardRb = (RadioButton) findViewById(R.id.bank_card_rb);
        alipayScanRb = (RadioButton) findViewById(R.id.alipay_scan_rb);
        wechatScanRb = (RadioButton) findViewById(R.id.wechat_scan_rb);
        userOptionalRb = (RadioButton) findViewById(R.id.optional_rb);
        okBtn = (Button) findViewById(R.id.ok_btn);
        okBtn.setOnClickListener(this);
        okBtn.setEnabled(false);
        modePaymentRg = (RadioGroup) findViewById(R.id.mode_payment_rg);
        modePaymentRg.setOnCheckedChangeListener(this);
        amountEdit.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void afterTextChanged(Editable editable) {
                if(!TextUtils.isEmpty(editable.toString())){
                    okBtn.setEnabled(true);
                }
            }
        });
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.ok_btn:
                Intent intent = new Intent("sunmi.payment.L3");
                intent.putExtra("transId", "L3 demo transId");
                intent.putExtra("transType", 0);
                intent.putExtra("paymentType", -1);
                intent.putExtra("amount", 1234L);
                intent.putExtra("appId", getPackageName());
        }
    }

    @Override
    public void onCheckedChanged(RadioGroup radioGroup, int i) {
        if(radioGroup.getId() == R.id.mode_payment_rg){
            switch (i){
                case 0:
                    break;
                case 1:
                    break;
                case 2:
                    break;
                case 3:
                    break;

            }
        }
    }
}
