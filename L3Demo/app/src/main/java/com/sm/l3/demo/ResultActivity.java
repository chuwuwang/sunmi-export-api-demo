package com.sm.l3.demo;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.widget.TextView;

import java.util.Set;

public class ResultActivity extends AppCompatActivity {

    private static final String TAG = "ResultActivity";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_result);

        TextView tvResult = findViewById(R.id.tv_result);

        Intent intent = getIntent();

        StringBuilder sb = new StringBuilder();

        try {
            Set<String> keySet = intent.getExtras().keySet();
            for (String key : keySet) {
                String temp;
                Object obj = intent.getExtras().get(key);
                if (obj != null) {
                    String value = obj.toString();
                    temp = "key = " + key + " || value = " + value;
                } else {
                    temp = "key = " + key + " || value = null";
                }
                Log.e(TAG, temp);
                sb.append(temp);
                sb.append("\n");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        String result = sb.toString();
        Log.e(TAG, "result: " + result);

        tvResult.setText(result);
    }


}
