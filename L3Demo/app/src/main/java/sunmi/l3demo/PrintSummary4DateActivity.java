package sunmi.l3demo;

import android.app.Activity;
import android.app.DatePickerDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.DatePicker;
import android.widget.TextView;
import android.widget.Toast;

import java.util.Calendar;

/**
 * @author Created by longx on 2017/12/20.
 */

public class PrintSummary4DateActivity extends Activity {

    private int year, month, day;
    private TextView txtDate;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_print_summary_4_date);
        txtDate = (TextView) this.findViewById(R.id.text_date);
        getDate();
        showDate();
    }

    private void showDate() {
        String str = "当前选择时间：%s/%s/%s";
        txtDate.setText(String.format(str, year, month, day));
    }

    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btn_select_date:
                DatePickerDialog datePickerDialog = new DatePickerDialog(this, onDateSetListener, year, month - 1, day);
                datePickerDialog.show();
                break;
            case R.id.btn_print_summary:
                printSummary();
                break;
        }
    }

    private void printSummary() {
        Intent intent = new Intent("sunmi.payment.L3");
        intent.putExtra("appId", getPackageName());
        intent.putExtra("transId", System.currentTimeMillis() + "");
        intent.putExtra("summary_date", year + "" + month + "" + day);
        intent.putExtra("transType", 23);
        if (Util.isIntentExisting(intent, this)) {
            startActivity(intent);
        } else {
            Toast.makeText(this, "此机器上没有安装L3应用", Toast.LENGTH_SHORT).show();
        }
    }

    private DatePickerDialog.OnDateSetListener onDateSetListener = new DatePickerDialog.OnDateSetListener() {
        @Override
        public void onDateSet(DatePicker view, int year, int monthOfYear, int dayOfMonth) {
            PrintSummary4DateActivity.this.year = year;
            PrintSummary4DateActivity.this.month = monthOfYear + 1;
            PrintSummary4DateActivity.this.day = dayOfMonth;
            showDate();
        }
    };

    //获取当前日期
    private void getDate() {
        Calendar cal = Calendar.getInstance();
        year = cal.get(Calendar.YEAR);       //获取年月日时分秒
        month = cal.get(Calendar.MONTH) + 1;   //获取到的月份是从0开始计数
        day = cal.get(Calendar.DAY_OF_MONTH);
    }

}
