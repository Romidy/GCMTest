package jp.co.iti.glan8.gcmtest;

import android.app.IntentService;
import android.content.Context;
import android.content.Intent;
import android.os.Handler;
import android.os.Looper;
import android.util.Log;

/**
 * GCMのRegistrationIdの登録・登録解除を処理をするサービス。
 * <li>アプリサーバへ登録・登録解除処理を行う。</li>
 *
 * @author kotemaru@kotemaru.org
 */
public class GCMRegisterService extends IntentService {
    private static final String SENDER_ID = "329383810024";

    private static final String TAG = GCMRegisterService.class.getSimpleName();
    private Handler toaster = new Handler(Looper.getMainLooper());

    public GCMRegisterService() {
        super("GCMRegisterService");
    }

    @Override
    protected void onHandleIntent(Intent intent) {
        String regId = GCMRegister.registerSync(this, SENDER_ID);
    }
}
