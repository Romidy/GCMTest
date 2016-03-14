package jp.co.iti.glan8.gcmtest;

import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.NotificationCompat;
import android.util.Log;

import com.google.android.gms.gcm.GcmListenerService;

/**
 * GCM のメッセージを受け取るサービス。
 *
 */
public class GCMReceiverService extends GcmListenerService {
    private static final String TAG = GCMReceiverService.class.getSimpleName();

    public static final int NOTIFICATION_ID = 1;
    private NotificationManager notificationManager;
    private NotificationCompat.Builder builder;


    /**
     * receive message from GCM Communication Server
     */
    @Override
    public void onMessageReceived(String from, Bundle data) {

        /*
        "data" property from received
            {
                "registration_ids": ["REGID1", "REGID2"],
                "data": {"title":"Yellow", "message":"YELLOW!"}
            }
         */
        String title = data.getString("title");
        String message = data.getString("message");

        Log.d(TAG, "onMessageReceived()");
        Log.d(TAG, "title: " + title);
        Log.d(TAG, "message: " + message);

        sendNotification(title, message);
    }

    private void sendNotification(String title, String msg) {
        //notification
        Notification notification = new Notification.Builder(this)
                .setContentTitle(title)
                .setContentText(msg)
                .setAutoCancel(true)
                .setSmallIcon(R.mipmap.ic_launcher)
                .build();

        NotificationManager manager = (NotificationManager) getSystemService(NOTIFICATION_SERVICE);
        manager.notify(0, notification);

//        notificationManager = (NotificationManager)
//                this.getSystemService(Context.NOTIFICATION_SERVICE);
//
//        PendingIntent contentIntent = PendingIntent.getActivity(this, 0,
//                new Intent(this, MainActivity.class), 0);
//
//        builder = new NotificationCompat.Builder(this).setContentTitle("GCM Notification").setStyle(new NotificationCompat.BigTextStyle().bigText(title)).setContentText(msg);
//
//        builder.setContentIntent(contentIntent);
//        notificationManager.notify(NOTIFICATION_ID, builder.build());
    }

}