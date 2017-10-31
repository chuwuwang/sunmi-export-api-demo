package sunmi.l3demo;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.Toast;

public class MainActivity extends Activity implements OnClickListener {

    private Button signBtn, consumeBtn, revokeBtn, preAuthBtn, returnGoodsBtn, settlementBtn, queryBalanceBtn,
            systemManagerBtn, printBtn, lastTransactionQueryBtn, queryMerchantBtn, signOutBtn, selectConsumptionBtn;
    private Button qrCodeTransactionQueryBtn, fyTransactionQueryBtn, localRecordTransactionQueryBtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        signBtn = (Button) findViewById(R.id.btn_sign);
        consumeBtn = (Button) findViewById(R.id.btn_consume);
        revokeBtn = (Button) findViewById(R.id.btn_revoke);
        preAuthBtn = (Button) findViewById(R.id.btn_pre_auth);
        returnGoodsBtn = (Button) findViewById(R.id.btn_return_goods);
        settlementBtn = (Button) findViewById(R.id.btn_settlement);
        queryBalanceBtn = (Button) findViewById(R.id.btn_query_balance);
        systemManagerBtn = (Button) findViewById(R.id.btn_system_manager);
        printBtn = (Button) findViewById(R.id.btn_print);
        lastTransactionQueryBtn = (Button) findViewById(R.id.btn_last_transaction_query);
        queryMerchantBtn = (Button) findViewById(R.id.btn_query_merchant);
        signOutBtn = (Button) findViewById(R.id.btn_sign_out);
        qrCodeTransactionQueryBtn = (Button) findViewById(R.id.btn_query_qrCode_transaction);
        fyTransactionQueryBtn = (Button) findViewById(R.id.btn_query_transaction_fy);
        localRecordTransactionQueryBtn = (Button) findViewById(R.id.btn_query_transaction_local_record);
        selectConsumptionBtn = (Button) findViewById(R.id.btn_select_consumption);

        settlementBtn.setOnClickListener(this);
        queryBalanceBtn.setOnClickListener(this);
        systemManagerBtn.setOnClickListener(this);
        printBtn.setOnClickListener(this);
        lastTransactionQueryBtn.setOnClickListener(this);
        queryMerchantBtn.setOnClickListener(this);
        signOutBtn.setOnClickListener(this);
        selectConsumptionBtn.setOnClickListener(this);
        signBtn.setOnClickListener(this);
        consumeBtn.setOnClickListener(this);
        revokeBtn.setOnClickListener(this);
        preAuthBtn.setOnClickListener(this);
        returnGoodsBtn.setOnClickListener(this);
        qrCodeTransactionQueryBtn.setOnClickListener(this);
        fyTransactionQueryBtn.setOnClickListener(this);
        localRecordTransactionQueryBtn.setOnClickListener(this);
    }


    @Override
    public void onClick(View v) {
        setEnable(false);
        Intent intent = new Intent("sunmi.payment.L3");
        switch (v.getId()) {
            // 消费
            case R.id.btn_consume:
                startActivity(new Intent(this, ConsumeActivity.class));
                return;
            // 消费撤销
            case R.id.btn_revoke:
                startActivity(new Intent(this, RevokeActivity.class));
                return;
            // 退货
            case R.id.btn_return_goods:
                startActivity(new Intent(this, ReturnGoodsActivity.class));
                return;
            // 预授权
            case R.id.btn_pre_auth:
                startActivity(new Intent(this, PreAuthActivity.class));
                return;
            // 结算
            case R.id.btn_settlement:
                startActivity(new Intent(this, SettlementActivity.class));
                return;
            // 签到
            case R.id.btn_sign:
                intent.putExtra("transType", 8);
                break;
            // 余额查询
            case R.id.btn_query_balance:
                intent.putExtra("transType", 9);
                break;
            // 系统管理
            case R.id.btn_system_manager:
                intent.putExtra("transType", 10);
                break;
            // 打印
            case R.id.btn_print:
                startActivity(new Intent(this, PrintActivity.class));
                return;
            // 末笔查询
            case R.id.btn_last_transaction_query:
                intent.putExtra("transType", 12);
                break;
            // 商户信息查询
            case R.id.btn_query_merchant:
                intent.putExtra("transType", 13);
                break;
            // 签退
            case R.id.btn_sign_out:
                intent.putExtra("transType", 15);
                break;
            // 扫码交易查询
            case R.id.btn_query_qrCode_transaction:
                startActivity(new Intent(this, QrCodeTransactionQueryActivity.class));
                return;
            // 交易查询（仅富友支持，发报文查询后台支付状态）
            case R.id.btn_query_transaction_fy:
                startActivity(new Intent(this, QueryAllTradeActivity.class));
                return;
            // 本地交易记录查询
            case R.id.btn_query_transaction_local_record:
                startActivity(new Intent(this, LocalRecordTransactionQueryActivity.class));
                return;
            // 自定义交易
            case R.id.btn_select_consumption:
                startActivity(new Intent(this, CustomConsumeActivity.class));
                return;
            default:
                break;
        }
        intent.putExtra("appId", getPackageName());
        intent.putExtra("transId", System.currentTimeMillis() + "");
        if (Util.isIntentExisting(intent, this)) {
            startActivity(intent);
        } else {
            setEnable(true);
            Toast.makeText(this, "此机器上没有安装L3应用", Toast.LENGTH_SHORT).show();
        }
    }

    @Override
    protected void onResume() {
        super.onResume();
        setEnable(true);
    }

    private void setEnable(boolean enable) {
        signBtn.setEnabled(enable);
        consumeBtn.setEnabled(enable);
        revokeBtn.setEnabled(enable);
        preAuthBtn.setEnabled(enable);
        returnGoodsBtn.setEnabled(enable);
        settlementBtn.setEnabled(enable);
        queryBalanceBtn.setEnabled(enable);
        systemManagerBtn.setEnabled(enable);
        printBtn.setEnabled(enable);
        lastTransactionQueryBtn.setEnabled(enable);
        queryMerchantBtn.setEnabled(enable);
        signOutBtn.setEnabled(enable);
        selectConsumptionBtn.setEnabled(enable);
        qrCodeTransactionQueryBtn.setEnabled(enable);
        fyTransactionQueryBtn.setEnabled(enable);
        localRecordTransactionQueryBtn.setEnabled(enable);
    }


}
