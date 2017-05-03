package sunmi.l3demo;

import android.app.Activity;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Toast;

public class MainActivity extends Activity implements OnClickListener {

	public static final String TAG = "MainActivity";

	// 原交易凭证号
	private String voucherNo;
	// 原参考号
	private String referenceNo;
	private String date;
	// 扫码消费订单号
	private String QROrderNo;
	// 交易ID
	private String transId;

	private long amount;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);

		findViewById(R.id.btn_consume_bank).setOnClickListener(this);
		findViewById(R.id.btn_consume_alipay).setOnClickListener(this);
		findViewById(R.id.btn_consume_wechat).setOnClickListener(this);

		findViewById(R.id.btn_revoke_bank).setOnClickListener(this);
		findViewById(R.id.btn_revoke_alipay).setOnClickListener(this);
		findViewById(R.id.btn_revoke_wechat).setOnClickListener(this);

		findViewById(R.id.btn_return_good_bank).setOnClickListener(this);
		findViewById(R.id.btn_return_good_alipay).setOnClickListener(this);
		findViewById(R.id.btn_return_good_wechat).setOnClickListener(this);

		findViewById(R.id.btn_pre_authorize).setOnClickListener(this);
		findViewById(R.id.btn_pre_authorize_revoke).setOnClickListener(this);
		findViewById(R.id.btn_pre_authorize_complete).setOnClickListener(this);
		findViewById(R.id.btn_pre_authorize_complete_revoke).setOnClickListener(this);

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

		// 注册广播
		IntentFilter filter = new IntentFilter();
		filter.addAction("sunmi.payment.L3Route.RESULT");
		registerReceiver(receiver, filter);
	}

	@Override
	protected void onDestroy() {
		super.onDestroy();
		if (receiver != null) {
			unregisterReceiver(receiver);
		}
	}

	private Handler mHandler=new Handler();
	private BroadcastReceiver receiver = new BroadcastReceiver() {

		@Override
		public void onReceive(Context context, Intent intent) {
			String action = intent.getAction();
			if (action.equals("sunmi.payment.L3Route.RESULT")) {
				int resultCode = intent.getIntExtra("resultCode", -1);
				amount = intent.getLongExtra("amount", 0);
				voucherNo = intent.getStringExtra("voucherNo");
				referenceNo = intent.getStringExtra("referenceNo");
				date = intent.getStringExtra("transDate");
				transId = intent.getStringExtra("transId");
				String batchNo = intent.getStringExtra("batchNo");
				String cardNo = intent.getStringExtra("cardNo");
				String cardType = intent.getStringExtra("cardType");
				String issue = intent.getStringExtra("issue");
				String terminalId = intent.getStringExtra("terminalId");
				String merchantId = intent.getStringExtra("merchantId");
				String merchantName = intent.getStringExtra("merchantName");
				String merchantNameEn = intent.getStringExtra("merchantNameEn");
				int paymentType = intent.getIntExtra("paymentType", 1);
				String transTime = intent.getStringExtra("transTime");
				String errorCode = intent.getStringExtra("errorId");
				final String errorMsg = intent.getStringExtra("errorMsg");
				long balance = intent.getLongExtra("balance", 0);
				int transNum = intent.getIntExtra("transNum", 0);
				long totalAmount = intent.getLongExtra("totalAmount", 0L);
				if (resultCode == 0) {
					// 交易成功
					Toast.makeText(MainActivity.this, "交易成功, 具体信息请查看控制台的Log", Toast.LENGTH_SHORT).show();
					Log.e(TAG, "交易成功");
				} else if (resultCode == -1) {
					// 交易失败
					mHandler.postDelayed(new Runnable() {
						@Override
						public void run() {
							Toast.makeText(MainActivity.this, errorMsg, Toast.LENGTH_SHORT).show();
						}
					},1000);
					Log.e(TAG, errorMsg);
				}
				Log.e(TAG, "resultCode:" + resultCode + "\namount:" + amount + "\nvoucherNo:" + voucherNo
						+ "\nreferenceNo:" + referenceNo + "\nbatchNo:" + batchNo + "\ncardNo:" + cardNo + "\ncardType:"
						+ cardType + "\nissue:" + issue + "\nterminalId:" + terminalId + "\nmerchantId:" + merchantId
						+ "\nmerchantName:" + merchantName + "\npaymentType:" + paymentType + "\ntransDate:" + date
						+ "\ntransTime:" + transTime + "\nerrorCode:" + errorCode + "\nerrorMsg:" + errorMsg
						+ "\nbalance:" + balance + "\ntransId:" + transId + "\nmerchantNameEn:" + merchantNameEn
						+ "\ntransNum:"+transNum + "\ntotalAmount:"+totalAmount);
			}
		}

	};

	@Override
	public void onClick(View v) {
		Intent intent = new Intent("sunmi.payment.L3");
		intent.putExtra("transId", "L3 demo transId");
		switch (v.getId()) {
		// 消费
		case R.id.btn_consume_bank:
			intent.putExtra("transType", 0);
			intent.putExtra("paymentType", 0);
			intent.putExtra("amount", 1L);
			intent.putExtra("appId", getPackageName());
			break;
		case R.id.btn_consume_alipay:
			intent.putExtra("transType", 0);
			intent.putExtra("paymentType", 1);
			intent.putExtra("amount", 1L);
			intent.putExtra("appId", getPackageName());
			break;
		case R.id.btn_consume_wechat:
			intent.putExtra("transType", 0);
			intent.putExtra("paymentType", 2);
			intent.putExtra("amount", 1L);
			intent.putExtra("appId", getPackageName());
			break;

		// 撤销
		case R.id.btn_revoke_bank:
			intent.putExtra("transType", 1);
			intent.putExtra("paymentType", 0);
			intent.putExtra("oriVoucherNo", voucherNo);//如果有值则会直接跳转到刷卡界面，为""则跳转到选择交易列表界面
			intent.putExtra("appId", getPackageName());
			break;
		case R.id.btn_revoke_alipay:
			intent.putExtra("transType", 1);
			intent.putExtra("paymentType", 1);
			intent.putExtra("oriVoucherNo", voucherNo);//如果有值则会直接跳转到刷卡界面，为""则跳转到选择交易列表界面
			intent.putExtra("appId", getPackageName());
			break;
		case R.id.btn_revoke_wechat:
			intent.putExtra("transType", 1);
			intent.putExtra("paymentType", 2);
			intent.putExtra("oriVoucherNo", voucherNo);//如果有值则会直接跳转到刷卡界面，为""则跳转到选择交易列表界面
			intent.putExtra("appId", getPackageName());
			break;

		// 退货
		case R.id.btn_return_good_bank:
			intent.putExtra("transType", 2);
			intent.putExtra("paymentType", 0);
			intent.putExtra("amount", amount);
			intent.putExtra("oriReferenceNo", voucherNo);//oriReferenceNo不能为"",否则交易失败
			intent.putExtra("oriTransDate", "0429");
			intent.putExtra("appId", getPackageName());
			break;
		case R.id.btn_return_good_alipay:
			intent.putExtra("transType", 2);
			intent.putExtra("paymentType", 1);
			intent.putExtra("amount", 1L);
			intent.putExtra("oriQROrderNo", "000000775196");//当oriQROrderNo不为"",会跳转到退货确认界面
			intent.putExtra("appId", getPackageName());
			break;
		case R.id.btn_return_good_wechat:
			intent.putExtra("transType", 2);
			intent.putExtra("paymentType", 2);
			intent.putExtra("amount", amount);
			intent.putExtra("oriQROrderNo", "");//当oriQROrderNo为"",会跳转到扫描界面
			intent.putExtra("appId", getPackageName());
			break;

		case R.id.btn_pre_authorize:
			// 预授权
			intent.putExtra("transType", 3);
			intent.putExtra("amount", 6888L);
			intent.putExtra("appId", getPackageName());
			break;

		case R.id.btn_pre_authorize_revoke:
			// 预授权撤销
			intent.putExtra("transType", 4);
			intent.putExtra("oriVoucherNo", voucherNo);
			intent.putExtra("appId", getPackageName());
			break;

		case R.id.btn_pre_authorize_complete:
			// 预授权完成
			intent.putExtra("transType", 5);
			intent.putExtra("amount", 6888L);
			intent.putExtra("oriVoucherNo", voucherNo);
			intent.putExtra("appId", getPackageName());
			break;
		case R.id.btn_pre_authorize_complete_revoke:
			// 预授权完成撤销
			intent.putExtra("transType", 6);
			intent.putExtra("oriVoucherNo", voucherNo);
			intent.putExtra("appId", getPackageName());
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

		// 二维码被扫
		case R.id.btn_generate_alipay:
			intent.putExtra("transType", 14);
			intent.putExtra("paymentType", 1);
			intent.putExtra("amount", 1L);
			intent.putExtra("appId", getPackageName());
			break;
		case R.id.btn_generate_wechat:
			intent.putExtra("transType", 14);
			intent.putExtra("paymentType", 2);
			intent.putExtra("amount", 1L);
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
		startActivity(intent);
	}

}
