package sunmi.l3demo;

import android.app.Activity;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.content.pm.ResolveInfo;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.Toast;

import java.util.List;

/**
 * @author  by xurong on 2017/5/15.
 */

public class RevokeActivity extends Activity implements View.OnClickListener {
    private RadioButton bank_card_rb;
    private RadioButton alipay_scan_rb;
    private RadioButton wechat_scan_rb;
    private EditText input_ori_voucher_no;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_revoke);
        bank_card_rb = (RadioButton) findViewById(R.id.bank_card_rb);
        alipay_scan_rb = (RadioButton) findViewById(R.id.alipay_scan_rb);
        wechat_scan_rb = (RadioButton) findViewById(R.id.wechat_scan_rb);
        input_ori_voucher_no = (EditText) findViewById(R.id.input_ori_voucher_no);
        Button ok_btn;
        ok_btn = (Button) findViewById(R.id.ok_btn);
        ok_btn.setOnClickListener(this);
    }

    private int getType() {
        int paymentType;
        if (bank_card_rb.isChecked()) {
            paymentType = 0;
        } else if (alipay_scan_rb.isChecked()) {
            paymentType = 1;
        } else {
            paymentType = 2;
        }
        return paymentType;
    }

    @Override
    public void onClick(View v) {
        //支付类型
        int paymentType = getType();
        // 原交易凭证号
        //如果有值则会直接跳转到刷卡界面，为""则跳转到选择交易列表界面
        String voucherNo = input_ori_voucher_no.getText().toString();

        Intent intent = new Intent("sunmi.payment.L3");
        intent.putExtra("transId", "L3 demo transId");
        intent.putExtra("transType", 1);
        intent.putExtra("paymentType", paymentType);
        intent.putExtra("oriVoucherNo", voucherNo);
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
