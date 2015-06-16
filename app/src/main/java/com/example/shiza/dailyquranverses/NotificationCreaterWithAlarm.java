package com.example.shiza.dailyquranverses;

import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v4.app.NotificationCompat;
import android.support.v4.app.TaskStackBuilder;
import android.widget.Toast;

import java.util.Random;

/**
 * Created by Shiza on 16-06-2015.
 */
public class NotificationCreaterWithAlarm extends BroadcastReceiver
{
    private static final String MyPREFERENCES = "NOTIFICATION";
    SharedPreferences sharedPreferences;

    @Override
    public void onReceive(Context context, Intent intent)
    {
        int notificationID = getNotificationID(context);
        NotificationCompat.Builder mBuilder = new NotificationCompat.Builder(context);

        mBuilder.setSmallIcon(R.mipmap.ic_launcher);
        mBuilder.setContentTitle("DQV");
        mBuilder.setContentText("Your Today's verse is one tap far!");

        Intent resultIntent = new Intent(context, MainActivity.class);
        TaskStackBuilder stackBuilder = TaskStackBuilder.create(context );
        stackBuilder.addParentStack(MainActivity.class);

// Adds the Intent that starts the Activity to the top of the stack
        stackBuilder.addNextIntent(resultIntent);
        PendingIntent resultPendingIntent = stackBuilder.getPendingIntent(0,PendingIntent.FLAG_UPDATE_CURRENT);
        mBuilder.setContentIntent(resultPendingIntent);
        mBuilder.setDefaults(NotificationCompat.DEFAULT_VIBRATE);

        NotificationManager mNotificationManager = (NotificationManager) context.getSystemService(context.NOTIFICATION_SERVICE);
        mBuilder.setAutoCancel(true);
// notificationID allows you to update the notification later on.
        mNotificationManager.notify(notificationID, mBuilder.build());

    }
    public int GetRandom(int min,int max)
    {
        Random ran = new Random();
        return ran.nextInt((max - min) + 1) + min;
    }
    public int getNotificationID(Context context)
    {
        int notificationID;
        sharedPreferences = context.getSharedPreferences(MyPREFERENCES, Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        notificationID =  sharedPreferences.getInt("notificationID", 0);

        if ( notificationID == 0 )
        {
            notificationID = 1;
        }
        else
        {
            notificationID++;
        }
//        Toast.makeText(context,"the notification id is" + notificationID,Toast.LENGTH_LONG).show();
        editor.putInt("notificationID", notificationID);
        editor.commit();
        return notificationID;
    }
}
