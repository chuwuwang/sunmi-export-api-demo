package sunmi.l3demo;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.RadioGroup;

/**
 * @author by xurong on 2017/5/15.
 */

public class ReturnGoodsActivity extends BaseActivity {

    private RadioGroup mRadioGroup;
    private EditText mEditReference;
    private EditText mEditDate;
    private EditText mEditAmount;
    private CheckBox mCheckBoxManagePwd;

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
        mRadioGroup = (RadioGroup) findViewById(R.id.radio_group);
        mEditReference = (EditText) findViewById(R.id.edit_input_reference);
        mEditDate = (EditText) findViewById(R.id.edit_input_date);
        mEditAmount = (EditText) findViewById(R.id.edit_input_money);
        mCheckBoxManagePwd = (CheckBox) findViewById(R.id.cb_manage_pwd);
        Button okBtn = (Button) findViewById(R.id.btn_ok);
        okBtn.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        int paymentType = 0;
        int buttonId = mRadioGroup.getCheckedRadioButtonId();
        switch (buttonId) {
            case R.id.rb_bank_card:
                paymentType = 0;
                break;
            case R.id.rb_aliPay_scan:
                paymentType = 1;
                break;
            case R.id.rb_aliPay_code:
                paymentType = 2;
                break;
            case R.id.rb_weChat_scan:
                paymentType = 3;
                break;
            case R.id.rb_weChat_code:
                paymentType = 4;
                break;
            case R.id.rb_union_scan:
                paymentType = 5;
                break;
            case R.id.rb_union_code:
                paymentType = 6;
                break;
            case R.id.rb_scan_and_scan:
                paymentType = 7;
                break;
        }

        Intent intent = new Intent("sunmi.payment.L3");
        intent.putExtra("transType", 2);
        intent.putExtra("appId", getPackageName());
        intent.putExtra("transId", System.currentTimeMillis() + "");
        intent.putExtra("paymentType", paymentType);

        String referenceNo = mEditReference.getText().toString();
        if (paymentType != 0) {
            intent.putExtra("oriQROrderNo", referenceNo);
        } else {
            intent.putExtra("oriReferenceNo", referenceNo);
        }
        try {
            long aLong = Long.parseLong(mEditAmount.getText().toString());
            intent.putExtra("amount", aLong);
        } catch (Exception e) {
            e.printStackTrace();
        }
        intent.putExtra("oriTransDate", mEditDate.getText().toString());
        intent.putExtra("isManagePwd", mCheckBoxManagePwd.isChecked());

        // 添加用户自定义小票内容
        intent = addUserCustomTicketContent(intent);

        startActivity(intent);
    }

}
