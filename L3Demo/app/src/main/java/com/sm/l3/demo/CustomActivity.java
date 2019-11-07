package com.sm.l3.demo;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.CheckBox;
import android.widget.EditText;

public class CustomActivity extends BaseActivity {

    private EditText mEditAuth;
    private EditText mEditDate;
    private EditText mEditAppId;
    private EditText mEditAmount;
    private EditText mEditQRCode;
    private EditText mEditVoucher;
    private EditText mEditReference;
    private EditText mEditRequestType;

    private EditText mEditTransId;
    private EditText mEditPayType;
    private EditText mEditPrintHeaderEdit;

    private CheckBox mCheckBoxDetail;
    private CheckBox mCheckBoxManagePwd;
    private CheckBox mCheckBoxLastTrade;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_custom);
        initView();
    }

    private void initView() {
        mEditAuth = findViewById(R.id.edit_input_auth);
        mEditDate = findViewById(R.id.edit_input_date);
        mEditAmount = findViewById(R.id.edit_input_money);
        mEditVoucher = findViewById(R.id.edit_input_voucher);
        mEditQRCode = findViewById(R.id.edit_input_qr_order);
        mEditTransId = findViewById(R.id.edit_input_trans_id);
        mEditReference = findViewById(R.id.edit_input_reference);
        mEditRequestType = findViewById(R.id.edit_input_trans_type);

        mEditAppId = findViewById(R.id.edit_input_app_id);
        mEditPayType = findViewById(R.id.edit_input_payment_type);
        mEditPrintHeaderEdit = findViewById(R.id.edit_input_print_header);

        mCheckBoxDetail = findViewById(R.id.cb_detail);
        mCheckBoxManagePwd = findViewById(R.id.cb_manage_pwd);
        mCheckBoxLastTrade = findViewById(R.id.cb_last_trade);

        findViewById(R.id.btn_ok).setOnClickListener(this);

        mEditAppId.setText(BuildConfig.APPLICATION_ID);
    }

    @Override
    public void onClick(View view) {
        String appId = mEditAppId.getText().toString();
        String transId = mEditTransId.getText().toString();

        String oriAuthNo = mEditAuth.getText().toString();
        String oriTransDate = mEditDate.getText().toString();
        String oriQROrderNo = mEditQRCode.getText().toString();
        String oriVoucherNo = mEditVoucher.getText().toString();
        String oriReferenceNo = mEditReference.getText().toString();

        String riseString = mEditPrintHeaderEdit.getText().toString();

        boolean isManagePwd = mCheckBoxManagePwd.isChecked();
        boolean isLastTrade = mCheckBoxLastTrade.isChecked();
        boolean isSettlementDetail = mCheckBoxDetail.isChecked();

        String amount = mEditAmount.getText().toString();
        String transType = mEditRequestType.getText().toString();
        String paymentType = mEditPayType.getText().toString();

        Intent intent = new Intent(CALL_EXTRA_ACTION);

        try {
            int value = Integer.parseInt(transType);
            intent.putExtra("transType", value);
        } catch (Exception e) {
            e.printStackTrace();
        }
        try {
            int value = Integer.parseInt(paymentType);
            intent.putExtra("paymentType", value);
        } catch (Exception e) {
            e.printStackTrace();
        }
        try {
            long value = Long.parseLong(amount);
            intent.putExtra("amount", value);
        } catch (Exception e) {
            e.printStackTrace();
        }

        intent.putExtra("appId", appId);
        intent.putExtra("transId", transId);

        intent.putExtra("oriAuthNo", oriAuthNo);
        intent.putExtra("oriVoucherNo", oriVoucherNo);
        intent.putExtra("oriTransDate", oriTransDate);
        intent.putExtra("oriQROrderNo", oriQROrderNo);
        intent.putExtra("oriReferenceNo", oriReferenceNo);

        intent.putExtra("riseString", riseString);

        intent.putExtra("isManagePwd", isManagePwd);
        intent.putExtra("isLastTrade", isLastTrade);
        intent.putExtra("isSettlementDetail", isSettlementDetail);

        intent = addUserCustomTicketContent(intent);

        startActivity(intent);
    }


}
