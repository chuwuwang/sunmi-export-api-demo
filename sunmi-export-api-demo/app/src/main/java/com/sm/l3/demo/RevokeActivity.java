package com.sm.l3.demo;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
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
        mEditVoucher = findViewById(R.id.edit_input_voucher);
        mCheckBoxManagePwd = findViewById(R.id.cb_manage_pwd);
        mEditTransId = findViewById(R.id.edit_input_trans_id);

        findViewById(R.id.btn_ok).setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        String transId = mEditTransId.getText().toString();
        boolean isManagePwd = mCheckBoxManagePwd.isChecked();
        String oriVoucherNo = mEditVoucher.getText().toString();

        Intent intent = new Intent(CALL_EXTRA_ACTION);

        intent.putExtra("appId", BuildConfig.APPLICATION_ID);

        intent.putExtra("transId", transId);

        intent.putExtra("transType", 1);
        intent.putExtra("isManagePwd", isManagePwd);
        intent.putExtra("oriVoucherNo", oriVoucherNo);

        intent = addPaymentChannel(intent);
        intent = addUserCustomTicketContent(intent);

        startActivity(intent);
    }


}
