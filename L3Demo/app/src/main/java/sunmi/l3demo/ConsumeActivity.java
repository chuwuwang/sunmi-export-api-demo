package sunmi.l3demo;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;


/**
 * @author xurong on 2017/5/15.
 */

public class ConsumeActivity extends BaseActivity implements View.OnClickListener {

    private EditText mEditAmount;

    private RadioGroup mRadioGroup;

    private RadioButton mRBUserOptional;
    private RadioButton mRbBankCard;
    private RadioButton mRbAliPayScan;
    private RadioButton mRbAliPayCode;
    private RadioButton mRbWeChatScan;
    private RadioButton mRbWeChatCode;
    private RadioButton mRbUnionScan;
    private RadioButton mRbUnionCode;
    private RadioButton mRbScanAndScan;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_consume);
        initView();
    }

    private void initView() {
        mEditAmount = (EditText) findViewById(R.id.ed_input_money);

        mRadioGroup = (RadioGroup) findViewById(R.id.radio_group);

        mRBUserOptional = (RadioButton) findViewById(R.id.rb_user_optional);
        mRbBankCard = (RadioButton) findViewById(R.id.rb_bank_card);
        mRbAliPayScan = (RadioButton) findViewById(R.id.rb_aliPay_scan);
        mRbAliPayCode = (RadioButton) findViewById(R.id.rb_aliPay_code);
        mRbWeChatScan = (RadioButton) findViewById(R.id.rb_weChat_scan);
        mRbWeChatCode = (RadioButton) findViewById(R.id.rb_weChat_code);
        mRbUnionScan = (RadioButton) findViewById(R.id.rb_union_scan);
        mRbUnionCode = (RadioButton) findViewById(R.id.rb_union_code);
        mRbScanAndScan = (RadioButton) findViewById(R.id.rb_scan_and_scan);

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
        intent.putExtra("paymentType", paymentType);
        intent.putExtra("transId", "sunmi_123456789");
        try {
            String amount = mEditAmount.getText().toString();
            long aLong = Long.parseLong(amount);
            intent.putExtra("amount", aLong);
        } catch (Exception e) {
            Toast.makeText(this, "消费金额填写错误", Toast.LENGTH_SHORT).show();
            return;
        }

        // 添加用户自定义小票内容
        intent = addUserCustomTicketContent(intent);

        boolean existing = Util.isIntentExisting(intent, this);
        if (existing) {
            startActivity(intent);
        } else {
            Toast.makeText(this, "此机器上没有安装L3应用", Toast.LENGTH_SHORT).show();
        }
    }


}
