package jp.co.iti.glan8.gcmtest;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;

/**
 */
public class GCMReceiver extends BroadcastReceiver {

    public static Handler handler;
    public static final String BROADCAST_ACTION_ID = "REGISTER_ACTION";
    public static final String BROADCAST_MESSAGE_ID = "message";

    @Override
    public void onReceive(Context context, Intent intent) {

        Bundle bundle = intent.getExtras();
        String message = bundle.getString(BROADCAST_MESSAGE_ID);

        if(handler !=null){
            Message msg = new Message();

            Bundle data = new Bundle();
            data.putString(BROADCAST_MESSAGE_ID, message);
            msg.setData(data);
            handler.sendMessage(msg);
        }
    }

    /**
     * メイン画面の表示を更新
     */
    public void registerHandler(Handler locationUpdateHandler) {
        handler = locationUpdateHandler;
    }
}
