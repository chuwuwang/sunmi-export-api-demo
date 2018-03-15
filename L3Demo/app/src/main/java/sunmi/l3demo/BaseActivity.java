package sunmi.l3demo;

import android.app.Activity;
import android.content.Intent;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.Toast;

/**
 * @author Created by Lee64 on 2018/3/2.
 */

public class BaseActivity extends Activity {

    /**
     * 添加用户自定义小票内容
     */
    protected Intent addUserCustomTicketContent(Intent intent) {
        EditText mEditUserInfo = (EditText) findViewById(R.id.et_user_info);
        EditText mEditUserCodeInfo = (EditText) findViewById(R.id.et_user_code_info);
        EditText mEditMerchantInfo = (EditText) findViewById(R.id.et_merchant_info);
        EditText mEditMerchantCodeInfo = (EditText) findViewById(R.id.et_merchant_code_info);
        CheckBox mCbPrint = (CheckBox) findViewById(R.id.cb_code_print);

        String printInfo = mEditUserInfo.getText().toString();
        String printCode = mEditUserCodeInfo.getText().toString();
        String printMerchantInfo = mEditMerchantInfo.getText().toString();
        String printMerchantCode = mEditMerchantCodeInfo.getText().toString();
        if (printInfo.length() > 100) {
            Toast.makeText(this, "用户联追加打印请在100字以内", Toast.LENGTH_SHORT).show();
        }
        if (printMerchantInfo.length() > 100) {
            Toast.makeText(this, "商户联追加打印请在100字以内", Toast.LENGTH_SHORT).show();
        }

        boolean checked = mCbPrint.isChecked();
        intent.putExtra("isPrintTicket", checked);
        intent.putExtra("printInfo", printInfo);
        intent.putExtra("printInfo2", printCode);
        intent.putExtra("printMerchantInfo", printMerchantInfo);
        intent.putExtra("printMerchantInfo2", printMerchantCode);

        return intent;
    }

}
