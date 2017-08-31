package sunmi.l3demo;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.Toast;

/**
 * @author by 47673 on 2017/5/15.
 */
public class CustomConsumeActivity extends Activity implements View.OnClickListener {

    private EditText transTypeEdit, paymentTypeEdit, amountEdit, transID, oriVoucherNoEdit, oriDateEdit, oriReferenceNoEdit, QROrderNoEdit, appIDEdit;

    private EditText userInfoEdit;
    private EditText userCodeInfoEdit;
    private EditText merchantInfoEdit;
    private EditText merchantCodeInfoEdit;

    private EditText printHeaderEdit;

    private CheckBox isPrintCb;
    private CheckBox isSettlePrintCb;
    private CheckBox isManagePwdCb;
    private CheckBox isLastTradeCb;
    private CheckBox isOrderDetailCb;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_custom_consume);
        initView();
    }

    private void initView() {
        transTypeEdit = (EditText) findViewById(R.id.input_trans_type_edt);
        paymentTypeEdit = (EditText) findViewById(R.id.input_payment_type_edt);
        amountEdit = (EditText) findViewById(R.id.input_money_edt);
        transID = (EditText) findViewById(R.id.input_trans_id);
        oriVoucherNoEdit = (EditText) findViewById(R.id.input_ori_voucher_no);
        oriDateEdit = (EditText) findViewById(R.id.ori_date_edt);
        oriReferenceNoEdit = (EditText) findViewById(R.id.ori_reference_no_edt);
        QROrderNoEdit = (EditText) findViewById(R.id.ori_QROrderNo_edt);
        appIDEdit = (EditText) findViewById(R.id.appId);
        appIDEdit.setText(getPackageName());
        Button ok = (Button) findViewById(R.id.ok_btn);
        ok.setOnClickListener(this);

        userInfoEdit = (EditText) findViewById(R.id.et_user_info);
        userCodeInfoEdit = (EditText) findViewById(R.id.et_user_code_info);
        merchantInfoEdit = (EditText) findViewById(R.id.et_merchant_info);
        merchantCodeInfoEdit = (EditText) findViewById(R.id.et_merchant_code_info);

        printHeaderEdit = (EditText) findViewById(R.id.et_print_header);

        isPrintCb = (CheckBox) findViewById(R.id.cb_code_print);
        isSettlePrintCb = (CheckBox) findViewById(R.id.cb_settle_print);
        isManagePwdCb = (CheckBox) findViewById(R.id.cb_manage_pwd);
        isLastTradeCb = (CheckBox) findViewById(R.id.cb_last_trade);
        isOrderDetailCb = (CheckBox) findViewById(R.id.cb_order_detail);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.ok_btn:
                Intent intent = new Intent("sunmi.payment.L3");
                intent.putExtra("transId", System.currentTimeMillis() + "");

                try {
                    String transType = transTypeEdit.getText().toString();
                    intent.putExtra("transType", Integer.parseInt(transType));
                } catch (Exception e) {
                    e.printStackTrace();
                }
                try {
                    String paymentType = paymentTypeEdit.getText().toString();
                    intent.putExtra("paymentType", Integer.parseInt(paymentType));
                } catch (Exception e) {
                    e.printStackTrace();
                }
                try {
                    String amount = amountEdit.getText().toString();
                    intent.putExtra("amount", Long.parseLong(amount));
                } catch (Exception e) {
                    e.printStackTrace();
                }

                String transId = transID.getText().toString();
                intent.putExtra("transId", transId);

                String voucherNo = oriVoucherNoEdit.getText().toString();
                intent.putExtra("oriVoucherNo", voucherNo);

                String date = oriDateEdit.getText().toString();
                intent.putExtra("oriTransDate", date);

                String referenceNo = oriReferenceNoEdit.getText().toString();
                intent.putExtra("oriReferenceNo", referenceNo);

                String qrOrder = QROrderNoEdit.getText().toString();
                intent.putExtra("oriQROrderNo", qrOrder);

                String appId = appIDEdit.getText().toString();
                intent.putExtra("appId", appId);

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

                String header = printHeaderEdit.getText().toString();
                intent.putExtra("riseString", header);

                intent.putExtra("isPrintTicket", isPrintCb.isChecked());
                intent.putExtra("isPrintSettleTicket", isSettlePrintCb.isChecked());
                intent.putExtra("isManagePwd", isManagePwdCb.isChecked());
                intent.putExtra("isLastTrade", isLastTradeCb.isChecked());
                intent.putExtra("isSettlementDetail", isOrderDetailCb.isChecked());

                if (Util.isIntentExisting(intent, this)) {
                    startActivity(intent);
                } else {
                    Toast.makeText(this, "此机器上没有安装L3应用", Toast.LENGTH_SHORT).show();
                }
                break;
        }
    }


}
