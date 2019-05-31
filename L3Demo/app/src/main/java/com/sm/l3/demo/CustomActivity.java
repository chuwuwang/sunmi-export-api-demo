package com.sm.l3.demo;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;

public class CustomActivity extends BaseActivity {

    private EditText mEditVoucher;
    private EditText mEditReference;
    private EditText mEditAuth;
    private EditText mEditQRCode;
    private EditText mEditDate;
    private EditText mEditAmount;
    private EditText mEditAppId;
    private EditText mEditRequestType;
    private EditText mEditPayType;
    private EditText mEditTransId;
    private EditText mEditPrintHeaderEdit;


    private CheckBox mCheckBoxManagePwd;
    private CheckBox mCheckBoxLastTrade;
    private CheckBox mCheckBoxDetail;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_custom);
        initView();
    }

    private void initView() {
        mEditRequestType = (EditText) findViewById(R.id.edit_input_trans_type);
        mEditPayType = (EditText) findViewById(R.id.edit_input_payment_type);
        mEditAmount = (EditText) findViewById(R.id.edit_input_money);
        mEditTransId = (EditText) findViewById(R.id.edit_input_trans_id);
        mEditVoucher = (EditText) findViewById(R.id.edit_input_voucher);
        mEditDate = (EditText) findViewById(R.id.edit_input_date);
        mEditReference = (EditText) findViewById(R.id.edit_input_reference);
        mEditQRCode = (EditText) findViewById(R.id.edit_input_qr_order);
        mEditAppId = (EditText) findViewById(R.id.edit_input_app_id);
        mEditAuth = (EditText) findViewById(R.id.edit_input_auth);
        mEditPrintHeaderEdit = (EditText) findViewById(R.id.edit_input_print_header);

        mCheckBoxManagePwd = (CheckBox) findViewById(R.id.cb_manage_pwd);
        mCheckBoxLastTrade = (CheckBox) findViewById(R.id.cb_last_trade);
        mCheckBoxDetail = (CheckBox) findViewById(R.id.cb_detail);

        Button ok = (Button) findViewById(R.id.btn_ok);
        ok.setOnClickListener(this);

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
            int parseInt = Integer.parseInt(transType);
            intent.putExtra("transType", parseInt);
        } catch (Exception e) {
            e.printStackTrace();
        }

        try {
            int parseInt = Integer.parseInt(paymentType);
            intent.putExtra("paymentType", parseInt);
        } catch (Exception e) {
            e.printStackTrace();
        }

        try {
            long parseLong = Long.parseLong(amount);
            intent.putExtra("amount", parseLong);
        } catch (Exception e) {
            e.printStackTrace();
        }

        intent.putExtra("appId", appId);
        intent.putExtra("transId", transId);
        intent.putExtra("oriVoucherNo", oriVoucherNo);
        intent.putExtra("oriTransDate", oriTransDate);
        intent.putExtra("oriQROrderNo", oriQROrderNo);
        intent.putExtra("oriReferenceNo", oriReferenceNo);
        intent.putExtra("oriAuthNo", oriAuthNo);

        intent.putExtra("riseString", riseString);

        intent.putExtra("isManagePwd", isManagePwd);
        intent.putExtra("isLastTrade", isLastTrade);
        intent.putExtra("isSettlementDetail", isSettlementDetail);

        // 添加用户自定义小票内容
        intent = addUserCustomTicketContent(intent);

        startActivity(intent);
    }


}
