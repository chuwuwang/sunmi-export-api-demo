package sunmi.l3demo;

import android.app.Activity;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.content.pm.ResolveInfo;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.TextView;
import android.widget.Toast;

import java.util.List;

/**
 * Created by xurong on 2017/5/15.
 */

public class PreAuthActivity extends Activity implements View.OnClickListener, CompoundButton.OnCheckedChangeListener {
    private RadioButton preauth_rb;
    private RadioButton preauth_revoke_rb;
    private RadioButton preauth_complete_rb;
    private RadioButton preauth_complete_revoke_rb;
    private TextView input_money_tv;
    private TextView input_ori_voucher_no_tv;
    private EditText input_ori_voucher_no_edt;
    private EditText input_money_edt;
    private Button ok_btn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_preauth);
        Constants.signBtnEnable = true;
        preauth_rb = (RadioButton) findViewById(R.id.preauth_rb);
        preauth_revoke_rb = (RadioButton) findViewById(R.id.preauth_revoke_rb);
        preauth_complete_rb = (RadioButton) findViewById(R.id.preauth_complete_rb);
        preauth_complete_revoke_rb = (RadioButton) findViewById(R.id.preauth_complete_revoke_rb);
        input_money_tv = (TextView) findViewById(R.id.input_money_tv);
        input_ori_voucher_no_tv = (TextView) findViewById(R.id.input_ori_voucher_no_tv);
        input_money_edt = (EditText) findViewById(R.id.input_money_edt);
        input_ori_voucher_no_edt = (EditText) findViewById(R.id.input_ori_voucher_no_edt);
        ok_btn = (Button) findViewById(R.id.ok_btn);
        ok_btn.setOnClickListener(this);
        preauth_rb.setOnCheckedChangeListener(this);
        preauth_revoke_rb.setOnCheckedChangeListener(this);
        preauth_complete_rb.setOnCheckedChangeListener(this);
        preauth_complete_revoke_rb.setOnCheckedChangeListener(this);
        isShowOriVoucherNo(false);
        isShowMoney(true);
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
     *
     * @param isShow
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

    private int getTransType() {
        int transType;
        if (preauth_rb.isChecked()) {
            transType = 3;
            isShowMoney(true);
            isShowOriVoucherNo(false);
        } else if (preauth_revoke_rb.isChecked()) {
            transType = 4;
            isShowMoney(false);
            isShowOriVoucherNo(false);
        } else if (preauth_complete_rb.isChecked()) {
            transType = 5;
            isShowMoney(false);
            isShowOriVoucherNo(false);
        } else {
            transType = 6;
            isShowMoney(false);
            isShowOriVoucherNo(true);
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
        String voucherNo = "";
        String amount = "";
        if (transType == 6) {
            //预授权没有原凭证号
            voucherNo = input_ori_voucher_no_edt.getText().toString();
            intent.putExtra("oriVoucherNo", voucherNo);
            if (TextUtils.isEmpty(voucherNo)) {
                Toast.makeText(getBaseContext(), getString(R.string.please_input_voucher_no), Toast.LENGTH_SHORT).show();
                return;
            }
        }

        long _amount = 0;
        try {
            if (input_money_edt.isShown()) {
                //预授权撤销和预授权完成撤销没有金额
                amount = input_money_edt.getText().toString();
                _amount = Long.valueOf(amount);
                intent.putExtra("amount", _amount);
            }
        } catch (Exception e) {
            e.printStackTrace();
            Toast.makeText(getBaseContext(), getString(R.string.please_input_amount), Toast.LENGTH_SHORT).show();
            return;
        }


        String transId = System.currentTimeMillis() + "";
        intent.putExtra("transId", transId);
        intent.putExtra("transType", transType);
        intent.putExtra("appId", getPackageName());

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
