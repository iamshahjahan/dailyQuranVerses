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

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Random;

/**
 * Created by Shiza on 16-06-2015.
 */
public class NotificationCreaterWithAlarm extends BroadcastReceiver
{
    private static final String MyPREFERENCES = "NOTIFICATION";
    SharedPreferences sharedPreferences;
    SharedPreferences sharedPreferencesChapter;
    SharedPreferences sharedPreferencesVerse;
    private static String TODAY_CHAPTER = "TODAY_CHAPTER";
    private static String TODAY_VERSE = "TODAY_VERSE";


    @Override
    public void onReceive(Context context, Intent intent)
    {
        int notificationID = getNotificationID(context);
        setChapterVerse(context);
//        Toast.makeText(context,"I am on recieve",Toast.LENGTH_LONG).show();
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

    public void setChapterVerse(Context context)
    {

        sharedPreferencesChapter = context.getSharedPreferences(TODAY_CHAPTER, Context.MODE_PRIVATE);
        sharedPreferencesVerse = context.getApplicationContext().getSharedPreferences(TODAY_VERSE, Context.MODE_PRIVATE);
        SharedPreferences.Editor editor_chapter = sharedPreferencesChapter.edit();
        SharedPreferences.Editor editor_verse = sharedPreferencesVerse.edit();

        Calendar c = Calendar.getInstance();

        SimpleDateFormat df = new SimpleDateFormat("ddMMyyyy");
        String today_string = df.format(c.getTime());


        int chapter_no = GetRandom(1, 114);

        String chapter_array_name = "chapter_" + chapter_no;

        String verse = sharedPreferencesVerse.getString(today_string,null);

        if ( verse == null )
        {
            editor_verse.putString(today_string, getVerse(context,chapter_array_name));
            editor_verse.apply();
        }

        int chapter_number = sharedPreferencesChapter.getInt(today_string,0);

        if ( chapter_number == 0 )
        {

            editor_chapter.putInt(today_string,chapter_no);
            editor_chapter.apply();
        }


    }

    public String getVerse(Context context,String chapter_array_name)
    {
        int id = context.getResources().getIdentifier(chapter_array_name, "array", context.getPackageName());
        String[] chapter = context.getResources().getStringArray(id);
        int random_verse = GetRandom(1, chapter.length - 1);
        return chapter[random_verse];
    }
}
