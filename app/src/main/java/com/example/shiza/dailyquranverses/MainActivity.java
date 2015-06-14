package com.example.shiza.dailyquranverses;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.support.v7.widget.Toolbar;
import android.text.method.ScrollingMovementMethod;
import android.view.View;
import android.widget.TextView;

import java.util.Random;


public class MainActivity extends ActionBarActivity
{
    private Toolbar toolbar;
    TextView textView;
    int id;
    String[] chapterName;
    int chapter_no;
    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        toolbar = (Toolbar)findViewById(R.id.app_bar);

        setTitle(" Daily Qura'n Verses");
        textView = (TextView)findViewById(R.id.verse);

        chapterName = getResources().getStringArray(R.array.chapters);

         chapter_no = GetRandom(1,114);

        String chapter_array_name = "chapter_" + chapter_no;

        id = getResources().getIdentifier(chapter_array_name, "array",this.getPackageName());
        String[] chapter = getResources().getStringArray(id);

        int random_verse = GetRandom(1,chapter.length);

        textView.setText(chapter[random_verse] +"\nVerse No: " + random_verse + "\nChapter:" + chapterName[chapter_no - 1]);
        textView.setMovementMethod(new ScrollingMovementMethod());


    }

    public int GetRandom(int min,int max)
    {
            Random ran = new Random();
            return ran.nextInt((max-min) + 1) + min;
    }
    public void GetQuran(View view)
    {
        Intent intent = new Intent(this,Quran.class);
        startActivity(intent);
    }
    public void GetChapter(View view)
    {
        Intent intent = new Intent(this,Chapter.class);
        intent.putExtra("chapter_id",id);
        intent.putExtra("chapter_name",chapterName[chapter_no - 1]);
        startActivity(intent);
    }

}
