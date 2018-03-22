package sunmi.l3demo;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioGroup;


/**
 * @author xurong on 2017/5/15.
 */

public class ConsumeActivity extends BaseActivity {

    private RadioGroup mRadioGroup;
    private EditText mEditAmount;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_consume);
        initView();
    }

    private void initView() {
        mRadioGroup = (RadioGroup) findViewById(R.id.radio_group);
        mEditAmount = (EditText) findViewById(R.id.edit_input_money);
        Button btnOk = (Button) findViewById(R.id.btn_ok);
        btnOk.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        int paymentType;
        int buttonId = mRadioGroup.getCheckedRadioButtonId();
        switch (buttonId) {
            case R.id.rb_user_optional:
                paymentType = -1;
                break;
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
            default:
                paymentType = -1;
                break;
        }

        Intent intent = new Intent("sunmi.payment.L3");
        intent.putExtra("transType", 0);
        intent.putExtra("appId", getPackageName());
        intent.putExtra("transId", System.currentTimeMillis() + "");
        intent.putExtra("paymentType", paymentType);
        try {
            long aLong = Long.parseLong(mEditAmount.getText().toString());
            intent.putExtra("amount", aLong);
        } catch (Exception e) {
            e.printStackTrace();
        }

        // 添加用户自定义小票内容
        intent = addUserCustomTicketContent(intent);

        startActivity(intent);

    }


}
