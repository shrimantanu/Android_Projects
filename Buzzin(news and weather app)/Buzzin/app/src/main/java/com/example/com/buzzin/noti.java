package com.example.com.buzzin;


import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.NotificationCompat;

/**
 * Created by shaur on 8/26/2017.
 */

public class noti {
    Context cont;

    public noti(Context con) {
        cont = con;
    }

    public void notifyuser() {
        Intent pi = new Intent(cont, MainActivity.class);
        PendingIntent pit = PendingIntent.getService(cont, 987, pi, PendingIntent.FLAG_UPDATE_CURRENT);
        NotificationCompat.Builder mBuilder = (NotificationCompat.Builder) new NotificationCompat.Builder(cont).setColor(ContextCompat.getColor(cont, R.color.color1))
                .setSmallIcon(R.drawable.buzz)
                .setContentTitle("something buzzin")
                .setDefaults(Notification.DEFAULT_VIBRATE).setContentIntent(pit).setContentText("sghfghfhfdjtjdjtdjd").setStyle(new NotificationCompat.DecoratedCustomViewStyle()).setAutoCancel(true).setPriority(Notification.PRIORITY_HIGH);
        NotificationManager nm = (NotificationManager) cont.getSystemService(Context.NOTIFICATION_SERVICE);
nm.notify(675,mBuilder.build());
    }
}