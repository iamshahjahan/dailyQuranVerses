package com.example.shiza.dailyquranverses;

import android.app.AlarmManager;
import android.app.AlertDialog;
import android.app.PendingIntent;
import android.app.SearchManager;
import android.content.ActivityNotFoundException;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.database.Cursor;
import android.database.MatrixCursor;
import android.net.Uri;
import android.os.Bundle;
import android.provider.BaseColumns;
import android.support.v4.app.FragmentTabHost;
import android.support.v4.view.MenuItemCompat;
import android.support.v7.app.ActionBarActivity;
import android.support.v7.widget.ShareActionProvider;
import android.support.v7.widget.Toolbar;
import android.text.InputType;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.CursorAdapter;
import android.widget.EditText;
import android.widget.SearchView;
import android.widget.SimpleCursorAdapter;
import android.widget.TextView;
import android.widget.Toast;

import java.util.Calendar;
import java.util.Random;

import static android.app.AlertDialog.*;


public class MainActivity extends ActionBarActivity {

    TextView textView;
    //The string for suggestion

    private static String[] SUGGESTIONS ;
    String m_Text;
//    Cursor adapter
    private SimpleCursorAdapter mAdapter;


    ShareActionProvider mShareActionProvider;
    Toolbar toolbar;
    SearchView search;
    private FragmentTabHost mTabHost;

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

        mTabHost = (FragmentTabHost) findViewById(android.R.id.tabhost);
        mTabHost.setup(this, getSupportFragmentManager(), R.id.realtabcontent);

        mTabHost.addTab(mTabHost.newTabSpec("tab1").setIndicator("Today's Verse"),
                TodayVerse.class, null);
        mTabHost.addTab(mTabHost.newTabSpec("tab2").setIndicator("Today's Chapter"),
                TodayChapter.class, null);
        mTabHost.addTab(mTabHost.newTabSpec("tab3").setIndicator("Complete Qur'an"),
                completeQuran.class, null);
//      set Alarm for notification creation
        createNotification();

