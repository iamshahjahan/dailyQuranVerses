package com.example.shiza.dailyquranverses;

import android.app.AlarmManager;
import android.app.PendingIntent;
import android.content.ActivityNotFoundException;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.FragmentTabHost;
import android.support.v4.view.MenuItemCompat;
import android.support.v7.app.ActionBarActivity;
import android.support.v7.widget.ShareActionProvider;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.SearchView;
import android.widget.SimpleCursorAdapter;
import android.widget.TextView;

import java.util.Calendar;


public class MainActivity extends ActionBarActivity {

    TextView textView;
    //The string for suggestion

    private static String[] SUGGESTIONS;
    String m_Text;
    //    Cursor adapter
    private SimpleCursorAdapter mAdapter;


    ShareActionProvider mShareActionProvider;
    Toolbar toolbar;
    SearchView search;
    private FragmentTabHost mTabHost;
    DailyQuranMethods dailyQuranMethods = new DailyQuranMethods();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

//      Setting up toolbar here

        toolbar = (Toolbar) findViewById(R.id.app_bar);
        toolbar.setLogo(R.mipmap.ic_launcher);
        toolbar.setTitle(" Daily Qura'n Verses");
        setSupportActionBar(toolbar);
//      add tabs in the mainactivity

        super.onPostResume();
        mTabHost = (FragmentTabHost) findViewById(android.R.id.tabhost);
        mTabHost.setup(this, getSupportFragmentManager(), R.id.realtabcontent);

        mTabHost.addTab(mTabHost.newTabSpec("tab1").setIndicator("Today's Verse"),
                TodayVerse.class, null);
        mTabHost.addTab(mTabHost.newTabSpec("tab2").setIndicator("Today's Chapter"),
                TodayChapter.class, null);
        mTabHost.addTab(mTabHost.newTabSpec("tab3").setIndicator("Complete Qur'an"),
                completeQuran.class, null);
        createNotification();

    }

    public void createNotification() {
//        Get current time
        Calendar c = Calendar.getInstance();

//       Create an intent which will create your notification
        Intent alertIntent = new Intent(this, NotificationCreaterWithAlarm.class);

//       calling alarm manager
        AlarmManager alarmManager = (AlarmManager) getSystemService(Context.ALARM_SERVICE);

//the alarm manager will repeatedly call the notification after specified time

        alarmManager.setRepeating(AlarmManager.RTC_WAKEUP, c.getTimeInMillis() + 60 * 60 * 1000, 60 * 60 * 1000, PendingIntent.getBroadcast(this, 1, alertIntent,
                PendingIntent.FLAG_UPDATE_CURRENT));

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {

        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu_main, menu);


//         Set up ShareActionProvider's default share intent
        MenuItem shareItem = menu.findItem(R.id.action_share);
        mShareActionProvider = (ShareActionProvider)
                MenuItemCompat.getActionProvider(shareItem);
        mShareActionProvider.setShareIntent(getDefaultIntent());
        return true;
    }

    private Intent getDefaultIntent() {
        Intent sharingIntent = new Intent(Intent.ACTION_SEND);
        sharingIntent.setType("text/plain");
        String shareBody = "Want to ready daily one verse from the quran. Here is the app of your choice. Download here:" + "http://amzn.to/1JZqRFc" ;
        sharingIntent.putExtra(android.content.Intent.EXTRA_SUBJECT, "Try out Daily Quranic Verses");
        sharingIntent.putExtra(android.content.Intent.EXTRA_TEXT, shareBody);
        return sharingIntent;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item)
    {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        switch (item.getItemId()) {
            case R.id.action_rateUs:
                Uri uri = Uri.parse("market://details?id=" + this.getPackageName());
                Intent goToMarket = new Intent(Intent.ACTION_VIEW, uri);
                try {
                    startActivity(goToMarket);
                } catch (ActivityNotFoundException e) {
                    startActivity(new Intent(Intent.ACTION_VIEW, Uri.parse("http://play.google.com/store/apps/details?id=" + this.getPackageName())));
                }

            default:
                return super.onOptionsItemSelected(item);
        }
    }
}
