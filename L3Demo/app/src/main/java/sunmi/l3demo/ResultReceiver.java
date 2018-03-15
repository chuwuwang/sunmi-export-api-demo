package sunmi.l3demo;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;

/**
 * 结果接收者
 *
 * @author xurong on 2017/5/15.
 */
public class ResultReceiver extends BroadcastReceiver {

    @Override
    public void onReceive(final Context context, final Intent intent) {
        intent.setClass(context, ResultActivity.class);
        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        context.startActivity(intent);
    }

}
