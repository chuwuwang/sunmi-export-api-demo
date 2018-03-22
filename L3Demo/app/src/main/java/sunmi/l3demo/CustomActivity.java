package sunmi.l3demo;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;

/**
 * @author by 47673 on 2017/5/15.
 */
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
        Intent intent = new Intent("sunmi.payment.L3");
        try {
            int parseInt = Integer.parseInt(mEditRequestType.getText().toString());
            intent.putExtra("transType", parseInt);
        } catch (Exception e) {
            e.printStackTrace();
        }
        try {
            int parseInt = Integer.parseInt(mEditPayType.getText().toString());
            intent.putExtra("paymentType", parseInt);
        } catch (Exception e) {
            e.printStackTrace();
        }
        try {
            long parseLong = Long.parseLong(mEditAmount.getText().toString());
            intent.putExtra("amount", parseLong);
        } catch (Exception e) {
            e.printStackTrace();
        }

        intent.putExtra("transId", mEditTransId.getText().toString());
        intent.putExtra("oriVoucherNo", mEditVoucher.getText().toString());
        intent.putExtra("oriTransDate", mEditDate.getText().toString());
        intent.putExtra("oriReferenceNo", mEditReference.getText().toString());
        intent.putExtra("oriQROrderNo", mEditQRCode.getText().toString());
        intent.putExtra("oriAuthNo", mEditAuth.getText().toString());
        intent.putExtra("appId", mEditAppId.getText().toString());
        intent.putExtra("riseString", mEditPrintHeaderEdit.getText().toString());

        intent.putExtra("isManagePwd", mCheckBoxManagePwd.isChecked());
        intent.putExtra("isLastTrade", mCheckBoxLastTrade.isChecked());
        intent.putExtra("isSettlementDetail", mCheckBoxDetail.isChecked());

        // 添加用户自定义小票内容
        intent = addUserCustomTicketContent(intent);

        startActivity(intent);
    }


}
