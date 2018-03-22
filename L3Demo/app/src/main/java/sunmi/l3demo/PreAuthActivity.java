package sunmi.l3demo;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioGroup;

/**
 * 预授权界面
 *
 * @author Created by xurong on 2017/5/15.
 */

public class PreAuthActivity extends BaseActivity {

    private RadioGroup mRadioGroup;
    private EditText mEditVoucher;
    private EditText mEditMoney;
    private EditText mEditDate;
    private EditText mEditAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_auth);
        initView();
    }

    private void initView() {
        mRadioGroup = (RadioGroup) findViewById(R.id.radio_group);
        mEditMoney = (EditText) findViewById(R.id.edit_input_money);
        mEditVoucher = (EditText) findViewById(R.id.edit_input_voucher);
        mEditDate = (EditText) findViewById(R.id.edit_input_date);
        mEditAuth = (EditText) findViewById(R.id.edit_input_auth);

        Button ok = (Button) findViewById(R.id.btn_ok);
        ok.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        int transType = 3;
        int buttonId = mRadioGroup.getCheckedRadioButtonId();
        switch (buttonId) {
            case R.id.rb_pre_auth:
                transType = 3;
                break;
            case R.id.rb_pre_revoke:
                transType = 4;
                break;
            case R.id.rb_pre_complete:
                transType = 5;
                break;
            case R.id.rb_pre_complete_revoke:
                transType = 6;
                break;
        }

        Intent intent = new Intent("sunmi.payment.L3");
        intent.putExtra("transType", transType);
        intent.putExtra("appId", getPackageName());
        intent.putExtra("transId", System.currentTimeMillis() + "");
        try {
            long money = Long.valueOf(mEditMoney.getText().toString());
            intent.putExtra("amount", money);
        } catch (Exception e) {
            e.printStackTrace();
        }
        intent.putExtra("oriTransDate", mEditDate.getText().toString());
        intent.putExtra("oriVoucherNo", mEditVoucher.getText().toString());
        intent.putExtra("oriAuthNo", mEditAuth.getText().toString());
        intent.putExtra("oriTransDate", mEditDate.getText().toString());

        // 添加用户自定义小票内容
        intent = addUserCustomTicketContent(intent);

        startActivity(intent);
    }


}
