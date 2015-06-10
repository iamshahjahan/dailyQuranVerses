package com.example.shiza.dailyquranverses;

import android.app.Activity;
import android.content.res.Resources;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewDebug;
import android.widget.Toast;

import java.util.Random;


public class MainActivity extends Activity {
//    Resources   resources = getResources();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

    }

    public int GetRandom(int min,int max)
    {
            Random ran = new Random();
            return ran.nextInt((max-min) + 1) + min;
    }
    public void GenerateVerse(View view)
    {

            String[] chapterName = getResources().getStringArray(R.array.chapters);

            int chapter_no = GetRandom(1,114);

            Toast.makeText(this,"The chapter is: " + chapterName[chapter_no - 1],Toast.LENGTH_LONG).show();
            String chapter_array_name = "chapter_" + chapter_no;

            int id = getResources().getIdentifier(chapter_array_name, "array",this.getPackageName());
            String[] chapter = getResources().getStringArray(id);

            int random_verse = GetRandom(1,chapter.length);
            Toast.makeText(this, "chapter_name is " + chapterName[chapter_no - 1] + "verse no is " + random_verse + "The verse is: " + chapter[random_verse],Toast.LENGTH_LONG).show();

    }


}
