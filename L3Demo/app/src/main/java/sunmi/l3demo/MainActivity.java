package sunmi.l3demo;

import android.app.Activity;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Toast;

public class MainActivity extends Activity implements OnClickListener {

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
				String errorMsg = intent.getStringExtra("errorMsg");
				long balance = intent.getLongExtra("balance", 0);
				int transNum = intent.getIntExtra("transNum", 0);
				long totalAmount = intent.getLongExtra("totalAmount", 0L);
				if (resultCode == 0) {
					// 交易成功
					Toast.makeText(context, "resultCode:" + resultCode + " amount:" + amount + " voucherNo:" + voucherNo
							+ " referenceNo:" + referenceNo + " batchNo:" + batchNo + " cardNo:" + cardNo + " cardType:"
							+ cardType + " issue:" + issue + " terminalId:" + terminalId + " merchantId:" + merchantId
							+ " merchantName:" + merchantName + " paymentType:" + paymentType + " transDate:" + date
							+ " transTime:" + transTime + " errorCode:" + errorCode + " errorMsg:" + errorMsg
							+ " balance:" + balance + " transId:" + transId + " merchantNameEn:" + merchantNameEn
							+ " transNum:"+transNum + " totalAmount:"+totalAmount,
							Toast.LENGTH_SHORT).show();
				} else if (resultCode == -1) {
					// 交易失败
					Toast.makeText(context, errorMsg, Toast.LENGTH_SHORT).show();
				}
			}
		}

	};

	@Override
	public void onClick(View v) {
		Intent intent = new Intent("sunmi.payment.L3");
		switch (v.getId()) {
		// 消费
		case R.id.btn_consume_bank:
			intent.putExtra("transType", 0);
			intent.putExtra("paymentType", 0);
			intent.putExtra("amount", 5555L);
			intent.putExtra("appId", getPackageName());
			startActivity(intent);
			break;
		case R.id.btn_consume_alipay:
			intent.putExtra("transType", 0);
			intent.putExtra("paymentType", 1);
			intent.putExtra("amount", 1L);
			intent.putExtra("appId", getPackageName());
			startActivity(intent);
			break;
		case R.id.btn_consume_wechat:
			intent.putExtra("transType", 0);
			intent.putExtra("paymentType", 2);
			intent.putExtra("amount", 1L);
			intent.putExtra("appId", getPackageName());
			startActivity(intent);
			break;

		// 撤销
		case R.id.btn_revoke_bank:
			intent.putExtra("transType", 1);
			intent.putExtra("paymentType", 0);
			intent.putExtra("oriVoucherNo", voucherNo);
			intent.putExtra("appId", getPackageName());
			startActivity(intent);
			break;
		case R.id.btn_revoke_alipay:
			intent.putExtra("transType", 1);
			intent.putExtra("paymentType", 1);
			intent.putExtra("oriVoucherNo", voucherNo);
			intent.putExtra("appId", getPackageName());
			startActivity(intent);
			break;
		case R.id.btn_revoke_wechat:
			intent.putExtra("transType", 1);
			intent.putExtra("paymentType", 2);
			intent.putExtra("oriVoucherNo", voucherNo);
			intent.putExtra("appId", getPackageName());
			startActivity(intent);
			break;

		// 退货
		case R.id.btn_return_good_bank:
			intent.putExtra("transType", 2);
			intent.putExtra("paymentType", 0);
			intent.putExtra("amount", amount);
			intent.putExtra("oriReferenceNo", referenceNo);
			intent.putExtra("oriTransDate", date);
			intent.putExtra("appId", getPackageName());
			startActivity(intent);
			break;
		case R.id.btn_return_good_alipay:
			intent.putExtra("transType", 2);
			intent.putExtra("paymentType", 1);
			intent.putExtra("amount", amount);
			intent.putExtra("oriQROrderNo", QROrderNo);
			intent.putExtra("appId", getPackageName());
			startActivity(intent);
			break;
		case R.id.btn_return_good_wechat:
			intent.putExtra("transType", 2);
			intent.putExtra("paymentType", 2);
			intent.putExtra("amount", amount);
			intent.putExtra("oriQROrderNo", QROrderNo);
			intent.putExtra("appId", getPackageName());
			startActivity(intent);
			break;

		// 预授权
		case R.id.btn_pre_authorize:
			intent.putExtra("transType", 3);
			intent.putExtra("amount", 6888L);
			intent.putExtra("appId", getPackageName());
			startActivity(intent);
			break;
		case R.id.btn_pre_authorize_revoke:
			intent.putExtra("transType", 4);
			intent.putExtra("oriVoucherNo", voucherNo);
			intent.putExtra("appId", getPackageName());
			startActivity(intent);
			break;
		case R.id.btn_pre_authorize_complete:
			intent.putExtra("transType", 5);
			intent.putExtra("amount", 6888L);
			intent.putExtra("oriVoucherNo", voucherNo);
			intent.putExtra("appId", getPackageName());
			startActivity(intent);
			break;
		case R.id.btn_pre_authorize_complete_revoke:
			intent.putExtra("transType", 6);
			intent.putExtra("oriVoucherNo", voucherNo);
			intent.putExtra("appId", getPackageName());
			startActivity(intent);
			break;

		// 结算
		case R.id.btn_settlement:
			intent.putExtra("transType", 7);
			intent.putExtra("appId", getPackageName());
			startActivity(intent);
			break;

		// 签到
		case R.id.btn_sign:
			intent.putExtra("transType", 8);
			intent.putExtra("appId", getPackageName());
			startActivity(intent);
			break;

		// 余额查询
		case R.id.btn_query_balance:
			intent.putExtra("transType", 9);
			intent.putExtra("appId", getPackageName());
			startActivity(intent);
			break;

		// 系统管理
		case R.id.btn_system_manager:
			intent.putExtra("transType", 10);
			intent.putExtra("appId", getPackageName());
			startActivity(intent);
			break;

		// 打印
		case R.id.btn_print:
			intent.putExtra("transType", 11);
			intent.putExtra("appId", getPackageName());
			startActivity(intent);
			break;

		// 末笔查询
		case R.id.btn_last_transaction_query:
			intent.putExtra("transType", 12);
			intent.putExtra("appId", getPackageName());
			startActivity(intent);
			break;

		// 商户信息查询
		case R.id.btn_query_merchant:
			intent.putExtra("transType", 13);
			intent.putExtra("appId", getPackageName());
			startActivity(intent);
			break;

		// 二维码被扫
		case R.id.btn_generate_alipay:
			intent.putExtra("transType", 14);
			intent.putExtra("paymentType", 1);
			intent.putExtra("amount", 1L);
			intent.putExtra("appId", getPackageName());
			startActivity(intent);
			break;
		case R.id.btn_generate_wechat:
			intent.putExtra("transType", 14);
			intent.putExtra("paymentType", 2);
			intent.putExtra("amount", 1L);
			intent.putExtra("appId", getPackageName());
			startActivity(intent);
			break;
		//签退
		case R.id.btn_sign_out:
			intent.putExtra("transType", 15);
			intent.putExtra("appId", getPackageName());
			startActivity(intent);
			break;
		default:
			break;
		}
	}

}
