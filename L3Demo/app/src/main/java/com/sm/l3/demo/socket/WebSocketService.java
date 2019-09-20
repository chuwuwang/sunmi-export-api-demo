package com.sm.l3.demo.socket;

import android.content.Intent;
import android.util.Log;
import android.widget.Toast;

import com.google.gson.Gson;
import com.sm.l3.demo.MyApplication;
import com.sm.l3.demo.R;
import com.sm.l3.demo.ResultActivity;

import org.java_websocket.client.WebSocketClient;
import org.java_websocket.handshake.ServerHandshake;

import java.net.URI;

public class WebSocketService {

    private static final String TAG = "nsz";

    private static WebSocketService sInstance = new WebSocketService();

    private WebSocketService() {

    }

    public static WebSocketService getInstance() {
        return sInstance;
    }

    public WebSocketClient mWebSocketClient;

    private OnConnectListener mOnConnectListener;

    public interface OnConnectListener {

        void onSuccess();

        void onFailed();

    }

    public void init(String ip, String port, OnConnectListener listener) throws Exception {
        this.mOnConnectListener = listener;
        URI uri = new URI("ws://" + ip + ":" + port);
        mWebSocketClient = new MyWebSocketClient(uri);
        mWebSocketClient.connect();
    }

    public void send(String data) {
        if (mWebSocketClient != null) {
            mWebSocketClient.send(data);
        }
    }

    public class MyWebSocketClient extends WebSocketClient {

        public MyWebSocketClient(URI serverUri) {
            super(serverUri);
        }

        @Override
        public void onOpen(ServerHandshake serverHandshake) {
            Log.e(TAG, "onOpen");
            if (mOnConnectListener != null) {
                mOnConnectListener.onSuccess();
            }
        }

        @Override
        public void onMessage(String message) {
            Log.e(TAG, "onMessage message:" + message);
            if (message == null) {
                return;
            }
            TransferExtra bean = new Gson().fromJson(message, TransferExtra.class);

            Intent intent = new Intent(MyApplication.sContext, ResultActivity.class);

            intent.putExtra("model", bean.model);
            intent.putExtra("version", bean.version);
            intent.putExtra("transType", bean.transType);
            intent.putExtra("packageName", bean.packageName);

            intent.putExtra("terminalId", bean.terminalId);
            intent.putExtra("merchantId", bean.merchantId);
            intent.putExtra("merchantName", bean.merchantName);

            intent.putExtra("transDate", bean.transDate);
            intent.putExtra("transTime", bean.transTime);
            intent.putExtra("operatorId", bean.operatorId);
            intent.putExtra("paymentType", bean.paymentType);

            intent.putExtra("errorMsg", bean.errorMsg);
            intent.putExtra("errorCode", bean.errorCode);
            intent.putExtra("resultCode", bean.resultCode);

            intent.putExtra("amount", bean.amount);

            intent.putExtra("authNo", bean.authNo);
            intent.putExtra("batchNo", bean.batchNo);
            intent.putExtra("transId", bean.transId);
            intent.putExtra("voucherNo", bean.voucherNo);
            intent.putExtra("voucherNo", bean.voucherNo);
            intent.putExtra("qrOrderNo", bean.qrOrderNo);
            intent.putExtra("referenceNo", bean.referenceNo);

            intent.putExtra("issue", bean.issue);
            intent.putExtra("cardNo", bean.cardNo);
            intent.putExtra("acquire", bean.acquire);
            intent.putExtra("cardType", bean.cardType);
            intent.putExtra("accountType", bean.accountType);

            intent.putExtra("answerCode", bean.answerCode);
            intent.putExtra("transactionType", bean.transactionType);
            intent.putExtra("transactionPlatform", bean.transactionPlatform);

            intent.putExtra("qrCodeScanModel", bean.qrCodeScanModel);
            intent.putExtra("qrCodeTransactionState", bean.qrCodeTransactionState);

            intent.putExtra("merchantNameEn", bean.merchantNameEn);

            intent.putExtra("balance", bean.balance);

            intent.putExtra("transNum", bean.transNum);
            intent.putExtra("totalAmount", bean.totalAmount);

            intent.putExtra("settleJson", bean.settleJson);

            intent.putExtra("bean", bean);

            intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
            MyApplication.sContext.startActivity(intent);
        }

        @Override
        public void onClose(int code, String reason, boolean remote) {
            Log.e(TAG, "onClose reason:" + reason);
            if (mOnConnectListener != null) {
                mOnConnectListener.onFailed();
            }
        }

        @Override
        public void onError(Exception ex) {
            Log.e(TAG, "onError");
            MyApplication.sInstance.post(
                    () -> Toast.makeText(MyApplication.sContext, R.string.error_timeout, Toast.LENGTH_SHORT).show()
            );
        }

    }


}
