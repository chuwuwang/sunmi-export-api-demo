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
public class CustomActivity extends Activity implements View.OnClickListener {

    private EditText transTypeEdit, paymentTypeEdit, amountEdit, transID,
            oriVoucherNoEdit, oriDateEdit, oriAuthNoEdit, oriReferenceNoEdit,
            qrOrderNoEdit, appIDEdit;

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
        setContentView(R.layout.activity_custom);
        initView();
    }

    private void initView() {
        transTypeEdit = (EditText) findViewById(R.id.input_trans_type);
        paymentTypeEdit = (EditText) findViewById(R.id.input_payment_type);
        amountEdit = (EditText) findViewById(R.id.input_money);
        transID = (EditText) findViewById(R.id.input_trans_id);
        oriVoucherNoEdit = (EditText) findViewById(R.id.input_ori_voucher_no);
        oriDateEdit = (EditText) findViewById(R.id.input_ori_date);
        oriReferenceNoEdit = (EditText) findViewById(R.id.input_ori_reference_no);
        qrOrderNoEdit = (EditText) findViewById(R.id.input_ori_qr_order_no);
        appIDEdit = (EditText) findViewById(R.id.input_app_id);
        oriAuthNoEdit = (EditText) findViewById(R.id.input_ori_auth_no);

        userInfoEdit = (EditText) findViewById(R.id.input_user_info);
        userCodeInfoEdit = (EditText) findViewById(R.id.input_user_code_info);
        merchantInfoEdit = (EditText) findViewById(R.id.input_merchant_info);
        merchantCodeInfoEdit = (EditText) findViewById(R.id.input_merchant_code_info);
        printHeaderEdit = (EditText) findViewById(R.id.input_print_header);

        isPrintCb = (CheckBox) findViewById(R.id.cb_code_print);
        isSettlePrintCb = (CheckBox) findViewById(R.id.cb_settle_print);
        isManagePwdCb = (CheckBox) findViewById(R.id.cb_manage_pwd);
        isLastTradeCb = (CheckBox) findViewById(R.id.cb_last_trade);
        isOrderDetailCb = (CheckBox) findViewById(R.id.cb_order_detail);

        Button ok = (Button) findViewById(R.id.btn_ok);
        ok.setOnClickListener(this);

        String packageName = getPackageName();
        appIDEdit.setText(packageName);
    }

    @Override
    public void onClick(View view) {
        Intent intent = new Intent("sunmi.payment.L3");
        try {
            String transType = transTypeEdit.getText().toString();
            int parseInt = Integer.parseInt(transType);
            intent.putExtra("transType", parseInt);
        } catch (Exception e) {
            e.printStackTrace();
        }
        try {
            String paymentType = paymentTypeEdit.getText().toString();
            int parseInt = Integer.parseInt(paymentType);
            intent.putExtra("paymentType", parseInt);
        } catch (Exception e) {
            e.printStackTrace();
        }
        try {
            String amount = amountEdit.getText().toString();
            long parseLong = Long.parseLong(amount);
            intent.putExtra("amount", parseLong);
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

        String qrOrder = qrOrderNoEdit.getText().toString();
        intent.putExtra("oriQROrderNo", qrOrder);

        String appId = appIDEdit.getText().toString();
        intent.putExtra("appId", appId);

        String oriAuthNo = oriAuthNoEdit.getText().toString();
        intent.putExtra("oriAuthNo", oriAuthNo);

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

        boolean existing = Util.isIntentExisting(intent, this);
        if (existing) {
            startActivity(intent);
        } else {
            Toast.makeText(this, "此机器上没有安装L3应用", Toast.LENGTH_SHORT).show();
        }
    }


}
