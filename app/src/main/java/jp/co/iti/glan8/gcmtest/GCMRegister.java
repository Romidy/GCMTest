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
    private static final String GCM_PREF_NAME = "GCM";
    private static final String KEY_REG_ID = "RegistrationId";

    /**
     * 登録済の RegistrationId を返す
     *
     * @param context
     * @return null=未登録
     */
    public static String getRegistrationId(Context context) {
        SharedPreferences prefs = context.getSharedPreferences(GCM_PREF_NAME, Context.MODE_PRIVATE);
        String regId = prefs.getString(KEY_REG_ID, null);
        Log.d(TAG, "getRegistrationId:" + regId);
        return regId;
    }

    /**
     * GCM3.0の InstanceID から RegistrationId を取得する
     * <li>RegistrationId は Preferences に保存する</li>
     *
     * @param context
     * @param senderId アプリのSENDER_ID
     * @return RegistrationId (null=取得失敗)
     */
    public static String registerSync(final Context context, final String senderId) {
        SharedPreferences prefs = context.getSharedPreferences(GCM_PREF_NAME, Context.MODE_PRIVATE);
        try {
            InstanceID instanceID = InstanceID.getInstance(context);
            String regId = instanceID.getToken(senderId, GoogleCloudMessaging.INSTANCE_ID_SCOPE, null);
            Log.d(TAG, "registerSync: " + senderId + ":" + regId);
            prefs.edit().putString(KEY_REG_ID, regId).apply();
            return regId;
        } catch (Exception e) {
            Log.e(TAG, "Failed get token:" + senderId, e);
            prefs.edit().putString(KEY_REG_ID, null).apply();
            return null;
        }
    }

    public static void onDestroy(Context context) {
    }
}
