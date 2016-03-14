package jp.co.iti.glan8.gcmtest;

import android.app.IntentService;
import android.content.Context;
import android.content.Intent;
import android.os.Handler;
import android.os.Looper;
import android.util.Log;

/**
 * GCMのRegistrationIdの登録・登録解除を処理をするサービス
 *
 */
public class GCMRegisterService extends IntentService {

    // https://console.developers.google.comのProject Number。
    public static final String SENDER_ID = "329383810024";

    private static final String TAG = GCMRegisterService.class.getSimpleName();

    public GCMRegisterService() {
        super("GCMRegisterService");
    }

    @Override
    protected void onHandleIntent(Intent intent) {
        String regId = GCMRegister.registerSync(this, SENDER_ID);

        Intent broadcastIntent = new Intent();
        broadcastIntent.putExtra(GCMReceiver.BROADCAST_MESSAGE_ID, regId);
        broadcastIntent.setAction(GCMReceiver.BROADCAST_ACTION_ID);
        getBaseContext().sendBroadcast(broadcastIntent);
    }
}
