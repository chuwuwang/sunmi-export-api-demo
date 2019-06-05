package com.sm.l3.demo.print;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;

import com.sm.l3.demo.MyApplication;
import com.sm.l3.demo.R;
import com.sm.l3.demo.util.BitmapUtil;

class PrintUtil {

    static String getTransactionPlatform(int platform) {
        switch (platform) {
            case 1:
                return getString(R.string.platform_ali);
            case 2:
                return getString(R.string.platform_wx);
            case 3:
                return "";
            case 4:
                return getString(R.string.platform_union);
            default:
                return "";
        }
    }

    static String getTransactionType(int type) {
        return getTransactionType(type, false);
    }

    static String getTransactionType(int type, boolean isSymbol) {
        switch (type) {
            case 1:
                return getString(R.string.sale);
            case 2:
                return getString(R.string.sale_void);
            case 3:
                return getString(R.string.refund);
            case 4:
                return getString(R.string.pre_auth);
            case 5:
                if (isSymbol) {
                    return getString(R.string.pre_auth_revoke_symbol);
                } else {
                    return getString(R.string.pre_auth_revoke);
                }
            case 6:
                if (isSymbol) {
                    return getString(R.string.pre_auth_complete_symbol);
                } else {
                    return getString(R.string.pre_auth_complete);
                }
            case 7:
                if (isSymbol) {
                    return getString(R.string.pre_auth_complete_revoke_symbol);
                } else {
                    return getString(R.string.pre_auth_complete_revoke);
                }
            default:
                return "";
        }
    }

    static Bitmap getReceiptTitleIcon(int platform) {
        Bitmap bitmap;
        switch (platform) {
            case 0:
                bitmap = BitmapFactory.decodeResource(MyApplication.getInstance().getResources(), R.drawable.print_third);
                break;
            case 1:
                bitmap = BitmapFactory.decodeResource(MyApplication.getInstance().getResources(), R.drawable.print_ali);
                break;
            case 2:
                bitmap = BitmapFactory.decodeResource(MyApplication.getInstance().getResources(), R.drawable.print_wx);
                break;
            case 4:
                bitmap = BitmapFactory.decodeResource(MyApplication.getInstance().getResources(), R.drawable.print_union);
                break;
            default:
                bitmap = BitmapFactory.decodeResource(MyApplication.getInstance().getResources(), R.drawable.print_third);
                break;
        }
        if (bitmap.getWidth() > 384) {
            double val = 1.0 * bitmap.getHeight() * 384 / bitmap.getWidth();
            int newHeight = (int) val;
            bitmap = BitmapUtil.scale(bitmap, 384, newHeight);
        }
        return bitmap;
    }

    private static String getString(int resId) {
        return MyApplication.sContext.getString(resId);
    }


}