        SUGGESTIONS = getQuranVerses();
//        textView = ( TextView)findViewById(R.id.verseTotal);
//
//        textView.setText("Length is " + SUGGESTIONS.length);
        search();

    }

    public void createNotification()
    {
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


    public void search()
    {
        //      Use search view on the top of your app

        search = (SearchView) findViewById(R.id.mySearchView);
        search.setQueryHint("Search Qur'an");

//      create a suggestion adapter for dropdown
        final String[] from = new String[] {"cityName"};
        final int[] to = new int[] {android.R.id.text1};
        mAdapter = new SimpleCursorAdapter(this,
                android.R.layout.simple_list_item_2,
                null,
                from,
                to,
                CursorAdapter.FLAG_REGISTER_CONTENT_OBSERVER);

        search.setSuggestionsAdapter(mAdapter);

        search.setOnSuggestionListener(new SearchView.OnSuggestionListener() {
            @Override
            public boolean onSuggestionClick(int position) {
                // Your code here


                Cursor theCursor = (Cursor) mAdapter.getCursor();
                String selectedItem = theCursor.getString(position);
                Toast.makeText(getBaseContext(), " on suggestion click position and item is" + position + selectedItem, Toast.LENGTH_LONG).show();


//                startActivity(new Intent(getBaseContext(), SearchResultsActivity.class));

                return true;
            }

            @Override
            public boolean onSuggestionSelect(int position) {
                // Your code here
                Toast.makeText(getBaseContext(), " on suggestion select position is" + position, Toast.LENGTH_LONG).show();

                startActivity(new Intent(getBaseContext(), SearchResultsActivity.class));

                return true;
            }
        });

        search.setOnQueryTextListener(new SearchView.OnQueryTextListener() {

            @Override
            public boolean onQueryTextSubmit(String query) {
                // TODO Auto-generated method stub
                startActivity(new Intent(getBaseContext(), SearchResultsActivity.class));

                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                // TODO Auto-generated method stub
                populateAdapter(newText);

                return false;
            }


        });



    }
//    This is for adapter population

    private void populateAdapter(String query) {
        final MatrixCursor c = new MatrixCursor(new String[]{ BaseColumns._ID, "cityName" });
        int j = 0;
//        int k = 0;
        if ( query.length() > 3 )
        {
//            k = GetRandom(0,60);
//            Toast.makeText(getApplicationContext(),"K is " + k,Toast.LENGTH_LONG).show();
            for (int i = 0 ; i < 6144 ; i++)
            {

                if (SUGGESTIONS[i].toLowerCase().contains(query.toLowerCase()) && SUGGESTIONS[i].length() > 0 )
                {
                    c.addRow(new Object[]{i, SUGGESTIONS[i]});
                    j++;

                    if ( j > 100 )
                    {
                        break;
                    }
                }
            }
            if ( j == 0  )
            {
                c.addRow(new Object[]{0, "No results found." });
            }
        }
        else
        {
            c.addRow(new Object[]{0, "Please enter a string of length 3 ." });
        }


        mAdapter.changeCursor(c);
    }

    public int GetRandom(int min,int max)
    {
        Random ran = new Random();
        return ran.nextInt((max - min) + 1) + min;
    }

    private String[] getQuranVerses()
    {
        String[] whole_quran = new String[7000];
        String[] current_chapter;
        String chapterSize="";
        String[] chapter_names;
        String chapter_array_name;
        int total_verse = 0, verse_in_current_chapter,chapter_number;

        chapter_names = getResources().getStringArray(R.array.chapters);

        for ( chapter_number = 1 ; chapter_number < 114 ; chapter_number++)
        {
//            Grab each chapter containing verse from Quran
            chapter_array_name = "chapter_" + chapter_number;
            int id = this.getResources().getIdentifier(chapter_array_name, "array", this.getPackageName());
            current_chapter = this.getResources().getStringArray(id);

            for ( verse_in_current_chapter = 1; verse_in_current_chapter < current_chapter.length - 1; verse_in_current_chapter++ )
            {
                whole_quran[total_verse] = current_chapter[verse_in_current_chapter] + "," + chapter_names[chapter_number - 1];
                total_verse++;
            }
            chapterSize += chapter_number + ":" +chapter_names[chapter_number - 1] + ":" + current_chapter.length + "\n";
        }
        Toast.makeText(getApplicationContext(),chapterSize,Toast.LENGTH_LONG).show();
        textView = ( TextView)findViewById(R.id.verseTotal);

//        textView.setText(chapterSize);


        return whole_quran;

    }
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {

        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu_main, menu);


        // Set up ShareActionProvider's default share intent
//        MenuItem shareItem = menu.findItem(R.id.action_share);
//        mShareActionProvider = (ShareActionProvider)
//                MenuItemCompat.getActionProvider(shareItem);
//        mShareActionProvider.setShareIntent(getDefaultIntent());
        return true;
    }

//    private Intent getDefaultIntent() {
//        Intent intent = new Intent(Intent.ACTION_SEND);
//        intent.setType("image/*");
//        return intent;
//    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        switch (item.getItemId()) {
//            case R.id.rateUs:
//                Uri uri = Uri.parse("market://details?id=" + this.getPackageName());
//                Intent goToMarket = new Intent(Intent.ACTION_VIEW, uri);
//                try {
//                    startActivity(goToMarket);
//                } catch (ActivityNotFoundException e) {
//                    startActivity(new Intent(Intent.ACTION_VIEW, Uri.parse("http://play.google.com/store/apps/details?id=" + this.getPackageName())));
//                }

            case R.id.search_bar:
                Toast.makeText(this, "Hello from search bar", Toast.LENGTH_LONG).show();

            default:
                return super.onOptionsItemSelected(item);
        }
    }
}
