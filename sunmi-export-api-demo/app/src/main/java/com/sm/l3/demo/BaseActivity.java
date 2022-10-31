package com.sm.l3.demo;

import android.content.Intent;
import android.content.pm.PackageManager;
import android.content.pm.ResolveInfo;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.RadioGroup;
import android.widget.Toast;

import java.util.List;

public class BaseActivity extends AppCompatActivity implements View.OnClickListener {

    // public static final String CALL_EXTRA_ACTION = "sunmi.payment.L3";
    public static final String CALL_EXTRA_ACTION_CARD = "com.pos.router.payment.ACTION_PAY";
    public static final String CALL_EXTRA_ACTION = "com.pos.router.payment.ACTION_RESULT_FINE_XUS";

    public Intent addUserCustomTicketContent(Intent intent) {
        CheckBox cbPrint = findViewById(R.id.cb_print);
        EditText editUserInfo = findViewById(R.id.edit_user_info);
        EditText editMerchantInfo = findViewById(R.id.edit_merchant_info);
        EditText editUserCodeInfo = findViewById(R.id.edit_user_code_info);
        EditText editMerchantCodeInfo = findViewById(R.id.edit_merchant_code_info);

        boolean checked = cbPrint.isChecked();
        String printInfo = editUserInfo.getText().toString();
        String printCode = editUserCodeInfo.getText().toString();
        String printMerchantInfo = editMerchantInfo.getText().toString();
        String printMerchantCode = editMerchantCodeInfo.getText().toString();
        if (printInfo.length() > 100) {
            Toast.makeText(this, "用户联追加打印请在100字以内", Toast.LENGTH_SHORT).show();
        }
        if (printMerchantInfo.length() > 100) {
            Toast.makeText(this, "商户联追加打印请在100字以内", Toast.LENGTH_SHORT).show();
        }

        intent.putExtra("printInfo", printInfo);
        intent.putExtra("printInfo2", printCode);
        intent.putExtra("isPrintTicket", checked);
        intent.putExtra("printMerchantInfo", printMerchantInfo);
        intent.putExtra("printMerchantInfo2", printMerchantCode);
        return intent;
    }

    public Intent addPaymentChannel(Intent intent) {
        RadioGroup radioGroup = findViewById(R.id.rg_payment);
        int buttonId = radioGroup.getCheckedRadioButtonId();
        switch (buttonId) {
            case R.id.rb_card_payment:
                intent.setAction(CALL_EXTRA_ACTION_CARD);
                break;
            case R.id.rb_wallet_payment:
                intent.setAction(CALL_EXTRA_ACTION);
                break;
        }
        return intent;
    }

    @Override
    public void onClick(View v) {

    }

    @Override
    public void startActivity(Intent intent) {
        final PackageManager packageManager = getPackageManager();
        List<ResolveInfo> resolveInfo = packageManager.queryIntentActivities(intent, PackageManager.MATCH_DEFAULT_ONLY);
        if (resolveInfo.size() > 0) {
            super.startActivity(intent);
        } else {
            Toast.makeText(this, "此机器上没有安装L3应用", Toast.LENGTH_SHORT).show();
        }
    }


}
