package com.ct.fitness.ui;

import android.app.NotificationManager;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.support.v4.app.NotificationCompat;

import com.ct.fitness.R;

import static android.support.v4.app.NotificationCompat.DEFAULT_ALL;

/**
 * Created by Elia on 12/19/2017.
 */

public class AlarmNotificationReciever extends BroadcastReceiver {
    @Override
    public void onReceive(Context context, Intent intent) {
        NotificationCompat.Builder notification=new NotificationCompat.Builder(context);
        notification.setAutoCancel(true).setDefaults(DEFAULT_ALL).setContentInfo("Information");
        notification.setContentTitle("Its time").setContentText("Its time to make some exercises");
        notification.setWhen(System.currentTimeMillis()).setSmallIcon(R.drawable.notification_icon_background);

        NotificationManager notificationManager=(NotificationManager)context.getSystemService(Context.NOTIFICATION_SERVICE);
    notificationManager.notify(1,notification.build());
    }
}
