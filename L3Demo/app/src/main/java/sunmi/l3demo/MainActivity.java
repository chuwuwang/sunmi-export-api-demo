package sunmi.l3demo;

import android.app.Activity;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.pm.PackageManager;
import android.content.pm.ResolveInfo;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Toast;

import java.util.List;

public class MainActivity extends Activity implements OnClickListener {

    public static final String TAG = "MainActivity";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        findViewById(R.id.btn_consume).setOnClickListener(this);
        findViewById(R.id.btn_revoke).setOnClickListener(this);
        findViewById(R.id.btn_pre_auth).setOnClickListener(this);

        findViewById(R.id.btn_settlement).setOnClickListener(this);
        findViewById(R.id.btn_sign).setOnClickListener(this);
        findViewById(R.id.btn_query_balance).setOnClickListener(this);
        findViewById(R.id.btn_system_manager).setOnClickListener(this);
        findViewById(R.id.btn_print).setOnClickListener(this);
        findViewById(R.id.btn_last_transaction_query).setOnClickListener(this);
        findViewById(R.id.btn_query_merchant).setOnClickListener(this);

        findViewById(R.id.btn_generate_alipay).setOnClickListener(this);
        findViewById(R.id.btn_generate_wechat).setOnClickListener(this);
        findViewById(R.id.btn_sign_out).setOnClickListener(this);

        findViewById(R.id.btn_select_consumption).setOnClickListener(this);
    }


    @Override
    public void onClick(View v) {
        Intent intent = new Intent("sunmi.payment.L3");
        intent.putExtra("transId", "L3 demo transId");
        switch (v.getId()) {
            // 消费
            case R.id.btn_consume:
                startActivity(new Intent(this, ConsumeActivity.class));
                break;
            //消费撤销
            case R.id.btn_revoke:
                startActivity(new Intent(this, RevokeActivity.class));
                break;
            // 退货
            case R.id.btn_return_goods:
                startActivity(new Intent(this, ReturnGoodsActivity.class));
                break;
            // 预授权
            case R.id.btn_pre_auth:
                startActivity(new Intent(this, PreAuthActivity.class));
                break;
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
            //签退
            case R.id.btn_sign_out:
                intent.putExtra("transType", 15);
                intent.putExtra("appId", getPackageName());
                break;
            default:
                break;
        }
        if (isIntentExisting(intent)) {
            startActivity(intent);
        } else {
            Toast.makeText(this, "此机器上没有安装L3应用", Toast.LENGTH_SHORT).show();
        }

    }

    public boolean isIntentExisting(Intent intent) {
        final PackageManager packageManager = getPackageManager();
        List<ResolveInfo> resolveInfo =
                packageManager.queryIntentActivities(intent,
                        PackageManager.MATCH_DEFAULT_ONLY);
        if (resolveInfo.size() > 0) {
            return true;
        }
        return false;
    }

}
