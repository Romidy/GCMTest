package jp.co.iti.glan8.gcmtest;

import android.content.Context;
import android.content.SharedPreferences;
import android.util.Log;

import com.google.android.gms.gcm.GoogleCloudMessaging;
import com.google.android.gms.iid.InstanceID;

/**
 */
public class GCMRegister {
    private static final String TAG = GCMRegister.class.getSimpleName();

    /**
     * GCM3.0の InstanceID から RegistrationId を取得する。
     * <li>RegistrationId は Preferences に保存する。</li>
     * <li>通信をするのでUI-Threadでは実行不可</li>
     *
     * @param context
     * @param senderId アプリのSENDER_ID
     * @return RegistrationId (null=取得失敗)
     */
    public static String registerSync(final Context context, final String senderId) {
        try {
            Log.d(TAG, "senderId: " + senderId);
            InstanceID instanceID = InstanceID.getInstance(context);
            String regId = instanceID.getToken(senderId, GoogleCloudMessaging.INSTANCE_ID_SCOPE, null);
            Log.d(TAG, "registerSync: " + senderId + ":" + regId);
            return regId;
        } catch (Exception e) {
            Log.e(TAG, "Failed get token:" + senderId, e);
            return null;
        }
    }

    public static void onDestroy(Context context) {
    }
}
