package com.sm.l3.demo;

import android.content.Intent;
import android.content.pm.PackageManager;
import android.content.pm.ResolveInfo;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.Toast;

import java.util.List;

public class BaseActivity extends AppCompatActivity implements View.OnClickListener {

    public static final String CALL_EXTRA_ACTION = "com.pos.router.payment.ACTION_PAY";

    public Intent addUserCustomTicketContent(Intent intent) {
        EditText mEditUserInfo = findViewById(R.id.edit_user_info);
        EditText mEditUserCodeInfo = findViewById(R.id.edit_user_code_info);

        EditText mEditMerchantInfo = findViewById(R.id.edit_merchant_info);
        EditText mEditMerchantCodeInfo = findViewById(R.id.edit_merchant_code_info);

        CheckBox mCbPrint = findViewById(R.id.cb_print);

        String printInfo = mEditUserInfo.getText().toString();
        String printCode = mEditUserCodeInfo.getText().toString();
        String printMerchantInfo = mEditMerchantInfo.getText().toString();
        String printMerchantCode = mEditMerchantCodeInfo.getText().toString();
        if (printInfo.length() > 100) {
            Toast.makeText(this, "用户联追加打印请在100字以内", Toast.LENGTH_SHORT).show();
        }
        if (printMerchantInfo.length() > 100) {
            Toast.makeText(this, "商户联追加打印请在100字以内", Toast.LENGTH_SHORT).show();
        }

        boolean checked = mCbPrint.isChecked();
        intent.putExtra("isPrintTicket", checked);
        intent.putExtra("printInfo", printInfo);
        intent.putExtra("printInfo2", printCode);
        intent.putExtra("printMerchantInfo", printMerchantInfo);
        intent.putExtra("printMerchantInfo2", printMerchantCode);

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
