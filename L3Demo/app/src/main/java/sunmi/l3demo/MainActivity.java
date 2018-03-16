package sunmi.l3demo;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.Toast;

public class MainActivity extends Activity implements OnClickListener {

    private Button mBtnConsume;
    private Button mBtnRevoke;
    private Button mBtnReturnGood;
    private Button mBtnPreAuth;
    private Button mBtnSettlement;
    private Button mBtnSignIn;
    private Button mBtnQueryBalance;
    private Button mBtnSystemManager;
    private Button mBtnPrint;
    private Button mBtnQueryMerchant;
    private Button mBtnSignOut;
    private Button mBtnTradeQueryOnline;
    private Button mBtnTradeQueryLocal;
    private Button mBtnCustom;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mBtnSignIn = (Button) findViewById(R.id.btn_sign_in);
        mBtnConsume = (Button) findViewById(R.id.btn_consume);
        mBtnRevoke = (Button) findViewById(R.id.btn_revoke);
        mBtnPreAuth = (Button) findViewById(R.id.btn_pre_auth);
        mBtnReturnGood = (Button) findViewById(R.id.btn_return_goods);
        mBtnSettlement = (Button) findViewById(R.id.btn_settlement);
        mBtnQueryBalance = (Button) findViewById(R.id.btn_query_balance);
        mBtnSystemManager = (Button) findViewById(R.id.btn_system_manager);
        mBtnPrint = (Button) findViewById(R.id.btn_print);
        mBtnQueryMerchant = (Button) findViewById(R.id.btn_query_merchant);
        mBtnSignOut = (Button) findViewById(R.id.btn_sign_out);
        mBtnTradeQueryOnline = (Button) findViewById(R.id.btn_query_trade_online);
        mBtnTradeQueryLocal = (Button) findViewById(R.id.btn_query_trade_local);
        mBtnCustom = (Button) findViewById(R.id.btn_custom);

        mBtnSettlement.setOnClickListener(this);
        mBtnQueryBalance.setOnClickListener(this);
        mBtnSystemManager.setOnClickListener(this);
        mBtnPrint.setOnClickListener(this);
        mBtnQueryMerchant.setOnClickListener(this);
        mBtnSignOut.setOnClickListener(this);
        mBtnCustom.setOnClickListener(this);
        mBtnSignIn.setOnClickListener(this);
        mBtnConsume.setOnClickListener(this);
        mBtnRevoke.setOnClickListener(this);
        mBtnPreAuth.setOnClickListener(this);
        mBtnReturnGood.setOnClickListener(this);
        mBtnTradeQueryOnline.setOnClickListener(this);
        mBtnTradeQueryLocal.setOnClickListener(this);
    }


    @Override
    public void onClick(View v) {
        boolean isOpen = true;
        setEnable(false);
        int id = v.getId();
        Intent intent = new Intent("sunmi.payment.L3");
        if (id == R.id.btn_consume) {
            // 消费
            isOpen = false;
            openActivity(ConsumeActivity.class);
        } else if (id == R.id.btn_revoke) {
            // 消费撤销
            isOpen = false;
            openActivity(RevokeActivity.class);
        } else if (id == R.id.btn_return_goods) {
            // 退货
            isOpen = false;
            openActivity(ReturnGoodsActivity.class);
        } else if (id == R.id.btn_pre_auth) {
            // 预授权
            isOpen = false;
            openActivity(PreAuthActivity.class);
        } else if (id == R.id.btn_settlement) {
            // 结算
            isOpen = false;
            openActivity(SettlementActivity.class);
        } else if (id == R.id.btn_sign_in) {
            // 签到
            intent.putExtra("transType", 8);
        } else if (id == R.id.btn_query_balance) {
            // 余额查询
            intent.putExtra("transType", 9);
        } else if (id == R.id.btn_system_manager) {
            // 系统管理
            intent.putExtra("transType", 10);
        } else if (id == R.id.btn_print) {
            // 打印
            openActivity(PrintActivity.class);
        } else if (id == R.id.btn_query_merchant) {
            // 商户信息查询
            intent.putExtra("transType", 13);
        } else if (id == R.id.btn_sign_out) {
            // 签退
            intent.putExtra("transType", 15);
        } else if (id == R.id.btn_query_trade_online) {
            // 联机交易记录查询
            isOpen = false;
            openActivity(QueryAllTradeActivity.class);
        } else if (id == R.id.btn_query_trade_local) {
            // 本地交易记录查询
            isOpen = false;
            openActivity(LocalTradeQueryActivity.class);
        } else if (id == R.id.btn_custom) {
            isOpen = false;
            openActivity(CustomActivity.class);
        }
        if (isOpen) {
            String packageName = getPackageName();
            intent.putExtra("appId", packageName);
            intent.putExtra("transId", "Sunmi_123456789");
            boolean existing = Util.isIntentExisting(intent, this);
            if (existing) {
                startActivity(intent);
            } else {
                Toast.makeText(this, "此机器上没有安装L3应用", Toast.LENGTH_SHORT).show();
            }
        }
    }

    @Override
    protected void onResume() {
        super.onResume();
        setEnable(true);
    }

    private void setEnable(boolean enable) {
        mBtnSignIn.setEnabled(enable);
        mBtnConsume.setEnabled(enable);
        mBtnRevoke.setEnabled(enable);
        mBtnPreAuth.setEnabled(enable);
        mBtnReturnGood.setEnabled(enable);
        mBtnSettlement.setEnabled(enable);
        mBtnQueryBalance.setEnabled(enable);
        mBtnSystemManager.setEnabled(enable);
        mBtnPrint.setEnabled(enable);
        mBtnQueryMerchant.setEnabled(enable);
        mBtnSignOut.setEnabled(enable);
        mBtnCustom.setEnabled(enable);
        mBtnTradeQueryOnline.setEnabled(enable);
        mBtnTradeQueryLocal.setEnabled(enable);
    }

    private void openActivity(Class clazz) {
        Intent intent = new Intent(this, clazz);
        startActivity(intent);
    }

}
