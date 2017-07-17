package sunmi.l3demo;

import android.app.Activity;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.content.pm.ResolveInfo;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.TextView;
import android.widget.Toast;

import java.util.List;

/**
 * @author xurong
 *         Created by xurong on 2017/5/15.
 */

public class PreAuthActivity extends Activity implements View.OnClickListener, CompoundButton.OnCheckedChangeListener {

    private RadioButton preAuth_rb;
    private RadioButton preAuth_revoke_rb;
    private RadioButton preAuth_complete_rb;
    private RadioButton preAuth_complete_revoke_rb;
    private TextView input_money_tv;
    private TextView input_ori_voucher_no_tv;
    private EditText input_ori_voucher_no_edt;
    private EditText input_money_edt;
    private TextView input_ori_date_tv;
    private EditText input_date_edt;
    private TextView input_auth_tv;
    private EditText input_auth_edt;
    private Button ok_btn;

    private EditText userInfoEdit;
    private EditText userCodeInfoEdit;
    private EditText merchantInfoEdit;
    private EditText merchantCodeInfoEdit;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Constants.signBtnEnable = true;
        setContentView(R.layout.activity_preauth);
        initView();
    }

    private void initView() {
        preAuth_rb = (RadioButton) findViewById(R.id.pre_rb);
        preAuth_revoke_rb = (RadioButton) findViewById(R.id.pre_revoke_rb);
        preAuth_complete_rb = (RadioButton) findViewById(R.id.pre_complete_rb);
        preAuth_complete_revoke_rb = (RadioButton) findViewById(R.id.pre_complete_revoke_rb);
        input_money_tv = (TextView) findViewById(R.id.input_money_tv);
        input_ori_voucher_no_tv = (TextView) findViewById(R.id.input_ori_voucher_no_tv);
        input_money_edt = (EditText) findViewById(R.id.input_money_edt);
        input_ori_voucher_no_edt = (EditText) findViewById(R.id.input_ori_voucher_no_edt);
        input_date_edt = (EditText) findViewById(R.id.input_date_edt);
        input_ori_date_tv = (TextView) findViewById(R.id.input_date_tv);
        input_auth_edt = (EditText) findViewById(R.id.input_ori_auth_edt);
        input_auth_tv = (TextView) findViewById(R.id.input_ori_auth_tv);
        ok_btn = (Button) findViewById(R.id.ok_btn);
        ok_btn.setOnClickListener(this);
        preAuth_rb.setOnCheckedChangeListener(this);
        preAuth_revoke_rb.setOnCheckedChangeListener(this);
        preAuth_complete_rb.setOnCheckedChangeListener(this);
        preAuth_complete_revoke_rb.setOnCheckedChangeListener(this);
        isShowOriVoucherNo(false);
        isShowMoney(true);
        isShowOriDate(false);
        isShowOriAuthNo(false);

        userInfoEdit = (EditText) findViewById(R.id.et_user_info);
        userCodeInfoEdit = (EditText) findViewById(R.id.et_user_code_info);
        merchantInfoEdit = (EditText) findViewById(R.id.et_merchant_info);
        merchantCodeInfoEdit = (EditText) findViewById(R.id.et_merchant_code_info);
    }

    /**
     * 是否显示原凭证号
     */
    private void isShowOriVoucherNo(boolean isShow) {
        if (isShow) {
            input_ori_voucher_no_tv.setVisibility(View.VISIBLE);
            input_ori_voucher_no_edt.setVisibility(View.VISIBLE);
        } else {
            input_ori_voucher_no_tv.setVisibility(View.GONE);
            input_ori_voucher_no_edt.setVisibility(View.GONE);
            input_ori_voucher_no_edt.setText("");
        }
    }

    /**
     * 是否显示钱
     */
    private void isShowMoney(boolean isShow) {
        if (isShow) {
            input_money_tv.setVisibility(View.VISIBLE);
            input_money_edt.setVisibility(View.VISIBLE);
        } else {
            input_money_tv.setVisibility(View.GONE);
            input_money_edt.setVisibility(View.GONE);
            input_money_edt.setText("");
        }
    }

    private void isShowOriDate(boolean isShow) {
        if (isShow) {
            input_ori_date_tv.setVisibility(View.VISIBLE);
            input_date_edt.setVisibility(View.VISIBLE);
        } else {
            input_ori_date_tv.setVisibility(View.GONE);
            input_date_edt.setVisibility(View.GONE);
            input_date_edt.setText("");
        }
    }

    private void isShowOriAuthNo(boolean isShow) {
        if (isShow) {
            input_auth_tv.setVisibility(View.VISIBLE);
            input_auth_edt.setVisibility(View.VISIBLE);
        } else {
            input_auth_tv.setVisibility(View.GONE);
            input_auth_edt.setVisibility(View.GONE);
            input_auth_edt.setText("");
        }
    }

    private int getTransType() {
        int transType = 3;
        if (preAuth_rb.isChecked()) {
            transType = 3;
            isShowOriVoucherNo(false);
            isShowOriDate(false);
            isShowMoney(true);
            isShowOriAuthNo(false);
        } else if (preAuth_revoke_rb.isChecked()) {
            transType = 4;
            isShowOriVoucherNo(false);
            isShowOriDate(true);
            isShowMoney(true);
            isShowOriAuthNo(true);
        } else if (preAuth_complete_rb.isChecked()) {
            transType = 5;
            isShowOriVoucherNo(false);
            isShowOriDate(true);
            isShowMoney(false);
            isShowOriAuthNo(true);
        } else if (preAuth_complete_revoke_rb.isChecked()) {
            transType = 6;
            isShowOriVoucherNo(true);
            isShowOriDate(false);
            isShowMoney(false);
            isShowOriAuthNo(false);
        }
        return transType;
    }

    @Override
    public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
        getTransType();
    }

    @Override
    public void onClick(View v) {
        Intent intent = new Intent("sunmi.payment.L3");
        int transType = getTransType();
        String amount = input_money_edt.getText().toString();
        String date = input_date_edt.getText().toString();
        String authNo = input_auth_edt.getText().toString();
        String oriVoucherNo = input_ori_voucher_no_edt.getText().toString();

        try {
            long money = Long.valueOf(amount);
            intent.putExtra("amount", money);
        } catch (Exception e) {
            e.printStackTrace();
        }

        intent.putExtra("transId", "fuck you");
        intent.putExtra("transType", transType);
        intent.putExtra("appId", getPackageName());
        intent.putExtra("oriTransDate", date);
        intent.putExtra("oriVoucherNo", oriVoucherNo);
        intent.putExtra("oriAuthNo", authNo);

        String printInfo = userInfoEdit.getText().toString();
        String printInfo2 = userCodeInfoEdit.getText().toString();
        String printMerchantInfo = merchantInfoEdit.getText().toString();
        String printMerchantInfo2 = merchantCodeInfoEdit.getText().toString();
        if (printInfo.length() > 100) {
            Toast.makeText(this, "用户联追加打印请在100字以内", Toast.LENGTH_SHORT).show();
        }
        if (printMerchantInfo.length() > 100) {
            Toast.makeText(this, "商户联追加打印请在100字以内", Toast.LENGTH_SHORT).show();
        }
        intent.putExtra("printInfo", printInfo);
        intent.putExtra("printInfo2", printInfo2);
        intent.putExtra("printMerchantInfo", printMerchantInfo);
        intent.putExtra("printMerchantInfo2", printMerchantInfo2);

        if (isIntentExisting(intent)) {
            startActivity(intent);
        } else {
            Toast.makeText(this, "此机器上没有安装L3应用", Toast.LENGTH_SHORT).show();
        }
    }

    public boolean isIntentExisting(Intent intent) {
        final PackageManager packageManager = getPackageManager();
        List<ResolveInfo> resolveInfo =
                packageManager.queryIntentActivities(intent,
                        PackageManager.MATCH_DEFAULT_ONLY);
        if (resolveInfo.size() > 0) {
            return true;
        }
        return false;
    }


}
