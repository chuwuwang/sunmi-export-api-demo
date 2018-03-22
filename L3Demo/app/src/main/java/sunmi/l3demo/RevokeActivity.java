package sunmi.l3demo;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;

/**
 * 撤销界面
 *
 * @author by xurong on 2017/5/15.
 */

public class RevokeActivity extends BaseActivity {

    private EditText mEditVoucher;
    private CheckBox mCheckBoxManagePwd;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_revoke);
        initView();
    }

    private void initView() {
        mEditVoucher = (EditText) findViewById(R.id.edit_input_voucher);
        mCheckBoxManagePwd = (CheckBox) findViewById(R.id.cb_manage_pwd);
        Button btnOk = (Button) findViewById(R.id.btn_ok);
        btnOk.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        Intent intent = new Intent("sunmi.payment.L3");
        intent.putExtra("transType", 1);
        intent.putExtra("appId", getPackageName());
        intent.putExtra("transId", System.currentTimeMillis() + "");
        // 原交易凭证号,如果有值则会直接跳转到刷卡界面,为null则跳转到选择交易列表界面
        intent.putExtra("oriVoucherNo", mEditVoucher.getText().toString());
        // 是否输入管理员密码
        intent.putExtra("isManagePwd", mCheckBoxManagePwd.isChecked());

        // 添加用户自定义小票内容
        intent = addUserCustomTicketContent(intent);

        startActivity(intent);
    }


}
