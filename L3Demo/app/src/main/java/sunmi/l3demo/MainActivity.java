package sunmi.l3demo;

import android.app.Activity;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.content.pm.ResolveInfo;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.Toast;

import java.util.List;

public class MainActivity extends Activity implements OnClickListener {

    private Button signBtn, consumeBtn, revokeBtn, preAuthBtn, returnGoodsBtn, settlementBtn, queryBalanceBtn,
            systemManagerBtn, printBtn, lastTransactionQueryBtn, queryMerchantBtn, signOutBtn, selectConsumptionBtn;

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
    }


    @Override
    public void onClick(View v) {
        Intent intent = new Intent("sunmi.payment.L3");
        String transId = System.currentTimeMillis() + "";
        intent.putExtra("transId", transId);
        Constants.signBtnEnable = false;
        setEnable();
        switch (v.getId()) {
            // 消费
            case R.id.btn_consume:
                startActivity(new Intent(this, ConsumeActivity.class));
                return;
            //消费撤销
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
                intent.putExtra("transType", 7);
                intent.putExtra("appId", getPackageName());
                break;
            // 签到
            case R.id.btn_sign:
                intent.putExtra("transType", 8);
                intent.putExtra("appId", getPackageName());
                break;
            // 余额查询
            case R.id.btn_query_balance:
                intent.putExtra("transType", 9);
                intent.putExtra("appId", getPackageName());
                break;
            // 系统管理
            case R.id.btn_system_manager:
                intent.putExtra("transType", 10);
                intent.putExtra("appId", getPackageName());
                break;
            // 打印
            case R.id.btn_print:
                intent.putExtra("transType", 11);
                intent.putExtra("appId", getPackageName());
                break;
            // 末笔查询
            case R.id.btn_last_transaction_query:
                intent.putExtra("transType", 12);
                intent.putExtra("appId", getPackageName());
                break;
            // 商户信息查询
            case R.id.btn_query_merchant:
                intent.putExtra("transType", 13);
                intent.putExtra("appId", getPackageName());
                break;
            // 签退
            case R.id.btn_sign_out:
                intent.putExtra("transType", 15);
                intent.putExtra("appId", getPackageName());
                break;
            case R.id.btn_select_consumption:
                startActivity(new Intent(this, CustomConsumeActivity.class));
                return;
            default:
                break;
        }
        if (isIntentExisting(intent)) {
            startActivity(intent);
        } else {
            Constants.signBtnEnable = true;
            setEnable();
            Toast.makeText(this, "此机器上没有安装L3应用", Toast.LENGTH_SHORT).show();
        }
    }

    @Override
    protected void onResume() {
        super.onResume();
        Constants.signBtnEnable = true;
        setEnable();
    }

    private void setEnable() {
        signBtn.setEnabled(Constants.signBtnEnable);
        consumeBtn.setEnabled(Constants.signBtnEnable);
        revokeBtn.setEnabled(Constants.signBtnEnable);
        preAuthBtn.setEnabled(Constants.signBtnEnable);
        returnGoodsBtn.setEnabled(Constants.signBtnEnable);
        settlementBtn.setEnabled(Constants.signBtnEnable);
        queryBalanceBtn.setEnabled(Constants.signBtnEnable);
        systemManagerBtn.setEnabled(Constants.signBtnEnable);
        printBtn.setEnabled(Constants.signBtnEnable);
        lastTransactionQueryBtn.setEnabled(Constants.signBtnEnable);
        queryMerchantBtn.setEnabled(Constants.signBtnEnable);
        signOutBtn.setEnabled(Constants.signBtnEnable);
        selectConsumptionBtn.setEnabled(Constants.signBtnEnable);
    }

    public boolean isIntentExisting(Intent intent) {
        final PackageManager packageManager = getPackageManager();
        List<ResolveInfo> resolveInfo =
                packageManager.queryIntentActivities(intent,
                        PackageManager.MATCH_DEFAULT_ONLY);
        return resolveInfo.size() > 0;
    }

}
