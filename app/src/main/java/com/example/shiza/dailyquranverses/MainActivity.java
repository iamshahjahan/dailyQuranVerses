package com.example.shiza.dailyquranverses;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.view.MenuItemCompat;
import android.support.v7.app.ActionBarActivity;
import android.support.v7.widget.ShareActionProvider;
import android.support.v7.widget.Toolbar;
import android.text.method.ScrollingMovementMethod;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import java.util.Random;


public class MainActivity extends ActionBarActivity {

    private ShareActionProvider mShareActionProvider;
    private Toolbar toolbar;
    TextView textView;
    int id;
    String[] chapterName;
    int chapter_no;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        toolbar = (Toolbar) findViewById(R.id.app_bar);
        toolbar.setLogo(R.mipmap.ic_launcher);
//        toolbar.setTitle(" Daily Qura'n Verses");
        setSupportActionBar(toolbar);
        textView = (TextView) findViewById(R.id.verse);

        chapterName = getResources().getStringArray(R.array.chapters);

        chapter_no = GetRandom(1, 114);

        String chapter_array_name = "chapter_" + chapter_no;

        id = getResources().getIdentifier(chapter_array_name, "array", this.getPackageName());
        String[] chapter = getResources().getStringArray(id);

        int random_verse = GetRandom(1, chapter.length);

        textView.setText(chapter[random_verse] + "\nChapter:" + chapterName[chapter_no - 1]);
        textView.setMovementMethod(new ScrollingMovementMethod());


    }

    public int GetRandom(int min, int max) {
        Random ran = new Random();
        return ran.nextInt((max - min) + 1) + min;
    }

    public void GetQuran(View view) {
        Intent intent = new Intent(this, Quran.class);
        startActivity(intent);
    }

    public void GetChapter(View view) {
        Intent intent = new Intent(this, Chapter.class);
        intent.putExtra("chapter_id", id);
        intent.putExtra("chapter_name", chapterName[chapter_no - 1]);
        startActivity(intent);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {

        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu_main, menu);

        // Set up ShareActionProvider's default share intent
        MenuItem shareItem = menu.findItem(R.id.action_share);
        mShareActionProvider = (ShareActionProvider)
                MenuItemCompat.getActionProvider(shareItem);
        mShareActionProvider.setShareIntent(getDefaultIntent());
        return super.onCreateOptionsMenu(menu);
    }
    private Intent getDefaultIntent() {
        Intent intent = new Intent(Intent.ACTION_SEND);
        intent.setType("image/*");
        return intent;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        switch (item.getItemId())
        {
            case R.id.rateUs:
                Toast.makeText(this, "Hello from rate us", Toast.LENGTH_LONG).show();
                return true;

            case R.id.action_share:
                Toast.makeText(this, "Hello from share", Toast.LENGTH_LONG).show();

            default:
                return super.onOptionsItemSelected(item);
        }
    }

}
