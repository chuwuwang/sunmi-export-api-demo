package com.sm.l3.demo.socket;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import com.sm.l3.demo.R;

public class WebSocketActivity extends AppCompatActivity {

    private EditText mEditIP;
    private TextView mTvStatus;
    private EditText mEditPort;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_web_socket);
        initView();
    }

    private void initView() {
        mEditIP = findViewById(R.id.edit_ip);
        mEditPort = findViewById(R.id.edit_port);
        mTvStatus = findViewById(R.id.tv_status);
        findViewById(R.id.mb_ok).setOnClickListener(
                new View.OnClickListener() {

                    @Override
                    public void onClick(View v) {
                        initWebSocket();
                    }

                }
        );

        mEditIP.setText("10.10.171.9");
        if (WebSocketService.getInstance().mWebSocketClient != null) {
            mTvStatus.setText(R.string.web_socket_success);
        }
    }

    private void initWebSocket() {
        String ip = mEditIP.getText().toString();
        try {
            WebSocketService.getInstance().init(ip, "9100",
                    new WebSocketService.OnConnectListener() {

                        @Override
                        public void onSuccess() {
                            runOnUiThread(
                                    new Runnable() {

                                        @Override
                                        public void run() {
                                            mTvStatus.setText(R.string.web_socket_success);
                                        }

                                    }
                            );
                        }

                        @Override
                        public void onFailed() {
                            runOnUiThread(
                                    new Runnable() {

                                        @Override
                                        public void run() {
                                            mTvStatus.setText(R.string.web_socket_failed);
                                        }

                                    }
                            );
                        }

                    }
            );
        } catch (Exception e) {
            e.printStackTrace();
            mTvStatus.setText(R.string.web_socket_failed);
        }
    }


}
