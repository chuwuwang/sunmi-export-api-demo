package com.sm.l3.demo.print;

import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;

import com.sm.l3.demo.MyApplication;
import com.sm.l3.demo.R;

/**
 * @author Created by Lee64 on 2017/8/25.
 */

public class BasePrintContent {

    /**
     * 打印小票Title的图标
     */
    public Bitmap getPrintTitleIcon(int platform) {
        Bitmap bitmap;
        Resources res = MyApplication.getInstance().getResources();
        switch (platform) {
            case 0:
                bitmap = BitmapFactory.decodeResource(res, R.drawable.abc);
                break;
            case 1:
                bitmap = BitmapFactory.decodeResource(res, R.raw.alipay);
                break;
            case 2:
                bitmap = BitmapFactory.decodeResource(res, R.raw.wechat);
                break;
            case 4:
                bitmap = BitmapFactory.decodeResource(res, R.drawable.union_pay_print_head);
                break;
            default:
                bitmap = BitmapFactory.decodeResource(res, R.drawable.abc);
                break;
        }
        return bitmap;
    }

}
