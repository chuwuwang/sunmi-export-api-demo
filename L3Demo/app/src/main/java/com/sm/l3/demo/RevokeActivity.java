package com.sm.l3.demo;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;

public class RevokeActivity extends BaseActivity {

    private EditText mEditVoucher;
    private EditText mEditTransId;
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
        mEditTransId = (EditText) findViewById(R.id.edit_input_trans_id);
        Button btnOk = (Button) findViewById(R.id.btn_ok);
        btnOk.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        String packageName = getPackageName();
        String transId = mEditTransId.getText().toString();

        boolean isManagePwd = mCheckBoxManagePwd.isChecked();
        String oriVoucherNo = mEditVoucher.getText().toString();

        Intent intent = new Intent(CALL_EXTRA_ACTION);
        intent.putExtra("transType", 1);
        intent.putExtra("transId", transId);
        intent.putExtra("appId", packageName);

        // 原交易凭证号
        // 如果有值则会直接跳转到刷卡界面
        // 为null则跳转到选择交易列表界面
        intent.putExtra("oriVoucherNo", oriVoucherNo);

        // 是否输入管理员密码
        intent.putExtra("isManagePwd", isManagePwd);

        // 添加用户自定义小票内容
        intent = addUserCustomTicketContent(intent);

        startActivity(intent);
    }


}
